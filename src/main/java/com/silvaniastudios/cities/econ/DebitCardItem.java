package com.silvaniastudios.cities.econ;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.silvaniastudios.cities.core.CoreItems;
import com.silvaniastudios.cities.core.FlenixCities;
import com.silvaniastudios.cities.core.items.CitiesItemBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DebitCardItem extends CitiesItemBase {

	public DebitCardItem(String name) {
		super(name, 1);
		this.setCreativeTab(FlenixCities.tabEcon);
	}
		
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5) {
		EntityPlayer player = (EntityPlayer)entity;
		Random rand = new Random();
		String gold = TextFormatting.GOLD + "";
		String green = TextFormatting.DARK_GREEN + "";

		if (item.getTagCompound() == null) {
			if (!world.isRemote) {
				item.setTagCompound(new NBTTagCompound());
				item.getTagCompound().setString("playerName", player.getDisplayName().getFormattedText());
				item.getTagCompound().setString("playerUUID", player.getUniqueID().toString());
				item.getTagCompound().setInteger("PIN", rand.nextInt(9000) + 1000);
				player.sendMessage(new TextComponentString(gold + "Hello, " + item.getTagCompound().getString("playerName") + 
						", your unique PIN is " + green + item.getTagCompound().getInteger("PIN") + "."));
				System.out.println(player.getDisplayName() + "'s PIN has been set to " + item.getTagCompound().getInteger("PIN"));
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (item.getTagCompound() != null) {
			String playerName = item.getTagCompound().getString("playerName");
			if (playerName.equals(player.getName())) {
				list.add(TextFormatting.GREEN + "Owner: " + playerName);
				list.add("PIN: " + getPinAsString(item));
			} else {
				list.add(TextFormatting.RED + "Owner: " + playerName);
			}
			
			if (player.capabilities.isCreativeMode) {
				list.add(TextFormatting.GOLD + playerName + "'s PIN is " + getPinAsString(item));
			}
		}
	}
	
	public String getPinAsString(ItemStack item) {
		int pinInt = item.getTagCompound().getInteger("PIN");
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
	
	public static String checkCardPin(EntityPlayer player) {
		ItemStack held = player.inventory.getCurrentItem();
		if (held.getItem() != CoreItems.debitCard) {
			return "";
		}
		
		int pinInt = held.getTagCompound().getInteger("PIN");
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
			if (held.getItem() == CoreItems.debitCard) {
				return held.getTagCompound().getString("playerUUID");
			}
		}
		return "";
	}
}
