package com.github.syren_dev_tech.scylla.registry.definitions;

import java.util.function.Supplier;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.utilities.files.ResourcePath;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BlockDefinition<T extends Block> {
    public final String name;
    public final Supplier<T> registry;
    public final ResourceKey<Block> resourceKey;

    public BlockDefinition(ModRegister modRegister, String name, Supplier<T> registry) {
        this.registry = registry;

        if (modRegister == null) {
            this.name = null;
            this.resourceKey = null;

            return;
        }

        this.name = name;
        this.resourceKey = ResourceKey.create(Registries.BLOCK, new ResourcePath(modRegister.modId, name).get());
    }

    public Properties properties() {
        return Properties.ofFullCopy(registry.get());
    }

    public static <T extends Block> BlockDefinition<T> of(Supplier<T> block) {
        return new BlockDefinition<>(null, "", block);
    }
}
