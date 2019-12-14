package com.silvaniastudios.cities.core.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShears;
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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWalkwayStairs extends CitiesBlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumWalkway> WALKWAY_TYPE = PropertyEnum.create("walkway_type", EnumWalkway.class);
	public static final PropertyEnum<EnumWalkwayRotation> ROTATION = PropertyEnum.create("rotation", EnumWalkwayRotation.class);
	public static final PropertyEnum<EnumWalkwaySide> LEFT = PropertyEnum.create("left", EnumWalkwaySide.class);
	public static final PropertyEnum<EnumWalkwaySide> RIGHT = PropertyEnum.create("right", EnumWalkwaySide.class);
    
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D,   0.0D, 0.875D, 1.0D,   2.0D,   1.0D);
    public static final AxisAlignedBB WEST_AABB  = new AxisAlignedBB(0.0D,   0.0D,   0.0D, 0.125D, 2.0D,   1.0D);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D,   0.0D,   0.0D, 1.0D,   2.0D, 0.125D);
    public static final AxisAlignedBB EAST_AABB  = new AxisAlignedBB(0.875D, 0.0D,   0.0D, 1.0D,   2.0D,   1.0D);

	public BlockWalkwayStairs(String name, Material mat) {
		super(name, mat);
		this.setCreativeTab(FurenikusCities.tabCity);
		this.setHardness(2.0F);
		this.setResistance(12.0F);
		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(WALKWAY_TYPE, EnumWalkway.STANDARD)
				.withProperty(LEFT, EnumWalkwaySide.YES)
				.withProperty(RIGHT, EnumWalkwaySide.YES));
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
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
    	if (placer.getHorizontalFacing().equals(EnumFacing.EAST))  { rot = 1; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) { rot = 2; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.WEST))  { rot = 3; }
    	
        return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta+rot, placer);
    }
    
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (meta == 0) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.STANDARD).withProperty(ROTATION, EnumWalkwayRotation.NORTH); }
		if (meta == 1) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.STANDARD).withProperty(ROTATION, EnumWalkwayRotation.EAST);  }
		if (meta == 2) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.STANDARD).withProperty(ROTATION, EnumWalkwayRotation.SOUTH); }
		if (meta == 3) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.STANDARD).withProperty(ROTATION, EnumWalkwayRotation.WEST);  }
		
		if (meta == 4) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.SNOW).withProperty(ROTATION, EnumWalkwayRotation.NORTH); }
		if (meta == 5) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.SNOW).withProperty(ROTATION, EnumWalkwayRotation.EAST);  }
		if (meta == 6) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.SNOW).withProperty(ROTATION, EnumWalkwayRotation.SOUTH); }
		if (meta == 7) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.SNOW).withProperty(ROTATION, EnumWalkwayRotation.WEST);  }
		
		if (meta == 8)  { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.LEAF).withProperty(ROTATION, EnumWalkwayRotation.NORTH); }
		if (meta == 9)  { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.LEAF).withProperty(ROTATION, EnumWalkwayRotation.EAST);  }
		if (meta == 10) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.LEAF).withProperty(ROTATION, EnumWalkwayRotation.SOUTH); }
		if (meta == 11) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.LEAF).withProperty(ROTATION, EnumWalkwayRotation.WEST);  }
		
		if (meta == 12) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.LIGHT).withProperty(ROTATION, EnumWalkwayRotation.NORTH); }
		if (meta == 13) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.LIGHT).withProperty(ROTATION, EnumWalkwayRotation.EAST);  }
		if (meta == 14) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.LIGHT).withProperty(ROTATION, EnumWalkwayRotation.SOUTH); }
		if (meta == 15) { return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.LIGHT).withProperty(ROTATION, EnumWalkwayRotation.WEST);  }
     
		return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.STANDARD);
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
		EnumWalkway type = state.getValue(WALKWAY_TYPE);
		EnumWalkwayRotation rot = state.getValue(ROTATION);
		
		int t = 0;
		int r = 0;
		
		if (type.equals(EnumWalkway.SNOW)) { t = 4; }
		if (type.equals(EnumWalkway.LEAF)) { t = 8; }
		if (type.equals(EnumWalkway.LIGHT)) { t = 12; }
		
		if (rot.equals(EnumWalkwayRotation.EAST))  { r = 1; }
		if (rot.equals(EnumWalkwayRotation.SOUTH)) { r = 2; }
		if (rot.equals(EnumWalkwayRotation.WEST))  { r = 3; }
		
        return t+r;
    }
    
    @Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    
	private EnumWalkwaySide canWalkwayConnectTo(IBlockState state, IBlockAccess world, BlockPos pos, boolean checkingLeft) {
		EnumWalkwayRotation thisRot = state.getValue(ROTATION);
		
		EnumFacing left = EnumFacing.WEST;
		EnumFacing right = EnumFacing.EAST;
		
		if (thisRot.equals(EnumWalkwayRotation.NORTH)) {
			left = EnumFacing.WEST;
			right = EnumFacing.EAST;
		}
		
		if (thisRot.equals(EnumWalkwayRotation.EAST)) {
			left = EnumFacing.NORTH;
			right = EnumFacing.SOUTH;
		}
		
		if (thisRot.equals(EnumWalkwayRotation.SOUTH)) {
			left = EnumFacing.EAST;
			right = EnumFacing.WEST;
		}
		
		if (thisRot.equals(EnumWalkwayRotation.WEST)) {
			left = EnumFacing.SOUTH;
			right = EnumFacing.NORTH;
		}
		
		IBlockState stateLeft  = world.getBlockState(pos.offset(left));
		IBlockState stateRight = world.getBlockState(pos.offset(right));
		
		boolean leftBlock  = stateLeft.getBlock() instanceof BlockWalkwayStairs;
		boolean rightBlock = stateRight.getBlock() instanceof BlockWalkwayStairs;

		if (checkingLeft && leftBlock) { 
			return EnumWalkwaySide.NO;
		}
		if (!checkingLeft && rightBlock) {
			return EnumWalkwaySide.NO;
		}
        
		return EnumWalkwaySide.YES;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.withProperty(LEFT, canWalkwayConnectTo(state, world, pos, true))
				.withProperty(RIGHT,  canWalkwayConnectTo(state, world, pos, false));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, WALKWAY_TYPE, ROTATION, LEFT, RIGHT);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		if (!isActualState) {
			state = state.getActualState(worldIn, pos);
		}
		
		int meta = getMetaFromState(state);
		
		boolean n = (meta == 0 || meta == 4 || meta == 8 || meta == 12);
		boolean e = (meta == 1 || meta == 5 || meta == 9 || meta == 13);
		boolean s = (meta == 2 || meta == 6 || meta == 10 || meta == 14);
		boolean w = (meta == 3 || meta == 7 || meta == 11 || meta == 15);

		EnumWalkwaySide left  = state.getValue(LEFT);
		EnumWalkwaySide right = state.getValue(RIGHT);
		
		boolean north_barrier = false;
		boolean east_barrier  = false;
		boolean south_barrier = false;
		boolean west_barrier  = false;
		
		if (n && left .equals(EnumWalkwaySide.YES)) { west_barrier = true; }
		if (n && right.equals(EnumWalkwaySide.YES)) { east_barrier = true; }
		
		if (e && left .equals(EnumWalkwaySide.YES)) { north_barrier = true; }
		if (e && right.equals(EnumWalkwaySide.YES)) { south_barrier = true; }
		
		if (s && left .equals(EnumWalkwaySide.YES)) { east_barrier = true; }
		if (s && right.equals(EnumWalkwaySide.YES)) { west_barrier = true; }
		
		if (w && left .equals(EnumWalkwaySide.YES)) { south_barrier = true; }
		if (w && right.equals(EnumWalkwaySide.YES)) { north_barrier = true; }
		
		if (north_barrier) { addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB); }
		if (east_barrier)  { addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);  }
		if (south_barrier) { addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB); }
		if (west_barrier)  { addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);  }
		
		if (n) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D,  0.75D, 1.0D, 0.0625D,  1.0D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.25D, 0.5D,  1.0D, 0.3125D, 0.75D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.5D,  0.25D, 1.0D, 0.5625D,  0.5D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.75D, 0.0D,  1.0D, 0.8125D, 0.25D));
		}
		if (e) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D,  0.0D,  0.0D, 0.25D, 0.0625D, 1.0D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.5D,  0.3125D, 1.0D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.5D,  0.5D,  0.0D, 0.75D, 0.5625D, 1.0D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.75D, 0.75D, 0.0D, 1.0D,  0.8125D, 1.0D));
		}
		if (s) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D,  0.0D,  1.0D, 0.0625D, 0.25D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.25D, 0.25D, 1.0D, 0.3125D,  0.5D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.5D,  0.5D,  1.0D, 0.5625D, 0.75D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.75D, 0.75D, 1.0D, 0.8125D,  1.0D));
		}
		if (w) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.75D, 0.0D,  0.0D, 1.0D,  0.0625D, 1.0D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.5D,  0.25D, 0.0D, 0.75D, 0.3125D, 1.0D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25D, 0.5D,  0.0D, 0.5D,  0.5625D, 1.0D));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D,  0.75D, 0.0D, 0.25D, 0.8125D, 1.0D));
		}
	}
    
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = getMetaFromState(state);
		
		boolean n = (meta == 0 || meta == 4 || meta == 8 || meta == 12);
		boolean e = (meta == 1 || meta == 5 || meta == 9 || meta == 13);
		boolean s = (meta == 2 || meta == 6 || meta == 10 || meta == 14);
		boolean w = (meta == 3 || meta == 7 || meta == 11 || meta == 15);
		
		state = state.getActualState(source, pos);
		EnumWalkwaySide left  = state.getValue(LEFT);
		EnumWalkwaySide right = state.getValue(RIGHT);
		
		AxisAlignedBB STAIRS_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
		
		if (n && left .equals(EnumWalkwaySide.YES)) { return STAIRS_BLOCK_AABB; }
		if (n && right.equals(EnumWalkwaySide.YES)) { return STAIRS_BLOCK_AABB; }
		
		if (e && left .equals(EnumWalkwaySide.YES)) { return STAIRS_BLOCK_AABB; }
		if (e && right.equals(EnumWalkwaySide.YES)) { return STAIRS_BLOCK_AABB; }
		
		if (s && left .equals(EnumWalkwaySide.YES)) { return STAIRS_BLOCK_AABB; }
		if (s && right.equals(EnumWalkwaySide.YES)) { return STAIRS_BLOCK_AABB; }
		
		if (w && left .equals(EnumWalkwaySide.YES)) { return STAIRS_BLOCK_AABB; }
		
        return FULL_BLOCK_AABB;
    }
	
	@Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 4));
		items.add(new ItemStack(this, 1, 8));
		items.add(new ItemStack(this, 1, 12));
    }

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		ItemStack item = player.getHeldItemMainhand();
		IBlockState state = world.getBlockState(pos);
		if (!world.isRemote) {
			if (item != null) {
				//Shovel to remove snow
				if (item.getItem().getHarvestLevel(item, "shovel", player, state) > 0) {
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.SNOW)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.STANDARD));
						if (!player.capabilities.isCreativeMode) {
							world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+0.5, pos.getZ(), new ItemStack(Items.SNOWBALL)));
							item.attemptDamageItem(1, new Random(), (EntityPlayerMP) player);
							return;
						}
					}
				}
				//Shears to remove leaves
				if (item.getItem() instanceof ItemShears) {
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LEAF)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.STANDARD));
						if (!player.capabilities.isCreativeMode) {
							world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+0.5, pos.getZ(), new ItemStack(Blocks.LEAVES)));
							item.attemptDamageItem(1, new Random(), (EntityPlayerMP) player);
							return;
						}
					}
				}
				//???? to remove lights
				if (item.getItem() instanceof ItemShears) {
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LIGHT)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.STANDARD));
						if (!player.capabilities.isCreativeMode) {
							//world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+0.5, pos.getZ(), new ItemStack(Blocks.LEAVES)));
							item.attemptDamageItem(1, new Random(), (EntityPlayerMP) player);
							return;
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack item = player.getHeldItemMainhand();
		if (!world.isRemote) {
			if (item != null) {
				if (item.getItem().equals(Items.SNOWBALL)) {
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.STANDARD)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.SNOW));
						if (!player.capabilities.isCreativeMode) { item.setCount(item.getCount()-1); }
					}
				} else if (item.getItem().equals(Items.GLOWSTONE_DUST)) {
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.STANDARD)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LIGHT));
						if (!player.capabilities.isCreativeMode) { item.setCount(item.getCount()-1); }
					}
				} else if (item.getItem() instanceof ItemBlock) {
					ItemBlock itemBlock = (ItemBlock) item.getItem();
					if (itemBlock.getBlock().equals(Blocks.LEAVES)) {
						if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.STANDARD)) {
							world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LEAF));
							if (!player.capabilities.isCreativeMode) { item.setCount(item.getCount()-1); }
						}
					}
				}
			}
		}		
		return false;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
		if (world.isRaining()) {
			if (world.canSnowAt(pos, true)) {
				if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.STANDARD)) {
					world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.SNOW));
				}
			}
		}
	}
	
	@Override
    public int getLightValue(IBlockState state) {
		EnumWalkway walkway = state.getValue(WALKWAY_TYPE);
		if (walkway.equals(EnumWalkway.LIGHT)) {
			return 12;
		}
        return this.lightValue;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "standard"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 4, new ModelResourceLocation(getRegistryName(), "snow"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 8, new ModelResourceLocation(getRegistryName(), "leaf"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 12, new ModelResourceLocation(getRegistryName(), "light"));
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	public enum EnumWalkwaySide implements IStringSerializable {
		YES(0, "yes"),
		NO(1, "no");
		
		private static final EnumWalkwaySide[] META_LOOKUP = new EnumWalkwaySide[values().length];
		private final int meta;
		private final String name;
		
		private EnumWalkwaySide(int meta, String name) {
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
		
		public static EnumWalkwaySide byMetadata(int meta) {
	        if (meta < 0 || meta >= META_LOOKUP.length) {
	            meta = 0;
	        }
	        
	        return META_LOOKUP[meta];
	    }
		
		static {
	        for (EnumWalkwaySide type: values()) {
	            META_LOOKUP[type.getMetadata()] = type;
	        }
	    }
	}
	
	public enum EnumWalkwayRotation implements IStringSerializable {
		NORTH(0, "north"),
		EAST (1, "east"),
		SOUTH(2, "south"),
		WEST (3, "west");
		
		private static final EnumWalkwayRotation[] META_LOOKUP = new EnumWalkwayRotation[values().length];
		private final int meta;
		private final String name;
		
		private EnumWalkwayRotation(int meta, String name) {
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
		
		public static EnumWalkwayRotation byMetadata(int meta) {
	        if (meta < 0 || meta >= META_LOOKUP.length) {
	            meta = 0;
	        }
	        
	        return META_LOOKUP[meta];
	    }
		
		static {
	        for (EnumWalkwayRotation type: values()) {
	            META_LOOKUP[type.getMetadata()] = type;
	        }
	    }
	}
}
