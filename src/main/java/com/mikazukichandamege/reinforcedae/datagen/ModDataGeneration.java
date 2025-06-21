package com.mikazukichandamege.reinforcedae.datagen;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ReinforcedAE.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGeneration {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        onGatherData(event.getGenerator(), event.getExistingFileHelper());
    }

    public static void onGatherData(DataGenerator generator, ExistingFileHelper existing) {
        onGatherData(generator, existing, generator.getVanillaPack(true));
    }

    public static void onGatherData(DataGenerator generator, ExistingFileHelper existing, DataGenerator.PackGenerator pack) {
        var localization = new ModLocalizationProvider(generator);

        pack.addProvider(ModLootTableProvider::new);

        pack.addProvider(output -> new ModItemModelProvider(output, existing));
        pack.addProvider(output -> new ModBlockModelProvider(output, existing));
        //pack.addProvider(output -> new ModAdvancementProvider(output, localization));

        pack.addProvider(output -> localization);
    }
}
