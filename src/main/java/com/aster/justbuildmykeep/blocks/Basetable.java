package com.aster.justbuildmykeep.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Basetable extends BaseHorizontalWaterloggedBlock {
    private static IntegerProperty STATE = IntegerProperty.create("pos", 0, 3);
    public Basetable() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        this.setDefaultState(
                this.getStateContainer().getBaseState()
                        .with(DIRECTION, Direction.NORTH)
                        .with(WATERLOGGED, false)
                        .with(STATE,0));

    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STATE);
        super.fillStateContainer(builder);
    }
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        Basetable westBlock = toBaseTable(worldIn.getBlockState(pos.west(1)).getBlock());
        Basetable eastBlock = toBaseTable(worldIn.getBlockState(pos.east(1)).getBlock());
        Basetable northBlock = toBaseTable(worldIn.getBlockState(pos.north(1)).getBlock());
        Basetable southBlock = toBaseTable(worldIn.getBlockState(pos.south(1)).getBlock());

        int s=0;

        if (isSameDIRECTION(state.get(DIRECTION),Direction.NORTH)){
            if(westBlock!= null && isSameDIRECTION(state.get(DIRECTION),worldIn.getBlockState(pos.west(1)).get(DIRECTION))){

                if(s==0) s=1;
                else s=2;
            }

            if(eastBlock!= null && isSameDIRECTION(state.get(DIRECTION),worldIn.getBlockState(pos.east(1)).get(DIRECTION))){
                if(s==0) s=3;
                else s=2;
            }
        }else {
            if(northBlock!= null && isSameDIRECTION(state.get(DIRECTION),worldIn.getBlockState(pos.north(1)).get(DIRECTION))){
                if(s==0) s=1;
                else s=2;
            }
            if(southBlock!= null && isSameDIRECTION(state.get(DIRECTION),worldIn.getBlockState(pos.south(1)).get(DIRECTION))){
                if(s==0) s=3;
                else s=2;
            }
        }
        worldIn.setBlockState(pos,
                this.getStateContainer().getBaseState()
                        .with(STATE,s)
                        .with(DIRECTION,state.get(DIRECTION))
                        .with(WATERLOGGED,state.get(WATERLOGGED))
        );
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        Basetable westBlock = toBaseTable(worldIn.getBlockState(pos.west(1)).getBlock());
        Basetable eastBlock = toBaseTable(worldIn.getBlockState(pos.east(1)).getBlock());
        Basetable northBlock = toBaseTable(worldIn.getBlockState(pos.north(1)).getBlock());
        Basetable southBlock = toBaseTable(worldIn.getBlockState(pos.south(1)).getBlock());

        int s=0;
        if (isSameDIRECTION(state.get(DIRECTION),Direction.NORTH)){
            if(westBlock!= null && isSameDIRECTION(state.get(DIRECTION),worldIn.getBlockState(pos.west(1)).get(DIRECTION))){

                if(s==0) s=1;
                else s=2;
            }

            if(eastBlock!= null && isSameDIRECTION(state.get(DIRECTION),worldIn.getBlockState(pos.east(1)).get(DIRECTION))){
                if(s==0) s=3;
                else s=2;
            }
        }else {
            if(northBlock!= null && isSameDIRECTION(state.get(DIRECTION),worldIn.getBlockState(pos.north(1)).get(DIRECTION))){
                if(s==0) s=1;
                else s=2;
            }
            if(southBlock!= null && isSameDIRECTION(state.get(DIRECTION),worldIn.getBlockState(pos.south(1)).get(DIRECTION))){
                if(s==0) s=3;
                else s=2;
            }
        }


        worldIn.setBlockState(pos,
                this.getStateContainer().getBaseState()
                        .with(STATE,s)
                        .with(DIRECTION,state.get(DIRECTION))
                        .with(WATERLOGGED,state.get(WATERLOGGED))
        );
    }

    private Basetable toBaseTable(Block block){
        try{
            return (Basetable)block;
        }catch (Exception re){
            return null;
        }
    }

    private Boolean isSameDIRECTION(Direction direction1,Direction direction2){

        return direction1==direction2 || direction1.getOpposite()==direction2;
    }


}
