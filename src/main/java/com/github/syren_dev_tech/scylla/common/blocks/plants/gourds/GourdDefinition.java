package com.github.syren_dev_tech.scylla.common.blocks.plants.gourds;

import java.util.function.Supplier;
import com.github.syren_dev_tech.scylla.common.blocks.plants.gourds.types.Gourd;
import com.github.syren_dev_tech.scylla.common.registry.ModRegister;
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class GourdDefinition {
    public final String name;
    public final ResourceKey<Block> resourceKey;
    public final Supplier<Gourd> gourd;

    public GourdDefinition(ModRegister register, String name, Supplier<Gourd> gourd) {
        this.name = name;
        this.gourd = gourd;
        this.resourceKey = ResourceKey.create(Registries.BLOCK, new ResourcePath(register.modId, name).get()); // Placeholder, replace nulls with actual registry and location
    }
}
