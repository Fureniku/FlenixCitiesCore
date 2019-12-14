package com.silvaniastudios.cities.core.blocks;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSlope extends CitiesBlockBase {
	
	public static final PropertyEnum<EnumRotationType> ROTATION = PropertyEnum.create("rotation", EnumRotationType.class);

	public BlockSlope(String name) {
		super(name, Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, EnumRotationType.FLOOR_NORTH));
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
    	int vertical_rot = 0;
    	if (placer.getHorizontalFacing().equals(EnumFacing.EAST))  { rot = 1; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) { rot = 2; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.WEST))  { rot = 3; }
    	if (facing.equals(EnumFacing.UP)) {
    		vertical_rot = 0;
    	} else if (facing.equals(EnumFacing.DOWN)) {
    		vertical_rot = 4;
    	} else {
    		vertical_rot = 8;
    	}
    	
        return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, rot+vertical_rot, placer);
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
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}


	public enum EnumRotationType implements IStringSerializable {
		FLOOR_NORTH(0, "floor_north"),
		FLOOR_EAST (1, "floor_east"),
		FLOOR_SOUTH(2, "floor_south"),
		FLOOR_WEST (3, "floor_west"),
		CEILING_NORTH(4, "ceiling_north"),
		CEILING_EAST (5, "ceiling_east"),
		CEILING_SOUTH(6, "ceiling_south"),
		CEILING_WEST (7, "ceiling_west"),
		SIDE_NE(8,  "side_ne"),
		SIDE_SE(9,  "side_se"),
		SIDE_SW(10, "side_sw"),
		SIDE_NW(11, "side_nw");
		
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
