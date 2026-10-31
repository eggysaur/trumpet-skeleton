package com.eggy.trumpetskeleton;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class TrumpetSkeletonRenderer extends HumanoidMobRenderer<TrumpetSkeletonEntity, TrumpetSkeletonRenderState, TrumpetSkeletonModel> {
    private static final ResourceLocation SKELETON_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png");

    public TrumpetSkeletonRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new TrumpetSkeletonModel(context.bakeLayer(TrumpetSkeletonModel.LAYER_LOCATION)),
                0.5F
        );
    }

    @Override
    public ResourceLocation getTextureLocation(TrumpetSkeletonRenderState entity) {
        return SKELETON_LOCATION;
    }

    @Override
    public TrumpetSkeletonRenderState createRenderState() {
        return new TrumpetSkeletonRenderState();
    }

    @Override
    public void extractRenderState(TrumpetSkeletonEntity entity, TrumpetSkeletonRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.isLeftHanded = entity.isLeftHanded();
        state.isUsingItem = entity.isUsingItem();
    }
}
