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
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LampConeBlock extends CitiesBlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumLightTypeRot> META_ID = PropertyEnum.create("light", EnumLightTypeRot.class);

	public LampConeBlock(String name) {
		super(name, Material.IRON);
		this.setCreativeTab(FurenikusCities.tabLighting);
		this.setHardness(2.2F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumLightTypeRot.FLOOR_1));
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
   	
    @Override
    public int getLightValue(IBlockState state) {
		return 15;
    }
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
    	int rot = 0;
    	if (facing == EnumFacing.SOUTH) { rot = 1; }
    	if (facing == EnumFacing.WEST) {  rot = 2; }
    	if (facing == EnumFacing.NORTH) { rot = 3; }
    	if (facing == EnumFacing.EAST) {  rot = 4; }
    	if (facing == EnumFacing.DOWN) {  rot = 5; }
    	
        return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta+rot, placer);
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 6));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		int meta = this.getMetaFromState(state);
		if (meta < 6) {
			return new ItemStack(state.getBlock(), 1, 0);
		}
		return new ItemStack(state.getBlock(), 1, 6);
    }
	
    @Override
    public int damageDropped(IBlockState state) {
    	if (this.getMetaFromState(state) < 6) {
    		return 0;
    	}
    	return 6;
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {META_ID});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(META_ID, EnumLightTypeRot.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumLightTypeRot)state.getValue(META_ID)).getMetadata();
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = getMetaFromState(state);
		
		if (meta == 0) 				 { return new AxisAlignedBB(0.3125D,   0.0D,    0.3125D,   0.6875D,   0.578125D, 0.6875D  ); }
		if (meta == 1 || meta == 7)  { return new AxisAlignedBB(0.421875D, 0.375D,  0.0D,      0.578125D, 0.640625D, 0.140625D); } 
		if (meta == 2 || meta == 8)  { return new AxisAlignedBB(0.859375D, 0.375D,  0.421875D, 1.0D,      0.640625D, 0.578125D); } 
		if (meta == 3 || meta == 9)  { return new AxisAlignedBB(0.421875D, 0.375D,  0.859375D, 0.578125D, 0.640625D, 1.0D     ); }  
		if (meta == 4 || meta == 10) { return new AxisAlignedBB(0.0D,      0.375D,  0.421875D, 0.140625D, 0.640625D, 0.578125D); } 
		if (meta == 5)  			 { return new AxisAlignedBB(0.3125D,   0.639375D,   0.3125D,   0.6875D,   1.0D,      0.6875D  ); }
		if (meta == 6) 				 { return new AxisAlignedBB(0.3125D,   0.0D,    0.3125D,   0.6875D,   0.65625,   0.6875D  ); }
		if (meta == 11) 			 { return new AxisAlignedBB(0.3125D,   0.68625D, 0.3125D,   0.6875D,   1.0D,      0.6875D  ); }

		return FULL_BLOCK_AABB;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	public enum EnumLightTypeRot implements IStringSerializable {
		FLOOR_1(0, "1_floor"),
		NORTH_1(1, "1_n"),
		EAST_1 (2, "1_e"),
		SOUTH_1(3, "1_s"),
		WEST_1 (4, "1_w"),
		TOP_1  (5, "1_top"),
		FLOOR_2(6, "2_floor"),
		NORTH_2(7, "2_n"),
		EAST_2 (8, "2_e"),
		SOUTH_2(9, "2_s"),
		WEST_2 (10, "2_w"),
		TOP_2  (11, "2_top");
		
		private static final EnumLightTypeRot[] META_LOOKUP = new EnumLightTypeRot[values().length];
		private final int meta;
		private final String name;
		
		private EnumLightTypeRot(int meta, String name) {
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
		
		public static EnumLightTypeRot byMetadata(int meta) {
	        if (meta < 0 || meta >= META_LOOKUP.length) {
	            meta = 0;
	        }
	        
	        return META_LOOKUP[meta];
	    }
		
		static {
	        for (EnumLightTypeRot type: values()) {
	            META_LOOKUP[type.getMetadata()] = type;
	        }
	    }
	}
}