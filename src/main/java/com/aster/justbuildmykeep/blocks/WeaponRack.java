package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.entity.EmblemShieldEntity;
import com.aster.justbuildmykeep.entity.WeaponRackTileEntity;
import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.*;
import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class WeaponRack extends BaseHorizontalBlock{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states) {

        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 12, 11, 16, 18, 16), Direction.NORTH));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 18, 12, 16, 20, 16),Direction.NORTH));
        VoxelShape[] BASE3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 20, 14, 16, 24, 16),Direction.NORTH));
        VoxelShape[] BASE4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 6, 8, 16, 12, 16),Direction.NORTH));
        VoxelShape[] BASE5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 6, 16, 6, 16),Direction.NORTH));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.get(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(BASE1[direction.getHorizontalIndex()]);
            shapes.add(BASE2[direction.getHorizontalIndex()]);
            shapes.add(BASE3[direction.getHorizontalIndex()]);
            shapes.add(BASE4[direction.getHorizontalIndex()]);
            shapes.add(BASE5[direction.getHorizontalIndex()]);

            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }
        return builder.build();
    }

    public WeaponRack(){
        super(AbstractBlock.Properties.create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        this.setDefaultState(
                this.getStateContainer().getBaseState()
                        .with(DIRECTION, Direction.NORTH)
        );
        setRegistryName("weapon_rack");
        SHAPES=blockBuilder(this.getStateContainer().getValidStates());
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

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new WeaponRackTileEntity();
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        super.onBlockActivated(state, worldIn, pos, player, handIn, hit);

        TileEntity tileentity = worldIn.getTileEntity(pos);
        WeaponRackTileEntity entity=(WeaponRackTileEntity) tileentity;
        if(worldIn.isRemote){
            System.out.println("true "+ entity.getItems());
        }System.out.println("false "+ entity.getItems());

        if(!worldIn.isRemote() && hit.getFace()==state.getBlockState().get(DIRECTION).getOpposite()){
            int clickPos=-1;
            if(hit.getFace()==Direction.NORTH||hit.getFace()==Direction.SOUTH){
                clickPos=getPos(hit.getPos().getX()-hit.getHitVec().getX(),3);
            }else {
                clickPos=getPos(hit.getPos().getZ()-hit.getHitVec().getZ(),3);
            }

            if(clickPos!=-1){
                ItemStack itemStack=player.getHeldItem(handIn);
                if( isWeapon(itemStack.getItem())){
                    WeaponRackTileEntity weaponRackTileEntity=(WeaponRackTileEntity)tileentity;
                    weaponRackTileEntity.creatItemTest(clickPos,itemStack);
                }
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.CONSUME;
    }

    private int getPos(Double num,int quota){
        num=Math.abs(num);
        if(num<1){
            return (int)Math.floor(num*quota);
        }else return quota-1;
    }

    private boolean isWeapon(Object item){

        System.out.println(item.getClass().getName());
        return item.getClass()==SwordItem.class || item.getClass()==AxeItem.class || item.getClass()==BowItem.class || item.getClass()==CrossbowItem.class || item.getClass()==AirItem.class ;

    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {

        if (!state.isIn(newState.getBlock())) {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof WeaponRackTileEntity) {
                InventoryHelper.dropItems(world, pos, ((WeaponRackTileEntity)tileentity).getItems());
            }

            super.onReplaced(state, world, pos, newState, isMoving);
        }

    }



    //extinguish receiveFluid



}
