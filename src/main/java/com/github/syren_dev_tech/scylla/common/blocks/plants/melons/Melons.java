package com.github.syren_dev_tech.scylla.common.blocks.plants.melons;

import com.github.syren_dev_tech.scylla.common.blocks.plants.melons.types.Melon;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.MelonDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Melons {

    public static final MelonDefinition create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.MELON));
    }

    public static final MelonDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.MELON), creativeTab);
    }

    public static final MelonDefinition create(ModRegister register, String name, Properties properties) {
        var def = register.blockRegistry.register(name, BlockDefinition.of(() -> new Melon(properties)));

        return new MelonDefinition(register, name, def);
    }

    public static final MelonDefinition create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        var def = register.blockRegistry.register(name, BlockDefinition.of(() -> new Melon(properties)), creativeTab);

        return new MelonDefinition(register, name, def);
    }

    private Melons() {
        // Prevent instantiation
    }
}
