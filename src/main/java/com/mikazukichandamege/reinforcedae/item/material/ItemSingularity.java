package com.mikazukichandamege.reinforcedae.item.material;

import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemSingularity extends Item {
    public ItemSingularity(Properties pProperties) {
        super(pProperties
                .rarity(ModRarity.SINGULARITY));
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
