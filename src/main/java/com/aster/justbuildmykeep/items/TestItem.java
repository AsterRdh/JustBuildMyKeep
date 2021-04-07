package com.aster.justbuildmykeep.items;

import com.aster.justbuildmykeep.setup.ModSetup;
import net.minecraft.block.Block;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.LeashKnotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.LeadItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class TestItem extends LeadItem {

    public TestItem(ModSetup setup) {
        super(new Properties().group(setup.getItemGroup()));
        setRegistryName("test_item");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        Block block = world.getBlockState(blockpos).getBlock();
        if (block.isIn(BlockTags.FENCES)) {
            PlayerEntity playerentity = context.getPlayer();
            if (!world.isRemote && playerentity != null) {
                bindPlayerMobs(playerentity, world, blockpos);
            }

            return ActionResultType.func_233537_a_(world.isRemote);
        } else {
            return ActionResultType.PASS;
        }
    }
}
