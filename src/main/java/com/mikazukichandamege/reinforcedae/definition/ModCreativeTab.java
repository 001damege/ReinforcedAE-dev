package com.mikazukichandamege.reinforcedae.definition;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.integration.appflux.AppFluxItem;
import com.mikazukichandamege.reinforcedae.integration.ars.ArsItem;
import com.mikazukichandamege.reinforcedae.integration.botania.BotaniaItem;
import com.mikazukichandamege.reinforcedae.integration.mekanism.MekanismItem;
import com.mikazukichandamege.reinforcedae.util.ModTooltip;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public final class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> DR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ReinforcedAE.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB = DR.register(ModTooltip.TAB_ID, () -> CreativeModeTab.builder()
            .title(Component.translatable(ModTooltip.TAB_ID))
            .icon(() -> ModItem.OPTICS_PROCESSOR.get().asItem().getDefaultInstance())
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
