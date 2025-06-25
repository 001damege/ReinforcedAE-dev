package com.mikazukichandamege.reinforcedae.mixin.client;

import com.mikazukichandamege.reinforcedae.ReinforcedAE;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBakery.class)
public abstract class MixinModelBakery {

    @Shadow
    protected abstract void cacheAndQueueDependencies(ResourceLocation id, UnbakedModel model);

    @Inject(method = "loadModel", at = @At("HEAD"), cancellable = true)
    private void loadModelHook(ResourceLocation id, CallbackInfo ci) {
        UnbakedModel model = rae$getUnbakedModel(id);
        if (model != null) {
            cacheAndQueueDependencies(id, model);
            ci.cancel();
        }
    }

    @Unique
    private UnbakedModel rae$getUnbakedModel(ResourceLocation variantId) {
        if (!variantId.getNamespace().equals(ReinforcedAE.MOD_ID)) {
            return null;
        } if (variantId instanceof ModelResourceLocation modelId) {
            if (modelId.getVariant().equals("inventory")) {
                ResourceLocation itemId = new ResourceLocation(modelId.getNamespace(), "item/" + modelId.getPath());
                return MixinBuiltInModelHooks.getBuiltInModels().get(itemId);
            }
            return null;
        } else {
            return MixinBuiltInModelHooks.getBuiltInModels().get(variantId);
        }
    }
}
