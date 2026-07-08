package com.github.syren_dev_tech.scylla.mobs.client;

import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import com.github.syren_dev_tech.scylla.mobs.creatures.CreatureState;
import com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature;
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CustomCreatureModel<T extends CustomCreature> extends GeoModel<T> {

    private final CreatureBuilder<T> builder;

    private ResourcePath model;
    private ResourcePath animations;

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
        return this.model.get();
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return this.builder.getTextures().apply((CreatureState<T>) animatable.getState()).get();
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return this.animations.get();
    }
}
