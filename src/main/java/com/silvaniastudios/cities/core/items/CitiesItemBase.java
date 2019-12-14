package com.silvaniastudios.cities.core.items;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.item.Item;

public class CitiesItemBase extends Item {
	
	protected String name;
	
	public CitiesItemBase(String name, int stackSize) {
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.maxStackSize = stackSize;
		this.setCreativeTab(FurenikusCities.tabCity);
	}

	public void registerItemModel() {
		FurenikusCities.proxy.registerItemRenderer(this, 0, name);
	}
}
