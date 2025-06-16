package com.mikazukichandamege.reinforcedae.common.integration.advancedae;

import appeng.api.upgrades.Upgrades;
import com.mikazukichandamege.reinforcedae.registry.ModItem;
import net.pedroksl.advanced_ae.common.definitions.AAEBlocks;
import net.pedroksl.advanced_ae.common.definitions.AAEItems;

public class AdvancedAEIntegration {
    private AdvancedAEIntegration() {}

    public static void init() {

        // Export Bus
        Upgrades.add(ModItem.SPEED_CARD, AAEItems.IMPORT_EXPORT_BUS, 4);
        Upgrades.add(ModItem.SPEED_CARD, AAEItems.STOCK_EXPORT_BUS, 4);

        // Machines
        Upgrades.add(ModItem.SPEED_CARD, AAEBlocks.REACTION_CHAMBER, 4);
        Upgrades.add(ModItem.SPEED_CARD, AAEBlocks.QUANTUM_CRAFTER, 4);
    }
}
