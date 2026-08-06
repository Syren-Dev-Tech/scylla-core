package com.github.syren_dev_tech.scylla.husbandry.types;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import com.github.syren_dev_tech.scylla.files.ResourcePath;
import com.github.syren_dev_tech.scylla.registry.definitions.BlockDefinition;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EggData<T extends Mob> {

    private final Supplier<EntityType<T>> entityType;
    private Set<BlockDefinition<?>> nestingBlocks = Set.of();

    private SoundEvent hatchSound;
    private SoundEvent crackSound;

    private int maxHatchLevel = 2;
    private IntegerProperty hatch = IntegerProperty.create("hatch", 0, maxHatchLevel);

    private int regularHatchTimeTicks = 24000;
    private int boostedHatchTimeTicks = 12000;
    private int randomHatchOffsetTicks = 300;

    private double minTimeOfDay = 0.65D;
    private double maxTimeOfDay = 0.69D;
    private int randHatchChance = 500;

    private VoxelShape shape = Block.box(1.0F, 0.0F, 2.0F, 15.0F, 16.0F, 14.0F);

    private TagKey<Block> boost;

    public EggData(Supplier<EntityType<T>> entityType) {
        this.entityType = entityType;
    }

    public EggData<T> addNestingBlock(BlockDefinition<?> block) {
        this.nestingBlocks.add(block);
        return this;
    }

    public boolean isNestingBlock(Block block) {
        return this.nestingBlocks.stream().anyMatch(blockDef -> blockDef.registry.get().equals(block));
    }

    public TagKey<Block> getBoost() {
        return boost;
    }

    public EggData<T> setBoost(TagKey<Block> boost) {
        this.boost = boost;
        return this;
    }

    public EggData<T> withBoost(String namespace) {
        this.boost = TagKey.create(Registries.BLOCK, new ResourcePath(namespace, "egg_hatch_boosters").get());
        return this;
    }

    public SoundEvent getHatchSound() {
        return hatchSound;
    }

    public EggData<T> setHatchSound(SoundEvent hatchSound) {
        this.hatchSound = hatchSound;
        return this;
    }

    public SoundEvent getCrackSound() {
        return crackSound;
    }

    public EggData<T> setCrackSound(SoundEvent crackSound) {
        this.crackSound = crackSound;
        return this;
    }

    public int getMaxHatchLevel() {
        return maxHatchLevel;
    }

    public EggData<T> setMaxHatchLevel(int maxHatchLevel) {
        this.maxHatchLevel = maxHatchLevel;
        this.hatch = IntegerProperty.create("hatch", 0, maxHatchLevel);
        return this;
    }

    public IntegerProperty getHatch() {
        return hatch;
    }

    public int getRegularHatchTimeTicks() {
        return regularHatchTimeTicks;
    }

    public EggData<T> setRegularHatchTimeTicks(int regularHatchTimeTicks) {
        this.regularHatchTimeTicks = regularHatchTimeTicks;
        return this;
    }

    public int getBoostedHatchTimeTicks() {
        return boostedHatchTimeTicks;
    }

    public EggData<T> setBoostedHatchTimeTicks(int boostedHatchTimeTicks) {
        this.boostedHatchTimeTicks = boostedHatchTimeTicks;
        return this;
    }

    public int getRandomHatchOffsetTicks() {
        return randomHatchOffsetTicks;
    }

    public EggData<T> setRandomHatchOffsetTicks(int randomHatchOffsetTicks) {
        this.randomHatchOffsetTicks = randomHatchOffsetTicks;
        return this;
    }

    public VoxelShape getShape() {
        return shape;
    }

    public EggData<T> setShape(VoxelShape shape) {
        this.shape = shape;
        return this;
    }

    public EntityType<T> getEntityType() {
        return entityType.get();
    }

    public double getMinTimeOfDay() {
        return minTimeOfDay;
    }

    public double getMaxTimeOfDay() {
        return maxTimeOfDay;
    }

    public int getRandHatchChance() {
        return randHatchChance;
    }

    public EggData<T> setRandHatchChance(int randHatchChance) {
        this.randHatchChance = randHatchChance;
        return this;
    }

    public EggData<T> setTimeOfDayRange(double min, double max) {
        this.minTimeOfDay = min;
        this.maxTimeOfDay = max;
        return this;
    }

    public T spawn(ServerLevel serverLevel) {
        return this.spawn(serverLevel, creature -> {
            // No-op
        });
    }

    public T spawn(ServerLevel serverLevel, Consumer<T> beforeSummon) {
        T creature = this.getEntityType().create(serverLevel);

        if (creature != null) {
            creature.setBaby(true);
            beforeSummon.accept(creature);
            serverLevel.addFreshEntity(creature);
        }

        return creature;
    }
}
