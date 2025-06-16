package com.mikazukichandamege.reinforcedae.init;

import appeng.core.definitions.BlockDefinition;
import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

public final class InitBlock {
    private InitBlock() {}

    public static void register(IForgeRegistry<Block> registry) {
        for (BlockDefinition<?> definition : ModBlock.getBlocks()) {
            registry.register(definition.id(), definition.block());
        }
    }
}
