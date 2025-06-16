package com.mikazukichandamege.reinforcedae.registry;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import appeng.api.parts.PartModels;
import appeng.api.upgrades.Upgrades;
import appeng.core.definitions.ItemDefinition;
import appeng.items.materials.EnergyCardItem;
import appeng.items.materials.MaterialItem;
import appeng.items.parts.PartItem;
import appeng.items.parts.PartModelsHelper;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemDriveKit;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemIOBusKit;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemInterfaceKit;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemPatternProviderKit;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class ModItem {
    public static void init() {
        ReinforcedAE.LOGGER.info("Init Items");
    }

    private static final List<ItemDefinition<?>> ITEMS = new ArrayList<>();

    public static List<ItemDefinition<?>> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }

    public static final ItemDefinition<Item> SPEED_CARD = item("Compressed Speed Card", "compressed_speed_card", Upgrades::createUpgradeCardItem);
    public static final ItemDefinition<EnergyCardItem> ENERGY_CARD = item("Compressed Energy Card", "compressed_energy_card", p -> new EnergyCardItem(p, 9));
    public static final ItemDefinition<MaterialItem> ITEM_CELL_HOUSING = item("Reinforced Item Cell Housing", "reinforced_item_cell_housing", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> FLUID_CELL_HOUSING = item("Reinforced Fluid Cell Housing", "reinforced_fluid_cell_housing", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> OPTICS_PROCESSOR = item("Optics Processor", "optics_processor", MaterialItem::new);
    public static final ItemDefinition<ItemPatternProviderKit> PATTERN_PROVIDER_KIT = item("Pattern Provider Kit", "pattern_provider_kit", ItemPatternProviderKit::new);
    public static final ItemDefinition<ItemInterfaceKit> INTERFACE_KIT = item("Interface Kit", "interface_kit", ItemInterfaceKit::new);
    public static final ItemDefinition<ItemIOBusKit> PORT_KIT = item("IO Port Kit", "io_port_kit", ItemIOBusKit::new);
    public static final ItemDefinition<ItemDriveKit> DRIVE_KIT = item("Drive Kit", "drive_kit", ItemDriveKit::new);

    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_1024M = item("1024M Reinforced Storage Component", "cell_component_1024m", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_2048M = item("2048M Reinforced Storage Component", "cell_component_2048m", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_8192M = item("8192M Reinforced Storage Component", "cell_component_8192m", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_32768M = item("32768M Reinforced Storage Component", "cell_component_32768m", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_131072M = item("131072M Reinforced Storage Component", "cell_component_131072m", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_CREATIVE = item("Creative Storage Component", "cell_component_creative", MaterialItem::new);

    public static <T extends Item> ItemDefinition<T> item(String englishName, String id, Function<Item.Properties, T> factory) {
        var registry = new ItemDefinition<>(englishName, ReinforcedAE.makeId(id), factory.apply(new Item.Properties()));
        ITEMS.add(registry);
        return registry;
    }

    public static <T extends IPart> ItemDefinition<PartItem<T>> part(String englishName, String id, Class<T> partClass, Function<IPartItem<T>, T> factory) {
        PartModels.registerModels(PartModelsHelper.createModels(partClass));
        return item(englishName, id, p -> new PartItem<>(p, partClass, factory));
    }
}
