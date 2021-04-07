package com.aster.justbuildmykeep.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class PitchDitch extends GrassBlock {
    public PitchDitch() {
        super(AbstractBlock.Properties.create(Material.LAVA)
                .sound(SoundType.SLIME)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );
        setRegistryName("pitch_ditch");
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return Block.makeCuboidShape(0, 0, 0, 16, 1, 16);
    }
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {

        Vector3d vector3d = entityIn.getMotion();
        if (vector3d.y < -0.13D) {
            double d0 = -1D / vector3d.y;
            System.out.println(vector3d.y);
            entityIn.setMotion(new Vector3d(vector3d.x * d0, -0.05D, vector3d.z * d0));
        } else {
            System.out.println(vector3d.y);
            entityIn.setMotion(new Vector3d(vector3d.x*-0.5, 0D, vector3d.z*-0.5));
        }
        super.onEntityCollision(state, worldIn, pos, entityIn);
    }


    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        Block e=worldIn.getBlockState(pos.east()).getBlock();
        Block s=worldIn.getBlockState(pos.south()).getBlock();
        Block w=worldIn.getBlockState(pos.west()).getBlock();
        Block n=worldIn.getBlockState(pos.north()).getBlock();
        if(e.getClass()== Blocks.FIRE.getClass()){
            worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState());
        }else
        if(s.getClass()==Blocks.FIRE.getClass()){
            worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState());
        }else
        if(w.getClass()==Blocks.FIRE.getClass()){
            worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState());
        }else
        if(n.getClass()==Blocks.FIRE.getClass()){
            worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState());
        }
    }



    @Override
    public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
        super.onProjectileCollision(worldIn, state, hit, projectile);
        if(!worldIn.isRemote()){
            int fireTimer = projectile.getFireTimer();
            if(fireTimer>0){
                BlockPos pos = hit.getPos();
                worldIn.setBlockState(pos,Blocks.FIRE.getDefaultState());
            }
        }

    }
}
