package com.mikazukichandamege.reinforcedae.common.block;

import appeng.block.AEBaseBlockItem;
import net.minecraft.world.level.block.Block;

public class RnfBlockItem extends AEBaseBlockItem {
    public RnfBlockItem(Block id, Properties props) {
        super(id, props.fireResistant());
    }
}
