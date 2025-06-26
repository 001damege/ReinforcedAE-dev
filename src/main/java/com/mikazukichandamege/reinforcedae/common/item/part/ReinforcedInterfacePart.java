package com.mikazukichandamege.reinforcedae.common.item.part;

import appeng.api.parts.IPartItem;
import appeng.api.parts.IPartModel;
import appeng.core.AppEng;
import appeng.helpers.InterfaceLogic;
import appeng.items.parts.PartModels;
import appeng.menu.ISubMenu;
import appeng.menu.MenuOpener;
import appeng.menu.locator.MenuLocator;
import appeng.parts.PartModel;
import appeng.parts.misc.InterfacePart;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.ModItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ReinforcedInterfacePart extends InterfacePart {
    public ReinforcedInterfacePart(IPartItem<?> partItem) {
        super(partItem);
    }

    private static final ResourceLocation MODEL_BASE = ReinforcedAE.makeId("part/reinforced_interface");
    @PartModels
    public static final PartModel MODELS_OFF = new PartModel(MODEL_BASE, AppEng.makeId("part/interface_off"));
    @PartModels
    public static final PartModel MODELS_ON = new PartModel(MODEL_BASE, AppEng.makeId("part/interface_on"));
    @PartModels
    public static final PartModel MODELS_HAS_CHANNEL = new PartModel(MODEL_BASE, AppEng.makeId("part/interface_has_channel"));

    @Override
    protected InterfaceLogic createLogic() {
        return new InterfaceLogic(getMainNode(), this, getPartItem().asItem(), 81);
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
    public IPartModel getStaticModels() {
        if (isActive() && isPowered()) {
            return MODELS_HAS_CHANNEL;
        } else if (isPowered()) {
            return MODELS_ON;
        } else {
            return MODELS_OFF;
        }
    }

    @Override
    public ItemStack getMainMenuIcon() {
        return ModItem.REINFORCED_INTERFACE.stack();
    }
}
