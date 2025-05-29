package com.mikazukichandamege.reinforcedae.item.tool;

import appeng.core.localization.GuiText;
import appeng.hooks.IntrinsicEnchantItem;
import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.server.command.TextComponentHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemChaosSword extends SwordItem implements IntrinsicEnchantItem {

    public ItemChaosSword(Properties properties) {
        super(ModToolTier.CHAOS_TIER, Integer.MAX_VALUE, 5.0f, properties.rarity(ModRarity.CHAOS).fireResistant().stacksTo(1));
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
        pTooltipComponents.add(GuiText.IntrinsicEnchant.text(Enchantments.SHARPNESS.getFullname(10)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public int getIntrinsicEnchantLevel(ItemStack itemStack, Enchantment enchantment) {
        return enchantment == Enchantments.SHARPNESS ? 10 : 0;
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }
}
