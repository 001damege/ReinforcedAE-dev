package com.mikazukichandamege.reinforcedae.item.tool;

import appeng.core.localization.GuiText;
import appeng.hooks.IntrinsicEnchantItem;
import com.mikazukichandamege.reinforcedae.util.ModRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.server.command.TextComponentHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemChaosBow extends BowItem implements IntrinsicEnchantItem {

    public ItemChaosBow(Properties properties) {
        super(properties.rarity(ModRarity.CHAOS).stacksTo(1).fireResistant());
    }

    @Override
    public boolean isFoil(@NotNull ItemStack pStack) {
        return true;
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getIntrinsicEnchantLevel(ItemStack itemStack, Enchantment enchantment) {
        return enchantment == Enchantments.INFINITY_ARROWS ? 10 : 0;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(TextComponentHelper.createComponentTranslation(null, "indestructible", new Object()).withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(GuiText.IntrinsicEnchant.text(Enchantments.INFINITY_ARROWS.getFullname(10)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
