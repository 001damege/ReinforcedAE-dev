package com.mikazukichandamege.reinforcedae.item.tool;

import appeng.core.localization.GuiText;
import appeng.hooks.IntrinsicEnchantItem;
import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.server.command.TextComponentHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemChaosAxe extends AxeItem implements IntrinsicEnchantItem {

    public ItemChaosAxe(Properties properties) {
        super(ModToolTier.CHAOS_TIER, Float.MAX_VALUE, 5.0f, properties.rarity(ModRarity.CHAOS).fireResistant().stacksTo(1));
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(BlockTags.MINEABLE_WITH_AXE)) return 8888.0f;
        return Math.max(super.getDestroySpeed(pStack, pState), 9999.0f);
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
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(TextComponentHelper.createComponentTranslation(null, "indestructible", new Object()).withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(GuiText.IntrinsicEnchant.text(Enchantments.BLOCK_EFFICIENCY.getFullname(10)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        if (state.is(BlockTags.MINEABLE_WITH_AXE)) return TierSortingRegistry.isCorrectTierForDrops(ModToolTier.CHAOS_TIER, state);
        if (state.is(ModToolTier.CHAOS_TOOL_TAG)) return TierSortingRegistry.isCorrectTierForDrops(ModToolTier.CHAOS_TIER, state);
        return false;
    }

    @Override
    public int getIntrinsicEnchantLevel(ItemStack itemStack, Enchantment enchantment) {
        return enchantment == Enchantments.BLOCK_EFFICIENCY ? 10 : 0;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
