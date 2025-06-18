package com.mikazukichandamege.reinforcedae.registry;

import appeng.core.AppEng;
import appeng.helpers.InterfaceLogicHost;
import appeng.helpers.patternprovider.PatternProviderLogicHost;
import appeng.menu.AEBaseMenu;
import appeng.menu.implementations.MenuTypeBuilder;
import com.mikazukichandamege.reinforcedae.common.container.ReinforcedInterfaceContainer;
import com.mikazukichandamege.reinforcedae.common.container.ReinforcedPatternProviderContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModContainer {
    private static final Map<ResourceLocation, MenuType<?>> MENU_TYPES = new HashMap<>();

    public static Map<ResourceLocation, MenuType<?>> getContainerType() {
        return Collections.unmodifiableMap(MENU_TYPES);
    }

    public static final MenuType<ReinforcedPatternProviderContainer> REINFORCED_PATTERN_PROVIDER = create("reinforced_pattern_provider", ReinforcedPatternProviderContainer::new, PatternProviderLogicHost.class);
    public static final MenuType<ReinforcedInterfaceContainer> REINFORCED_INTERFACE = create("reinforced_interface", ReinforcedInterfaceContainer::new, InterfaceLogicHost.class);

    public static <C extends AEBaseMenu, I> MenuType<C> create(String id, MenuTypeBuilder.MenuFactory<C, I> factory, Class<I> host) {
        var menu = MenuTypeBuilder.create(factory, host).build(id);
        MENU_TYPES.put(AppEng.makeId(id), menu);
        return menu;
    }
}
