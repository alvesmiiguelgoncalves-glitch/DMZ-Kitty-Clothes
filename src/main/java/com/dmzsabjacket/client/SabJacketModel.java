package com.dmzsabjacket.client;

import com.dmzsabjacket.DmzSabJacket;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

/**
 * S.A.B. Jacket model, converted from the Bedrock geometry "overcoat" (128x128 texture).
 * Every box sits on one of the vanilla humanoid parts, so it follows the player's pose.
 */
public class SabJacketModel extends HumanoidModel<LivingEntity> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            new ResourceLocation(DmzSabJacket.MOD_ID, "sab_jacket"), "main");

    public SabJacketModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -8F, -4F, 8F, 8F, 8F, new CubeDeformation(0.2F)).texOffs(64, 0).addBox(-4.5F, -1F, -4.5F, 9F, 3F, 9F, new CubeDeformation(0.1F)), PartPose.offset(0F, 0F, 0F));
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4F, 0F, -2F, 8F, 12F, 4F, new CubeDeformation(0.3F)).texOffs(24, 16).addBox(-4F, 0F, -2F, 8F, 12F, 4F, new CubeDeformation(0.4F)).texOffs(68, 26).addBox(-4.5F, 9.5F, -3F, 9F, 2F, 6F, new CubeDeformation(0.1F)), PartPose.offset(0F, 0F, 0F));
        PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(48, 16).addBox(-3F, -2F, -2F, 4F, 12F, 4F, new CubeDeformation(0.21F)).texOffs(32, 48).addBox(-3F, -2F, -2F, 4F, 12F, 4F, new CubeDeformation(0.4F)), PartPose.offset(-5F, 2F, 0F));
        PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 0).addBox(-1F, -2F, -2F, 4F, 12F, 4F, new CubeDeformation(0.3F)).texOffs(16, 48).addBox(-1F, -2F, -2F, 4F, 12F, 4F, new CubeDeformation(0.4F)), PartPose.offset(5F, 2F, 0F));
        PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(86, 12).addBox(-3F, -1F, -3F, 5F, 8F, 6F, new CubeDeformation(0.3F)), PartPose.offset(-1.9F, 12F, 0F));
        PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(64, 12).addBox(-2F, -1F, -3F, 5F, 8F, 6F, new CubeDeformation(0.3F)), PartPose.offset(1.9F, 12F, 0F));
        // Bedrock rotation x=-37.5 deg; Java xRot negative tilts the lower edge forward.
        body.addOrReplaceChild("breasts", CubeListBuilder.create().texOffs(106, 26).addBox(-4F, 0F, 0F, 8F, 4F, 3F, new CubeDeformation(0.29F)).texOffs(106, 33).mirror().addBox(-4F, 0F, 0F, 8F, 4F, 3F, new CubeDeformation(0.39F)).mirror(false), PartPose.offsetAndRotation(0F, 1F, -2F, -0.6545F, 0.0F, 0.0F));
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(mesh, 128, 128);
    }

    /** Copies the pose of the player model that is being rendered (DMZ keeps it in sync with its animation). */
    public void followPose(HumanoidModel<?> base) {
        this.head.copyFrom(base.head);
        this.body.copyFrom(base.body);
        this.rightArm.copyFrom(base.rightArm);
        this.leftArm.copyFrom(base.leftArm);
        this.rightLeg.copyFrom(base.rightLeg);
        this.leftLeg.copyFrom(base.leftLeg);
        this.hat.copyFrom(base.hat);
        this.head.visible = true;
        this.body.visible = true;
        this.rightArm.visible = true;
        this.leftArm.visible = true;
        this.rightLeg.visible = true;
        this.leftLeg.visible = true;
        this.hat.visible = false;
    }
}
