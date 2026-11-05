package com.github.syren_dev_tech.scylla.mobs.creatures;

import com.github.syren_dev_tech.hydra.animatable.HydraEntity;
import com.github.syren_dev_tech.hydra.animatable.instance.AnimatableInstanceCache;
import com.github.syren_dev_tech.hydra.animation.AnimationController;
import com.github.syren_dev_tech.hydra.animation.AnimatableManager.ControllerRegistrar;
import com.github.syren_dev_tech.hydra.util.HydraUtil;
import com.github.syren_dev_tech.scylla.ScyllaCommon;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder;
import com.github.syren_dev_tech.scylla.mobs.CreatureBuilder.NbtDataType;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.ShortTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagParser;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.level.Level;

public class CustomCreature extends PathfinderMob implements HydraEntity { // NOSONAR - Ignore parent class limit

    private static final EntityDataAccessor<String> FORCED_ANIMATIONS = SynchedEntityData.defineId(CustomCreature.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> SYNCED_CUSTOM_DATA = SynchedEntityData.defineId(CustomCreature.class, EntityDataSerializers.STRING);
    private static final String CUSTOM_DATA_TAG = "scylla_custom_data";
    private static final Pattern NEWLINE_SPLIT_PATTERN = Pattern.compile("\\n");
    private static final Pattern TAB_SPLIT_PATTERN = Pattern.compile("\\t");
    private static final Pattern PATH_SPLIT_PATTERN = Pattern.compile("\\.");

    private final CreatureBuilder<CustomCreature> builder;
    private final AnimatableInstanceCache cache;
    private final CreatureState<CustomCreature> state;
    private CompoundTag customData = new CompoundTag();

    public CustomCreature(EntityType<? extends PathfinderMob> type, Level worldIn, CreatureBuilder<CustomCreature> builder) {
        super(type, worldIn);

        this.cache = HydraUtil.createInstanceCache(this);
        this.builder = builder;
        this.state = new CreatureState<>(builder);

        this.registerGoalsFromBuilder();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomCreature that = (CustomCreature) obj;

        return this.getUUID().equals(that.getUUID());
    }

    @Override
    public int hashCode() {
        return this.getUUID().hashCode();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public CreatureState<CustomCreature> getState() {
        return this.state;
    }

    public CreatureBuilder<CustomCreature> getBuilder() {
        return builder;
    }

    public void setCustomNbt(String path, Object value) {
        if (path == null || path.isBlank()) {
            return;
        }

        boolean shouldSync = this.builder.getSyncedNbtFields().contains(path);

        NbtDataType expectedType = this.builder.getNbtFieldType(path);
        if (expectedType == null) {
            throw new IllegalArgumentException("No NBT schema field registered for path: " + path);
        }

        if (value == null) {
            this.removeNestedTag(path);
            if (shouldSync) {
                this.syncCustomData();
            }
            return;
        }

        this.putValue(path, expectedType, value);
        if (shouldSync) {
            this.syncCustomData();
        }
    }

    public Object getCustomNbt(String path) {
        if (path == null || path.isBlank()) {
            return null;
        }

        Tag valueTag = this.getNestedTag(path);
        if (valueTag == null) {
            return null;
        }

        NbtDataType expectedType = this.builder.getNbtFieldType(path);
        if (expectedType != null) {
            return this.readValue(expectedType, valueTag);
        }

        return this.readRawValue(valueTag);
    }

    public boolean hasCustomNbt(String path) {
        return this.getNestedTag(path) != null;
    }

    public String getCustomNbtString(String path, String defaultValue) {
        return this.valueOrDefault(path, String.class, defaultValue);
    }

    public int getCustomNbtInt(String path, int defaultValue) {
        Number value = this.valueOrDefault(path, Number.class, null);
        return value != null ? value.intValue() : defaultValue;
    }

    public boolean getCustomNbtBoolean(String path, boolean defaultValue) {
        Boolean value = this.valueOrDefault(path, Boolean.class, null);
        return value != null ? value : defaultValue;
    }

    public float getCustomNbtFloat(String path, float defaultValue) {
        Number value = this.valueOrDefault(path, Number.class, null);
        return value != null ? value.floatValue() : defaultValue;
    }

    public double getCustomNbtDouble(String path, double defaultValue) {
        Number value = this.valueOrDefault(path, Number.class, null);
        return value != null ? value.doubleValue() : defaultValue;
    }

    public long getCustomNbtLong(String path, long defaultValue) {
        Number value = this.valueOrDefault(path, Number.class, null);
        return value != null ? value.longValue() : defaultValue;
    }

    public short getCustomNbtShort(String path, short defaultValue) {
        Number value = this.valueOrDefault(path, Number.class, null);
        return value != null ? value.shortValue() : defaultValue;
    }

    public byte getCustomNbtByte(String path, byte defaultValue) {
        Number value = this.valueOrDefault(path, Number.class, null);
        return value != null ? value.byteValue() : defaultValue;
    }

    public ListTag getCustomNbtList(String path) {
        Object value = this.getCustomNbt(path);
        return value instanceof ListTag listTag ? listTag : null;
    }

    public CompoundTag getCustomNbtCompound(String path) {
        Object value = this.getCustomNbt(path);
        return value instanceof CompoundTag compoundTag ? compoundTag : null;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        if (!this.customData.isEmpty()) {
            tag.put(CUSTOM_DATA_TAG, this.customData.copy());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        this.customData = new CompoundTag();
        if (tag.contains(CUSTOM_DATA_TAG, Tag.TAG_COMPOUND)) {
            this.customData.merge(tag.getCompound(CUSTOM_DATA_TAG));
        }

        this.validateCustomDataAgainstSchema();

        if (!this.level().isClientSide) {
            this.syncCustomData();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FORCED_ANIMATIONS, "");
        builder.define(SYNCED_CUSTOM_DATA, "");
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (FORCED_ANIMATIONS.equals(key) && this.level().isClientSide) {
            this.applySyncedForcedAnimations();
        }

        if (SYNCED_CUSTOM_DATA.equals(key) && this.level().isClientSide) {
            this.applySyncedCustomData();
        }
    }

    public void requestAnimation(String animatorName, String animation, boolean loop) {
        this.getState().setForcedAnimation(animatorName, animation, loop);
        this.syncForcedAnimations();
    }

    public boolean requestAnimation(String animatorName, String animation, boolean loop, Object owner) {
        boolean updated = this.getState().setForcedAnimation(animatorName, animation, loop, owner);

        if (updated) {
            this.syncForcedAnimations();
        }

        return updated;
    }

    public void claimAnimation(String animatorName, Object owner) {
        this.getState().claimForcedAnimationOwner(animatorName, owner);
    }

    public void clearRequestedAnimation(String animatorName) {
        this.getState().clearForcedAnimation(animatorName);
        this.syncForcedAnimations();
    }

    public boolean clearRequestedAnimation(String animatorName, Object owner) {
        boolean updated = this.getState().clearForcedAnimation(animatorName, owner);

        if (updated) {
            this.syncForcedAnimations();
        }

        return updated;
    }

    public void clearAllRequestedAnimations() {
        this.getState().clearAllForcedAnimations();
        this.syncForcedAnimations();
    }

    private void registerGoalsFromBuilder() {
        builder.getGoals().forEach(goalFunc -> {
            var goal = goalFunc.apply(this);
            this.goalSelector.addGoal(goal.getWeight(), goal);
        });
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        builder.getAnimators().forEach((name, handler) -> {
            Animator<CustomCreature> animator = new Animator<>(name, handler);
            this.getState().setAnimator(name, animator);
            controllers.add(new AnimationController<>(this, name, 10, animator::apply));
        });
    }

    private void syncForcedAnimations() {
        if (this.level().isClientSide) {
            return;
        }

        this.entityData.set(FORCED_ANIMATIONS, this.serializeForcedAnimations());
    }

    private void applySyncedForcedAnimations() {
        this.getState().replaceForcedAnimations(this.deserializeForcedAnimations(this.entityData.get(FORCED_ANIMATIONS)));
    }

    private void syncCustomData() {
        if (this.level().isClientSide) {
            return;
        }

        this.entityData.set(SYNCED_CUSTOM_DATA, this.serializeSyncedCustomData());
    }

    private String serializeSyncedCustomData() {
        if (this.builder.getSyncedNbtFields().isEmpty()) {
            return "";
        }

        CompoundTag syncedSubset = new CompoundTag();
        for (String path : this.builder.getSyncedNbtFields()) {
            Tag valueTag = this.getNestedTag(path);
            if (valueTag != null) {
                this.putNestedTag(syncedSubset, path, valueTag.copy());
            }
        }

        return syncedSubset.isEmpty() ? "" : syncedSubset.toString();
    }

    private void applySyncedCustomData() {
        String encoded = this.entityData.get(SYNCED_CUSTOM_DATA);
        if (encoded == null || encoded.isEmpty()) {
            this.clearSyncedCustomDataPaths();
            return;
        }

        try {
            Tag parsed = TagParser.parseTag(encoded);
            if (parsed instanceof CompoundTag syncedSubset) {
                this.mergeSyncedCustomData(syncedSubset);
                return;
            }
        } catch (CommandSyntaxException exception) {
            ScyllaCommon.LOGGER.warn("Failed to parse synced custom NBT for entity '{}': {}", this.getType(), exception.getMessage());
        }

        this.clearSyncedCustomDataPaths();
    }

    private String serializeForcedAnimations() {
        StringBuilder encoded = new StringBuilder();

        for (Map.Entry<String, AnimationDefinition> entry : this.getState().getForcedAnimationsSnapshot().entrySet()) {
            AnimationDefinition definition = entry.getValue();

            if (definition == null || definition.animation() == null || definition.animation().isEmpty()) {
                continue;
            }

            if (!encoded.isEmpty()) {
                encoded.append('\n');
            }

            encoded.append(entry.getKey()).append('\t').append(definition.animation()).append('\t').append(definition.loop() ? '1' : '0');
        }

        return encoded.toString();
    }

    private Map<String, AnimationDefinition> deserializeForcedAnimations(String encoded) {
        Map<String, AnimationDefinition> animations = new HashMap<>();

        if (encoded == null || encoded.isEmpty()) {
            return animations;
        }

        for (String entry : NEWLINE_SPLIT_PATTERN.split(encoded)) {
            if (!entry.isEmpty()) {
                String[] parts = TAB_SPLIT_PATTERN.split(entry, 3);
                if (parts.length >= 3 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                    animations.put(parts[0], new AnimationDefinition(parts[1], "1".equals(parts[2])));
                }
            }
        }

        return animations;
    }

    private void validateCustomDataAgainstSchema() {
        for (Map.Entry<String, NbtDataType> entry : this.builder.getNbtSchema().entrySet()) {
            String path = entry.getKey();
            NbtDataType expectedType = entry.getValue();
            Tag valueTag = this.getNestedTag(path);

            if (valueTag != null && !this.isTagTypeCompatible(expectedType, valueTag)) {
                ScyllaCommon.LOGGER.warn("Removing invalid custom NBT '{}' on entity '{}' (expected {}, got tag id {})", path, this.getType(), expectedType, valueTag.getId());
                this.removeNestedTag(path);
            }
        }
    }

    private void mergeSyncedCustomData(CompoundTag syncedSubset) {
        for (String path : this.builder.getSyncedNbtFields()) {
            Tag valueTag = this.getNestedTag(syncedSubset, path);
            NbtDataType expectedType = this.builder.getNbtFieldType(path);

            if (valueTag == null || expectedType == null || !this.isTagTypeCompatible(expectedType, valueTag)) {
                this.removeNestedTag(this.customData, path);
                continue;
            }

            this.putNestedTag(this.customData, path, valueTag.copy());
        }
    }

    private void clearSyncedCustomDataPaths() {
        for (String path : this.builder.getSyncedNbtFields()) {
            this.removeNestedTag(this.customData, path);
        }
    }

    private <V> V valueOrDefault(String path, Class<V> type, V defaultValue) {
        Object value = this.getCustomNbt(path);
        if (type.isInstance(value)) {
            return type.cast(value);
        }

        return defaultValue;
    }

    private void putValue(String path, NbtDataType expectedType, Object value) {
        this.putNestedTag(path, this.createTypedTag(path, expectedType, value));
    }

    private Tag createTypedTag(String path, NbtDataType expectedType, Object value) {
        return switch (expectedType) {
            case STRING -> StringTag.valueOf(this.requireString(path, expectedType, value));
            case INTEGER -> IntTag.valueOf(this.requireNumber(path, expectedType, value).intValue());
            case BOOLEAN -> ByteTag.valueOf(this.requireBoolean(path, expectedType, value));
            case FLOAT -> FloatTag.valueOf(this.requireNumber(path, expectedType, value).floatValue());
            case DOUBLE -> DoubleTag.valueOf(this.requireNumber(path, expectedType, value).doubleValue());
            case LONG -> LongTag.valueOf(this.requireNumber(path, expectedType, value).longValue());
            case SHORT -> ShortTag.valueOf(this.requireNumber(path, expectedType, value).shortValue());
            case BYTE -> ByteTag.valueOf(this.requireNumber(path, expectedType, value).byteValue());
            case LIST -> this.requireListTag(path, expectedType, value).copy();
            case COMPOUND -> this.requireCompoundTag(path, expectedType, value).copy();
        };
    }

    private String requireString(String path, NbtDataType expectedType, Object value) {
        if (value instanceof String stringValue) {
            return stringValue;
        }
        throw this.invalidNbtValue(path, expectedType);
    }

    private Number requireNumber(String path, NbtDataType expectedType, Object value) {
        if (value instanceof Number numberValue) {
            return numberValue;
        }
        throw this.invalidNbtValue(path, expectedType);
    }

    private boolean requireBoolean(String path, NbtDataType expectedType, Object value) {
        if (value instanceof Boolean booleanValue) {
            return booleanValue;
        }
        throw this.invalidNbtValue(path, expectedType);
    }

    private ListTag requireListTag(String path, NbtDataType expectedType, Object value) {
        if (value instanceof ListTag listTag) {
            return listTag;
        }
        throw this.invalidNbtValue(path, expectedType);
    }

    private CompoundTag requireCompoundTag(String path, NbtDataType expectedType, Object value) {
        if (value instanceof CompoundTag compoundTag) {
            return compoundTag;
        }
        throw this.invalidNbtValue(path, expectedType);
    }

    private IllegalArgumentException invalidNbtValue(String path, NbtDataType expectedType) {
        return new IllegalArgumentException("NBT field '" + path + "' expects " + expectedType);
    }

    private Object readValue(NbtDataType expectedType, Tag valueTag) {
        return switch (expectedType) {
            case STRING -> ((StringTag) valueTag).getAsString();
            case INTEGER -> ((NumericTag) valueTag).getAsInt();
            case BOOLEAN -> ((NumericTag) valueTag).getAsByte() != 0;
            case FLOAT -> ((NumericTag) valueTag).getAsFloat();
            case DOUBLE -> ((NumericTag) valueTag).getAsDouble();
            case LONG -> ((NumericTag) valueTag).getAsLong();
            case SHORT -> ((NumericTag) valueTag).getAsShort();
            case BYTE -> ((NumericTag) valueTag).getAsByte();
            case LIST -> valueTag;
            case COMPOUND -> valueTag;
        };
    }

    private Object readRawValue(Tag valueTag) {
        if (valueTag instanceof StringTag stringTag) {
            return stringTag.getAsString();
        }

        if (valueTag instanceof NumericTag numericTag) {
            return numericTag.getAsNumber();
        }

        return valueTag;
    }

    private boolean isTagTypeCompatible(NbtDataType expectedType, Tag valueTag) {
        return switch (expectedType) {
            case STRING -> valueTag.getId() == Tag.TAG_STRING;
            case INTEGER -> valueTag instanceof NumericTag;
            case BOOLEAN, BYTE -> valueTag.getId() == Tag.TAG_BYTE;
            case FLOAT -> valueTag.getId() == Tag.TAG_FLOAT;
            case DOUBLE -> valueTag.getId() == Tag.TAG_DOUBLE;
            case LONG -> valueTag.getId() == Tag.TAG_LONG;
            case SHORT -> valueTag.getId() == Tag.TAG_SHORT;
            case LIST -> valueTag.getId() == Tag.TAG_LIST;
            case COMPOUND -> valueTag.getId() == Tag.TAG_COMPOUND;
        };
    }

    private Tag getNestedTag(String path) {
        return this.getNestedTag(this.customData, path);
    }

    private void putNestedTag(String path, Tag value) {
        this.putNestedTag(this.customData, path, value);
    }

    private void removeNestedTag(String path) {
        this.removeNestedTag(this.customData, path);
    }

    private Tag getNestedTag(CompoundTag root, String path) {
        String[] parts = PATH_SPLIT_PATTERN.split(path);
        CompoundTag current = root;

        for (int i = 0; i < parts.length - 1; i++) {
            if (!current.contains(parts[i], Tag.TAG_COMPOUND)) {
                return null;
            }
            current = current.getCompound(parts[i]);
        }

        String leaf = parts[parts.length - 1];
        if (!current.contains(leaf)) {
            return null;
        }

        return current.get(leaf);
    }

    private void putNestedTag(CompoundTag root, String path, Tag value) {
        String[] parts = PATH_SPLIT_PATTERN.split(path);
        CompoundTag current = root;

        for (int i = 0; i < parts.length - 1; i++) {
            String part = parts[i];
            if (!current.contains(part, Tag.TAG_COMPOUND)) {
                current.put(part, new CompoundTag());
            }
            current = current.getCompound(part);
        }

        current.put(parts[parts.length - 1], value);
    }

    private void removeNestedTag(CompoundTag root, String path) {
        String[] parts = PATH_SPLIT_PATTERN.split(path);
        CompoundTag current = root;

        for (int i = 0; i < parts.length - 1; i++) {
            if (!current.contains(parts[i], Tag.TAG_COMPOUND)) {
                return;
            }
            current = current.getCompound(parts[i]);
        }

        current.remove(parts[parts.length - 1]);
    }

}
