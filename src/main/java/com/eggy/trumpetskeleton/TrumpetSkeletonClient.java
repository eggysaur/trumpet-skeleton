package com.eggy.trumpetskeleton;

import net.minecraft.client.model.SkeletonModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = TrumpetSkeleton.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = TrumpetSkeleton.MODID, value = Dist.CLIENT)

public class TrumpetSkeletonClient {
    public TrumpetSkeletonClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                TrumpetSkeleton.TRUMPET_SKELETON.get(),
                TrumpetSkeletonRenderer::new
        );
    }
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                TrumpetSkeletonModel.LAYER_LOCATION,
                SkeletonModel::createBodyLayer
        );
    }
}
