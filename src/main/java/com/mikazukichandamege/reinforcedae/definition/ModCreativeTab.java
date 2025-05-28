package com.mikazukichandamege.reinforcedae.definition;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.integration.appflux.AppFluxItem;
import com.mikazukichandamege.reinforcedae.integration.ars.ArsItem;
import com.mikazukichandamege.reinforcedae.integration.botania.BotaniaItem;
import com.mikazukichandamege.reinforcedae.integration.mekanism.MekanismItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public final class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> DR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ReinforcedAE.MOD_ID);
    public static final String ID = "itemgroup." + ReinforcedAE.MOD_ID + ".general";

    public static final RegistryObject<CreativeModeTab> TAB = DR.register(ID, () -> CreativeModeTab.builder()
            .title(Component.translatable(ID))
            .icon(() -> ModItem.INFINITY_PROCESSOR.get().asItem().getDefaultInstance())
            .displayItems((display, output) -> {
                for (var entryItem : ModItem.DR.getEntries()) {
                    output.accept(entryItem.get());
                }
                for (var entryMekanismItem : MekanismItem.DR.getEntries()) {
                    output.accept(entryMekanismItem.get());
                }
                for (var entryBotaniaItem : BotaniaItem.DR.getEntries()) {
                    output.accept(entryBotaniaItem.get());
                }
                for (var entryArsItem : ArsItem.DR.getEntries()) {
                    output.accept(entryArsItem.get());
                }
                for (var entryAppFluxItem : AppFluxItem.DR.getEntries()) {
                    output.accept(entryAppFluxItem.get());
                }
            })
            .build());

    public static void init(IEventBus modEventBus) {
        DR.register(modEventBus);
    }
}
