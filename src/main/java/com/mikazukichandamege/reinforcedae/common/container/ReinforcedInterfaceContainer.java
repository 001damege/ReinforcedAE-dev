package com.mikazukichandamege.reinforcedae.common.container;

import appeng.helpers.InterfaceLogicHost;
import appeng.menu.implementations.InterfaceMenu;
import com.mikazukichandamege.reinforcedae.registry.ModContainer;
import net.minecraft.world.entity.player.Inventory;

public class ReinforcedInterfaceContainer extends InterfaceMenu {
    public ReinforcedInterfaceContainer(int id, Inventory ip, InterfaceLogicHost host) {
        super(ModContainer.REINFORCED_INTERFACE, id, ip, host);
    }
}
