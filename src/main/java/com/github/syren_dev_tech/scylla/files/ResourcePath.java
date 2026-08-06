package com.github.syren_dev_tech.scylla.files;

import net.minecraft.resources.ResourceLocation;

public class ResourcePath {

    private final ResourceLocation resourceLocation;

    public ResourcePath(String namespace, String path) {
        this.resourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, path);
    }

    public ResourceLocation get() {
        return this.resourceLocation;
    }
}
