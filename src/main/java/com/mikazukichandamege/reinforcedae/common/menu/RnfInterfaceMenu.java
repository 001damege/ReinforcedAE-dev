package com.mikazukichandamege.reinforcedae.common.menu;

import appeng.api.config.Settings;
import appeng.api.util.IConfigManager;
import appeng.helpers.InterfaceLogicHost;
import appeng.menu.implementations.MenuTypeBuilder;
import appeng.menu.implementations.SetStockAmountMenu;
import appeng.menu.implementations.UpgradeableMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public class RnfInterfaceMenu extends UpgradeableMenu<InterfaceLogicHost> {
    public static final String ACTION = "setAmount";
    public static final MenuType<RnfInterfaceMenu> TYPE = MenuTypeBuilder.create(RnfInterfaceMenu::new, InterfaceLogicHost.class).build("rnf_interface");

    public RnfInterfaceMenu(MenuType<?> menuType, int id, Inventory ip, InterfaceLogicHost host) {
        super(menuType, id, ip, host);
    }

    @Override
    protected void loadSettingsFromHost(IConfigManager cm) {
        this.setFuzzyMode(cm.getSetting(Settings.FUZZY_MODE));
    }

    public void openSetAmountMenu(int configSlot) {
        if (isClientSide()) {
            sendClientAction(ACTION, configSlot);
        } else {
            var stack = getHost().getConfig().getStack(configSlot);
            if (stack != null) {
                SetStockAmountMenu.open((ServerPlayer) getPlayer(), getLocator(), configSlot, stack.what(), (int) stack.amount());
            }
        }
    }
}
