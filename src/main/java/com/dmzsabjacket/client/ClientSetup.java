package com.dmzsabjacket.client;

import com.dmzsabjacket.DmzSabJacket;
import com.dmzsabjacket.SabJacketItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = DmzSabJacket.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientSetup {

    private ClientSetup() {
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SabJacketModel.LAYER, SabJacketModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CuriosRendererRegistry.register(DmzSabJacket.SAB_JACKET.get(),
                    () -> new SabJacketRenderer(new SabJacketModel(Minecraft.getInstance().getEntityModels().bakeLayer(SabJacketModel.LAYER))));
            // 1 = dyed: the item model switches to the grayscale icon, which is tinted with the dye color.
            ItemProperties.register(DmzSabJacket.SAB_JACKET.get(), new ResourceLocation(DmzSabJacket.MOD_ID, "dyed"),
                    (stack, level, entity, seed) -> stack.getItem() instanceof SabJacketItem item && item.hasCustomColor(stack) ? 1.0F : 0.0F);
        });
    }

    @SubscribeEvent
    public static void itemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            if (tintIndex == 0 && stack.getItem() instanceof DyeableLeatherItem dyeable && dyeable.hasCustomColor(stack)) {
                return dyeable.getColor(stack);
            }
            return 0xFFFFFF;
        }, DmzSabJacket.SAB_JACKET.get());
    }
}
