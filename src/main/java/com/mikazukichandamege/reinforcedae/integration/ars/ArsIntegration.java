package com.mikazukichandamege.reinforcedae.integration.ars;

import appeng.api.upgrades.Upgrades;
import appeng.core.definitions.AEItems;

public class ArsIntegration {
    private ArsIntegration() {}

    public static void init() {
        Upgrades.add(AEItems.VOID_CARD, ArsItem.OVER_SOURCE_CELL.get(), 1);
    }
}
