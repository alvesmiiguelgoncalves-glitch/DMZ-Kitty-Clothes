package com.dmzkittyclothes;

import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

/**
 * Curios item (slot "jacket"). Dyeable with the vanilla leather armor recipe (crafting table with dye).
 * Without dye it renders with the original blue texture; dyed, it uses the grayscale texture tinted by the color.
 */
public class SabJacketItem extends Item implements ICurioItem, DyeableLeatherItem {

    public SabJacketItem(Properties properties) {
        super(properties);
    }
}
