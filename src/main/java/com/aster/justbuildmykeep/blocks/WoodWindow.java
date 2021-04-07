package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WoodWindow extends DoorBlock {
    public ImmutableMap<BlockState, VoxelShape> SHAPES;
    public ImmutableMap<BlockState, VoxelShape> SHAPES2;
    public static final BooleanProperty STATE = BooleanProperty.create("open");
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    private ImmutableMap<BlockState, VoxelShape> blockBuilder(ImmutableList<BlockState> states) {
        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 22, 0, 16, 32, 16), Direction.NORTH));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(11, 20, 0, 13, 22, 7), Direction.NORTH));
        VoxelShape[] BASE3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 20, 0, 5, 22, 7), Direction.NORTH));
        VoxelShape[] BASE4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 18, 0, 4, 20, 7), Direction.NORTH));
        VoxelShape[] BASE5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(12, 18, 0, 13, 20, 7), Direction.NORTH));
        VoxelShape[] BASE6 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(9, 21, 0, 11, 22, 7), Direction.NORTH));
        VoxelShape[] BASE7 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(5, 21, 0, 7, 22, 7), Direction.NORTH));
        VoxelShape[] BASE8 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 0, 16, 2, 16), Direction.NORTH));
        VoxelShape[] BASE9 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 4, 1, 13, 6, 3), Direction.NORTH));
        VoxelShape[] BASE10 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 9, 1, 13, 11, 3), Direction.NORTH));
        VoxelShape[] BASE11 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 14, 1, 13, 16, 3), Direction.NORTH));
        VoxelShape[] BASE12 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(6.26937, 20.89296, 0, 9.73067, 23.89296, 0), Direction.NORTH));
        VoxelShape[] BASE13 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 2, 0, 3, 16, 16), Direction.NORTH));
        VoxelShape[] BASE14 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 16, 0, 3, 22, 16), Direction.NORTH));
        VoxelShape[] BASE15 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(13, 16, 0, 16, 22, 16), Direction.NORTH));
        VoxelShape[] BASE16 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(13, 2, 0, 16, 16, 16), Direction.NORTH));
        VoxelShape[] BASE17 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 2, 8, 4, 22, 13), Direction.NORTH));
        VoxelShape[] BASE18 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(12, 2, 8, 13, 22, 13), Direction.NORTH));
        VoxelShape[] BASE19 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 2, 7, 8, 22, 8), Direction.NORTH));
        VoxelShape[] BASE20 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(8, 2, 7, 13, 22, 8), Direction.NORTH));
        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.get(FACING);
            List<VoxelShape> shapes1 = new ArrayList<>();
            shapes1.add(BASE1[direction.getHorizontalIndex()]);
            shapes1.add(BASE2[direction.getHorizontalIndex()]);
            shapes1.add(BASE3[direction.getHorizontalIndex()]);
            shapes1.add(BASE4[direction.getHorizontalIndex()]);
            shapes1.add(BASE5[direction.getHorizontalIndex()]);
            shapes1.add(BASE6[direction.getHorizontalIndex()]);
            shapes1.add(BASE7[direction.getHorizontalIndex()]);
            shapes1.add(BASE8[direction.getHorizontalIndex()]);
            shapes1.add(BASE9[direction.getHorizontalIndex()]);
            shapes1.add(BASE10[direction.getHorizontalIndex()]);
            shapes1.add(BASE11[direction.getHorizontalIndex()]);
            shapes1.add(BASE12[direction.getHorizontalIndex()]);
            shapes1.add(BASE13[direction.getHorizontalIndex()]);
            shapes1.add(BASE14[direction.getHorizontalIndex()]);
            shapes1.add(BASE15[direction.getHorizontalIndex()]);
            shapes1.add(BASE16[direction.getHorizontalIndex()]);
            if(state.get(OPEN)){
                shapes1.add(BASE17[direction.getHorizontalIndex()]);
                shapes1.add(BASE18[direction.getHorizontalIndex()]);
            }else {
                shapes1.add(BASE19[direction.getHorizontalIndex()]);
                shapes1.add(BASE20[direction.getHorizontalIndex()]);
            }
            builder.put(state, VoxelShapeHelper.combineAll(shapes1));
        }
        return builder.build();
    }

    private ImmutableMap<BlockState, VoxelShape> blockBuilder2(ImmutableList<BlockState> states) {
        VoxelShape[] BASE17 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(14, 0, 0, 15, 1, 16), Direction.NORTH));
        VoxelShape[] BASE18 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(13, 0, 0, 14, 6, 16), Direction.NORTH));
        VoxelShape[] BASE19 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(14, 5, 0, 15, 6, 16), Direction.NORTH));
        VoxelShape[] BASE20 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(14, 1, 0, 15, 5, 16), Direction.NORTH));
        VoxelShape[] BASE21 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(15, 0, 0, 16, 6, 16), Direction.NORTH));
        VoxelShape[] BASE22 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(1, 0, 0, 2, 1, 16), Direction.NORTH));
        VoxelShape[] BASE23 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(2, 0, 0, 3, 6, 16), Direction.NORTH));
        VoxelShape[] BASE24 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(1, 5, 0, 2, 6, 16), Direction.NORTH));
        VoxelShape[] BASE25 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(1, 1, 0, 2, 5, 16), Direction.NORTH));
        VoxelShape[] BASE26 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 0, 1, 6, 16), Direction.NORTH));
        VoxelShape[] BASE27 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 6, 0, 16, 16, 16), Direction.NORTH));
        VoxelShape[] BASE28 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(11, 4, 0, 13, 6, 7), Direction.NORTH));
        VoxelShape[] BASE29 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 4, 0, 5, 6, 7), Direction.NORTH));
        VoxelShape[] BASE30 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, 2, 0, 4, 4, 7), Direction.NORTH));
        VoxelShape[] BASE31 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(12, 2, 0, 13, 4, 7), Direction.NORTH));
        VoxelShape[] BASE32 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(9, 5, 0, 11, 6, 7), Direction.NORTH));
        VoxelShape[] BASE33 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(5, 5, 0, 7, 6, 7), Direction.NORTH));

        VoxelShape[] BASE1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, -14, 8, 4, 6, 13), Direction.NORTH));
        VoxelShape[] BASE2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(12, -14, 8, 13, 6, 13), Direction.NORTH));
        VoxelShape[] BASE3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(3, -14, 7, 8, 6, 8), Direction.NORTH));
        VoxelShape[] BASE4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(8, -14, 7, 13, 6, 8), Direction.NORTH));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.get(FACING);
            List<VoxelShape> shapes2 = new ArrayList<>();
            shapes2.add(BASE17[direction.getHorizontalIndex()]);
            shapes2.add(BASE18[direction.getHorizontalIndex()]);
            shapes2.add(BASE19[direction.getHorizontalIndex()]);
            shapes2.add(BASE20[direction.getHorizontalIndex()]);
            shapes2.add(BASE21[direction.getHorizontalIndex()]);
            shapes2.add(BASE22[direction.getHorizontalIndex()]);
            shapes2.add(BASE23[direction.getHorizontalIndex()]);
            shapes2.add(BASE24[direction.getHorizontalIndex()]);
            shapes2.add(BASE25[direction.getHorizontalIndex()]);
            shapes2.add(BASE26[direction.getHorizontalIndex()]);
            shapes2.add(BASE27[direction.getHorizontalIndex()]);
            shapes2.add(BASE28[direction.getHorizontalIndex()]);
            shapes2.add(BASE29[direction.getHorizontalIndex()]);
            shapes2.add(BASE30[direction.getHorizontalIndex()]);
            shapes2.add(BASE31[direction.getHorizontalIndex()]);
            shapes2.add(BASE32[direction.getHorizontalIndex()]);
            shapes2.add(BASE33[direction.getHorizontalIndex()]);
            if(state.get(OPEN)){
                shapes2.add(BASE1[direction.getHorizontalIndex()]);
                shapes2.add(BASE2[direction.getHorizontalIndex()]);
            }else {
                shapes2.add(BASE3[direction.getHorizontalIndex()]);
                shapes2.add(BASE4[direction.getHorizontalIndex()]);
            }
            builder.put(state, VoxelShapeHelper.combineAll(shapes2));
        }
        return builder.build();
    }

    public WoodWindow() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        SHAPES = blockBuilder(this.getStateContainer().getValidStates());
        SHAPES2 = blockBuilder2(this.getStateContainer().getValidStates());
        setRegistryName("wood_window");

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        try{
            if(state.get(HALF)==DoubleBlockHalf.UPPER) return SHAPES2.get(state);
        } catch (Exception e){
            System.out.println(e);
        }

        return SHAPES.get(state);
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader reader, BlockPos pos) {
        try{
            if(state.get(HALF)==DoubleBlockHalf.UPPER) return SHAPES2.get(state);
        } catch (Exception e){
            System.out.println(e);
        }
        return SHAPES.get(state);
    }
}
