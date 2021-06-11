package com.aster.justbuildmykeep.entity;

import com.aster.justbuildmykeep.container.ObsidianFirstContainerItemNumber;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class BoxContainerTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {
    private BlockState state;
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(6, ItemStack.EMPTY);
    private Inventory inventory = new Inventory(6);
    private ObsidianFirstContainerItemNumber itemNumber = new ObsidianFirstContainerItemNumber();
    private int[] chestContentsDirection = new int[6];


    public BoxContainerTileEntity(BlockState state) {
        super(TileEntityTypeRegistry.OBSIDIAN_FIRST_CONTAINER_ENTITY.get());
        this.state=state;
    }
    public BoxContainerTileEntity() {
        super(TileEntityTypeRegistry.OBSIDIAN_FIRST_CONTAINER_ENTITY.get());
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {

    }

    @Override
    protected ITextComponent getDefaultName() {
        return null;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return null;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public void tick() {

    }
}
