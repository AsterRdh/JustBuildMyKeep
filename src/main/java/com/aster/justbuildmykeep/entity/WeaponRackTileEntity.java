package com.aster.justbuildmykeep.entity;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.*;
import net.minecraft.item.AirItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

public class WeaponRackTileEntity extends TileEntity implements IClearable {
    private final NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    public WeaponRackTileEntity() {
        super(TileEntityTypeRegistry.WEAPON_RACK_ENTITIES.get());
    }


    public void creatItemTest(int i,ItemStack itemStack){

        if(itemStack.getItem().getClass()== AirItem.class){
            pickUpItem(i);
        }else {
            setItem(itemStack,i);
        }
        System.out.println("click "+i+" pos");
        System.out.println(this.hashCode());
        this.inventoryChanged();
    }

    public void setItem(ItemStack item,int index){
        if(items.get(index)==ItemStack.EMPTY){
            items.set(index,item.split(1));
        }
        this.inventoryChanged();
    }

    public void  pickUpItem(int index){
        if(items.get(index)!=ItemStack.EMPTY){
            ItemStack item=items.get(index).copy();
            IInventory iinventory = new Inventory(item);
            BlockPos blockpos = this.getPos();
            InventoryHelper.spawnItemStack(this.world, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), item);
            items.set(index,ItemStack.EMPTY);
        }
        this.inventoryChanged();
    }

    public NonNullList<ItemStack> getItems(){
        return this.items;
    }

    public ItemStack getItem(int i){
        return items.get(i);
    }

    private void inventoryChanged() {
        this.markDirty();
        this.getWorld().notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        ItemStackHelper.saveAllItems(compound, this.items, true);
        return compound;
    }

    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.items.clear();
        ItemStackHelper.loadAllItems(nbt, this.items);
    }



    /**
     * Retrieves packet to send to the client whenever this Tile Entity is resynced via World.notifyBlockUpdate. For
     * modded TE's, this packet comes back to you clientside in {@link #onDataPacket}
     */
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 1, this.getUpdateTag());
    }

    /**
     * Get an NBT compound to sync to the client with SPacketChunkData, used for initial loading of the chunk or when
     * many blocks change at once. This compound comes back to you clientside in {@link handleUpdateTag}
     */
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    public void dropAllItems() {
        if (this.world != null) {
            if (!this.world.isRemote) {
                InventoryHelper.dropItems(this.world, this.getPos(), this.getItems());
            }

            this.inventoryChanged();
        }

    }

}
