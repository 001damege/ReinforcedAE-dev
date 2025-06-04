package com.mikazukichandamege.reinforcedae.item.tool;

import appeng.hooks.IntrinsicEnchantItem;
import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.server.command.TextComponentHelper;
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
        if (entity instanceof LivingEntity) {
            LivingEntity victim = (LivingEntity) entity;
            if (!victim.isDeadOrDying() && victim.getHealth() > 0) {
                victim.setHealth(0);
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
        tooltip.add(TextComponentHelper.createComponentTranslation(null, "victim.setHealth(0)!", new Object()).withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, level, tooltip, advancedTooltip);
    }
}
