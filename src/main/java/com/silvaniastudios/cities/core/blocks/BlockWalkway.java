package com.silvaniastudios.cities.core.blocks;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.silvaniastudios.cities.core.FlenixCities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWalkway extends CitiesBlockBase {
	
	public static final PropertyEnum<EnumWalkway> WALKWAY_TYPE = PropertyDirection.create("walkway_type", EnumWalkway.class);
    
	public static final ImmutableList<IProperty<Boolean>> CONNECTED_PROPERTIES = ImmutableList.copyOf(Stream.of(EnumFacing.VALUES).map(facing -> PropertyBool.create(facing.getName())).collect(Collectors.toList()));
    
    public static final AxisAlignedBB PLATE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
    
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 1.0D, 0.875D, 1.0D, 1.0D);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.125D, 0.0D, 1.0D, 1.0D);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0D);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(1.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.875D);

	public BlockWalkway(String name, Material mat) {
		super(name, mat);
		this.setCreativeTab(FlenixCities.tabCity);
		this.setHardness(2.0F);
		this.setResistance(12.0F);
		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED_PROPERTIES.get(0), true));
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
	
	private boolean canWalkwayConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        BlockPos other = pos.offset(facing);
        Block block = world.getBlockState(other).getBlock();
        return block.canBeConnectedTo(world, other, facing.getOpposite()) || canConnectTo(world, other, facing.getOpposite());
    }
	
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, facing);
        Block block = iblockstate.getBlock();
        return !isExceptBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID;
    }

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		for (final EnumFacing facing : EnumFacing.VALUES) {
			state = state.withProperty(CONNECTED_PROPERTIES.get(facing.getIndex()), canWalkwayConnectTo(world, pos, facing));
		}

		return state;
	}

	public final boolean isConnected(IBlockState state, EnumFacing facing) {
		return state.getValue(CONNECTED_PROPERTIES.get(facing.getIndex()));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, WALKWAY_TYPE, CONNECTED_PROPERTIES.get(0), CONNECTED_PROPERTIES.get(1), CONNECTED_PROPERTIES.get(2), CONNECTED_PROPERTIES.get(3), CONNECTED_PROPERTIES.get(4), CONNECTED_PROPERTIES.get(5));
	}

	
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        if (!isActualState) {
            state = state.getActualState(worldIn, pos);
        }

        if (!((Boolean)state.getValue(CONNECTED_PROPERTIES.get(0))).booleanValue()) {
        	addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATE_AABB);
        }

        if (!((Boolean)state.getValue(CONNECTED_PROPERTIES.get(2))).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
        }

        if (!((Boolean)state.getValue(CONNECTED_PROPERTIES.get(5))).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
        }

        if (!((Boolean)state.getValue(CONNECTED_PROPERTIES.get(3))).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
        }

        if (!((Boolean)state.getValue(CONNECTED_PROPERTIES.get(4))).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
        }
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(WALKWAY_TYPE, EnumWalkway.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumWalkway)state.getValue(WALKWAY_TYPE)).getMetadata();
    }
    
	@Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		for (int i = 0; i < WALKWAY_TYPE.getAllowedValues().size(); i++) {
			items.add(new ItemStack(this, 1, i));
		}
    }


   /* public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = this.getActualState(state, source, pos);
        return BOUNDING_BOXES[getBoundingBoxIdx(state)];
    }*/

    /**
     * Returns the correct index into boundingBoxes, based on what the fence is connected to.
     */
    /*private static int getBoundingBoxIdx(IBlockState state)
    {
        int i = 0;

        if (((Boolean)state.getValue(NORTH)).booleanValue())
        {
            i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(EAST)).booleanValue())
        {
            i |= 1 << EnumFacing.EAST.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(SOUTH)).booleanValue())
        {
            i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(WEST)).booleanValue())
        {
            i |= 1 << EnumFacing.WEST.getHorizontalIndex();
        }

        return i;
    }*/
	
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
						}
					}
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LEAF_SNOW)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LEAF));
						if (!player.capabilities.isCreativeMode) {
							world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+0.5, pos.getZ(), new ItemStack(Items.SNOWBALL)));
							item.attemptDamageItem(1, new Random(), (EntityPlayerMP) player);
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
						}
					}
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LEAF_SNOW)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.SNOW));
						if (!player.capabilities.isCreativeMode) {
							world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+0.5, pos.getZ(), new ItemStack(Blocks.LEAVES)));
							item.attemptDamageItem(1, new Random(), (EntityPlayerMP) player);
						}
					}
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LEAF_LIGHT)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LIGHT));
						if (!player.capabilities.isCreativeMode) {
							world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+0.5, pos.getZ(), new ItemStack(Blocks.LEAVES)));
							item.attemptDamageItem(1, new Random(), (EntityPlayerMP) player);
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
						}
					}
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LEAF_LIGHT)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LEAF));
						if (!player.capabilities.isCreativeMode) {
							//world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+0.5, pos.getZ(), new ItemStack(Blocks.LEAVES)));
							item.attemptDamageItem(1, new Random(), (EntityPlayerMP) player);
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
						if (!player.capabilities.isCreativeMode) {
							item.setCount(item.getCount()-1);
						}
					}
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LEAF)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LEAF_SNOW));
						if (!player.capabilities.isCreativeMode) {
							item.setCount(item.getCount()-1);
						}
					}
				} else if (item.getItem().equals(Items.GLOWSTONE_DUST)) {
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.STANDARD)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LIGHT));
						if (!player.capabilities.isCreativeMode) {
							item.setCount(item.getCount()-1);
						}
					}
					if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LEAF)) {
						world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LEAF_LIGHT));
						if (!player.capabilities.isCreativeMode) {
							item.setCount(item.getCount()-1);
						}
					}
				} else if (item.getItem() instanceof ItemBlock) {
					ItemBlock itemBlock = (ItemBlock) item.getItem();
					if (itemBlock.getBlock().equals(Blocks.LEAVES)) {
						if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.STANDARD)) {
							world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LEAF));
							if (!player.capabilities.isCreativeMode) {
								item.setCount(item.getCount()-1);
							}
						}
						if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.SNOW)) {
							world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LEAF_SNOW));
							if (!player.capabilities.isCreativeMode) {
								item.setCount(item.getCount()-1);
							}
						}
						if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LIGHT)) {
							world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LEAF_LIGHT));
							if (!player.capabilities.isCreativeMode) {
								item.setCount(item.getCount()-1);
							}
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
				if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LEAF)) {
					world.setBlockState(pos, state.withProperty(WALKWAY_TYPE, EnumWalkway.LEAF_SNOW));
				}
			}
		}
	}
	
	@Override
    public int getLightValue(IBlockState state) {
		if (state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LIGHT) || state.getValue(WALKWAY_TYPE).equals(EnumWalkway.LEAF_LIGHT)) {
			return 12;
		}
        return this.lightValue;
    }
}
