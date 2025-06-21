package com.mikazukichandamege.reinforcedae.datagen;

import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class ModLootTableProvider extends LootTableProvider {
    protected ModLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(new SubProviderEntry(BlockLootProvider::new, LootContextParamSets.BLOCK)));
    }

    private static class BlockLootProvider extends BlockLootSubProvider {
        protected BlockLootProvider() {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
        }

        @Override
        public void generate() {
            for (var block : ModBlock.getBlocks()) {
                add(block.block(), LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block))
                        .when(ExplosionCondition.survivesExplosion())));
            }
        }

        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
            generate();
            map.forEach(consumer);
        }
    }
}
