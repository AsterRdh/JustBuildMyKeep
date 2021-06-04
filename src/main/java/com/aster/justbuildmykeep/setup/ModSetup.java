package com.aster.justbuildmykeep.setup;

import com.aster.justbuildmykeep.Blocks;
import com.aster.justbuildmykeep.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModSetup{
    private static ItemGroup itemGroup=new ItemGroup("justbuildmykeep") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Blocks.EMBLEM_SHIELD);
        }
    };

    public static ItemGroup  TABLE_WARE=new ItemGroup("table_ware") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.PLATE_WOOD);
        }
    };

    public static ItemGroup  GLASS_GROUP=new ItemGroup("grass_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.STAINED_GLASS_A);
        }
    };



    public static ItemGroup getItemGroup() {
        return itemGroup;
    }

    public void init(){

    }

}
