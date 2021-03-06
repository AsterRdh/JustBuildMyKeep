package com.aster.justbuildmykeep.container;

import com.aster.justbuildmykeep.entity.BoxContainerTileEntity;
import com.aster.justbuildmykeep.events.ContainerTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GoodsBaxContainer  extends Container {
    private ObsidianFirstContainerItemNumber intArray;
    private BoxContainerTileEntity tileEntity;

    public BlockState getBlockState() {
        return tileEntity.getBlockState();
    }

    public GoodsBaxContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world, ObsidianFirstContainerItemNumber intArray) {
        super(ContainerTypeRegistry.goodsBoxContainer.get(), id);
        this.intArray = intArray;
        trackIntArray(this.intArray);
        BoxContainerTileEntity boxContainerTileEntity = (BoxContainerTileEntity) world.getTileEntity(pos);
        tileEntity=boxContainerTileEntity;
        this.addSlot(new Slot(boxContainerTileEntity.getInventory(), 0, 51, 17));
        this.addSlot(new Slot(boxContainerTileEntity.getInventory(), 1, 69, 17));
        this.addSlot(new Slot(boxContainerTileEntity.getInventory(), 2, 87, 17));

        this.addSlot(new Slot(boxContainerTileEntity.getInventory(), 3, 51, 35));
        this.addSlot(new Slot(boxContainerTileEntity.getInventory(), 4, 69, 35));
        this.addSlot(new Slot(boxContainerTileEntity.getInventory(), 5, 87, 35));

        layoutPlayerInventorySlots(playerInventory, 8, 84);
    }
    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);
        //ChestScreen
        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }
    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }
    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
        if (clickTypeIn==ClickType.PICKUP && slotId>=0 && slotId<9 ){
            System.out.println(slotId);
            tileEntity.setChestContentsDirection(slotId,player.getHorizontalFacing());
        }
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }


    public IIntArray getIntArray() {
        return intArray;
    }

}
