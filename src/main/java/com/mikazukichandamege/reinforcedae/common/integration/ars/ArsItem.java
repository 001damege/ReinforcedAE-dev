package com.mikazukichandamege.reinforcedae.common.integration.ars;

import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;

public final class ArsItem {
    public static final ItemDeferredRegistries<MaterialItem> SOURCE_CELL_HOUSING = RAEItem.item("Reinforced Source Cell Housing", "reinforced_source_cell_housing", MaterialItem::new);
}
