package com.aster.justbuildmykeep.creature.villages;

import com.aster.justbuildmykeep.Utils;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Utils.MOD_ID, bus = Bus.MOD)
@ObjectHolder(Utils.MOD_ID)
public class VillagerProfessions {
    public static final  VillagerProfession BANKER=null;
    public static final  VillagerProfession GUARD=null;
    @SubscribeEvent
    public static void registerVillagerProfessions(Register<VillagerProfession> event)
    {
        IForgeRegistry<VillagerProfession> registry = event.getRegistry();
        registry.register(VillagerUtil.villagerProfession("banker", PointOfInterestTypes.BANKER, ImmutableSet.of(), ImmutableSet.of(), null).setRegistryName(Utils.MOD_ID, "banker"));
        registry.register(VillagerUtil.villagerProfession("guard", PointOfInterestTypes.GUARD, ImmutableSet.of(), ImmutableSet.of(), null).setRegistryName(Utils.MOD_ID, "guard"));

    }

}
