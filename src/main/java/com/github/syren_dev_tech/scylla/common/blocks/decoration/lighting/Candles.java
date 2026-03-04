package com.github.syren_dev_tech.scylla.common.blocks.decoration.lighting;

import com.github.syren_dev_tech.scylla.registry.ModRegister;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import com.github.syren_dev_tech.scylla.registry.definitions.CandleDefinition;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.ResourceKey;

public class Candles {

    public static class CakeCandles {

        public static final BlockDefinition<CandleCakeBlock> create(ModRegister register, String name, BlockDefinition<CandleBlock> standingCandle) {
            return create(register, name, Properties.ofFullCopy(Blocks.CANDLE_CAKE), standingCandle);
        }

        public static final BlockDefinition<CandleCakeBlock> create(ModRegister register, String name, BlockDefinition<CandleBlock> standingCandle, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.CANDLE_CAKE), standingCandle, creativeTab);
        }

        public static final BlockDefinition<CandleCakeBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<CandleBlock> standingCandle) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new CandleCakeBlock(standingCandle.registry.get(), properties)));
        }

        public static final BlockDefinition<CandleCakeBlock> create(ModRegister register, String name, Properties properties, BlockDefinition<CandleBlock> standingCandle, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new CandleCakeBlock(standingCandle.registry.get(), properties)), creativeTab);
        }

        private CakeCandles() {
            // Prevent instantiation
        }
    }

    public static class PlainCandles {

        public static final BlockDefinition<CandleBlock> create(ModRegister register, String name) {
            return create(register, name, Properties.ofFullCopy(Blocks.FIRE));
        }

        public static final BlockDefinition<CandleBlock> create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
            return create(register, name, Properties.ofFullCopy(Blocks.FIRE), creativeTab);
        }

        public static final BlockDefinition<CandleBlock> create(ModRegister register, String name, Properties properties) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new CandleBlock(properties)));
        }

        public static final BlockDefinition<CandleBlock> create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
            return register.blockRegistry.register(name, BlockDefinition.of(() -> new CandleBlock(properties)), creativeTab);
        }

        private PlainCandles() {
            // Prevent instantiation
        }
    }

    public static final CandleDefinition create(ModRegister register, String name) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_CANDLE));
    }

    public static final CandleDefinition create(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        return create(register, name, Properties.ofFullCopy(Blocks.WHITE_CANDLE), creativeTab);
    }

    public static final CandleDefinition create(ModRegister register, String name, Properties properties) {
        var standing = PlainCandles.create(register, name, properties);
        var cake = CakeCandles.create(register, name + "_cake", properties, standing);

        return new CandleDefinition(name, standing, cake);
    }

    public static final CandleDefinition create(ModRegister register, String name, Properties properties, ResourceKey<CreativeModeTab> creativeTab) {
        var standing = PlainCandles.create(register, name, properties, creativeTab);
        var cake = CakeCandles.create(register, name + "_cake", properties, standing);

        return new CandleDefinition(name, standing, cake);
    }

    private Candles() {
        // Prevent instantiation
    }
}
