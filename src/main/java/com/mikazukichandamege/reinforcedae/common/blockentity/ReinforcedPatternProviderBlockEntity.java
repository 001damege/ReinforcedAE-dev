package com.mikazukichandamege.reinforcedae.common.blockentity;

import appeng.api.stacks.AEItemKey;
import appeng.blockentity.crafting.PatternProviderBlockEntity;
import appeng.helpers.patternprovider.PatternProviderLogic;
import appeng.menu.ISubMenu;
import appeng.menu.MenuOpener;
import appeng.menu.locator.MenuLocator;
import com.mikazukichandamege.reinforcedae.common.block.ReinforcedPatternProviderBlock;
import com.mikazukichandamege.reinforcedae.registry.ModContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ReinforcedPatternProviderBlockEntity extends PatternProviderBlockEntity {
    public ReinforcedPatternProviderBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState) {
        super(blockEntityType, pos, blockState);
    }

    @Override
    protected PatternProviderLogic createLogic() {
        return ReinforcedPatternProviderBlock.createLogic(this.getMainNode(), this);
    }

    @Override
    public void openMenu(Player player, MenuLocator locator) {
        MenuOpener.open(ModContainer.REINFORCED_PATTERN_PROVIDER, player, locator);
    }

    @Override
    public void returnToMainMenu(Player player, ISubMenu subMenu) {
        MenuOpener.open(ModContainer.REINFORCED_PATTERN_PROVIDER, player, subMenu.getLocator());
    }

    @Override
    public AEItemKey getTerminalIcon() {
        return super.getTerminalIcon();
    }

    @Override
    public ItemStack getMainMenuIcon() {
        return super.getMainMenuIcon();
    }
}
