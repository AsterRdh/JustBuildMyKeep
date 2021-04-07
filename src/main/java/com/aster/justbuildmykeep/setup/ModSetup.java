package com.aster.justbuildmykeep.setup;

import com.aster.justbuildmykeep.Blocks;
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

    public static ItemGroup getItemGroup() {
        return itemGroup;
    }

    public void init(){


    }

}
