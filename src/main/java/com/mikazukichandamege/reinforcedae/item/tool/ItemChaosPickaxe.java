package com.mikazukichandamege.reinforcedae.item.tool;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mikazukichandamege.reinforcedae.util.ModRarity;
import com.mikazukichandamege.reinforcedae.util.ModTooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemChaosPickaxe extends PickaxeItem {

    public ItemChaosPickaxe(Properties props) {
        super(ModToolTier.CHAOS_TIER, -50, 0f, props
                .stacksTo(1)
                .fireResistant()
                .rarity(ModRarity.CHAOS));
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 0;
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack pStack, @NotNull BlockState pState) {
        return Math.max(super.getDestroySpeed(pStack, pState), 9999.0f);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EquipmentSlot.MAINHAND) {
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getTier().getAttackDamageBonus(), AttributeModifier.Operation.ADDITION));
            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getTier().getSpeed(), AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isCrouching()) {
            if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {
                clearEnchantments(stack);
                stack.enchant(Enchantments.BLOCK_FORTUNE, 20);
                if (!level.isClientSide && player instanceof ServerPlayer sPlayer)
                    sPlayer.sendSystemMessage(Component.translatable(ModTooltip.SILK_TOUCH), true);
            } else {
                clearEnchantments(stack);
                stack.enchant(Enchantments.SILK_TOUCH, 1);
                if (!level.isClientSide && player instanceof ServerPlayer sPlayer)
                    sPlayer.sendSystemMessage(Component.translatable(ModTooltip.FORTUNE), true);
            }
            player.swing(hand);
            return InteractionResultHolder.success(stack);
        }
        return super.use(level, player, hand);
    }

    public void clearEnchantments(ItemStack stack) {
        if (stack.getOrCreateTag().contains("Enchantments", Tag.TAG_LIST)) {
            stack.getOrCreateTag().remove("Enchantments");
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advancedTooltip) {
        tooltip.add(Component.translatable(ModTooltip.INDESTRUCTIBLE).withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, level, tooltip, advancedTooltip);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity victim, @NotNull LivingEntity entity) {
        if (!(victim instanceof Player)) {
            int i = 10;
            victim.setDeltaMovement(-Mth.sin(entity.yBodyRot * (float) Math.PI / 180.0f) * i * 0.5f, 2.0d, Mth.cos(entity.yBodyRot * (float) Math.PI / 180.f) * i * 0.5f);
        }
        return true;
    }
}
