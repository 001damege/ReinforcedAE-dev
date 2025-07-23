package com.mikazukichandamege.reinforcedae.registry;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import appeng.api.parts.PartModels;
import appeng.api.stacks.AEKeyType;
import appeng.api.upgrades.Upgrades;
import appeng.api.util.AEColor;
import appeng.items.materials.EnergyCardItem;
import appeng.items.materials.MaterialItem;
import appeng.items.parts.ColoredPartItem;
import appeng.items.parts.PartItem;
import appeng.items.parts.PartModelsHelper;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.common.item.cell.ExDISK;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemDriveKit;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemIOBusKit;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemInterfaceKit;
import com.mikazukichandamege.reinforcedae.common.item.kit.ItemPatternProviderKit;
import com.mikazukichandamege.reinforcedae.common.item.part.RnfInterfacePart;
import com.mikazukichandamege.reinforcedae.common.item.part.RnfPatternProviderPart;
import com.mikazukichandamege.reinforcedae.common.item.part.RnfPatternProviderPartItem;
import com.mikazukichandamege.reinforcedae.common.item.part.networking.*;
import com.mikazukichandamege.reinforcedae.common.item.tool.QuantumTool;
import com.mikazukichandamege.reinforcedae.registries.ColoredDeferredRegistries;
import com.mikazukichandamege.reinforcedae.registries.ItemDeferredRegistries;
import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class RAEItem {
    public static final DeferredRegister<Item> DR = DeferredRegister.create(ForgeRegistries.ITEMS, ReinforcedAE.MOD_ID);
    public static final List<ColoredDeferredRegistries<?>> COLOR = new ArrayList<>();
    private static final List<ItemDeferredRegistries<?>> ITEMS = new ArrayList<>();

    public static List<ItemDeferredRegistries<?>> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }

    public static final ItemDeferredRegistries<MaterialItem> REINFORCED_CARD = item("Reinforced Card", "rnf_card", MaterialItem::new);
    public static final ItemDeferredRegistries<Item> SPEED_CARD = item("Compressed Accelerator Card", "compressed_speed_card", Upgrades::createUpgradeCardItem);
    public static final ItemDeferredRegistries<EnergyCardItem> ENERGY_CARD = item("Compressed Energy Card", "compressed_energy_card", p -> new EnergyCardItem(p, 9));
    public static final ItemDeferredRegistries<MaterialItem> DISK_HOUSING = item("Reinforced Disk Housing", "rnf_disk_housing", MaterialItem::new);
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
    public static final ItemDeferredRegistries<PartItem<RnfPatternProviderPart>> PATTERN_PROVIDER = part("Reinforced Pattern Provider", "cable_rnf_pattern_provider", RnfPatternProviderPart.class, RnfPatternProviderPart::new);

    public static final ItemDeferredRegistries<QuantumTool> QUANTUM_TOOL = item("Quantum MultiTool", "quantum_tool", p -> new QuantumTool(getQuantumToolAE(), p));

    public static final ItemDeferredRegistries<ExDISK> ITEM_DISK = item("Item Disk Drive", "item_disk_drive", p -> new ExDISK(CELL_COMPONENT_1G, Integer.MAX_VALUE / 1000, 10.0f, AEKeyType.items()));
    public static final ItemDeferredRegistries<ExDISK> FLUID_DISK = item("Fluid Disk Drive", "fluid_disk_drive", p -> new ExDISK(CELL_COMPONENT_1G, Integer.MAX_VALUE / 1000, 10.0f, AEKeyType.fluids()));

    public static final ColoredDeferredRegistries<ColoredPartItem<RnfSmartCablePart>> SMART_CABLE = color("Reinforced ME Smart Cable", "rnf_smart_cable", RnfSmartCablePart.class, RnfSmartCablePart::new);
    public static final ColoredDeferredRegistries<ColoredPartItem<RnfCoveredCablePart>> COVERED_CABLE = color("Reinforced ME Covered Cable", "rnf_covered_cable", RnfCoveredCablePart.class, RnfCoveredCablePart::new);
    public static final ColoredDeferredRegistries<ColoredPartItem<RnfGlassCablePart>> GLASS_CABLE = color("Reinforced ME Glass Cable", "rnf_glass_cable", RnfGlassCablePart.class, RnfGlassCablePart::new);
    public static final ColoredDeferredRegistries<ColoredPartItem<RnfCoveredDenseCablePart>> COVERED_DENSE_CABLE = color("Reinforced ME Dense Covered Cable", "rnf_covered_dense_cable", RnfCoveredDenseCablePart.class, RnfCoveredDenseCablePart::new);
    public static final ColoredDeferredRegistries<ColoredPartItem<RnfSmartDenseCablePart>> SMART_DENSE_CABLE = color("Reinforced ME Dense Smart Cable", "rnf_smart_dense_cable", RnfSmartDenseCablePart.class, RnfSmartDenseCablePart::new);

    public static <T extends Item> ItemDeferredRegistries<T> item(String englishName, String id, Function<Item.Properties, T> factory) {
        ItemDeferredRegistries<T> def = new ItemDeferredRegistries<>(englishName, DR.register(id, () -> factory.apply(new Item.Properties())));
        ITEMS.add(def);
        return def;
    }

    private static <T extends IPart> ItemDeferredRegistries<PartItem<T>> part(String englishName, String id, Class<T> partClass, Function<IPartItem<T>, T> factory) {
        PartModels.registerModels(PartModelsHelper.createModels(partClass));
        return item(englishName, id, p -> new PartItem<>(p, partClass, factory));
    }

    private static <T extends IPart> ColoredDeferredRegistries<ColoredPartItem<T>> color(String nameSuffix, String idSuffix, Class<T> partClass, Function<ColoredPartItem<T>, T> factory) {
        PartModels.registerModels(PartModelsHelper.createModels(partClass));
        var def = new ColoredDeferredRegistries<ColoredPartItem<T>>();

        for (AEColor color : AEColor.values()) {
            var id = color.registryPrefix + '_' + idSuffix;
            var name = color.registryPrefix + " " + nameSuffix;
            var itemDef = item(name, id, p -> new ColoredPartItem<>(p, partClass, factory, color));

            def.add(color, id, itemDef);
        }

        COLOR.add(def);
        return def;
    }

    public static DoubleSupplier getQuantumToolAE() {
        return () -> 123456789;
    }
}
