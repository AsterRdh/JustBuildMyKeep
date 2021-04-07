package com.aster.justbuildmykeep.blocks;

import com.aster.justbuildmykeep.entity.FireBasketEntity;
import com.aster.justbuildmykeep.items.TestItem;
import com.aster.justbuildmykeep.setup.ModSetup;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.*;
import net.minecraft.item.crafting.CampfireCookingRecipe;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static net.minecraft.item.ShootableItem.ARROWS;

public class FireBasket extends ContainerBlock implements IWaterLoggable{

    protected  VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty SIGNAL_FIRE = BlockStateProperties.SIGNAL_FIRE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SMOKING_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    private final boolean smokey;
    private final int fireDamage;


    public FireBasket() {
        super(Properties.create(Material.ROCK)
                        .hardnessAndResistance(5)
                        .notSolid()
                        .setLightLevel(getLightValueLit(15))
        );
        setRegistryName("fire_basket");
        this.smokey = true;
        this.fireDamage = 1;
        this.setDefaultState(
                this.stateContainer.getBaseState()
                        .with(LIT, Boolean.valueOf(true))
                        .with(SIGNAL_FIRE, Boolean.valueOf(false))
                        .with(WATERLOGGED, Boolean.valueOf(false))
                        .with(FACING, Direction.NORTH));
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> {
            return state.get(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }
    public static void extinguish(IWorld world, BlockPos pos, BlockState state) {
        if (world.isRemote()) {
            for(int i = 0; i < 20; ++i) {
                spawnSmokeParticles((World)world, pos, state.get(SIGNAL_FIRE), true);
            }
        }

        TileEntity tileentity = world.getTileEntity(pos);
        if (tileentity instanceof FireBasketEntity) {
            ((FireBasketEntity)tileentity).dropAllItems();
        }

    }

    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        if (!state.get(BlockStateProperties.WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
            boolean flag = state.get(LIT);
            if (flag) {
                if (!worldIn.isRemote()) {
                    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                extinguish(worldIn, pos, state);
            }

            worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)).with(LIT, Boolean.valueOf(false)), 3);
            worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
            return true;
        } else {
            return false;
        }
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new FireBasketEntity();
    }

    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof FireBasketEntity) {
                InventoryHelper.dropItems(worldIn, pos, ((FireBasketEntity)tileentity).getInventory());
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }


    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        Iterable<ItemStack> heldEquipment = player.getHeldEquipment();
        int i=0;
        for(ItemStack itemStack:heldEquipment){
            if(i==0 && itemStack.getItem().getClass() == FlintAndSteelItem.class){
                worldIn.setBlockState(pos, state.with(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
                FlintAndSteelItem item = (FlintAndSteelItem)itemStack.getItem();
                String a = itemStack.getItem().getClass().getName();
                System.out.println(a);
            }
            i++;
        }
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof FireBasketEntity) {
            FireBasketEntity fireBasketEntity = (FireBasketEntity)tileentity;
            ItemStack itemstack = player.getHeldItem(handIn);
            Optional<CampfireCookingRecipe> optional = fireBasketEntity.findMatchingRecipe(itemstack);
            if (optional.isPresent()) {
                if (!worldIn.isRemote && fireBasketEntity.addItem(player.abilities.isCreativeMode ? itemstack.copy() : itemstack, optional.get().getCookTime())) {
                    player.addStat(Stats.INTERACT_WITH_CAMPFIRE);
                    return ActionResultType.SUCCESS;
                }

                return ActionResultType.CONSUME;
            }
        }

        return ActionResultType.PASS;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT, SIGNAL_FIRE, WATERLOGGED, FACING);
    }


    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(LIT)) {
            if (rand.nextInt(10) == 0) {
                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.75D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
            }

            if (this.smokey && rand.nextInt(5) == 0) {
                for(int i = 0; i < rand.nextInt(1) + 1; ++i) {
                    worldIn.addParticle(ParticleTypes.LAVA,
                            (double)pos.getX() + 0.5D,
                            (double)pos.getY() + 0.75D,
                            (double)pos.getZ() + 0.5D,
                            (double)(rand.nextFloat() / 2.0F), 5.0E-5D, (double)(rand.nextFloat() / 2.0F));
                }
            }
            List<LivingEntity> entitiesWithinAABB = worldIn.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.down(1).east(2).north(2), pos.up(2).west(2).south(2)));
            for(LivingEntity living:entitiesWithinAABB){

                ItemStack activeItemStack = living.getActiveItemStack();
                if(activeItemStack.getItem()!=null && activeItemStack.getItem().getClass()==BowItem.class){
                    try{
                        BowItem bow = (BowItem)activeItemStack.getItem();
                        ItemStack itemstack = living.findAmmo(activeItemStack);
                        //todo 火箭效果 沥青地面 木制挡板

                        AbstractArrowEntity abstractArrowEntity = (AbstractArrowEntity) itemstack.getAttachedEntity();
                        abstractArrowEntity.setFire(100);
                        System.out.println(abstractArrowEntity);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }


            }
        }
    }



    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IWorld iworld = context.getWorld();
        BlockPos blockpos = context.getPos();
        boolean flag = iworld.getFluidState(blockpos).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(flag)).with(SIGNAL_FIRE, Boolean.valueOf(this.isHayBlock(iworld.getBlockState(blockpos.down())))).with(LIT, Boolean.valueOf(!flag)).with(FACING, context.getPlacementHorizontalFacing());
    }
    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return facing == Direction.DOWN ? stateIn.with(SIGNAL_FIRE, Boolean.valueOf(this.isHayBlock(facingState))) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {

        if(PotionEntity.class==projectile.getClass()){
            PotionEntity p1=(PotionEntity)projectile;
            try {
                ItemStack p3 = p1.getItem();
                System.out.println(p3.getTag().get("Potion").getClass());
                if(p3.getTag().get("Potion").toString().equals("\"minecraft:water\"")){
                    worldIn.setBlockState(hit.getPos(), state.with(BlockStateProperties.LIT, Boolean.valueOf(false)), 11);
                }
            }catch (Exception e){
                System.out.println(e);
            }
            System.out.println(p1.getItem().getItem().getClass());
        }else {
            if (!worldIn.isRemote && projectile.isBurning()) {
                Entity entity = projectile.func_234616_v_();
                boolean flag = entity == null || entity instanceof PlayerEntity || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entity);
                if (flag && !state.get(LIT) && !state.get(WATERLOGGED)) {
                    BlockPos blockpos = hit.getPos();
                    worldIn.setBlockState(blockpos, state.with(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
                }
            }
        }
    }
    /**
     * Returns true if the block of the passed blockstate is a Hay block, otherwise false.
     */
    private boolean isHayBlock(BlockState stateIn) {
        return stateIn.isIn(Blocks.HAY_BLOCK);
    }

    public static boolean isLit(BlockState state) {
        return state.hasProperty(LIT) && state.isIn(BlockTags.CAMPFIRES) && state.get(LIT);
    }


    public static void spawnSmokeParticles(World worldIn, BlockPos pos, boolean isSignalFire, boolean spawnExtraSmoke) {
        Random random = worldIn.getRandom();
        BasicParticleType basicparticletype = isSignalFire ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
        worldIn.addOptionalParticle(basicparticletype, true, (double)pos.getX() + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
        if (spawnExtraSmoke) {
            worldIn.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.25D + random.nextDouble() / 2.0D * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4D, (double)pos.getZ() + 0.25D + random.nextDouble() / 2.0D * (double)(random.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
        }
    }


    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }



}
