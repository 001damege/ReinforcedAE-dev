package com.mikazukichandamege.reinforcedae.common.item.part.networking;

import appeng.api.networking.GridFlags;
import appeng.api.networking.GridHelper;
import appeng.api.parts.BusSupport;
import appeng.api.parts.IPartCollisionHelper;
import appeng.items.parts.ColoredPartItem;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public abstract class RnfDenseCablePart extends RnfCablePart {
    public RnfDenseCablePart(ColoredPartItem<?> partItem) {
        super(partItem);
        this.getMainNode().setFlags(GridFlags.DENSE_CAPACITY);
    }

    @Override
    public BusSupport supportsBuses() {
        return BusSupport.DENSE_CABLE;
    }

    @Override
    public void getBoxes(IPartCollisionHelper bch, Predicate<@Nullable Direction> filter) {
        updateConnections();

        final boolean noLadder = !bch.isBBCollision();
        final double min = noLadder ? 3.0 : 4.9;
        final double max = noLadder ? 13.0 : 11.1;

        if (filter.test(null)) {
            bch.addBox(min, min, min, max, max, max);
        }

        for (var of : this.getConnections()) {
            if (!filter.test(of)) {
                continue;
            }

            if (this.isDense(of)) {
                addConnectionBox(bch, of, min, max, 0.0);
            } else {
                addConnectionBox(bch, of, 5.0, 11.0, 0.0);
            }
        }
    }

    private boolean isDense(Direction of) {
        var adjacentPos = getBlockEntity().getBlockPos().relative(of);

        if (!getLevel().hasChunkAt(adjacentPos)) {
            return false;
        }

        var adjacentHost = GridHelper.getNodeHost(getBlockEntity().getLevel(), adjacentPos);
        if (adjacentHost != null) {
            var t = adjacentHost.getCableConnectionType(of.getOpposite());
            return t.isDense();
        }

        return false;
    }
}
