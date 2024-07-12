package com.mayon.createlegacy.blocks.decoration;

import com.mayon.createlegacy.CreateLegacy;
import com.mayon.createlegacy.blocks.item.ItemBlockVariants;
import com.mayon.createlegacy.mainRegistry.ModBlocks;
import com.mayon.createlegacy.mainRegistry.ModItems;
import com.mayon.createlegacy.util.IHasModel;
import com.mayon.createlegacy.util.IMetaName;
import com.mayon.createlegacy.util.handlers.EnumHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class BlockStoneBricksFancy extends Block implements IHasModel, IMetaName {
    public static final PropertyEnum<EnumHandler.DecoStoneEnumType> VARIANT = PropertyEnum.<EnumHandler.DecoStoneEnumType>create("variant", EnumHandler.DecoStoneEnumType.class);

    private static final String name = "stone_bricks_fancy";

    public BlockStoneBricksFancy() {
        super(Material.ROCK);
        setUnlocalizedName("create:" + name);
        setRegistryName(name);
        setCreativeTab(CreateLegacy.TAB_CREATE_DECORATIONS);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.DecoStoneEnumType.ASURINE));
        setHarvestLevel("pickaxe", 1);
        setHarvestLevel("pickaxe", 0, this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.DecoStoneEnumType.CALCITE));
        setHarvestLevel("pickaxe", 0, this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.DecoStoneEnumType.TUFF));
        setHardness(5f);
        setResistance(20f);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumHandler.DecoStoneEnumType) state.getValue(VARIANT)).getMeta();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumHandler.DecoStoneEnumType) state.getValue(VARIANT)).getMeta();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumHandler.DecoStoneEnumType.byMetaData(meta));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumHandler.DecoStoneEnumType variant : EnumHandler.DecoStoneEnumType.values()) {
            items.add(new ItemStack(this, 1, variant.getMeta()));
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return "_" + EnumHandler.DecoStoneEnumType.values()[stack.getItemDamage()].getName();
    }

    @Override
    public void registerModels() {
        for (int i = 0; i < EnumHandler.DecoStoneEnumType.values().length; i++) {
            String s = EnumHandler.DecoStoneEnumType.values()[i].getName();
            CreateLegacy.proxy.registerVariantRenderer(Item.getItemFromBlock(this),
                    i, "stone/stone_bricks_fancy_" + s, "inventory");

        }
    }
}
