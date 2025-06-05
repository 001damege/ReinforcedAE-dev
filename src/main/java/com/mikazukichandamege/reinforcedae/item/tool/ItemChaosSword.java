package com.mikazukichandamege.reinforcedae.item.tool;

import appeng.hooks.IntrinsicEnchantItem;
import com.mikazukichandamege.reinforcedae.util.ModRarity;
import com.mikazukichandamege.reinforcedae.util.ModTooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemChaosSword extends SwordItem implements IntrinsicEnchantItem {

    public ItemChaosSword(Properties properties) {
        super(ModToolTier.CHAOS_TIER, -12, 0f, properties
                .rarity(ModRarity.CHAOS)
                .fireResistant()
                .stacksTo(1));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        Level level = player.level();
        if (entity instanceof LivingEntity) {
            LivingEntity victim = (LivingEntity) entity;
            if (!victim.isDeadOrDying() && victim.getHealth() > 0) {
                victim.setHealth(0);
                //level.playSound(player, player.getOnPos(), SoundEvents.WITHER_SPAWN, SoundSource.PLAYERS, 1.0f, 5.0f);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public int getIntrinsicEnchantLevel(ItemStack itemStack, Enchantment enchantment) {
        return 0;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advancedTooltip) {
        tooltip.add(Component.translatable(ModTooltip.INDESTRUCTIBLE).withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, level, tooltip, advancedTooltip);
    }
}
