package com.mikazukichandamege.reinforcedae.registries;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.core.definitions.BlockDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class BlockDeferredRegistries<T extends Block> implements ItemLike {
    private final String englishName;
    private final RegistryObject<T> block;
    private final ItemDeferredRegistries<BlockItem> item;

    public BlockDeferredRegistries(String englishName, RegistryObject<T> block, ItemDeferredRegistries<BlockItem> item) {
        this.englishName = englishName;
        this.block = Objects.requireNonNull(block, "block");
        this.item = Objects.requireNonNull(item, "item");
    }

    public String getEnglishName() {
        return englishName;
    }

    public ResourceLocation id() {
        return block.getId();
    }

    public final T block() {
        return this.block.get();
    }

    public ItemStack stack() {
        return item.stack();
    }

    public ItemStack stack(int stackSize) {
        return item.stack(stackSize);
    }

    public GenericStack genericStack(long stackSize) {
        return item.genericStack(stackSize);
    }

    public boolean is(ItemStack comparableStack) {
        return item.is(comparableStack);
    }

    public boolean is(AEKey key) {
        return item.is(key);
    }

    public ItemDeferredRegistries<BlockItem> item() {
        return item;
    }

    @Override
    public BlockItem asItem() {
        return item.asItem();
    }

    public BlockDefinition<T> getBlockDefinition() {
        return new BlockDefinition<>(this.englishName, id(), block(), asItem());
    }
}
