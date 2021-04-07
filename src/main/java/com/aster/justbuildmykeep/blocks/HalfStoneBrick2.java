package com.aster.justbuildmykeep.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class HalfStoneBrick2 extends BaseBlock{
    public HalfStoneBrick2() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(1.5f)
                .notSolid()
        );

        setRegistryName("half_stone2");
    }
}
