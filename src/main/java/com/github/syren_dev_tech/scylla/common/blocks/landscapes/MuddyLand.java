package com.github.syren_dev_tech.scylla.common.blocks.landscapes;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MudBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class MuddyLand {

    public static final BlockDefinition<MudBlock> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.MUD));
    }

    public static final BlockDefinition<MudBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.MUD), creativeTab);
    }

    public static final BlockDefinition<MudBlock> create(ModRegister register, String name, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new MudBlock(properties)));
    }

    public static final BlockDefinition<MudBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new MudBlock(properties)), creativeTab);
    }

    private MuddyLand() {
        // Prevent instantiation
    }
}
