package com.aster.justbuildmykeep.blocks;
import com.aster.justbuildmykeep.blocks.BaseHorizontalBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ConcreteWall15 extends BaseHorizontalBlock {
    public ConcreteWall15() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(5.0f)
        );
        setRegistryName("concrete_wall15");
    }
}