package com.dmzsabjacket;

import com.mojang.logging.LogUtils;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

/**
 * S.A.B. Jacket for DragonMine Z. Model and textures by Novatsugs (Novatsugs Extra Clothes), used with the creator's permission.
 */
@Mod(DmzSabJacket.MOD_ID)
public class DmzSabJacket {

    public static final String MOD_ID = "dmz_sab_jacket";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<Item> SAB_JACKET = ITEMS.register("sab_jacket",
            () -> new SabJacketItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<CreativeModeTab> TAB = TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MOD_ID))
                    .icon(() -> SAB_JACKET.get().getDefaultInstance())
                    .displayItems((parameters, output) -> output.accept(SAB_JACKET.get()))
                    .build());

    public DmzSabJacket() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        TABS.register(bus);
        bus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Washing the jacket in a water cauldron removes the dye, like leather armor.
        event.enqueueWork(() -> CauldronInteraction.WATER.put(SAB_JACKET.get(), CauldronInteraction.DYED_ITEM));
    }
}
