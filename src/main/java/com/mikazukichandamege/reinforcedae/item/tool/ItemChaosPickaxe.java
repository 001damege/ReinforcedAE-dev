package com.mikazukichandamege.reinforcedae.item.tool;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.server.command.TextComponentHelper;
import org.jetbrains.annotations.NotNull;

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
        if (pState.is(BlockTags.MINEABLE_WITH_PICKAXE)) return 8888.0f;
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
                    sPlayer.sendSystemMessage(TextComponentHelper.createComponentTranslation(null, "Fortune"), true);
            } else {
                clearEnchantments(stack);
                stack.enchant(Enchantments.SILK_TOUCH, 1);
                if (!level.isClientSide && player instanceof ServerPlayer sPlayer)
                    sPlayer.sendSystemMessage(TextComponentHelper.createComponentTranslation(null, "Silk Touch"), true);
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
}
