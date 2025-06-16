package com.mikazukichandamege.reinforcedae.common.integration.ars;

import appeng.core.definitions.ItemDefinition;
import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.ModItem;

public final class ArsItem {
    public static void init() {
        ReinforcedAE.LOGGER.info("Init Integration Ars Nouveau Items");
    }

    public static final ItemDefinition<MaterialItem> SOURCE_CELL_HOUSING = ModItem.item("Reinforced Source Cell Housing", "reinforced_source_cell_housing", MaterialItem::new);
}
