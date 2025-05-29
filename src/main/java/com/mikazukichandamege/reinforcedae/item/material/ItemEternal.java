package com.mikazukichandamege.reinforcedae.item.material;

import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemEternal extends Item {
    public ItemEternal(Properties pProperties) {
        super(pProperties
                .rarity(ModRarity.ETERNAL));
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
