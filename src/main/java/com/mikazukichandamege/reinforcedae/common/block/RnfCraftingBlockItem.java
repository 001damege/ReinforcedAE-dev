package com.mikazukichandamege.reinforcedae.common.block;

import appeng.block.crafting.CraftingBlockItem;
import appeng.core.AEConfig;
import appeng.util.InteractionUtil;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;
import com.mikazukichandamege.reinforcedae.registry.RAETranslation;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class RnfCraftingBlockItem extends CraftingBlockItem {
    public RnfCraftingBlockItem(Block id, Properties props, Supplier<ItemLike> disassemblyExtra) {
        super(id, props.fireResistant(), disassemblyExtra);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (AEConfig.instance().isDisassemblyCraftingEnabled() && InteractionUtil.isInAlternateUseMode(player)) {
            int itemCount = player.getItemInHand(hand).getCount();
            player.setItemInHand(hand, ItemStack.EMPTY);
            player.getInventory().placeItemBackInInventory(RAEBlock.UNIT.stack(itemCount));
            player.getInventory().placeItemBackInInventory(new ItemStack(disassemblyExtra.get(), itemCount));
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
        }
        return super.use(level, player, hand);
    }

    @Override
    public void addCheckedInformation(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag advancedTooltips) {
        if (this.getBlock().equals(RAEBlock.ACCELERATOR_4x.block())) {
            tooltip.add(RAETranslation.Threads_4x.text().withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
        } else if (this.getBlock().equals(RAEBlock.ACCELERATOR_16x.block())) {
            tooltip.add(RAETranslation.Threads_16x.text().withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
        } else if (this.getBlock().equals(RAEBlock.ACCELERATOR_64x.block())) {
            tooltip.add(RAETranslation.Threads_64x.text().withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
        } else if (this.getBlock().equals(RAEBlock.ACCELERATOR_256x.block())) {
            tooltip.add(RAETranslation.Threads_256x.text().withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
        } else if (this.getBlock().equals(RAEBlock.ACCELERATOR_1024x.block())) {
            tooltip.add(RAETranslation.Threads_1024x.text().withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
        } else if (this.getBlock().equals(RAEBlock.ACCELERATOR_4096x.block())) {
            tooltip.add(RAETranslation.Threads_4096x.text().withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
        } else if (this.getBlock().equals(RAEBlock.ACCELERATOR_16384x.block())) {
            tooltip.add(RAETranslation.Threads_16384x.text().withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
        } else if (this.getBlock().equals(RAEBlock.ACCELERATOR_65536x.block())) {
            tooltip.add(RAETranslation.Threads_65536x.text().withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }
}
