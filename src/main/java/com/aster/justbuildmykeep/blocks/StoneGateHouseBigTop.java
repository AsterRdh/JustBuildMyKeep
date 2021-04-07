package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.ArrayList;
import java.util.List;

public class StoneGateHouseBigTop extends BaseHorizontalWaterloggedBlock{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;
    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states) {

        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(9, 10, 0, 16, 12, 16), Direction.EAST));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(14, 5, 0, 16, 10, 16), Direction.EAST));
        VoxelShape[] BASE3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 12, 0, 16, 16, 16), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.get(DIRECTION);

            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(BASE1[direction.getHorizontalIndex()]);
            shapes.add(BASE2[direction.getHorizontalIndex()]);
            shapes.add(BASE3[direction.getHorizontalIndex()]);
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }

        return builder.build();
    }

    public StoneGateHouseBigTop(){
        super(AbstractBlock.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        setRegistryName("stone_gatehouse_big_top");
        SHAPES=blockBuilder(this.getStateContainer().getValidStates());
        this.setDefaultState(
                this.stateContainer.getBaseState()
                        .with(DIRECTION, Direction.NORTH)
                        .with(WATERLOGGED, Boolean.valueOf(false))
        );
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return SHAPES.get(state);
    }
}
