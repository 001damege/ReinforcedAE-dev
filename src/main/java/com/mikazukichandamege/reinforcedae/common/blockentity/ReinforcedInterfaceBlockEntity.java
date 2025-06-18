package com.mikazukichandamege.reinforcedae.common.blockentity;

import appeng.blockentity.misc.InterfaceBlockEntity;
import appeng.helpers.InterfaceLogic;
import appeng.menu.ISubMenu;
import appeng.menu.MenuOpener;
import appeng.menu.locator.MenuLocator;
import com.mikazukichandamege.reinforcedae.registry.ModBlock;
import com.mikazukichandamege.reinforcedae.registry.ModContainer;
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
        MenuOpener.open(ModContainer.REINFORCED_INTERFACE, player, locator);
    }

    @Override
    public void returnToMainMenu(Player player, ISubMenu subMenu) {
        MenuOpener.returnTo(ModContainer.REINFORCED_INTERFACE, player, subMenu.getLocator());
    }

    @Override
    public ItemStack getMainMenuIcon() {
        return ModBlock.REINFORCED_INTERFACE.stack();
    }
}
