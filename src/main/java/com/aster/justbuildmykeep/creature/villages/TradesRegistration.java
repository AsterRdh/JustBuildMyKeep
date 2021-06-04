package com.aster.justbuildmykeep.creature.villages;
import com.aster.justbuildmykeep.Utils;
import net.minecraft.item.Items;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Utils.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class TradesRegistration {
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event){
        if (event.getType() == VillagerProfessions.BANKER){
            event.getTrades().get(1).add((new RandomTradeBuilder(64, 25, 0F).setPrice(Items.EMERALD, 4,4).setForSale(Items.GOLD_INGOT, 1, 1).build()));
            event.getTrades().get(1).add((new RandomTradeBuilder(64, 25, 0F).setPrice(Items.GOLD_INGOT, 1,1).setForSale(Items.EMERALD, 4, 4).build()));
            
        }else if(event.getType() == VillagerProfessions.GUARD){
            event.getTrades().get(1).add((new RandomTradeBuilder(64, 25, 0.2F).setPrice(Items.IRON_INGOT, 1,1).setPrice2(Items.STONE, 8,8).setForSale(Items.IRON_ORE, 1, 2).build()));

        }
    }
}
