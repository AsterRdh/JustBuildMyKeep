package com.aster.justbuildmykeep.items;

import com.aster.justbuildmykeep.setup.ModSetup;
import net.minecraft.item.Item;

public class GrassGroup extends Item {
    public GrassGroup() {
        super(new Properties().group(ModSetup.GLASS_GROUP));
        setRegistryName("plate_wood");
    }
}
