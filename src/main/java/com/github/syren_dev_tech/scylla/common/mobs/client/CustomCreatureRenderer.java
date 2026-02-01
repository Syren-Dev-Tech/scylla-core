package com.github.syren_dev_tech.scylla.common.mobs.client;

import com.github.syren_dev_tech.scylla.common.mobs.CreatureBuilder;
import com.github.syren_dev_tech.scylla.common.mobs.creatures.CustomCreature;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CustomCreatureRenderer<T extends CustomCreature> extends GeoEntityRenderer<T> {

    private final CreatureBuilder<T> builder;

    public CustomCreatureRenderer(CreatureBuilder<T> builder, EntityRendererProvider.Context context) {
        super(context, new CustomCreatureModel<>(builder));
        this.shadowRadius = builder.getShadowSize(); // Set shadow size
        this.builder = builder;

        if (builder.getTextureRenderer() != null)
            this.addRenderLayer(builder.getTextureRenderer());
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return builder.getTextures().apply(pEntity.getState());
    }
}
