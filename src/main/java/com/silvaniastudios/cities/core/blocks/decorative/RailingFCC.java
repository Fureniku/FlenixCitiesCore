/*package com.silvaniastudios.cities.core.blocks.decorative;

import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.IBlockAccess;

public class RailingFCC extends WalkwayFenceFCC {

	public RailingFCC(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.railingRenderID;
	}
	
	@Override
	public String getBlockNameFromMeta(int meta) {
		if (meta <= 3) {
			return "oneRailing";
		} else if (meta <= 7) {
			return "rightAngleRailing";
		} else if (meta <= 11) {
			return "threeRailing";
		} else if (meta <= 13) {
			return "parallelRailing";
		} else if (meta == 14) {
			return "squareRailing";
		} else
			return "connectedRailing";
	}
	
	@Override
    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
		if (world.getBlock(x, y+1, z) instanceof RailingFCC || world.getBlock(x, y-1, z) instanceof RailingFCC) {
			return true;
		}
		
        return false;
    }
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
*/