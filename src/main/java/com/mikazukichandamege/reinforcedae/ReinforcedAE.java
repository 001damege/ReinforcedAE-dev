package com.mikazukichandamege.reinforcedae;

import com.mikazukichandamege.reinforcedae.init.InitBlock;
import com.mikazukichandamege.reinforcedae.init.InitBlockEntity;
import com.mikazukichandamege.reinforcedae.init.InitItem;
import com.mikazukichandamege.reinforcedae.init.internal.InitUpgrade;
import com.mikazukichandamege.reinforcedae.registry.ModCreativeTab;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;


@Mod(ReinforcedAE.MOD_ID)
public final class ReinforcedAE {
    public static final String MOD_ID = "reinforcedae";
    public static final String MOD_NAME = "ReinforcedAE";
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
        eventBus.addListener(ModCreativeTab::initExternal);
        eventBus.addListener(this::commonSetup);

        eventBus.addListener((RegisterEvent event) -> {
            if (event.getRegistryKey() == Registries.CREATIVE_MODE_TAB) {
                registerCreativeTab(event.getVanillaRegistry());
                return;
            }
            if (!event.getRegistryKey().equals(Registries.BLOCK)) {
                return;
            }

            InitBlock.register(ForgeRegistries.BLOCKS);
            InitBlockEntity.register(ForgeRegistries.BLOCK_ENTITY_TYPES);
            InitItem.register(ForgeRegistries.ITEMS);
        });
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

    public void registerCreativeTab(Registry<CreativeModeTab> registry) {
        ModCreativeTab.init(registry);
    }
}
