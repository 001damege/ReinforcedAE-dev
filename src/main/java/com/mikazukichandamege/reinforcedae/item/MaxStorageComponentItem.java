package com.mikazukichandamege.reinforcedae.item;

import appeng.items.AEBaseItem;
import com.mikazukichandamege.reinforcedae.interfaces.ExStorageComponent;
import net.minecraft.world.item.ItemStack;

public class MaxStorageComponentItem extends AEBaseItem implements ExStorageComponent {
    private final long storageInByte;

    public MaxStorageComponentItem(Properties properties) {
        super(properties);
        this.storageInByte = Long.MAX_VALUE;
    }

    @Override
    public long getBytes(ItemStack stack) {
        return this.storageInByte;
    }

    @Override
    public boolean isStorageComponent(ItemStack stack) {
        return true;
    }
}
