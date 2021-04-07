package com.aster.justbuildmykeep.entity;

import com.aster.justbuildmykeep.util.ItemRenderInfo;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrowBarrelEntity extends TileEntity {
    private final NonNullList<ItemStack> items = NonNullList.withSize(32, ItemStack.EMPTY);
    private final List<ItemRenderInfo> itemRenderInfos=new ArrayList<>();
    private Random random = new Random();
    public ArrowBarrelEntity() {
        super(TileEntityTypeRegistry.ARROW_BARREL_ENTITIES.get());
    }

    public List<ItemRenderInfo> getItems(){
        return itemRenderInfos;
    }

    public NonNullList<ItemStack> getDropAllItems(){
        for(int i =0;i<itemRenderInfos.size();i++){
            items.set(i,itemRenderInfos.get(i).getItemStack());
        }
        itemRenderInfos.clear();
        return items;
    }

    public boolean addItem(ItemStack itemStack,int type){
        double x=0;
        double y=0;
        double z=(random.nextDouble()+5)/10;

        int xA=random.nextInt(21)-10;
        int yA=random.nextInt(361)-180;
        int zA=random.nextInt(10)+130;

        if(itemRenderInfos.size()<32){
            while (true){
                x = random.nextDouble()*10+3;
                y = random.nextDouble()*10+3;
                double x1=x-8;
                double y1=y-8;
                x1=x1*x1;
                y1=y1*y1;
                if ((x1+y1)<25) {
                    x=x/16;
                    y=y/16;
                    break;
                }
            }
            itemRenderInfos.add(new ItemRenderInfo(itemStack.split(1),new Quaternion(xA,yA,zA,true),new Vector3d(x,z,y),new Vector3d(1,1,1)));
            if(type==1){
                this.inventoryChanged();
            }
            return true;
        }else {
            if(type==1){
                this.inventoryChanged();
            }
            return false;
        }
    }

    public ItemStack pickUpItem(){
        if(itemRenderInfos.size()>0){
            int index=random.nextInt(itemRenderInfos.size());
            ItemRenderInfo itemRenderInfo = itemRenderInfos.get(index);
            ItemStack itemStack = itemRenderInfo.getItemStack().copy();
            BlockPos blockpos = this.getPos();
            InventoryHelper.spawnItemStack(this.world, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), itemStack);
            itemRenderInfos.remove(index);
            this.inventoryChanged();
            return  itemStack;
        }
        this.inventoryChanged();
        return  null;
    }

    //网络相关
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

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        this.items.clear();
        for(int i =0;i<itemRenderInfos.size();i++){
            items.set(i,itemRenderInfos.get(i).getItemStack());
        }
        ItemStackHelper.saveAllItems(compound, this.items, true);
        return compound;
    }


    //本地
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.items.clear();
        this.itemRenderInfos.clear();
        ItemStackHelper.loadAllItems(nbt, this.items);
        for(ItemStack itemStack:items){
            if(itemStack!=null && itemStack!=ItemStack.EMPTY) addItem(itemStack,0);
        }
    }

    private void setAll(){
        for(int i =0;i<32;i++){
            try{
                ItemRenderInfo itemRenderInfo = itemRenderInfos.get(i);
                if(itemRenderInfo!=null){
                    items.set(i,itemRenderInfos.get(i).getItemStack()!=ItemStack.EMPTY?itemRenderInfos.get(i).getItemStack():ItemStack.EMPTY);
                }else {
                    items.set(i,ItemStack.EMPTY);
                }
            }catch (Exception e){
                items.set(i,ItemStack.EMPTY);
            }
        }
    }

    private void inventoryChanged() {
        this.markDirty();
        this.getWorld().notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
        setAll();
    }

}
