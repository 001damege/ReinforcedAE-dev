package com.mikazukichandamege.reinforcedae.common.item.kit;

import appeng.blockentity.AEBaseBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ItemKitBase extends Item {
    public ItemKitBase(Properties pProperties) {
        super(pProperties);
    }

    protected void replaceBlockEntity(Level level, BlockPos pos, BlockEntity oldTile, BlockEntity newTile, BlockState newBlock) {
        CompoundTag nbt = oldTile.serializeNBT();
        level.removeBlockEntity(pos);
        level.removeBlock(pos, false);
        level.setBlock(pos, newBlock, 3);
        level.setBlockEntity(newTile);
        newTile.deserializeNBT(nbt);
        if (newTile instanceof AEBaseBlockEntity baseBlockEntity) {
            baseBlockEntity.markForUpdate();
        } else {
            newTile.setChanged();
        }
    }
}
