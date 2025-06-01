package com.mikazukichandamege.reinforcedae.integration.appflux;

import appeng.api.upgrades.Upgrades;
import appeng.core.definitions.AEItems;

public class AppFluxIntegration {
    private AppFluxIntegration() {}

    public static void init() {
        Upgrades.add(AEItems.VOID_CARD, AppFluxItem.OVER_FLUX_CELL.get(), 1);
    }
}
