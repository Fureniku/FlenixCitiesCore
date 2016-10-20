package com.silvaniastudios.cities.core.blocks;

import java.util.List;
import java.util.Random;

import com.silvaniastudios.cities.core.CoreItems;
import com.silvaniastudios.cities.core.FlenixCities_Core;
import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWalkway extends Block {
	
	String textureName;
	String oversizeName;

	public BlockWalkway(Material mat, SoundType sound, String icon, String icon2) {
		super(mat);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.textureName = icon;
		this.oversizeName = icon2;
		this.setStepSound(sound);
		this.setHardness(2.0F);
		this.setResistance(12.0F);
		this.setTickRandomly(true);
	}
	
	@Override public boolean renderAsNormalBlock() { return false; }
	@Override public boolean isOpaqueCube() { return false; }
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		
		if ((meta >= 8 && meta <= 11) || meta == 14 || meta == 15) {
			return 15;
		} else {
			return 0;
		}
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		ItemStack item = player.getHeldItem();
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote) {
			if (item != null) {
				if (item.getItem().getHarvestLevel(item, "shovel") > 0) {
					if (meta >= 4 && meta <= 7) {
						world.setBlockMetadataWithNotify(x, y, z, meta - 4, 3);
						if (!player.capabilities.isCreativeMode) {
							world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.snowball)));
							item.attemptDamageItem(1, new Random());
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float clickPosX, float clickPosY, float clickPosZ) {
		int meta = world.getBlockMetadata(x, y, z);
		if (player.isSneaking()) {
			System.out.println("Meta: " + meta);
		}
		ItemStack item = player.getHeldItem();
		if (!world.isRemote) {
			if (item != null) {
				if (item.getItem().equals(Items.snowball)) {
					if (meta < 4) {
						world.setBlockMetadataWithNotify(x, y, z, meta + 4, 3);
					}
				} else if (item.getItem().equals(Items.glowstone_dust)) {
					if (meta < 4) {
						world.setBlockMetadataWithNotify(x, y, z, meta + 8, 3);
						if (!player.capabilities.isCreativeMode) {
							item.stackSize--;
						}
					} else if (meta < 8) {
						world.setBlockMetadataWithNotify(x, y, z, meta + 4, 3);
						if (!player.capabilities.isCreativeMode) {
							item.stackSize--;
						}
					} else if (meta == 12 || meta == 13) {
						world.setBlockMetadataWithNotify(x, y, z, meta + 2, 3);
						if (!player.capabilities.isCreativeMode) {
							item.stackSize--;
						}
					}
				} else if (item.getItem().getHarvestLevel(item, "pickaxe") > 0) {
					if (meta >= 8 && meta <= 11) {
						world.setBlockMetadataWithNotify(x, y, z, meta - 8, 3);
						if (!player.capabilities.isCreativeMode) {
							item.attemptDamageItem(1, new Random());
						}
					} else if (meta == 14 || meta == 15) {
						world.setBlockMetadataWithNotify(x, y, z, meta - 2, 3);
						if (!player.capabilities.isCreativeMode) {
							item.attemptDamageItem(1, new Random());
						}
					}
				} else if (item.getItem().equals(CoreItems.pliers)) {
					if (meta == 0 || meta == 1) {
						world.setBlockMetadataWithNotify(x, y, z, meta + 2, 3);
					} else if (meta == 2 || meta == 3) {
						world.setBlockMetadataWithNotify(x, y, z, meta + 10, 3);
					} else if (meta == 12 || meta == 13) {
						world.setBlockMetadataWithNotify(x, y, z, meta - 12, 3);
					}
					
					else if (meta == 4 || meta == 5) {
						world.setBlockMetadataWithNotify(x, y, z, meta + 2, 3);
					} else if (meta == 6 || meta == 7) {
						world.setBlockMetadataWithNotify(x, y, z, meta - 2, 3);
					}
					
					else if (meta == 8 || meta == 9) {
						world.setBlockMetadataWithNotify(x, y, z, meta + 2, 3);
					} else if (meta == 10 || meta == 11) {
						world.setBlockMetadataWithNotify(x, y, z, meta + 4, 3);
					} else if (meta == 14 || meta == 15) {
						world.setBlockMetadataWithNotify(x, y, z, meta - 6, 3);
					}
					/*} else { //TODO sneak-click isn't working. Set pliers able to sneak-click stuff.
						System.out.println("Rotating");
						if ((meta % 2) == 0 || meta == 0) {
							world.setBlockMetadataWithNotify(x, y, z, meta + 1, 3);
						} else {
							world.setBlockMetadataWithNotify(x, y, z, meta - 1, 3);
						}
					}*/
				}
			}
		}		
		return false;
	}
	
	@Override
	public int getRenderType() {
		return ClientProxy.walkwayTraditionalRenderID;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
		int rot = MathHelper.floor_double(entity.getRotationYawHead());
		int meta = item.getItemDamage();
		
		while (rot > 360) {
			rot = rot - 360;
		}
		while (rot < 0) {
			rot = rot + 360;
		}
		
		if ((rot >= 45 && rot <= 135) || (rot >= 225 && rot <= 315)) {
			world.setBlockMetadataWithNotify(x, y, z, meta, 3); //Facing east/west
		} else {
			world.setBlockMetadataWithNotify(x, y, z, meta + 1, 3);
		}
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity) {
		boolean hasCollision = false;
		if (!(world.getBlock(x, y-1, z) instanceof BlockWalkway)) {
			hasCollision = true;
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		
		int meta = world.getBlockMetadata(x, y, z);
		//North: Z1 - Z0, East: X0 - X1, South: Z0 - Z1, West = X1 - X0
		boolean connectNorth = checkConnections(world, x, y, z-1, 0, meta); //z-1
		boolean connectEast  = checkConnections(world, x+1, y, z, 1, meta);  //x+1
		boolean connectSouth = checkConnections(world, x, y, z+1, 0, meta); //z+1
		boolean connectWest  = checkConnections(world, x-1, y, z, 1, meta);  //x-1
		
		boolean hitboxNorth = false;
		boolean hitboxEast  = false;
		boolean hitboxSouth = false;
		boolean hitboxWest  = false;
		
		if ((meta % 2) == 0 || meta == 0) { //Even metadata
			if (!connectNorth) { hitboxNorth = true; }
			if (!connectSouth) { hitboxSouth = true; }
			
			if (connectNorth && !connectEast) { hitboxEast = true; }
			if (connectNorth && !connectWest) { hitboxWest = true; }
			if (connectSouth && !connectEast) { hitboxEast = true; }
			if (connectSouth && !connectWest) { hitboxWest = true; }
			
		} else {
			if (!connectEast) { hitboxEast = true; }
			if (!connectWest) { hitboxWest = true; }
			
			if (connectEast && !connectNorth) { hitboxNorth = true; }
			if (connectEast && !connectSouth) { hitboxSouth = true; }
			if (connectWest && !connectNorth) { hitboxNorth = true; }
			if (connectWest && !connectSouth) { hitboxSouth = true; }
		}
		
		if (hitboxNorth) {
			hasCollision = true;
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0625F, 0.0625F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		
		if (hitboxSouth) {
			hasCollision = true;
			setBlockBounds(0.0F, 0.0F, 0.9375F, 1.0F, 1.0625F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		
		if (hitboxEast) {
			hasCollision = true;
			setBlockBounds(0.9375F, 0.0F, 0.0625F, 1.0F, 1.0625F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		
		if (hitboxWest) {
			hasCollision = true;
			setBlockBounds(0.0F, 0.0F, 0.0F, 0.0625F, 1.0625F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}

		if (!hasCollision) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		
		setBlockBoundsForItemRender();
	}
	
	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if (world.isRaining()) {
			if (world.getBiomeGenForCoords(x, z).getEnableSnow()) {
				if (world.canBlockSeeTheSky(x, y, z)) {
					if (meta < 4) {
						world.setBlockMetadataWithNotify(x, y, z, meta + 4, 3);
					}
				}
			}
		}
		if (world.getSavedLightValue(EnumSkyBlock.Block, x, y, z) > 11) {
            if (meta == 5 || meta == 7) {
            	world.setBlockMetadataWithNotify(x, y, z, meta - 1, 3);
            }
        }
	}
	
	//TODO meta-based check for Y-1
	public boolean checkConnections(World world, int x, int y, int z, int targetMeta, int meta) {
		if (world.getBlock(x, y, z).isNormalCube(world, x, y, z)) {
			return true;
		}
		
		if (!(meta == 2 || meta == 3 || meta == 6 || meta == 7 || meta >= 10)) {
			if (world.getBlock(x, y-1, z).isNormalCube(world, x, y, z)) {
				return true;
			}
		}
		
		if (world.getBlock(x, y, z) instanceof BlockWalkway || world.getBlock(x, y, z) instanceof BlockWalkwayStairs) {
			return true;
		}
		
		if (targetMeta >= 0) {
			if (world.getBlock(x, y - 1, z) instanceof BlockWalkwayStairs || world.getBlock(x, y, z) instanceof BlockWalkwayStairs) {
				int m = world.getBlockMetadata(x, y - 1, z);
				if (m == targetMeta || m == (targetMeta + 2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon oversizeIcon;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + textureName);
		oversizeIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + oversizeName);
	}
	
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }
	
	@Override
	public IIcon getIcon(int side, int metadata) {
		if (side == 7) {
			return oversizeIcon;
		} else {
			return blockIcon;
		}
	}
}
