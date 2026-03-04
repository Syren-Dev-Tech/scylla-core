package com.github.syren_dev_tech.scylla.registry.definitions;

import com.github.syren_dev_tech.scylla.common.blocks.plants.melons.types.Melon;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class MelonDefinition {
    public final String name;
    public final BlockDefinition<Melon> melon;
    public final ResourceKey<Block> resourceKey;

    public MelonDefinition(ModRegister register, String name, BlockDefinition<Melon> melon) {
        this.name = name;
        this.melon = melon;
        this.resourceKey = ResourceKey.create(Registries.BLOCK, new ResourcePath(register.modId, name).get());
    }
}
