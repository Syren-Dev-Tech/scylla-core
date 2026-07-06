package com.github.syren_dev_tech.scylla.blocks.fluids;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Ice {

    public static BlockDefinition<CustomIce> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.ICE));
    }

    public static BlockDefinition<CustomIce> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.ICE), creativeTab);
    }

    public static BlockDefinition<CustomIce> create(ModRegister register, String name, Block meltedBlock) {
        return create(register, name, Properties.ofFullCopy(Blocks.ICE), meltedBlock);
    }

    public static BlockDefinition<CustomIce> create(ModRegister register, String name, Block meltedBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.ICE), meltedBlock, creativeTab);
    }

    public static BlockDefinition<CustomIce> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CustomIce(properties)));
    }

    public static BlockDefinition<CustomIce> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CustomIce(properties)), creativeTab);
    }

    public static BlockDefinition<CustomIce> create(ModRegister register, String name, Properties properties, Block meltedBlock) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CustomIce(properties, meltedBlock)));
    }

    public static BlockDefinition<CustomIce> create(ModRegister register, String name, Properties properties, Block meltedBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CustomIce(properties, meltedBlock)), creativeTab);
    }

    private Ice() {
        // Prevent instantiation
    }
}
