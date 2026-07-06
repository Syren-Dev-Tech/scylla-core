package com.github.syren_dev_tech.scylla.blocks.plants;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

// Need custom bush, this only produces sweet berries.

public class Bushes {

    public static BlockDefinition<SweetBerryBushBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    }

    public static BlockDefinition<SweetBerryBushBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH), creativeTab);
    }

    public static BlockDefinition<SweetBerryBushBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SweetBerryBushBlock(properties)));
    }

    public static BlockDefinition<SweetBerryBushBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new SweetBerryBushBlock(properties)), creativeTab);
    }

    private Bushes() {
        // Prevent instantiation
    }
}
