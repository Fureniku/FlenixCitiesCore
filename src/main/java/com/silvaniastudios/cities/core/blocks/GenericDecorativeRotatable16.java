package com.silvaniastudios.cities.core.blocks;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GenericDecorativeRotatable16 extends CitiesBlockBase {
	
	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("block", EnumMeta.class);
	
	double voxelWidth;
	double voxelHeight;
	
	public GenericDecorativeRotatable16(String name, double voxelWidth, double voxelHeight, int light) {
		super(name, Material.IRON);
		this.lightValue = light;
		this.voxelWidth = voxelWidth;
		this.voxelHeight = voxelHeight;
		this.setCreativeTab(FurenikusCities.tabDecorative);
		this.setHardness(2.2F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @Override
   	public EnumBlockRenderType getRenderType(IBlockState state) {
    	return EnumBlockRenderType.MODEL;
    }
   	
    @Override
   	public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
	
	@SuppressWarnings("deprecation")
	@Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		float y = placer.rotationYaw;
		//why doesnt minecraft do this by default...
		while (y < 0) {
			y += 360F;
		}
		while (y > 360) {
			y -= 360F;
		}
		
		
		System.out.println("Yaw on placement (offset): " + y);
		
    	int rot = 0;
    	for (float i = 0; i < 360F; i = i+22.5F) {
    		if (y >= i-11.25F && y < i+11.25F) {
    			System.out.println("For loop rotation. I is: " + i);
    			return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta+rot, placer);
    		} else {
    			rot++;
    		}
    	}

    	return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {META_ID});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumMeta)state.getValue(META_ID)).getMetadata();
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		double v = 1.0/16.0;
		double edge = (1-voxelWidth*v)/2;
		
		return new AxisAlignedBB(edge, 0.0D, edge, edge+voxelWidth*v, v*voxelHeight, edge+voxelWidth*v);
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}