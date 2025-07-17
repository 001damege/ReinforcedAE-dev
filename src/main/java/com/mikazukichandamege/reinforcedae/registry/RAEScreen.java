package com.mikazukichandamege.reinforcedae.registry;

import appeng.init.client.InitScreens;
import com.mikazukichandamege.reinforcedae.common.menu.RnfDriveMenu;
import com.mikazukichandamege.reinforcedae.common.menu.RnfInterfaceMenu;
import com.mikazukichandamege.reinforcedae.common.menu.RnfPatternProviderMenu;
import com.mikazukichandamege.reinforcedae.common.screen.RnfDriveScreen;
import com.mikazukichandamege.reinforcedae.common.screen.RnfInterfaceScreen;
import com.mikazukichandamege.reinforcedae.common.screen.RnfPatternProviderScreen;

public final class RAEScreen {

    public static void register() {
        InitScreens.register(RnfPatternProviderMenu.TYPE, RnfPatternProviderScreen<RnfPatternProviderMenu>::new, "/screens/rnf_pattern_provider.json");
        InitScreens.register(RnfInterfaceMenu.TYPE, RnfInterfaceScreen<RnfInterfaceMenu>::new, "/screens/rnf_interface.json");
        InitScreens.register(RnfDriveMenu.TYPE, RnfDriveScreen<RnfDriveMenu>::new, "/screens/rnf_drive.json");
    }

    private RAEScreen() {}
}
