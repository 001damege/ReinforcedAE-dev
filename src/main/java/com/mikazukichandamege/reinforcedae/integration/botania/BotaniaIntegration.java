package com.mikazukichandamege.reinforcedae.integration.botania;

import appeng.api.upgrades.Upgrades;
import appeng.core.definitions.AEItems;

public class BotaniaIntegration {
    private BotaniaIntegration() {}

    public static void init() {
        Upgrades.add(AEItems.VOID_CARD, BotaniaItem.OVER_MANA_CELL.get(), 1);
    }
}
