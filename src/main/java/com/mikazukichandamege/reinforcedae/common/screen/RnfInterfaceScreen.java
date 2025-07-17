package com.mikazukichandamege.reinforcedae.common.screen;

import appeng.api.config.FuzzyMode;
import appeng.api.config.Settings;
import appeng.client.gui.Icon;
import appeng.client.gui.implementations.UpgradeableScreen;
import appeng.client.gui.style.ScreenStyle;
import appeng.client.gui.widgets.IconButton;
import appeng.client.gui.widgets.ServerSettingToggleButton;
import appeng.client.gui.widgets.SettingToggleButton;
import appeng.core.definitions.AEItems;
import appeng.core.localization.ButtonToolTips;
import appeng.menu.SlotSemantics;
import com.mikazukichandamege.reinforcedae.common.menu.RnfInterfaceMenu;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public class RnfInterfaceScreen<R extends RnfInterfaceMenu> extends UpgradeableScreen<R> {
    private final SettingToggleButton<FuzzyMode> fuzzyMode;
    private final List<Button> buttons = new ArrayList<>();

    public RnfInterfaceScreen(R menu, Inventory playerInventory, Component title, ScreenStyle style) {
        super(menu, playerInventory, title, style);
        this.fuzzyMode = new ServerSettingToggleButton<>(Settings.FUZZY_MODE, FuzzyMode.IGNORE_ALL);
        addToLeftToolbar(this.fuzzyMode);
        widgets.addOpenPriorityButton();

        var configSlots = menu.getSlots(SlotSemantics.CONFIG);

        for (int i = 0; i < configSlots.size(); i++) {
            var button = new SetAmountButtom(btn -> {
                var idx = buttons.indexOf(btn);
                var configSlot = configSlots.get(idx);
                menu.openSetAmountMenu(configSlot.index);
            });
            button.setDisableBackground(true);
            button.setMessage(ButtonToolTips.InterfaceSetStockAmount.text());
            widgets.add("amtButton" + (1 + i), button);
            buttons.add(button);
        }
    }

    @Override
    protected void updateBeforeRender() {
        super.updateBeforeRender();

        this.fuzzyMode.set(menu.getFuzzyMode());
        this.fuzzyMode.setVisibility(menu.hasUpgrade(AEItems.FUZZY_CARD));
        var configSlots = menu.getSlots(SlotSemantics.CONFIG);

        for (int i = 0; i < buttons.size(); i++) {
            var button = buttons.get(i);
            var item = configSlots.get(i).getItem();
            button.visible = !item.isEmpty();
        }
    }

    static class SetAmountButtom extends IconButton {
        public SetAmountButtom(OnPress press) {
            super(press);
        }

        @Override
        protected Icon getIcon() {
            return isHoveredOrFocused() ? Icon.WRENCH : Icon.WRENCH_DISABLED;
        }
    }
}
