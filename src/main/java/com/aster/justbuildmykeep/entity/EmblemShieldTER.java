package com.aster.justbuildmykeep.entity;

import com.aster.justbuildmykeep.blocks.EmblemShield;
import com.aster.justbuildmykeep.util.MathCalculator;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.model.data.EmptyModelData;

public class EmblemShieldTER  extends TileEntityRenderer<EmblemShieldEntity> {
    public EmblemShieldTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }
    @Override
    public void render(EmblemShieldEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        //todo 1.随放置武器位置改变 1.插入剑/斧头 2.拔出剑/斧头

        BlockState blockState = tileEntityIn.getBlockState();
        Direction direction = blockState.get(EmblemShield.DIRECTION);

        MathCalculator mathCalculator=new MathCalculator();
        Vector3d vector3d = computeLocationByDirection1(new Vector3d(0.3, 0.7, 0), direction);
        Vector3d vector3d2 = computeLocationByDirection2(new Vector3d(0.7, 0.7, 0), direction);

        matrixStackIn.push();
        matrixStackIn.translate(vector3d.getX(),vector3d.getY(),vector3d.getZ());
        matrixStackIn.rotate(computeRotationByDirection1(new Quaternion(3.2f,0f,-1.6f,false),direction));
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(Items.IRON_SWORD);
        IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(vector3d2.getX(),vector3d2.getY(),vector3d2.getZ());
        matrixStackIn.rotate(computeRotationByDirection2(new Quaternion(3.2f,0f,0f,false),direction));
        itemRenderer = Minecraft.getInstance().getItemRenderer();
        stack = new ItemStack(Items.IRON_SWORD);
        ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.pop();

    }
    private Vector3d computeLocationByDirection1(Vector3d pos, Direction direction){
        switch(direction) {
            case SOUTH:
                return new Vector3d(0.3,0.7,1);
            case WEST:
                return new Vector3d(0,0.7,0.3);
            case EAST:
                return new Vector3d(1,0.7,0.7);
            case NORTH:
            case DOWN:
            case UP:
            default:
                return pos;
        }
    }

    private Vector3d computeLocationByDirection2(Vector3d pos, Direction direction){
        switch(direction) {
            case SOUTH:
                return new Vector3d(0.7,0.7,1);
            case WEST:
                return new Vector3d(0,0.7,0.7);
            case EAST:
                return new Vector3d(1,0.7,0.3);
            case NORTH:
            case DOWN:
            case UP:
            default:
                return pos;
        }
    }

    public Quaternion computeRotationByDirection1(Quaternion rotation, Direction direction){
        switch(direction) {
            case SOUTH:
                return new Quaternion(3.2f,0f,-1.6f,false);
            case WEST:
                return new Quaternion(3.2f,-1.6f,0.05f,false);
            case EAST:
                return  new Quaternion(3.2f,1.6f,0f,false);
            case NORTH:
            case DOWN:
            case UP:
            default:
                return rotation;
        }
    }
    public Quaternion computeRotationByDirection2(Quaternion rotation, Direction direction){
        switch(direction) {
            case SOUTH:
                return new Quaternion(3.2f,0f,0f,false);
            case WEST:
                return new Quaternion(3.2f,-1.6f,-1.55f,false);
            case EAST:
                return  new Quaternion(1.6f,1.6f,0f,false);
            case NORTH:
            case DOWN:
            case UP:
            default:
                return rotation;
        }
    }



}
