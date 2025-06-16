package com.mikazukichandamege.reinforcedae.common.integration.appflux;

import appeng.core.definitions.ItemDefinition;
import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.ModItem;

public final class AppFluxItem {
    public static void init() {
        ReinforcedAE.LOGGER.info("Init Integration AppFlux Items");
    }

    public static final ItemDefinition<MaterialItem> FLUX_CELL_HOUSING = ModItem.item("Reinforced Flux Cell Housing", "reinforced_flux_cell_housing", MaterialItem::new);
}
