package com.mikazukichandamege.reinforcedae.definition;

import appeng.api.upgrades.Upgrades;
import appeng.items.materials.EnergyCardItem;
import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.item.ExStorageComponentItem;
import com.mikazukichandamege.reinforcedae.item.kit.ItemDriveKit;
import com.mikazukichandamege.reinforcedae.item.kit.ItemIOBusKit;
import com.mikazukichandamege.reinforcedae.item.kit.ItemInterfaceKit;
import com.mikazukichandamege.reinforcedae.item.kit.ItemPatternProviderKit;
import com.mikazukichandamege.reinforcedae.item.material.ItemChaos;
import com.mikazukichandamege.reinforcedae.item.material.ItemEternal;
import com.mikazukichandamege.reinforcedae.item.material.ItemInfinity;
import com.mikazukichandamege.reinforcedae.item.material.ItemSingularity;
import com.mikazukichandamege.reinforcedae.item.tool.ItemChaosAxe;
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
    public static final RegistryObject<Item> ENERGY_CARD = item("compressed_energy_card", p -> new EnergyCardItem(p, 5));
    public static final RegistryObject<Item> ITEM_CELL_HOUSING = item("reinforced_item_cell_housing", MaterialItem::new);
    public static final RegistryObject<Item> FLUID_CELL_HOUSING = item("reinforced_fluid_cell_housing", MaterialItem::new);
    public static final RegistryObject<Item> ETERNAL_PROCESSOR = item("eternal_processor", ItemEternal::new);
    public static final RegistryObject<Item> ETERNAL_PROCESSOR_PRINT = item("printed_eternal_processor", MaterialItem::new);
    public static final RegistryObject<Item> SINGULARITY_PROCESSOR = item("singularity_processor", ItemSingularity::new);
    public static final RegistryObject<Item> SINGULARITY_PROCESSOR_PRINT = item("printed_singularity_processor", MaterialItem::new);
    public static final RegistryObject<Item> INFINITY_PROCESSOR = item("infinity_processor", ItemInfinity::new);
    public static final RegistryObject<Item> INFINITY_PROCESSOR_PRINT = item("printed_infinity_processor", MaterialItem::new);
    public static final RegistryObject<Item> CHAOS_PROCESSOR = item("chaos_processor", ItemChaos::new);
    public static final RegistryObject<Item> CHAOS_PROCESSOR_PRINT = item("printed_chaos_processor", MaterialItem::new);

    public static final RegistryObject<Item> ETERNAL_INGOT = item("eternal_ingot", ItemEternal::new);
    public static final RegistryObject<Item> SINGULARITY_INGOT = item("singularity_ingot", ItemSingularity::new);
    public static final RegistryObject<Item> INFINITY_INGOT = item("infinity_ingot", ItemInfinity::new);
    public static final RegistryObject<Item> CHAOS_INGOT = item("chaos_ingot", ItemChaos::new);

    public static final RegistryObject<Item> ETERNAL_PROCESSOR_PRESS = item("eternal_processor_press", MaterialItem::new);
    public static final RegistryObject<Item> SINGULARITY_PROCESSOR_PRESS = item("singularity_processor_press", MaterialItem::new);
    public static final RegistryObject<Item> INFINITY_PROCESSOR_PRESS = item("infinity_processor_press", MaterialItem::new);
    public static final RegistryObject<Item> CHAOS_PROCESSOR_PRESS = item("chaos_processor_press", MaterialItem::new);

    public static final RegistryObject<Item> PATTERN_PROVIDER_KIT = item("reinforced_pattern_provider_kit", ItemPatternProviderKit::new);
    public static final RegistryObject<Item> INTERFACE_KIT = item("reinforced_interface_kit", ItemInterfaceKit::new);
    public static final RegistryObject<Item> IO_BUS_KIT = item("reinforced_io_bus_kit", ItemIOBusKit::new);
    public static final RegistryObject<Item> DRIVE_KIT = item("reinforced_drive_kit", ItemDriveKit::new);

    public static final RegistryObject<Item> CHAOS_SWORD = item("chaos_sword", ItemChaosSword::new);
    public static final RegistryObject<Item> CHAOS_PICKAXE = item("chaos_pickaxe", ItemChaosPickaxe::new);
    public static final RegistryObject<Item> CHAOS_AXE = item("chaos_axe", ItemChaosAxe::new);

    public static final RegistryObject<Item> MAX_STORAGE_COMPONENT = item("max_storage_component", p -> new ExStorageComponentItem(p, Integer.MAX_VALUE));


    public static RegistryObject<Item> item(String id, Function<Item.Properties, ? extends Item> factory) {
        return DR.register(id, () -> factory.apply(new Item.Properties()));
    }

    public static void init(IEventBus modEventBus) {
        DR.register(modEventBus);
    }
}
