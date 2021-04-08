package com.aster.justbuildmykeep.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;

import java.util.ArrayList;
import java.util.List;

public class TableTER extends TileEntityRenderer<ObsidianFirstContainerTileEntity> {
    private List<Vector3d> posList=new ArrayList<>();

    public TableTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
        posList.add( new Vector3d(0.166667,1,0.166667));
        posList.add( new Vector3d(0.166667,1,0.5));
        posList.add( new Vector3d(0.166667,1,0.833333));
        posList.add( new Vector3d(0.5,1,0.166667));
        posList.add( new Vector3d(0.5,1,0.5));
        posList.add( new Vector3d(0.5,1,0.833333));
        posList.add( new Vector3d(0.833333,1,0.166667));
        posList.add( new Vector3d(0.833333,1,0.5));
        posList.add( new Vector3d(0.833333,1,0.833333));
        posList.add( new Vector3d(0.166667,1,0.5));
        posList.add( new Vector3d(0.5,1,0.5));
        posList.add( new Vector3d(0.833333,1,0.5));
    }

    @Override
    public void render(ObsidianFirstContainerTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        for(int i=0;i<tileEntityIn.getItemList().size();i++){
            matrixStackIn.push();
            ItemStack item = tileEntityIn.getItemList().get(i);
            //Vector3d pos = new Vector3d(0.5,1,0.833333);
            Vector3d pos = posList.get(i);
            Quaternion roa = new Quaternion(0,0,0,1);
            matrixStackIn.translate(pos.getX(),pos.getY(),pos.getZ());
            matrixStackIn.rotate(roa);
            matrixStackIn.scale(1.2f,1.2f,1.2f);

            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(item, tileEntityIn.getWorld(), null);
            itemRenderer.renderItem(item, ItemCameraTransforms.TransformType.GROUND, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
            matrixStackIn.pop();

        }
    }
}
