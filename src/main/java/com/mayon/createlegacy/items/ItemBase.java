package com.mayon.createlegacy.items;

import com.mayon.createlegacy.CreateLegacy;
import com.mayon.createlegacy.mainRegistry.ModItems;
import com.mayon.createlegacy.util.IHasModel;
import net.minecraft.item.Item;
/** ItemBase doesn't allow subtypes and doesn't do anything special.
 * Please make a custom item class instead!*/
@Deprecated
public class ItemBase extends Item implements IHasModel {

    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreateLegacy.TAB_CREATE);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        CreateLegacy.proxy.registerItemRenderer(this, 0, "inventory");
    }
}