package com.mikazukichandamege.reinforcedae.common.integration.mekanism;

import appeng.core.definitions.ItemDefinition;
import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.ModItem;

public final class MekanismItem {
    public static void init() {
        ReinforcedAE.LOGGER.info("Init Integration Mekanism Items");
    }

    public static final ItemDefinition<MaterialItem> CHEMICAL_CELL_HOUSING = ModItem.item("Reinforced Chemical Cell Housing", "reinforced_chemical_cell_housing", MaterialItem::new);
}
