package com.aster.justbuildmykeep.events;

import com.aster.justbuildmykeep.container.GoodsBaxContainer;
import com.aster.justbuildmykeep.container.GoodsBoxContainerScreen;
import com.aster.justbuildmykeep.container.ObsidianFirstContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEventHandler {
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ScreenManager.registerFactory(ContainerTypeRegistry.obsidianFirstContainer.get(), ObsidianFirstContainerScreen::new);
        });
        event.enqueueWork(() -> {
            ScreenManager.registerFactory(ContainerTypeRegistry.goodsBoxContainer.get(), GoodsBoxContainerScreen::new);
        });
    }
}

