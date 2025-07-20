package com.mikazukichandamege.reinforcedae.common.blockentity;

import appeng.api.config.*;
import appeng.api.inventories.InternalInventory;
import appeng.api.networking.IGridNode;
import appeng.api.networking.energy.IEnergyService;
import appeng.api.networking.energy.IEnergySource;
import appeng.api.networking.ticking.TickRateModulation;
import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.UpgradeInventories;
import appeng.api.util.IConfigManager;
import appeng.blockentity.misc.InscriberBlockEntity;
import appeng.blockentity.misc.InscriberRecipes;
import appeng.recipes.handlers.InscriberProcessType;
import appeng.recipes.handlers.InscriberRecipe;
import appeng.util.ConfigManager;
import appeng.util.inv.AppEngInternalInventory;
import appeng.util.inv.CombinedInternalInventory;
import appeng.util.inv.filter.IAEItemFilter;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Objects;

public class RnfInscriberBlockEntity extends InscriberBlockEntity {
    private static final int MAX_PROCESSING_STEPS = 100;
    private final IUpgradeInventory upgrades;
    private int finalStep;
    private int processingTime = 0;

    private final ConfigManager configManager;

    private final IAEItemFilter baseFilter = new BaseFilter();
    private final AppEngInternalInventory topItemHandler = new AppEngInternalInventory(this, 1, 64, baseFilter);
    private final AppEngInternalInventory bottomItemHandler = new AppEngInternalInventory(this, 1, 64, baseFilter);
    private final AppEngInternalInventory sideItemHandler = new AppEngInternalInventory(this, 1, 64, baseFilter);
    private final InternalInventory inv = new CombinedInternalInventory(this.topItemHandler, this.bottomItemHandler, this.sideItemHandler);

    public RnfInscriberBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState) {
        super(blockEntityType, pos, blockState);
        this.setInternalMaxPower(6400);

        this.upgrades = UpgradeInventories.forMachine(RAEBlock.INSCRIBER, 4, this::saveChanges);
        this.configManager = new ConfigManager(this::onConfigChanged);
    }

    @Override
    public TickRateModulation tickingRequest(IGridNode node, int ticksSinceLastCall) {
        if (this.isSmash()) {
            this.finalStep++;
            if (this.finalStep == 8) {
                final @Nullable InscriberRecipe out = this.getTask();
                if (out != null) {
                    final ItemStack outputCopy = out.getResultItem().copy();

                    if (this.sideItemHandler.insertItem(1, outputCopy, false).isEmpty()) {
                        this.setProgressingTime(0);
                        if (out.getProcessType() == InscriberProcessType.PRESS) {
                            this.topItemHandler.extractItem(0, 1, false);
                            this.bottomItemHandler.extractItem(0, 1, false);
                        }
                        this.sideItemHandler.extractItem(0, 1, false);
                    }
                }
                this.saveChanges();
            } else if (this.finalStep == 16) {
                this.finalStep = 0;
                this.setSmash(false);
                this.markForUpdate();
            }
        } else if (this.hasCraftWork()) {
            getMainNode().ifPresent(grid -> {
                IEnergyService eg = grid.getEnergyService();
                IEnergySource src = this;

                final int speedFactor = (int) switch (this.upgrades.getInstalledUpgrades(RAEItem.SPEED_CARD)) {
                    case 1 -> 1.5;
                    case 2 -> 2.5;
                    case 3 -> 5;
                    case 4 -> 25;
                    default -> 1;
                };
                final int powerConsumption = 10 * speedFactor;
                final double powerThrehold = powerConsumption - 0.01;
                double powerReq = this.extractAEPower(powerConsumption, Actionable.MODULATE, PowerMultiplier.CONFIG);

                if (powerReq <= powerThrehold) {
                    src = eg;
                    powerReq = eg.extractAEPower(powerConsumption, Actionable.SIMULATE, PowerMultiplier.CONFIG);
                }

                if (powerReq > powerConsumption) {
                    src.extractAEPower(powerConsumption, Actionable.MODULATE, PowerMultiplier.CONFIG);
                    this.setProgressingTime(this.getProcessingTime() + speedFactor);
                }
            });

            if (this.getProcessingTime() > this.getMaxProcessingTime()) {
                this.setProgressingTime(this.getMaxProcessingTime());
                final InscriberRecipe out = this.getTask();
                if (out != null) {
                    final ItemStack outputCopy = out.getResultItem().copy();
                    if (this.sideItemHandler.insertItem(1, outputCopy, true).isEmpty()) {
                        this.setSmash(true);
                        this.finalStep = 0;
                        this.markForUpdate();
                    }
                }
            }
        }

        if (this.pushOutputResult()) {
            return TickRateModulation.URGENT;
        }

        return this.hasCraftWork() ? TickRateModulation.URGENT : this.hasAutoExportWork() ? TickRateModulation.SLOWER : TickRateModulation.SLEEP;
    }

    private void onConfigChanged(IConfigManager manager, Setting<?> setting) {
        if (setting == Settings.AUTO_EXPORT) {
            getMainNode().ifPresent((grid, node) -> grid.getTickManager().wakeDevice(node));
        }

        if (setting == Settings.INSCRIBER_SEPARATE_SIDES) {
            markForUpdate();
        }

        if (setting == Settings.INSCRIBER_BUFFER_SIZE) {
            if (configManager.getSetting(Settings.INSCRIBER_BUFFER_SIZE) == YesNo.YES) {
                topItemHandler.setMaxStackSize(0, 64);
                sideItemHandler.setMaxStackSize(0, 64);
                bottomItemHandler.setMaxStackSize(0, 64);
            } else {
                topItemHandler.setMaxStackSize(0, 4);
                sideItemHandler.setMaxStackSize(0, 4);
                bottomItemHandler.setMaxStackSize(0, 4);
            }
        }

        saveChanges();
    }

    private boolean hasAutoExportWork() {
        return !this.sideItemHandler.getStackInSlot(1).isEmpty()
                && configManager.getSetting(Settings.AUTO_EXPORT) == YesNo.YES;
    }

    private boolean pushOutputResult() {
        if (!this.hasAutoExportWork()) {
            return false;
        }

        var pushSides = EnumSet.allOf(Direction.class);
        if (isSeparateSides()) {
            pushSides.remove(this.getTop());
            pushSides.remove(this.getTop().getOpposite());
        }

        for (var dir : pushSides) {
            var target = InternalInventory.wrapExternal(Objects.requireNonNull(level), getBlockPos().relative(dir), dir.getOpposite());

            if (target != null) {
                int startItems = this.sideItemHandler.getStackInSlot(1).getCount();
                this.sideItemHandler.insertItem(1, target.addItems(this.sideItemHandler.extractItem(1, 64, false)), false);
                int endItems = this.sideItemHandler.getStackInSlot(1).getCount();

                if (startItems != endItems) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isSeparateSides() {
        return this.configManager.getSetting(Settings.INSCRIBER_SEPARATE_SIDES) == YesNo.NO;
    }

    private boolean hasCraftWork() {
        var task = this.getTask();
        if (task != null) {
            return sideItemHandler.insertItem(1, task.getResultItem().copy(), true).isEmpty();
        }
        this.setProgressingTime(0);
        return this.isSmash();
    }

    private void setProgressingTime(int progressingTime) {
        this.processingTime = progressingTime;
    }

    public int getMaxProcessingTime() {
        return MAX_PROCESSING_STEPS;
    }
}
