package com.silvaniastudios.cities.econ;

import java.util.List;
import java.util.Random;

import com.silvaniastudios.cities.core.CityConfig;
import com.silvaniastudios.cities.core.CoreItems;
import com.silvaniastudios.cities.core.FlenixCities_Core;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DebitCardItem extends Item {

	public DebitCardItem() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
	}
		
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5) {
		EntityPlayer player = (EntityPlayer)entity;
		Random rand = new Random();
		String gold = EnumChatFormatting.GOLD + "";
		String green = EnumChatFormatting.DARK_GREEN + "";

		if (item.stackTagCompound == null) {
			if (!world.isRemote) {
				item.stackTagCompound = new NBTTagCompound();
				item.stackTagCompound.setString("playerName", player.getDisplayName());
				item.stackTagCompound.setString("playerUUID", player.getUniqueID().toString());
				item.stackTagCompound.setInteger("PIN", rand.nextInt(9000) + 1000);
				player.addChatComponentMessage(new ChatComponentText(gold + "Hello, " + item.stackTagCompound.getString("playerName") + 
						", your unique PIN is " + green + item.stackTagCompound.getInteger("PIN") + "."));
				System.out.println(player.getDisplayName() + "'s PIN has been set to " + item.stackTagCompound.getInteger("PIN"));
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
		if (item.stackTagCompound != null) {
			String playerName = item.stackTagCompound.getString("playerName");
			if (playerName.equals(player.getDisplayName())) {
				list.add(EnumChatFormatting.GREEN + "Owner: " + playerName);
				list.add("PIN: " + getPinAsString(item));
			} else {
				list.add(EnumChatFormatting.RED + "Owner: " + playerName);
			}
			
			if (player.capabilities.isCreativeMode) {
				list.add(EnumChatFormatting.GOLD + playerName + "'s PIN is " + getPinAsString(item));
			}
		}
	}
	
	public String getPinAsString(ItemStack item) {
		int pinInt = item.stackTagCompound.getInteger("PIN");
		String pin = "" + pinInt;
		if (pinInt <= 0) {
			pin = "0000";
		} else if (pinInt < 10) {
			pin = "000" + pinInt;
		} else if (pinInt < 100) {
			pin = "00" + pinInt;
		} else if (pinInt < 1000) {
			pin = "0" + pinInt;
		}
		return pin;
	}
	
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer entityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if (CityConfig.debugMode) {
			System.out.println("Refreshing Inventory!");
		}
		return true;
	}
	
	public static String checkCardPin(EntityPlayer player) {
		ItemStack held = player.inventory.getCurrentItem();
		if (held.getItem() != CoreItems.debitCardNew) {
			return "";
		}
		
		int pinInt = held.stackTagCompound.getInteger("PIN");
		String pin = "" + pinInt;
		if (pinInt <= 0) {
			pin = "0000";
		} else if (pinInt < 10) {
			pin = "000" + pinInt;
		} else if (pinInt < 100) {
			pin = "00" + pinInt;
		} else if (pinInt < 1000) {
			pin = "0" + pinInt;
		}
		return pin;
	}
	
	public static String checkCardOwner(EntityPlayer player, ItemStack held) {
		if (held != null) {
			if (held.getItem() == CoreItems.debitCardNew) {
				return held.stackTagCompound.getString("playerUUID");
			}
		}
		return "";
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	
}
