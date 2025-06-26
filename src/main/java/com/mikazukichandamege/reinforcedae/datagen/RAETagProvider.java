package com.mikazukichandamege.reinforcedae.datagen;

import appeng.core.definitions.BlockDefinition;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RAETagProvider {

    public static class RAEBlockTagProvider extends IntrinsicHolderTagsProvider<Block> {
        protected RAEBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper existing) {
            super(output, Registries.BLOCK, registries, block -> BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow(), ReinforcedAE.MOD_ID, existing);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            Map<BlockDefinition<?>, List<TagKey<Block>>> specialTags = new HashMap<>();
            var mineableTag = List.of(BlockTags.MINEABLE_WITH_PICKAXE);

            for (var block : ModBlock.getBlocks()) {
                for (var desiredTag : specialTags.getOrDefault(block, mineableTag)) {
                    tag(desiredTag).add(block.block());
                }
            }
        }

        @Override
        public String getName() {
            return "Tags (Block)";
        }
    }
}
