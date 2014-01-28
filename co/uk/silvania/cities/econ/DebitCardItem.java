package co.uk.silvania.cities.econ;

import java.util.List;
import java.util.Random;
import java.lang.Math;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.NBTConfig;
import co.uk.silvania.cities.core.ServerPacketHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
		EntityPlayer player = (EntityPlayer)entity;
		Random rand = new Random();
		String gold = EnumChatFormatting.GOLD + "";
		String green = EnumChatFormatting.DARK_GREEN + "";
		//Used for changing the PIN code. Checks if it's been set or not. Need to test in SMP...
		if (item.stackTagCompound != null) {
			if (!world.isRemote) {
				setNewPIN(item, player, world);
			}
		}
		if (item.stackTagCompound == null) {
			if (!world.isRemote) {
				item.stackTagCompound = new NBTTagCompound();
				item.stackTagCompound.setString("playerName", player.username);
				item.stackTagCompound.setInteger("PIN", rand.nextInt(9000) + 1000);
				player.addChatMessage(gold + "Hello, " + item.stackTagCompound.getString("playerName") + 
						", your unique PIN is " + green + item.stackTagCompound.getInteger("PIN") + 
						gold + ". Please write this down!");
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
			if (CityConfig.debugMode) {
				if (playerName.equals(player.username)) {
					list.add("PIN: " + item.stackTagCompound.getInteger("PIN"));
				}
			}
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer entityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		//((EntityPlayerMP) entityPlayer).sendContainerToPlayer(entityPlayer.inventoryContainer);
		if (CityConfig.debugMode) {
			System.out.println("Refreshing Inventory!");
		}
		return true;
	}
	
	public static void setNewPIN(ItemStack item, EntityPlayer player, World world) {
		String pin = ServerPacketHandler.newPin;
		String name = ServerPacketHandler.playerName;
		if (player.username.equalsIgnoreCase(name)) {
			//System.out.println("First things first, now we've got the packet we'll check it's for this player.");
			//System.out.println("Now, Lets check this PIN is of the correct length");
			if (pin.length() == 4) {
				//System.out.println("It is, lets turn it to an INT. as a string, it's" + pin);
				int intPin = EconUtils.parseInt(pin);
				//System.out.println("Now it should be an int. Is it still the same?" + intPin);
				if (item.stackTagCompound != null) {
					if (!world.isRemote) {
						item.stackTagCompound.setInteger("PIN", intPin);
						//System.out.println("And now, the PIN should be changed on the card. Am I good or what?");
						pin = "";
						name = "";
					}
				}	
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
