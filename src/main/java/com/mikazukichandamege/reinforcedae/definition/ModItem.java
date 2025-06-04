package com.mikazukichandamege.reinforcedae.definition;

import appeng.api.stacks.AEKeyType;
import appeng.api.upgrades.Upgrades;
import appeng.items.materials.EnergyCardItem;
import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.item.cell.OverStorageCell;
import com.mikazukichandamege.reinforcedae.item.kit.ItemDriveKit;
import com.mikazukichandamege.reinforcedae.item.kit.ItemIOBusKit;
import com.mikazukichandamege.reinforcedae.item.kit.ItemInterfaceKit;
import com.mikazukichandamege.reinforcedae.item.kit.ItemPatternProviderKit;
import com.mikazukichandamege.reinforcedae.item.material.ItemChaos;
import com.mikazukichandamege.reinforcedae.item.tool.ItemChaosPickaxe;
import com.mikazukichandamege.reinforcedae.item.tool.ItemChaosSword;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

@SuppressWarnings("unused")
public final class ModItem {
    public static final DeferredRegister<Item> DR = DeferredRegister.create(ForgeRegistries.ITEMS, ReinforcedAE.MOD_ID);

    public static final RegistryObject<Item> SPEED_CARD = item("compressed_speed_card", Upgrades::createUpgradeCardItem);
    public static final RegistryObject<Item> ENERGY_CARD = item("compressed_energy_card", p -> new EnergyCardItem(p, 9));
    public static final RegistryObject<Item> ITEM_CELL_HOUSING = item("reinforced_item_cell_housing", MaterialItem::new);
    public static final RegistryObject<Item> FLUID_CELL_HOUSING = item("reinforced_fluid_cell_housing", MaterialItem::new);
    public static final RegistryObject<Item> OPTICS_PROCESSOR = item("optics_processor", MaterialItem::new);
    public static final RegistryObject<Item> OPTICS_PROCESSOR_PRINT = item("printed_optics_processor", MaterialItem::new);
    public static final RegistryObject<Item> CHAOS_INGOT = item("chaos_ingot", ItemChaos::new);
    public static final RegistryObject<Item> CHAOS_PROCESSOR_PRESS = item("chaos_processor_press", MaterialItem::new);

    public static final RegistryObject<Item> PATTERN_PROVIDER_KIT = item("reinforced_pattern_provider_kit", ItemPatternProviderKit::new);
    public static final RegistryObject<Item> INTERFACE_KIT = item("reinforced_interface_kit", ItemInterfaceKit::new);
    public static final RegistryObject<Item> IO_BUS_KIT = item("reinforced_io_bus_kit", ItemIOBusKit::new);
    public static final RegistryObject<Item> DRIVE_KIT = item("reinforced_drive_kit", ItemDriveKit::new);

    public static final RegistryObject<Item> CHAOS_SWORD = item("chaos_sword", ItemChaosSword::new);
    public static final RegistryObject<Item> CHAOS_PICKAXE = item("chaos_pickaxe", ItemChaosPickaxe::new);

    public static final RegistryObject<Item> OVER_STORAGE_COMPONENT = item("over_storage_component", MaterialItem::new);

    public static final RegistryObject<Item> OVER_ITEM_CELL = item("over_item_cell", p -> new OverStorageCell(ITEM_CELL_HOUSING.get(), AEKeyType.items()));
    public static final RegistryObject<Item> OVER_FLUID_CELL = item("over_fluid_cell", p -> new OverStorageCell(FLUID_CELL_HOUSING.get(), AEKeyType.fluids()));

    public static RegistryObject<Item> item(String id, Function<Item.Properties, ? extends Item> factory) {
        return DR.register(id, () -> factory.apply(new Item.Properties()));
    }

    public static void init(IEventBus modEventBus) {
        DR.register(modEventBus);
    }
}
