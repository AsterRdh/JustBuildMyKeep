package com.aster.justbuildmykeep.items;

import com.aster.justbuildmykeep.setup.ModSetup;
import net.minecraft.item.Item;

public class PlateWood extends Item {
    public PlateWood() {
        super(new Properties().group(ModSetup.TABLE_WARE));
        setRegistryName("plate_wood");
    }

}
