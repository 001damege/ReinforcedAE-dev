package com.mikazukichandamege.reinforcedae.item;

import appeng.api.config.FuzzyMode;
import appeng.api.stacks.AEKeyType;
import appeng.api.storage.StorageCells;
import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.UpgradeInventories;
import appeng.core.localization.PlayerMessages;
import appeng.hooks.AEToolItem;
import appeng.items.AEBaseItem;
import appeng.items.contents.CellConfig;
import appeng.util.ConfigInventory;
import appeng.util.InteractionUtil;
import com.mikazukichandamege.reinforcedae.definition.ModItem;
import com.mikazukichandamege.reinforcedae.interfaces.OverBasicCellItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public class OverStorageCell extends AEBaseItem implements OverBasicCellItem, AEToolItem {

    private final ItemLike housingItem;
    private final AEKeyType keyType;

    public OverStorageCell(ItemLike housingItem, AEKeyType keyType) {
        super(new Properties().stacksTo(1));
        this.housingItem = housingItem;
        this.keyType = keyType;
    }

    @Override
    public AEKeyType getKeyType() {
        return this.keyType;
    }

    @Override
    public long getBytes(ItemStack cellItem) {
        return Long.MAX_VALUE;
    }

    @Override
    public long getBytesPerType(ItemStack cellItem) {
        return 0;
    }

    @Override
    public int getTotalTypes(ItemStack cellItem) {
        return 315;
    }

    @Override
    public double getIdleDrain() {
        return 5.0;
    }

    @Override
    public IUpgradeInventory getUpgrades(ItemStack stack) {
        return UpgradeInventories.forItem(stack, keyType == AEKeyType.items() ? 4 : 3);
    }

    @Override
    public ConfigInventory getConfigInventory(ItemStack is) {
        return CellConfig.create(keyType.filter(), is);
    }

    @Override
    public FuzzyMode getFuzzyMode(ItemStack is) {
        final String fz = is.getOrCreateTag().getString("FuzzyMode");
        if (fz.isEmpty()) {
            return FuzzyMode.IGNORE_ALL;
        }
        try {
            return FuzzyMode.valueOf(fz);
        } catch (Exception e) {
            return FuzzyMode.IGNORE_ALL;
        }
    }

    @Override
    public void setFuzzyMode(ItemStack is, FuzzyMode fzMode) {
        is.getOrCreateTag().putString("FuzzyMode", fzMode.name());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        this.disassembleDrive(pPlayer.getItemInHand(pUsedHand), pLevel, pPlayer);
        return new InteractionResultHolder<>(InteractionResult.sidedSuccess(pLevel.isClientSide()),
                pPlayer.getItemInHand(pUsedHand));
    }

    private boolean disassembleDrive(ItemStack stack, Level level, Player player) {
        if (InteractionUtil.isInAlternateUseMode(player)) {
            if (level.isClientSide()) {
                return false;
            }
            final Inventory playerInventory = player.getInventory();
            var inv = StorageCells.getCellInventory(stack, null);
            if (inv != null && playerInventory.getSelected() == stack) {
                var list = inv.getAvailableStacks();
                if (list.isEmpty()) {
                    playerInventory.setItem(playerInventory.selected, ItemStack.EMPTY);
                    playerInventory.placeItemBackInInventory(new ItemStack(ModItem.OVER_STORAGE_COMPONENT.get()));
                    for (var upgrade : this.getUpgrades(stack)) {
                        playerInventory.placeItemBackInInventory(upgrade);
                    }
                    playerInventory.placeItemBackInInventory(new ItemStack(housingItem));
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
}
