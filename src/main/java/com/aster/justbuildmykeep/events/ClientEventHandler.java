package com.aster.justbuildmykeep.events;

import com.aster.justbuildmykeep.JustBuildMyKeep;
import com.aster.justbuildmykeep.entity.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void onFMLCLientSetup(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeRegistry.emblemShieldEntity.get(), (tileEntityRendererDispatcher -> {
            return new EmblemShieldTER(tileEntityRendererDispatcher);
        }));
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeRegistry.WEAPON_RACK_ENTITIES.get(), (tileEntityRendererDispatcher -> {
            return new WeaponRackTER(tileEntityRendererDispatcher);
        }));
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeRegistry.ARROW_BARREL_ENTITIES.get(), (tileEntityRendererDispatcher -> {
            return new ArrowBarrelTER(tileEntityRendererDispatcher);
        }));
//        RenderingRegistry.registerEntityRenderingHandler(JustBuildMyKeep.SIT_ENTITY_TYPE,
//                (EntityRendererManager manager) -> { return new EmptyRenderer(manager);
//        });
        RenderingRegistry.registerEntityRenderingHandler(JustBuildMyKeep.SIT_ENTITY_TYPE, EmptyRenderer::new);

    }
//    @SubscribeEvent
//    public static void onClientSetupEvent(FMLClientSetupEvent event) {
//        event.enqueueWork(() -> {
//            ScreenManager.registerFactory(ContainerTypeRegistry.obsidianFirstContainer.get(), ObsidianFirstContainerScreen::new);
//        });
//    }


    private static class EmptyRenderer extends EntityRenderer<SitEntity>
    {
        protected EmptyRenderer(EntityRendererManager renderManager)
        {
            super(renderManager);
        }

        @Override
        public boolean shouldRender(SitEntity entity, ClippingHelper camera, double camX, double camY, double camZ)
        {
            return false;
        }

        @Override
        public ResourceLocation getEntityTexture(SitEntity entity)
        {
            return null;
        }
    }
}
