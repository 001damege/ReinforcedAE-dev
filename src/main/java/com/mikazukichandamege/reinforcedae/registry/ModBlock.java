package com.mikazukichandamege.reinforcedae.registry;

import appeng.block.AEBaseBlockItem;
import appeng.block.crafting.CraftingMonitorBlock;
import appeng.block.crafting.CraftingUnitBlock;
import appeng.block.networking.EnergyCellBlock;
import appeng.block.networking.EnergyCellBlockItem;
import appeng.core.definitions.AEParts;
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
    public static final BlockDefinition<CraftingUnitBlock> ACCELERATOR_4X = block("Reinforced Crafting 4x Co-Processing Unit", "reinforced_crafting_accelerator_4x", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.ACCELERATOR_4x), () -> null);
    public static final BlockDefinition<CraftingUnitBlock> ACCELERATOR_16X = block("Reinforced Crafting 16x Co-Processing Unit", "reinforced_crafting_accelerator_16x", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.ACCELERATOR_16X), () -> null);
    public static final BlockDefinition<CraftingUnitBlock> ACCELERATOR_32X = block("Reinforced Crafting 32x Co-Processing Unit", "reinforced_crafting_accelerator_32x", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.ACCELERATOR_32X), () -> null);
    public static final BlockDefinition<CraftingUnitBlock> ACCELERATOR_64X = block("Reinforced Crafting 64x Co-Processing Unit", "reinforced_crafting_accelerator_64x", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.ACCELERATOR_64X), () -> null);
    public static final BlockDefinition<CraftingUnitBlock> ACCELERATOR_128X = block("Reinforced Crafting 128x Co-Processing Unit", "reinforced_crafting_accelerator_128x", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.ACCELERATOR_128X), () -> null);
    public static final BlockDefinition<CraftingUnitBlock> ACCELERATOR_256X = block("Reinforced Crafting 256x Co-Processing Unit", "reinforced_crafting_accelerator_256x", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.ACCELERATOR_256X), () -> null);
    public static final BlockDefinition<CraftingUnitBlock> ACCELERATOR_1024X = block("Reinforced Crafting 1024x Co-Processing Unit", "reinforced_crafting_accelerator_1024x", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.ACCELERATOR_1024X), () -> null);
    public static final BlockDefinition<CraftingUnitBlock> ACCELERATOR_2048X = block("Reinforced Crafting 2048x Co-Processing Unit", "reinforced_crafting_accelerator_2048x", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.ACCELERATOR_2048X), () -> null);
    public static final BlockDefinition<CraftingUnitBlock> ACCELERATOR_8192X = block("Reinforced Crafting 8192x Co-Processing Unit", "reinforced_crafting_accelerator_8192x", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.ACCELERATOR_8192X), () -> null);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_1G = block("1G Crafting Storage", "1g_crafting_storage", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.STORAGE_1024M), () -> ModItem.CELL_COMPONENT_1G);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_2G = block("2G Crafting Storage", "2g_crafting_storage", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.STORAGE_2048M), () -> ModItem.CELL_COMPONENT_2G);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_8G = block("8G Crafting Storage", "8g_crafting_storage", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.STORAGE_8192M), () -> ModItem.CELL_COMPONENT_8G);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_32G = block("32G Crafting Storage", "32g_crafting_storage", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.STORAGE_32768M), () -> ModItem.CELL_COMPONENT_32G);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_128G = block("128G Crafting Storage", "128g_crafting_storage", () -> new CraftingUnitBlock(ReinforcedCraftingUnitType.STORAGE_131072M), () -> ModItem.CELL_COMPONENT_128G);
    public static final BlockDefinition<ReinforcedPatternProviderBlock> REINFORCED_PATTERN_PROVIDER = block("Reinforced Pattern Provider", "reinforced_pattern_provider", ReinforcedPatternProviderBlock::new, AEBaseBlockItem::new);
    public static final BlockDefinition<ReinforcedInterfaceBlock> REINFORCED_INTERFACE = block("Reinforced Interface", "reinforced_interface", ReinforcedInterfaceBlock::new, AEBaseBlockItem::new);
    public static final BlockDefinition<CraftingMonitorBlock> CRAFTING_MONITOR = block("Reinforced Crafting Monitor", "reinforced_crafting_monitor", () -> new CraftingMonitorBlock(ReinforcedCraftingUnitType.MONITOR), () -> AEParts.STORAGE_MONITOR);
    public static final BlockDefinition<EnergyCellBlock> IMPROVED_ENERGY_CELL = block("Improved Energy Cell", "improved_energy_cell", () -> new EnergyCellBlock(16777216, 3200, 12800), EnergyCellBlockItem::new);
    public static final BlockDefinition<EnergyCellBlock> ADVANCED_ENERGY_CELL = block("Advanced Energy Cell", "advanced_energy_cell", () -> new EnergyCellBlock(115343360, 3200, 12800), EnergyCellBlockItem::new);
    public static final BlockDefinition<EnergyCellBlock> PERFECT_ENERGY_CELL = block("Perfect Energy Cell", "perfect_energy_cell", () -> new EnergyCellBlock(805306368, 3200, 12800), EnergyCellBlockItem::new);
    public static final BlockDefinition<EnergyCellBlock> QUANTUM_ENERGY_CELL = block("Quantum Energy Cell", "quantum_energy_cell", () -> new EnergyCellBlock(1.7179869184E10, 3200, 12800), EnergyCellBlockItem::new);

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
