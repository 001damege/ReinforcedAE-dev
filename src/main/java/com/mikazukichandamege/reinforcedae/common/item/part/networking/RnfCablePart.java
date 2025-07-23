package com.mikazukichandamege.reinforcedae.common.item.part.networking;

import appeng.api.implementations.parts.ICablePart;
import appeng.api.networking.pathing.ChannelMode;
import appeng.items.parts.ColoredPartItem;
import appeng.parts.networking.CablePart;
import io.netty.buffer.Unpooled;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.FriendlyByteBuf;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public abstract class RnfCablePart extends CablePart implements ICablePart {
    private Set<Direction> connections = Collections.emptySet();
    private final int[] channelsOnSide = { 0, 0, 0, 0, 0, 0 };

    public RnfCablePart(ColoredPartItem<?> partItem) {
        super(partItem);
    }

    @Override
    protected void updateConnections() {
        if (!isClientSide()) {
            var n = this.getGridNode();
            if (n != null) {
                this.setConnections(n.getConnectedSides());
            } else {
                this.setConnections(Collections.emptySet());
            }
        }
    }

    @Override
    public boolean readFromStream(FriendlyByteBuf data) {
        var changed = super.readFromStream(data);
        int connectedSidesPacked = data.readByte();
        var previousConnections = this.getConnections();
        boolean channelsChanged = false;
        var connections = EnumSet.noneOf(Direction.class);

        for (var d : Direction.values()) {
            boolean canOnSide = (connectedSidesPacked & (1 << d.ordinal())) != 0;
            if (canOnSide) {
                connections.add(d);
            }

            int ch = 0;

            if (canOnSide || this.getHost().getPart(d) != null) {
                ch = data.readByte() & 0xFF;
            }

            if (ch != this.channelsOnSide[d.ordinal()]) {
                channelsChanged = true;
                this.setChannelsOnSide(d.ordinal(), ch);
            }
        }
        this.setConnections(connections);

        return changed || !previousConnections.equals(this.getConnections()) || channelsChanged;
    }

    @Override
    public void writeToStream(FriendlyByteBuf data) {
        super.writeToStream(data);

        boolean[] writeChannels = new boolean[Direction.values().length];
        byte[] channelsPerSide = new byte[Direction.values().length];

        for (var thisSide : Direction.values()) {
            var part = this.getHost().getPart(thisSide);
            if (part != null) {
                int channels = 0;
                if (part.getGridNode() != null) {
                    for (var gc : part.getGridNode().getConnections()) {
                        channels = Math.max(channels, gc.getUsedChannels());
                    }
                }
                channelsPerSide[thisSide.ordinal()] = getVisualChannels(channels);
                writeChannels[thisSide.ordinal()] = true;
            }
        }

        int connectedSidesPacked = 0;
        var n = getGridNode();
        if (n != null) {
            for (var entry : n.getInWorldConnections().entrySet()) {
                var side = entry.getKey().ordinal();
                var connection = entry.getValue();
                channelsPerSide[side] = getVisualChannels(connection.getUsedChannels());
                writeChannels[side] = true;
                connectedSidesPacked |= 1 << side;
            }
        }
        data.writeByte((byte) connectedSidesPacked);

        for (int i = 0; i < writeChannels.length; i++) {
            if (writeChannels[i]) {
                data.writeByte(channelsPerSide[i]);
            }
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void writeVisualStateToNBT(CompoundTag data) {
        super.writeVisualStateToNBT(data);

        if (!isClientSide()) {
            updateConnections();
            var packet = new FriendlyByteBuf(Unpooled.buffer());
            writeToStream(packet);
            readFromStream(packet);
        }

        for (var side : Direction.values()) {
            if (connections.contains(side)) {
                var sideName = "channels" + StringUtils.capitalize(side.getSerializedName());
                data.putInt(sideName, channelsOnSide[side.ordinal()]);
            }
        }

        var connectionsTag = new ListTag();
        for (var connection : connections) {
            connectionsTag.add(StringTag.valueOf(connection.getSerializedName()));
        }
        data.put("connections", connectionsTag);
    }

    private byte getVisualChannels(int channels) {
        var node = getGridNode();
        if (node == null) {
            return 0;
        }

        byte visualMaxChannels = switch (getCableConnectionType()) {
            case NONE -> 0;
            case GLASS, SMART, COVERED -> 16;
            case DENSE_COVERED, DENSE_SMART -> 64;
        };

        if (node.getGrid().getPathingService().getChannelMode() == ChannelMode.INFINITE) {
            return channels <= 0 ? 0 : visualMaxChannels;
        }

        int gridMaxChannels = node.getMaxChannels();
        if (visualMaxChannels == 0 || gridMaxChannels == 0) {
            return 0;
        }

        var result = (byte) (Math.min(visualMaxChannels, channels * visualMaxChannels / gridMaxChannels));
        if (result == 0 && channels > 0) {
            return 1;
        } else {
            return result;
        }
    }

    void setChannelsOnSide(int i, int channels) {
        this.channelsOnSide[i] = channels;
    }

    Set<Direction> getConnections() {
        return this.connections;
    }

    void setConnections(Set<Direction> connections) {
        this.connections = connections;
    }
}
