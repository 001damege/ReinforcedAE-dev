package com.mikazukichandamege.reinforcedae.item;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import appeng.api.upgrades.Upgrades;
import appeng.core.localization.ButtonToolTips;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class ItemUpgradeBase extends Item {
    private record BlockEntityPair(Block block, Class<? extends BlockEntity> blockEntity) {}
    private final HashMap<Class<? extends BlockEntity>, BlockEntityPair> BLOCK_MAP = new HashMap<>();
    private final HashMap<Class<? extends IPart>, IPartItem<?>> PART_MAP = new HashMap<>();

    public ItemUpgradeBase(Properties pProperties) {
        super(pProperties);
    }

    protected void addBlockEntity(Class<? extends BlockEntity> clazz, Block block, Class<? extends BlockEntity> blockEntity) {
        this.BLOCK_MAP.put(clazz, new BlockEntityPair(block, blockEntity));
    }

    protected void addPart(Class<? extends IPart> clazz, IPartItem<?> item) {
        this.PART_MAP.put(clazz, item);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        var supportedBy = Upgrades.getTooltipLinesForMachine(this);
        if (!supportedBy.isEmpty()) {
            pTooltipComponents.add(ButtonToolTips.SupportedBy.text());
            pTooltipComponents.addAll(supportedBy);
        }
    }

    
}
