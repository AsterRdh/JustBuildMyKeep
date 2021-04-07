package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.AbstractBlock;
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

public class LoopHoleBig extends BaseHorizontalBlock{

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states){
        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 9, 16, 16, 16),Direction.NORTH));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(2, 0, 7, 14, 16, 11),Direction.NORTH));
        VoxelShape[] BASE3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(4, 0, 5, 12, 16, 9),Direction.NORTH));
        VoxelShape[] BASE4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(6, 0, 3, 10, 16, 7),Direction.NORTH));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.get(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(BASE1[direction.getHorizontalIndex()]);
            shapes.add(BASE2[direction.getHorizontalIndex()]);
            shapes.add(BASE3[direction.getHorizontalIndex()]);
            shapes.add(BASE4[direction.getHorizontalIndex()]);
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }

        return builder.build();
    }

    public LoopHoleBig() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        SHAPES=blockBuilder(this.getStateContainer().getValidStates());
        this.setDefaultState(this.getStateContainer().getBaseState().with(DIRECTION, Direction.NORTH));

        setRegistryName("loophole_big");
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
