package com.silvaniastudios.cities.core.blocks;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GenericDecorativeRotatable extends CitiesBlockBase {
	
	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("block", EnumMeta.class);

	public GenericDecorativeRotatable(String name) {
		super(name, Material.IRON);
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
    	int rot = 0;
    	if (placer.getHorizontalFacing().equals(EnumFacing.EAST))  { rot = 1; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) { rot = 2; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.WEST))  { rot = 3; }
    	
        return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta+rot, placer);
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 4));
		list.add(new ItemStack(this, 1, 8));
		list.add(new ItemStack(this, 1, 12));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		int meta = this.getMetaFromState(state);
		if (meta < 4) { return new ItemStack(state.getBlock(), 1, 0); }
		if (meta < 8) { return new ItemStack(state.getBlock(), 1, 4); }
		if (meta < 12) { return new ItemStack(state.getBlock(), 1, 8); }
		return new ItemStack(state.getBlock(), 1, 12);
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
        return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumMeta)state.getValue(META_ID)).getMetadata();
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = getMetaFromState(state);
		
		if (meta == 0) {  return new AxisAlignedBB(0.4375D,   0.9375D,  0.0D,       0.5625D,   1.0D, 1.0D     ); } //simple strip
		if (meta == 1) {  return new AxisAlignedBB(0.0D,      0.9375D,  0.4375D,    1.0D,      1.0D, 0.5625D  ); } //simple strip
		if (meta == 2) {  return new AxisAlignedBB(0.4375D,   0.9375D,  0.25D,      0.5625D,   1.0D, 0.75D    ); } //simple strip short
		if (meta == 3) {  return new AxisAlignedBB(0.25D,     0.9375D,  0.4375D,    0.75D,     1.0D, 0.5625D  ); } //simple strip short
		if (meta == 4) {  return new AxisAlignedBB(0.453125D, 0.78125D, 0.0D,       0.546875D, 1.0D, 1.0D     ); } //down
		if (meta == 5) {  return new AxisAlignedBB(0.0D,      0.78125D, 0.453125D,  1.0D,      1.0D, 0.546875D); } //down
		if (meta == 6) {  return new AxisAlignedBB(0.375D,    0.78125D, 0.0D,       0.625D,    1.0D, 1.0D     ); } //angled
		if (meta == 7) {  return new AxisAlignedBB(0.0D,      0.78125D, 0.375D,     1.0D,      1.0D, 0.625D   ); } //angled
		if (meta == 8) {  return new AxisAlignedBB(0.34375D,  0.78125D, 0.0D,       0.65625D,  1.0D, 1.0D     ); } //industrial
		if (meta == 9) {  return new AxisAlignedBB(0.0D,      0.78125D, 0.34375D,   1.0D,      1.0D, 0.65625D ); } //industrial
		if (meta == 10) { return new AxisAlignedBB(0.25D,     0.78125D, 0.0D,       0.75D,     1.0D, 1.0D     ); } //industrial large
		if (meta == 11) { return new AxisAlignedBB(0.0D,      0.78125D, 0.25D,      1.0D,      1.0D, 0.75D    ); } //industrial large
		if (meta == 12) { return new AxisAlignedBB(0.359375D, 0.484375D, 0.0D,      0.640625D, 1.0D, 1.0D     ); } //hanging black
		if (meta == 13) { return new AxisAlignedBB(0.0D,      0.484375D, 0.359375D, 1.0D,      1.0D, 0.640625D); } //hanging black
		if (meta == 14) { return new AxisAlignedBB(0.359375D, 0.484375D, 0.0D,      0.640625D, 1.0D, 1.0D     ); } //hanging white
		if (meta == 15) { return new AxisAlignedBB(0.0D,      0.484375D, 0.359375D, 1.0D,      1.0D, 0.640625D); } //hanging white

		return FULL_BLOCK_AABB;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}