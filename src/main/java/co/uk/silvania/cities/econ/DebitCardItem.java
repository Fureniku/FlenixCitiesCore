package co.uk.silvania.cities.econ;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.FlenixCities_Core;
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
		//Used for changing the PIN code. Checks if it's been set or not. Need to test in SMP...
		if (item.stackTagCompound != null) {
			if (!world.isRemote) {
				//TODO setNewPIN(item, player, world);
			}
		}
		if (item.stackTagCompound == null) {
			if (!world.isRemote) {
				item.stackTagCompound = new NBTTagCompound();
				item.stackTagCompound.setString("playerName", player.getDisplayName());
				item.stackTagCompound.setInteger("PIN", rand.nextInt(9000) + 1000);
				player.addChatComponentMessage(new ChatComponentText(gold + "Hello, " + item.stackTagCompound.getString("playerName") + 
						", your unique PIN is " + green + item.stackTagCompound.getInteger("PIN") + "."));
				System.out.println("PIN has been set to " + item.stackTagCompound.getInteger("PIN"));
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
		System.out.println("PIN (int): " + pinInt + ", PIN (string): " + pin);
		return pin;
	}
	
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer entityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if (CityConfig.debugMode) {
			System.out.println("Refreshing Inventory!");
		}
		return true;
	}
	
	/*public static void setNewPIN(ItemStack item, EntityPlayer player, World world) {
		String pin = ServerPacketHandler.newPin;
		String name = ServerPacketHandler.playerName;
		if (player.getDisplayName().equalsIgnoreCase(name)) {
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
	}*/
	
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
	
	public static String checkCardOwner(EntityPlayer player) {
		ItemStack held = player.inventory.getCurrentItem();
		if (held != null) {
			if (held.getItem() == CoreItems.debitCardNew) {
				return held.stackTagCompound.getString("playerName");
			}
		}
		return "";
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	
}