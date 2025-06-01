package com.mikazukichandamege.reinforcedae.util;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;

public class ChaosDamageSource extends DamageSource {
    private static final ResourceKey<DamageType> TYPE = ResourceKey.create(Registries.DAMAGE_TYPE, ReinforcedAE.makeId("chaos"));

    public ChaosDamageSource(LivingEntity entity) {
        super(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(TYPE), entity);
    }

    @Override
    public Component getLocalizedDeathMessage(LivingEntity pLivingEntity) {
        String death = "death.attack." + this.getMsgId() + "." + pLivingEntity.getRandom().nextInt(7);
        return Component.translatable(death, pLivingEntity.getDisplayName());
    }
}
