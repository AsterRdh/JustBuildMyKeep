package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import net.minecraft.util.math.shapes.VoxelShape;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.ArrayList;
import java.util.List;

public class BattlementBottomCorner extends BaseHorizontalBlock{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states){

        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 0,8, 16, 8),Direction.EAST));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(8, 0, 8,16, 16, 16),Direction.EAST));
        VoxelShape[] BASE3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(8, 0, 4,12, 16, 8),Direction.EAST));
        VoxelShape[] BASE4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(4, 0, 8,8, 16, 12),Direction.EAST));
        VoxelShape[] BASE5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(16, 0, 12,20, 16, 16),Direction.EAST));
        VoxelShape[] BASE6 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, -4,4, 16, 0),Direction.EAST));
        VoxelShape[] BASE7 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(12, 0, 16,16, 16, 20),Direction.EAST));
        VoxelShape[] BASE8 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(-4, 0, 0,0, 16, 4),Direction.EAST));


        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.get(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(BASE1[direction.getHorizontalIndex()]);
            shapes.add(BASE2[direction.getHorizontalIndex()]);
            shapes.add(BASE3[direction.getHorizontalIndex()]);
            shapes.add(BASE4[direction.getHorizontalIndex()]);
            shapes.add(BASE5[direction.getHorizontalIndex()]);
            shapes.add(BASE6[direction.getHorizontalIndex()]);
            shapes.add(BASE7[direction.getHorizontalIndex()]);
            shapes.add(BASE8[direction.getHorizontalIndex()]);
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }

        return builder.build();
    }


    public BattlementBottomCorner() {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(5.0f)
        );
        SHAPES=blockBuilder(this.getStateContainer().getValidStates());
        this.setDefaultState(this.getStateContainer().getBaseState().with(DIRECTION, Direction.NORTH));
        setRegistryName("battlement_bottom_corner");
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return SHAPES.get(state);
    }
    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return SHAPES.get(state);
    }
}
