package com.mikazukichandamege.reinforcedae.common.integration.botania;

import appeng.core.definitions.ItemDefinition;
import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.ModItem;

public final class BotaniaItem {
    public static void init() {
        ReinforcedAE.LOGGER.info("Init Integration Botania Items");
    }

    public static final ItemDefinition<MaterialItem> MANA_CELL_HOUSING = ModItem.item("Reinforced Mana Cell Housing", "reinforced_mana_cell_housing", MaterialItem::new);
}
