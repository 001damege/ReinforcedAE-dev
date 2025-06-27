package com.mikazukichandamege.reinforcedae.datagen;

import appeng.api.orientation.BlockOrientation;
import appeng.block.crafting.AbstractCraftingUnitBlock;
import appeng.block.networking.EnergyCellBlock;
import appeng.core.AppEng;
import com.mikazukichandamege.reinforcedae.registries.BlockDeferredRegistries;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;
import appeng.datagen.providers.models.AE2BlockStateProvider;
import appeng.init.client.InitItemModelsProperties;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.common.block.RnfCraftingUnitType;
import com.mikazukichandamege.reinforcedae.common.block.RnfPatternProviderBlock;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;

public class RAEModelProvider extends AE2BlockStateProvider {
    protected RAEModelProvider(PackOutput output, ExistingFileHelper existing) {
        super(output, ReinforcedAE.MOD_ID, existing);
    }

    @Override
    protected void registerStatesAndModels() {
        basicItem(RAEItem.REINFORCED_CARD);
        basicItem(RAEItem.SPEED_CARD);
        basicItem(RAEItem.ENERGY_CARD);
        basicItem(RAEItem.OPTICS_PROCESSOR);
        basicItem(RAEItem.PATTERN_PROVIDER_KIT);
        basicItem(RAEItem.INTERFACE_KIT);
        basicItem(RAEItem.PORT_KIT);
        basicItem(RAEItem.DRIVE_KIT);
        basicItem(RAEItem.CELL_COMPONENT_1G);
        basicItem(RAEItem.CELL_COMPONENT_4G);
        basicItem(RAEItem.CELL_COMPONENT_16G);
        basicItem(RAEItem.CELL_COMPONENT_64G);
        basicItem(RAEItem.CELL_COMPONENT_256G);

        for (var type : RnfCraftingUnitType.values()) {
            basicCraftingBlockModel(type);
        }

        interfaceOrPatternPart(RAEItem.PATTERN_PROVIDER);
        interfaceOrPatternPart(RAEItem.INTERFACE);

        patternProvider(RAEBlock.PATTERN_PROVIDER);
        patternProvider(RAEBlock.INTERFACE);

        energyCell(RAEBlock.ADV_ENERGY);
        energyCell(RAEBlock.IMP_ENERGY);
        energyCell(RAEBlock.PER_ENERGY);
        energyCell(RAEBlock.QUA_ENERGY);

        craftingMonitor();
    }

    private String modelPath(BlockDeferredRegistries<?> block) {
        return block.id().getPath();
    }

    private void basicItem(ItemDeferredRegistries<?> item, String path) {
        if (path.isEmpty()) itemModels().basicItem(item.asItem());
        else {
            String id = item.id().getPath();
            itemModels().singleTexture(id, mcLoc("item/generated"), "layer0", ReinforcedAE.makeId("item/" + path + "/" + id));
        }
    }

    private void basicItem(ItemDeferredRegistries<?> item) {
        basicItem(item, "");
    }

    private void basicBlock(BlockDeferredRegistries<?> block) {
        var model = cubeAll(block.block());
        simpleBlock(block.block(), model);
        simpleBlockItem(block.block(), model);
    }

    private void basicCraftingBlockModel(RnfCraftingUnitType type) {
        var craftingBlock = type.getDefinition().block();
        var blockModel = models().cubeAll("block/crafting/" + type.getAffix(), ReinforcedAE.makeId("block/crafting/" + type.getAffix()));
        simpleBlockItem(craftingBlock, blockModel);
        simpleBlock(craftingBlock,  blockModel);
    }

