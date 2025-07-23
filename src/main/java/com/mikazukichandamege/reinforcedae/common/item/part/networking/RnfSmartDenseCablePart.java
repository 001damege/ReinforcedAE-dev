package com.mikazukichandamege.reinforcedae.common.item.part.networking;

import appeng.api.networking.IGridNodeListener;
import appeng.api.util.AECableType;
import appeng.items.parts.ColoredPartItem;
import appeng.parts.networking.IUsedChannelProvider;

public class RnfSmartDenseCablePart extends RnfDenseCablePart implements IUsedChannelProvider {
    public RnfSmartDenseCablePart(ColoredPartItem<?> partItem) {
        super(partItem);
    }

    @Override
    public AECableType getCableConnectionType() {
        return AECableType.DENSE_COVERED;
    }

    @Override
    protected void onMainNodeStateChanged(IGridNodeListener.State reason) {
        if (reason != IGridNodeListener.State.GRID_BOOT) {
            this.getHost().markForUpdate();
        }
    }
}
