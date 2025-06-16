package com.mikazukichandamege.reinforcedae.api.infe;

import appeng.api.stacks.AEKeyType;
import appeng.api.storage.cells.ICellWorkbenchItem;

public interface IOverCellItem extends ICellWorkbenchItem {

    AEKeyType getKeyType();
    long getBytes();
    int getBytePerType();
    int getTotalTypes();
}
