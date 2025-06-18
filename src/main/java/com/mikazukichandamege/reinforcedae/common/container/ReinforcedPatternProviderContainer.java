package com.mikazukichandamege.reinforcedae.common.container;

import appeng.helpers.patternprovider.PatternProviderLogicHost;
import appeng.menu.implementations.PatternProviderMenu;
import com.mikazukichandamege.reinforcedae.registry.ModContainer;
import net.minecraft.world.entity.player.Inventory;

public class ReinforcedPatternProviderContainer extends PatternProviderMenu {
    public ReinforcedPatternProviderContainer(int id, Inventory playerInventory, PatternProviderLogicHost host) {
        super(ModContainer.REINFORCED_PATTERN_PROVIDER, id, playerInventory, host);
    }
}
