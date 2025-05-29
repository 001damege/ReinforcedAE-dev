package com.mikazukichandamege.reinforcedae.definition;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public final class ModBlock {
    public static final DeferredRegister<Block> DR = DeferredRegister.create(ForgeRegistries.BLOCKS, ReinforcedAE.MOD_ID);

    public static void init(IEventBus modEventBus) {
        DR.register(modEventBus);
    }
}
