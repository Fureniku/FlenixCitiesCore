package com.silvaniastudios.cities.core.blocks;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockGlassFakeWall extends StyledGlass {
	
	private boolean isLit;

	public BlockGlassFakeWall(String name, CreativeTabs tab, boolean lit) {
		super(name, tab);
		this.isLit = lit;
	}
	
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST  = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST  = PropertyBool.create("west");
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.withProperty(NORTH, canConnectTo(world, pos.offset(EnumFacing.NORTH)))
				.withProperty(EAST,  canConnectTo(world, pos.offset(EnumFacing.EAST)))
				.withProperty(SOUTH, canConnectTo(world, pos.offset(EnumFacing.SOUTH)))
				.withProperty(WEST,  canConnectTo(world, pos.offset(EnumFacing.WEST)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, META_ID, NORTH, EAST, SOUTH, WEST);
	}
	
	@SuppressWarnings("deprecation")
	protected boolean canConnectTo(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock().isFullBlock(state) || state.getBlock() instanceof BlockGlassFakeWall) {
			return true;
		}
		return false;
	}
	
	@Override
    public int getLightValue(IBlockState state) {
		if (isLit) {
			return 12;
		}
        return 0;
    }
}
