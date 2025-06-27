package com.mikazukichandamege.reinforcedae.common.integration.appmek;

import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;

public final class AppMekItem {
    public static final ItemDeferredRegistries<MaterialItem> CHEMICAL_CELL_HOUSING = RAEItem.item("Reinforced Chemical Cell Housing", "reinforced_chemical_cell_housing", MaterialItem::new);
}
