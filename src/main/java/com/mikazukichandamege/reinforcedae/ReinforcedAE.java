package com.mikazukichandamege.reinforcedae;

import com.mikazukichandamege.reinforcedae.init.internal.InitUpgrade;
import com.mikazukichandamege.reinforcedae.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Mod(ReinforcedAE.MOD_ID)
public class ReinforcedAE {
    public static final String MOD_ID = "reinforcedae";
    public static final Logger LOGGER = LogUtils.getLogger();

    static ReinforcedAE INSTANCE;

    public static ResourceLocation makeId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public ReinforcedAE() {
        this(FMLJavaModLoadingContext.get());
    }

    public ReinforcedAE(FMLJavaModLoadingContext context) {
        if (INSTANCE != null) {
            throw new IllegalStateException();
        }
        INSTANCE = this;

        IEventBus eventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        eventBus.addListener(this::commonSetup);

        RAEItem.DR.register(eventBus);
        RAECreativeTab.DR.register(eventBus);
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

    @OnlyIn(Dist.CLIENT)
    public static class ReinforcedAEClient extends ReinforcedAE {
        private static final Logger LOGGER = LoggerFactory.getLogger(ReinforcedAEClient.class);
        public static final ResourceLocation GUIDE_ID = makeId("guide");

        public ReinforcedAEClient() {
            IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

            eventBus.addListener(this::clientSetup);
        }

        private void clientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
               Minecraft minecraft = Minecraft.getInstance();

               try {
                   postClientSetup(minecraft);
               } catch (Throwable e) {
                   LOGGER.error("ReinforcedAE failed postClientSetup", e);
                   throw new RuntimeException(e);
               }
            });
        }

        private void postClientSetup(Minecraft minecraft) {
            RAEScreen.register();
        }
    }
}
