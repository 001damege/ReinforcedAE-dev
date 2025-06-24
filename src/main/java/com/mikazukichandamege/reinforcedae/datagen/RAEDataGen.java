package com.mikazukichandamege.reinforcedae.datagen;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ReinforcedAE.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RAEDataGen {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var existing = event.getExistingFileHelper();
        var lookup = event.getLookupProvider();
        var localization = new RAELocalizationProvider(output);

        generator.addProvider(event.includeServer(), new RAERecipeProvider(output, lookup));
        generator.addProvider(event.includeServer(), new RAELootTableProvider(output));

        generator.addProvider(event.includeClient(), localization);
    }
}
