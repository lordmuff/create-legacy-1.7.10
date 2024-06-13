package com.siepert.createlegacy.tileentity;

import com.siepert.createlegacy.util.IKineticActor;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

import static com.siepert.createlegacy.blocks.kinetic.BlockHandCrank.FACING;
import static com.siepert.createlegacy.blocks.kinetic.BlockHandCrank.ACTIVATED;

public class TileEntityHandCrank extends TileEntity implements ITickable {
    @Override
    public void update() {
        IBlockState myState = world.getBlockState(pos);

        Block block = world.getBlockState(pos.offset(myState.getValue(FACING))).getBlock();
        if (block instanceof IKineticActor && myState.getValue(ACTIVATED)) {
            List<BlockPos> iteratedBlocks = new ArrayList<>(); //Generate the iteratedBlocks list for using
            ((IKineticActor) block).passRotation(world, pos.offset(myState.getValue(FACING)), myState.getValue(FACING).getOpposite(),
                    iteratedBlocks, false, false);
            world.markBlockRangeForRenderUpdate(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1,
                    pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
            world.setBlockState(pos, myState.withProperty(ACTIVATED, false), 0);
        }
    }


}