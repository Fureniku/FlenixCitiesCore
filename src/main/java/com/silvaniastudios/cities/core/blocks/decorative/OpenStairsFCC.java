package com.silvaniastudios.cities.core.blocks.decorative;

import java.util.List;

import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class OpenStairsFCC extends WalkwayStairsFCC {

	public OpenStairsFCC(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.openStairsRenderID;
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity) {
		boolean hasCollision = false;
		int meta = world.getBlockMetadata(x, y, z);
		if (!(world.getBlock(x, y-1, z) instanceof WalkwayStairsFCC)) {
			hasCollision = true;
			
			if (meta == 0) { //South-facing
				setBlockBounds(0.0F, 0.0F,  0.0F,  1.0F, 0.0625F, 0.25F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.25F, 0.25F, 1.0F, 0.3125F, 0.50F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.50F, 0.50F, 1.0F, 0.5625F, 0.75F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.75F, 0.75F, 1.0F, 0.8125F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			} else if (meta == 1) { //West-facing
				setBlockBounds(0.75F, 0.0F, 0.0F,  1.00F, 0.0625F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.50F, 0.25F, 0.0F, 0.75F, 0.3125F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.25F, 0.50F, 0.0F, 0.50F, 0.5625F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F,  0.75F, 0.0F, 0.25F, 0.8125F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			} else if (meta == 2) { //North-facing
				setBlockBounds(0.0F, 0.0F, 0.75F,  1.0F, 0.0625F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.25F, 0.50F, 1.0F, 0.3125F, 0.75F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.50F, 0.25F, 1.0F, 0.5625F, 0.50F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.75F, 0.0F,  1.0F, 0.8125F, 0.25F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			} else if (meta == 3) { //East-facing
				setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 0.0625F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.25F, 0.25F, 0.0F, 0.50F, 0.3125F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.50F, 0.50F, 0.0F, 0.75F, 0.5625F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.75F, 0.75F, 0.0F, 1.00F, 0.8125F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			}
		}
		setBlockBoundsForItemRender();
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