    private void interfaceOrPatternPart(ItemDeferredRegistries<?> part, boolean isExport) {
        var id = part.id().getPath();
        var partName = id.substring(0, id.lastIndexOf('_'));
        var front = ReinforcedAE.makeId("part/" + partName);
        var back = ReinforcedAE.makeId("part/" + partName + "_back");
        var sides = ReinforcedAE.makeId("part/" + partName + "_sides");
        var base = isExport ? AppEng.makeId("part/export_bus_base") : AppEng.makeId("part/pattern_provider_base");
        var itemBase = isExport ? AppEng.makeId("item/export_bus") : AppEng.makeId("item/cable_pattern_provider");

        models().singleTexture("part/" + id, base, "sidesStatus", AppEng.makeId("part/monitor_sides_status"))
                .texture("sides", sides)
                .texture("front", front)
                .texture("back", back)
                .texture("particle", back);
        itemModels().singleTexture("item/" + id, itemBase, "sides", sides)
                .texture("front", front)
                .texture("back", back);
    }

    private void interfaceOrPatternPart(ItemDeferredRegistries<?> part) {
        interfaceOrPatternPart(part, false);
    }

    private void patternProvider(BlockDeferredRegistries<?> block) {
        var patternProviderNormal = cubeAll(block.block());
        var blockName = block.id().getPath();
        simpleBlockItem(block.block(), patternProviderNormal);

        var patternProviderOriented = models().cubeBottomTop(
                "block/" + blockName + "_oriented",
                ReinforcedAE.makeId("block/" + blockName + "_alt"),
                ReinforcedAE.makeId("block/" + blockName + "_back"),
                ReinforcedAE.makeId("block/" + blockName + "_front"));
        multiVariantGenerator(block.getBlockDefinition(), Variant.variant())
                .with(PropertyDispatch.property(RnfPatternProviderBlock.FACING)
                        .generate((facing) -> {
                            var forward = facing.getDirection();
                            if (forward == null) {
                                return Variant.variant()
                                        .with(VariantProperties.MODEL, patternProviderNormal.getLocation());
                            } else {
                                var orientation = BlockOrientation.get(forward);
                                return applyRotation(
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, patternProviderOriented.getLocation()),
                                        orientation.getAngleX() + 90,
                                        orientation.getAngleY(), 0);
                            }
                        }));
    }

    private void energyCell(BlockDeferredRegistries<?> block) {
        var blockBuilder = getVariantBuilder(block.block());
        var models = new ArrayList<ModelFile>();

        for (var i = 0; i < 5; i++) {
            var model = models().cubeAll(modelPath(block) + "_" + i, ReinforcedAE.makeId(block.id().getPath() + "_" + i));
            blockBuilder.partialState().with(EnergyCellBlock.ENERGY_STORAGE, i).setModels(new ConfiguredModel(model));
            models.add(model);
        }

        var item = itemModels().withExistingParent(modelPath(block), models.get(0).getLocation());
        for (var i = 1; i < models.size(); i ++) {
            float fillFactor = i / (float) models.size();
            item.override()
                    .predicate(InitItemModelsProperties.ENERGY_FILL_LEVEL_ID, fillFactor)
                    .model(models.get(i));
        }
    }

    private void ioPort(BlockDeferredRegistries<?> block) {

    }

    private void craftingMonitor() {
        var formedModel = ReinforcedAE.makeId("block/crafting/" + RAEBlock.MONITOR.id().getPath() + "_formed");
        var unformedModel = ReinforcedAE.makeId("block/crafting/" + RAEBlock.MONITOR.id().getPath());

        multiVariantGenerator(RAEBlock.MONITOR.getBlockDefinition())
                .with(PropertyDispatch.properties(AbstractCraftingUnitBlock.FORMED, BlockStateProperties.FACING)
                        .generate((formed, facing) -> {
                            if (formed) {
                                return Variant.variant().with(VariantProperties.MODEL, formedModel);
                            } else {
                                return applyOrientation(
                                        Variant.variant().with(VariantProperties.MODEL, unformedModel),
                                        BlockOrientation.get(facing));
                            }
                        }));
    }

    @Override
    public String getName() {
        return "Block States / Models";
    }
}
