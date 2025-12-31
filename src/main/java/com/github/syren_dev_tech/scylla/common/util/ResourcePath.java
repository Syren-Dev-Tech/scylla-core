package com.github.syren_dev_tech.scylla.common.util;

import net.minecraft.resources.ResourceLocation;

public class ResourcePath extends ResourceLocation {
    public ResourcePath(String namespace, String path) {
        super(namespace, path);
    }

    public ResourcePath(String combined) {
        super(combined);
    }

    public ResourceLocation toResourceLocation() {
        return new ResourceLocation(this.getNamespace(), this.getPath());
    }
}
