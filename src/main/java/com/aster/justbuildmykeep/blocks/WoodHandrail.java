package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class WoodHandrail extends BaseHorizontalWaterloggedBlock{

    private static BooleanProperty IS_CORNER = BooleanProperty.create("is_corner");
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states){
        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 0, 16, 2, 5),Direction.SOUTH));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 14, 0, 16, 16, 5),Direction.SOUTH));
        VoxelShape[] BASE3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 2, 1, 1.5, 14, 4),Direction.SOUTH));

        VoxelShape[] BASE4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(14.5, 2, 1, 16, 14, 4),Direction.SOUTH));
        VoxelShape[] BASE5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(6.5, 2, 1, 9.5, 14, 4),Direction.SOUTH));

        VoxelShape[] BASE6 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(11, 14, 5, 16, 16, 16),Direction.SOUTH));
        VoxelShape[] BASE7 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(12, 2, 14.5, 15, 14, 16),Direction.SOUTH));
        VoxelShape[] BASE8 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(12, 2, 7.5, 15, 14, 10.5),Direction.SOUTH));
        VoxelShape[] BASE9 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(11, 0, 5, 16, 2, 16),Direction.SOUTH));

        VoxelShape[] BASE10 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(12, 2, 1, 15, 14, 4),Direction.SOUTH));
        VoxelShape[] BASE11 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(5.5, 2, 1, 8.5, 14, 4),Direction.SOUTH));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.get(DIRECTION);
            Boolean isC=state.get(IS_CORNER);

            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(BASE1[direction.getHorizontalIndex()]);
            shapes.add(BASE2[direction.getHorizontalIndex()]);
            shapes.add(BASE3[direction.getHorizontalIndex()]);
            if(isC){
                shapes.add(BASE6[direction.getHorizontalIndex()]);
                shapes.add(BASE7[direction.getHorizontalIndex()]);
                shapes.add(BASE8[direction.getHorizontalIndex()]);
                shapes.add(BASE9[direction.getHorizontalIndex()]);
                shapes.add(BASE10[direction.getHorizontalIndex()]);
                shapes.add(BASE11[direction.getHorizontalIndex()]);
            }else {
                shapes.add(BASE4[direction.getHorizontalIndex()]);
                shapes.add(BASE5[direction.getHorizontalIndex()]);
            }


            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }

        return builder.build();
    }



    public WoodHandrail() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        this.setDefaultState(
                this.getStateContainer().getBaseState()
                        .with(DIRECTION, Direction.NORTH)
                        .with(IS_CORNER,false));
        SHAPES=blockBuilder(this.getStateContainer().getValidStates());
        setRegistryName("wood_handrail");
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(IS_CORNER);
        super.fillStateContainer(builder);
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        Direction direction = state.get(DIRECTION);
        state=state.with(IS_CORNER,cornerCheck(worldIn,pos,direction));
        worldIn.setBlockState(pos,state);
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(state);
    }
    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return SHAPES.get(state);
    }



    private boolean cornerCheck(World world,BlockPos pos, Direction face){
        switch (face){
            case EAST:
                BlockState blockWest=world.getBlockState(pos.west(1));
                if(blockWest.getBlock() == Blocks.AIR){
                    return false;
                }
                if(blockWest.getBlock().getClass()==WoodHandrail.class){
                    Direction direction = blockWest.getBlockState().get(DIRECTION);
                    if(direction==Direction.SOUTH) return true;
                }
                return true;

            case WEST:
                BlockState blockEast=world.getBlockState(pos.east(1));
                if(blockEast.getBlock() == Blocks.AIR){
                    return false;
                }
                if(blockEast.getBlock().getClass()==WoodHandrail.class){
                    Direction direction = blockEast.getBlockState().get(DIRECTION);
                    if(direction==Direction.NORTH) return true;
                }
                return true;

            case SOUTH:
                BlockState blockNorth=world.getBlockState(pos.north(1));
                if(blockNorth.getBlock() == Blocks.AIR){
                    return false;
                }
                if(blockNorth.getBlock().getClass()==WoodHandrail.class){
                    Direction direction = blockNorth.getBlockState().get(DIRECTION);
                    if(direction==Direction.WEST) return true;
                }
                return true;

            case NORTH:
                BlockState blockSouth=world.getBlockState(pos.south(1));
                if(blockSouth.getBlock() == Blocks.AIR){
                    return false;
                }
                if(blockSouth.getBlock().getClass()==WoodHandrail.class){
                    Direction direction = blockSouth.getBlockState().get(DIRECTION);
                    if(direction==Direction.SOUTH) return true;
                }
                return true;

        }
        return false;
    }


}


