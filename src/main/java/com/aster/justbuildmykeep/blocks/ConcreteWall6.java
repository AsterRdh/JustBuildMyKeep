package com.aster.justbuildmykeep.blocks;
import com.aster.justbuildmykeep.blocks.BaseHorizontalBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ConcreteWall6 extends BaseHorizontalBlock {
    public ConcreteWall6() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(5.0f)
        );
        setRegistryName("concrete_wall6");
    }
}