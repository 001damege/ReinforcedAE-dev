package com.mikazukichandamege.reinforcedae.common.integration.appbot;

import appeng.items.materials.MaterialItem;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;

public final class AppBotItem {
    public static final ItemDeferredRegistries<MaterialItem> MANA_CELL_HOUSING = RAEItem.item("Reinforced Mana Cell Housing", "rnf_mana_cell_housing", MaterialItem::new);
}
