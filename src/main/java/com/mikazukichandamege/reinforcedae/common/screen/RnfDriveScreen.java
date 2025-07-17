package com.mikazukichandamege.reinforcedae.common.screen;

import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.style.ScreenStyle;
import appeng.menu.AEBaseMenu;
import com.mikazukichandamege.reinforcedae.common.menu.RnfDriveMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class RnfDriveScreen<R extends AEBaseMenu> extends AEBaseScreen<RnfDriveMenu> {
    public RnfDriveScreen(RnfDriveMenu menu, Inventory playerInventory, Component title, ScreenStyle style) {
        super(menu, playerInventory, title, style);
        widgets.addOpenPriorityButton();
    }
}
