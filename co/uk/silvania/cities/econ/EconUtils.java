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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class EconUtils {
	
	
	/*
	 * Methods available in this class:
	 * parseDouble(string) - Turn a string to a double
	 * parseInt(string) - Turn a string to an int
	 * moveIntToBalance - Turn an int into a double, used for balance stuff
	 * 
	 * giveChange(d paid, d cost, player) - Calculates the change owed to a player. First double is the amount the player gave, second is the value.
	 * getAllInventoryCash(player) - Calculates the total amount of cash in the players inventory.
	 * removeAllPlayerCash(player) - Takes all the cash from the players inventory
	 * findCashInInventory(player, d value) - Find's a particular coin or note in the players inventory. If found, it will remove it.
	 * chargePlayerAnywhere - Charges a player with both physical money and card. Will try physical first, then fallback to debit card before failing.
	 * findCoinsInInventory - Charge a player using only coins
	 * findNotesInInventory - Charge a player using only notes
	 * getBalance(player, world) - Gets the player's current bank balance. Does not count inventory.
	 * TODO payBalanceViaCard(d cost, player, playerOwner, world) - Opens the GUI for a card transaction to send money from the player to the shop owner
	 */
		
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
	public static void giveChange(double paid, double cost, EntityPlayer entityPlayer) {
		double change = paid - cost;
		
		while (change >= 100) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note10000));
			change = change - 100;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 100! Still left to pay: " + change);
        	}
		} while (change >= 50) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note5000));
			change = change - 50;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 50! Still left to pay: " + change);
        	}
		} while (change >= 20) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note2000));
			change = change - 20;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 20! Still left to pay: " + change);
        	}
		} while (change >= 10) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note1000));
			change = change - 10;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 10! Still left to pay: " + change);
        	}
		} while (change >= 5) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note500));
			change = change - 5;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 5! Still left to pay: " + change);
        	}
		} while (change >= 2) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note200));
			change = change - 2;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 2! Still left to pay: " + change);
        	}
		} while (change >= 1) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note100));
			change = change - 1;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 1! Still left to pay: " + change);
        	}
		} while (change >= 0.5) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin50));
			change = change - 0.5;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.50! Still left to pay: " + change);
        	}
		} while (change >= 0.25) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin25));
			change = change - 0.25;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.25! Still left to pay: " + change);
        	}
		} while (change >= 0.1) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin10));
			change = change - 0.1;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.10! Still left to pay: " + change);
        	}
		} while (change >= 0.05) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin5));
			change = change - 0.05;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.05! Still left to pay: " + change);
        	}
		} while (change >= 0.02) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin2));
			change = change - 0.02;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.02! Still left to pay: " + change);
        	}
		} while (change >= 0.01) {
			entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin1));
			change = change - 0.01;
        	if (CityConfig.debugMode == true) {
        		System.out.println("Giving change of 0.01! Still left to pay: " + change);
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
	
	public static void removeAllPlayerCash(EntityPlayer player) {
		if (CityConfig.debugMode == true) {
			System.out.println("Beginning loop to remove all palyer cash");
		}
		for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (stack != null) {
				if (stack.getItem() instanceof ItemNote || stack.getItem() instanceof ItemCoin) {
					if (CityConfig.debugMode) {
						System.out.println("Found note, Removing!");
					}
					player.inventory.setInventorySlotContents(i, null);
					((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
				}
			}
		}
	}
	
	/*
	 * Second to the ATM, this is the most complex section of code I've ever written, and it does a lot more than it looks!
	 * 
	 * This paragraph (/essay) will explain what this deceivingly small block of code does.
	 * 
	 * First things first, it checks the player actually has enough money in their inventory. If they don't, it won't go any further.
	 * Next, it begins a loop. This loop repeats the ENTIRE remaining code on each slot of the inventory.
	 * 
	 * If the slot contains either a coin or note, it continues. It checks the amount of that item in the stack, and also gets the value of the singular item.
	 * 
	 * Next, we start a second loop. This is where the magic really happens.
	 * The loop starts at 1, and increases 1 each time it goes around, until it reaches however many are in the item's stack.
	 * 
	 * Each time it goes around, it checks the value of the amount it has counted against the amount requested
	 * For example, say it's the third time around and we're checking $10, it would be $30.
	 * 
	 * When we reach the correct amount (either equal or higher than the originally requested value), we do a few more things:
	 * First, it will either nullify the stack if appropriate, or just reduce by the correct amount.
	 * After that, we run the giveChange method above, in case change is required (eg they paid $30, but only $28 was needed)
	 * We then refresh the inventory, and return true, because we have the correct amount.
	 * 
	 * Now, what happens if the stack doesn't have enough cash?
	 * If the for loop never reaches the critical point where it's equal to value, it won't continue through the if and thus won't return true.
	 * Instead, it skips past it, and saves the value it has counted so far to the currentlyPaid variable.
	 * The first for loop begins again, continuing to the next inventory slot, and starting the whole process again. It keeps doing this until eventually, the value is met.
	 * 
	 * LIKE A BOSS.
	 */
	public static boolean findCashInInventory(EntityPlayer player, double value) {
		if (getInventoryCash(player) >= value) {
			for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack != null) {
					if (stack.getItem() instanceof ItemNote || stack.getItem() instanceof ItemCoin) {
						int qty = stack.stackSize;
						double noteValue = 0;
						double coinValue = 0;
						if (stack.getItem() instanceof ItemNote) {
							//These are inside if statements in order to avoid bad casting.
							ItemNote note = (ItemNote) stack.getItem();
							noteValue = note.getMoneyValue();
						}
						if (stack.getItem() instanceof ItemCoin) {
							ItemCoin coin = (ItemCoin) stack.getItem();
							coinValue = coin.getMoneyValue();
						}
						//Here, we add the two values. Only one of the two is ever used, so "moneyValue" is both note and coin value.
						double moneyValue = noteValue + coinValue;
						double currentlyPaid = 0;
						//Second loop, basically checks if the stack's value is high enough one item at a time (as to not overpay)
						for(int x = 1; x <= qty; x++) {
							if (CityConfig.debugMode) {
								System.out.println("Nested Loop! Current stack value is: " + (moneyValue * x) + " - The target is " + value);
							}
							if (currentlyPaid + (moneyValue * x) >= value) {
								System.out.println("This is fired if the moneyValue is higher than the value, allegedly");
								if (x == qty) {
									player.inventory.setInventorySlotContents(i, null);
								} else
									player.inventory.decrStackSize(i, x);
								double paidAmount = moneyValue * x;
								System.out.println("Give change: " + (paidAmount - value));
								giveChange(paidAmount, value, player);
								((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
								//If second loop pays enough, we return; we don't need to do anything else as the balance has been paid.
								return true;
							}
						}
						//If second loop fails, this part is ran.
						currentlyPaid = currentlyPaid + (moneyValue * qty);
						player.inventory.setInventorySlotContents(i, null);
						((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
					}
				}
			}
		}
		//If they don't have enough cash, that's it - they can't buy it.
		//Later, I'll add a secondary option for paying by card here.
		return false;
	}
	
	public static boolean chargePlayerAnywhere(EntityPlayer player, double value, World world) {
		if (findCashInInventory(player, value) == false) {
			double invBalance = getInventoryCash(player);
			double cardBalance = getBalance(player, world);
			
			double totalBalance = invBalance + cardBalance;
			if (player.inventory.hasItem(CoreItems.debitCardNew.itemID)) {
				if (invBalance < value) {
					if (totalBalance >= value) {
						double payAmount = value - invBalance;
						if (payBalanceByCard(player, world, payAmount) == true) {
							player.addChatMessage("You have paid " + invBalance + " by cash, and will pay the remaining " + payAmount + " by card.");
							removeAllPlayerCash(player);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean findCoinsInInventory(EntityPlayer player, double value) {
		if (getInventoryCash(player) >= value) {
			for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack != null) {
					if (stack.getItem() instanceof ItemCoin) {
						int qty = stack.stackSize;
						double noteValue = 0;
						double coinValue = 0;
						ItemCoin coin = (ItemCoin) stack.getItem();
						coinValue = coin.getMoneyValue();
						double currentlyPaid = 0;
						for(int x = 1; x <= qty; x++) {
							if (CityConfig.debugMode) {
								System.out.println("Nested Loop! Current stack value is: " + (coinValue * x) + " - The target is " + value);
							}
							if (currentlyPaid + (coinValue * x) >= value) {
								System.out.println("This is fired if the moneyValue is higher than the value, allegedly");
								if (x == qty) {
									player.inventory.setInventorySlotContents(i, null);
								} else
									player.inventory.decrStackSize(i, x);
								double paidAmount = coinValue * x;
								System.out.println("Give change: " + (paidAmount - value));
								giveChange(paidAmount, value, player);
								((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
								return true;
							}
						}
						currentlyPaid = currentlyPaid + (coinValue * qty);
						player.inventory.setInventorySlotContents(i, null);
						((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
					}
				}
			}
		}
		return false;
	}
	
	public static boolean findNotesInInventory(EntityPlayer player, double value) {
		if (getInventoryCash(player) >= value) {
			for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack != null) {
					if (stack.getItem() instanceof ItemNote) {
						int qty = stack.stackSize;
						double noteValue = 0;
						ItemNote note = (ItemNote) stack.getItem();
						noteValue = note.getMoneyValue();
						double currentlyPaid = 0;
						for(int x = 1; x <= qty; x++) {
							if (CityConfig.debugMode) {
								System.out.println("Nested Loop! Current stack value is: " + (noteValue * x) + " - The target is " + value);
							}
							if (currentlyPaid + (noteValue * x) >= value) {
								System.out.println("This is fired if the moneyValue is higher than the value, allegedly");
								if (x == qty) {
									player.inventory.setInventorySlotContents(i, null);
								} else
									player.inventory.decrStackSize(i, x);
								double paidAmount = noteValue * x;
								System.out.println("Give change: " + (paidAmount - value));
								giveChange(paidAmount, value, player);
								((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
								return true;
							}
						}
						currentlyPaid = currentlyPaid + (noteValue * qty);
						player.inventory.setInventorySlotContents(i, null);
						((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
					}
				}
			}
		}
		return false;
	}
	
	public static boolean payBalanceByCard(EntityPlayer player, World world, double value) {
		double cardBalance = getBalance(player, world);
		boolean pinCode = false;
		if (value <= cardBalance) {
			//They can pay by card!
			//Open GUI
			//Check PIN
			if (pinCode == true) {
		        ByteArrayOutputStream bt = new ByteArrayOutputStream();
		        DataOutputStream out = new DataOutputStream(bt);
				
				String victimPlayer = DebitCardItem.checkCardOwner(player);
		        NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
				double currentBalance = 0;
				
		        if (nbt.hasKey(player.username)) {
		            NBTTagCompound playernbt = nbt.getCompoundTag(player.username);
		            if (playernbt.hasKey("Balance")) {
		                currentBalance = playernbt.getDouble("Balance");
		            }
		            double modifiedBalance = currentBalance - value;
		            playernbt.setDouble("Balance", modifiedBalance);
		            nbt.setCompoundTag(player.username, playernbt);
		        } else {
		            NBTTagCompound playernbt = new NBTTagCompound();
		            if (playernbt.hasKey("Balance")) {
		                currentBalance = playernbt.getDouble("Balance");
		            }
		            double modifiedBalance = currentBalance - value;
		            playernbt.setDouble("Balance", modifiedBalance);
		            nbt.setCompoundTag(player.username, playernbt);
		        }
				return true;
			}
		}
		return false;
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
