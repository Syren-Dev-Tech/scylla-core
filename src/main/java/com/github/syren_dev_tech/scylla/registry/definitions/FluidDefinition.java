package com.github.syren_dev_tech.scylla.registry.definitions;

import java.util.function.Supplier;
import com.github.syren_dev_tech.scylla.files.ResourcePath;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.Fluid;

public class FluidDefinition<T extends Fluid> {
    public final String name;
    public final Supplier<T> registry;
    public final ResourceKey<Fluid> resourceKey;

    public FluidDefinition(ModRegister modRegister, String name, Supplier<T> registry) {
        this.name = name;
        this.registry = registry;
        this.resourceKey = ResourceKey.create(Registries.FLUID, new ResourcePath(modRegister.modId, name).get());
    }

    public static <T extends Fluid> FluidDefinition<T> of(T fluid) {
        return new FluidDefinition<>(null, "", () -> fluid);
    }

    public static <T extends Fluid> FluidDefinition<T> from(Supplier<T> fluid) {
        return new FluidDefinition<>(null, "", fluid);
    }
}
