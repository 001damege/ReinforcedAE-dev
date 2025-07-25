package com.mikazukichandamege.reinforcedae.common.integration.extendedae;

import appeng.api.upgrades.Upgrades;
import com.glodblock.github.extendedae.common.EPPItemAndBlock;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;

public class ExAEIntegration {
    private ExAEIntegration() {}

    public static void init() {

        // Extended IO Port
        Upgrades.add(RAEItem.SPEED_CARD, EPPItemAndBlock.EX_IO_PORT, 5);

        // Extended Inscriber
        Upgrades.add(RAEItem.SPEED_CARD, EPPItemAndBlock.EX_INSCRIBER, 4);

        // Circuit Silcer
        Upgrades.add(RAEItem.SPEED_CARD, EPPItemAndBlock.CIRCUIT_CUTTER, 4);

        // Extended Molecular Assembler
        Upgrades.add(RAEItem.SPEED_CARD, EPPItemAndBlock.EX_ASSEMBLER, 5);

        // Wireless Terminal
        Upgrades.add(RAEItem.ENERGY_CARD, EPPItemAndBlock.WIRELESS_EX_PAT, 2);

        // Import or Export Bus
        Upgrades.add(RAEItem.SPEED_CARD, EPPItemAndBlock.EX_IMPORT_BUS, 4);
        Upgrades.add(RAEItem.SPEED_CARD, EPPItemAndBlock.EX_EXPORT_BUS, 4);
        Upgrades.add(RAEItem.SPEED_CARD, EPPItemAndBlock.TAG_EXPORT_BUS, 4);
        Upgrades.add(RAEItem.SPEED_CARD, EPPItemAndBlock.MOD_EXPORT_BUS, 4);

    }
}
