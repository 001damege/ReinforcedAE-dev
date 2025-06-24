package com.mikazukichandamege.reinforcedae.datagen;

import appeng.api.orientation.BlockOrientation;
import appeng.block.crafting.AbstractCraftingUnitBlock;
import appeng.block.crafting.PatternProviderBlock;
import appeng.block.networking.EnergyCellBlock;
import appeng.core.definitions.BlockDefinition;
import appeng.datagen.providers.models.AE2BlockStateProvider;
import appeng.init.client.InitItemModelsProperties;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;

public class ModBlockModelProvider extends AE2BlockStateProvider {
    protected ModBlockModelProvider(PackOutput output, ExistingFileHelper existing) {
        super(output, ReinforcedAE.MOD_ID, existing);
    }

    @Override
    protected void registerStatesAndModels() {
        var driveModel = builtInBlockModel("drive");
        var chargerModel = models().getExistingFile(ReinforcedAE.makeId("charger"));
        var inscriberModel = models().getExistingFile(ReinforcedAE.makeId("inscriber"));

        patternProvider();
        craftingMonitor();

        builtInBlockModel("crafting/1g_storage_formed");
        builtInBlockModel("crafting/2g_storage_formed");
        builtInBlockModel("crafting/8g_storage_formed");
        builtInBlockModel("crafting/32g_storage_formed");
        builtInBlockModel("crafting/128g_storage_formed");

        simpleBlockAndItem(ModBlock.REINFORCED_INTERFACE);

        craftingModel(ModBlock.ACCELERATOR_4X, "accelerator_4x");
        craftingModel(ModBlock.ACCELERATOR_16X, "accelerator_16x");
        craftingModel(ModBlock.ACCELERATOR_32X, "accelerator_32x");
        craftingModel(ModBlock.ACCELERATOR_64X, "accelerator_64x");
        craftingModel(ModBlock.ACCELERATOR_128X, "accelerator_128x");
        craftingModel(ModBlock.ACCELERATOR_256X, "accelerator_256x");
        craftingModel(ModBlock.ACCELERATOR_1024X, "accelerator_1024x");
        craftingModel(ModBlock.ACCELERATOR_2048X, "accelerator_2048x");
        craftingModel(ModBlock.ACCELERATOR_8192X, "accelerator_8192x");
        craftingModel(ModBlock.CRAFTING_UNIT, "unit");
        craftingModel(ModBlock.STORAGE_1G, "1g_storage");
        craftingModel(ModBlock.STORAGE_2G, "2g_storage");
        craftingModel(ModBlock.STORAGE_8G, "8g_storage");
        craftingModel(ModBlock.STORAGE_32G, "32g_storage");
        craftingModel(ModBlock.STORAGE_128G, "128g_storage");

        energyCell(ModBlock.IMPROVED_ENERGY_CELL, "block/improved_energy_cell");
        energyCell(ModBlock.ADVANCED_ENERGY_CELL, "block/advanced_energy_cell");
        energyCell(ModBlock.PERFECT_ENERGY_CELL, "block/perfect_energy_cell");
        energyCell(ModBlock.QUANTUM_ENERGY_CELL, "block/quantum_energy_cell");

    }

    private void craftingMonitor() {
        var formedModel = ReinforcedAE.makeId("block/crafting/monitor_formed");
        var unformedModel = ReinforcedAE.makeId("block/crafting/monitor");
        multiVariantGenerator(ModBlock.CRAFTING_MONITOR).with(PropertyDispatch.properties(AbstractCraftingUnitBlock.FORMED, BlockStateProperties.FACING)
                .generate((formed, facing) -> {
                    if (formed) {
                        return Variant.variant().with(VariantProperties.MODEL, formedModel);
                    } else {
                        return applyOrientation(Variant.variant().with(VariantProperties.MODEL, unformedModel), BlockOrientation.get(facing));
                    }}));
    }

    private void patternProvider() {
        var def = ModBlock.REINFORCED_PATTERN_PROVIDER;
        var normalModel = cubeAll(def.block());
        simpleBlockItem(def.block(), normalModel);
        var orientedModel = models().getExistingFile(ReinforcedAE.makeId("block/reinforced_pattern_provider_oriented"));
        multiVariantGenerator(ModBlock.REINFORCED_PATTERN_PROVIDER, Variant.variant())
                .with(PropertyDispatch.property(PatternProviderBlock.PUSH_DIRECTION).generate(facing -> {
                    var forward = facing.getDirection();
                    if (forward == null) {
                        return Variant.variant().with(VariantProperties.MODEL, normalModel.getLocation());
                    } else {
                        var orientation = BlockOrientation.get(forward);
                        return applyRotation(Variant.variant().with(VariantProperties.MODEL, orientedModel.getLocation()),
                                orientation.getAngleX() + 90, orientation.getAngleY(), 0);
                    }
                }));
    }

    private void ioPort() {

    }

    private String modelPath(BlockDefinition<?> block) {
        return block.id().getPath();
    }

    private void emptyModel(BlockDefinition<?> block) {
        var model = models().getBuilder(block.id().getPath());
        simpleBlockItem(block.block(), model);
    }

    private void builtInModel(BlockDefinition<?> block) {
        builtInModel(block, false);
    }

    private void builtInModel(BlockDefinition<?> block, boolean skipItem) {
        var model = builtInBlockModel(block.id().getPath());
        getVariantBuilder(block.block()).partialState().setModels(new ConfiguredModel(model));

        if (!skipItem) {
            itemModels().getBuilder(block.id().getPath());
        }
    }

    private BlockModelBuilder builtInBlockModel(String name) {
        return models().getBuilder("block/" + name);
    }

    private void energyCell(BlockDefinition<?> block, String baseTexture) {
        var blockBuilder = getVariantBuilder(block.block());
        var models = new ArrayList<ModelFile>();
        for (var i = 0; i < 5; i++) {
            var model = models().cubeAll(modelPath(block) + "_" + i, ReinforcedAE.makeId(baseTexture + "_" + i));
            blockBuilder.partialState().with(EnergyCellBlock.ENERGY_STORAGE, i).setModels(new ConfiguredModel(model));
            models.add(model);
        }
        var item = itemModels().withExistingParent(modelPath(block), models.get(0).getLocation());
        for (var i = 1; i < models.size(); i ++) {
            float fillFactor = i / (float) models.size();
            item.override().predicate(InitItemModelsProperties.ENERGY_FILL_LEVEL_ID, fillFactor).model(models.get(1));
        }
    }

    private void craftingModel(BlockDefinition<?> block, String path) {
        var blockModel = models().cubeAll("block/crafting/" + path, ReinforcedAE.makeId("block/crafting/" + path));
        getVariantBuilder(block.block())
                .partialState().with(AbstractCraftingUnitBlock.FORMED, false).setModels(new ConfiguredModel(blockModel))
                .partialState().with(AbstractCraftingUnitBlock.FORMED, true).setModels(new ConfiguredModel(models().getBuilder("block/crafting/" + path + "_formed")));
        simpleBlockItem(block.block(), blockModel);
    }

    private ResourceLocation getExistingModel(String path) {
        return models().getExistingFile(ReinforcedAE.makeId(path)).getLocation();
    }
}
