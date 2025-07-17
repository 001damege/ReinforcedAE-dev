package com.mikazukichandamege.reinforcedae.common.item.cell;

import appeng.api.config.FuzzyMode;
import appeng.api.stacks.AEKeyType;
import appeng.api.stacks.KeyCounter;
import appeng.api.storage.StorageCells;
import appeng.api.storage.cells.CellState;
import appeng.api.storage.cells.StorageCell;
import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.UpgradeInventories;
import appeng.hooks.AEToolItem;
import appeng.items.AEBaseItem;
import appeng.items.contents.CellConfig;
import appeng.util.ConfigInventory;
import appeng.util.InteractionUtil;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;
import io.github.projectet.ae2things.storage.DISKCellHandler;
import io.github.projectet.ae2things.storage.DISKCellInventory;
import io.github.projectet.ae2things.storage.IDISKCellItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ExDISK extends AEBaseItem implements IDISKCellItem, AEToolItem {
    private final ItemLike coreItem;
    private final int bytes;
    private final double idleDrain;
    private final AEKeyType keyType;

    public ExDISK(ItemLike coreItem, int kiloBytes, double idleDrain, AEKeyType keyType) {
        super(new Properties().stacksTo(1).fireResistant());
        this.coreItem = coreItem;
        this.bytes = kiloBytes * 1000;
        this.idleDrain = idleDrain;
        this.keyType = keyType;
    }

    @Override
    public AEKeyType getKeyType() {
        return this.keyType;
    }

    @Override
    public int getBytes(ItemStack stack) {
        return this.bytes;
    }

    @Override
    public double getIdleDrain() {
        return this.idleDrain;
    }

    @Override
    public ConfigInventory getConfigInventory(ItemStack stack) {
        return CellConfig.create(AEKeyType.items().filter(), stack);
    }

    @Override
    public FuzzyMode getFuzzyMode(ItemStack stack) {
        String fz = stack.getOrCreateTag().getString("FuzzyMode");
        if (fz.isEmpty()) {
            return FuzzyMode.IGNORE_ALL;
        } else {
            try {
                return FuzzyMode.valueOf(fz);
            } catch (Throwable t) {
                return FuzzyMode.IGNORE_ALL;
            }
        }
    }

    @Override
    public void setFuzzyMode(ItemStack stack, FuzzyMode fz) {
        stack.getOrCreateTag().putString("FuzzyMode", fz.name());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        this.disassembleDrive(player.getItemInHand(hand), level, player);
        return new InteractionResultHolder<>(InteractionResult.sidedSuccess(level.isClientSide()), player.getItemInHand(hand));
    }

    @Override
    public @Nullable IUpgradeInventory getUpgrades(ItemStack stack) {
        return UpgradeInventories.forItem(stack, 2);
    }

    private boolean disassembleDrive(ItemStack stack, Level level, Player player) {
        if (InteractionUtil.isInAlternateUseMode(player)) {
            if (level.isClientSide()) {
                return false;
            }
            Inventory playerInv = player.getInventory();
            StorageCell inv = StorageCells.getCellInventory(stack, null);
            if (inv != null && playerInv.getSelected() == stack) {
                KeyCounter list = inv.getAvailableStacks();
                if (list.isEmpty()) {
                    playerInv.setItem(playerInv.selected, ItemStack.EMPTY);
                    playerInv.placeItemBackInInventory(new ItemStack(this.coreItem));

                    for(ItemStack upgrade : Objects.requireNonNull(this.getUpgrades(stack))) {
                        playerInv.placeItemBackInInventory(upgrade);
                    }

                    playerInv.placeItemBackInInventory(new ItemStack(RAEItem.DISK_HOUSING));
                    return true;
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
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, List<Component> tooltip, @NotNull TooltipFlag advancedTooltip) {
        tooltip.add(Component.literal("Deep Item Storage disK - Storage for dummies").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
        this.addCellInformationToTooltip(stack, tooltip);
    }

    public static int getColor(ItemStack stack, int tintIndex) {
        if (tintIndex == 1) {
            DISKCellInventory cellInv = DISKCellHandler.INSTANCE.getCellInventory(stack, null);
            CellState cellStatus = cellInv != null ? cellInv.getClientStatus() : CellState.EMPTY;
            return cellStatus.getStateColor();
        } else {
            return 16777215;
        }
    }
}
