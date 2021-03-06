package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.util.VoxelShapeHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BattlementBottom extends BaseHorizontalBlock {

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        System.out.println(placer);

    }

    public BattlementBottom() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(1.5f)

        );
        this.setDefaultState(this.getStateContainer().getBaseState().with(DIRECTION, Direction.NORTH));
        setRegistryName("battlement_bottom");
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        final VoxelShape[] BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 0, 8, 16, 16, 16),Direction.SOUTH));
        Direction direction = state.get(DIRECTION);
        return BASE[direction.getHorizontalIndex()];
    }

}
