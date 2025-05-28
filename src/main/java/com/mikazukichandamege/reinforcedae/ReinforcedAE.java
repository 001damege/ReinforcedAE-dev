package com.mikazukichandamege.reinforcedae;

import com.mikazukichandamege.reinforcedae.definition.ModCreativeTab;
import com.mikazukichandamege.reinforcedae.definition.ModItem;
import com.mikazukichandamege.reinforcedae.integration.appflux.AppFluxItem;
import com.mikazukichandamege.reinforcedae.integration.ars.ArsItem;
import com.mikazukichandamege.reinforcedae.integration.botania.BotaniaItem;
import com.mikazukichandamege.reinforcedae.integration.mekanism.MekanismItem;
import com.mikazukichandamege.reinforcedae.util.Addon;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(ReinforcedAE.MOD_ID)
public class ReinforcedAE {

    public static final String MOD_ID = "reinforcedae";
    public static final String MOD_NAME = "ReinforcedAE";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ReinforcedAE() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeTab.init(modEventBus);
        ModItem.init(modEventBus);

        if (ModList.get().isLoaded(Addon.Mekanism.getModId())) {
            MekanismItem.init(modEventBus);
        }
        if (ModList.get().isLoaded(Addon.Botania.getModId())) {
            BotaniaItem.init(modEventBus);
        }
        if (ModList.get().isLoaded(Addon.Ars.getModId())) {
            ArsItem.init(modEventBus);
        }
        if (ModList.get().isLoaded(Addon.Appflux.getModId())) {
            AppFluxItem.init(modEventBus);
        }
    }

    public static ResourceLocation makeId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
