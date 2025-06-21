package com.mikazukichandamege.reinforcedae.datagen;

import appeng.core.localization.LocalizationEnum;
import appeng.datagen.providers.IAE2DataProvider;
import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import com.mikazukichandamege.reinforcedae.registry.ModItem;
import com.mikazukichandamege.reinforcedae.registry.ModTranslation;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public class ModLocalizationProvider implements IAE2DataProvider {
    private final Map<String, String> localization = new HashMap<>();
    private final DataGenerator generator;
    private boolean wasSaved = false;

    protected ModLocalizationProvider(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    public final CompletableFuture<?> run(CachedOutput cache) {
        for (var block : ModBlock.getBlocks()) {
            add("block.reinforcedae." + block.id().getPath(), block.getEnglishName());
        }
        for (var item : ModItem.getItems()) {
            add("item.reinforcedae." + item.id().getPath(), item.getEnglishName());
        }

        addEnum(ModTranslation.class);
        return save(cache, localization);
    }

    private void generateJadeLocalizations() {

    }

    private void addJadeProviderDisplayName(ResourceLocation providerId, String englishName) {
        add("config.jade.plugin_" + providerId.getNamespace() + "." + providerId.getPath(), englishName);
    }

    public <T extends Enum<T> & LocalizationEnum> void addEnum(Class<T> enums) {
        for (var enumConstant : enums.getEnumConstants()) {
            add(enumConstant.getTranslationKey(), enumConstant.getEnglishText());
        }
    }

    public Component component(String key, String text) {
        add(key, text);
        return Component.translatable(key);
    }

    public void add(String key, String text) {
        Preconditions.checkState(!wasSaved, "Cannot add more translations after they were already saved");
        var previous = localization.put(key, text);
        if (previous != null) {
            throw new IllegalStateException("Localization key " + key + "is already translated to: " + previous);
        }
    }

    private void generateLocalizations() {

    }

    private CompletableFuture<?> save(CachedOutput output, Map<String, String> localizations) {
        wasSaved = true;
        var path = this.generator.getPackOutput().getOutputFolder().resolve("assets/reinforcedae/lang/en_us.json");
        var sorted = new TreeMap<>(localizations);
        var jsonLocalization = new JsonObject();
        for (var entry : sorted.entrySet()) {
            jsonLocalization.addProperty(entry.getKey(), entry.getValue());
        }
        return DataProvider.saveStable(output, jsonLocalization, path);
    }

    @Override
    public String getName() {
        return "Localization (en_us)";
    }
}
