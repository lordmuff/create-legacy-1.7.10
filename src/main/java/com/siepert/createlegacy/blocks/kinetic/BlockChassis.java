package com.siepert.createlegacy.blocks.kinetic;

import com.siepert.createlegacy.CreateLegacy;
import com.siepert.createlegacy.blocks.item.ItemBlockVariants;
import com.siepert.createlegacy.mainRegistry.ModBlocks;
import com.siepert.createlegacy.mainRegistry.ModItems;
import com.siepert.createlegacy.util.IHasModel;
import com.siepert.createlegacy.util.IMetaName;
import com.siepert.createlegacy.util.handlers.EnumHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class BlockChassis extends Block implements IHasModel {
   public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class);

    public static final PropertyBool STICKY_TOP = PropertyBool.create("sticky_top");
    public static final PropertyBool STICKY_BOTTOM = PropertyBool.create("sticky_bottom");

    public BlockChassis(String name) {
        super(Material.WOOD);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreateLegacy.TAB_CREATE);
        setDefaultState(this.blockState.getBaseState()
                .withProperty(STICKY_TOP, false).withProperty(STICKY_BOTTOM, false)
                .withProperty(AXIS, EnumFacing.Axis.Y));
        setHarvestLevel("pickaxe", 0);
        setHardness(1);
        setResistance(2);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int toReturn = 0;
        if (state.getValue(STICKY_TOP)) toReturn++;
        if (state.getValue(STICKY_BOTTOM)) toReturn += 2;
        switch (state.getValue(AXIS)) {
            case X:
                toReturn += 4;
                break;
            case Z:
                toReturn += 8;
                break;
            default:
                break;
        }
        return toReturn;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        switch (meta) {
            case 0:
                return this.getDefaultState();
            case 1:
                return this.getDefaultState().withProperty(STICKY_TOP, true);
            case 2:
                return this.getDefaultState().withProperty(STICKY_BOTTOM, true);
            case 3:
                return this.getDefaultState().withProperty(STICKY_TOP, true).withProperty(STICKY_BOTTOM, true);

            case 4:
                return this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.X);
            case 5:
                return this.getDefaultState().withProperty(STICKY_TOP, true).withProperty(AXIS, EnumFacing.Axis.X);
            case 6:
                return this.getDefaultState().withProperty(STICKY_BOTTOM, true).withProperty(AXIS, EnumFacing.Axis.X);
            case 7:
                return this.getDefaultState().withProperty(STICKY_TOP, true).withProperty(STICKY_BOTTOM, true)
                        .withProperty(AXIS, EnumFacing.Axis.X);

            case 8:
                return this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Z);
            case 9:
                return this.getDefaultState().withProperty(STICKY_TOP, true).withProperty(AXIS, EnumFacing.Axis.Z);
            case 10:
                return this.getDefaultState().withProperty(STICKY_BOTTOM, true).withProperty(AXIS, EnumFacing.Axis.Z);
            case 11:
                return this.getDefaultState().withProperty(STICKY_TOP, true).withProperty(STICKY_BOTTOM, true)
                        .withProperty(AXIS, EnumFacing.Axis.Z);
            default:
                return this.getDefaultState();
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {AXIS, STICKY_TOP, STICKY_BOTTOM});
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(playerIn.getHeldItem(hand).getItem() == Items.SLIME_BALL) {
            worldIn.setBlockState(pos, state.withProperty(STICKY_BOTTOM, true).withProperty(STICKY_TOP, true), 0);
            worldIn.markBlockRangeForRenderUpdate(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1,
                    pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
            return true;
        }
        return false;
    }

    @Override
    public void registerModels() {
        CreateLegacy.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }


    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing,
                                            float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        EnumFacing.Axis axis = facing.getAxis();
        if (world.getBlockState(pos.offset(facing.getOpposite())).getBlock() == this) {
            return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand)
                    .withProperty(AXIS, world.getBlockState(pos.offset(facing.getOpposite())).getValue(AXIS));
        }
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(AXIS, axis);
    }
}