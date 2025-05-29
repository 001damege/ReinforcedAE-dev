package com.mikazukichandamege.reinforcedae.item.tool;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.definition.ModItem;
import com.mikazukichandamege.reinforcedae.definition.ModTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTier {
    public static final TagKey<Block> CHAOS_TOOL_TAG = BlockTags.create(new ResourceLocation("mineable/pickaxe"));
    public static final TagKey<Block> CHAOS_TIER_TAG = ModTag.NEEDS_CHAOS_TOOL;

    public static final Tier CHAOS_TIER = TierSortingRegistry.registerTier(
            new ForgeTier(5, 15000, 10, 11.0f, 85, CHAOS_TIER_TAG, () -> Ingredient.of(ModItem.CHAOS_INGOT.get())),
            new ResourceLocation(ReinforcedAE.MOD_ID, "chaos"),
            List.of(Tiers.NETHERITE), List.of());
}
