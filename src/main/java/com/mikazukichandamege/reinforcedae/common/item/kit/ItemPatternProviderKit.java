package com.mikazukichandamege.reinforcedae.common.item.kit;

import appeng.blockentity.crafting.PatternProviderBlockEntity;
import appeng.blockentity.networking.CableBusBlockEntity;
import appeng.parts.AEBasePart;
import appeng.parts.crafting.PatternProviderPart;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;
import com.mikazukichandamege.reinforcedae.registry.RAEItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class ItemPatternProviderKit extends ItemKitBase {
    public ItemPatternProviderKit(Properties properties) {
        super(properties);
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
            if (blockEntityClass == PatternProviderBlockEntity.class) {
                var originState = level.getBlockState(pos);
                var isSmall = blockEntityClass == PatternProviderBlockEntity.class;
                var state = isSmall ? RAEBlock.PATTERN_PROVIDER.block().getStateForPlacement(ctx) : null;
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
                BlockEntityType<?> blockEntityType = isSmall ? RAEBlock.PATTERN_PROVIDER.block().getBlockEntityType() : null;
                BlockEntity blockEntity = blockEntityType.create(pos, state);
                replaceBlockEntity(level, pos, entity, blockEntity, state);
                context.getItemInHand().shrink(1);
                return InteractionResult.CONSUME;
            } else if (entity instanceof CableBusBlockEntity cable) {
                Vec3 hitVec = context.getClickLocation();
                Vec3 hitInBlock = new Vec3(hitVec.x - pos.getX(), hitVec.y - pos.getY(), hitVec.z - pos.getZ());
                var part = cable.getCableBus().selectPartLocal(hitInBlock).part;
                if (part instanceof AEBasePart basePart && (part.getClass() == PatternProviderPart.class)) {
                    var side = basePart.getSide();
                    var constants = new CompoundTag();
                    var isSmall = part.getClass() == PatternProviderPart.class;
                    var partItem = isSmall ? RAEItem.PATTERN_PROVIDER.asItem() : null;
                    part.writeToNBT(constants);
                    var p = cable.replacePart(partItem, side, context.getPlayer(), null);
                    if (p != null) {
                        p.readFromNBT(constants);
                    }
                } else {
                    return InteractionResult.PASS;
                }
                context.getItemInHand().shrink(1);
                return InteractionResult.sidedSuccess(level.isClientSide());
            }
        }
        return InteractionResult.PASS;
    }
}
