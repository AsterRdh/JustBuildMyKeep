package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
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

public class StoneDoorFrameL extends BaseHorizontalWaterloggedBlock{
    public StoneDoorFrameL() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        this.setDefaultState(this.getStateContainer().getBaseState().with(DIRECTION, Direction.NORTH));
        setRegistryName("stone_door_frame_left");
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        final VoxelShape[] BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 0, 16, 16, 16),Direction.WEST));
        Direction direction = state.get(DIRECTION);
        return BASE[direction.getHorizontalIndex()];
    }
}
