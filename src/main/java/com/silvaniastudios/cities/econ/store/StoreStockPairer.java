package com.silvaniastudios.cities.econ.store;

import com.silvaniastudios.cities.core.FlenixCities;

import net.minecraft.item.Item;

public class StoreStockPairer extends Item {
	
	public StoreStockPairer() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(FlenixCities.tabEcon);
	}
	
	/*@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
		if (item.stackTagCompound != null) {
			if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				list.add(EnumChatFormatting.ITALIC + "Sneak to see stored data");
			} else {
				EnumChatFormatting colour;
				
				if (player.getDisplayName().equalsIgnoreCase(item.stackTagCompound.getString("storeOwner"))) {
					colour = EnumChatFormatting.GREEN;
				} else {
					colour = EnumChatFormatting.RED;
				}
				
				list.add("Stock Chest owner: " + item.stackTagCompound.getString("playerName"));
				list.add("");
				list.add("Stock Chest X Position: " + item.stackTagCompound.getInteger("xPos"));
				list.add("Stock Chest Y Position: " + item.stackTagCompound.getInteger("yPos"));
				list.add("Stock Chest Z Position: " + item.stackTagCompound.getInteger("zPos"));
			}
		} else {
			list.add(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GOLD + "This Stock Link has not been used yet!");
			list.add(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.YELLOW + "Right-click a Stock Chest to save it's data,");
			list.add(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.YELLOW + "Then right-click your store to create a link.");
		}
	}*/
}
