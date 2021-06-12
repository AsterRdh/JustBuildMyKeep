package com.aster.justbuildmykeep.entity;

import com.aster.justbuildmykeep.Utils;
import com.aster.justbuildmykeep.container.GoodsBaxContainer;
import com.aster.justbuildmykeep.container.ObsidianFirstContainerItemNumber;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class BoxContainerTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {
    private BlockState state;
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(6, ItemStack.EMPTY);
    private Inventory inventory = new Inventory(6);
    private ObsidianFirstContainerItemNumber itemNumber = new ObsidianFirstContainerItemNumber();
    private int[] chestContentsDirection = new int[6];


    public BoxContainerTileEntity(BlockState state) {
        super(TileEntityTypeRegistry.BOX_CONTAINER_TILEENTITY.get());
        this.state=state;
    }
    public BoxContainerTileEntity() {
        super(TileEntityTypeRegistry.BOX_CONTAINER_TILEENTITY.get());
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {

    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public void tick() {

    }
    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("gui." + Utils.MOD_ID + ".goods_box");
    }
    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new GoodsBaxContainer(id, player, this.pos, this.world, itemNumber);
    }

    public IInventory getInventory() {
        return inventory;
    }
}
