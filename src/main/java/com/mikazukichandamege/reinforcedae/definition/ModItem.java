package com.mikazukichandamege.reinforcedae.definition;

import appeng.api.upgrades.Upgrades;
import appeng.items.materials.EnergyCardItem;
import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.item.ExStorageComponentItem;
import com.mikazukichandamege.reinforcedae.item.material.ItemProcessor;
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
    public static final RegistryObject<Item> ETERNAL_PROCESSOR = item("eternal_processor", ItemProcessor::new);
    public static final RegistryObject<Item> SINGULARITY_PROCESSOR = item("singularity_processor", ItemProcessor::new);
    public static final RegistryObject<Item> INFINITY_PROCESSOR = item("infinity_processor", ItemProcessor::new);
    public static final RegistryObject<Item> CHAOS_PROCESSOR = item("chaos_processor", ItemProcessor::new);

    public static final RegistryObject<Item> MAX_STORAGE_COMPONENT = item("max_storage_component", p -> new ExStorageComponentItem(p, Integer.MAX_VALUE));


    public static RegistryObject<Item> item(String id, Function<Item.Properties, ? extends Item> factory) {
        return DR.register(id, () -> factory.apply(new Item.Properties()));
    }

    public static void init(IEventBus modEventBus) {
        DR.register(modEventBus);
    }
}
