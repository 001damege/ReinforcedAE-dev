package com.mikazukichandamege.reinforcedae.definition;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainer {
    public static final DeferredRegister<MenuType<?>> DR = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ReinforcedAE.MOD_ID);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<?>> register(String id, IContainerFactory<T> factory) {
        return DR.register(id, () -> IForgeMenuType.create(factory));
    }

    public static void init(IEventBus modEventBus) {
        DR.register(modEventBus);
    }
}
