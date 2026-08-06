package com.github.syren_dev_tech.scylla.registry.definitions;

import com.github.syren_dev_tech.scylla.blocks.plants.gourds.types.Gourd;
import com.github.syren_dev_tech.scylla.files.ResourcePath;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class GourdDefinition {
    public final String name;
    public final ResourceKey<Block> resourceKey;
    public final BlockDefinition<Gourd> gourd;

    public GourdDefinition(ModRegister register, String name, BlockDefinition<Gourd> gourd) {
        this.name = name;
        this.gourd = gourd;
        this.resourceKey = ResourceKey.create(Registries.BLOCK, new ResourcePath(register.modId, name).get()); // Placeholder, replace nulls with actual registry and location
    }
}
