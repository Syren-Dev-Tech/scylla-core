package com.github.syren_dev_tech.scylla.common.blocks.decoration.lighting;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.TorchDefinition;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Torches {

    public static class StandingTorches {

        public static final BlockDefinition<TorchBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.TORCH), ParticleTypes.FLAME);
        }

        public static final BlockDefinition<TorchBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TORCH), ParticleTypes.FLAME, creativeTab);
        }

        public static final BlockDefinition<TorchBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, ParticleTypes.FLAME);
        }

        public static final BlockDefinition<TorchBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, properties, ParticleTypes.FLAME, creativeTab);
        }

        public static final BlockDefinition<TorchBlock> create(ModRegister register, String name, Properties properties, SimpleParticleType particle) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new TorchBlock(particle, properties)));
        }

        public static final BlockDefinition<TorchBlock> create(ModRegister register, String name, Properties properties, SimpleParticleType particle, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new TorchBlock(particle, properties)), creativeTab);
        }

        private StandingTorches() {
            // Prevent instantiation
        }
    }

    public static class WallTorches {

        public static final BlockDefinition<WallTorchBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.TORCH), ParticleTypes.FLAME);
        }

        public static final BlockDefinition<WallTorchBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.TORCH), ParticleTypes.FLAME, creativeTab);
        }

        public static final BlockDefinition<WallTorchBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, ParticleTypes.FLAME);
        }

        public static final BlockDefinition<WallTorchBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, properties, ParticleTypes.FLAME, creativeTab);
        }

        public static final BlockDefinition<WallTorchBlock> create(ModRegister register, String name, Properties properties, SimpleParticleType particle) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new WallTorchBlock(particle, properties)));
        }

        public static final BlockDefinition<WallTorchBlock> create(ModRegister register, String name, Properties properties, SimpleParticleType particle, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new WallTorchBlock(particle, properties)), creativeTab);
        }

        private WallTorches() {
            // Prevent instantiation
        }
    }

    private static final String WALL_PREFIX = "wall_";

    public static final TorchDefinition create(ModRegister register, String name) {
        var standing = StandingTorches.create(register, name);
        var wall = WallTorches.create(register, WALL_PREFIX + name);

        return new TorchDefinition(name, standing, wall);
    }

    public static final TorchDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        var standing = StandingTorches.create(register, name, creativeTab);
        var wall = WallTorches.create(register, WALL_PREFIX + name);

        return new TorchDefinition(name, standing, wall);
    }

    public static final TorchDefinition create(ModRegister register, String name, Properties properties) {
        var standing = StandingTorches.create(register, name, properties);
        var wall = WallTorches.create(register, WALL_PREFIX + name, properties);

        return new TorchDefinition(name, standing, wall);
    }

    public static final TorchDefinition create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        var standing = StandingTorches.create(register, name, properties, creativeTab);
        var wall = WallTorches.create(register, WALL_PREFIX + name, properties);

        return new TorchDefinition(name, standing, wall);
    }

    private Torches() {
        // Prevent instantiation
    }
}
