package com.silvaniastudios.cities.core.blocks;

import java.util.List;

import com.silvaniastudios.cities.core.FlenixCities_Core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class FloorBlocks extends Block {
	
	int qty = 16;

	public FloorBlocks() {
		super(Material.rock);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(1.0F);
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	@SideOnly(Side.CLIENT)
	private IIcon ceilingTile;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[qty];

		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
			ceilingTile = iconRegister.registerIcon(FlenixCities_Core.modid + ":ceilingTile");
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2) {
		if (par2 == 8) {
			return ceilingTile;
		}
		return icons[par2];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
		for (int var4 = 0; var4 < qty; ++var4) {
			list.add(new ItemStack(item, 1, var4));
		}
	}
	
    @Override
    public int damageDropped(int meta) {
    	return meta;
    }
}
