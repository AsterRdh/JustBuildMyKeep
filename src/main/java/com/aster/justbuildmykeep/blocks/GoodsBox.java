package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.entity.BoxContainerTileEntity;
import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
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
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class GoodsBox extends SlabBlock {
    public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states) {

        VoxelShape[] BOTTOM_SHAPE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3.0D, 0.0D, 1.0D, 13.0D, 8.0D, 15.0D), Direction.NORTH));
        VoxelShape[] TOP_SHAPE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3.0D, 8.0D, 1.0D, 13.0D, 16.0D, 15.0D), Direction.NORTH));
        VoxelShape[] DOUBLE_SHAPE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3.0D, 0.0D, 1.0D, 13.0D, 16.0D, 15.0D), Direction.NORTH));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.get(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            SlabType slabType = state.get(TYPE);
            if(slabType==SlabType.TOP){
                shapes.add(TOP_SHAPE[direction.getHorizontalIndex()]);
            }else if(slabType==SlabType.BOTTOM){
                shapes.add(BOTTOM_SHAPE[direction.getHorizontalIndex()]);
            }else {
                shapes.add(DOUBLE_SHAPE[direction.getHorizontalIndex()]);
            }
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }
        return builder.build();
    }

    public GoodsBox() {
        super(Properties.create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(5)
                .notSolid()
        );
        setRegistryName("goods_box_f1");
        SHAPES=blockBuilder(this.getStateContainer().getValidStates());
    }




    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return super.getStateForPlacement(context).with(DIRECTION, context.getPlacementHorizontalFacing());
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(DIRECTION);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(state);
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BoxContainerTileEntity(state);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            BoxContainerTileEntity boxContainerTileEntity = (BoxContainerTileEntity) worldIn.getTileEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player, boxContainerTileEntity, (PacketBuffer packerBuffer) -> {
                packerBuffer.writeBlockPos(boxContainerTileEntity.getPos());
            });
            System.out.println("open gui");
        }
        return ActionResultType.SUCCESS;
    }

}

