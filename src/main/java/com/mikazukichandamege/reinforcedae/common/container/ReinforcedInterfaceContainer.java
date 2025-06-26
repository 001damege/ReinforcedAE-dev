package com.mikazukichandamege.reinforcedae.common.container;

import appeng.helpers.InterfaceLogicHost;
import appeng.menu.implementations.InterfaceMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public class ReinforcedInterfaceContainer extends InterfaceMenu {
    public ReinforcedInterfaceContainer(MenuType<? extends InterfaceMenu> menuType, int id, Inventory ip, InterfaceLogicHost host) {
        super(menuType, id, ip, host);
    }
}
