package com.silvaniastudios.cities.core.blocks.decorative;

import com.silvaniastudios.cities.core.FlenixCities_Core;
import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class Slope225HorizontalBFCC extends Block {

	String texture;
	
	public Slope225HorizontalBFCC(String textureName) {
		super(Material.rock);
		this.texture = textureName;
		this.setHardness(1.0F);
		this.setCreativeTab(FlenixCities_Core.tabDecorative);
		this.useNeighborBrightness = true;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.slope225HorizontalBRenderID;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + DecorativeBlockFCC.getTextureFromID(texture));
	}
	
	public void onBlockPlacedBy(World block, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
        int rot = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int meta = block.getBlockMetadata(x, y, z);
        
        if (rot == 0) {
        	block.setBlockMetadataWithNotify(x, y, z, 2 | meta, 2); 
        }

        if (rot == 1) {
        	block.setBlockMetadataWithNotify(x, y, z, 1 | meta, 2);
        }

        if (rot == 2) {
        	block.setBlockMetadataWithNotify(x, y, z, 3 | meta, 2);
        }

        if (rot == 3) {
        	block.setBlockMetadataWithNotify(x, y, z, 0 | meta, 2);
        }
    }
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		if (side != 0 && side != 1) {
			//If they hit the lower or upper quarters, place as lower or upper.
			if ((double)hitY <= 0.3D) {
				return meta;
			} else if ((double) hitY >= 0.7D) {
				return meta + 4;
			}
			if (side == 2) {
				if ((double)hitX <= 0.3D) {
					return meta + 12;
				} else if ((double)hitX >= 0.7D) {
					return meta + 8;
				}
			}
			if (side == 3) {
				if ((double)hitX <= 0.3D) {
					return meta + 8;
				} else if ((double)hitX >= 0.7D) {
					return meta + 12;
				}
			}
			if (side == 4) {
				if ((double)hitZ <= 0.3D) {
					return meta + 8;
				} else if ((double)hitZ >= 0.7D) {
					return meta + 12;
				}
			}
			if (side == 5) {
				if ((double)hitZ <= 0.3D) {
					return meta + 12;
				} else if ((double)hitZ >= 0.7D) {
					return meta + 8;
				}
			}
		}
		//No criteria met? They're probably in the middle somewhere. Divide 50/50, pick lower or upper.
        return side != 0 && (side == 1 || (double)hitY <= 0.5D) ? meta : meta | 4;
    }

    public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
