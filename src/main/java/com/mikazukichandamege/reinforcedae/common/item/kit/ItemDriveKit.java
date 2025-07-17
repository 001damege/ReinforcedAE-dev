package com.mikazukichandamege.reinforcedae.common.item.kit;

import appeng.blockentity.storage.DriveBlockEntity;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class ItemDriveKit extends ItemKitBase {
    public ItemDriveKit(Properties properties) {
        super(properties.fireResistant());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Nonnull
    @Override
    public InteractionResult useOn(@NotNull UseOnContext context) {
        var pos = context.getClickedPos();
        var level = context.getLevel();
        var entity = level.getBlockEntity(pos);
        if (entity != null) {
            var ctx = new BlockPlaceContext(context);
            var blockEntityClass = entity.getClass();
            if (blockEntityClass == DriveBlockEntity.class) {
                var originState = level.getBlockState(pos);
                var isSmall = blockEntityClass == DriveBlockEntity.class;
                var state = isSmall ? RAEBlock.DRIVE.block().getStateForPlacement(ctx) : null;
                if (state == null) {
                    return InteractionResult.PASS;
                }
                for (var sp : originState.getValues().entrySet()) {
                    var pt = sp.getKey();
                    var va = sp.getValue();
                    try {
                        if (state.hasProperty(pt)) {
                            state = state.<Comparable, Comparable> setValue((Property) pt, va);
                        }
                    } catch (Exception ignored) {

                    }
                }
                BlockEntityType<?> blockEntityType = isSmall ? RAEBlock.DRIVE.block().getBlockEntityType() : null;
                BlockEntity blockEntity = blockEntityType.create(pos, state);
                replaceBlockEntity(level, pos, entity, blockEntity, state);
                context.getItemInHand().shrink(1);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }
}
