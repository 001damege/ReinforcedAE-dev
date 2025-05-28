package com.mikazukichandamege.reinforcedae.definition;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

@SuppressWarnings("unused")
public final class ModBlock {
    public static final DeferredRegister<Block> DR = DeferredRegister.create(ForgeRegistries.BLOCKS, ReinforcedAE.MOD_ID);

    public static RegistryObject<Block> block(String id, Function<BlockBehaviour.Properties, ? extends Block> factory) {
        return DR.register(id, () -> factory.apply(BlockBehaviour.Properties.of()));
    }

    public static void init(IEventBus modEventBus) {
        DR.register(modEventBus);
    }
}
