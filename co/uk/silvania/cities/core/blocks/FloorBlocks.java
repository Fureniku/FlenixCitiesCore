package co.uk.silvania.cities.core.blocks;

import java.util.List;

import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class FloorBlocks extends Block {

	public FloorBlocks(int id) {
		super(id, Material.rock);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(1.0F);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[8];

		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return icons[par2];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
		for (int var4 = 0; var4 < 8; ++var4) {
			list.add(new ItemStack(par1, 1, var4));
		}
	}
}
