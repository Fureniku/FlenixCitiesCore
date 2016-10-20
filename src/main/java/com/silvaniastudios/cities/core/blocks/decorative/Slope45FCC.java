package com.silvaniastudios.cities.core.blocks.decorative;

import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Slope45FCC extends BlockStairsFCC {

	public Slope45FCC(Block block, String textureName) {
		super(block, textureName);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.slope45RenderID;
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
	
	public void onBlockPlacedBy(World block, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
        int rot = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int meta = block.getBlockMetadata(x, y, z);
        System.out.println("META PRE: " + meta);
        if (rot == 0) {
        	block.setBlockMetadataWithNotify(x, y, z, 2 + meta, 2); 
        }

        if (rot == 1) {
        	block.setBlockMetadataWithNotify(x, y, z, 1 + meta, 2);
        }

        if (rot == 2) {
        	block.setBlockMetadataWithNotify(x, y, z, 3 + meta, 2);
        }

        if (rot == 3) {
        	block.setBlockMetadataWithNotify(x, y, z, 0 + meta, 2);
        }
    }
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		if (side != 0 && side != 1) {
			System.out.println("Side: " + side + ", Meta: " + meta);
			//If they hit the lower or upper quarters, place as lower or upper.
			if ((double)hitY <= 0.3D) {
				return 0;
			} else if ((double) hitY >= 0.7D) {
				return 4;
			}
			if (side == 2) {
				if ((double)hitX <= 0.3D) {
					return 7;
				} else if ((double)hitX >= 0.7D) {
					return 8;
				}
			}
			if (side == 3) {
				if ((double)hitX <= 0.3D) {
					System.out.println("8");
					return 8;
				} else if ((double)hitX >= 0.7D) {
					System.out.println("5");
					return 5;
				}
			}
			if (side == 4) {
				if ((double)hitZ <= 0.3D) {
					return 8;
				} else if ((double)hitZ >= 0.7D) {
					return 10;
				}
			}
			if (side == 5) {
				if ((double)hitZ <= 0.3D) {
					return 10;
				} else if ((double)hitZ >= 0.7D) {
					return 8;
				}
			}
		}
		//No criteria met? They're probably in the middle somewhere. Divide 50/50, pick lower or upper.
        return side != 0 && (side == 1 || (double)hitY <= 0.5D) ? meta : meta | 4;
    }
}
