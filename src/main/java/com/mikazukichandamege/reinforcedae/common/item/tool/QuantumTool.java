package com.mikazukichandamege.reinforcedae.common.item.tool;

import appeng.api.config.FuzzyMode;
import appeng.api.storage.cells.ICellWorkbenchItem;
import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.UpgradeInventories;
import appeng.api.upgrades.Upgrades;
import appeng.items.tools.powered.powersink.AEBasePoweredItem;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.DoubleSupplier;

public class QuantumTool extends AEBasePoweredItem implements ICellWorkbenchItem {
    private static final Set<ToolAction> ACTION = Sets.newHashSet(ToolActions.AXE_DIG, ToolActions.PICKAXE_DIG, ToolActions.SHOVEL_DIG, ToolActions.HOE_DIG, ToolActions.SWORD_DIG);

    public QuantumTool(DoubleSupplier powerCapacity, Properties props) {
        super(powerCapacity, props.rarity(Rarity.EPIC).stacksTo(1).fireResistant());
    }

    @Override
    public double getChargeRate(ItemStack stack) {
        return 800d + 800d + Upgrades.getEnergyCardMultiplier(getUpgrades(stack));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> lines, TooltipFlag advancedTooltips) {
        super.appendHoverText(stack, level, lines, advancedTooltips);
    }

    @Override
    public FuzzyMode getFuzzyMode(ItemStack stack) {
        return null;
    }

    @Override
    public void setFuzzyMode(ItemStack stack, FuzzyMode fz) {}

    @Override
    public IUpgradeInventory getUpgrades(ItemStack stack) {
        return UpgradeInventories.forItem(stack, 8, this::onUpgradesCharged);
    }

    private void onUpgradesCharged(ItemStack stack, IUpgradeInventory inv) {
        setAEMaxPowerMultiplier(stack, 1 + Upgrades.getEnergyCardMultiplier(inv));
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return true;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction action) {
        return ACTION.contains(action);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 100;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public @Nullable EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.MAINHAND;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return true;
    }
}
