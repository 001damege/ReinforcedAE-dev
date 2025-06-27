package com.mikazukichandamege.reinforcedae.registry;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import appeng.api.parts.PartModels;
import appeng.api.upgrades.Upgrades;
import appeng.items.materials.EnergyCardItem;
import appeng.items.materials.MaterialItem;
import appeng.items.parts.PartItem;
import appeng.items.parts.PartModelsHelper;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemDriveKit;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemIOBusKit;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemInterfaceKit;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemPatternProviderKit;
import com.mikazukichandamege.reinforcedae.common.item.part.RnfInterfacePart;
import com.mikazukichandamege.reinforcedae.common.item.part.RnfPatternProviderPart;
import com.mikazukichandamege.reinforcedae.common.item.part.RnfPatternProviderPartItem;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;
import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class RAEItem {
    public static final DeferredRegister<Item> DR = DeferredRegister.create(ForgeRegistries.ITEMS, ReinforcedAE.MOD_ID);
    private static final List<ItemDeferredRegistries<?>> ITEMS = new ArrayList<>();

    public static List<ItemDeferredRegistries<?>> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }

    public static final ItemDeferredRegistries<MaterialItem> REINFORCED_CARD = item("Reinforced Card", "reinforced_card", MaterialItem::new);
    public static final ItemDeferredRegistries<Item> SPEED_CARD = item("Compressed Accelerator Card", "compressed_speed_card", Upgrades::createUpgradeCardItem);
    public static final ItemDeferredRegistries<EnergyCardItem> ENERGY_CARD = item("Compressed Energy Card", "compressed_energy_card", p -> new EnergyCardItem(p, 9));
    public static final ItemDeferredRegistries<MaterialItem> ITEM_CELL_HOUSING = item("Reinforced Item Cell Housing", "reinforced_item_cell_housing", MaterialItem::new);
    public static final ItemDeferredRegistries<MaterialItem> FLUID_CELL_HOUSING = item("Reinforced Fluid Cell Housing", "reinforced_fluid_cell_housing", MaterialItem::new);
    public static final ItemDeferredRegistries<MaterialItem> OPTICS_PROCESSOR = item("Optics Processor", "optics_processor", MaterialItem::new);
    public static final ItemDeferredRegistries<ItemPatternProviderKit> PATTERN_PROVIDER_KIT = item("Pattern Provider kit", "pattern_provider_kit", ItemPatternProviderKit::new);
    public static final ItemDeferredRegistries<ItemInterfaceKit> INTERFACE_KIT = item("Interface kit", "interface_kit", ItemInterfaceKit::new);
    public static final ItemDeferredRegistries<ItemIOBusKit> PORT_KIT = item("IO Port kit", "port_kit", ItemIOBusKit::new);
    public static final ItemDeferredRegistries<ItemDriveKit> DRIVE_KIT = item("Drive kit", "drive_kit", ItemDriveKit::new);

    public static final ItemDeferredRegistries<MaterialItem> CELL_COMPONENT_1G = item("1G Storage Component", "cell_component_1g", MaterialItem::new);
    public static final ItemDeferredRegistries<MaterialItem> CELL_COMPONENT_4G = item("4G Storage Component", "cell_component_4g", MaterialItem::new);
    public static final ItemDeferredRegistries<MaterialItem> CELL_COMPONENT_16G = item("16G Storage Component", "cell_component_16g", MaterialItem::new);
    public static final ItemDeferredRegistries<MaterialItem> CELL_COMPONENT_64G = item("64G Storage Component", "cell_component_64g", MaterialItem::new);
    public static final ItemDeferredRegistries<MaterialItem> CELL_COMPONENT_256G = item("256G Storage Component", "cell_component_256g", MaterialItem::new);
    public static final ItemDeferredRegistries<MaterialItem> CELL_COMPONENT_CREATIVE = item("Creative Storage Component", "cell_component_creative", p -> new MaterialItem(p.rarity(Rarity.EPIC)));

    public static final ItemDeferredRegistries<PartItem<RnfInterfacePart>> INTERFACE = part("Reinforced Interface", "cable_rnf_interface", RnfInterfacePart.class, RnfInterfacePart::new);
    public static final ItemDeferredRegistries<RnfPatternProviderPartItem> PATTERN_PROVIDER = Util.make(() -> {
        PartModels.registerModels(PartModelsHelper.createModels(RnfPatternProviderPart.class));
        return item("Reinforced Pattern Provider", "cable_rnf_pattern_provider", RnfPatternProviderPartItem::new);
    });

    public static <T extends Item> ItemDeferredRegistries<T> item(String englishName, String id, Function<Item.Properties, T> factory) {
        ItemDeferredRegistries<T> def = new ItemDeferredRegistries<>(englishName, DR.register(id, () -> factory.apply(new Item.Properties())));
        ITEMS.add(def);
        return def;
    }

    private static <T extends IPart> ItemDeferredRegistries<PartItem<T>> part(String englishName, String id, Class<T> partClass, Function<IPartItem<T>, T> factory) {
        PartModels.registerModels(PartModelsHelper.createModels(partClass));
        return item(englishName, id, p -> new PartItem<>(p, partClass, factory));
    }
}
