package com.silvaniastudios.cities.core.items;

import com.silvaniastudios.cities.core.FlenixCities;

import net.minecraft.item.ItemTool;

public class PliersItem extends ItemTool {

	public PliersItem() {
		super(ToolMaterial.IRON, null);
		this.setCreativeTab(FlenixCities.tabCity);
		this.setMaxDamage(256);
		this.setMaxStackSize(1);
	}
}
