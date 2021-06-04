package com.aster.justbuildmykeep.entity;

import com.aster.justbuildmykeep.Utils;
import com.aster.justbuildmykeep.blocks.Basetable;
import com.aster.justbuildmykeep.container.ObsidianFirstContainer;
import com.aster.justbuildmykeep.container.ObsidianFirstContainerItemNumber;
import com.aster.justbuildmykeep.entity.TileEntityTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.Arrays;

public class ObsidianFirstContainerTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(12, ItemStack.EMPTY);
    private Inventory inventory = new Inventory(12);
    private ObsidianFirstContainerItemNumber itemNumber = new ObsidianFirstContainerItemNumber();
    private int[] chestContentsDirection = new int[12];

    public void setChestContentsDirection(int index,Direction d){
        System.out.println(chestContentsDirection.length);
        if(index<this.chestContentsDirection.length)
        switch (d){
            case EAST: this.chestContentsDirection[index]= 0 ;break;
            case SOUTH:this.chestContentsDirection[index]= 1 ;break;
            case WEST:this.chestContentsDirection[index]= 2 ;break;
            case NORTH:this.chestContentsDirection[index]= 3 ;break;
            default: break;
        }
    }

    public Quaternion getLookingDirection(int index) {

        switch (chestContentsDirection[index]){
            case 0:
                return new Quaternion(0f,0,0f,true);
            case 1:
                return new Quaternion(0,904,0,true);
            case 2:
                return new Quaternion(0,180,0,true);
            case 3:
            default:
                return new Quaternion(0,-90,0,true);
        }
    }

    public ObsidianFirstContainerTileEntity() {
        super(TileEntityTypeRegistry.OBSIDIAN_FIRST_CONTAINER_ENTITY.get());
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("gui." + Utils.MOD_ID + ".wood_table_a");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.chestContents;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.chestContents=itemsIn;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new ObsidianFirstContainer(id, player, this.pos, this.world, itemNumber);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.chestContents = NonNullList.withSize(12, ItemStack.EMPTY);
        if (!this.checkLootAndRead(nbt)) {
            CompoundNBT contents = nbt.getCompound("contents");
            ItemStackHelper.loadAllItems(contents, this.chestContents);
            this.chestContentsDirection= nbt.getIntArray("directions");
            for(int i=0;i<12;i++){
                inventory.setInventorySlotContents(i,chestContents.get(i));
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        CompoundNBT contents=new CompoundNBT();
        compound.put("contents",contents);
        compound.putIntArray("directions",chestContentsDirection);
        if (!this.checkLootAndRead(compound)) {
            this.setList();
            ItemStackHelper.saveAllItems(contents, this.chestContents);
        }
        return compound;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public NonNullList<ItemStack> getItemList(){
        NonNullList<ItemStack> list=NonNullList.withSize(12, ItemStack.EMPTY);
        for(int i=0;i<12;i++){
            list.set(i,inventory.getStackInSlot(i));
        }
        return list;
    }

    public void setList(){
        for(int i=0;i<12;i++){
            chestContents.set(i,inventory.getStackInSlot(i));
        }
    }

    @Override
    public void tick() {
    }

    @Override
    public int getSizeInventory() {
        return 12;
    }
    
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 1, this.getUpdateTag());
    }

    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Override
    public void clear() {
        this.chestContents.clear();
    }


}

