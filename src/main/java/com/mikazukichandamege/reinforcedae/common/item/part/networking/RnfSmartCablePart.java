package com.mikazukichandamege.reinforcedae.common.item.part.networking;

import appeng.api.networking.IGridNodeListener;
import appeng.api.parts.IPartCollisionHelper;
import appeng.api.util.AECableType;
import appeng.items.parts.ColoredPartItem;
import appeng.parts.networking.IUsedChannelProvider;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class RnfSmartCablePart extends RnfCablePart implements IUsedChannelProvider {
    public RnfSmartCablePart(ColoredPartItem<?> partItem) {
        super(partItem);
    }

    @Override
    protected void onMainNodeStateChanged(IGridNodeListener.State reason) {
        super.onMainNodeStateChanged(reason);
        if (reason != IGridNodeListener.State.GRID_BOOT) {
            this.getHost().markForUpdate();
        }
    }

    @Override
    public void getBoxes(IPartCollisionHelper bch, Predicate<@Nullable Direction> filter) {
        updateConnections();
        addNonDenseBoxes(bch, filter, 5.0, 11.0);
    }

    @Override
    public AECableType getCableConnectionType() {
        return AECableType.SMART;
    }
}
