package com.mikazukichandamege.reinforcedae.registry;

import appeng.menu.AEBaseMenu;
import appeng.menu.implementations.MenuTypeBuilder;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class RAEContainer {
    public static final DeferredRegister<MenuType<?>> DR = DeferredRegister.create(Registries.MENU, ReinforcedAE.MOD_ID);

    private static <M extends AEBaseMenu, H> Supplier<MenuType<M>> create(String id, MenuTypeBuilder.MenuFactory<M, H> factory, Class<H> host) {
        return DR.register("rae_" + id, () -> MenuTypeBuilder.create(factory, host).build("rae_" + id));
    }
}
