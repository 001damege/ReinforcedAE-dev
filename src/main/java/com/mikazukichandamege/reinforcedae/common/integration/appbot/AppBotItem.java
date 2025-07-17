package com.mikazukichandamege.reinforcedae.common.integration.appbot;

import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;

import java.util.List;

import static com.mikazukichandamege.reinforcedae.registry.RAEItem.item;

public final class AppBotItem {
    public static void init() {}

    public static List<ItemDeferredRegistries<?>> getItems() {
        return List.of(MANA_CELL_HOUSING);
    }

    public static final ItemDeferredRegistries<MaterialItem> MANA_CELL_HOUSING = item("Reinforced Mana Cell Housing", "rnf_mana_cell_housing", MaterialItem::new);
}
