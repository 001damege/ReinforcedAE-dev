package com.mikazukichandamege.reinforcedae.common.integration.appmek;

import appeng.api.stacks.AEKey;
import com.mikazukichandamege.reinforcedae.common.item.cell.ExDISK;
import me.ramidzkh.mekae2.ae2.MekanismKey;
import me.ramidzkh.mekae2.ae2.MekanismKeyType;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ExChemicalDISK extends ExDISK {

    public ExChemicalDISK(ItemLike coreItem, int kiloBytes, double idleDrain) {
        super(coreItem, kiloBytes, idleDrain, MekanismKeyType.TYPE);
    }

    @Override
    public boolean isBlackListed(ItemStack cellItem, AEKey requestedAddition) {
        if (requestedAddition instanceof MekanismKey key) {
            return !ChemicalAttributeValidator.DEFAULT.process(key.getStack());
        }
        return false;
    }
}
