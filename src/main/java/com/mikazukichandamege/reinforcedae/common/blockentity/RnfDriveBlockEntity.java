package com.mikazukichandamege.reinforcedae.common.blockentity;

import appeng.api.implementations.blockentities.IChestOrDrive;
import appeng.api.inventories.InternalInventory;
import appeng.api.networking.GridFlags;
import appeng.api.networking.IGridNodeListener;
import appeng.api.orientation.BlockOrientation;
import appeng.api.orientation.RelativeSide;
import appeng.api.storage.IStorageMounts;
import appeng.api.storage.IStorageProvider;
import appeng.api.storage.MEStorage;
import appeng.api.storage.StorageCells;
import appeng.api.storage.cells.CellState;
import appeng.api.storage.cells.StorageCell;
import appeng.api.util.AECableType;
import appeng.blockentity.grid.AENetworkInvBlockEntity;
import appeng.blockentity.inventory.AppEngCellInventory;
import appeng.client.render.model.DriveModelData;
import appeng.helpers.IPriorityHost;
import appeng.me.storage.DriveWatcher;
import appeng.menu.ISubMenu;
import appeng.menu.MenuOpener;
import appeng.menu.implementations.DriveMenu;
import appeng.menu.locator.MenuLocator;
import appeng.menu.locator.MenuLocators;
import appeng.util.inv.filter.IAEItemFilter;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.registry.RAEBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class RnfDriveBlockEntity extends AENetworkInvBlockEntity implements IChestOrDrive, IPriorityHost, IStorageProvider {
    private final AppEngCellInventory inv = new AppEngCellInventory(this, getCellCount());
    private final DriveWatcher[] invBySlot = new DriveWatcher[getCellCount()];
    private boolean isCached = false;
    private int priority = 0;
    private boolean wasOnline = false;
    private final Item[] clientSideCellItems = new Item[getCellCount()];
    private final CellState[] clientSideCellState = new CellState[getCellCount()];
    private boolean clientSideOnline;

    public RnfDriveBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState) {
        super(blockEntityType, pos, blockState);
        getMainNode()
                .addService(IStorageProvider.class, this)
                .setFlags(GridFlags.REQUIRE_CHANNEL);
        inv.setFilter(new CellValidInventoryFilter());
        Arrays.fill(clientSideCellState, CellState.ABSENT);
    }

    @Override
    public Set<Direction> getGridConnectableSides(BlockOrientation orientation) {
        return EnumSet.complementOf(EnumSet.of(orientation.getSide(RelativeSide.FRONT)));
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void writeToStream(FriendlyByteBuf data) {
        super.writeToStream(data);
        updateClientSideState();

        long packedState = 0;
        for (int i = 0; i < getCellCount(); i++) {
            packedState |= (long) clientSideCellState[i].ordinal() << (i * 3);
        }

        if (clientSideOnline) {
            packedState |= 1 << 61;
        }
        data.writeLong(packedState);

        for (int i = 0; i < getCellCount(); i++) {
            data.writeVarLong(BuiltInRegistries.ITEM.getId(getCellItem(i)));
        }
    }

    @Override
    public void onMainNodeStateChanged(IGridNodeListener.State reason) {
        var currentOnline = getMainNode().isOnline();
        if (this.wasOnline != currentOnline) {
            this.wasOnline = currentOnline;
            IStorageProvider.requestUpdate(getMainNode());
            updateVisualStateIfNeeded();
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void saveVisualState(CompoundTag data) {
        super.saveVisualState(data);

        data.putBoolean("online", isPowered());

        for (int i = 0; i < getCellCount(); i++) {
            var cellItem = getCellItem(i);
            if (cellItem != null) {
                var cellDate = new CompoundTag();
                cellDate.putString("id", BuiltInRegistries.ITEM.getKey(cellItem).toString());

                var cellState = getCellStatus(i);
                cellDate.putString("state", cellState.name().toLowerCase(Locale.ROOT));
                data.put("cell" + i, cellDate);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    protected boolean readFromStream(FriendlyByteBuf data) {
        var changed = super.readFromStream(data);

        var packedState = data.readInt();
        for (int i = 0; i < getCellCount(); i++) {
            var cellStateOrdinal = (packedState >> (i * 3)) & 0b111;
            var cellState = CellState.values()[cellStateOrdinal];
            if (clientSideCellState[i] != cellState) {
                clientSideCellState[i] = cellState;
                changed = true;
            }
        }

        var online = (packedState & (1 << 31)) != 0;
        if (clientSideOnline != online) {
            clientSideOnline = online;
            changed = true;
        }

        for (int i = 0; i < getCellCount(); i++) {
            var itemId = data.readVarInt();
            Item item = itemId == 0 ? null : BuiltInRegistries.ITEM.byId(itemId);
            if (itemId != 0 && item == Items.AIR) {
                ReinforcedAE.LOGGER.warn("Received unknown item id from server for disk drive %s: %d", this, itemId);
            }
            if (clientSideCellItems[i] != item) {
                clientSideCellItems[i] = item;
                changed = true;
            }
        }

        return changed;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void loadVisualState(CompoundTag data) {
        super.loadVisualState(data);

        clientSideOnline = data.getBoolean("online");

        for (int i = 0; i < getCellCount(); i++) {
            this.clientSideCellItems[i] = null;
            this.clientSideCellState[i] = CellState.ABSENT;

            var tagName = "cell" + i;
            if (data.contains(tagName, Tag.TAG_COMPOUND)) {
                var cellData = data.getCompound(tagName);
                var id = new ResourceLocation(cellData.getString("id"));
                var cellStateName = cellData.getString("state");

                clientSideCellItems[i] = BuiltInRegistries.ITEM.getOptional(id).orElse(null);

                try {
                    clientSideCellState[i] = CellState.valueOf(cellStateName.toUpperCase(Locale.ROOT));
                } catch (IllegalArgumentException e) {
                    ReinforcedAE.LOGGER.warn("Cannot parse cell state for cell %d: %s", i, cellStateName);
                }
            }
        }
    }

    @Override
    public int getCellCount() {
        return 100;
    }

    @Override
    public CellState getCellStatus(int slot) {
        if (isClientSide()) {
            return this.clientSideCellState[slot];
        }

        var handler = this.invBySlot[slot];
        if (handler == null) {
            return CellState.ABSENT;
        }

        return handler.getStatus();
    }

    @Override
    public boolean isPowered() {
        if (isClientSide()) {
            return clientSideOnline;
        }

        return this.getMainNode().isOnline();
    }

    @Override
    public boolean isCellBlinking(int i) {
        return false;
    }

    @Override
    public @Nullable Item getCellItem(int i) {
        if (level == null || level.isClientSide) {
            return clientSideCellItems[i];
        }

        ItemStack stack = inv.getStackInSlot(i);
        if (!stack.isEmpty()) {
            return stack.getItem();
        }

        return null;
    }

    @Override
    public @Nullable MEStorage getCellInventory(int i) {
        return this.invBySlot[i];
    }

    @Override
    public @Nullable StorageCell getOriginalCellInventory(int i) {
        var handler = this.invBySlot[i];
        if (handler != null) {
            return handler.getCell();
        }

        return null;
    }

    @Override
    public void mountInventories(IStorageMounts iStorageMounts) {
        if (this.getMainNode().isOnline()) {
            this.updateState();

            for (var inventory : this.invBySlot) {
                if (inventory != null) {
                    iStorageMounts.mount(inventory, priority);
                }
            }
        }
    }

    @Override
    public void returnToMainMenu(Player player, ISubMenu iSubMenu) {
        MenuOpener.returnTo(DriveMenu.TYPE, player, MenuLocators.forBlockEntity(this));
    }

    @Override
    public ItemStack getMainMenuIcon() {
        return RAEBlock.DRIVE.stack();
    }

    @Override
    public InternalInventory getInternalInventory() {
        return this.inv;
    }

    @Override
    public void onChangeInventory(InternalInventory internalInventory, int i) {
        if (this.isCached) {
            this.isCached = false;
            this.updateState();
        }

        IStorageProvider.requestUpdate(getMainNode());
        this.markForUpdate();
    }

    private void updateState() {
        if (!this.isCached) {
            double power = 2.0;

            for (int i = 0; i < this.inv.size(); i++) {
                power += updateStateForSlot(i);
            }
            this.getMainNode().setIdlePowerUsage(power);
            this.isCached = true;
        }
    }

    private double updateStateForSlot(int slot) {
        this.invBySlot[slot] = null;
        this.inv.setHandler(slot, null);

        var stack = this.inv.getStackInSlot(slot);
        if (!stack.isEmpty()) {
            var cell = StorageCells.getCellInventory(stack, this::onCellContentChanged);

            if (cell != null) {
                this.inv.setHandler(slot, cell);

                var driveWatcher = new DriveWatcher(cell, () -> blinkCell(slot));
                this.invBySlot[slot] = driveWatcher;

                return cell.getIdleDrain();
            }
        }
        return 0;
    }

    private void blinkCell(int slot) {
        this.updateVisualStateIfNeeded();
    }

    private void onCellContentChanged() {
        Objects.requireNonNull(this.level).blockEntityChanged(this.worldPosition);
    }

    @Override
    public void onReady() {
        super.onReady();
        this.updateState();
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public void setPriority(int newValue) {
        this.priority = newValue;
        this.saveChanges();
        this.isCached = false;
        this.updateState();

        IStorageProvider.requestUpdate(getMainNode());
    }

    @Override
    public void saveAdditional(CompoundTag data) {
        super.saveAdditional(data);
        data.putInt("priority", this.priority);
    }

    private void updateVisualStateIfNeeded() {
        if (updateClientSideState()) {
            this.markForUpdate();
        }
    }

    private boolean updateClientSideState() {
        if (isClientSide()) {
            return false;
        }

        updateState();

        var changed = false;
        var online = getMainNode().isOnline();
        if (online != this.clientSideOnline) {
            this.clientSideOnline = online;
            changed = true;
        }

        for (int i = 0; i < this.getCellCount(); i++) {
            var cellItem = getCellItem(i);
            if (cellItem != this.clientSideCellItems[i]) {
                this.clientSideCellItems[i] = cellItem;
                changed = true;
            }

            var cellState = this.getCellStatus(i);
            if (cellState != this.clientSideCellState[i]) {
                this.clientSideCellState[i] = cellState;
                changed = true;
            }
        }

        return changed;
    }

    @Override
    public AECableType getCableConnectionType(Direction dir) {
        return AECableType.SMART;
    }

    private static class CellValidInventoryFilter implements IAEItemFilter {

        @Override
        public boolean allowExtract(InternalInventory inv, int slot, int amount) {
            return true;
        }

        @Override
        public boolean allowInsert(InternalInventory inv, int slot, ItemStack stack) {
            return !stack.isEmpty() && StorageCells.isCellHandled(stack);
        }
    }

    @NotNull
    @Override
    public ModelData getModelData() {
        var cells = new Item[getCellCount()];

        for (int i = 0; i < getCellCount(); i++) {
            cells[i] = getCellItem(i);
        }

        return DriveModelData.create(cells);
    }

    public void openMenu(Player player, MenuLocator locator) {
        MenuOpener.open(DriveMenu.TYPE, player, MenuLocators.forBlockEntity(this));
    }
}
