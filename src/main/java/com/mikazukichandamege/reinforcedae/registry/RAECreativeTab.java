package com.mikazukichandamege.reinforcedae.registry;

import appeng.block.AEBaseBlock;
import appeng.block.AEBaseBlockItem;
import appeng.items.AEBaseItem;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registries.BlockDeferredRegistries;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;

@SuppressWarnings("unused")
public final class RAECreativeTab {
    public static final DeferredRegister<CreativeModeTab> DR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ReinforcedAE.MOD_ID);

    static {
        DR.register("tab", () -> CreativeModeTab.builder()
                .title(RAETranslation.Mod_Name.text())
                .icon(RAEItem.OPTICS_PROCESSOR::stack)
                .displayItems(RAECreativeTab::insertTab)
                .build());
    }

    private static void insertTab(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output output) {
        var itemDefs = new ArrayList<ItemDeferredRegistries<?>>();
        itemDefs.addAll(RAEItem.getItems());
        itemDefs.addAll(RAEBlock.getBlocks().stream().map(BlockDeferredRegistries::item).toList());

        for (var itemDef : itemDefs) {
            var item = itemDef.asItem();

            if (item instanceof AEBaseBlockItem baseItem && baseItem.getBlock() instanceof AEBaseBlock baseBlock) {
                baseBlock.addToMainCreativeTab(output);
            } else if (item instanceof AEBaseItem baseItem) {
                baseItem.addToMainCreativeTab(output);
            } else {
                output.accept(itemDef);
            }
        }
    }
}
