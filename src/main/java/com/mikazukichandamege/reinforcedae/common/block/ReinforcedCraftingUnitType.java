package com.mikazukichandamege.reinforcedae.common.block;

import appeng.block.crafting.ICraftingUnitType;
import appeng.core.definitions.BlockDefinition;
import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import net.minecraft.world.item.Item;

import java.util.Objects;

public enum ReinforcedCraftingUnitType implements ICraftingUnitType {
    STORAGE_1024M(1024, "1024m_storage"),
    STORAGE_2048M(2048, "2048m_storage"),
    STORAGE_8192M(8192, "8192m_storage"),
    STORAGE_32768M(32768, "32768m_storage"),
    STORAGE_131072M(131072, "131072m_storage"),
    UNIT(0, "unit"),
    ACCELERATOR(0, "accelerator"),
    MONITOR(0, "monitor");

    private final int storageMb;
    private final String affix;

    ReinforcedCraftingUnitType(int storageMb, String affix) {
        this.storageMb = storageMb;
        this.affix = affix;
    }

    @Override
    public long getStorageBytes() {
        return 1024L * 1024 * storageMb;
    }

    @Override
    public int getAcceleratorThreads() {
        return this == ACCELERATOR ? 16 : 0;
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
            case STORAGE_1024M -> ModBlock.STORAGE_1G;
            case STORAGE_2048M -> ModBlock.STORAGE_2G;
            case STORAGE_8192M -> ModBlock.STORAGE_8G;
            case STORAGE_32768M -> ModBlock.STORAGE_32G;
            case STORAGE_131072M -> ModBlock.STORAGE_128G;
            case UNIT -> ModBlock.CRAFTING_UNIT;
            case ACCELERATOR -> ModBlock.CRAFTING_ACCELERATOR;
            case MONITOR -> ModBlock.CRAFTING_MONITOR;
        };
    }
}
