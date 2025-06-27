package com.mikazukichandamege.reinforcedae;

import com.mikazukichandamege.reinforcedae.init.internal.InitUpgrade;
import com.mikazukichandamege.reinforcedae.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(ReinforcedAE.MOD_ID)
public class ReinforcedAE {
    public static final String MOD_ID = "reinforcedae";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation makeId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public ReinforcedAE() {
        this(FMLJavaModLoadingContext.get());
    }

    public ReinforcedAE(FMLJavaModLoadingContext context) {
        IEventBus eventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        eventBus.addListener(this::commonSetup);

        RAEItem.DR.register(eventBus);
        RAECreativeTab.DR.register(eventBus);
        RAEContainer.DR.register(eventBus);
        RAEBlock.DR.register(eventBus);
        RAEBlockEntity.DR.register(eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(this::postRegistrationInitialization).whenComplete((res, err) -> {
            if (err != null) {
                ReinforcedAE.LOGGER.warn(String.valueOf(err));
            }
        });
    }

    public void postRegistrationInitialization() {
        InitUpgrade.register();
    }
}
