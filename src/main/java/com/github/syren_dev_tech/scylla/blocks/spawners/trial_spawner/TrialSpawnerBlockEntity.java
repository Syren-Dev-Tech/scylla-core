package com.github.syren_dev_tech.scylla.blocks.spawners.trial_spawner;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TrialSpawnerBlockEntity extends BlockEntity {
    private static final String CONFIG_ID_TAG = "config_id";
    private static final String CURRENT_WAVE_TAG = "current_wave";
    private static final String IS_SPAWNING_TAG = "is_spawning";
    private static final Map<ResourceLocation, TrialSpawnerConfig> CONFIGS = new HashMap<>();

    private ResourceLocation configId;
    private int currentWave = 0;
    private boolean isSpawning = false;

    public TrialSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(null, pos, state);
    }

    public static void registerConfig(ResourceLocation id, TrialSpawnerConfig config) {
        CONFIGS.put(id, config);
    }

    public void setConfig(ResourceLocation configId) {
        this.configId = configId;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (configId != null) {
            tag.putString(CONFIG_ID_TAG, configId.toString());
        }
        tag.putInt(CURRENT_WAVE_TAG, currentWave);
        tag.putBoolean(IS_SPAWNING_TAG, isSpawning);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains(CONFIG_ID_TAG, Tag.TAG_STRING)) {
            this.configId = ResourceLocation.tryParse(tag.getString(CONFIG_ID_TAG));
        }
        this.currentWave = tag.getInt(CURRENT_WAVE_TAG);
        this.isSpawning = tag.getBoolean(IS_SPAWNING_TAG);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (!(level instanceof ServerLevel serverLevel) || configId == null) {
            return;
        }

        var config = resolveConfig(configId);
        if (config.isEmpty()) {
            return;
        }

        if (!isSpawning && checkTrigger(level, pos, config.get().triggerType())) {
            startNextWave(serverLevel, pos, config.get());
        }
    }

    private Optional<TrialSpawnerConfig> resolveConfig(ResourceLocation configId) {
        return Optional.ofNullable(CONFIGS.get(configId));
    }

    private boolean checkTrigger(Level level, BlockPos pos, TrialSpawnerConfig.TriggerType type) {
        return switch (type) {
            case PROXIMITY -> !level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(5.0D)).isEmpty();
            case REDSTONE -> level.hasNeighborSignal(pos);
            case INTERACTION -> false;
        };
    }

    private void startNextWave(ServerLevel level, BlockPos pos, TrialSpawnerConfig config) {
        isSpawning = true;
        currentWave = Math.min(currentWave + 1, config.totalWaves());

        for (TrialSpawnerConfig.SpawnEntry entry : config.spawns()) {
            for (int i = 0; i < entry.count(); i++) {
                spawnEntity(level, pos, entry.entityId());
            }
        }
    }

    private void spawnEntity(ServerLevel level, BlockPos pos, ResourceLocation entityId) {
        EntityType<?> entityType = BuiltInRegistries.ENTITY_TYPE.get(entityId);
        if (entityType == null) {
            return;
        }

        var entity = entityType.create(level);
        if (entity == null) {
            return;
        }

        double offsetX = pos.getX() + 0.5D + (Math.random() - 0.5D) * 2.0D;
        double offsetY = pos.getY() + 1.0D;
        double offsetZ = pos.getZ() + 0.5D + (Math.random() - 0.5D) * 2.0D;
        entity.setPos(offsetX, offsetY, offsetZ);
        level.addFreshEntity(entity);
    }

    public void triggerManual() {
        this.isSpawning = true;
        this.currentWave = 1;
    }
}
