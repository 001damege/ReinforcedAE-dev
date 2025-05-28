package com.mikazukichandamege.reinforcedae.integration.extendedae;

import appeng.api.upgrades.Upgrades;
import com.glodblock.github.extendedae.common.EPPItemAndBlock;
import com.mikazukichandamege.reinforcedae.definition.ModItem;

public class ExtendedAEIntegration {
    private ExtendedAEIntegration() {}

    public static void init() {

        // Extended IO Port
        Upgrades.add(ModItem.SPEED_CARD.get(), EPPItemAndBlock.EX_IO_PORT, 5);

        // Extended Inscriber
        Upgrades.add(ModItem.SPEED_CARD.get(), EPPItemAndBlock.EX_INSCRIBER, 4);

        // Circuit Silcer
        Upgrades.add(ModItem.SPEED_CARD.get(), EPPItemAndBlock.CIRCUIT_CUTTER, 4);

        // Extended Molecular Assembler
        Upgrades.add(ModItem.SPEED_CARD.get(), EPPItemAndBlock.EX_ASSEMBLER, 5);

        // Wireless Terminal
        Upgrades.add(ModItem.ENERGY_CARD.get(), EPPItemAndBlock.WIRELESS_EX_PAT, 2);

        // Import or Export Bus
        Upgrades.add(ModItem.SPEED_CARD.get(), EPPItemAndBlock.EX_IMPORT_BUS, 4);
        Upgrades.add(ModItem.SPEED_CARD.get(), EPPItemAndBlock.EX_EXPORT_BUS, 4);
        Upgrades.add(ModItem.SPEED_CARD.get(), EPPItemAndBlock.TAG_EXPORT_BUS, 4);
        Upgrades.add(ModItem.SPEED_CARD.get(), EPPItemAndBlock.MOD_EXPORT_BUS, 4);

    }
}
