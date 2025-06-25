package com.mikazukichandamege.reinforcedae.common.block;

import appeng.block.crafting.ICraftingUnitType;
import appeng.core.definitions.BlockDefinition;
import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import net.minecraft.world.item.Item;

import java.util.Objects;

public enum ReinforcedCraftingUnitType implements ICraftingUnitType {
    ACCELERATOR_1024X(0, "accelerator_1024x", 1024),
    ACCELERATOR_128X(0, "accelerator_128x", 128),
    ACCELERATOR_16X(0, "accelerator_16x", 16),
    ACCELERATOR_2048X(0, "accelerator_2048x", 2048),
    ACCELERATOR_256X(0, "accelerator_256x", 256),
    ACCELERATOR_32X(0, "accelerator_32x", 32),
    ACCELERATOR_4x(0, "accelerator_4x", 4),
    ACCELERATOR_64X(0, "accelerator_64x", 64),
    ACCELERATOR_8192X(0, "accelerator_8192x", 8192),
    MONITOR(0, "monitor", 0),
    STORAGE_16G(16384, "16g_storage", 0),
    STORAGE_1G(1024, "1g_storage", 0),
    STORAGE_256G(262144, "256g_storage", 0),
    STORAGE_4G(4096, "4g_storage", 0),
    STORAGE_64G(65536, "64g_storage", 0),
    UNIT(0, "unit", 0);

    private final int storageMb;
    private final String affix;
    private final int thread;

    ReinforcedCraftingUnitType(int storageMb, String affix, int thread) {
        this.storageMb = storageMb;
        this.affix = affix;
        this.thread = thread;
    }

    @Override
    public long getStorageBytes() {
        return 1024L * 1024 * storageMb;
    }

    @Override
    public int getAcceleratorThreads() {
        return this.thread;
    }

    public String getAffix() {
        return affix;
    }

    @Override
    public Item getItemFromType() {
        return Objects.requireNonNull(getDefinition()).asItem();
    }

    public BlockDefinition<?> getDefinition() {
        return switch (this) {
            case STORAGE_1G -> ModBlock.STORAGE_1G;
            case STORAGE_4G -> ModBlock.STORAGE_4G;
            case STORAGE_16G -> ModBlock.STORAGE_16G;
            case STORAGE_64G -> ModBlock.STORAGE_64G;
            case STORAGE_256G -> ModBlock.STORAGE_256G;
            case UNIT -> ModBlock.CRAFTING_UNIT;
            case ACCELERATOR_8192X -> ModBlock.ACCELERATOR_8192X;
            case ACCELERATOR_2048X -> ModBlock.ACCELERATOR_2048X;
            case ACCELERATOR_1024X -> ModBlock.ACCELERATOR_1024X;
            case ACCELERATOR_256X -> ModBlock.ACCELERATOR_256X;
            case ACCELERATOR_128X -> ModBlock.ACCELERATOR_128X;
            case ACCELERATOR_64X -> ModBlock.ACCELERATOR_64X;
            case ACCELERATOR_32X -> ModBlock.ACCELERATOR_32X;
            case ACCELERATOR_16X -> ModBlock.ACCELERATOR_16X;
            case ACCELERATOR_4x -> ModBlock.ACCELERATOR_4X;
            case MONITOR -> ModBlock.CRAFTING_MONITOR;
        };
    }
}
