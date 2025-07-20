package com.mikazukichandamege.reinforcedae.init.internal;

import appeng.api.upgrades.Upgrades;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.core.localization.GuiText;
import com.mikazukichandamege.reinforcedae.common.integration.advancedae.AdAEIntegration;
import com.mikazukichandamege.reinforcedae.common.integration.appflux.AppFluxIntegration;
import com.mikazukichandamege.reinforcedae.common.integration.extendedae.ExAEIntegration;
import com.mikazukichandamege.reinforcedae.common.util.Addon;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;
import com.mikazukichandamege.reinforcedae.registry.RAETranslation;
import net.minecraftforge.fml.ModList;

import java.util.List;

public final class InitUpgrade {
    private InitUpgrade() {}

    public static void register() {
        Upgrades.add(RAEItem.ENERGY_CARD, AEItems.WIRELESS_TERMINAL, 2, GuiText.WirelessTerminals.getTranslationKey());
        Upgrades.add(RAEItem.ENERGY_CARD, AEItems.WIRELESS_CRAFTING_TERMINAL, 2, GuiText.WirelessTerminals.getTranslationKey());
        Upgrades.add(RAEItem.ENERGY_CARD, AEItems.COLOR_APPLICATOR, 2);
        Upgrades.add(RAEItem.ENERGY_CARD, AEItems.MATTER_CANNON, 2);
        Upgrades.add(RAEItem.ENERGY_CARD, AEBlocks.VIBRATION_CHAMBER, 3);
        Upgrades.add(RAEItem.SPEED_CARD, RAEBlock.INSCRIBER, 4, RAETranslation.Inscriber.getTranslationKey());

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
            Upgrades.add(RAEItem.ENERGY_CARD, portableCell, 2, GuiText.PortableCells.getTranslationKey());
        }

        if (ModList.get().isLoaded(Addon.AdvancedAE.getModId())) {
            AdAEIntegration.init();
        }
        if (ModList.get().isLoaded(Addon.ExtendedAE.getModId())) {
            ExAEIntegration.init();
        }
        if (ModList.get().isLoaded(Addon.Appflux.getModId())) {
            AppFluxIntegration.init();
        }
    }
}
