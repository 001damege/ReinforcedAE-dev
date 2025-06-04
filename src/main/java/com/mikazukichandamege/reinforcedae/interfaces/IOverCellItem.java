package com.mikazukichandamege.reinforcedae.interfaces;

import appeng.api.stacks.AEKeyType;
import appeng.api.storage.cells.ICellWorkbenchItem;

public interface IOverCellItem extends ICellWorkbenchItem {

    AEKeyType getKeyType();
    long getBytes();
    int getBytePerType();
    int getTotalTypes();
}
