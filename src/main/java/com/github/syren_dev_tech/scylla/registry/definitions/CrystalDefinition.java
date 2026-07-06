package com.github.syren_dev_tech.scylla.registry.definitions;

import com.github.syren_dev_tech.scylla.blocks.decoration.crystals.CrystalBlocks;
import com.github.syren_dev_tech.scylla.blocks.decoration.crystals.CrystalBudBlocks;
import com.github.syren_dev_tech.scylla.blocks.decoration.crystals.FullCrystals;
import com.github.syren_dev_tech.scylla.blocks.decoration.crystals.LargeCrystals;
import com.github.syren_dev_tech.scylla.blocks.decoration.crystals.MediumCrystals;
import com.github.syren_dev_tech.scylla.blocks.decoration.crystals.SmallCrystals;
import com.github.syren_dev_tech.scylla.blocks.decoration.crystals.types.BuddingCrystal;
import com.github.syren_dev_tech.scylla.blocks.decoration.crystals.types.BuddingCrystalProperties;
import com.github.syren_dev_tech.scylla.registry.ModRegister;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.AmethystClusterBlock;

public class CrystalDefinition {
    public final String name;
    public final BlockDefinition<BuddingCrystal<AmethystClusterBlock>> buddingBlock;
    public final BlockDefinition<AmethystClusterBlock> smallCrystal;
    public final BlockDefinition<AmethystClusterBlock> mediumCrystal;
    public final BlockDefinition<AmethystClusterBlock> largeCrystal;
    public final BlockDefinition<AmethystClusterBlock> fullCrystal;
    public final BlockDefinition<AmethystBlock> block;

    public CrystalDefinition(ModRegister register, String name) {
        this.name = name;

        this.smallCrystal = SmallCrystals.create(register, "small_" + name + "_bud");
        this.mediumCrystal = MediumCrystals.create(register, "medium_" + name + "_bud");
        this.largeCrystal = LargeCrystals.create(register, "large_" + name + "_bud");
        this.fullCrystal = FullCrystals.create(register, name + "_cluster");

        var budProperties = new BuddingCrystalProperties<>(this.smallCrystal.registry, this.mediumCrystal.registry, this.largeCrystal.registry, this.fullCrystal.registry);

        this.buddingBlock = CrystalBudBlocks.create(register, "budding_" + name, budProperties);
        this.block = CrystalBlocks.create(register, name + "_block");
    }

    public CrystalDefinition(ModRegister register, String name, ResourceKey<CreativeModeTab> creativeTab) {
        this.name = name;

        this.smallCrystal = SmallCrystals.create(register, "small_" + name + "_bud", creativeTab);
        this.mediumCrystal = MediumCrystals.create(register, "medium_" + name + "_bud", creativeTab);
        this.largeCrystal = LargeCrystals.create(register, "large_" + name + "_bud", creativeTab);
        this.fullCrystal = FullCrystals.create(register, name + "_cluster", creativeTab);

        var budProperties = new BuddingCrystalProperties<>(this.smallCrystal.registry, this.mediumCrystal.registry, this.largeCrystal.registry, this.fullCrystal.registry);

        this.buddingBlock = CrystalBudBlocks.create(register, "budding_" + name, creativeTab, budProperties);
        this.block = CrystalBlocks.create(register, name + "_block", creativeTab);
    }
}
