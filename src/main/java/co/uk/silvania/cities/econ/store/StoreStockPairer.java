package co.uk.silvania.cities.econ.store;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class StoreStockPairer extends Item {
	
	public StoreStockPairer() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
	}
	
	@Override
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
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon iconOn;
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":stockChestPairer_off");
		iconOn = iconRegister.registerIcon(FlenixCities_Core.modid + ":stockChestPairer_on");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public IIcon getIconIndex(ItemStack item) {
        if (item.stackTagCompound != null) {
        	return iconOn;
        }
        return itemIcon;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(ItemStack item, int pass) {
        if (item.stackTagCompound != null) {
        	return iconOn;
        }
		return itemIcon;
	}

}
