package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoneBrickPillarMiddle extends BaseHorizontalWaterloggedBlock{

    private static IntegerProperty STATE = IntegerProperty.create("vertical", 0, 1);



    public StoneBrickPillarMiddle() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        this.setDefaultState(
                this.getStateContainer().getBaseState()
                        .with(DIRECTION, Direction.NORTH)
                        .with(STATE,0));
        setRegistryName("stone_brick_pillar_middle");
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STATE);
        super.fillStateContainer(builder);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        Block upBlock = worldIn.getBlockState(pos.up(1)).getBlock();
        Block downBlock = worldIn.getBlockState(pos.down(1)).getBlock();
        if(!upBlock.getRegistryName().toString().equals("minecraft:air")){
            worldIn.setBlockState(pos,this.getStateContainer().getBaseState().with(STATE,1).with(WATERLOGGED,state.get(WATERLOGGED)).with(DIRECTION, state.get(DIRECTION)));
        }else {
            worldIn.setBlockState(pos,this.getStateContainer().getBaseState().with(STATE,0).with(WATERLOGGED,state.get(WATERLOGGED)).with(DIRECTION, state.get(DIRECTION)));
        }

        if(downBlock.getRegistryName().toString().equals("justbuildmykeep:stone_brick_pillar_middle")){
            BlockState downBlockS = worldIn.getBlockState(pos.down(1));
            worldIn.setBlockState(pos.down(1),this.getStateContainer().getBaseState().with(STATE,1).with(WATERLOGGED,downBlockS.get(WATERLOGGED)).with(DIRECTION, downBlockS.get(DIRECTION)));
        }

    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        Block upBlock = worldIn.getBlockState(pos.up(1)).getBlock();
        Block downBlock = worldIn.getBlockState(pos.down(1)).getBlock();
        if(!upBlock.getRegistryName().toString().equals("minecraft:air")){
            worldIn.setBlockState(pos,this.getStateContainer().getBaseState().with(STATE,1).with(WATERLOGGED,state.get(WATERLOGGED)).with(DIRECTION, state.get(DIRECTION)));
        }else {
            worldIn.setBlockState(pos,this.getStateContainer().getBaseState().with(STATE,0).with(WATERLOGGED,state.get(WATERLOGGED)).with(DIRECTION, state.get(DIRECTION)));
        }

        if(downBlock.getRegistryName().toString().equals("justbuildmykeep:stone_brick_pillar_middle")){
            Block downBlockUp=downBlock.getBlock();
            BlockState downBlockS = worldIn.getBlockState(pos.down(1));
            worldIn.setBlockState(pos.down(1),this.getStateContainer().getBaseState().with(STATE,1).with(WATERLOGGED,downBlockS.get(WATERLOGGED)).with(DIRECTION, downBlockS.get(DIRECTION)));
            System.out.println(downBlockUp);
        }
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        final VoxelShape[] BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(4, 0, 0, 12, 16, 8),Direction.SOUTH));
        Direction direction = state.get(DIRECTION);
        return BASE[direction.getHorizontalIndex()];

    }


    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }


}
