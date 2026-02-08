package com.github.syren_dev_tech.scylla.common.blocks.plants.melons;

import java.util.function.Supplier;
import com.github.syren_dev_tech.scylla.common.blocks.plants.melons.types.Melon;
import com.github.syren_dev_tech.scylla.common.registry.ModRegister;
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class MelonDefinition {
    public final String name;
    public final Supplier<Melon> melon;
    public final ResourceKey<Block> resourceKey;

    public MelonDefinition(ModRegister register, String name, Supplier<Melon> melon) {
        this.name = name;
        this.melon = melon;
        this.resourceKey = ResourceKey.create(Registries.BLOCK, new ResourcePath(register.modId, name).get());
    }
}
