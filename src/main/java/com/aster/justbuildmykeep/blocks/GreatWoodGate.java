package com.aster.justbuildmykeep.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class GreatWoodGate extends DoorBlock {
    private static final VoxelShape shape;
    static {
        VoxelShape base = Block.makeCuboidShape(0, 0, 0, 32, 32, 4);
        //VoxelShape column1 = Block.makeCuboidShape(0, 32, 0, 32, 64, 4);
        shape = VoxelShapes.or(base);
    }
    public GreatWoodGate(){
        super(Properties.create(Material.ROCK).hardnessAndResistance(5).notSolid());
        setRegistryName("great_wood_gate");
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }
    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return shape;
    }


}
