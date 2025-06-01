package com.mikazukichandamege.reinforcedae.item.tool;

import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemChaosSword extends SwordItem {

    public ItemChaosSword(Properties properties) {
        super(ModToolTier.CHAOS_TIER, 3, 5.0f, properties.rarity(ModRarity.CHAOS).fireResistant().stacksTo(1));
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
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advancedTooltip) {
        tooltip.add(Component.translatable("tooltip.reinforcedae.indestructible").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, level, tooltip, advancedTooltip);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 30;
    }
}
