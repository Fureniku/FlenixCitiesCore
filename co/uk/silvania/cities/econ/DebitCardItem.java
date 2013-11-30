package co.uk.silvania.cities.econ;

import java.util.List;
import java.util.Random;
import java.lang.Math;

import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.NBTConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class DebitCardItem extends Item {

	public DebitCardItem(int id) {
		super(id);
		this.setMaxStackSize(1);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
	}
		
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5) {
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		Random rand = new Random();
		if (item.stackTagCompound == null) {
			if (!world.isRemote) {
				item.stackTagCompound = new NBTTagCompound();
				item.stackTagCompound.setString("playerName", player.username);
				item.stackTagCompound.setInteger("PIN", rand.nextInt(9000) + 1000);
				player.addChatMessage(EnumChatFormatting.GOLD + "Hello, " + item.stackTagCompound.getString("playerName") + 
						", your unique PIN is " + EnumChatFormatting.GREEN + item.stackTagCompound.getInteger("PIN") + 
						EnumChatFormatting.GOLD + ". Please write this down!");
				System.out.println("PIN has been set to " + item.stackTagCompound.getInteger("PIN"));
				//System.out.println("Name has been set to " + item.stackTagCompound.getString("playerName"));	
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
		if (item.stackTagCompound != null) {
			String playerName = item.stackTagCompound.getString("playerName");
			if (playerName.equals(player.username)) {
				list.add(EnumChatFormatting.GREEN + "Owner: " + playerName);
			} else {
				list.add(EnumChatFormatting.RED + "Owner: " + playerName);
			}
		}
	}
	
	public static int checkCardPin(EntityPlayer player) {
		ItemStack held = player.inventory.getCurrentItem();
		if (held.itemID != CoreItems.debitCardNew.itemID) {
			return -1;
		}
		
		return held.stackTagCompound.getInteger("PIN");
	}
	
	public static String checkCardOwner(EntityPlayer player) {
		ItemStack held = player.inventory.getCurrentItem();
		if (held.itemID != CoreItems.debitCardNew.itemID){
			return "";
		}
		return held.stackTagCompound.getString("playerName");
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

}
