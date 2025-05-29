package com.mikazukichandamege.reinforcedae.item.material;

import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemChaos extends Item {
    public ItemChaos(Properties pProperties) {
        super(pProperties
                .rarity(ModRarity.CHAOS));
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
