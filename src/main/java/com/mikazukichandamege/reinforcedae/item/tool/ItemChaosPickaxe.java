package com.mikazukichandamege.reinforcedae.item.tool;

import com.google.common.collect.Sets;
import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class ItemChaosPickaxe extends PickaxeItem {

    private static final Set<ToolAction> DIG_ACTION = Sets.newHashSet(ToolActions.AXE_DIG, ToolActions.PICKAXE_DIG, ToolActions.SHOVEL_DIG, ToolActions.HOE_DIG, ToolActions.SWORD_DIG);

    public ItemChaosPickaxe(Properties properties) {
        super(ModToolTier.CHAOS_TIER, 1, 5.0f, properties.rarity(ModRarity.CHAOS).fireResistant().stacksTo(1));
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(BlockTags.MINEABLE_WITH_PICKAXE)) return speed;
        return super.getDestroySpeed(pStack, pState);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advancedTooltip) {
        tooltip.add(Component.translatable("tooltip.reinforcedae.indestructible").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, level, tooltip, advancedTooltip);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return true;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 30;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return DIG_ACTION.contains(toolAction);
    }
}
