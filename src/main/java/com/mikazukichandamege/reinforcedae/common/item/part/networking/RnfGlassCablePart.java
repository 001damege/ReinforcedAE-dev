package com.mikazukichandamege.reinforcedae.common.item.part.networking;

import appeng.api.parts.IPartCollisionHelper;
import appeng.api.util.AECableType;
import appeng.items.parts.ColoredPartItem;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class RnfGlassCablePart extends RnfCablePart {
    public RnfGlassCablePart(ColoredPartItem<?> partItem) {
        super(partItem);
    }

    @Override
    public void getBoxes(IPartCollisionHelper bch, Predicate<@Nullable Direction> filter) {
        updateConnections();
        addNonDenseBoxes(bch, filter, 6.0, 10.0);
    }

    @Override
    public AECableType getCableConnectionType() {
        return AECableType.GLASS;
    }
}
