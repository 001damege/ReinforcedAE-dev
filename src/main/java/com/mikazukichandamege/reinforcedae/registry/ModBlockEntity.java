package com.mikazukichandamege.reinforcedae.registry;

import appeng.block.AEBaseEntityBlock;
import appeng.blockentity.AEBaseBlockEntity;
import appeng.blockentity.crafting.CraftingBlockEntity;
import appeng.core.definitions.BlockDefinition;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import com.mikazukichandamege.reinforcedae.common.blockentity.ReinforcedInterfaceBlockEntity;
import com.mikazukichandamege.reinforcedae.common.blockentity.ReinforcedPatternProviderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings({"unused", "unchecked"})
public final class ModBlockEntity {
    public static void init() {
        ReinforcedAE.LOGGER.info("Init block entity");
    }

    private static final Map<ResourceLocation, BlockEntityType<?>> BLOCK_ENTITY_TYPES = new HashMap<>();

    public static Map<ResourceLocation, BlockEntityType<?>> getBlockEntityTypes() {
        return Collections.unmodifiableMap(BLOCK_ENTITY_TYPES);
    }

    public static final BlockEntityType<CraftingBlockEntity> CRAFTING_UNIT = create("reinforced_crafting_unit", CraftingBlockEntity.class, CraftingBlockEntity::new, ModBlock.CRAFTING_UNIT, ModBlock.CRAFTING_ACCELERATOR);
    public static final BlockEntityType<CraftingBlockEntity> CRAFTING_STORAGE = create("reinforced_crafting_storage", CraftingBlockEntity.class, CraftingBlockEntity::new, ModBlock.STORAGE_1024M, ModBlock.STORAGE_2048M, ModBlock.STORAGE_8192M, ModBlock.STORAGE_32768M, ModBlock.STORAGE_131072M);
    public static final BlockEntityType<ReinforcedPatternProviderBlockEntity> REINFORCED_PATTERN_PROVIDER = create("reinforced_pattern_provider", ReinforcedPatternProviderBlockEntity.class, ReinforcedPatternProviderBlockEntity::new, ModBlock.REINFORCED_PATTERN_PROVIDER);
    public static final BlockEntityType<ReinforcedInterfaceBlockEntity> REINFORCED_INTERFACE = create("reinforced_interface", ReinforcedInterfaceBlockEntity.class, ReinforcedInterfaceBlockEntity::new, ModBlock.REINFORCED_INTERFACE);

    @SafeVarargs
    public static <T extends AEBaseBlockEntity> BlockEntityType<T> create(String id, Class<T> entityClass, BlockEntityFactory<T> factory, BlockDefinition<? extends AEBaseEntityBlock<?>>... blockDefinitions) {
        if (blockDefinitions.length == 0) {
            throw new IllegalArgumentException();
        }
        var blocks = Arrays.stream(blockDefinitions).map(BlockDefinition::block).toArray(AEBaseEntityBlock[]::new);
        var typeHolder = new AtomicReference<BlockEntityType<T>>();
        var type = BlockEntityType.Builder.of((blockPos, blockState) ->
                factory.create(typeHolder.get(), blockPos, blockState), blocks).build(null);
        typeHolder.set(type);
        BLOCK_ENTITY_TYPES.put(ReinforcedAE.makeId(id), type);
        AEBaseBlockEntity.registerBlockEntityItem(type, blockDefinitions[0].asItem());
        for (var block : blocks) {
            var baseBlock = (AEBaseEntityBlock<T>) block;
            baseBlock.setBlockEntity(entityClass, type, null, null);
        }
        return type;
    }

    public interface BlockEntityFactory<T extends AEBaseBlockEntity> {
        T create(BlockEntityType<T> type, BlockPos pos, BlockState state);
    }
}
