package com.mikazukichandamege.reinforcedae.common.integration.appflux;

import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;

public final class AppFluxItem {
    public static final ItemDeferredRegistries<MaterialItem> FLUX_CELL_HOUSING = RAEItem.item("Reinforced Flux Cell Housing", "reinforced_flux_cell_housing", MaterialItem::new);
}
