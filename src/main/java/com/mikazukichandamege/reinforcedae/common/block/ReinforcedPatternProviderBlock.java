package com.mikazukichandamege.reinforcedae.common.block;

import appeng.api.networking.IManagedGridNode;
import appeng.block.AEBaseEntityBlock;
import appeng.block.crafting.PatternProviderBlock;
import appeng.block.crafting.PushDirection;
import appeng.core.definitions.AEItems;
import appeng.helpers.patternprovider.PatternProviderLogic;
import appeng.helpers.patternprovider.PatternProviderLogicHost;
import appeng.menu.locator.MenuLocators;
import appeng.util.InteractionUtil;
import appeng.util.Platform;
import appeng.util.inv.AppEngInternalInventory;
import appeng.util.inv.filter.AEItemDefinitionFilter;
import com.mikazukichandamege.reinforcedae.common.blockentity.ReinforcedPatternProviderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class ReinforcedPatternProviderBlock extends AEBaseEntityBlock<ReinforcedPatternProviderBlockEntity> {
    public static final EnumProperty<PushDirection> FACING = EnumProperty.create("push_direction", PushDirection.class);

    public ReinforcedPatternProviderBlock() {
        super(metalProps());
        registerDefaultState(defaultBlockState().setValue(FACING, PushDirection.ALL));
    }

    public static PatternProviderLogic createLogic(IManagedGridNode mainNode, PatternProviderLogicHost host) {
        var logic = new PatternProviderLogic(mainNode, host, 81);
        var filter = new AEItemDefinitionFilter(AEItems.PROCESSING_PATTERN);
        ((AppEngInternalInventory) logic.getPatternInv()).setFilter(filter);
        return logic;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public InteractionResult onActivated(Level level, BlockPos pos, Player player, InteractionHand hand, @Nullable ItemStack heldItem, BlockHitResult hit) {
        if (InteractionUtil.isInAlternateUseMode(player)) {
            return InteractionResult.PASS;
        }
        if (heldItem != null && InteractionUtil.canWrenchRotate(heldItem)) {
            setSide(level, pos, hit.getDirection());
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        var blockEntity = getBlockEntity(level, pos);
        if (blockEntity != null) {
            if (!level.isClientSide()) {
                blockEntity.openMenu(player, MenuLocators.forBlockEntity(blockEntity));
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.PASS;
    }

    private void setSide(Level level, BlockPos pos, Direction facing) {
        var currentState = level.getBlockState(pos);
        var pushSide = currentState.getValue(FACING).getDirection();

        PushDirection newPushDirection;

        if (pushSide == facing.getOpposite()) {
            newPushDirection = PushDirection.fromDirection(facing);
        } else if (pushSide == facing) {
            newPushDirection = PushDirection.ALL;
        } else if (pushSide == null) {
            newPushDirection = PushDirection.fromDirection(facing.getOpposite());
        } else {
            newPushDirection = PushDirection.fromDirection(Platform.rotateAround(pushSide, facing));
        }

        level.setBlockAndUpdate(pos, currentState.setValue(FACING, newPushDirection));
    }
}
