package com.aster.justbuildmykeep;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderTypeRegistry {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(Blocks.FIRE_BASKET, RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(Blocks.STAINED_GLASS_A, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Blocks.STAINED_GLASS_B, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Blocks.STAINED_GLASS_C, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Blocks.STAINED_GLASS_D, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Blocks.STAINED_GLASS_E, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Blocks.STAINED_GLASS_F, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Blocks.STAINED_GLASS_G, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Blocks.STAINED_GLASS_H, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Blocks.STAINED_GLASS_I, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Blocks.STAINED_GLASS_J, RenderType.getTranslucent());
        });
    }
}
