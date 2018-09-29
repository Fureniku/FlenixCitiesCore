/*package com.silvaniastudios.cities.core.blocks.decorative;

import java.util.List;

import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WalkwayFenceFCC extends DecorativeBlockFCC {

	public WalkwayFenceFCC(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.walkwayFenceRenderID;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
		int meta = item.getItemDamage();
		
		int itemDamage = 0;
		int direction = MathHelper.floor_double((entity.rotationYaw * 4F) / 360F + 0.5D) & 3;
		if (direction < 3) { direction++; } else { direction = 0; }
		if (meta <= 11) {
			itemDamage = direction + meta;
		} else if (meta <= 13) {
			if (direction == 0 || direction == 2) {
				itemDamage = 12;
			} else { 
				itemDamage = 13;
			}
		} else {
			itemDamage = meta;
		}
		
		world.setBlockMetadataWithNotify(x, y, z, itemDamage, 3);
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity) {
		boolean hasCollision = false;

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
			
		if (meta == 0)  { hitboxNorth = true; }
		if (meta == 1)  { hitboxEast  = true; }
		if (meta == 2)  { hitboxSouth = true; }
		if (meta == 3)  { hitboxWest  = true; }
		if (meta == 4)  { hitboxNorth = hitboxEast  = true; }
		if (meta == 5)  { hitboxSouth = hitboxEast  = true; }
		if (meta == 6)  { hitboxSouth = hitboxWest  = true; }
		if (meta == 7)  { hitboxNorth = hitboxWest  = true; }
		if (meta == 8)  { hitboxNorth = hitboxEast  = hitboxSouth = true;}
		if (meta == 9)  { hitboxEast  = hitboxSouth = hitboxWest  = true;}
		if (meta == 10) { hitboxSouth = hitboxWest  = hitboxNorth = true;}
		if (meta == 11) { hitboxWest  = hitboxNorth = hitboxEast  = true;}
		if (meta == 12) { hitboxNorth = hitboxSouth = true; }
		if (meta == 13) { hitboxEast  = hitboxWest  = true; }
		if (meta == 14) { hitboxNorth = hitboxEast  = hitboxSouth = hitboxWest = true; }
		if (meta == 15) {
			if (!connectNorth) { hitboxNorth = true; }
			if (!connectSouth) { hitboxSouth = true; }
			if (!connectNorth) { hitboxNorth = true; }
			if (!connectSouth) { hitboxSouth = true; }
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
	
	public boolean checkConnections(World world, int x, int y, int z, int targetMeta, int meta) {
		if (world.getBlock(x, y, z).isNormalCube(world, x, y, z)) {
			return true;
		}
		
		if (!(meta == 2 || meta == 3 || meta == 6 || meta == 7 || meta >= 10)) {
			if (world.getBlock(x, y-1, z).isNormalCube(world, x, y, z)) {
				return true;
			}
		}
		
		if (world.getBlock(x, y, z) instanceof WalkwayFenceFCC || world.getBlock(x, y, z) instanceof WalkwayStairsFCC) {
			return true;
		}
		
		if (targetMeta >= 0) {
			if (world.getBlock(x, y - 1, z) instanceof WalkwayStairsFCC || world.getBlock(x, y, z) instanceof WalkwayStairsFCC) {
				int m = world.getBlockMetadata(x, y - 1, z);
				if (m == targetMeta || m == (targetMeta + 2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 4));
		list.add(new ItemStack(item, 1, 8));
		list.add(new ItemStack(item, 1, 13));
		list.add(new ItemStack(item, 1, 14));
		list.add(new ItemStack(item, 1, 15));
	}
	
	@Override
	public String getBlockNameFromMeta(int meta) {
		if (meta <= 3) {
			return "oneFence";
		} else if (meta <= 7) {
			return "rightAngleFence";
		} else if (meta <= 11) {
			return "threeFence";
		} else if (meta <= 13) {
			return "parallelFence";
		} else if (meta == 14) {
			return "squareFence";
		} else
			return "connectedFence";
	}

	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}*/
