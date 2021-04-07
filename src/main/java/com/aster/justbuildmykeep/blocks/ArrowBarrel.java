package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.entity.ArrowBarrelEntity;
import com.aster.justbuildmykeep.entity.EmblemShieldEntity;
import com.aster.justbuildmykeep.entity.WeaponRackTileEntity;
import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
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

public class ArrowBarrel extends BaseHorizontalWaterloggedBlock{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;
    public ArrowBarrel() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        this.setDefaultState(this.getStateContainer().getBaseState().with(DIRECTION, Direction.NORTH));
        SHAPES=blockBuilder(this.getStateContainer().getValidStates());
        setRegistryName("arrow_barrel");
    }

    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states){
        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(12, 0, 3, 13, 12, 4),Direction.NORTH));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 0, 3, 4, 12, 4),Direction.NORTH));
        VoxelShape[] BASE3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 0, 12, 4, 12, 13),Direction.NORTH));
        VoxelShape[] BASE4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(12, 0, 12, 13, 12, 13),Direction.NORTH));
        VoxelShape[] BASE5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(4, 0, 2, 12, 12, 3),Direction.NORTH));
        VoxelShape[] BASE6 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(4, 0, 13, 12, 12, 14),Direction.NORTH));
        VoxelShape[] BASE7 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(13, 0, 4, 14, 12, 12),Direction.NORTH));
        VoxelShape[] BASE8 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(2, 0, 4, 3, 12, 12),Direction.NORTH));
        VoxelShape[] BASE9 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 0, 4, 13, 1, 12),Direction.NORTH));
        VoxelShape[] BASE10 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(4, 0, 12, 12, 1, 13),Direction.NORTH));
        VoxelShape[] BASE11 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(4, 0, 3, 12, 1, 4),Direction.NORTH));
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
            shapes.add(BASE6[direction.getHorizontalIndex()]);
            shapes.add(BASE7[direction.getHorizontalIndex()]);
            shapes.add(BASE8[direction.getHorizontalIndex()]);
            shapes.add(BASE9[direction.getHorizontalIndex()]);
            shapes.add(BASE10[direction.getHorizontalIndex()]);
            shapes.add(BASE11[direction.getHorizontalIndex()]);
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }

        return builder.build();
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
        return new ArrowBarrelEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        super.onBlockActivated(state, worldIn, pos, player, handIn, hit);

        TileEntity tileentity = worldIn.getTileEntity(pos);
        ArrowBarrelEntity entity=(ArrowBarrelEntity) tileentity;
        ItemStack itemStack=player.getHeldItem(handIn);

        try{
            ArrowItem item=(ArrowItem)itemStack.getItem();
            if(ArrowItem.class.isAssignableFrom(item.getClass())||item.getClass()==ArrowItem.class)
                entity.addItem(itemStack,1);
        }catch (Exception e){
            System.out.println(e);
            if(itemStack.equals(ItemStack.EMPTY)||itemStack.getItem().getClass()==Items.AIR.getClass()){
                entity.pickUpItem();
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {

        if (!state.isIn(newState.getBlock())) {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof ArrowBarrelEntity) {
                InventoryHelper.dropItems(world, pos, ((ArrowBarrelEntity)tileentity).getDropAllItems());
            }

            super.onReplaced(state, world, pos, newState, isMoving);
        }

    }
}
