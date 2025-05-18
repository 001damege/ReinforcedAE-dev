package com.mikazukichandamege.reinforcedae;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(ReinforcedAE.MOD_ID)
public class ReinforcedAE {

    public static final String MOD_ID = "reinforcedae";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MOD_ID));

    public ReinforcedAE() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static Registrate registrate() {
        return REGISTRATE.get();
    }
}
