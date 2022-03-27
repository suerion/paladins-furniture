package com.unlikepaladin.pfm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SimpleLight extends PowerableBlock{
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public SimpleLight(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(LIT,  false).with(POWERLOCKED, false));
    }
    @Override
    public void setPowered(World world, BlockPos lightPos, boolean powered) {
        BlockState state = world.getBlockState(lightPos);
        world.setBlockState(lightPos, state.with(LIT, powered).with(POWERLOCKED, powered));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean powered = ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos());
        return this.getDefaultState().with(LIT, powered);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT) && !world.isReceivingRedstonePower(pos) && !state.get(POWERLOCKED)) {
            world.setBlockState(pos, state.cycle(LIT), Block.NOTIFY_ALL);
        }
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
        builder.add(POWERLOCKED);
    }

    private static final VoxelShape SIMPLE_LIGHT = VoxelShapes.union(createCuboidShape(4.5, 13.5, 4.5,11.5, 14.5, 11.5),createCuboidShape(3, 14.5, 3,13, 16, 13));
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SIMPLE_LIGHT;
    }
}
