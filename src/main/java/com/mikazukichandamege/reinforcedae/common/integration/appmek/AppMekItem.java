package com.mikazukichandamege.reinforcedae.common.integration.appmek;

import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;

import java.util.List;

import static com.mikazukichandamege.reinforcedae.registry.RAEItem.item;

public final class AppMekItem {
    public static void init() {}

    public static List<ItemDeferredRegistries<?>> getItems() {
        return List.of(CHEMICAL_CELL_HOUSING, CHEMICAL_DISK);
    }

    public static final ItemDeferredRegistries<MaterialItem> CHEMICAL_CELL_HOUSING = item("Reinforced Chemical Cell Housing", "rnf_chemical_cell_housing", MaterialItem::new);
    public static final ItemDeferredRegistries<ExChemicalDISK> CHEMICAL_DISK = item("2G Chemical Disk Drive", "2g_chemical_disk_drive", p -> new ExChemicalDISK(RAEItem.CELL_COMPONENT_1G, Integer.MAX_VALUE / 1000, 10.0f));
}
