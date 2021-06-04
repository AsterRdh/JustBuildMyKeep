package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.*;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BaseGlassBlock extends BaseBlock{
    private static BooleanProperty HORIZONTAL = BooleanProperty.create("is_horizontal");
    private static IntegerProperty POS = IntegerProperty.create("pos", 0, 2);
    public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states) {
        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 0, 16, 16, 2), Direction.NORTH));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 7, 16, 16, 9),Direction.NORTH));
        VoxelShape[] BASE3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 14, 16, 16, 16),Direction.NORTH));

        VoxelShape BASE4 = Block.makeCuboidShape(0, 0, 0, 16, 2, 16);
        VoxelShape BASE5 = Block.makeCuboidShape(0, 7, 0, 16, 9, 16);
        VoxelShape BASE6 = Block.makeCuboidShape(0, 14, 0, 16, 16, 16);

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {

            if(state.get(HORIZONTAL)){
                switch (state.get(POS)){
                    case 0:
                        builder.put(state, BASE4);
                        break;
                    case 1:
                        builder.put(state, BASE5);
                        break;
                    case 2:
                        builder.put(state, BASE6);
                        break;
                }
            }else {
                Direction direction = state.get(DIRECTION);
                List<VoxelShape> shapes = new ArrayList<>();
                switch (state.get(POS)){
                    case 0:
                        shapes.add(BASE1[direction.getHorizontalIndex()]);
                        break;
                    case 1:
                        shapes.add(BASE2[direction.getHorizontalIndex()]);
                        break;
                    case 2:
                        shapes.add(BASE3[direction.getHorizontalIndex()]);
                        break;
                }
                builder.put(state, VoxelShapeHelper.combineAll(shapes));
            }
        }

        return builder.build();
    }

    public BaseGlassBlock() {
        super(AbstractBlock.Properties.create(Material.GLASS)
                .sound(SoundType.GLASS)
                .hardnessAndResistance(0.3F)
                .notSolid()

        );
        SHAPES=blockBuilder(this.getStateContainer().getValidStates());
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        //return super.getStateForPlacement(context).with(FACING, context.getPlacementHorizontalFacing());
        return super.getStateForPlacement(context)
                .with(HORIZONTAL, getFace(context.getFace()))
                .with(POS,getPos(context))
                .with(DIRECTION, context.getPlacementHorizontalFacing());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL);
        builder.add(DIRECTION);
        builder.add(POS);
    }

    private Boolean getFace(Direction direction){
        switch (direction){
            case UP:
            case DOWN:
                return false;
            default:
                return true;
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    private int getPos(BlockItemUseContext context){

        Vector3d hitVec = context.getHitVec();

        hitVec=new Vector3d(Math.abs(hitVec.getX()),Math.abs(hitVec.getY()),Math.abs(hitVec.getZ()));
        Vector3i baseVec=new Vector3i(Math.abs(hitVec.getX()),Math.abs(hitVec.getY()),Math.abs(hitVec.getZ()));
        hitVec=hitVec.subtract(new Vector3d(baseVec.getX(),baseVec.getY(),baseVec.getZ()));
        System.out.println(hitVec);

        if(getFace(context.getFace())){
            //水平放置
            return get3Part(hitVec.getY());
        }else {
            //垂直放置
            switch (context.getPlacementHorizontalFacing()){
                case EAST:
                    return get3Part(hitVec.getX());
                case WEST:
                    return get3Part(1d-hitVec.getX());
                case NORTH:
                    return get3Part(hitVec.getZ());
                case SOUTH:
                    return get3Part(1d-hitVec.getZ());
            }
        }
        return 1;
    }

    private int get3Part(Double d){
        if(d>2d/3d){
            return 2;
        }else if(d>1d/3d){
            return 1;
        }else return 0;
    }



    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return SHAPES.get(state);
    }

}
