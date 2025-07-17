package com.mikazukichandamege.reinforcedae.common.integration.ars;

import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;

import java.util.List;

import static com.mikazukichandamege.reinforcedae.registry.RAEItem.item;

public final class ArsItem {
    public static void init() {}

    public static List<ItemDeferredRegistries<?>> getItems() {
        return List.of(SOURCE_CELL_HOUSING);
    }

    public static final ItemDeferredRegistries<MaterialItem> SOURCE_CELL_HOUSING = item("Reinforced Source Cell Housing", "reinforced_source_cell_housing", MaterialItem::new);
}
