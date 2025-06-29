package com.mikazukichandamege.reinforcedae.common.item.tool;

import appeng.api.upgrades.IUpgradeableItem;
import appeng.api.upgrades.Upgrades;
import appeng.items.tools.powered.powersink.AEBasePoweredItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;

@SuppressWarnings("NonExtendableApiUsage")
public class QuantumTool extends AEBasePoweredItem implements IUpgradeableItem {
    private static final Set<ToolAction> ALL = Set.of(ToolActions.AXE_DIG, ToolActions.HOE_DIG, ToolActions.SHOVEL_DIG, ToolActions.PICKAXE_DIG, ToolActions.SWORD_DIG);

    public QuantumTool(DoubleSupplier powerCapacity, Properties props) {
        super(powerCapacity, props.stacksTo(1).rarity(Rarity.EPIC).fireResistant().setNoRepair());
    }

    @Override
    public double getChargeRate(ItemStack stack) {
        return 800d + 800d * Upgrades.getEnergyCardMultiplier(getUpgrades(stack));
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState state) {
        return true;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        if (ALL.contains(toolAction)) {
            return true;
        }
        return super.canPerformAction(stack, toolAction);
    }

    @Override
    public int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
        return 0;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return Float.MAX_VALUE;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity attack) {
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attack) {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return true;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (entity instanceof Player player) {
            return 1200;
        }
        return 0;
    }


}
