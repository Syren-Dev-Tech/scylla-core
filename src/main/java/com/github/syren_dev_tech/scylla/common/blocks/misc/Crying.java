package com.github.syren_dev_tech.scylla.common.blocks.misc;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CryingObsidianBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Crying {

    public static final BlockDefinition<CryingObsidianBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.CRYING_OBSIDIAN));
    }

    public static final BlockDefinition<CryingObsidianBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.CRYING_OBSIDIAN), creativeTab);
    }

    public static final BlockDefinition<CryingObsidianBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CryingObsidianBlock(properties)));
    }

    public static final BlockDefinition<CryingObsidianBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new CryingObsidianBlock(properties)), creativeTab);
    }

    private Crying() {
        // Prevent instantiation
    }
}
