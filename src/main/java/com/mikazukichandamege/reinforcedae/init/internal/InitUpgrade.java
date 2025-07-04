package com.mikazukichandamege.reinforcedae.init.internal;

import appeng.api.upgrades.Upgrades;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.core.definitions.AEParts;
import appeng.core.localization.GuiText;
import com.mikazukichandamege.reinforcedae.common.integration.advancedae.AdAEIntegration;
import com.mikazukichandamege.reinforcedae.common.integration.extendedae.ExAEIntegration;
import com.mikazukichandamege.reinforcedae.common.util.Addon;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;
import net.minecraftforge.fml.ModList;

import java.util.List;

public final class InitUpgrade {
    private InitUpgrade() {}

    public static void register() {
        var storageCellGroup = GuiText.StorageCells.getTranslationKey();
        var portableCellGroup = GuiText.PortableCells.getTranslationKey();
        var interfaceGroup = GuiText.Interface.getTranslationKey();
        var wirelessTerminalGroup = GuiText.WirelessTerminals.getTranslationKey();
        var itemIoBusGroup = GuiText.IOBuses.getTranslationKey();

        Upgrades.add(RAEItem.SPEED_CARD, AEBlocks.IO_PORT, 3);
        Upgrades.add(RAEItem.SPEED_CARD, AEParts.IMPORT_BUS, 4, itemIoBusGroup);
        Upgrades.add(RAEItem.SPEED_CARD, AEParts.EXPORT_BUS, 4, itemIoBusGroup);
        Upgrades.add(RAEItem.ENERGY_CARD, AEItems.WIRELESS_TERMINAL, 2, wirelessTerminalGroup);
        Upgrades.add(RAEItem.ENERGY_CARD, AEItems.WIRELESS_CRAFTING_TERMINAL, 2, wirelessTerminalGroup);
        Upgrades.add(RAEItem.ENERGY_CARD, AEItems.COLOR_APPLICATOR, 2);
        Upgrades.add(RAEItem.ENERGY_CARD, AEItems.MATTER_CANNON, 2);
        Upgrades.add(RAEItem.SPEED_CARD, AEItems.MATTER_CANNON, 4);
        Upgrades.add(RAEItem.SPEED_CARD, AEBlocks.MOLECULAR_ASSEMBLER, 4);
        Upgrades.add(RAEItem.SPEED_CARD, AEBlocks.VIBRATION_CHAMBER, 3);
        Upgrades.add(RAEItem.ENERGY_CARD, AEBlocks.VIBRATION_CHAMBER, 3);
        Upgrades.add(RAEItem.SPEED_CARD, AEBlocks.INSCRIBER, 4);

        for (var portableCell : List.of(
                AEItems.PORTABLE_ITEM_CELL1K,
                AEItems.PORTABLE_ITEM_CELL4K,
                AEItems.PORTABLE_ITEM_CELL16K,
                AEItems.PORTABLE_ITEM_CELL64K,
                AEItems.PORTABLE_ITEM_CELL256K,
                AEItems.PORTABLE_FLUID_CELL1K,
                AEItems.PORTABLE_FLUID_CELL4K,
                AEItems.PORTABLE_FLUID_CELL16K,
                AEItems.PORTABLE_FLUID_CELL64K,
                AEItems.PORTABLE_FLUID_CELL256K)) {
            Upgrades.add(RAEItem.ENERGY_CARD, portableCell, 2, portableCellGroup);
        }

        if (ModList.get().isLoaded(Addon.AdvancedAE.getModId())) {
            AdAEIntegration.init();
        }
        if (ModList.get().isLoaded(Addon.ExtendedAE.getModId())) {
            ExAEIntegration.init();
        }
    }
}
