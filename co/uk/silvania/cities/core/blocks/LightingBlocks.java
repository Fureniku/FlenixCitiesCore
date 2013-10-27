package co.uk.silvania.cities.core.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LightingBlocks extends Block {
	
	public LightingBlocks(int id) {
		super(id, Material.glass);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(0.3F);
		this.setLightValue(1.0F);
	}
	
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	@SideOnly(Side.CLIENT)
	private Icon sideIcon;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[16];

		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
			sideIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":ceilingTile");	
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (side == 0) {
			return icons[meta];
		}
		if (side == 1) {
			return icons[meta];
		}
		return sideIcon;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
		for (int var4 = 0; var4 < 4; ++var4) {
			list.add(new ItemStack(par1, 1, var4));
		}
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
    @Override
    public int damageDropped(int meta) {
    	return meta;
    }
    
    @Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		int meta = block.getBlockMetadata(x, y, z);
    	if (meta == 0) {
        	this.setBlockBounds(0.0F, 0.96875F, 0.0F, 1.0F, 1.0F, 1.0F);
    	} else if (meta == 1) {
        	this.setBlockBounds(0.25F, 0.875F, 0.25F, 0.75F, 1.0F, 0.75F);
    	} else if (meta == 2) {
        	this.setBlockBounds(0.0F, 0.875F, 0.25F, 1.0F, 1.0F, 0.75F);
    	} else
        	this.setBlockBounds(0.25F, 0.875F, 0.0F, 0.75F, 1.0F, 1.0F);
	}

}
