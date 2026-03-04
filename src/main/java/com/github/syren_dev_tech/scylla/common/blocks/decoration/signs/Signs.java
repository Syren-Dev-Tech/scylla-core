package com.github.syren_dev_tech.scylla.common.blocks.decoration.signs;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.SignDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.WoodType;

public class Signs {

    public static class StandingSigns {

        public static final BlockDefinition<StandingSignBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.OAK_SIGN), WoodType.OAK);
        }

        public static final BlockDefinition<StandingSignBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.OAK_SIGN), WoodType.OAK, creativeTab);
        }

        public static final BlockDefinition<StandingSignBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, WoodType.OAK);
        }

        public static final BlockDefinition<StandingSignBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, properties, WoodType.OAK, creativeTab);
        }

        public static final BlockDefinition<StandingSignBlock> create(ModRegister register, String name, Properties properties, WoodType woodType) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new StandingSignBlock(woodType, properties)));
        }

        public static final BlockDefinition<StandingSignBlock> create(ModRegister register, String name, Properties properties, WoodType woodType, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new StandingSignBlock(woodType, properties)), creativeTab);
        }

        private StandingSigns() {
            // Prevent instantiation
        }
    }

    public static class WallSigns {

        public static final BlockDefinition<WallSignBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.OAK_SIGN), WoodType.OAK);
        }

        public static final BlockDefinition<WallSignBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, WoodType.OAK);
        }

        public static final BlockDefinition<WallSignBlock> create(ModRegister register, String name, Properties properties, WoodType woodType) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new WallSignBlock(woodType, properties)));
        }

        private WallSigns() {
            // Prevent instantiation
        }
    }

    public static final SignDefinition<StandingSignBlock, WallSignBlock> create(ModRegister register, String name) {
        var standing = StandingSigns.create(register, name);
        var wall = WallSigns.create(register, name);

        return new SignDefinition<>(name, standing, wall);
    }

    public static final SignDefinition<StandingSignBlock, WallSignBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        var standing = StandingSigns.create(register, name, creativeTab);
        var wall = WallSigns.create(register, name);

        return new SignDefinition<>(name, standing, wall);
    }

    public static final SignDefinition<StandingSignBlock, WallSignBlock> create(ModRegister register, String name, Properties properties) {
        var standing = StandingSigns.create(register, name, properties);
        var wall = WallSigns.create(register, name, properties);

        return new SignDefinition<>(name, standing, wall);
    }

    public static final SignDefinition<StandingSignBlock, WallSignBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        var standing = StandingSigns.create(register, name + "_sign", properties, creativeTab);
        var wall = WallSigns.create(register, name + "_wall_sign", properties);

        return new SignDefinition<>(name, standing, wall);
    }

    private Signs() {
        // Prevent instantiation
    }
}
