package com.mikazukichandamege.reinforcedae.item;

import appeng.items.AEBaseItem;
import com.mikazukichandamege.reinforcedae.interfaces.ExStorageComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ExStorageComponentItem extends AEBaseItem implements ExStorageComponent {
    private final long storageInByte;

    public ExStorageComponentItem(Item.Properties properties, long storageInByte) {
        super(properties);
        this.storageInByte = storageInByte;
    }

    @Override
    public long getBytes(ItemStack stack) {
        return this.storageInByte * 1024;
    }

    @Override
    public boolean isStorageComponent(ItemStack stack) {
        return true;
    }
}
