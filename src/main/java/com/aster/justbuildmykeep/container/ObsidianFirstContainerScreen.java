package com.aster.justbuildmykeep.container;

import com.aster.justbuildmykeep.Utils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ObsidianFirstContainerScreen extends ContainerScreen<ObsidianFirstContainer> {
    private final ResourceLocation OBSIDIAN_CONTAINER_RESOURCE = new ResourceLocation("minecraft", "textures/gui/container/dispenser.png");
    private final int textureWidth = 256;
    private final int textureHeight = 256;

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    public ObsidianFirstContainerScreen(ObsidianFirstContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        //screenContainer.
//        int i = 222;
//        int j = 114;
//        this.inventoryRows = container.getNumRows();
//        this.ySize = 114 + this.inventoryRows * 18;
//        this.playerInventoryTitleY = this.ySize - 94;
        this.xSize = textureWidth/2;
        this.ySize = textureHeight/2;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        drawCenteredString(matrixStack, this.font, Integer.toString(this.getContainer().getIntArray().get(0)), 82, 20, 0xeb0505);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bindTexture(OBSIDIAN_CONTAINER_RESOURCE);
        int i = (this.width - this.xSize)/2;
        int j = (this.height - this.ySize)/2;
        //Screen
        //blit(matrixStack, i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
        blit(matrixStack,i-24,j-19,0,0,256,256);
//        public void blit(MatrixStack matrixStack, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight) {
//            blit(matrixStack, x, y, this.blitOffset, (float)uOffset, (float)vOffset, uWidth, vHeight, 256, 256);
//        }
    }
}

