package com.mikazukichandamege.reinforcedae.init;

import com.mikazukichandamege.reinforcedae.registry.ModBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.IForgeRegistry;

public final class InitBlockEntity {
    private InitBlockEntity() {}

    public static void register(IForgeRegistry<BlockEntityType<?>> registry) {
        for (var entry : ModBlockEntity.getBlockEntityTypes().entrySet()) {
            registry.register(entry.getKey(), entry.getValue());
        }
    }
}
