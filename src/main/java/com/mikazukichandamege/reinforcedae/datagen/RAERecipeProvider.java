package com.mikazukichandamege.reinforcedae.datagen;

import appeng.api.util.AEColor;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.core.definitions.AEParts;
import appeng.datagen.providers.tags.ConventionTags;
import appeng.recipes.handlers.InscriberProcessType;
import appeng.recipes.handlers.InscriberRecipeBuilder;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import com.mikazukichandamege.reinforcedae.registry.ModItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class RAERecipeProvider extends RecipeProvider {
    public RAERecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> save) {

        InscriberRecipeBuilder.inscribe(AEItems.SINGULARITY, ModItem.OPTICS_PROCESSOR, 1)
                .setTop(Ingredient.of(AEItems.LOGIC_PROCESSOR))
                .setBottom(Ingredient.of(AEItems.ENGINEERING_PROCESSOR))
                .setMode(InscriberProcessType.PRESS)
                .save(save, ReinforcedAE.makeId(ModItem.OPTICS_PROCESSOR.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.REINFORCED_PATTERN_PROVIDER, 1)
                .pattern("ABA")
                .pattern("C C")
                .pattern("ABA")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', AEBlocks.PATTERN_PROVIDER)
                .define('C', ModItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(AEBlocks.PATTERN_PROVIDER))
                .save(save, ReinforcedAE.makeId(ModBlock.REINFORCED_PATTERN_PROVIDER.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.REINFORCED_INTERFACE, 1)
                .pattern("ABA")
                .pattern("C C")
                .pattern("ABA")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', AEBlocks.INTERFACE)
                .define('C', ModItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(AEBlocks.INTERFACE))
                .save(save, ReinforcedAE.makeId(ModBlock.REINFORCED_INTERFACE.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.REINFORCED_CARD, 2)
                .pattern("AB ")
                .pattern("CDB")
                .pattern("AB ")
                .define('A', ConventionTags.DIAMOND)
                .define('B', Items.NETHERITE_INGOT)
                .define('C', ConventionTags.REDSTONE)
                .define('D', ModItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(AEItems.ADVANCED_CARD))
                .save(save, ReinforcedAE.makeId(ModItem.REINFORCED_CARD.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.SPEED_CARD, 1)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', ModItem.OPTICS_PROCESSOR)
                .define('B', AEItems.SPEED_CARD)
                .define('C', AEItems.SINGULARITY)
                .unlockedBy("hasItem", has(AEItems.SPEED_CARD))
                .save(save, ReinforcedAE.makeId(ModItem.SPEED_CARD.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.ENERGY_CARD, 1)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', ModItem.OPTICS_PROCESSOR)
                .define('B', AEItems.ENERGY_CARD)
                .define('C', AEItems.SINGULARITY)
                .unlockedBy("hasItem", has(AEItems.ENERGY_CARD))
                .save(save, ReinforcedAE.makeId(ModItem.ENERGY_CARD.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.PATTERN_PROVIDER_KIT, 1)
                .pattern("AB")
                .pattern("CD")
                .define('A', AEItems.SINGULARITY)
                .define('B', ConventionTags.REDSTONE)
                .define('C', AEItems.FLUIX_PEARL)
                .define('D', ModItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(ModItem.OPTICS_PROCESSOR))
                .save(save, ReinforcedAE.makeId(ModItem.PATTERN_PROVIDER_KIT.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.INTERFACE_KIT, 1)
                .pattern("AB")
                .pattern("CD")
                .define('A', AEItems.SINGULARITY)
                .define('B', ConventionTags.REDSTONE)
                .define('C', AEItems.SKY_DUST)
                .define('D', ModItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(ModItem.OPTICS_PROCESSOR))
                .save(save, ReinforcedAE.makeId(ModItem.INTERFACE_KIT.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CELL_COMPONENT_1G, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', ModItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', AEItems.CELL_COMPONENT_256K)
                .unlockedBy("hasItem", has(AEItems.CELL_COMPONENT_256K))
                .save(save, ReinforcedAE.makeId(ModItem.CELL_COMPONENT_1G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CELL_COMPONENT_2G, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', ModItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', ModItem.CELL_COMPONENT_1G)
                .unlockedBy("hasItem", has(ModItem.CELL_COMPONENT_1G))
                .save(save, ReinforcedAE.makeId(ModItem.CELL_COMPONENT_2G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CELL_COMPONENT_8G, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', ModItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', ModItem.CELL_COMPONENT_2G)
                .unlockedBy("hasItem", has(ModItem.CELL_COMPONENT_2G))
                .save(save, ReinforcedAE.makeId(ModItem.CELL_COMPONENT_8G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CELL_COMPONENT_32G, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', ModItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', ModItem.CELL_COMPONENT_8G)
                .unlockedBy("hasItem", has(ModItem.CELL_COMPONENT_8G))
                .save(save, ReinforcedAE.makeId(ModItem.CELL_COMPONENT_32G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CELL_COMPONENT_128G, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', ModItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', ModItem.CELL_COMPONENT_32G)
                .unlockedBy("hasItem", has(ModItem.CELL_COMPONENT_32G))
                .save(save, ReinforcedAE.makeId(ModItem.CELL_COMPONENT_128G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CELL_COMPONENT_CREATIVE, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', ModItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', ModItem.CELL_COMPONENT_128G)
                .unlockedBy("hasItem", has(ModItem.CELL_COMPONENT_128G))
                .save(save, ReinforcedAE.makeId(ModItem.CELL_COMPONENT_CREATIVE.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.CRAFTING_UNIT, 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ABA")
                .define('A', AEBlocks.CRAFTING_UNIT)
                .define('B', ModItem.OPTICS_PROCESSOR)
                .define('C', AEParts.SMART_DENSE_CABLE.item(AEColor.TRANSPARENT))
                .define('D', AEItems.SINGULARITY)
                .unlockedBy("hasItem", has(AEBlocks.CRAFTING_UNIT))
                .save(save, ReinforcedAE.makeId(ModBlock.CRAFTING_UNIT.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.STORAGE_1G, 1)
                .pattern("AB")
                .define('A', ModBlock.CRAFTING_UNIT)
                .define('B', ModItem.CELL_COMPONENT_1G)
                .unlockedBy("hasItem", has(AEBlocks.CRAFTING_STORAGE_256K))
                .save(save, ReinforcedAE.makeId(ModBlock.STORAGE_1G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.STORAGE_2G, 1)
                .pattern("AB")
                .define('A', ModBlock.CRAFTING_UNIT)
                .define('B', ModItem.CELL_COMPONENT_2G)
                .unlockedBy("hasItem", has(ModBlock.STORAGE_1G))
                .save(save, ReinforcedAE.makeId(ModBlock.STORAGE_2G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.STORAGE_8G, 1)
                .pattern("AB")
                .define('A', ModBlock.CRAFTING_UNIT)
                .define('B', ModItem.CELL_COMPONENT_8G)
                .unlockedBy("hasItem", has(ModBlock.STORAGE_2G))
                .save(save, ReinforcedAE.makeId(ModBlock.STORAGE_8G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.STORAGE_32G, 1)
                .pattern("AB")
                .define('A', ModBlock.CRAFTING_UNIT)
                .define('B', ModItem.CELL_COMPONENT_32G)
                .unlockedBy("hasItem", has(ModBlock.STORAGE_8G))
                .save(save, ReinforcedAE.makeId(ModBlock.STORAGE_32G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.STORAGE_128G, 1)
                .pattern("AB")
                .define('A', ModBlock.CRAFTING_UNIT)
                .define('B', ModItem.CELL_COMPONENT_128G)
                .unlockedBy("hasItem", has(ModBlock.STORAGE_32G))
                .save(save, ReinforcedAE.makeId(ModBlock.STORAGE_128G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.CRAFTING_MONITOR, 1)
                .pattern("AB")
                .define('A', ModBlock.CRAFTING_UNIT)
                .define('B', AEParts.STORAGE_MONITOR)
                .unlockedBy("hasItem", has(AEBlocks.CRAFTING_MONITOR))
                .save(save, ReinforcedAE.makeId(ModBlock.CRAFTING_MONITOR.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.IMPROVED_ENERGY_CELL, 1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', AEBlocks.DENSE_ENERGY_CELL)
                .define('B', AEItems.SINGULARITY)
                .unlockedBy("hasItem", has(AEBlocks.DENSE_ENERGY_CELL))
                .save(save, ReinforcedAE.makeId(ModBlock.IMPROVED_ENERGY_CELL.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.ADVANCED_ENERGY_CELL, 1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlock.IMPROVED_ENERGY_CELL)
                .define('B', ModItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(ModBlock.IMPROVED_ENERGY_CELL))
                .save(save, ReinforcedAE.makeId(ModBlock.ADVANCED_ENERGY_CELL.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.PERFECT_ENERGY_CELL, 1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlock.ADVANCED_ENERGY_CELL)
                .define('B', ModItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(ModBlock.ADVANCED_ENERGY_CELL))
                .save(save, ReinforcedAE.makeId(ModBlock.PERFECT_ENERGY_CELL.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlock.QUANTUM_ENERGY_CELL, 1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlock.PERFECT_ENERGY_CELL)
                .define('B', ModItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(ModBlock.PERFECT_ENERGY_CELL))
                .save(save, ReinforcedAE.makeId(ModBlock.QUANTUM_ENERGY_CELL.id().getPath()));
    }
}
