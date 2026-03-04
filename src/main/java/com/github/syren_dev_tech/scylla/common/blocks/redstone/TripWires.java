package com.github.syren_dev_tech.scylla.common.blocks.redstone;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TripWireBlock;
import net.minecraft.world.level.block.TripWireHookBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class TripWires {

    public static class TripWireHooks {
        public static final BlockDefinition<TripWireHookBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.TRIPWIRE_HOOK));
        }

        public static final BlockDefinition<TripWireHookBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TRIPWIRE_HOOK), creativeTab);
        }

        public static final BlockDefinition<TripWireHookBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new TripWireHookBlock(properties)));
        }

        public static final BlockDefinition<TripWireHookBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new TripWireHookBlock(properties)), creativeTab);
        }

        private TripWireHooks() {
            // Prevent instantiation
        }
    }

    public static final BlockDefinition<TripWireBlock> create(ModRegister register, String name, TripWireHookBlock hookBlock) {
        return create(register, name, hookBlock, Properties.ofFullCopy(Blocks.TRIPWIRE));
    }

    public static final BlockDefinition<TripWireBlock> create(ModRegister register, String name, TripWireHookBlock hookBlock, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, hookBlock, Properties.ofFullCopy(Blocks.TRIPWIRE), creativeTab);
    }

    public static final BlockDefinition<TripWireBlock> create(ModRegister register, String name, TripWireHookBlock hookBlock, Properties properties) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new TripWireBlock(hookBlock, properties)));
    }

    public static final BlockDefinition<TripWireBlock> create(ModRegister register, String name, TripWireHookBlock hookBlock, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return register.blockRegistry.register(name, BlockDefinition.of(() -> new TripWireBlock(hookBlock, properties)), creativeTab);
    }

    private TripWires() {
        // Prevent instantiation
    }
}
