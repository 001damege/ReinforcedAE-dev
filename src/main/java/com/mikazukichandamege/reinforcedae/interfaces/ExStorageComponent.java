package com.mikazukichandamege.reinforcedae.interfaces;

import net.minecraft.world.item.ItemStack;

public interface ExStorageComponent {

    long getBytes(ItemStack stack);

    boolean isStorageComponent(ItemStack stack);
}
