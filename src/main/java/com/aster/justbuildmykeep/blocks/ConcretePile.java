package com.aster.justbuildmykeep.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ConcretePile extends BaseWaterloggedBlock{
    public ConcretePile() {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(5.0f)
                .notSolid()
        );
        setRegistryName("concrete_pile");
    }
}
