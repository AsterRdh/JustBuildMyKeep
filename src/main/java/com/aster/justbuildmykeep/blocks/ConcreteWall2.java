package com.aster.justbuildmykeep.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ConcreteWall2 extends BaseHorizontalBlock{
    public ConcreteWall2() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(5.0f)
        );
        setRegistryName("concrete_wall2");
    }
}
