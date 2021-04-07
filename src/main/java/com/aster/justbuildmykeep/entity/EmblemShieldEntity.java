package com.aster.justbuildmykeep.entity;

import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.item.ItemFrameItem;
import net.minecraft.tileentity.TileEntity;

public class EmblemShieldEntity extends TileEntity {
    public EmblemShieldEntity() {
        super(TileEntityTypeRegistry.emblemShieldEntity.get());
    }
}
