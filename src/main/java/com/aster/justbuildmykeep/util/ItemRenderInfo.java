package com.aster.justbuildmykeep.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;

public class ItemRenderInfo {
    private ItemStack itemStack;
    private Quaternion quaternion;
    private Vector3d pos;
    private Vector3d scale;

    public ItemRenderInfo(ItemStack itemStack, Quaternion quaternion, Vector3d pos, Vector3d scale) {
        this.itemStack = itemStack;
        this.quaternion = quaternion;
        this.pos = pos;
        this.scale = scale;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Quaternion getQuaternion() {
        return quaternion;
    }

    public void setQuaternion(Quaternion quaternion) {
        this.quaternion = quaternion;
    }

    public Vector3d getPos() {
        return pos;
    }

    public void setPos(Vector3d pos) {
        this.pos = pos;
    }

    public Vector3d getScale() {
        return scale;
    }

    public void setScale(Vector3d scale) {
        this.scale = scale;
    }
}
