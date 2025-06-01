package com.mikazukichandamege.reinforcedae.interfaces;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.AEKeyType;
import appeng.api.storage.cells.ICellWorkbenchItem;
import appeng.me.cells.BasicCellHandler;
import com.google.common.base.Preconditions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public interface OverBasicCellItem extends ICellWorkbenchItem {
    AEKeyType getKeyType();

    long getBytes(ItemStack cellItem);

    long getBytesPerType(ItemStack cellItem);

    int getTotalTypes(ItemStack cellItem);

    default boolean isBlackListed(ItemStack cellItem, AEKey requestedAddition) {
        return false;
    }

    default boolean storableInStorageCell() {
        return false;
    }

    default boolean isStorageCell(ItemStack stack) {
        return true;
    }

    double getIdleDrain();

    default void addCellInformationTooltip(ItemStack stack, List<Component> lines) {
        Preconditions.checkArgument(stack.getItem() == this);
        BasicCellHandler.INSTANCE.addCellInformationToTooltip(stack, lines);
    }

    default Optional<TooltipComponent> getCellTooltipImage(ItemStack stack) {
        Preconditions.checkArgument(stack.getItem() == this);
        return BasicCellHandler.INSTANCE.getTooltipImage(stack);
    }
}
