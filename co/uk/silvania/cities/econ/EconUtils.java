package co.uk.silvania.cities.econ;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.NBTConfig;
import co.uk.silvania.cities.econ.money.ItemCoin;
import co.uk.silvania.cities.econ.money.ItemNote;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class EconUtils {
		
	//Use to convert things like a string to a double, usable by the economy.
	//VERY useful for example in the ATM, keying in values from the buttons.
	public static double parseDouble(String s) {
		try { 
			return Double.parseDouble(s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	//This one is used to turn the PIN from a string to an int when changing it in the ATM.
	public static int parseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	public static double moveIntToBalance(int i) {
		double newBalance = i;
		return newBalance;
	}
	
	//This takes the amount paid against the total cost, and pays the player the correct change.
	//Also used by the ATM withdrawl.
	public static void giveChange(double paid, double cost, Player player) {
		EntityPlayer entityPlayer = (EntityPlayer) player;
		double change = paid - cost;
		
		while (change >= 100) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note10000));
			change = change - 100;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 100! Still left to give: " + change);
        	}
		} while (change >= 50) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note5000));
			change = change - 50;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 50! Still left to give: " + change);
        	}
		} while (change >= 20) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note2000));
			change = change - 20;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 20! Still left to give: " + change);
        	}
		} while (change >= 10) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note1000));
			change = change - 10;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 10! Still left to give: " + change);
        	}
		} while (change >= 5) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note500));
			change = change - 5;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 5! Still left to give: " + change);
        	}
		} while (change >= 2) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note200));
			change = change - 2;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 2! Still left to give: " + change);
        	}
		} while (change >= 1) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note100));
			change = change - 1;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 1! Still left to give: " + change);
        	}
		} while (change >= 0.5) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin50));
			change = change - 0.5;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.50! Still left to give: " + change);
        	}
		} while (change >= 0.25) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin25));
			change = change - 0.25;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.25! Still left to give: " + change);
        	}
		} while (change >= 0.1) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin10));
			change = change - 0.1;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.10! Still left to give: " + change);
        	}
		} while (change >= 0.05) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin5));
			change = change - 0.05;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.05! Still left to give: " + change);
        	}
		} while (change >= 0.02) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin2));
			change = change - 0.02;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.02! Still left to give: " + change);
        	}
		} while (change >= 0.01) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin1));
			change = change - 0.01;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.01! Still left to give: " + change);
        	}
		}
	}
	
	//Counts up all the money in the players inventory.
	//When wallets are added, they will have something like this independently, and will just be added to the final balance.
	//That way, getInventoryCash will return all money both in your wallet and in your general inventory.
	public static double getInventoryCash(EntityPlayer player) {
		double balance = 0;
		for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (stack != null) {
				if (stack.getItem() instanceof ItemNote) {
					int quantity = stack.stackSize;
					ItemNote note = (ItemNote) stack.getItem();
					double noteValue = note.getMoneyValue();
					
					double totalValue = noteValue * quantity;
					if (CityConfig.debugMode == true) {
						System.out.println("There is a note stack with value of " + noteValue  + ". The stack size is " + quantity + " with a total value of " + totalValue);
					}
					balance = balance + totalValue;
				} else if (stack.getItem() instanceof ItemCoin) {
					int quantity = stack.stackSize;
					ItemCoin coin = (ItemCoin) stack.getItem();
					double coinValue = coin.getMoneyValue();
					
					double totalValue = coinValue * quantity;
					
					if (CityConfig.debugMode == true) {
						System.out.println("There is a coin stack with value of " + coinValue  + ". The stack size is " + quantity + " with a total value of " + totalValue);
					}
					balance = balance + totalValue;
				}
			}
		}
		return balance;
	}
	


	//Quick n' easy method of getting the players balance.
	public static double getBalance(EntityPlayer player, World world) {
        ByteArrayOutputStream bt = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bt);
		
		String victimPlayer = DebitCardItem.checkCardOwner(player);
        NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
        double balance = 0;
        if (nbt.hasKey(victimPlayer)) {
            NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayer);
            if (playernbt.hasKey("Balance")) {
                balance = playernbt.getDouble("Balance");
            }
        }
        return balance;
	}
}
