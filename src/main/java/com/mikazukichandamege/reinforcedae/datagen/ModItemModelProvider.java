package com.mikazukichandamege.reinforcedae.datagen;

import appeng.core.definitions.ItemDefinition;
import appeng.datagen.providers.IAE2DataProvider;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.ModItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.mikazukichandamege.reinforcedae.ReinforcedAE.makeId;

public class ModItemModelProvider extends ItemModelProvider implements IAE2DataProvider {
    private static final String Generated = "item/generated";
    private static final String Handheld = "item/handheld";

    protected ModItemModelProvider(PackOutput output, ExistingFileHelper existing) {
        super(output, ReinforcedAE.MOD_ID, existing);
    }

    @Override
    protected void registerModels() {
        flat(ModItem.REINFORCED_CARD, "item/reinforced_card");
        flat(ModItem.SPEED_CARD, "item/compressed_speed_card");
        flat(ModItem.ENERGY_CARD, "item/compressed_energy_card");
        flat(ModItem.ITEM_CELL_HOUSING, "item/reinforced_item_cell_housing");
        flat(ModItem.FLUID_CELL_HOUSING, "item/reinforced_fluid_cell_housing");
        flat(ModItem.OPTICS_PROCESSOR, "item/optics_processor");
        flat(ModItem.PATTERN_PROVIDER_KIT, "item/pattern_provider_kit");
        flat(ModItem.INTERFACE_KIT, "item/interface_kit");
        flat(ModItem.PORT_KIT, "item/io_port_kit");
        flat(ModItem.DRIVE_KIT, "item/drive_kit");
        flat(ModItem.CELL_COMPONENT_1G, "item/cell_component_1g");
        flat(ModItem.CELL_COMPONENT_2G, "item/cell_component_2g");
        flat(ModItem.CELL_COMPONENT_8G, "item/cell_component_8g");
        flat(ModItem.CELL_COMPONENT_32G, "item/cell_component_32g");
        flat(ModItem.CELL_COMPONENT_128G, "item/cell_component_128g");

    }

    private void storageCell(ItemDefinition<?> item, String background) {
        String id = item.id().getPath();
        singleTexture(id, mcLoc(Generated), "layer0", makeId(background)).texture("layer1", "item/storage_cell_led");
    }

    private void registerHandheld() {

    }

    private void handheld(ItemDefinition<?> item) {
        singleTexture(item.id().getPath(), new ResourceLocation(Handheld), "layer0", makeId("item/" + item.id().getPath()));
    }

    private void registerEmptyModel(ItemDefinition<?> item) {
        this.getBuilder(item.id().getPath());
    }

    private ItemModelBuilder flat(ItemDefinition<?> item, String texturePath) {
        String id = item.id().getPath();
        return singleTexture(id, mcLoc(Generated), "layer0", makeId(texturePath));
    }

    private ItemModelBuilder flat(ResourceLocation id, String texturePath) {
        return singleTexture(id.getPath(), mcLoc(Generated), "layer0", makeId(texturePath));
    }

    private ItemModelBuilder builtInItemModel(String name) {
        var model = getBuilder("item/" + name);
        return model;
    }
}
