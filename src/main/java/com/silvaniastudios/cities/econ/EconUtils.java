package com.silvaniastudios.cities.econ;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import com.silvaniastudios.cities.core.CityConfig;
import com.silvaniastudios.cities.core.CoreItems;
import com.silvaniastudios.cities.core.NBTConfig;
import com.silvaniastudios.cities.econ.money.ItemMoney;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class EconUtils {
	
	public EconUtils(){}
	/*
	 * Methods available in this class:
	 * createAccount(string) - Create a bank account, string is name.
	 * 
	 * parseDouble(string) - Turn a string to a double
	 * parseInt(string) - Turn a string to an int
	 * moveIntToBalance - Turn an int into a double, used for balance stuff
	 * 
	 * reqClientInventoryBalance() - Fires off and receives a response packet, giving you the client's correct inventory balance. Uses packets to avoid item ghosts.
	 * reqClientBankBalance() - Tells the client how much is in the players bank balance.
	 * Both of these are CLIENT-side, for use in things like GUI and on-screen rendering. Please don't call them server-side, you will crash.
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
		
	public void debug(String s) {
		if (CityConfig.debugMode) {
			System.out.println("[FCC EconUtils] " + s);
		}
	}
	
	public void createAccount(String s) {}
	
	//Use to convert things like a string to a double, usable by the economy.
	//VERY useful for example in the ATM, keying in values from the buttons.
	public double parseDouble(String s) {
		try { 
			return Double.parseDouble("" + s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	public int parseInt(String s) {
		try {
			return Integer.parseInt("" + s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	public void depositAllCash(EntityPlayer player) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
        String uuid = player.getUniqueID().toString();
        
		int cash = getInventoryCash(player); //Check how much cash the player has on them
		int currentBalance = 0;
        if (nbt.hasKey(uuid)) {
            NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getInteger("Balance");
            }
            int  modifiedBalance = currentBalance + cash;
            playernbt.setInteger("Balance", modifiedBalance);
            nbt.setTag(uuid, playernbt);
        } else {
            NBTTagCompound playernbt = new NBTTagCompound();
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getInteger("Balance");
            }
            int modifiedBalance = currentBalance + cash;
            playernbt.setInteger("Balance", modifiedBalance);
            nbt.setTag(uuid, playernbt);
        }
        NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "$" + formatBalance(cash) + " " + CityConfig.currencyLargePlural + TextFormatting.GREEN + " Deposited! Your balance is now " + TextFormatting.GOLD + "$" + formatBalance(playernbt.getInteger("Balance")) + " " + CityConfig.currencyLargePlural));
        NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
        removeAllPlayerCash(player);
	}
	
	public void withdrawFunds(int amount, EntityPlayer entityPlayer) {
		int balance = getBalance(entityPlayer);
		if ((balance - amount) >= 0) {
			chargeBalance(entityPlayer, amount);
			giveChange(amount, 0, entityPlayer);
		}
	}
	
	//This takes the amount paid against the total cost, and pays the player the correct change.
	//It ensures you have space for it, and if you don't it'll send the excess to your bank account instead.
	//This method is also used by the withdrawl system - so if you don't have room to withdraw, it basically will just stay in your account.
	public void giveChange(int paid, int cost, EntityPlayer entityPlayer) {
		int change = paid - cost;
		int toBank = 0;	
		
		while (change >= 10000) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note10000))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note10000));
				debug("$100 to inventory :)");
			} else {
				debug("Sending $100 to bank");
				toBank = toBank + 10000;
			}
			change = change - 10000;
		}
		
		while (change >= 5000) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note5000))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note5000));
				debug("$50 to inventory :)");
			} else {
				debug("$50 to bank.");
				toBank = toBank + 5000;
			}
			change = change - 5000;
		} 
		
		while (change >= 2000) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note2000))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note2000));
				debug("$20 to inventory :)");
			} else {
				debug("$20 to bank.");
				toBank = toBank + 2000;
			}
			change = change - 2000;
		}
		
		while (change >= 1000) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note1000))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note1000));
				debug("$10 to inventory :)");
			} else {
				if (CityConfig.debugMode) {
					debug("$10 to bank.");
				}
				toBank = toBank + 1000;
			}
			change = change - 1000;
		}
		
		while (change >= 500) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note500))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note500));
				debug("$5 to inventory :)");
			} else {
				debug("$5 to bank.");
				toBank = toBank + 500;
			}
			change = change - 500;
		}
		
		while (change >= 200) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note200))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note200));
				debug("$2 to inventory :)");
			} else {
				debug("$2 to bank.");
				toBank = toBank + 200;
			}
			change = change - 200;
		}
		
		while (change >= 100) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note100))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note100));
				debug("$1 to inventory :)");
			} else {
				debug("$1 to bank.");
				toBank = toBank + 100;
			}
			change = change - 100;
	
		//Coins
		}
		while (change >= 50) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin50))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin50));
				debug("$0.50 to inventory :)");
			} else {
				debug("$0.50 to bank.");
				toBank = toBank + 50;
			}
			change = change - 50;
		}
		
		while (change >= 25) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin25))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin25));
				debug("$0.25 to inventory :)");
			} else {
				debug("$0.25 to bank.");
				toBank = toBank + 25;
			}
			change = change - 25;
		}
		
		while (change >= 10) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin10))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin10));
				debug("$0.10 to inventory :)");
			} else {
				debug("$0.10 to bank.");
				toBank = toBank + 10;
			}
			change = change - 10;
		}
		
		while (change >= 5) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin5))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin5));
				debug("$0.05 to inventory :)");
			} else {
				debug("$0.05 to bank.");
				toBank = toBank + 5;
			}
			change = change - 5;
		}
		
		while (change >= 2) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin2))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin2));
				debug("$0.02 to inventory :)");
			} else {
				debug("$0.02 to bank.");
				toBank = toBank + 2;
			}
			change = change - 2;
		}
		
		while (change > 0) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin1))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin1));
				debug("$0.01 to inventory :)");
			} else {
				debug("$0.01 to bank.");
				toBank = toBank + 1;
			}
			change = change - 1;
		}
		if (toBank >= 1) {
			System.out.println("Depositing " + toBank + " to " + entityPlayer.getDisplayName() + "'s account.");
		}
		depositToAccount(entityPlayer, toBank);
	}
	
	//Counts up all the money in the players inventory.
	//When wallets are added, they will have something like this independently, and will just be added to the final balance.
	//That way, getInventoryCash will return all money both in your wallet and in your general inventory.
	public int getInventoryCash(EntityPlayer player) {
		int balance = 0;
		for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (stack != null) {
				int moneyValue = 0;
				if (stack.getItem() instanceof ItemMoney) {
					ItemMoney note = (ItemMoney) stack.getItem();
					if (note.getMoneyValue() >= 0) {
						moneyValue = note.getMoneyValue();
					}
				}
				int quantity = stack.getCount();
				int totalValue = moneyValue * quantity;
				
				if (moneyValue > 0) {
					debug("There is a money stack with value of " + moneyValue  + ". The stack size is " + quantity + " with a total value of " + totalValue);
				}
				balance = balance + totalValue;
			}
		}
		return balance;
	}
	
	public void removeAllPlayerCash(EntityPlayer player) {
		debug("Beginning loop to remove all player cash");
		for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (stack != null) {
				if (stack.getItem() instanceof ItemMoney) {
					debug("Found money, Removing!");
					player.inventory.removeStackFromSlot(i);
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
	public boolean findCashInInventory(EntityPlayer player, int value) {
		if (getInventoryCash(player) >= value) {
			for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack != null) {
					if (stack.getItem() instanceof ItemMoney) {
						int qty = stack.getCount();
						ItemMoney money = (ItemMoney) stack.getItem();
						int moneyValue = money.getMoneyValue();
						int currentlyPaid = 0;
						//Second loop, basically checks if the stack's value is high enough one item at a time (as to not overpay)
						for(int x = 1; x <= qty; x++) {
							debug("Nested Loop! Current stack value is: " + (moneyValue * x) + " - The target is " + value);
							if (currentlyPaid + (moneyValue * x) >= value) {
								debug("This is fired if the moneyValue is higher than the value, allegedly");
								if (x == qty) {
									player.inventory.setInventorySlotContents(i, null);
								} else
									player.inventory.decrStackSize(i, x);
								int paidAmount = moneyValue * x;
								debug("Give change: " + (paidAmount - value));
								depositToAccount(player, paidAmount-value);
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
		return false;
	}
	
	//Almost identical to findCashInInv, except it returns the value of the change.
	//Useful for player owned shop systems, as change should be taken from the player and NOT generated.
	public int findCashInInventoryWithChange(EntityPlayer player, int value) {
		int currentlyPaid = 0;
		if (getInventoryCash(player) >= value) {
			for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
				debug("Currently paid: " + currentlyPaid + ", Value: " + value);
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack != null) {
					if (stack.getItem() instanceof ItemMoney) {
						int qty = stack.getCount();
						ItemMoney money = (ItemMoney) stack.getItem();
						int moneyValue = money.getMoneyValue();
						int stackValue = qty * moneyValue;
						currentlyPaid = currentlyPaid + stackValue;
						player.inventory.setInventorySlotContents(i, null);
						if (currentlyPaid >= value) {
							debug("Giving change, SHOULD be " + (value - currentlyPaid));
							giveChange(currentlyPaid, value, player);
							((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
							return 0;
						}
					}
				}
			}
		}
		return 0;
	}
	
	//Attempt to give change from an inventory, such as a shop's cash register.
	public boolean giveChangeFromInventory(EntityPlayer player, int x, int y, int z, int value) {
		return false;
	}
	
	public boolean chargePlayerAnywhere(EntityPlayer player, int value) {
		if (findCashInInventory(player, value) == false) {
			
			int invBalance = getInventoryCash(player);
			int cardBalance = getBalance(player);
			
			int totalBalance = invBalance + cardBalance;
			if (player.inventory.hasItemStack(new ItemStack(CoreItems.debitCard))) {
				if (invBalance < value) {
					if (totalBalance >= value) {
						int payAmount = value - invBalance;
						if (payBalanceByCard(player, payAmount)) {
							removeAllPlayerCash(player);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	//Basically removes value from the players account
	public boolean payBalanceByCard(EntityPlayer player, int value) {
		debug("Attempting to pay balance by card");
		int cardBalance = getBalance(player);
		if (value <= cardBalance) {
			//They can pay by card!
			//Open GUI
			//Check PIN
			return chargeBalance(player, value);
		}
		return false;
	}
	
	//Removes money from the players account- for withdrawl etc.
	public boolean chargeBalance(EntityPlayer player, int amt) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		debug("Charging balance! Amount: $" + amt);
		int cardBalance = getBalance(player);
		debug("cardBalance: " + cardBalance);
		if (amt <= cardBalance) {
			debug("AMT is >= cardBalance");
			String playerUUID = player.getUniqueID().toString();
			int currentBalance = 0;
	        if (nbt.hasKey(playerUUID)) {
	        	debug("nbt.hasKey");
	            NBTTagCompound playernbt = nbt.getCompoundTag(playerUUID);
	            if (playernbt.hasKey("Balance")) {
	                currentBalance = playernbt.getInteger("Balance");
	            }
	            int modifiedBalance = currentBalance - amt;
	            playernbt.setInteger("Balance", modifiedBalance);
	            //TODO nbt.setCompoundTag(player.username, playernbt);
	        } else {
	            debug("BAD! Player has no money, how'd they get this far?");
	            return false;
	        }
	        //NBTTagCompound playernbt = nbt.getCompoundTag(playerUUID);
	        NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
			return true;
		}
		debug(player.getDisplayName() + " did not have enough to withdraw.");
		return false;
	}
	
	//Used for printing the balance ONLY. Do NOT use anywhere money values are actually altered!
	public String formatBalance(int bal) {
		double printBal = bal / 100.0; 
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setRoundingMode(RoundingMode.HALF_UP);
		String str = nf.format(printBal);
		str = str.replace(",", "");
		
		return str;
	}

	//Quick n' easy method of getting the players balance.
	public int getBalance(EntityPlayer player) {
		System.out.println("Getting balance for player " + player.getDisplayName());
        return getBalanceViaUUID(player.getUniqueID().toString());
	}
	
	public int getVictimBalance(EntityPlayer player) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		int balance = 0;
		if (player.inventory.getCurrentItem() != null) {
			String victimPlayerUUID = DebitCardItem.checkCardOwner(player, player.inventory.getCurrentItem());
	        if (nbt.hasKey(victimPlayerUUID)) {
	            NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayerUUID);
	            if (playernbt.hasKey("Balance")) {
	                balance = playernbt.getInteger("Balance");
	            }
	        }
		}
        return balance;
	}
	
	//Checks if the inventory has room for the specified itemstack. Returns false either if all slots are full (with no matching types),
	//Or if there's an available slot but it doesn't have room to accomodate.
	//Returns true if there's an empty slot, or a partially filled slot of the same type which has enough free space to add to it.
	//I am aware that the addItemStackToInventory method already does this check, but you may want to use this anyway.
	//For example, you don't want to charge a player and then not give them their stuff,
	//and likewise, you don't want to give them the stuff and find you can't charge them.
	//Consider this a "safe check" - it finds the answer without altering the inventory in any way.
	public boolean inventoryHasSpace(EntityPlayer player, ItemStack item) {
		for (int x = 35; x >= 0; --x) {
			ItemStack slot = player.inventory.getStackInSlot(x);
			if (slot != null) {
				if (slot.getItem().equals(item.getItem())) {
					int max = slot.getMaxStackSize();
					int slotSize = slot.getCount();
					int itemSize = item.getCount();
					
					if ((itemSize + slotSize) <= max) {
						debug("Unfilled compatable stack found; adding to it.");
						return true;
					}	
				}
			} else {
				return true;
			}
		}
		return false;
	}
	
	//Finds out if the player has a debit card that they own in their inventory.
	public boolean hasOwnCard(EntityPlayer player) {
		for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (stack != null) {
				if (stack.getItem() == CoreItems.debitCard) {
					if (player.getUniqueID().toString().equals(stack.getTagCompound().getString("playerUUID"))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void depositToAccount(EntityPlayer player, int deposit) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		int currentBalance = 0;
		String playerUUID = player.getUniqueID().toString();
        if (nbt.hasKey(playerUUID)) {
            NBTTagCompound playernbt = nbt.getCompoundTag(playerUUID);
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getInteger("Balance");
            }
            int modifiedBalance = currentBalance + deposit;
            playernbt.setInteger("Balance", modifiedBalance);
            nbt.setTag(playerUUID, playernbt);
        } else {
            NBTTagCompound playernbt = new NBTTagCompound();
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getInteger("Balance");
            }
            int modifiedBalance = currentBalance + deposit;
            playernbt.setInteger("Balance", modifiedBalance);
            nbt.setTag(playerUUID, playernbt);
        }
       // NBTTagCompound playernbt = nbt.getCompoundTag(playerUUID);
        NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
        if (deposit >= 0.1) {
        	player.sendMessage(new TextComponentString(TextFormatting.GOLD + "$" + formatBalance(deposit) + TextFormatting.GREEN + " was sent to your bank account. Your current total balance is " + TextFormatting.GOLD  + "$" + formatBalance(getBalance(player))));
        }
	}
	
	public void depositToAccountViaUUID(String uuid, int deposit) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		int currentBalance = 0;
		String playerUUID = uuid;
        if (nbt.hasKey(playerUUID)) {
            NBTTagCompound playernbt = nbt.getCompoundTag(playerUUID);
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getInteger("Balance");
            }
            int modifiedBalance = currentBalance + deposit;
            playernbt.setInteger("Balance", modifiedBalance);
            nbt.setTag(playerUUID, playernbt);
        } else {
            NBTTagCompound playernbt = new NBTTagCompound();
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getInteger("Balance");
            }
            int modifiedBalance = currentBalance + deposit;
            playernbt.setInteger("Balance", modifiedBalance);
            nbt.setTag(playerUUID, playernbt);
        }
        //NBTTagCompound playernbt = nbt.getCompoundTag(playerUUID);
        NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
        if (deposit >= 0.1) {
        	//TODO player.addChatMessage(TextFormatting.GOLD + "$" + formatBalance(deposit) + TextFormatting.GREEN + " was sent to your bank account. Your current total balance is $" + TextFormatting.GOLD + formatBalance(getBalance(player, player.worldObj)));
        }
	}
	
	public boolean chargeAccountViaUUID(String uuid, int amt) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		int cardBalance = getBalanceViaUUID(uuid);
		if (amt <= cardBalance) {
			int currentBalance = 0;
	        if (nbt.hasKey(uuid)) {
	            NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
	            if (playernbt.hasKey("Balance")) {
	                currentBalance = playernbt.getInteger("Balance");
	            }
	            int modifiedBalance = currentBalance - amt;
	            playernbt.setInteger("Balance", modifiedBalance);
	            //TODO nbt.setCompoundTag(player.username, playernbt);
	        } else {
	            NBTTagCompound playernbt = new NBTTagCompound();
	            if (playernbt.hasKey("Balance")) {
	                currentBalance = playernbt.getInteger("Balance");
	            }
	            int modifiedBalance = currentBalance - amt;
	            playernbt.setInteger("Balance", modifiedBalance);
	            //TODO nbt.setCompoundTag(player.username, playernbt);
	        }
	        //NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
	        NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
			return true;
		}
		return false;
	}
	
	public int getBalanceViaUUID(String uuid) {
        NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
        int balance = 0;
        if (nbt.hasKey(uuid)) {
            NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
            if (playernbt.hasKey("Balance")) {
                balance = playernbt.getInteger("Balance");
            }
        }
        return balance;
	}
}
