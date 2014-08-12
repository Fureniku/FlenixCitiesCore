package co.uk.silvania.cities.core.blocks;

import java.util.List;

import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class SkyscraperBlocks extends Block {

	public SkyscraperBlocks() {
		super(Material.rock);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(2.2F);
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[16];

		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 0 && meta == 4) {
			return icons[0];
		}
		if (side == 1 && meta == 4) {
			return icons[0];
		}
		if (side == 0 && meta == 5) {
			return icons[1];
		}
		if (side == 1 && meta == 5) {
			return icons[1];
		}
		if (side == 0 && meta == 6) {
			return icons[2];
		}
		if (side == 1 && meta == 6) {
			return icons[2];
		}
		if (side == 0 && meta == 7) {
			return icons[3];
		}
		if (side == 1 && meta == 7) {
			return icons[3];
		}
		if (side == 1 && meta == 8) {
			return icons[0];
		}
		if (side == 0 && meta == 8) {
			return icons[0];
		}
		if (side == 1 && meta == 9) {
			return icons[1];
		}
		if (side == 0 && meta == 9) {
			return icons[1];
		}
		if (side == 1 && meta == 10) {
			return icons[2];
		}
		if (side == 0 && meta == 10) {
			return icons[2];
		}
		if (side == 1 && meta == 11) {
			return icons[3];
		}
		if (side == 0 && meta == 11) {
			return icons[3];
		}
		return icons[meta];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
		for (int meta = 0; meta < 16; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
	
    @Override
    public int damageDropped(int meta) {
    	return meta;
    }
}