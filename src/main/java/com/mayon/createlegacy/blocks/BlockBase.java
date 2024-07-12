package com.mayon.createlegacy.blocks;

import com.mayon.createlegacy.CreateLegacy;
import com.mayon.createlegacy.mainRegistry.ModBlocks;
import com.mayon.createlegacy.mainRegistry.ModItems;
import com.mayon.createlegacy.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/** BlockBase does the unspeakable:
 * not share registries and have subtypes!
 * Please don't use this :3c */
@Deprecated
public class BlockBase extends Block implements IHasModel {
    public BlockBase(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreateLegacy.TAB_CREATE);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        CreateLegacy.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
