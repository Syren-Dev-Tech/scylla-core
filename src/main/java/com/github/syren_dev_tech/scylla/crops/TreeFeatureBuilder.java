package com.github.syren_dev_tech.scylla.crops;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class TreeFeatureBuilder {

    private BlockDefinition<? extends Block> wood = BlockDefinition.of(() -> Blocks.OAK_LOG);
    private BlockDefinition<? extends Block> leaves = BlockDefinition.of(() -> Blocks.OAK_LEAVES);
    private TrunkPlacer trunkPlacer = new StraightTrunkPlacer(5, 2, 0);
    private FoliagePlacer foliagePlacer = new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3);
    private FeatureSize featureSize = new TwoLayersFeatureSize(1, 0, 1);
    private final List<TreeDecorator> decorators = new ArrayList<>();
    private final List<PlacementModifier> placementModifiers = new ArrayList<>(List.of(CountPlacement.of(1), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.top()), BiomeFilter.biome()));
    private TagKey<Biome> biomeTag = BiomeTags.IS_OVERWORLD;

    private TreeFeatureBuilder() {}

    public static TreeFeatureBuilder create() {
        return new TreeFeatureBuilder();
    }

    public TreeFeatureBuilder withWood(BlockDefinition<? extends Block> wood) {
        this.wood = Objects.requireNonNull(wood, "Wood block cannot be null");
        return this;
    }

    public TreeFeatureBuilder withLeaves(BlockDefinition<? extends Block> leaves) {
        this.leaves = Objects.requireNonNull(leaves, "Leaves block cannot be null");
        return this;
    }

    public TreeFeatureBuilder withTrunkPlacer(TrunkPlacer trunkPlacer) {
        this.trunkPlacer = Objects.requireNonNull(trunkPlacer, "Trunk placer cannot be null");
        return this;
    }

    public TreeFeatureBuilder withFoliagePlacer(FoliagePlacer foliagePlacer) {
        this.foliagePlacer = Objects.requireNonNull(foliagePlacer, "Foliage placer cannot be null");
        return this;
    }

    public TreeFeatureBuilder withFeatureSize(FeatureSize featureSize) {
        this.featureSize = Objects.requireNonNull(featureSize, "Feature size cannot be null");
        return this;
    }

    public TreeFeatureBuilder addDecorator(TreeDecorator decorator) {
        this.decorators.add(Objects.requireNonNull(decorator, "Tree decorator cannot be null"));
        return this;
    }

    public TreeFeatureBuilder withDecorators(List<TreeDecorator> decorators) {
        this.decorators.clear();

        if (decorators != null) {
            this.decorators.addAll(decorators);
        }

        return this;
    }

    public TreeFeatureBuilder addPlacementModifier(PlacementModifier modifier) {
        this.placementModifiers.add(Objects.requireNonNull(modifier, "Placement modifier cannot be null"));
        return this;
    }

    public TreeFeatureBuilder withPlacementModifiers(List<PlacementModifier> modifiers) {
        this.placementModifiers.clear();

        if (modifiers != null) {
            this.placementModifiers.addAll(modifiers);
        }

        return this;
    }

    public TreeFeatureBuilder withBiomeTag(TagKey<Biome> biomeTag) {
        this.biomeTag = Objects.requireNonNull(biomeTag, "Biome tag cannot be null");
        return this;
    }

    public TreeFeatureDefinition build() {
        TreeConfiguration.TreeConfigurationBuilder treeBuilder = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(this.wood.registry.get()), this.trunkPlacer, BlockStateProvider.simple(this.leaves.registry.get()), this.foliagePlacer, this.featureSize);

        if (!this.decorators.isEmpty()) {
            treeBuilder.decorators(List.copyOf(this.decorators));
        }

        return new TreeFeatureDefinition(treeBuilder.build(), List.copyOf(this.placementModifiers), this.biomeTag);
    }

    public record TreeFeatureDefinition(TreeConfiguration configuration, List<PlacementModifier> placementModifiers, TagKey<Biome> biomeTag) {
    }
}
