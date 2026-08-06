package com.github.syren_dev_tech.scylla.blocks.decoration;

import com.github.syren_dev_tech.scylla.collections.Tuple;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Banners {

    public static class StandingBanners {

        public static BlockDefinition<BannerBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.WHITE_BANNER), DyeColor.WHITE);
        }

        public static BlockDefinition<BannerBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.WHITE_BANNER), DyeColor.WHITE, creativeTab);
        }

        public static BlockDefinition<BannerBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, DyeColor.WHITE);
        }

        public static BlockDefinition<BannerBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, properties, DyeColor.WHITE, creativeTab);
        }

        public static BlockDefinition<BannerBlock> create(ModRegister register, String name, DyeColor color) {
            return create(register, name, Properties.ofFullCopy(Blocks.WHITE_BANNER), color);
        }

        public static BlockDefinition<BannerBlock> create(ModRegister register, String name, DyeColor color, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.WHITE_BANNER), color, creativeTab);
        }

        public static BlockDefinition<BannerBlock> create(ModRegister register, String name, Properties properties, DyeColor color) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new BannerBlock(color, properties)));
        }

        public static BlockDefinition<BannerBlock> create(ModRegister register, String name, Properties properties, DyeColor color, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new BannerBlock(color, properties)), creativeTab);
        }

        private StandingBanners() {
            // Prevent instantiation
        }
    }

    public static class WallBanners {

        public static BlockDefinition<WallBannerBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.WHITE_WALL_BANNER), DyeColor.WHITE);
        }

        public static BlockDefinition<WallBannerBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.WHITE_WALL_BANNER), DyeColor.WHITE, creativeTab);
        }

        public static BlockDefinition<WallBannerBlock> create(ModRegister register, String name, Properties properties) {
            return create(register, name, properties, DyeColor.WHITE);
        }

        public static BlockDefinition<WallBannerBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, properties, DyeColor.WHITE, creativeTab);
        }

        public static BlockDefinition<WallBannerBlock> create(ModRegister register, String name, DyeColor color) {
            return create(register, name, Properties.ofFullCopy(Blocks.WHITE_WALL_BANNER), color);
        }

        public static BlockDefinition<WallBannerBlock> create(ModRegister register, String name, DyeColor color, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.WHITE_WALL_BANNER), color, creativeTab);
        }

        public static BlockDefinition<WallBannerBlock> create(ModRegister register, String name, Properties properties, DyeColor color) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new WallBannerBlock(color, properties)));
        }

        public static BlockDefinition<WallBannerBlock> create(ModRegister register, String name, Properties properties, DyeColor color, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new WallBannerBlock(color, properties)), creativeTab);
        }

        private WallBanners() {
            // Prevent instantiation
        }
    }

    public static final Tuple<BlockDefinition<BannerBlock>, BlockDefinition<WallBannerBlock>> create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_BANNER), DyeColor.WHITE);
    }

    public static final Tuple<BlockDefinition<BannerBlock>, BlockDefinition<WallBannerBlock>> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_BANNER), DyeColor.WHITE, creativeTab);
    }

    public static final Tuple<BlockDefinition<BannerBlock>, BlockDefinition<WallBannerBlock>> create(ModRegister register, String name, Properties properties) {
        return create(register, name, properties, DyeColor.WHITE);
    }

    public static final Tuple<BlockDefinition<BannerBlock>, BlockDefinition<WallBannerBlock>> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, properties, DyeColor.WHITE, creativeTab);
    }

    public static final Tuple<BlockDefinition<BannerBlock>, BlockDefinition<WallBannerBlock>> create(ModRegister register, String name, DyeColor color) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_BANNER), color);
    }

    public static final Tuple<BlockDefinition<BannerBlock>, BlockDefinition<WallBannerBlock>> create(ModRegister register, String name, DyeColor color, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_BANNER), color, creativeTab);
    }

    public static final Tuple<BlockDefinition<BannerBlock>, BlockDefinition<WallBannerBlock>> create(ModRegister register, String name, Properties properties, DyeColor color) {
        var standing = StandingBanners.create(register, name, properties, color);
        var wall = WallBanners.create(register, name, properties, color);

        return new Tuple<>(standing, wall);
    }

    public static final Tuple<BlockDefinition<BannerBlock>, BlockDefinition<WallBannerBlock>> create(ModRegister register, String name, Properties properties, DyeColor color, ResourceKey<CreativeModeTab> creativeTab) {
        var standing = StandingBanners.create(register, name, properties, color, creativeTab);
        var wall = WallBanners.create(register, name, properties, color);

        return new Tuple<>(standing, wall);
    }

    private Banners() {
        // Prevent instantiation
    }
}
