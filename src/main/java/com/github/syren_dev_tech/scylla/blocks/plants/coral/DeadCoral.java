package com.github.syren_dev_tech.scylla.blocks.plants.coral;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.BaseCoralPlantBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DeadCoral {

    public static BlockDefinition<BaseCoralPlantBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL));
    }

    public static BlockDefinition<BaseCoralPlantBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL), creativeTab);
    }

    public static BlockDefinition<BaseCoralPlantBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new BaseCoralPlantBlock(properties)));
    }

    public static BlockDefinition<BaseCoralPlantBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new BaseCoralPlantBlock(properties)), creativeTab);
    }

    private DeadCoral() {
        // Prevent instantiation
    }
}
