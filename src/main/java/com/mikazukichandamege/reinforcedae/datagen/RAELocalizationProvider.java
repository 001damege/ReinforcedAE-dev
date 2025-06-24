package com.mikazukichandamege.reinforcedae.datagen;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import com.mikazukichandamege.reinforcedae.registry.ModItem;
import com.mikazukichandamege.reinforcedae.registry.ModTranslation;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

public class RAELocalizationProvider extends LanguageProvider {
    protected RAELocalizationProvider(PackOutput output) {
        super(output, ReinforcedAE.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (var item : ModItem.getItems()) {
            add(item.asItem(), item.getEnglishName());
        }
        for (var block : ModBlock.getBlocks()) {
            add(block.block(), block.getEnglishName());
        }
        for (var translation : ModTranslation.values()) {
            add(translation.getTranslationKey(), translation.getEnglishText());
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "Language";
    }
}
