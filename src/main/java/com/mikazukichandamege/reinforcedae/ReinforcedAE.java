package com.mikazukichandamege.reinforcedae;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(ReinforcedAE.MOD_ID)
public class ReinforcedAE {

    public static final String MOD_ID = "reinforcedae";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ReinforcedAE() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
