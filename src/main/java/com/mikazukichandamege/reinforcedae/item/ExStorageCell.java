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
import com.mikazukichandamege.reinforcedae.interfaces.ExBasicCellItem;
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
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ExStorageCell extends AEBaseItem implements ExBasicCellItem, AEToolItem {

    protected final ItemLike coreItem;
    protected final ItemLike housingItem;
    protected final double idleDrain;
    protected final long totalBytes;
    protected final long bytesPerType;
    protected final int totalTypes;
    private final AEKeyType keyType;

    public ExStorageCell(Properties properties, ItemLike coreItem, ItemLike housingItem, double idleDrain, long inBytes, long bytesPerType, int totalTypes, AEKeyType keyType) {
        super(properties);
        this.idleDrain = idleDrain;
        this.totalBytes = inBytes * 1024;
        this.coreItem = coreItem;
        this.housingItem = housingItem;
        this.bytesPerType = bytesPerType;
        this.totalTypes = totalTypes;
        this.keyType = keyType;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lines, TooltipFlag advancedTooltips) {
        addCellInformationTooltip(stack, lines);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return super.getTooltipImage(stack);
    }

    @Override
    public ConfigInventory getConfigInventory(ItemStack is) {
        return CellConfig.create(keyType.filter(), is);
    }

    @Override
    public IUpgradeInventory getUpgrades(ItemStack stack) {
        return UpgradeInventories.forItem(stack, keyType == AEKeyType.items() ? 4 : 3);
    }

    @Override
    public AEKeyType getKeyType() {
        return this.keyType;
    }

    @Override
    public long getBytes(ItemStack cellItem) {
        return this.totalBytes;
    }

    @Override
    public long getBytesPerType(ItemStack cellItem) {
        return bytesPerType;
    }

    @Override
    public int getTotalTypes(ItemStack cellItem) {
        return totalTypes;
    }

    @Override
    public double getIdleDrain() {
        return idleDrain;
    }

    @Override
    public FuzzyMode getFuzzyMode(ItemStack stack) {
        final String fz = stack.getOrCreateTag().getString("FuzzyMode");
        if (fz.isEmpty()) {
            return FuzzyMode.IGNORE_ALL;
        }
        try {
            return FuzzyMode.valueOf(fz);
        } catch (Throwable t) {
            return FuzzyMode.IGNORE_ALL;
        }
    }

    @Override
    public void setFuzzyMode(ItemStack stack, FuzzyMode fuzzyMode) {
        stack.getOrCreateTag().putString("FuzzyMode", fuzzyMode.name());
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
                    playerInventory.placeItemBackInInventory(new ItemStack(coreItem));

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
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        this.disassembleDrive(player.getItemInHand(hand), level, player);
        return new InteractionResultHolder<>(InteractionResult.sidedSuccess(level.isClientSide()),
                player.getItemInHand(hand));
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        return this.disassembleDrive(stack, context.getLevel(), context.getPlayer())
                ? InteractionResult.sidedSuccess(context.getLevel().isClientSide())
                : InteractionResult.PASS;
    }
}
