package com.silvaniastudios.cities.core.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWallDecorativeConnecting extends CitiesBlockBase implements IMetaBlockName {

	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("block", EnumMeta.class);
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST  = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST  = PropertyBool.create("west");
	public static final PropertyBool UP    = PropertyBool.create("up");
	public static final PropertyBool UP_R  = PropertyBool.create("r_up");
	
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D,    0.0D,    0.9375D, 1.0D,    1.0D, 1.0D);
    public static final AxisAlignedBB WEST_AABB  = new AxisAlignedBB(0.0D,    0.0D,    0.0D,    0.0625D, 1.0D, 1.0D);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D,    0.0D,    0.0D,    1.0D,    1.0D, 0.0625D);
    public static final AxisAlignedBB EAST_AABB  = new AxisAlignedBB(0.9375D, 0.0D,    0.0D,    1.0D,    1.0D, 1.0D);
    public static final AxisAlignedBB UP_AABB    = new AxisAlignedBB(0.0D,    0.9375D, 0.0D,    1.0D,    1.0D, 1.0D);
	
	public BlockWallDecorativeConnecting(String name) {
		super(name, Material.ROCK);
		this.setCreativeTab(FurenikusCities.tabBlocks);
		this.setHardness(2.2F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0).withProperty(NORTH, true));
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		for (int meta = 0; meta < 16; meta++) {
			list.add(new ItemStack(this, 1, meta));
		}
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(state.getBlock(), 1, this.getMetaFromState(state));
    }
	
    @Override
    public int damageDropped(IBlockState state) {
    	return this.getMetaFromState(state);
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
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean n = canConnectTo(world, pos.offset(EnumFacing.NORTH), EnumFacing.SOUTH);
		boolean e = canConnectTo(world, pos.offset(EnumFacing.EAST), EnumFacing.WEST);
		boolean s = canConnectTo(world, pos.offset(EnumFacing.SOUTH), EnumFacing.NORTH);
		boolean w = canConnectTo(world, pos.offset(EnumFacing.WEST), EnumFacing.EAST);
		boolean u = canConnectTo(world, pos.offset(EnumFacing.UP), EnumFacing.DOWN);
		boolean u_r = u && (e || w) && (!n && !s);
		
		//If none of them connect, enable *all* of them, just so something will render at least and it doesnt look dumb.
		if (!n && !e && !s && !w && !u) {
			return state.withProperty(NORTH, true)
					.withProperty(EAST,  true)
					.withProperty(SOUTH, true)
					.withProperty(WEST,  true)
					.withProperty(UP, true)
					.withProperty(UP_R, false);
		}
		
		return state.withProperty(NORTH, n)
				.withProperty(EAST,  e)
				.withProperty(SOUTH, s)
				.withProperty(WEST,  w)
				.withProperty(UP, u)
				.withProperty(UP_R, u_r);
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		if (!isActualState) {
			state = state.getActualState(world, pos);
		}
		
		boolean n = canConnectTo(world, pos.offset(EnumFacing.NORTH), EnumFacing.SOUTH);
		boolean e = canConnectTo(world, pos.offset(EnumFacing.EAST), EnumFacing.WEST);
		boolean s = canConnectTo(world, pos.offset(EnumFacing.SOUTH), EnumFacing.NORTH);
		boolean w = canConnectTo(world, pos.offset(EnumFacing.WEST), EnumFacing.EAST);
		boolean u = canConnectTo(world, pos.offset(EnumFacing.UP), EnumFacing.DOWN);
		
		if (!n && !e && !s && !w && !u) { addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB); }

		if (n) { addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB); }
		if (e) { addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);  }
		if (s) { addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB); }
		if (w) { addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);  }
		if (u) { addCollisionBoxToList(pos, entityBox, collidingBoxes, UP_AABB);    }
	}
    
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		boolean n = canConnectTo(source, pos.offset(EnumFacing.NORTH), EnumFacing.SOUTH);
		boolean e = canConnectTo(source, pos.offset(EnumFacing.EAST), EnumFacing.WEST);
		boolean s = canConnectTo(source, pos.offset(EnumFacing.SOUTH), EnumFacing.NORTH);
		boolean w = canConnectTo(source, pos.offset(EnumFacing.WEST), EnumFacing.EAST);
		boolean u = canConnectTo(source, pos.offset(EnumFacing.UP), EnumFacing.DOWN);
		
		if ( n && !e && !s && !w && !u) { return NORTH_AABB; }
		if (!n &&  e && !s && !w && !u) { return EAST_AABB;  }
		if (!n && !e &&  s && !w && !u) { return SOUTH_AABB; }
		if (!n && !e && !s &&  w && !u) { return WEST_AABB;  }
		if (!n && !e && !s && !w &&  u) { return UP_AABB;  }
		
        return FULL_BLOCK_AABB;
    }
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, META_ID, NORTH, EAST, SOUTH, WEST, UP, UP_R);
	}
	
	protected boolean canConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		IBlockState state = world.getBlockState(pos);
		return state.getBlockFaceShape(world, pos, facing) == BlockFaceShape.SOLID;
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
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		for (int meta = 0; meta < 16; meta++) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), meta, new ModelResourceLocation(getRegistryName(), "inventory_" + meta));
		}
	}
}
