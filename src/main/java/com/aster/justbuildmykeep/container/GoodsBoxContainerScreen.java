package com.aster.justbuildmykeep.container;

import com.aster.justbuildmykeep.Utils;
import com.aster.justbuildmykeep.entity.BoxContainerTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GoodsBoxContainerScreen extends ContainerScreen<GoodsBaxContainer> {
    private final ResourceLocation OBSIDIAN_CONTAINER_RESOURCE_A = new ResourceLocation(Utils.MOD_ID, "textures/gui/container/goodBox_dispenser_a.png");
    private final ResourceLocation OBSIDIAN_CONTAINER_RESOURCE_B = new ResourceLocation(Utils.MOD_ID, "textures/gui/container/goodBox_dispenser_b.png");
    private final int textureWidth = 176;
    private final int textureHeight = 166;
    private BlockState state;

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    public GoodsBoxContainerScreen(GoodsBaxContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        state= screenContainer.getBlockState();
        //screenContainer.
//        int i = 222;
//        int j = 114;
//        this.inventoryRows = container.getNumRows();
//        this.ySize = 114 + this.inventoryRows * 18;
//        this.playerInventoryTitleY = this.ySize - 94;
        this.xSize = textureWidth;
        this.ySize = textureHeight;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        //drawCenteredString(matrixStack, this.font, Integer.toString(this.getContainer().getIntArray().get(0)), 82, 20, 0xeb0505);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        state.get(TYPE)
        this.minecraft.getTextureManager().bindTexture(OBSIDIAN_CONTAINER_RESOURCE_A);
        int i = (this.width - this.xSize)/2;
        int j = (this.height - this.ySize)/2;
        //Screen
        blit(matrixStack, i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
    }
}

