package com.aster.justbuildmykeep.events;

import com.aster.justbuildmykeep.JustBuildMyKeep;
import com.aster.justbuildmykeep.entity.SitEntity;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


public class SitClient {

    public static void onFMLCLientSetup(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(JustBuildMyKeep.SIT_ENTITY_TYPE, EmptyRenderer::new);
    }

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
