package com.mikazukichandamege.reinforcedae.registry;

import appeng.block.AEBaseBlockItem;
import appeng.block.crafting.CraftingUnitBlock;
import appeng.core.definitions.BlockDefinition;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.common.block.ReinforcedCraftingBlockItem;
import com.mikazukichandamege.reinforcedae.common.block.ReinforcedCraftingUnitType;
import com.mikazukichandamege.reinforcedae.common.block.ReinforcedInterfaceBlock;
import com.mikazukichandamege.reinforcedae.common.block.ReinforcedPatternProviderBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class ModBlock {
    public static void init() {
        ReinforcedAE.LOGGER.info("Init Blocks");
    }

    private static final List<BlockDefinition<?>> BLOCKS = new ArrayList<>();

    public static List<BlockDefinition<?>> getBlocks() {
        return Collections.unmodifiableList(BLOCKS);
    }

    public static final BlockDefinition<CraftingUnitBlock> CRAFTING_UNIT = block("Reinforced Crafting Unit", "reinforced_crafting_unit", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.UNIT), AEBaseBlockItem::new);
    public static final BlockDefinition<CraftingUnitBlock> CRAFTING_ACCELERATOR = block("Reinforced Crafting Co-Processing Unit", "reinforced_crafting_accelerator", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.ACCELERATOR), () -> ModItem.OPTICS_PROCESSOR);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_1G = block("1G Crafting Storage", "1g_crafting_storage", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.STORAGE_1024M), () -> ModItem.CELL_COMPONENT_1G);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_2G = block("2G Crafting Storage", "2g_crafting_storage", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.STORAGE_2048M), () -> ModItem.CELL_COMPONENT_2G);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_8G = block("8G Crafting Storage", "8g_crafting_storage", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.STORAGE_8192M), () -> ModItem.CELL_COMPONENT_8G);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_32G = block("32G Crafting Storage", "32g_crafting_storage", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.STORAGE_32768M), () -> ModItem.CELL_COMPONENT_32G);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_128G = block("128G Crafting Storage", "128g_crafting_storage", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.STORAGE_131072M), () -> ModItem.CELL_COMPONENT_128G);
    public static final BlockDefinition<ReinforcedPatternProviderBlock> REINFORCED_PATTERN_PROVIDER = block("Reinforced Pattern Provider", "reinforced_pattern_provider", ReinforcedPatternProviderBlock::new, AEBaseBlockItem::new);
    public static final BlockDefinition<ReinforcedInterfaceBlock> REINFORCED_INTERFACE = block("Reinforced Interface", "reinforced_interface", ReinforcedInterfaceBlock::new, AEBaseBlockItem::new);

    public static <T extends Block> BlockDefinition<T> block(String englishName, String id, Supplier<T> blockSupplier, BiFunction<Block, Item.Properties, BlockItem> factory) {
        var block = blockSupplier.get();
        var item = factory.apply(block, new Item.Properties());
        var registry = new BlockDefinition<>(englishName, ReinforcedAE.makeId(id), block, item);
        BLOCKS.add(registry);
        return registry;
    }

    public static <T extends Block> BlockDefinition<T> block(String englishName, String id, Supplier<T> blockSupplier, Supplier<ItemLike> disassemblyExtra) {
        return block(englishName, id, blockSupplier, (b, p) -> new ReinforcedCraftingBlockItem(b, p, disassemblyExtra));
    }
}
