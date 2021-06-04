package com.aster.justbuildmykeep;

import com.aster.justbuildmykeep.items.GrassGroup;
import com.aster.justbuildmykeep.items.PlateWood;
import com.aster.justbuildmykeep.setup.ModSetup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class Items {
    @ObjectHolder("justbuildmykeep:plate_wood")
    public static PlateWood PLATE_WOOD ;
    @ObjectHolder("justbuildmykeep:stained_glass_a")
    public static Item STAINED_GLASS_A = new BlockItem(Blocks.STAINED_GLASS_A, new Item.Properties().group(ModSetup.GLASS_GROUP)).setRegistryName("justbuildmykeep","stained_glass_a") ;
}
