package com.mikazukichandamege.reinforcedae.common.block;

import appeng.block.crafting.ICraftingUnitType;
import com.mikazukichandamege.reinforcedae.registries.BlockDeferredRegistries;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;
import net.minecraft.world.item.Item;

import java.util.Objects;

public enum RnfCraftingUnitType implements ICraftingUnitType {
    ACCELERATOR_1024x(0, "accelerator_1024x", 1024),
    ACCELERATOR_16x(0, "accelerator_16x", 16),
    ACCELERATOR_256x(0, "accelerator_256x", 256),
    ACCELERATOR_4x(0, "accelerator_4x", 4),
    ACCELERATOR_64x(0, "accelerator_64x", 64),
    ACCELERATOR_4096x(0, "accelerator_8192x", 4096),
    ACCELERATOR_16384x(0, "accelerator_16384x", 16384),
    ACCELERATOR_65536x(0, "accelerator_65536x", 65536),
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

    RnfCraftingUnitType(int storageMb, String affix, int thread) {
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

    public BlockDeferredRegistries<?> getDefinition() {
        return switch (this) {
            case ACCELERATOR_1024x -> RAEBlock.ACCELERATOR_1024x;
            case ACCELERATOR_16x -> RAEBlock.ACCELERATOR_16x;
            case ACCELERATOR_256x -> RAEBlock.ACCELERATOR_256x;
            case ACCELERATOR_4x -> RAEBlock.ACCELERATOR_4x;
            case ACCELERATOR_64x -> RAEBlock.ACCELERATOR_64x;
            case ACCELERATOR_4096x -> RAEBlock.ACCELERATOR_4096x;
            case ACCELERATOR_16384x -> RAEBlock.ACCELERATOR_16384x;
            case ACCELERATOR_65536x -> RAEBlock.ACCELERATOR_65536x;
            case MONITOR -> RAEBlock.MONITOR;
            case STORAGE_16G -> RAEBlock.STORAGE_16G;
            case STORAGE_1G -> RAEBlock.STORAGE_1G;
            case STORAGE_256G -> RAEBlock.STORAGE_256G;
            case STORAGE_4G -> RAEBlock.STORAGE_4G;
            case STORAGE_64G -> RAEBlock.STORAGE_64G;
            case UNIT -> RAEBlock.UNIT;
        };
    }
}
