package com.silvaniastudios.cities.econ.money;

import com.silvaniastudios.cities.core.FlenixCities_Core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class PrePaidCard extends Item {

	public PrePaidCard() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
