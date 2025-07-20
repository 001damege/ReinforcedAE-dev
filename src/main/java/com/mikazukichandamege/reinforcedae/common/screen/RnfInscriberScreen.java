package com.mikazukichandamege.reinforcedae.common.screen;

import appeng.api.config.Settings;
import appeng.api.config.YesNo;
import appeng.client.gui.implementations.UpgradeableScreen;
import appeng.client.gui.style.ScreenStyle;
import appeng.client.gui.widgets.ProgressBar;
import appeng.client.gui.widgets.ServerSettingToggleButton;
import appeng.client.gui.widgets.SettingToggleButton;
import com.mikazukichandamege.reinforcedae.common.menu.RnfInscriberMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class RnfInscriberScreen extends UpgradeableScreen<RnfInscriberMenu> {
    private final ProgressBar pb;
    private final SettingToggleButton<YesNo> separateSidesBtn;
    private final SettingToggleButton<YesNo> autoExportBtn;
    private final SettingToggleButton<YesNo> bufferSizeBtn;

    public RnfInscriberScreen(RnfInscriberMenu menu, Inventory playerInventory, Component title, ScreenStyle style) {
        super(menu, playerInventory, title, style);
        this.pb = new ProgressBar(this.getMenu(), style.getImage("progressBar"), ProgressBar.Direction.VERTICAL);
        widgets.add("progressBar", this.pb);

        this.separateSidesBtn = new ServerSettingToggleButton<>(Settings.INSCRIBER_SEPARATE_SIDES, YesNo.NO);
        this.addToLeftToolbar(separateSidesBtn);

        this.autoExportBtn = new ServerSettingToggleButton<>(Settings.AUTO_EXPORT, YesNo.NO);
        this.addToLeftToolbar(autoExportBtn);

        this.bufferSizeBtn = new ServerSettingToggleButton<>(Settings.INSCRIBER_BUFFER_SIZE, YesNo.YES);
        this.addToLeftToolbar(bufferSizeBtn);
    }

    @Override
    protected void updateBeforeRender() {
        super.updateBeforeRender();

        int progress = this.menu.getCurrentProgress() * 100 / this.menu.getMaxProgress();
        this.pb.setFullMsg(Component.literal(progress + "%"));

        this.separateSidesBtn.set(getMenu().getSeparateSides());
        this.autoExportBtn.set(getMenu().getAutoExport());
        this.bufferSizeBtn.set(getMenu().getBufferSize());
    }
}
