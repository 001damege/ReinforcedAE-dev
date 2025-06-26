package com.mikazukichandamege.reinforcedae.common.blockentity;

import appeng.blockentity.misc.InterfaceBlockEntity;
import appeng.helpers.InterfaceLogic;
import appeng.menu.ISubMenu;
import appeng.menu.locator.MenuLocator;
import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ReinforcedInterfaceBlockEntity extends InterfaceBlockEntity {
    public ReinforcedInterfaceBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState) {
        super(blockEntityType, pos, blockState);
    }

    @Override
    protected InterfaceLogic createLogic() {
        return new InterfaceLogic(getMainNode(), this, getItemFromBlockEntity().asItem(), 81);
    }

    @Override
    public void openMenu(Player player, MenuLocator locator) {
        super.openMenu(player, locator);
    }

    @Override
    public void returnToMainMenu(Player player, ISubMenu subMenu) {
        super.returnToMainMenu(player, subMenu);
    }

    @Override
    public ItemStack getMainMenuIcon() {
        return ModBlock.REINFORCED_INTERFACE.stack();
    }
}
