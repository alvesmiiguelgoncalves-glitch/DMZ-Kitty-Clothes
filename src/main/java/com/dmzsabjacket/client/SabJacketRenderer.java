package com.dmzsabjacket.client;

import com.dmzsabjacket.DmzSabJacket;
import com.dmzsabjacket.SabJacketItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

/**
 * Draws the jacket through the Curios renderer. DMZ forwards third-party layers (including Curios') to its own
 * player renderer, with the vanilla player model kept in sync with the DMZ animation.
 */
public class SabJacketRenderer implements ICurioRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(DmzSabJacket.MOD_ID, "textures/models/sab_jacket.png");
    private static final ResourceLocation TEXTURE_DYED = new ResourceLocation(DmzSabJacket.MOD_ID, "textures/models/sab_jacket_dyed.png");

    private final SabJacketModel model;

    public SabJacketRenderer(SabJacketModel model) {
        this.model = model;
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack,
                                                                          RenderLayerParent<T, M> parent, MultiBufferSource buffer, int light,
                                                                          float limbSwing, float limbSwingAmount, float partialTicks,
                                                                          float ageInTicks, float netHeadYaw, float headPitch) {
        if (!(stack.getItem() instanceof SabJacketItem item)) return;
        if (!(parent.getModel() instanceof HumanoidModel<?> base)) return;

        model.young = false;
        model.crouching = base.crouching;
        model.followPose(base);

        boolean dyed = item.hasCustomColor(stack);
        int color = dyed ? item.getColor(stack) : 0xFFFFFF;
        float r = (color >> 16 & 0xFF) / 255.0F;
        float g = (color >> 8 & 0xFF) / 255.0F;
        float b = (color & 0xFF) / 255.0F;

        VertexConsumer consumer = ItemRenderer.getArmorFoilBuffer(buffer,
                RenderType.armorCutoutNoCull(dyed ? TEXTURE_DYED : TEXTURE), false, stack.hasFoil());
        model.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
    }
}
