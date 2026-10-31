package com.eggy.trumpetskeleton;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;



public class TrumpetSkeletonModel extends SkeletonModel<TrumpetSkeletonRenderState> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TrumpetSkeleton.MODID, "trumpet_skeleton"),
            "main"
    );

    public TrumpetSkeletonModel (ModelPart root){
        super(root);
    }

    @Override
    public void setupAnim(TrumpetSkeletonRenderState state) {
        boolean isLeftHanded = state.isLeftHanded;

        if (state.isUsingItem) {
            if (isLeftHanded) {
                state.leftArmPose = HumanoidModel.ArmPose.TOOT_HORN;
                state.rightArmPose = HumanoidModel.ArmPose.EMPTY;
            } else {
                state.rightArmPose = HumanoidModel.ArmPose.TOOT_HORN;
                state.leftArmPose = HumanoidModel.ArmPose.EMPTY;
            }
        } else {
            if (isLeftHanded) {
                state.leftArmPose = HumanoidModel.ArmPose.ITEM;
                state.rightArmPose = HumanoidModel.ArmPose.EMPTY;
            } else {
                state.rightArmPose = HumanoidModel.ArmPose.ITEM;
                state.leftArmPose = HumanoidModel.ArmPose.EMPTY;
            }
        }

        super.setupAnim(state);

        if (state.isUsingItem) {
            if (isLeftHanded) {
                this.rightArm.xRot = this.leftArm.xRot;
                this.rightArm.yRot = -this.leftArm.yRot;
                this.rightArm.zRot = -this.leftArm.zRot;
            } else {
                this.leftArm.xRot = this.rightArm.xRot;
                this.leftArm.yRot = -this.rightArm.yRot;
                this.leftArm.zRot = -this.rightArm.zRot;
            }
        } else {
            this.rightArm.xRot = (float) -(Math.PI / 10) + this.rightArm.xRot * 0.5f;
            this.leftArm.xRot = (float) -(Math.PI / 10) + this.leftArm.xRot * 0.5f;
        }
    }

}
