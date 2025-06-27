package com.mikazukichandamege.reinforcedae.registry;

import appeng.block.AEBaseEntityBlock;
import appeng.blockentity.AEBaseBlockEntity;
import appeng.blockentity.crafting.CraftingBlockEntity;
import appeng.blockentity.crafting.CraftingMonitorBlockEntity;
import appeng.blockentity.networking.EnergyCellBlockEntity;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.common.blockentity.RnfInterfaceBlockEntity;
import com.mikazukichandamege.reinforcedae.common.blockentity.RnfPatternProviderBlockEntity;
import com.mikazukichandamege.reinforcedae.registries.BlockDeferredRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class RAEBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> DR = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ReinforcedAE.MOD_ID);

    public static final Supplier<BlockEntityType<RnfInterfaceBlockEntity>> INTERFACE = create("rnf_interface", RnfInterfaceBlockEntity.class, RnfInterfaceBlockEntity::new, RAEBlock.INTERFACE);
    public static final Supplier<BlockEntityType<RnfPatternProviderBlockEntity>> PATTERN_PROVIDER = create("rnf_pattern_provider", RnfPatternProviderBlockEntity.class, RnfPatternProviderBlockEntity::new, RAEBlock.PATTERN_PROVIDER);
    public static final Supplier<BlockEntityType<CraftingMonitorBlockEntity>> MONITOR = create("rnf_crafting_monitor", CraftingMonitorBlockEntity.class, CraftingMonitorBlockEntity::new, RAEBlock.MONITOR);
    public static final Supplier<BlockEntityType<EnergyCellBlockEntity>> IMP_ENERGY = create("imp_energy_cell", EnergyCellBlockEntity.class, EnergyCellBlockEntity::new, RAEBlock.IMP_ENERGY);
    public static final Supplier<BlockEntityType<EnergyCellBlockEntity>> ADV_ENERGY = create("adv_energy_cell", EnergyCellBlockEntity.class, EnergyCellBlockEntity::new, RAEBlock.ADV_ENERGY);
    public static final Supplier<BlockEntityType<EnergyCellBlockEntity>> PER_ENERGY = create("per_energy_cell", EnergyCellBlockEntity.class, EnergyCellBlockEntity::new, RAEBlock.PER_ENERGY);
    public static final Supplier<BlockEntityType<EnergyCellBlockEntity>> QUA_ENERGY = create("qua_energy_cell", EnergyCellBlockEntity.class, EnergyCellBlockEntity::new, RAEBlock.QUA_ENERGY);

    public static final Supplier<BlockEntityType<CraftingBlockEntity>> UNIT = create(
            "rnf_crafting_unit", CraftingBlockEntity.class, CraftingBlockEntity::new,
            RAEBlock.UNIT,
            RAEBlock.ACCELERATOR_4x,
            RAEBlock.ACCELERATOR_16x,
            RAEBlock.ACCELERATOR_64x,
            RAEBlock.ACCELERATOR_256x,
            RAEBlock.ACCELERATOR_1024x,
            RAEBlock.ACCELERATOR_4096x,
            RAEBlock.ACCELERATOR_16384x,
            RAEBlock.ACCELERATOR_65536x);

    public static final Supplier<BlockEntityType<CraftingBlockEntity>> STORAGE = create(
            "rnf_crafting_storage", CraftingBlockEntity.class, CraftingBlockEntity::new,
            RAEBlock.STORAGE_1G,
            RAEBlock.STORAGE_4G,
            RAEBlock.STORAGE_16G,
            RAEBlock.STORAGE_64G,
            RAEBlock.STORAGE_256G);

    @SuppressWarnings({"unchecked", "DataFlowIssue"})
    @SafeVarargs
    private static <T extends AEBaseBlockEntity> Supplier<BlockEntityType<T>> create(String id, Class<T> entityClass, BlockEntityFactory<T> factory, BlockDeferredRegistries<? extends AEBaseEntityBlock<?>>... blockDef) {
        if (blockDef.length == 0) {
            throw new IllegalArgumentException();
        }

        return DR.register(id, () -> {
            var blocks = Arrays.stream(blockDef).map(BlockDeferredRegistries::block).toArray(AEBaseEntityBlock[]::new);
            var typeHolder = new AtomicReference<BlockEntityType<T>>();
            var type = BlockEntityType.Builder.of((p, s) -> factory.create(typeHolder.get(), p ,s), blocks).build(null);
            typeHolder.set(type);
            AEBaseBlockEntity.registerBlockEntityItem(type, blockDef[0].asItem());

            for (var block : blocks) {
                block.setBlockEntity(entityClass, type, null, null);
            }
            return type;
        });
    }

    private interface BlockEntityFactory<T extends AEBaseBlockEntity> {
        T create(BlockEntityType<T> type, BlockPos pos, BlockState state);
    }
}
