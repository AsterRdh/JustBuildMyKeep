package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.advancements.criterion.FishingRodHookedTrigger;
import net.minecraft.advancements.criterion.MobEffectsPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.LeadItem;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.*;

public class PortcullisBlock extends BaseHorizontalBlock{
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public PortcullisBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(5.0f)
                .notSolid()
        );
        SHAPES= blockBuilder(this.getStateContainer().getValidStates());
        setRegistryName("portcullis_block");
    }
    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states) {
        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 7, 16, 16, 9), Direction.NORTH));
        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();

        for(BlockState state : states)
        {
            Direction direction = state.get(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(BASE1[direction.getHorizontalIndex()]);
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }
        return builder.build();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        super.onBlockActivated(state, worldIn, pos, player, handIn, hit);

        Set<BlockPos> set=new HashSet<>();
        set=getAroundBlockState(state,worldIn,pos,set);
        System.out.println(set);

        List<BlockStateAndPos> blockList=new ArrayList<>();

        for (BlockPos pos1:set){
            blockList.add(new BlockStateAndPos(pos1,worldIn.getBlockState(pos1)));
        }

        if (state.get(OPEN)){
            Collections.sort(blockList, new Comparator<BlockStateAndPos>(){
                @Override
                public int compare(BlockStateAndPos o1, BlockStateAndPos o2) {
                    return  o1.getPos().getY()-o2.getPos().getY();
                }
            });
            for (BlockStateAndPos state1: blockList){
                closeDoor(state1.getState(),worldIn,state1.getPos());
            }

        } else {
            Collections.sort(blockList, new Comparator<BlockStateAndPos>(){
                @Override
                public int compare(BlockStateAndPos o1, BlockStateAndPos o2) {
                    return  o2.getPos().getY()-o1.getPos().getY();
                }
            });
            for (BlockStateAndPos state1: blockList){
                openDoor(state1.getState(),worldIn,state1.getPos());
            }
        }

        return  ActionResultType.SUCCESS;
    }

    public Set<BlockPos> getAroundBlockState( BlockState state,World worldIn, BlockPos pos,Set<BlockPos> sets){
        boolean isInPos=false;
        for (BlockPos blockPos:sets){
            isInPos=blockPos.getX()==pos.getX()&&blockPos.getY()==pos.getY()&&blockPos.getZ()==pos.getZ();
            if(isInPos) break;
        }


        if(state.getBlock().getClass()==PortcullisBlock.class&&!isInPos){
            sets.add(new BlockPos(pos.getX(),pos.getY(),pos.getZ()));
            BlockPos up = pos.up();
            BlockPos down = pos.down();
            BlockPos left=null;
            BlockPos right=null;
            Direction direction = state.get(DIRECTION);
            switch (direction){
                case SOUTH:
                    left=pos.east();
                    right=pos.west();
                    break;
                case NORTH:
                    left=pos.west();
                    right=pos.east();
                    break;
                case WEST:
                    left=pos.south();
                    right=pos.north();
                    break;
                case EAST:
                    left=pos.north();
                    right=pos.south();
                    break;
            }

            BlockState blockState = worldIn.getBlockState(right);
            if(blockState.getBlock().getClass()==PortcullisBlock.class){
                PortcullisBlock block = (PortcullisBlock)blockState.getBlock();
                block.getAroundBlockState(blockState,worldIn,right,sets);
            }

            blockState = worldIn.getBlockState(up);
            if(blockState.getBlock().getClass()==PortcullisBlock.class){
                PortcullisBlock block = (PortcullisBlock)blockState.getBlock();
                block.getAroundBlockState(blockState,worldIn,up,sets);
            }

            blockState = worldIn.getBlockState(left);
            if(blockState.getBlock().getClass()==PortcullisBlock.class){
                PortcullisBlock block = (PortcullisBlock)blockState.getBlock();
                block.getAroundBlockState(blockState,worldIn,left,sets);
            }

            blockState = worldIn.getBlockState(down);
            if(blockState.getBlock().getClass()==PortcullisBlock.class){
                PortcullisBlock block = (PortcullisBlock)blockState.getBlock();
                block.getAroundBlockState(blockState,worldIn,down,sets);
            }
        }
        return sets;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return super.getStateForPlacement(context).with(OPEN,false);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(OPEN);
    }

    public void openDoor(BlockState state, World worldIn, BlockPos pos){
        //System.out.println(pos.toString()+": "+state.toString());
        BlockState with = state.with(OPEN, true);
        worldIn.setBlockState(pos,Blocks.AIR.getDefaultState());
        worldIn.setBlockState(pos.up(3),with);
    }

    public void closeDoor(BlockState state, World worldIn, BlockPos pos){

        BlockState with = state.with(OPEN,false);
        worldIn.setBlockState(pos,Blocks.AIR.getDefaultState());
        worldIn.setBlockState(pos.down(3),with);
    }

    private class BlockStateAndPos{
        private BlockPos pos;
        private BlockState state;

        public BlockStateAndPos(BlockPos pos, BlockState state) {
            this.pos = pos;
            this.state = state;
        }

        public BlockPos getPos() {
            return pos;
        }

        public void setPos(BlockPos pos) {
            this.pos = pos;
        }

        public BlockState getState() {
            return state;
        }

        public void setState(BlockState state) {
            this.state = state;
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
