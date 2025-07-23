package com.mikazukichandamege.reinforcedae.registry;

import appeng.block.crafting.CraftingMonitorBlock;
import appeng.block.crafting.CraftingUnitBlock;
import appeng.block.networking.EnergyCellBlock;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.common.block.*;
import com.mikazukichandamege.reinforcedae.common.block.energy.RnfEnergyBlockItem;
import com.mikazukichandamege.reinforcedae.registries.BlockDeferredRegistries;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class RAEBlock {
    public static final DeferredRegister<Block> DR = DeferredRegister.create(ForgeRegistries.BLOCKS, ReinforcedAE.MOD_ID);
    private static final List<BlockDeferredRegistries<?>> BLOCKS = new ArrayList<>();

    public static List<BlockDeferredRegistries<?>> getBlocks() {
        return Collections.unmodifiableList(BLOCKS);
    }

    public static final BlockDeferredRegistries<CraftingUnitBlock> UNIT = block("Reinforced Crafting Unit", "rnf_crafting_unit", () -> new CraftingUnitBlock(RnfCraftingUnitType.UNIT), RnfBlockItem::new);
    public static final BlockDeferredRegistries<CraftingMonitorBlock> MONITOR = block("Reinforced Crafting Monitor", "rnf_crafting_monitor", () -> new CraftingMonitorBlock(RnfCraftingUnitType.MONITOR), RnfBlockItem::new);
    public static final BlockDeferredRegistries<CraftingUnitBlock> ACCELERATOR_4x = block("Reinforced Crafting 4x Co-Processing Unit", "rnf_accelerator_4x", () -> new CraftingUnitBlock(RnfCraftingUnitType.ACCELERATOR_4x), RnfBlockItem::new);
    public static final BlockDeferredRegistries<CraftingUnitBlock> ACCELERATOR_16x = block("Reinforced Crafting 16x Co-Processing Unit", "rnf_accelerator_16x", () -> new CraftingUnitBlock(RnfCraftingUnitType.ACCELERATOR_16x), RnfBlockItem::new);
    public static final BlockDeferredRegistries<CraftingUnitBlock> ACCELERATOR_64x = block("Reinforced Crafting 64x Co-Processing Unit", "rnf_accelerator_64x", () -> new CraftingUnitBlock(RnfCraftingUnitType.ACCELERATOR_64x), RnfBlockItem::new);
    public static final BlockDeferredRegistries<CraftingUnitBlock> ACCELERATOR_256x = block("Reinforced Crafting 256x Co-Processing Unit", "rnf_accelerator_256x", () -> new CraftingUnitBlock(RnfCraftingUnitType.ACCELERATOR_256x), RnfBlockItem::new);
    public static final BlockDeferredRegistries<CraftingUnitBlock> ACCELERATOR_1024x = block("Reinforced Crafting 1024x Co-Processing Unit", "rnf_accelerator_1024x", () -> new CraftingUnitBlock(RnfCraftingUnitType.ACCELERATOR_1024x), RnfBlockItem::new);
    public static final BlockDeferredRegistries<CraftingUnitBlock> ACCELERATOR_4096x = block("Reinforced Crafting 4096x Co-Processing Unit", "rnf_accelerator_4096x", () -> new CraftingUnitBlock(RnfCraftingUnitType.ACCELERATOR_4096x), RnfBlockItem::new);
    public static final BlockDeferredRegistries<CraftingUnitBlock> ACCELERATOR_16384x = block("Reinforced Crafting 16384x Co-Processing Unit", "rnf_accelerator_16384x", () -> new CraftingUnitBlock(RnfCraftingUnitType.ACCELERATOR_16384x), RnfBlockItem::new);
    public static final BlockDeferredRegistries<CraftingUnitBlock> ACCELERATOR_65536x = block("Reinforced Crafting 65536x Co-Processing Unit", "rnf_accelerator_65536x", () -> new CraftingUnitBlock(RnfCraftingUnitType.ACCELERATOR_65536x), RnfBlockItem::new);
    public static final BlockDeferredRegistries<CraftingUnitBlock> STORAGE_1G = block("1G Crafting Storage", "rnf_crafting_storage_1g", () -> new CraftingUnitBlock(RnfCraftingUnitType.STORAGE_1G), () -> RAEItem.CELL_COMPONENT_1G);
    public static final BlockDeferredRegistries<CraftingUnitBlock> STORAGE_4G = block("4G Crafting Storage", "rnf_crafting_storage_4g", () -> new CraftingUnitBlock(RnfCraftingUnitType.STORAGE_4G), () -> RAEItem.CELL_COMPONENT_4G);
    public static final BlockDeferredRegistries<CraftingUnitBlock> STORAGE_16G = block("16G Crafting Storage", "rnf_crafting_storage_16g", () -> new CraftingUnitBlock(RnfCraftingUnitType.STORAGE_16G), () -> RAEItem.CELL_COMPONENT_16G);
    public static final BlockDeferredRegistries<CraftingUnitBlock> STORAGE_64G = block("64G Crafting Storage", "rnf_crafting_storage_64g", () -> new CraftingUnitBlock(RnfCraftingUnitType.STORAGE_64G), () -> RAEItem.CELL_COMPONENT_64G);
    public static final BlockDeferredRegistries<CraftingUnitBlock> STORAGE_256G = block("256G Crafting Storage", "rnf_crafting_storage_256g", () -> new CraftingUnitBlock(RnfCraftingUnitType.STORAGE_256G), () -> RAEItem.CELL_COMPONENT_256G);
    public static final BlockDeferredRegistries<RnfPatternProviderBlock> PATTERN_PROVIDER = block("Reinforced Pattern Provider", "rnf_pattern_provider", RnfPatternProviderBlock::new, RnfBlockItem::new);
    public static final BlockDeferredRegistries<RnfInterfaceBlock> INTERFACE = block("Reinforced Interface", "rnf_interface", RnfInterfaceBlock::new, RnfBlockItem::new);
    public static final BlockDeferredRegistries<EnergyCellBlock> IMP_ENERGY = block("Improved Energy Cell", "imp_energy_cell", () -> new EnergyCellBlock(16777216, 3200, 12800), RnfEnergyBlockItem::new);
    public static final BlockDeferredRegistries<EnergyCellBlock> ADV_ENERGY = block("Advanced Energy Cell", "adv_energy_cell", () -> new EnergyCellBlock(115343360, 18400, 12800), RnfEnergyBlockItem::new);
    public static final BlockDeferredRegistries<EnergyCellBlock> PER_ENERGY = block("Perfect Energy Cell", "per_energy_cell", () -> new EnergyCellBlock(805306368, 147200, 12800), RnfEnergyBlockItem::new);
    public static final BlockDeferredRegistries<EnergyCellBlock> QUA_ENERGY = block("Quantum Energy Cell", "qua_energy_cell", () -> new EnergyCellBlock(1.7179869184E10, 1177600, 128000), RnfEnergyBlockItem::new);
    public static final BlockDeferredRegistries<EnergyCellBlock> INF_ENERGY = block("Infinity Energy Cell", "inf_energy_cell", () -> new EnergyCellBlock(9223372036854775807L, 2147483647, 128000), RnfEnergyBlockItem::new);
    public static final BlockDeferredRegistries<RnfDriveBlock> DRIVE = block("Reinforced Drive", "rnf_drive", RnfDriveBlock::new, RnfBlockItem::new);
    public static final BlockDeferredRegistries<RnfInscriberBlock> INSCRIBER = block("Reinforced Inscriber", "rnf_inscriber", RnfInscriberBlock::new, RnfBlockItem::new);

    private static <T extends Block> BlockDeferredRegistries<T> block(String englishName, String id, Supplier<T> blockSupplier, @Nullable BiFunction<Block, Item.Properties, BlockItem> factory) {
        var block = DR.register(id, blockSupplier);
        var item = RAEItem.DR.register(id, () -> Objects.requireNonNull(factory).apply(block.get(), new Item.Properties()));
        var itemDef = new ItemDeferredRegistries<>(englishName, item);
        var def = new BlockDeferredRegistries<>(englishName, block, itemDef);
        BLOCKS.add(def);
        return def;
    }

    private static <T extends Block> BlockDeferredRegistries<T> block(String englishName, String id, Supplier<T> blockSupplier, Supplier<ItemLike> disassemblyExtra) {
        return block(englishName, id, blockSupplier, (b, p) -> new RnfCraftingBlockItem(b, p, disassemblyExtra));
    }
}
