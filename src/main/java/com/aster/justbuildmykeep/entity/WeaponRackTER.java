package com.aster.justbuildmykeep.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.*;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;

import static com.aster.justbuildmykeep.blocks.BaseHorizontalWaterloggedBlock.DIRECTION;


@OnlyIn(Dist.CLIENT)
public class WeaponRackTER  extends TileEntityRenderer<WeaponRackTileEntity> {

    private Map<Class,Map<Direction,Quaternion>> rotMap = new HashMap<>();
    private Map<Class,Map<Direction,V3d[]>> posMap = new HashMap<>();

    public WeaponRackTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
        this.rocBuilder();
    }


    private Map<Class,Map<Direction,Quaternion>> rocBuilder(){
        Map<Direction,Quaternion> r1=new HashMap<>();
        Map<Direction,Quaternion> r2=new HashMap<>();
        Map<Direction,Quaternion> r3=new HashMap<>();
        Map<Direction,Quaternion> r4=new HashMap<>();
        Map<Direction,Quaternion> r5=new HashMap<>();

        r1.put(Direction.SOUTH,new Quaternion(22.5f,-0f,135f,true));
        r1.put(Direction.EAST,new Quaternion(90,67.5f,45,true));
        r1.put(Direction.WEST,new Quaternion(90,112.5f,45,true));
        r1.put(Direction.NORTH,new Quaternion(-22.5f,0f,135f,true));

        r2.put(Direction.SOUTH,new Quaternion(22.5f,-90f,-45f,true));
        r2.put(Direction.EAST,new Quaternion(90,67.5f,45,true));
        r2.put(Direction.WEST,new Quaternion(90,112.5f,45,true));
        r2.put(Direction.NORTH,new Quaternion(-22.5f,90f,-45f,true));

        r3.put(Direction.SOUTH,new Quaternion(22.5f,0f,165f,true));
        r3.put(Direction.EAST,new Quaternion(90,67.5f,75,true));
        r3.put(Direction.WEST,new Quaternion(90,112.5f,45,true));
        r3.put(Direction.NORTH,new Quaternion(-22.5f,90f,-45f,true));

        r4.put(Direction.SOUTH,new Quaternion(22.5f,0f,45f,true));
        r4.put(Direction.EAST,new Quaternion(90,67.5f,-45,true));
        r4.put(Direction.WEST,new Quaternion(90,112.5f,75,true));
        r4.put(Direction.NORTH,new Quaternion(-22.5f,0f,165f,true));

        rotMap.put(SwordItem.class,r1);
        rotMap.put(AxeItem.class,r2);
        rotMap.put(BowItem.class,r3);
        rotMap.put(CrossbowItem.class,r4);
        rotMap.put(AirItem.class,r1);
        return rotMap;
    }


    @Override
    public void render(WeaponRackTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        NonNullList<ItemStack> nonnulllist = tileEntityIn.getItems();

        //System.out.println(tileEntityIn.getWorld().isRemote);

        BlockState blockState = tileEntityIn.getBlockState();
        Direction direction = blockState.get(DIRECTION);
        V3d[] pos=getPos(direction);
        //rotMap=rocBuilder();
        matrixStackIn.push();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        matrixStackIn.translate(pos[0].getX(),pos[0].getY(),pos[0].getZ());
        matrixStackIn.scale(0.7f,0.7f,0.7f);
        ItemStack stack=tileEntityIn.getItem(0);
        matrixStackIn.rotate(getRot(direction,stack));
        IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.pop();



        matrixStackIn.push();
        itemRenderer = Minecraft.getInstance().getItemRenderer();
        matrixStackIn.translate(pos[1].getX(),pos[1].getY(),pos[1].getZ());
        matrixStackIn.scale(0.7f,0.7f,0.7f);
        stack=tileEntityIn.getItem(1);
        matrixStackIn.rotate(getRot(direction,stack));
        ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.pop();


        matrixStackIn.push();
        itemRenderer = Minecraft.getInstance().getItemRenderer();
        matrixStackIn.translate(pos[2].getX(),pos[2].getY(),pos[2].getZ());
        matrixStackIn.scale(0.7f,0.7f,0.7f);
        stack=tileEntityIn.getItem(2);
        matrixStackIn.rotate(getRot(direction,stack));
        ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.pop();


    }

    private V3d[] getPos(Direction direction){
        switch (direction){
            case SOUTH:
                return new V3d[]{new V3d(0.19,1.05,0.75),new V3d(0.5,1.07,0.73),new V3d(0.81,1.05,0.75)};
            case WEST:
                return new V3d[]{new V3d(0.25,1.05,0.19),new V3d(0.3,1.07,0.5),new V3d(0.25,1.05,0.81)};
            case EAST:
                return new V3d[]{new V3d(0.715,1.05,0.19),new V3d(0.70,1.07,0.5),new V3d(0.715,1.05,0.81)};
            case NORTH:
            default:
                return new V3d[]{new V3d(0.19,1.05,0.25),new V3d(0.5,1.07,0.27),new V3d(0.81,1.05,0.25)};
        }
    }

    private Quaternion getRot(Direction direction,ItemStack itemStack){

        Class<? extends Item> aClass = itemStack.getItem().getClass();
        if (SwordItem.class.equals(aClass)) {
            switch (direction){
                case SOUTH: return new Quaternion(22.5f,-0f,135f,true);
                case EAST: return new Quaternion(90,67.5f,45,true);
                case WEST: return new Quaternion(90,112.5f,45,true);
                case NORTH: default: return new Quaternion(-22.5f,0f,135f,true);
            }
        } else if (AxeItem.class.equals(aClass)) {
            switch (direction){
                case SOUTH: return new Quaternion(22.5f,-90f,-45f,true);
                case EAST: return new Quaternion(0f,0f,-67.5f,true);
                case WEST: return new Quaternion(180f,0f,112.5f,true);
                case NORTH: default: return new Quaternion(-22.5f,90f,-45f,true);
            }
        } else if (BowItem.class.equals(aClass)) {
            switch (direction){
                case SOUTH: return new Quaternion(22.5f,0f,165f,true);
                case EAST: return new Quaternion(90,67.5f,75,true);
                case WEST: return new Quaternion(90,112.5f,75,true);
                case NORTH: default: return new Quaternion(-22.5f,0f,165f,true);
            }
        } else if (CrossbowItem.class.equals(aClass)) {
            switch (direction){
                case SOUTH: return new Quaternion(22.5f,0f,45f,true);
                case EAST: return new Quaternion(90,67.5f,-45,true);
                case WEST: return new Quaternion(90,112.5f,-45,true);
                case NORTH: default: return new Quaternion(-22.5f,0f,45f,true);
            }
        } else {
           return new Quaternion(0,0,0,1);
        }




//
//        return rotMap.get(itemStack.getItem().getClass()).get(direction);
//        Quaternion quaternion = new Quaternion(-22.5f, 0f, 135f, true);
//        Quaternion quaternion1 = new Quaternion(new Vector3f(-1,-1,0),90,true);
//        quaternion.multiply(quaternion1);
//        switch (direction){
//            case SOUTH:
//                return new Quaternion(22.5f,0f,165f,true);
//            case EAST:
//                return  new Quaternion(90,67.5f,75,true);
//            case WEST:
//                return new Quaternion(90,112.5f,75,true);
//            case NORTH:
//            default:
//                return new Quaternion(-22.5f,0f,165f,true);
//        }
    }

    private class V3d{
        private double x;
        private double y;
        private double z;

        public V3d(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }
    }


}
