package com.mikazukichandamege.reinforcedae.common.container;

import appeng.helpers.patternprovider.PatternProviderLogicHost;
import appeng.menu.implementations.PatternProviderMenu;
import net.minecraft.world.entity.player.Inventory;

public class RnfPatternProviderContainer extends PatternProviderMenu {
    public RnfPatternProviderContainer(int id, Inventory playerInventory, PatternProviderLogicHost host) {
        super(id, playerInventory, host);
    }
}
