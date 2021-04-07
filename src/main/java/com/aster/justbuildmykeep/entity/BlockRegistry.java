package com.aster.justbuildmykeep.entity;

import com.aster.justbuildmykeep.Utils;
import com.aster.justbuildmykeep.blocks.FireBasket;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Utils.MOD_ID);
    public static final RegistryObject<Block> obsidianTERBlock = BLOCKS.register("fire_basket", FireBasket::new);
}
