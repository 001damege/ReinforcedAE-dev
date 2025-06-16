package com.mikazukichandamege.reinforcedae.init;

import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import com.mikazukichandamege.reinforcedae.registry.ModItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public final class InitItem {
    private InitItem() {}

    public static void register(IForgeRegistry<Item> registry) {
        for (var definition : ModBlock.getBlocks()) {
            registry.register(definition.id(), definition.asItem());
        }
        for (var definition : ModItem.getItems()) {
            registry.register(definition.id(), definition.asItem());
        }
    }
}
