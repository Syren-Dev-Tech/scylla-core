package com.github.syren_dev_tech.scylla.mobs.client;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;

public class TextureDefinition {

    public static final String ENTITY_TEXTURES_PATH = "textures/entity/";

    private ResourcePath texture;
    private ResourcePath mask;

    public TextureDefinition(ModRegister register, String textureName) {
        this.texture = new ResourcePath(register.modId, ENTITY_TEXTURES_PATH + textureName + ".png");
        this.mask = null;
    }

    public TextureDefinition(ModRegister register, String textureName, String maskName) {
        this.texture = new ResourcePath(register.modId, ENTITY_TEXTURES_PATH + textureName + ".png");
        this.mask = new ResourcePath(register.modId, ENTITY_TEXTURES_PATH + maskName + ".png");
    }

    public TextureDefinition(ResourcePath texture, ResourcePath mask) {
        this.texture = texture;
        this.mask = mask;
    }

    public TextureDefinition setTexture(ResourcePath texture) {
        this.texture = texture;
        return this;
    }

    public TextureDefinition setMask(ResourcePath mask) {
        this.mask = mask;
        return this;
    }

    public ResourcePath getTexture() {
        return this.texture;
    }

    public ResourcePath getMask() {
        return this.mask;
    }

}
