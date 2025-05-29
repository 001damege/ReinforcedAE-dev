package com.mikazukichandamege.reinforcedae.item.material;

import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemInfinity extends Item {
    public ItemInfinity(Properties pProperties) {
        super(pProperties
                .rarity(ModRarity.INFINITY));
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
