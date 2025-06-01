package com.mikazukichandamege.reinforcedae.integration.botania;

import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public final class BotaniaItem {
    public static final DeferredRegister<Item> DR = DeferredRegister.create(ForgeRegistries.ITEMS, ReinforcedAE.MOD_ID);

    public static final RegistryObject<Item> MANA_CELL_HOUSING = item("reinforced_mana_cell_housing", MaterialItem::new);
    public static final RegistryObject<Item> OVER_MANA_CELL = item("over_mana_cell", ItemOverManaCell::new);

    public static RegistryObject<Item> item(String id, Function<Item.Properties, ? extends Item> factory) {
        return DR.register(id, () -> factory.apply(new Item.Properties()));
    }

    public static void init(IEventBus modEventBus) {
        DR.register(modEventBus);
    }
}
