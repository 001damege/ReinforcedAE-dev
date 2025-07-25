package com.mikazukichandamege.reinforcedae.mixin;

import appeng.blockentity.crafting.CraftingBlockEntity;
import appeng.me.cluster.implementations.CraftingCPUCluster;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = CraftingCPUCluster.class, remap = false)
public class MixinCraftingCPUCluster {

    @Redirect(method = "addBlockEntity(Lappeng/blockentity/crafting/CraftingBlockEntity;)V", at = @At(ordinal = 1, value = "INVOKE", target = "Lappeng/blockentity/crafting/CraftingBlockEntity;getAcceleratorThreads()I"))
    public int getThreads(CraftingBlockEntity cbe) {
        return 1;
    }
}
