package com.silvaniastudios.cities.core.blocks;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSteepSlope extends CitiesBlockBase {
	
	public static final PropertyEnum<EnumRotationType> ROTATION = PropertyEnum.create("rotation", EnumRotationType.class);

	public BlockSteepSlope(String name) {
		super(name, Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, EnumRotationType.A_FLOOR_NORTH));
		this.setCreativeTab(FurenikusCities.tabBlocks);
	}

	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
    	int rot = 0;
    	int flip = 0;
    	if (placer.getHorizontalFacing().equals(EnumFacing.EAST))  { rot = 1; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) { rot = 2; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.WEST))  { rot = 3; }
    	
    	if (facing.equals(EnumFacing.DOWN)) { flip = 8; }
    	
        return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta+rot+flip, placer);
    }
    
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(ROTATION, EnumRotationType.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
		EnumRotationType rot = state.getValue(ROTATION);
        return rot.getMetadata();
    }
    
    @Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, ROTATION);
	}
	
	@Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 4));
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = getMetaFromState(state);
		
		if (meta == 4 || meta == 12) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D); }
		if (meta == 5 || meta == 13) { return new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D); }
		if (meta == 6 || meta == 14) { return new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D); }
		if (meta == 7 || meta == 15) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D); }
		
        return FULL_BLOCK_AABB;
    }
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	public enum EnumRotationType implements IStringSerializable {
		A_FLOOR_NORTH(0, "a_floor_north"),
		A_FLOOR_EAST (1, "a_floor_east"),
		A_FLOOR_SOUTH(2, "a_floor_south"),
		A_FLOOR_WEST (3, "a_floor_west"),
		B_FLOOR_NORTH(4, "b_floor_north"),
		B_FLOOR_EAST (5, "b_floor_east"),
		B_FLOOR_SOUTH(6, "b_floor_south"),
		B_FLOOR_WEST (7, "b_floor_west"),
		A_TOP_NORTH(8,  "a_top_north"),
		A_TOP_EAST (9,  "a_top_east"),
		A_TOP_SOUTH(10, "a_top_south"),
		A_TOP_WEST (11, "a_top_west"),
		B_TOP_NORTH(12, "b_top_north"),
		B_TOP_EAST (13, "b_top_east"),
		B_TOP_SOUTH(14, "b_top_south"),
		B_TOP_WEST (15, "b_top_west");
		
		private static final EnumRotationType[] META_LOOKUP = new EnumRotationType[values().length];
		private final int meta;
		private final String name;
		
		private EnumRotationType(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
		public int getMetadata() {
	        return this.meta;
	    }
		
		public static EnumRotationType byMetadata(int meta) {
	        if (meta < 0 || meta >= META_LOOKUP.length) {
	            meta = 0;
	        }
	        
	        return META_LOOKUP[meta];
	    }
		
		static {
	        for (EnumRotationType type: values()) {
	            META_LOOKUP[type.getMetadata()] = type;
	        }
	    }
	}
}
