package com.mikazukichandamege.reinforcedae.registries;

import appeng.api.util.AEColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.EnumMap;
import java.util.Map;

public class ColoredDeferredRegistries<T extends Item>{
    private final Map<AEColor, ItemDeferredRegistries<T>> items = new EnumMap<>(AEColor.class);
    private final Map<AEColor, String> ids = new EnumMap<>(AEColor.class);

    public void add(AEColor v, String id, ItemDeferredRegistries<T> idr) {
        this.ids.put(v, id);
        this.items.put(v, idr);
    }

    public String id(AEColor color) {
        return ids.get(color);
    }

    public T item(AEColor color) {
        return this.items.get(color).asItem();
    }

    public ItemStack stack(AEColor color) {
        return stack(color, 1);
    }

    public ItemStack stack(AEColor color, int stackSize) {
        var item = item(color);

        if (item == null) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(item, stackSize);
    }
}
