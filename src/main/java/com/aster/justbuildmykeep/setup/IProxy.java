package com.aster.justbuildmykeep.setup;

import net.minecraft.world.World;

public interface IProxy {
    public void init();
    public World getClientWorld();
}
