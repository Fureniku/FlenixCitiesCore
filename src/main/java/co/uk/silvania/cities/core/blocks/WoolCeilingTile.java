package co.uk.silvania.cities.core.blocks;

import java.util.List;

import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class WoolCeilingTile extends Block {

	public WoolCeilingTile() {
		super(Material.rock);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(2.2F);
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon base;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		base = iconRegister.registerIcon(FlenixCities_Core.modid + ":ceilingTile");
	}
	

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		if (side == 1) {
			return Blocks.wool.getIcon(side, metadata);
		} 
		return base;
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