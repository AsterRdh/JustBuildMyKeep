package com.aster.justbuildmykeep;

import com.aster.justbuildmykeep.blocks.*;
import com.aster.justbuildmykeep.entity.SitEntity;
import com.aster.justbuildmykeep.entity.TileEntityTypeRegistry;

import com.aster.justbuildmykeep.events.ContainerTypeRegistry;
import com.aster.justbuildmykeep.items.TestItem;
import com.aster.justbuildmykeep.setup.ModSetup;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("justbuildmykeep")
public class JustBuildMyKeep {

    private static final Logger LOGGER = LogManager.getLogger();
    private static ModSetup setup=new ModSetup();
    @ObjectHolder(Utils.MOD_ID + ":entity_sit")
    public static final EntityType<SitEntity> SIT_ENTITY_TYPE = null;
    public JustBuildMyKeep() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        TileEntityTypeRegistry.TILE_ENTITIES.register(eventBus);
        ContainerTypeRegistry.CONTAINERS.register(eventBus);

    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
    }


    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            blockRegistryEvent.getRegistry().register(new TestBlock());
            blockRegistryEvent.getRegistry().register(new TestArrowBlock());
            blockRegistryEvent.getRegistry().register(new BattlementBottom());
            blockRegistryEvent.getRegistry().register(new BattlementTop1());
            blockRegistryEvent.getRegistry().register(new LoopHoleBig());
            blockRegistryEvent.getRegistry().register(new LoopholeSmall());
            blockRegistryEvent.getRegistry().register(new WoodBarrel());
            blockRegistryEvent.getRegistry().register(new EmblemShield());
            blockRegistryEvent.getRegistry().register(new StoneBrickPillarCorner());
            blockRegistryEvent.getRegistry().register(new StoneBrickPillarMiddle());
            blockRegistryEvent.getRegistry().register(new BattlementBottomCorner());
            blockRegistryEvent.getRegistry().register(new BattlementTopFlatCorner());
            blockRegistryEvent.getRegistry().register(new CounterWeigh());
            //blockRegistryEvent.getRegistry().register(new GreatWoodGate());
            blockRegistryEvent.getRegistry().register(new FireBasket());
            blockRegistryEvent.getRegistry().register(new LoopholeTiny());
            blockRegistryEvent.getRegistry().register(new CrossOrnaments());
            blockRegistryEvent.getRegistry().register(new HalfStoneBrick1());
            blockRegistryEvent.getRegistry().register(new HalfStoneBrick2());
            blockRegistryEvent.getRegistry().register(new StoneDoorFrameTopSmall());
            blockRegistryEvent.getRegistry().register(new StoneDoorFrameL());
            blockRegistryEvent.getRegistry().register(new StoneDoorFrameR());
            blockRegistryEvent.getRegistry().register(new WoodWindow());
            blockRegistryEvent.getRegistry().register(new StoneDoorFrameBigTop());
            blockRegistryEvent.getRegistry().register(new StoneGateHouseBigTop());
            blockRegistryEvent.getRegistry().register(new WoodTableA());
            blockRegistryEvent.getRegistry().register(new WoodChairA());
            blockRegistryEvent.getRegistry().register(new WeaponRack());
            blockRegistryEvent.getRegistry().register(new ArrowBarrel());
            blockRegistryEvent.getRegistry().register(new WoodGirder());
            blockRegistryEvent.getRegistry().register(new VerticalStoneStairs());
            blockRegistryEvent.getRegistry().register(new WoodHandrail());
            blockRegistryEvent.getRegistry().register(new PitchDitch());
            blockRegistryEvent.getRegistry().register(new ConcretePile());
            blockRegistryEvent.getRegistry().register(new ConcreteWall1());
            blockRegistryEvent.getRegistry().register(new ConcreteWall2());
            blockRegistryEvent.getRegistry().register(new ConcreteWall3());
            blockRegistryEvent.getRegistry().register(new ConcreteWall4());
            blockRegistryEvent.getRegistry().register(new ConcreteWall5());
            blockRegistryEvent.getRegistry().register(new ConcreteWall6());
            blockRegistryEvent.getRegistry().register(new ConcreteWall7());
            blockRegistryEvent.getRegistry().register(new ConcreteWall8());
            blockRegistryEvent.getRegistry().register(new ConcreteWall9());
            blockRegistryEvent.getRegistry().register(new ConcreteWall10());
            blockRegistryEvent.getRegistry().register(new ConcreteWall11());
            blockRegistryEvent.getRegistry().register(new ConcreteWall12());
            blockRegistryEvent.getRegistry().register(new ConcreteWall13());
            blockRegistryEvent.getRegistry().register(new ConcreteWall14());
            blockRegistryEvent.getRegistry().register(new ConcreteWall15());
            blockRegistryEvent.getRegistry().register(new ConcreteWall16());
            blockRegistryEvent.getRegistry().register(new ConcreteWall17());
            blockRegistryEvent.getRegistry().register(new ConcreteWall18());
            blockRegistryEvent.getRegistry().register(new ConcreteWall19());
            blockRegistryEvent.getRegistry().register(new ConcreteWall20());
            blockRegistryEvent.getRegistry().register(new WoodEave());
        }


        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            Item.Properties properties=new Item.Properties().group(setup.getItemGroup());
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.TESTBLOCK, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","testblock"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.TESTARROWBLOCK, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","test_arrow_block"));

            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.BATTLEMENT_BOTTOM, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","battlement_bottom"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.BATTLEMENT_TOP_FLAT, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","battlement_top_flat"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.LOOPHOLE_BIG, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","loophole_big"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.LOOPHOLE_SMALL, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","loophole_small"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.WOOD_BARREL, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","wood_barrel"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.COUNTER_WEIGHT, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","counter_weigh"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.EMBLEM_SHIELD, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","emblem_shield"));
            //itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.GREAT_WOOD_GATE, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","great_wood_gate"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.FIRE_BASKET, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","fire_basket"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.LOOPHOLE_TINY, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","loophole_tiny"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.HALF_STONE_BRICK1, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","half_stone1"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.HALF_STONE_BRICK2, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","half_stone2"));

            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.STONE_DOOR_FRAME_TOP_SMALL, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","stone_door_frame_top_small"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.STONE_DOOR_FRAME_L, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","stone_door_frame_left"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.STONE_DOOR_FRAME_R, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","stone_door_frame_right"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.STONE_DOOR_FRAME_BIG_TOP, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","stone_door_frame_big_top"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.STONE_GATEHOUSE_BIG_TOP, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","stone_gatehouse_big_top"));

            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.WOOD_WINDOW, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","wood_window"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.WOOD_TABLE1, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","wood_table_a"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.WOOD_CHAIR1, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","wood_chair_a"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.WEAPON_RACK, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","weapon_rack"));

            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CROSS_ORNAMENTS,new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","cross_ornament_small"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.BATTLEMENT_BUTTON_CORNER, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","battlement_bottom_corner"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.STONE_BRICK_PILLAR_CORNER, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","stone_brick_pillar_corner"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.STONE_BRICK_PILLAR_MIDDLE, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","stone_brick_pillar_middle"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.BATTLEMENT_TOP_FLAT_CORNER, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","battlement_top_flat_corner"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.ARROW_BARREL, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","arrow_barrel"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.WOOD_GIRDER, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","wood_girder"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.VERTICAL_STONE_STAIRS, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","vertical_stone_stairs"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.WOOD_HANDRAIL, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","wood_handrail"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.PITCH_DITCH, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","pitch_ditch"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_PILE, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_pile"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL1, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall1"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL2, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall2"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL3, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall3"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL4, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall4"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL5, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall5"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL6, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall6"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL7, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall7"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL8, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall8"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL9, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall9"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL10, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall10"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL11, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall11"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL12, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall12"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL13, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall13"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL14, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall14"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL15, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall15"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL16, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall16"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL17, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall17"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL18, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall18"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL19, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall19"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CONCRETE_WALL20, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","concrete_wall20"));
            itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.WOOD_EAVE, new Item.Properties().group(setup.getItemGroup())).setRegistryName("justbuildmykeep","wood_eave"));
            itemRegistryEvent.getRegistry().register(new TestItem(setup));
        }

        @SubscribeEvent
        public static void registerEntity(RegistryEvent.Register<EntityType<?>> event)
        {
            event.getRegistry().register(EntityType.Builder.<SitEntity>create(SitEntity::new, EntityClassification.MISC)
                    .setCustomClientFactory((spawnEntity, world) -> SIT_ENTITY_TYPE.create(world))
                    .setTrackingRange(256)
                    .setUpdateInterval(20)
                    .size(0.0001F, 0.0001F)
                    .build(Utils.MOD_ID + ":entity_sit")
                    .setRegistryName(new ResourceLocation(Utils.MOD_ID, "entity_sit")));
        }

    }
}
