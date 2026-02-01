package com.github.syren_dev_tech.scylla.common.mobs.client;

import com.github.syren_dev_tech.scylla.common.mobs.CreatureBuilder;
import com.github.syren_dev_tech.scylla.common.mobs.creatures.CustomCreature;
import com.github.syren_dev_tech.scylla.common.util.ResourcePath;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CustomCreatureModel<T extends CustomCreature> extends GeoModel<T> {

    private final CreatureBuilder<T> builder;

    private ResourceLocation model;
    private ResourceLocation animations;

    public CustomCreatureModel(CreatureBuilder<T> builder) {
        super();
        this.builder = builder;

        defineResources();
    }

    private void defineResources() {
        var registry = this.builder.getRegister();

        this.model = new ResourcePath(registry.modId, "geo/" + this.builder.getName() + ".geo.json");
        this.animations = new ResourcePath(registry.modId, "animations/" + this.builder.getName() + ".json");
    }

    @Override
    public ResourceLocation getModelResource(T animatable) {
        return this.model;
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return this.builder.getTextures().apply(animatable.getState());
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return this.animations;
    }
}
