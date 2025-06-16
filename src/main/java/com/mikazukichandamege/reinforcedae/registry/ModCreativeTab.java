package com.mikazukichandamege.reinforcedae.registry;

import appeng.block.AEBaseBlock;
import appeng.block.AEBaseBlockItem;
import appeng.core.definitions.ItemDefinition;
import appeng.items.AEBaseItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class ModCreativeTab {
    private static final Multimap<ResourceKey<CreativeModeTab>, ItemDefinition<?>> externalItemDefs = HashMultimap.create();
    private static final List<ItemDefinition<?>> itemDefs = new ArrayList<>();

    public static void init(Registry<CreativeModeTab> registry) {
        var tab = CreativeModeTab.builder()
                .title(ModTranslation.Mod_Name.text())
                .icon(() -> ModItem.OPTICS_PROCESSOR.stack(1))
                .displayItems(ModCreativeTab::build)
                .build();
        Registry.register(registry, "main", tab);
    }

    public static void initExternal(BuildCreativeModeTabContentsEvent event) {
        for (var itemDefinition : externalItemDefs.get(event.getTabKey())) {
            event.accept(itemDefinition);
        }
    }

    public static void addExternal(ResourceKey<CreativeModeTab> tab, ItemDefinition<?> itemDef) {
        externalItemDefs.put(tab, itemDef);
    }

    private static void build(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output output) {
        var itemDefis = new ArrayList<ItemDefinition<?>>();
        itemDefis.addAll(ModItem.getItems());
        itemDefis.addAll(ModBlock.getBlocks());
        for (var itemDef : itemDefis) {
            var item = itemDef.asItem();

            if (item instanceof AEBaseBlockItem baseItem
                    && baseItem.getBlock() instanceof AEBaseBlock baseBlock) {
                baseBlock.addToMainCreativeTab(output);
            } else if (item instanceof AEBaseItem baseItem) {
                baseItem.addToMainCreativeTab(output);
            } else {
                output.accept(itemDef);
            }
        }
    }
}
