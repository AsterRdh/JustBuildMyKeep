package com.aster.justbuildmykeep.creature.villages;

import com.aster.justbuildmykeep.Blocks;
import com.aster.justbuildmykeep.Utils;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = Utils.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
@ObjectHolder(Utils.MOD_ID)
public class PointOfInterestTypes {
    public static final PointOfInterestType BANKER = null;
    public static final PointOfInterestType GUARD = null;
    @SubscribeEvent
    public static void registerPointOfInterestTypes(Register<PointOfInterestType> event) {
        IForgeRegistry<PointOfInterestType> registry = event.getRegistry();
        registry.register(VillagerUtil.pointOfInterestType("banker", VillagerUtil.getAllStates(net.minecraft.block.Blocks.CRAFTING_TABLE), 1, 1).setRegistryName(Utils.MOD_ID, "banker"));
        registry.register(VillagerUtil.pointOfInterestType("guard", VillagerUtil.getAllStates(Blocks.WOOD_GIRDER), 1, 1).setRegistryName(Utils.MOD_ID, "guard"));


    }
}
