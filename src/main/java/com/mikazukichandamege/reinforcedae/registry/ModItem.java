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
import com.mikazukichandamege.reinforcedae.common.item.part.ReinforcedInterfacePart;
import com.mikazukichandamege.reinforcedae.common.item.part.ReinforcedPatternProviderPart;
import com.mikazukichandamege.reinforcedae.common.item.part.ReinforcedPatternProviderPartItem;
import io.github.projectet.ae2things.item.DISKDrive;
import net.minecraft.Util;
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

    public static final ItemDefinition<MaterialItem> REINFORCED_CARD = item("Reinforced Card", "reinforced_card", MaterialItem::new);
    public static final ItemDefinition<Item> SPEED_CARD = item("Compressed Accelerator Card", "compressed_speed_card", Upgrades::createUpgradeCardItem);
    public static final ItemDefinition<EnergyCardItem> ENERGY_CARD = item("Compressed Energy Card", "compressed_energy_card", p -> new EnergyCardItem(p, 9));
    public static final ItemDefinition<MaterialItem> ITEM_CELL_HOUSING = item("Reinforced Item Cell Housing", "reinforced_item_cell_housing", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> FLUID_CELL_HOUSING = item("Reinforced Fluid Cell Housing", "reinforced_fluid_cell_housing", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> DISK_CELL_HOUSING = item("Drive Housing", "disk_housing", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> OPTICS_PROCESSOR = item("Optics Processor", "optics_processor", MaterialItem::new);
    public static final ItemDefinition<ItemPatternProviderKit> PATTERN_PROVIDER_KIT = item("Pattern Provider Kit", "pattern_provider_kit", ItemPatternProviderKit::new);
    public static final ItemDefinition<ItemInterfaceKit> INTERFACE_KIT = item("Interface Kit", "interface_kit", ItemInterfaceKit::new);
    public static final ItemDefinition<ItemIOBusKit> PORT_KIT = item("IO Port Kit", "io_port_kit", ItemIOBusKit::new);
    public static final ItemDefinition<ItemDriveKit> DRIVE_KIT = item("Drive Kit", "drive_kit", ItemDriveKit::new);

    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_1G = item("1G Reinforced Storage Component", "cell_component_1g", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_2G = item("2G Reinforced Storage Component", "cell_component_2g", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_8G = item("8G Reinforced Storage Component", "cell_component_8g", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_32G = item("32G Reinforced Storage Component", "cell_component_32g", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_128G = item("128G Reinforced Storage Component", "cell_component_128g", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> CELL_COMPONENT_CREATIVE = item("Creative Storage Component", "cell_component_creative", MaterialItem::new);

    public static final ItemDefinition<PartItem<ReinforcedInterfacePart>> REINFORCED_INTERFACE = part("Reinforced Interface", "cable_reinforced_interface", ReinforcedInterfacePart.class, ReinforcedInterfacePart::new);
    public static final ItemDefinition<ReinforcedPatternProviderPartItem> REINFORCED_PATTERN_PROVIDER = Util.make(() -> {
        PartModels.registerModels(PartModelsHelper.createModels(ReinforcedPatternProviderPart.class));
        return item("Reinforced Pattern Provider", "cable_reinforced_pattern_provider", ReinforcedPatternProviderPartItem::new);
    });
/*

    public static final ItemDefinition<DISKDrive> DISK_DRIVE_1G = item("1G Disk Drive", "1g_disk_drive", p -> new DISKDrive(CELL_COMPONENT_1G, (int) 1024L * 1024, 5.5f));
    public static final ItemDefinition<DISKDrive> DISK_DRIVE_2G = item("2G Disk Drive", "2g_disk_drive", p -> new DISKDrive(CELL_COMPONENT_2G, (int) 1024L * 2048, 6.0f));

 */
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
