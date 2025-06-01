package com.mikazukichandamege.reinforcedae.integration.appflux;

import appeng.api.stacks.AEKeyType;
import appeng.api.storage.StorageCells;
import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.UpgradeInventories;
import appeng.core.localization.PlayerMessages;
import appeng.items.AEBaseItem;
import appeng.util.InteractionUtil;
import com.glodblock.github.appflux.api.IFluxCell;
import com.glodblock.github.appflux.common.caps.CellFEPower;
import com.glodblock.github.appflux.common.me.cell.FECellHandler;
import com.glodblock.github.appflux.common.me.key.type.EnergyType;
import com.glodblock.github.appflux.common.me.key.type.FluxKeyType;
import com.google.common.base.Preconditions;
import com.mikazukichandamege.reinforcedae.definition.ModItem;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ItemOverFECell extends AEBaseItem implements IFluxCell {

    private final ItemLike coreItem;
    private final long totalBytes;
    private final double idleDrain;

    public ItemOverFECell(Properties properties) {
        super(properties.stacksTo(1));
        this.coreItem = ModItem.OVER_STORAGE_COMPONENT.get();
        this.totalBytes = Long.MAX_VALUE;
        this.idleDrain = 5.0;
    }

    @Override
    public AEKeyType getKeyType() {
        return FluxKeyType.TYPE;
    }

    @Override
    public EnergyType getEnergyType() {
        return EnergyType.FE;
    }

    @Override
    public long getBytes(ItemStack itemStack) {
        return this.totalBytes;
    }

    @Override
    public double getIdleDrain() {
        return this.idleDrain;
    }

    @Override
    public void addCellInformationToTooltip(ItemStack stack, List<Component> list) {
        Preconditions.checkArgument(stack.getItem() == this);
        FECellHandler.HANDLER.addCellInformationToTooltip(stack, list);
    }

    @Override
    public Optional<TooltipComponent> getCellTooltipImage(ItemStack stack) {
        return this.getCellTooltipImage(stack);
    }

    @Override
    public IUpgradeInventory getUpgrades(ItemStack stack) {
        return UpgradeInventories.forItem(stack, 1);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        this.disassembleDrive(pPlayer.getItemInHand(pUsedHand), pLevel, pPlayer);
        return new InteractionResultHolder<>(InteractionResult.sidedSuccess(pLevel.isClientSide()), pPlayer.getItemInHand(pUsedHand));
    }

    private boolean disassembleDrive(ItemStack stack, Level level, Player player) {
        if (InteractionUtil.isInAlternateUseMode(player)) {
            if (level.isClientSide()) {
                return false;
            }

            final Inventory playerInventory = player.getInventory();
            var inventory = StorageCells.getCellInventory(stack, null);
            if (inventory != null && playerInventory.getSelected() == stack) {
                var list = inventory.getAvailableStacks();
                if (list.isEmpty()) {
                    playerInventory.setItem(playerInventory.selected, ItemStack.EMPTY);
                    playerInventory.placeItemBackInInventory(new ItemStack(coreItem));
                    for (var upgrade : this.getUpgrades(stack)) {
                        playerInventory.placeItemBackInInventory(upgrade);
                    }
                    playerInventory.placeItemBackInInventory(new ItemStack(AppFluxItem.FLUX_CELL_HOUSING.get()));
                    return true;
                } else {
                    player.displayClientMessage(PlayerMessages.OnlyEmptyCellsCanBeDisassembled.text(), true);
                }
            }
        }
        return false;
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
            return this.disassembleDrive(stack, context.getLevel(), context.getPlayer())
                    ? InteractionResult.sidedSuccess(context.getLevel().isClientSide())
                    : InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advancedTooltip) {
        addCellInformationToTooltip(stack, tooltip);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        var inventory = FECellHandler.HANDLER.getCellInventory(stack, null);
        if (inventory != null) {
            return new ICapabilityProvider() {
                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    if (cap == ForgeCapabilities.ENERGY) {
                        return LazyOptional.of(() -> new CellFEPower(inventory)).cast();
                    }
                    return LazyOptional.empty();
                }
            };
        }
        return super.initCapabilities(stack, nbt);
    }
}
