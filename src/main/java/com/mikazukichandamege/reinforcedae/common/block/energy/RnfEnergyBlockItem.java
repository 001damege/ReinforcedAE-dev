package com.mikazukichandamege.reinforcedae.common.block.energy;

import appeng.block.networking.EnergyCellBlockItem;
import net.minecraft.world.level.block.Block;

public class RnfEnergyBlockItem extends EnergyCellBlockItem {
    public RnfEnergyBlockItem(Block block, Properties props) {
        super(block, props.fireResistant());
    }
}
