package com.aster.justbuildmykeep.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;

public class TestArrowBlock extends BaseHorizontalBlock{
    public TestArrowBlock() {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(5.0f)
        );
        this.setDefaultState(this.getStateContainer().getBaseState().with(DIRECTION, Direction.NORTH));
        setRegistryName("test_arrow_block");
    }
}
