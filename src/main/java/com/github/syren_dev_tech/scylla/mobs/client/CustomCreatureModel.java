package com.github.syren_dev_tech.scylla.mobs.client;

import com.github.syren_dev_tech.hydra.model.HydraModel;
import com.github.syren_dev_tech.scylla.files.ResourcePath;
import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import com.github.syren_dev_tech.scylla.mobs.creatures.CreatureState;
import com.github.syren_dev_tech.scylla.mobs.creatures.CustomCreature;
import net.minecraft.resources.ResourceLocation;

public class CustomCreatureModel<T extends CustomCreature> extends HydraModel<T> {

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

        this.model = new ResourcePath(registry.modId, "hydra/" + this.builder.getName() + ".hydra.json");
        this.animations = new ResourcePath(registry.modId, "animations/" + this.builder.getName() + ".animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T animatable) {
        return this.model.get();
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        CreatureState<T> state = this.getTypedState(animatable);
        TextureDefinition definition = this.builder.getTextureForState(state);
        return definition.getTexture().get();
    }

    @SuppressWarnings("unchecked")
    private CreatureState<T> getTypedState(T animatable) {
        return (CreatureState<T>) animatable.getState();
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return this.animations.get();
    }
}
