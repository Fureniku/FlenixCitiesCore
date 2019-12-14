package com.silvaniastudios.cities.core.blocks;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DecorativeSigns extends CitiesBlockBase {
	
	public static final PropertyEnum<EnumMetaRotate> META_ID = PropertyEnum.create("block", EnumMetaRotate.class);
	
	double voxelWidth;
	double voxelHeight;
	double voxelBottom;
	double voxelDepth;

	public DecorativeSigns(String name, double voxelWidth, double voxelHeight, double voxelBottom, double voxelDepth, int light) {
		super(name, Material.IRON);
		this.voxelWidth = voxelWidth;
		this.voxelHeight = voxelHeight;
		this.voxelBottom = voxelBottom;
		this.voxelDepth = voxelDepth;
		this.lightValue = light;
		this.setCreativeTab(FurenikusCities.tabDecorative);
		this.setHardness(2.2F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMetaRotate.id0));
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
    	int rot = 0;
    	if (placer.getHorizontalFacing().equals(EnumFacing.EAST))  { rot = 1; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) { rot = 2; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.WEST))  { rot = 3; }
    	
        return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta+rot, placer);
    }


	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(state.getBlock(), 1, 0);
    }
	
    @Override
    public int damageDropped(IBlockState state) {
    	return this.getMetaFromState(state);
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {META_ID});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(META_ID, EnumMetaRotate.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumMetaRotate)state.getValue(META_ID)).getMetadata();
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = getMetaFromState(state);
		double v = 1.0/16.0; //one voxel
		double left = (1-voxelWidth*v)/2;
		if (meta == 0) {  return new AxisAlignedBB(left, voxelBottom*v,  0.0D, left + voxelWidth*v, voxelBottom*v + voxelHeight*v,        voxelDepth*v); }
		if (meta == 1) {  return new AxisAlignedBB(1.0D, voxelBottom*v,  left, 1.0D - voxelDepth*v, voxelBottom*v + voxelHeight*v, left + voxelWidth*v); }
		if (meta == 2) {  return new AxisAlignedBB(left, voxelBottom*v,  1.0D, left + voxelWidth*v, voxelBottom*v + voxelHeight*v, 1.0D - voxelDepth*v); }
		if (meta == 3) {  return new AxisAlignedBB(0.0D, voxelBottom*v,  left,        voxelDepth*v, voxelBottom*v + voxelHeight*v, left + voxelWidth*v); }

		return FULL_BLOCK_AABB;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}