package com.github.syren_dev_tech.scylla.common.mobs.client;

import java.util.function.Function;
import com.github.syren_dev_tech.scylla.common.registry.ModRegister;
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class TextureRenderer<T extends LivingEntity & GeoAnimatable> extends GeoRenderLayer<T> {

    private Function<T, ResourcePath> textureSupplier;

    public TextureRenderer(ModRegister register, String name, CustomGeoRenderer<T> geoRenderer) {
        super(geoRenderer);

        this.textureSupplier = t -> new ResourcePath(register.modId, "textures/entity/" + name + "/" + name + ".png");
    }

    public void setTextureSupplier(Function<T, ResourcePath> textureSupplier) {
        this.textureSupplier = textureSupplier;
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return this.textureSupplier.apply(animatable).get();
    }
}
