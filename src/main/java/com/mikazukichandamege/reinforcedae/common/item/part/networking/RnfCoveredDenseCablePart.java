package com.mikazukichandamege.reinforcedae.common.item.part.networking;

import appeng.api.util.AECableType;
import appeng.items.parts.ColoredPartItem;

public class RnfCoveredDenseCablePart extends RnfDenseCablePart {
    public RnfCoveredDenseCablePart(ColoredPartItem<?> partItem) {
        super(partItem);
    }

    @Override
    public AECableType getCableConnectionType() {
        return AECableType.DENSE_COVERED;
    }
}
