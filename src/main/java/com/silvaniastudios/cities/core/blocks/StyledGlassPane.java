package com.silvaniastudios.cities.core.blocks;

import java.util.List;

import com.silvaniastudios.cities.core.FlenixCities_Core;

import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StyledGlassPane extends BlockStainedGlassPane {
	
	public StyledGlassPane(String par2Str, String par3Str, boolean par5) {
		super();
		this.setCreativeTab(FlenixCities_Core.tabCity);
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[16];

		for(int i = 0; i < 12; i++) {
			icons[i] = iconRegister.registerIcon(FlenixCities_Core.modid + ":styledGlass" + i);
		}
	}
	
	@Override
    public int getRenderBlockPass() {
        return 1;
    }
    
    @Override
    public int damageDropped(int meta) {
    	return meta;
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2) {
		return icons[par2];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
		for (int meta = 0; meta < 16; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
}
