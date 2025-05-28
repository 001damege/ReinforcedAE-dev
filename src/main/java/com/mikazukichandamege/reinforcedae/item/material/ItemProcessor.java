package com.mikazukichandamege.reinforcedae.item.material;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class ItemProcessor extends Item {
    public ItemProcessor(Properties pProperties) {
        super(pProperties
                .rarity(Rarity.EPIC));
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
