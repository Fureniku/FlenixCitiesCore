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

public class BlockFence extends CitiesBlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumWalkway> WALKWAY_TYPE = PropertyEnum.create("walkway_type", EnumWalkway.class);
	public static final PropertyEnum<EnumWalkwayRotation> ROTATION = PropertyEnum.create("rotation", EnumWalkwayRotation.class);
	public static final PropertyEnum<EnumWalkwaySide> LEFT = PropertyEnum.create("left", EnumWalkwaySide.class);
	public static final PropertyEnum<EnumWalkwaySide> RIGHT = PropertyEnum.create("right", EnumWalkwaySide.class);
    //public static final PropertyBool UP = PropertyBool.create("up");
    
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D,   0.0D, 0.875D, 1.0D,   1.0D,   1.0D);
    public static final AxisAlignedBB WEST_AABB  = new AxisAlignedBB(0.0D,   0.0D,   0.0D, 0.125D, 1.0D,   1.0D);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D,   0.0D,   0.0D, 1.0D,   1.0D, 0.125D);
    public static final AxisAlignedBB EAST_AABB  = new AxisAlignedBB(0.875D, 0.0D,   0.0D, 1.0D,   1.0D,   1.0D);

	public BlockFence(String name, Material mat) {
		super(name, mat);
		this.setCreativeTab(FurenikusCities.tabCity);
		this.setHardness(2.0F);
		this.setResistance(12.0F);
		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(WALKWAY_TYPE, EnumWalkway.STANDARD)
				.withProperty(LEFT, EnumWalkwaySide.NORMAL)
				.withProperty(RIGHT, EnumWalkwaySide.NORMAL));
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
		
		EnumFacing up = EnumFacing.NORTH;
		EnumFacing left = EnumFacing.WEST;
		EnumFacing right = EnumFacing.EAST;
		EnumFacing down = EnumFacing.SOUTH;
		
		EnumWalkwayRotation wrLeft = EnumWalkwayRotation.WEST;
		EnumWalkwayRotation wrRight = EnumWalkwayRotation.EAST;
		
		if (thisRot.equals(EnumWalkwayRotation.NORTH)) {
			up = EnumFacing.NORTH;
			left = EnumFacing.WEST;
			right = EnumFacing.EAST;
			down = EnumFacing.SOUTH;
			
			wrLeft = EnumWalkwayRotation.WEST;
			wrRight = EnumWalkwayRotation.EAST;
		}
		
		if (thisRot.equals(EnumWalkwayRotation.EAST)) {
			up = EnumFacing.EAST;
			left = EnumFacing.NORTH;
			right = EnumFacing.SOUTH;
			down = EnumFacing.WEST;
			
			wrLeft = EnumWalkwayRotation.NORTH;
			wrRight = EnumWalkwayRotation.SOUTH;
		}
		
		if (thisRot.equals(EnumWalkwayRotation.SOUTH)) {
			up = EnumFacing.SOUTH;
			left = EnumFacing.EAST;
			right = EnumFacing.WEST;
			down = EnumFacing.NORTH;
			
			wrLeft = EnumWalkwayRotation.EAST;
			wrRight = EnumWalkwayRotation.WEST;
		}
		
		if (thisRot.equals(EnumWalkwayRotation.WEST)) {
			up = EnumFacing.WEST;
			left = EnumFacing.SOUTH;
			right = EnumFacing.NORTH;
			down = EnumFacing.EAST;
			
			wrLeft = EnumWalkwayRotation.SOUTH;
			wrRight = EnumWalkwayRotation.NORTH;
		}
		
		IBlockState stateUp    = world.getBlockState(pos.offset(up));
		IBlockState stateLeft  = world.getBlockState(pos.offset(left));
		IBlockState stateRight = world.getBlockState(pos.offset(right));
		IBlockState stateDown  = world.getBlockState(pos.offset(down));
		
		boolean upBlock    = stateUp.getBlock() instanceof BlockFence;
		boolean leftBlock  = stateLeft.getBlock() instanceof BlockFence;
		boolean rightBlock = stateRight.getBlock() instanceof BlockFence;
		boolean downBlock  = stateDown.getBlock() instanceof BlockFence;
		
		/*if (stateUp.getBlock()    instanceof BlockFence || world.getBlockState(pos.offset(up)   .offset(EnumFacing.DOWN)) instanceof BlockWalkwayStairs) { upBlock    = true; }
		if (stateLeft.getBlock()  instanceof BlockFence || world.getBlockState(pos.offset(left) .offset(EnumFacing.DOWN)) instanceof BlockWalkwayStairs) { leftBlock  = true; }
		if (stateRight.getBlock() instanceof BlockFence || world.getBlockState(pos.offset(right).offset(EnumFacing.DOWN)) instanceof BlockWalkwayStairs) { rightBlock = true; }
		if (stateDown.getBlock()  instanceof BlockFence || world.getBlockState(pos.offset(down) .offset(EnumFacing.DOWN)) instanceof BlockWalkwayStairs) { downBlock  = true; }*/
		
		if (upBlock && leftBlock) {
			if (stateUp.getValue(ROTATION).equals(wrLeft) && stateLeft.getValue(ROTATION).equals(thisRot)) {
				if (checkingLeft) {
					return EnumWalkwaySide.CORNER;
				} else {
					return EnumWalkwaySide.NONE;
				}
			}
		}
		
		if (upBlock && rightBlock) {
			if (stateUp.getValue(ROTATION).equals(wrRight) && stateRight.getValue(ROTATION).equals(thisRot)) {
				if (checkingLeft) {
					return EnumWalkwaySide.NONE;
				} else {
					return EnumWalkwaySide.CORNER;
				}
			}
		}
		
		if (downBlock && !leftBlock && !upBlock && checkingLeft) {
			if (stateDown.getValue(ROTATION).equals(wrLeft)) {
				return EnumWalkwaySide.DOWN;
			}
		}
		
		if (downBlock && !rightBlock && !upBlock && !checkingLeft) {
			if (stateDown.getValue(ROTATION).equals(wrRight)) {
				return EnumWalkwaySide.DOWN;
			}
		}
        
		return EnumWalkwaySide.NORMAL;
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
		
		if (n) {
			if (left .equals(EnumWalkwaySide.DOWN)) { w = true; }
			if (right.equals(EnumWalkwaySide.DOWN)) { e = true; }
		} else if (e) { 
			if (left .equals(EnumWalkwaySide.DOWN)) { n = true; }
			if (right.equals(EnumWalkwaySide.DOWN)) { s = true; }
		} else if (s) {
			if (left .equals(EnumWalkwaySide.DOWN)) { e = true; }
			if (right.equals(EnumWalkwaySide.DOWN)) { w = true; }
		} else if (w) {
			if (left .equals(EnumWalkwaySide.DOWN)) { s = true; }
			if (right.equals(EnumWalkwaySide.DOWN)) { n = true; }
		}
		
		if (n && (left.equals(EnumWalkwaySide.CORNER) || right.equals(EnumWalkwaySide.CORNER))) { n = false; }
		if (e && (left.equals(EnumWalkwaySide.CORNER) || right.equals(EnumWalkwaySide.CORNER))) { e = false; }
		if (s && (left.equals(EnumWalkwaySide.CORNER) || right.equals(EnumWalkwaySide.CORNER))) { s = false; }
		if (w && (left.equals(EnumWalkwaySide.CORNER) || right.equals(EnumWalkwaySide.CORNER))) { w = false; }
		
		if ((n && e && s) || (n && w && s) || (n && e && w) || (n && e && s && w)) { 
			System.out.println("Broken! NESW: " + n + e + s + w);
		}
		
		if (n) { addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB); }
		if (e) { addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB); }
		if (s) { addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB); }
		if (w) { addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB); }
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
		
		if (n && left .equals(EnumWalkwaySide.DOWN)) { return FULL_BLOCK_AABB; }
		if (n && right.equals(EnumWalkwaySide.DOWN)) { return FULL_BLOCK_AABB; }
		
		if (e && left .equals(EnumWalkwaySide.DOWN)) { return FULL_BLOCK_AABB; }
		if (e && right.equals(EnumWalkwaySide.DOWN)) { return FULL_BLOCK_AABB; }
		
		if (s && left .equals(EnumWalkwaySide.DOWN)) { return FULL_BLOCK_AABB; }
		if (s && right.equals(EnumWalkwaySide.DOWN)) { return FULL_BLOCK_AABB; }
		
		if (w && left .equals(EnumWalkwaySide.DOWN)) { return FULL_BLOCK_AABB; }
		if (w && right.equals(EnumWalkwaySide.DOWN)) { return FULL_BLOCK_AABB; }
		
		if (n &&  left.equals(EnumWalkwaySide.CORNER)) { return new AxisAlignedBB(0.0D,   0.0D, 0.0D,   0.125D, 1.0D, 0.125D); }
		if (n && right.equals(EnumWalkwaySide.CORNER)) { return new AxisAlignedBB(0.875D, 0.0D, 0.0D,     1.0D, 1.0D, 0.125D); }
		
		if (e &&  left.equals(EnumWalkwaySide.CORNER)) { return new AxisAlignedBB(0.875D, 0.0D, 0.0D,     1.0D, 1.0D, 0.125D); }
		if (e && right.equals(EnumWalkwaySide.CORNER)) { return new AxisAlignedBB(0.875D, 0.0D, 0.875D,   1.0D, 1.0D,   1.0D); }
		
		if (s &&  left.equals(EnumWalkwaySide.CORNER)) { return new AxisAlignedBB(0.875D, 0.0D, 0.875D,   1.0D, 1.0D,   1.0D); }
		if (s && right.equals(EnumWalkwaySide.CORNER)) { return new AxisAlignedBB(  0.0D, 0.0D, 0.875D, 0.125D, 1.0D,   1.0D); }
		
		if (w &&  left.equals(EnumWalkwaySide.CORNER)) { return new AxisAlignedBB(  0.0D, 0.0D, 0.875D, 0.125D, 1.0D,   1.0D); }
		if (w && right.equals(EnumWalkwaySide.CORNER)) { return new AxisAlignedBB(  0.0D, 0.0D,   0.0D, 0.125D, 1.0D, 0.125D); }
		
		if (n) { return NORTH_AABB; }
		if (e) { return EAST_AABB;  }
		if (s) { return SOUTH_AABB; }
		if (w) { return WEST_AABB;  }
		
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
	
	public enum EnumWalkwaySide implements IStringSerializable {
		NORMAL(0, "normal"),
		DOWN(1, "down"),
		CORNER(2, "corner"),
		NONE(3, "none");
		
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
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
