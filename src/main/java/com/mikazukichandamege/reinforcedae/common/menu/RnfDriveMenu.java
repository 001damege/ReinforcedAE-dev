package com.mikazukichandamege.reinforcedae.common.menu;

import appeng.menu.AEBaseMenu;
import appeng.menu.SlotSemantics;
import appeng.menu.implementations.MenuTypeBuilder;
import appeng.menu.slot.RestrictedInputSlot;
import com.mikazukichandamege.reinforcedae.common.blockentity.RnfDriveBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public class RnfDriveMenu extends AEBaseMenu {
    public static final MenuType<RnfDriveMenu> TYPE = MenuTypeBuilder.create(RnfDriveMenu::new, RnfDriveBlockEntity.class).build("rnf_drive");

    public RnfDriveMenu(int id, Inventory ip, RnfDriveBlockEntity drive) {
        super(TYPE, id, ip, drive);

        for (int i = 0; i < 10; i++) {
            this.addSlot(new RestrictedInputSlot(RestrictedInputSlot.PlacableItemType.STORAGE_CELLS, drive.getInternalInventory(), i), SlotSemantics.STORAGE_CELL);
        }

        this.createPlayerInventorySlots(ip);
    }
}
