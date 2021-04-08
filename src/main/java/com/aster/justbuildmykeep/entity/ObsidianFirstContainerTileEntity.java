package com.aster.justbuildmykeep.entity;

import com.aster.justbuildmykeep.Utils;
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
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class ObsidianFirstContainerTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(12, ItemStack.EMPTY);
    private Inventory inventory = new Inventory(12);
    private ObsidianFirstContainerItemNumber itemNumber = new ObsidianFirstContainerItemNumber();

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
            ItemStackHelper.loadAllItems(nbt, this.chestContents);
            for(int i=0;i<12;i++){
                inventory.setInventorySlotContents(i,chestContents.get(i));
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndRead(compound)) {
            this.setList();
            ItemStackHelper.saveAllItems(compound, this.chestContents);
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

    public void setChestContents(ItemStack itemStack,int index){
        if(itemStack!=null){
            chestContents.set(index,itemStack);
        }
        inventory.setInventorySlotContents(index,itemStack);
    }

    @Override
    public void tick() {
        if (world.isRemote) {
            setList();
        }
    }

    @Override
    public int getSizeInventory() {
        return 12;
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
        this.chestContents.clear();
    }


}

