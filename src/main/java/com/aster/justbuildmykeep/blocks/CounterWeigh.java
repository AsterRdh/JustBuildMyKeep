package com.aster.justbuildmykeep.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CounterWeigh extends Block {
    public CounterWeigh() {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(5.0f)
                .notSolid()
        );
        setRegistryName("counter_weigh");
    }

}
