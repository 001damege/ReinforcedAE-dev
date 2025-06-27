package com.mikazukichandamege.reinforcedae.datagen;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;
import com.mikazukichandamege.reinforcedae.registry.RAETranslation;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

public class RAELocalizationProvider extends LanguageProvider {
    protected RAELocalizationProvider(PackOutput output) {
        super(output, ReinforcedAE.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (var item : RAEItem.getItems()) {
            add(item.asItem(), item.getEnglishName());
        }
        for (var block : RAEBlock.getBlocks()) {
            add(block.block(), block.getEnglishName());
        }
        for (var translation : RAETranslation.values()) {
            add(translation.getTranslationKey(), translation.getEnglishText());
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "Language";
    }
}
