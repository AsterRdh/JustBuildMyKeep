package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class WoodGirder extends BaseHorizontalBlock{
    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;
    public WoodGirder() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        setRegistryName("wood_girder");
        this.setDefaultState(
                this.stateContainer.getBaseState()
                        .with(DIRECTION, Direction.NORTH)
                        .with(HINGE, DoorHingeSide.LEFT)
        );
        SHAPES= blockBuilder(this.getStateContainer().getValidStates());
    }
    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states) {

        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 0, 8, 8, 16), Direction.NORTH));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(8, 0, 0, 16, 8, 16), Direction.NORTH));
        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.get(DIRECTION);
            DoorHingeSide doorHingeSide = state.get(HINGE);
            List<VoxelShape> shapes = new ArrayList<>();
            if(doorHingeSide==DoorHingeSide.LEFT){
                shapes.add(BASE2[direction.getHorizontalIndex()]);
            }else {
                shapes.add(BASE1[direction.getHorizontalIndex()]);
            }
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }
        return builder.build();
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(HINGE);
    }



    private DoorHingeSide getHingeSide(BlockItemUseContext context) {
        IBlockReader iblockreader = context.getWorld();
        BlockPos blockpos = context.getPos();
        Direction direction = context.getPlacementHorizontalFacing();
        BlockPos blockpos1 = blockpos.up();
        Direction direction1 = direction.rotateYCCW();
        BlockPos blockpos2 = blockpos.offset(direction1);
        BlockState blockstate = iblockreader.getBlockState(blockpos2);
        BlockPos blockpos3 = blockpos1.offset(direction1);
        BlockState blockstate1 = iblockreader.getBlockState(blockpos3);
        Direction direction2 = direction.rotateY();
        BlockPos blockpos4 = blockpos.offset(direction2);
        BlockState blockstate2 = iblockreader.getBlockState(blockpos4);
        BlockPos blockpos5 = blockpos1.offset(direction2);
        BlockState blockstate3 = iblockreader.getBlockState(blockpos5);
        int i = (blockstate.hasOpaqueCollisionShape(iblockreader, blockpos2) ? -1 : 0) + (blockstate1.hasOpaqueCollisionShape(iblockreader, blockpos3) ? -1 : 0) + (blockstate2.hasOpaqueCollisionShape(iblockreader, blockpos4) ? 1 : 0) + (blockstate3.hasOpaqueCollisionShape(iblockreader, blockpos5) ? 1 : 0);
        boolean flag = blockstate.isIn(this);
        boolean flag1 = blockstate2.isIn(this);
        if ((!flag || flag1) && i <= 0) {
            if ((!flag1 || flag) && i >= 0) {
                int j = direction.getXOffset();
                int k = direction.getZOffset();
                Vector3d vector3d = context.getHitVec();
                double d0 = vector3d.x - (double)blockpos.getX();
                double d1 = vector3d.z - (double)blockpos.getZ();
                return (j >= 0 || !(d1 < 0.5D)) && (j <= 0 || !(d1 > 0.5D)) && (k >= 0 || !(d0 > 0.5D)) && (k <= 0 || !(d0 < 0.5D)) ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
            } else {
                return DoorHingeSide.LEFT;
            }
        } else {
            return DoorHingeSide.RIGHT;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        if (blockpos.getY() < 255 ) {
            return this.getDefaultState().with(DIRECTION, context.getPlacementHorizontalFacing()).with(HINGE, this.getHingeSide(context));
        } else {
            return null;
        }
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


