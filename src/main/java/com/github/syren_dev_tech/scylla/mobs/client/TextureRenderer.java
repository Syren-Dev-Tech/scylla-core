package com.github.syren_dev_tech.scylla.mobs.client;

import java.util.function.Function;
import com.github.syren_dev_tech.hydra.animatable.HydraAnimatable;
import com.github.syren_dev_tech.hydra.renderer.layer.HydraRenderLayer;
import com.github.syren_dev_tech.scylla.files.ResourcePath;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class TextureRenderer<T extends LivingEntity & HydraAnimatable> extends HydraRenderLayer<T> {

    private Function<T, ResourcePath> textureSupplier;

    public TextureRenderer(ResourcePath resource, CustomHydraRenderer<T> geoRenderer) {
        super(geoRenderer);

        this.textureSupplier = t -> resource;
    }

    public void setTextureSupplier(Function<T, ResourcePath> textureSupplier) {
        this.textureSupplier = textureSupplier;
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return this.textureSupplier.apply(animatable).get();
    }
}
