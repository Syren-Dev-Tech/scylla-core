package com.github.syren_dev_tech.scylla.dimensions.portals;

import java.util.Optional;
import com.github.syren_dev_tech.scylla.ScyllaCommon;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VerticalPortal extends Block implements Portal {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final int AABB_OFFSET = 2;
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    // This should be a set:
    private EntityType<?>[] spawnableEntities = new EntityType<?>[] {};
    private SoundEvent ambientSound = SoundEvents.PORTAL_AMBIENT;
    private ParticleOptions particles = ParticleTypes.PORTAL;

    private final Level oldLevel;
    private final Level newLevel;

    public VerticalPortal(Level oldLevel, Level newLevel, Properties properties) {
        super(properties);

        this.oldLevel = oldLevel;
        this.newLevel = newLevel;

        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) { // NOSONAR - Ignore deprecation warning
        if (blockState.getValue(AXIS) == Direction.Axis.Z)
            return Z_AXIS_AABB;

        return X_AXIS_AABB;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) { // NOSONAR - Ignore deprecation warning
        if (spawnableEntities.length == 0)
            return;

        boolean spawningAllowed = serverLevel.dimensionType().natural() && serverLevel.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING);
        boolean onRand = randomSource.nextInt(2000) < serverLevel.getDifficulty().getId();

        if (spawningAllowed && onRand) {
            while (serverLevel.getBlockState(blockPos).is(this))
                blockPos = blockPos.below();

            EntityType<?> entityToSpawn = this.spawnableEntities[randomSource.nextInt(spawnableEntities.length)];

            if (serverLevel.getBlockState(blockPos).isValidSpawn(serverLevel, blockPos, entityToSpawn)) {
                Entity entity = entityToSpawn.spawn(serverLevel, blockPos.above(), MobSpawnType.STRUCTURE);

                if (entity != null)
                    entity.setPortalCooldown();
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState sourceBlockState, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPosY) { // NOSONAR - Ignore deprecation warning
        Direction.Axis directionAxis = direction.getAxis();
        Direction.Axis directionAxis1 = blockState.getValue(AXIS);

        boolean flag = directionAxis1 != directionAxis && directionAxis.isHorizontal();

        return !flag && !sourceBlockState.is(this) && !(new PortalShape(levelAccessor, blockPos, directionAxis1)).isComplete() ? Blocks.AIR.defaultBlockState() : blockState;
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (entity.canChangeDimensions(oldLevel, newLevel) && entity.canUsePortal(false))
            entity.setAsInsidePortal(this, blockPos);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(100) == 0) {
            level.playLocalSound(blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D, this.ambientSound, SoundSource.BLOCKS, 0.5F, randomSource.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i) {
            double d0 = blockPos.getX() + randomSource.nextDouble();
            double d1 = blockPos.getY() + randomSource.nextDouble();
            double d2 = blockPos.getZ() + randomSource.nextDouble();
            double d3 = (randomSource.nextFloat() - 0.5D) * 0.5D;
            double d4 = (randomSource.nextFloat() - 0.5D) * 0.5D;
            double d5 = (randomSource.nextFloat() - 0.5D) * 0.5D;
            int j = randomSource.nextInt(2) * 2 - 1;
            if (!level.getBlockState(blockPos.west()).is(this) && !level.getBlockState(blockPos.east()).is(this)) {
                d0 = blockPos.getX() + 0.5D + 0.25D * j;
                d3 = (randomSource.nextFloat() * 2.0F * j);
            } else {
                d2 = blockPos.getZ() + 0.5D + 0.25D * j;
                d5 = (randomSource.nextFloat() * 2.0F * j);
            }

            level.addParticle(this.particles, d0, d1, d2, d3, d4, d5);
        }
    }

    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90:
                switch (blockState.getValue(AXIS)) {
                    case Z:
                        return blockState.setValue(AXIS, Direction.Axis.X);
                    case X:
                        return blockState.setValue(AXIS, Direction.Axis.Z);
                    default:
                        return blockState;
                }
            default:
                return blockState;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    private static DimensionTransition createDimensionTransition(ServerLevel level, BlockUtil.FoundRectangle rectangle, Direction.Axis axis, Vec3 offset, Entity entity, Vec3 speed, float yRot, float xRot, DimensionTransition.PostDimensionTransition postDimensionTransition) {
        BlockPos blockpos = rectangle.minCorner;
        BlockState blockstate = level.getBlockState(blockpos);
        Direction.Axis directionAxis = blockstate.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Axis.X);
        double d0 = rectangle.axis1Size;
        double d1 = rectangle.axis2Size;
        EntityDimensions entitydimensions = entity.getDimensions(entity.getPose());
        int i = axis == directionAxis ? 0 : 90;
        Vec3 vec3 = axis == directionAxis ? speed : new Vec3(speed.z, speed.y, -speed.x);
        double d2 = entitydimensions.width() / 2.0 + (d0 - entitydimensions.width()) * offset.x();
        double d3 = (d1 - entitydimensions.height()) * offset.y();
        double d4 = 0.5 + offset.z();
        boolean flag = directionAxis == Axis.X;
        Vec3 vec31 = new Vec3(blockpos.getX() + (flag ? d2 : d4), blockpos.getY() + d3, blockpos.getZ() + (flag ? d4 : d2));
        Vec3 vec32 = PortalShape.findCollisionFreePosition(vec31, level, entity, entitydimensions);

        return new DimensionTransition(level, vec32, vec3, yRot + i, xRot, postDimensionTransition);
    }

    private static DimensionTransition getDimensionTransitionFromExit(Entity entity, BlockPos pos, BlockUtil.FoundRectangle rectangle, ServerLevel level, DimensionTransition.PostDimensionTransition postDimensionTransition) {
        BlockState blockstate = entity.level().getBlockState(pos);
        Direction.Axis directionAxis;
        Vec3 vec3;

        if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            directionAxis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            BlockUtil.FoundRectangle foundrectangle = BlockUtil.getLargestRectangleAround(pos, directionAxis, 21, Axis.Y, 21, (blockStatePos) -> {
                return entity.level().getBlockState(blockStatePos) == blockstate;
            });
            vec3 = entity.getRelativePortalPosition(directionAxis, foundrectangle);
        } else {
            directionAxis = Axis.X;
            vec3 = new Vec3(0.5, 0.0, 0.0);
        }

        return createDimensionTransition(level, rectangle, directionAxis, vec3, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), postDimensionTransition);
    }

    private DimensionTransition getExitPortal(ServerLevel level, Entity entity, BlockPos pos, BlockPos exitPos, boolean isNether, WorldBorder worldBorder) {
        Optional<BlockPos> optional = level.getPortalForcer().findClosestPortalPosition(exitPos, isNether, worldBorder);
        BlockUtil.FoundRectangle foundrectangle;
        DimensionTransition.PostDimensionTransition postdimensiontransition;
        if (optional.isPresent()) {
            BlockPos blockpos = optional.get();
            BlockState blockstate = level.getBlockState(blockpos);
            foundrectangle = BlockUtil.getLargestRectangleAround(blockpos, (Direction.Axis) blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Axis.Y, 21, (blockStatePos) -> {
                return level.getBlockState(blockStatePos) == blockstate;
            });
            postdimensiontransition = DimensionTransition.PLAY_PORTAL_SOUND.then((transition) -> {
                transition.placePortalTicket(blockpos);
            });
        } else {
            Direction.Axis directionAxis = (Direction.Axis) entity.level().getBlockState(pos).getOptionalValue(AXIS).orElse(Axis.X);
            Optional<BlockUtil.FoundRectangle> optional1 = level.getPortalForcer().createPortal(exitPos, directionAxis);
            if (optional1.isEmpty()) {
                ScyllaCommon.LOGGER.error("Unable to create a portal, likely target out of worldborder");

                return null;
            }

            foundrectangle = optional1.get();
            postdimensiontransition = DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET);
        }

        return getDimensionTransitionFromExit(entity, pos, foundrectangle, level, postdimensiontransition);
    }

    @Override
    public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        ResourceKey<Level> resourcekey = level.dimension() == Level.NETHER ? Level.OVERWORLD : Level.NETHER;
        ServerLevel serverlevel = level.getServer().getLevel(resourcekey);
        if (serverlevel == null) {
            return null;
        } else {
            boolean flag = serverlevel.dimension() == Level.NETHER;
            WorldBorder worldborder = serverlevel.getWorldBorder();
            double d0 = DimensionType.getTeleportationScale(level.dimensionType(), serverlevel.dimensionType());
            BlockPos blockpos = worldborder.clampToBounds(entity.getX() * d0, entity.getY(), entity.getZ() * d0);

            return this.getExitPortal(serverlevel, entity, pos, blockpos, flag, worldborder);
        }
    }
}
