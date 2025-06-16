package com.mikazukichandamege.reinforcedae.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTag {

    public static final TagKey<Block> NEEDS_CHAOS_TOOL = BlockTags.create(forge("needs_chaos_tool"));

    public static ResourceLocation forge(String path) {
        return new ResourceLocation("forge", path);
    }
}
