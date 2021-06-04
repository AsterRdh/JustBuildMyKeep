package com.aster.justbuildmykeep.items;

import com.aster.justbuildmykeep.setup.ModSetup;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.LeashKnotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.LeadItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;


public class TestItem extends Item {

    public TestItem(ModSetup setup) {
        super(new Properties().group(setup.getItemGroup()));
        setRegistryName("test_item");
    }


    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        return ActionResultType.SUCCESS;
    }



    private <E extends Entity> void renderLeash(Vector3d endPoint,Vector3d startPoint, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn) {
        Vector3d vector3d = startPoint;
        double d0 = (double)(MathHelper.lerp(partialTicks, endPoint.y,  endPoint.y) * ((float)Math.PI / 180F)) + (Math.PI / 2D);
        Vector3d vector3d1 = endPoint;
        double d1 = Math.cos(d0) * vector3d1.z + Math.sin(d0) * vector3d1.x;
        double d2 = Math.sin(d0) * vector3d1.z - Math.cos(d0) * vector3d1.x;
        double d3 = MathHelper.lerp((double)partialTicks, endPoint.getX(), endPoint.getX()) + d1;
        double d4 = MathHelper.lerp((double)partialTicks,endPoint.getY(), endPoint.getY()) + vector3d1.y;
        double d5 = MathHelper.lerp((double)partialTicks, endPoint.getZ(), endPoint.getZ()) + d2;
        matrixStackIn.translate(d1, vector3d1.y, d2);
        float f = (float)(vector3d.x - d3);
        float f1 = (float)(vector3d.y - d4);
        float f2 = (float)(vector3d.z - d5);
        float f3 = 0.025F;
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getLeash());
        Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
        float f4 = MathHelper.fastInvSqrt(f * f + f2 * f2) * 0.025F / 2.0F;
        float f5 = f2 * f4;
        float f6 = f * f4;
        BlockPos blockpos = new BlockPos(endPoint);
        BlockPos blockpos1 = new BlockPos(startPoint);
        int i = 0;
        int j = 0;
        int k = 15;
        int l = 15;
        renderSide(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.025F, 0.025F, f5, f6);
        renderSide(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.025F, 0.0F, f5, f6);
    }

    public static void renderSide(IVertexBuilder bufferIn, Matrix4f matrixIn, float p_229119_2_, float p_229119_3_, float p_229119_4_, int blockLight, int holderBlockLight, int skyLight, int holderSkyLight, float p_229119_9_, float p_229119_10_, float p_229119_11_, float p_229119_12_) {
        int i = 24;

        for(int j = 0; j < 24; ++j) {
            float f = (float)j / 23.0F;
            int k = (int) MathHelper.lerp(f, (float)blockLight, (float)holderBlockLight);
            int l = (int)MathHelper.lerp(f, (float)skyLight, (float)holderSkyLight);
            int i1 = LightTexture.packLight(k, l);
            addVertexPair(bufferIn, matrixIn, i1, p_229119_2_, p_229119_3_, p_229119_4_, p_229119_9_, p_229119_10_, 24, j, false, p_229119_11_, p_229119_12_);
            addVertexPair(bufferIn, matrixIn, i1, p_229119_2_, p_229119_3_, p_229119_4_, p_229119_9_, p_229119_10_, 24, j + 1, true, p_229119_11_, p_229119_12_);
        }

    }

    public static void addVertexPair(IVertexBuilder bufferIn, Matrix4f matrixIn, int packedLight, float p_229120_3_, float p_229120_4_, float p_229120_5_, float p_229120_6_, float p_229120_7_, int p_229120_8_, int p_229120_9_, boolean p_229120_10_, float p_229120_11_, float p_229120_12_) {
        float f = 0.5F;
        float f1 = 0.4F;
        float f2 = 0.3F;
        if (p_229120_9_ % 2 == 0) {
            f *= 0.7F;
            f1 *= 0.7F;
            f2 *= 0.7F;
        }

        float f3 = (float)p_229120_9_ / (float)p_229120_8_;
        float f4 = p_229120_3_ * f3;
        float f5 = p_229120_4_ > 0.0F ? p_229120_4_ * f3 * f3 : p_229120_4_ - p_229120_4_ * (1.0F - f3) * (1.0F - f3);
        float f6 = p_229120_5_ * f3;
        if (!p_229120_10_) {
            bufferIn.pos(matrixIn, f4 + p_229120_11_, f5 + p_229120_6_ - p_229120_7_, f6 - p_229120_12_).color(f, f1, f2, 1.0F).lightmap(packedLight).endVertex();
        }

        bufferIn.pos(matrixIn, f4 - p_229120_11_, f5 + p_229120_7_, f6 + p_229120_12_).color(f, f1, f2, 1.0F).lightmap(packedLight).endVertex();
        if (p_229120_10_) {
            bufferIn.pos(matrixIn, f4 + p_229120_11_, f5 + p_229120_6_ - p_229120_7_, f6 - p_229120_12_).color(f, f1, f2, 1.0F).lightmap(packedLight).endVertex();
        }

    }
}
