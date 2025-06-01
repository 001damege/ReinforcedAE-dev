package com.mikazukichandamege.reinforcedae.item.upgrade;

import appeng.api.upgrades.Upgrades;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEParts;
import appeng.core.localization.GuiText;
import com.mikazukichandamege.reinforcedae.definition.ModItem;
import com.mikazukichandamege.reinforcedae.integration.advancedae.AdvancedAEIntegration;
import com.mikazukichandamege.reinforcedae.integration.appflux.AppFluxIntegration;
import com.mikazukichandamege.reinforcedae.integration.extendedae.ExtendedAEIntegration;
import com.mikazukichandamege.reinforcedae.util.Addon;
import net.minecraftforge.fml.ModList;

import java.util.List;

import static appeng.core.definitions.AEItems.*;

public class InitUpgrades {
    private InitUpgrades() {}

    public static void init() {
        String itemIoBusGroup = GuiText.IOBuses.getTranslationKey();
        String portableCellGroup = GuiText.PortableCells.getTranslationKey();
        String wirelessTerminalGroup = GuiText.WirelessTerminals.getTranslationKey();

        // IO Port
        Upgrades.add(ModItem.SPEED_CARD.get(), AEBlocks.IO_PORT, 3);

        // Import or Export Bus
        Upgrades.add(ModItem.SPEED_CARD.get(), AEParts.IMPORT_BUS, 4, itemIoBusGroup);
        Upgrades.add(ModItem.SPEED_CARD.get(), AEParts.EXPORT_BUS, 4, itemIoBusGroup);

        var portableItemCells = List.of(
                PORTABLE_ITEM_CELL1K, PORTABLE_ITEM_CELL4K, PORTABLE_ITEM_CELL16K, PORTABLE_ITEM_CELL64K, PORTABLE_ITEM_CELL256K);
        for (var portableCell : portableItemCells) {
            Upgrades.add(ModItem.ENERGY_CARD.get(), portableCell, 2, portableCellGroup);
        }

        var portableFluidCells = List.of(
                PORTABLE_FLUID_CELL1K, PORTABLE_FLUID_CELL4K, PORTABLE_FLUID_CELL16K, PORTABLE_FLUID_CELL64K, PORTABLE_FLUID_CELL256K);
        for (var portableCell : portableFluidCells) {
            Upgrades.add(ModItem.ENERGY_CARD.get(), portableCell, 2, portableCellGroup);
        }

        // Wireless Terminal
        Upgrades.add(ModItem.ENERGY_CARD.get(), WIRELESS_TERMINAL, 2, wirelessTerminalGroup);
        Upgrades.add(ModItem.ENERGY_CARD.get(), WIRELESS_CRAFTING_TERMINAL, 2, wirelessTerminalGroup);

        // Color Applicator
        Upgrades.add(ModItem.ENERGY_CARD.get(), COLOR_APPLICATOR, 2);

        // Matter Cannon
        Upgrades.add(ModItem.ENERGY_CARD.get(), MATTER_CANNON, 2);
        Upgrades.add(ModItem.SPEED_CARD.get(), MATTER_CANNON, 4);

        // Molecular Assembler
        Upgrades.add(ModItem.SPEED_CARD.get(), AEBlocks.MOLECULAR_ASSEMBLER, 4);

        // Vibrant Chamber
        Upgrades.add(ModItem.SPEED_CARD.get(), AEBlocks.VIBRATION_CHAMBER, 3);
        Upgrades.add(ModItem.ENERGY_CARD.get(), AEBlocks.VIBRATION_CHAMBER, 3);

        // Inscriber
        Upgrades.add(ModItem.SPEED_CARD.get(), AEBlocks.INSCRIBER, 4);

        var itemCell = ModItem.OVER_ITEM_CELL.get();
        // Item Cell
        Upgrades.add(INVERTER_CARD, itemCell, 1);
        Upgrades.add(EQUAL_DISTRIBUTION_CARD, itemCell, 1);
        Upgrades.add(FUZZY_CARD, itemCell, 1);
        Upgrades.add(VOID_CARD, itemCell, 1);

        var fluidCell = ModItem.OVER_FLUID_CELL.get();
        // Fluid Cell
        Upgrades.add(INVERTER_CARD, fluidCell, 1);
        Upgrades.add(EQUAL_DISTRIBUTION_CARD, fluidCell, 1);
        Upgrades.add(VOID_CARD, fluidCell, 1);

        if (ModList.get().isLoaded(Addon.ExtendedAE.getModId())) {
            ExtendedAEIntegration.init();
        }

        if (ModList.get().isLoaded(Addon.AdvancedAE.getModId())) {
            AdvancedAEIntegration.init();
        }

        if (ModList.get().isLoaded(Addon.Appflux.getModId())) {
            AppFluxIntegration.init();
        }
    }
}
