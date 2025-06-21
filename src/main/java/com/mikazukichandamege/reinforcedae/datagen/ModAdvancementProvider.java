package com.mikazukichandamege.reinforcedae.datagen;

import appeng.datagen.providers.IAE2DataProvider;
import com.google.common.collect.Sets;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider implements IAE2DataProvider {
    private final PackOutput output;
    private final ModLocalizationProvider localization;

    protected ModAdvancementProvider(PackOutput output, ModLocalizationProvider localization) {
        this.output = output;
        this.localization = localization;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Path path = this.output.getOutputFolder();
        Set<ResourceLocation> rl = Sets.newHashSet();
        var futures = new ArrayList<CompletableFuture<?>>();
        Consumer<Advancement> consumer = (adv) -> {
            if (!rl.add(adv.getId())) {
                throw new IllegalStateException("Duplicate advancement" + adv.getId());
            } else {
                Path path1 = createPath(path, adv);
                futures.add(DataProvider.saveStable(cache, adv.deconstruct().serializeToJson(), path1));
            }
        };
        generateAdvancements(consumer);
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private void generateAdvancements(Consumer<Advancement> consumer) {

    }

    private static Path createPath(Path basePath, Advancement advancement) {
        return basePath.resolve("data/" + advancement.getId().getNamespace() + "/advancements/" + advancement.getId().getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Advancements";
    }
}
