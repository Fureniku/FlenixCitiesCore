package com.silvaniastudios.cities.core.items;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.item.ItemTool;

public class PliersItem extends ItemTool {

	public PliersItem() {
		super(ToolMaterial.IRON, null);
		this.setCreativeTab(FurenikusCities.tabCity);
		this.setMaxDamage(256);
		this.setMaxStackSize(1);
	}
}
