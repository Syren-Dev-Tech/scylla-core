package com.github.syren_dev_tech.scylla.common.dimensions.portals;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.EndPlatformFeature;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HorizontalPortal extends BaseEntityBlock implements Portal {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 6.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    private ResourceKey<Level> dimension;

    public HorizontalPortal(Properties properties, ResourceKey<Level> dimension) {
        super(properties);
        this.dimension = dimension;
    }

    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TheEndPortalBlockEntity(blockPos, blockState);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (entity.canUsePortal(false) && Shapes.joinIsNotEmpty(Shapes.create(entity.getBoundingBox().move((-blockPos.getX()), (-blockPos.getY()), (-blockPos.getZ()))), blockState.getShape(level, blockPos), BooleanOp.AND)) {
            if (!level.isClientSide && level.dimension() == Level.END && entity instanceof ServerPlayer serverplayer && !serverplayer.seenCredits) {
                serverplayer.showEndCredits();
                return;
            }

            entity.setAsInsidePortal(this, blockPos);
        }
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        double d0 = blockPos.getX() + randomSource.nextDouble();
        double d1 = blockPos.getY() + 0.8D;
        double d2 = blockPos.getZ() + randomSource.nextDouble();

        level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, Fluid fluid) {
        return false;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(EndPortalBlock::new);
    }

    @Override
    public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        ResourceKey<Level> resourcekey = level.dimension() == Level.END ? Level.OVERWORLD : Level.END;
        ServerLevel serverlevel = level.getServer().getLevel(resourcekey);
        if (serverlevel == null) {
            return null;
        } else {
            boolean flag = resourcekey == Level.END;
            BlockPos blockpos = flag ? ServerLevel.END_SPAWN_POINT : serverlevel.getSharedSpawnPos();
            Vec3 vec3 = blockpos.getBottomCenter();
            float f = entity.getYRot();
            if (flag) {
                EndPlatformFeature.createEndPlatform(serverlevel, BlockPos.containing(vec3).below(), true);
                f = Direction.WEST.toYRot();
                if (entity instanceof ServerPlayer) {
                    vec3 = vec3.subtract(0.0, 1.0, 0.0);
                }
            } else {
                if (entity instanceof ServerPlayer serverplayer) {
                    return serverplayer.findRespawnPositionAndUseSpawnBlock(false, DimensionTransition.DO_NOTHING);
                }

                vec3 = entity.adjustSpawnLocation(serverlevel, blockpos).getBottomCenter();
            }

            return new DimensionTransition(serverlevel, vec3, entity.getDeltaMovement(), f, entity.getXRot(), DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET));
        }
    }
}
