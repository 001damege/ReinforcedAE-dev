package com.mikazukichandamege.reinforcedae.common.integration.appflux;

import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;

import java.util.List;

import static com.mikazukichandamege.reinforcedae.registry.RAEItem.item;

public final class AppFluxItem {
    public static void init() {}

    public static List<ItemDeferredRegistries<?>> getItems() {
        return List.of(FLUX_CELL_HOUSING);
    }

    public static final ItemDeferredRegistries<MaterialItem> FLUX_CELL_HOUSING = item("Reinforced Flux Cell Housing", "rnf_flux_cell_housing", MaterialItem::new);
}
