package com.github.syren_dev_tech.scylla.blocks.decoration.lighting;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.ResourceKey;

public class Fires {

    public static BlockDefinition<FireBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.FIRE));
    }

    public static BlockDefinition<FireBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.FIRE), creativeTab);
    }

    public static BlockDefinition<FireBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new FireBlock(properties)));
    }

    public static BlockDefinition<FireBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new FireBlock(properties)), creativeTab);
    }

    private Fires() {
        // Prevent instantiation
    }
}
