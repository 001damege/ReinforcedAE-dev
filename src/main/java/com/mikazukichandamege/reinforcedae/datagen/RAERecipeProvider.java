package com.mikazukichandamege.reinforcedae.datagen;

import appeng.api.util.AEColor;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.core.definitions.AEParts;
import appeng.datagen.providers.tags.ConventionTags;
import appeng.recipes.handlers.InscriberProcessType;
import appeng.recipes.handlers.InscriberRecipeBuilder;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;
import gripe._90.megacells.definition.MEGAItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class RAERecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected RAERecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> save) {

        InscriberRecipeBuilder.inscribe(AEItems.SINGULARITY, RAEItem.OPTICS_PROCESSOR, 1)
                .setTop(Ingredient.of(AEItems.LOGIC_PROCESSOR))
                .setBottom(Ingredient.of(AEItems.ENGINEERING_PROCESSOR))
                .setMode(InscriberProcessType.PRESS)
                .save(save, ReinforcedAE.makeId(RAEItem.OPTICS_PROCESSOR.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.PATTERN_PROVIDER, 1)
                .pattern("ABA")
                .pattern("C C")
                .pattern("ABA")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', AEBlocks.PATTERN_PROVIDER)
                .define('C', RAEItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(AEBlocks.PATTERN_PROVIDER))
                .save(save, ReinforcedAE.makeId(RAEBlock.PATTERN_PROVIDER.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.INTERFACE, 1)
                .pattern("ABA")
                .pattern("C C")
                .pattern("ABA")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', AEBlocks.INTERFACE)
                .define('C', RAEItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(AEBlocks.INTERFACE))
                .save(save, ReinforcedAE.makeId(RAEBlock.INTERFACE.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.REINFORCED_CARD, 2)
                .pattern("AB ")
                .pattern("CDB")
                .pattern("AB ")
                .define('A', ConventionTags.DIAMOND)
                .define('B', Items.NETHERITE_INGOT)
                .define('C', ConventionTags.REDSTONE)
                .define('D', RAEItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(AEItems.ADVANCED_CARD))
                .save(save, ReinforcedAE.makeId(RAEItem.REINFORCED_CARD.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.SPEED_CARD, 1)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', RAEItem.OPTICS_PROCESSOR)
                .define('B', AEItems.SPEED_CARD)
                .define('C', AEItems.SINGULARITY)
                .unlockedBy("hasItem", has(AEItems.SPEED_CARD))
                .save(save, ReinforcedAE.makeId(RAEItem.SPEED_CARD.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.ENERGY_CARD, 1)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', RAEItem.OPTICS_PROCESSOR)
                .define('B', AEItems.ENERGY_CARD)
                .define('C', AEItems.SINGULARITY)
                .unlockedBy("hasItem", has(AEItems.ENERGY_CARD))
                .save(save, ReinforcedAE.makeId(RAEItem.ENERGY_CARD.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.PATTERN_PROVIDER_KIT, 1)
                .pattern("AB")
                .pattern("CD")
                .define('A', AEItems.SINGULARITY)
                .define('B', ConventionTags.REDSTONE)
                .define('C', AEItems.FLUIX_PEARL)
                .define('D', RAEItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(RAEItem.OPTICS_PROCESSOR))
                .save(save, ReinforcedAE.makeId(RAEItem.PATTERN_PROVIDER_KIT.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.INTERFACE_KIT, 1)
                .pattern("AB")
                .pattern("CD")
                .define('A', AEItems.SINGULARITY)
                .define('B', ConventionTags.REDSTONE)
                .define('C', AEItems.SKY_DUST)
                .define('D', RAEItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(RAEItem.OPTICS_PROCESSOR))
                .save(save, ReinforcedAE.makeId(RAEItem.INTERFACE_KIT.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.CELL_COMPONENT_1G, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', RAEItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', MEGAItems.CELL_COMPONENT_256M)
                .unlockedBy("hasItem", has(MEGAItems.CELL_COMPONENT_256M))
                .save(save, ReinforcedAE.makeId(RAEItem.CELL_COMPONENT_1G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.CELL_COMPONENT_4G, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', RAEItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', RAEItem.CELL_COMPONENT_1G)
                .unlockedBy("hasItem", has(RAEItem.CELL_COMPONENT_1G))
                .save(save, ReinforcedAE.makeId(RAEItem.CELL_COMPONENT_4G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.CELL_COMPONENT_16G, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', RAEItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', RAEItem.CELL_COMPONENT_4G)
                .unlockedBy("hasItem", has(RAEItem.CELL_COMPONENT_4G))
                .save(save, ReinforcedAE.makeId(RAEItem.CELL_COMPONENT_16G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.CELL_COMPONENT_64G, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', RAEItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', RAEItem.CELL_COMPONENT_16G)
                .unlockedBy("hasItem", has(RAEItem.CELL_COMPONENT_16G))
                .save(save, ReinforcedAE.makeId(RAEItem.CELL_COMPONENT_64G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.CELL_COMPONENT_256G, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', RAEItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', RAEItem.CELL_COMPONENT_64G)
                .unlockedBy("hasItem", has(RAEItem.CELL_COMPONENT_64G))
                .save(save, ReinforcedAE.makeId(RAEItem.CELL_COMPONENT_256G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEItem.CELL_COMPONENT_CREATIVE, 1)
                .pattern("ABA")
                .pattern("DCD")
                .pattern("ADA")
                .define('A', AEItems.SKY_DUST)
                .define('B', RAEItem.OPTICS_PROCESSOR)
                .define('C', AEItems.SINGULARITY)
                .define('D', RAEItem.CELL_COMPONENT_256G)
                .unlockedBy("hasItem", has(RAEItem.CELL_COMPONENT_256G))
                .save(save, ReinforcedAE.makeId(RAEItem.CELL_COMPONENT_CREATIVE.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.UNIT, 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ABA")
                .define('A', AEBlocks.CRAFTING_UNIT)
                .define('B', RAEItem.OPTICS_PROCESSOR)
                .define('C', AEParts.SMART_DENSE_CABLE.item(AEColor.TRANSPARENT))
                .define('D', AEItems.SINGULARITY)
                .unlockedBy("hasItem", has(AEBlocks.CRAFTING_UNIT))
                .save(save, ReinforcedAE.makeId(RAEBlock.UNIT.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.STORAGE_1G, 1)
                .pattern("AB")
                .define('A', RAEBlock.UNIT)
                .define('B', RAEItem.CELL_COMPONENT_1G)
                .unlockedBy("hasItem", has(AEBlocks.CRAFTING_STORAGE_256K))
                .save(save, ReinforcedAE.makeId(RAEBlock.STORAGE_1G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.STORAGE_4G, 1)
                .pattern("AB")
                .define('A', RAEBlock.UNIT)
                .define('B', RAEItem.CELL_COMPONENT_4G)
                .unlockedBy("hasItem", has(RAEBlock.STORAGE_1G))
                .save(save, ReinforcedAE.makeId(RAEBlock.STORAGE_4G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.STORAGE_16G, 1)
                .pattern("AB")
                .define('A', RAEBlock.UNIT)
                .define('B', RAEItem.CELL_COMPONENT_16G)
                .unlockedBy("hasItem", has(RAEBlock.STORAGE_4G))
                .save(save, ReinforcedAE.makeId(RAEBlock.STORAGE_16G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.STORAGE_64G, 1)
                .pattern("AB")
                .define('A', RAEBlock.UNIT)
                .define('B', RAEItem.CELL_COMPONENT_64G)
                .unlockedBy("hasItem", has(RAEBlock.STORAGE_16G))
                .save(save, ReinforcedAE.makeId(RAEBlock.STORAGE_64G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.STORAGE_256G, 1)
                .pattern("AB")
                .define('A', RAEBlock.UNIT)
                .define('B', RAEItem.CELL_COMPONENT_256G)
                .unlockedBy("hasItem", has(RAEBlock.STORAGE_64G))
                .save(save, ReinforcedAE.makeId(RAEBlock.STORAGE_256G.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.MONITOR, 1)
                .pattern("AB")
                .define('A', RAEBlock.UNIT)
                .define('B', AEParts.STORAGE_MONITOR)
                .unlockedBy("hasItem", has(AEBlocks.CRAFTING_MONITOR))
                .save(save, ReinforcedAE.makeId(RAEBlock.MONITOR.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.IMP_ENERGY, 1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', AEBlocks.DENSE_ENERGY_CELL)
                .define('B', AEItems.SINGULARITY)
                .unlockedBy("hasItem", has(AEBlocks.DENSE_ENERGY_CELL))
                .save(save, ReinforcedAE.makeId(RAEBlock.IMP_ENERGY.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.ADV_ENERGY, 1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', RAEBlock.IMP_ENERGY)
                .define('B', RAEItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(RAEBlock.IMP_ENERGY))
                .save(save, ReinforcedAE.makeId(RAEBlock.ADV_ENERGY.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.PER_ENERGY, 1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', RAEBlock.ADV_ENERGY)
                .define('B', RAEItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(RAEBlock.ADV_ENERGY))
                .save(save, ReinforcedAE.makeId(RAEBlock.PER_ENERGY.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.QUA_ENERGY, 1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', RAEBlock.PER_ENERGY)
                .define('B', RAEItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(RAEBlock.PER_ENERGY))
                .save(save, ReinforcedAE.makeId(RAEBlock.QUA_ENERGY.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.DRIVE, 1)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', AEItems.CAPACITY_CARD)
                .define('B', AEBlocks.DRIVE)
                .define('C', RAEItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(AEBlocks.DRIVE))
                .save(save, ReinforcedAE.makeId(RAEBlock.DRIVE.id().getPath()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAEBlock.INSCRIBER, 1)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', AEItems.CAPACITY_CARD)
                .define('B', AEBlocks.INSCRIBER)
                .define('C', RAEItem.OPTICS_PROCESSOR)
                .unlockedBy("hasItem", has(AEBlocks.INSCRIBER))
                .save(save, ReinforcedAE.makeId(RAEBlock.INSCRIBER.id().getPath()));
    }
}
