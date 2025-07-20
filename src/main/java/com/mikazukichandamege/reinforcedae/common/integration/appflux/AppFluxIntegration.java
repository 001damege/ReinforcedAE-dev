package com.mikazukichandamege.reinforcedae.common.integration.appflux;

import appeng.api.upgrades.Upgrades;
import com.glodblock.github.appflux.common.AFItemAndBlock;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;

public class AppFluxIntegration {
    private AppFluxIntegration() {}

    public static void init() {
        Upgrades.add(AFItemAndBlock.INDUCTION_CARD, RAEBlock.PATTERN_PROVIDER, 1);
    }
}
