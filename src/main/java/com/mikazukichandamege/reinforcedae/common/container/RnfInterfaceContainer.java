package com.mikazukichandamege.reinforcedae.common.container;

import appeng.helpers.InterfaceLogicHost;
import appeng.menu.implementations.InterfaceMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public class RnfInterfaceContainer extends InterfaceMenu {
    public RnfInterfaceContainer(MenuType<? extends InterfaceMenu> menuType, int id, Inventory ip, InterfaceLogicHost host) {
        super(menuType, id, ip, host);
    }
}
