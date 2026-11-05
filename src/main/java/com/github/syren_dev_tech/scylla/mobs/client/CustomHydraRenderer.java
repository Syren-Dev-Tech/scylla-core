package com.github.syren_dev_tech.scylla.mobs.client;

import com.github.syren_dev_tech.hydra.animatable.HydraAnimatable;
import com.github.syren_dev_tech.hydra.cache.object.BakedHydraModel;
import com.github.syren_dev_tech.hydra.model.HydraModel;
import com.github.syren_dev_tech.hydra.renderer.HydraRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class CustomHydraRenderer<T extends LivingEntity & HydraAnimatable> implements HydraRenderer<T> {

    @Override
    public HydraModel<T> getHydraModel() {
        // Return a valid GeoModel<T> instance (replace with actual implementation)
        return new HydraModel<T>() {
            @Override
            public ResourceLocation getModelResource(T animatable) {
                // Provide a valid ResourceLocation for the model
                return ResourceLocation.fromNamespaceAndPath("mod_id", "models/entity/model.json");
            }

            @Override
            public ResourceLocation getTextureResource(T animatable) {
                throw new UnsupportedOperationException("Unimplemented method 'getTextureResource'");
            }

            @Override
            public ResourceLocation getAnimationResource(T animatable) {
                throw new UnsupportedOperationException("Unimplemented method 'getAnimationResource'");
            }
        };
    }

    @Override
    public T getAnimatable() {
        throw new UnsupportedOperationException("Unimplemented method 'getAnimatable'");
    }

    @Override
    public void fireCompileRenderLayersEvent() {
        throw new UnsupportedOperationException("Unimplemented method 'fireCompileRenderLayersEvent'");
    }

    @Override
    public boolean firePreRenderEvent(PoseStack poseStack, BakedHydraModel model, MultiBufferSource bufferSource, float partialTick, int packedLight) {
        throw new UnsupportedOperationException("Unimplemented method 'firePreRenderEvent'");
    }

    @Override
    public void firePostRenderEvent(PoseStack poseStack, BakedHydraModel model, MultiBufferSource bufferSource, float partialTick, int packedLight) {
        throw new UnsupportedOperationException("Unimplemented method 'firePostRenderEvent'");
    }

    @Override
    public void updateAnimatedTextureFrame(T animatable) {
        throw new UnsupportedOperationException("Unimplemented method 'updateAnimatedTextureFrame'");
    }

}
