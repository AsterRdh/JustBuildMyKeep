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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LoopholeTiny extends BaseHorizontalWaterloggedBlock{
    private static IntegerProperty STATE = IntegerProperty.create("pos", 0, 3);

    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states,int i){
        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 0, 6, 16, 16),Direction.NORTH));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(10, 0, 0, 16, 16, 16),Direction.NORTH));
        VoxelShape[] BASE3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(6, 0, 0, 10, 3, 16),Direction.NORTH));
        VoxelShape[] BASE4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(6, 13, 0, 10, 16, 16),Direction.NORTH));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.get(DIRECTION);
            List<VoxelShape> shapes1 = new ArrayList<>();
            shapes1.add(BASE1[direction.getHorizontalIndex()]);
            shapes1.add(BASE2[direction.getHorizontalIndex()]);
            switch (i){
                case 0:
                    shapes1.add(BASE3[direction.getHorizontalIndex()]);
                    shapes1.add(BASE4[direction.getHorizontalIndex()]);
                    break;
                case 1:
                    shapes1.add(BASE3[direction.getHorizontalIndex()]);
                    break;
                case 3:
                    shapes1.add(BASE4[direction.getHorizontalIndex()]);
                    break;
                default:
                    break;
            }

            builder.put(state, VoxelShapeHelper.combineAll(shapes1));
        }
        return builder.build();
    }
    public LoopholeTiny() {
        super(
                AbstractBlock.Properties.create(Material.ROCK)
                        .sound(SoundType.STONE)
                        .hardnessAndResistance(1.5f)
                        .notSolid()
        );
        this.setDefaultState(this.getStateContainer().getBaseState().with(DIRECTION, Direction.NORTH).with(STATE,0));
        setRegistryName("loophole_tiny");
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STATE);
        super.fillStateContainer(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        int i=state.get(STATE);
        return blockBuilder(this.getStateContainer().getValidStates(),state.get(STATE)).get(state);
    }
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        Block upBlock = worldIn.getBlockState(pos.up(1)).getBlock();
        Block downBlock = worldIn.getBlockState(pos.down(1)).getBlock();

        if((worldIn.getBlockState(pos.up(1)).getBlock().getClass()==LoopholeTiny.class && worldIn.getBlockState(pos.down(1)).getBlock().getClass()==LoopholeTiny.class)){
            worldIn.setBlockState(
                    pos,
                    this.getStateContainer().getBaseState().
                            with(STATE,2).
                            with(WATERLOGGED,state.get(WATERLOGGED)).
                            with(DIRECTION, state.get(DIRECTION)));
        }else{
            if(worldIn.getBlockState(pos.up(1)).getBlock().getClass()==LoopholeTiny.class){
                worldIn.setBlockState(
                        pos,
                        this.getStateContainer().getBaseState().
                                with(STATE,1).
                                with(WATERLOGGED,state.get(WATERLOGGED)).
                                with(DIRECTION, state.get(DIRECTION)));
            }else {
                if(worldIn.getBlockState(pos.down(1)).getBlock().getClass()==LoopholeTiny.class){
                    worldIn.setBlockState(
                            pos,
                            this.getStateContainer().getBaseState().
                                    with(STATE,3).
                                    with(WATERLOGGED,state.get(WATERLOGGED)).
                                    with(DIRECTION, state.get(DIRECTION)));
                }
            }
        }
    }
    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        LoopholeTiny upBlock=null;
        LoopholeTiny downBlock=null;

        try{
            upBlock=(LoopholeTiny)worldIn.getBlockState(pos.up(1)).getBlock();
        }catch (Exception e){

        }
        try{
            downBlock=(LoopholeTiny)worldIn.getBlockState(pos.down(1)).getBlock();
        }catch (Exception e){

        }


        if(upBlock == null && downBlock == null){
            worldIn.setBlockState(
                    pos,
                    this.getStateContainer().getBaseState().
                            with(STATE,0).
                            with(WATERLOGGED,state.get(WATERLOGGED)).
                            with(DIRECTION, state.get(DIRECTION)));
        }

        if(upBlock == null && downBlock != null){
            worldIn.setBlockState(
                    pos,
                    this.getStateContainer().getBaseState().
                            with(STATE,3).
                            with(WATERLOGGED,state.get(WATERLOGGED)).
                            with(DIRECTION, state.get(DIRECTION)));
        }

        if(upBlock != null && downBlock == null){
            worldIn.setBlockState(
                    pos,
                    this.getStateContainer().getBaseState().
                            with(STATE,1).
                            with(WATERLOGGED,state.get(WATERLOGGED)).
                            with(DIRECTION, state.get(DIRECTION)));
        }

        if(upBlock != null && downBlock != null){
            worldIn.setBlockState(
                    pos,
                    this.getStateContainer().getBaseState().
                            with(STATE,2).
                            with(WATERLOGGED,state.get(WATERLOGGED)).
                            with(DIRECTION, state.get(DIRECTION)));
        }

    }

}
