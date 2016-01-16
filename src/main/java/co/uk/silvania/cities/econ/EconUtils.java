package co.uk.silvania.cities.econ;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.NBTConfig;
import co.uk.silvania.cities.econ.money.ItemCoin;
import co.uk.silvania.cities.econ.money.ItemNote;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class EconUtils {
	
	
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
		
	public static void debug(String s) {
		if (CityConfig.debugMode) {
			System.out.println("[FCC EconUtils] " + s);
		}
	}
	
	public static void createAccount(String s) {}
	
	//Use to convert things like a string to a double, usable by the economy.
	//VERY useful for example in the ATM, keying in values from the buttons.
	public static double parseDouble(String s) {
		try { 
			return Double.parseDouble("" + s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	public static int parseInt(String s) {
		try {
			return Integer.parseInt("" + s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	public static double moveIntToBalance(int i) {
		double newBalance = i;
		return newBalance;
	}
	
	public static void depositAllCash(EntityPlayer player) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
        String uuid = player.getUniqueID().toString();
        
		double cash = EconUtils.getInventoryCash(player); //Check how much cash the player has on them
		double currentBalance = 0;
        if (nbt.hasKey(uuid)) {
            NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getDouble("Balance");
            }
            double modifiedBalance = currentBalance + cash;
            playernbt.setDouble("Balance", modifiedBalance);
            nbt.setTag(uuid, playernbt);
        } else {
            NBTTagCompound playernbt = new NBTTagCompound();
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getDouble("Balance");
            }
            double modifiedBalance = currentBalance + cash;
            playernbt.setDouble("Balance", modifiedBalance);
            nbt.setTag(uuid, playernbt);
        }
        NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
        player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "$" + EconUtils.formatBalance(cash) + " " + CityConfig.currencyLargePlural + EnumChatFormatting.GREEN + " Deposited! Your balance is now " + EnumChatFormatting.GOLD + "$" + EconUtils.formatBalance(playernbt.getDouble("Balance")) + " " + CityConfig.currencyLargePlural));
        NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
        EconUtils.removeAllPlayerCash(player);
	}
	
	public static void withdrawFunds(double amount, EntityPlayer entityPlayer) {
		double balance = getBalance(entityPlayer);
		if ((balance - amount) >= 0) {
			chargeBalance(entityPlayer, amount);
			giveChange(amount, 0, entityPlayer);
		}
	}
	
	//This takes the amount paid against the total cost, and pays the player the correct change.
	//It ensures you have space for it, and if you don't it'll send the excess to your bank account instead.
	//This method is also used by the withdrawl system - so if you don't have room to withdraw, it basically will just stay in your account.
	public static void giveChange(double paid, double cost, EntityPlayer entityPlayer) {
		double change = parseDouble(formatBalance(paid - cost));
		double toBank = 0;	
		
		while (change >= 100) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note10000))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note10000));
				debug("100 to inventory :)");
			} else {
				debug("Sending 100 to bank");
				toBank = toBank + 100;
			}
			change = change - 100;
		}
		
		while (change >= 50) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note5000))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note5000));
				debug("50 to inventory :)");
			} else {
				debug("50 to bank.");
				toBank = toBank + 50;
			}
			change = change - 50;
		} 
		
		while (change >= 20) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note2000))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note2000));
				debug("20 to inventory :)");
			} else {
				debug("20 to bank.");
				toBank = toBank + 20;
			}
			change = change - 20;
		}
		
		while (change >= 10) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note1000))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note1000));
				debug("10 to inventory :)");
			} else {
				if (CityConfig.debugMode) {
					System.out.println("10 to bank.");
				}
				toBank = toBank + 10;
			}
			change = change - 10;
		}
		
		while (change >= 5) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note500))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note500));
				debug("5 to inventory :)");
			} else {
				debug("5 to bank.");
				toBank = toBank + 5;
			}
			change = change - 5;
		}
		
		while (change >= 2) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note200))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note200));
				debug("2 to inventory :)");
			} else {
				debug("2 to bank.");
				toBank = toBank + 2;
			}
			change = change - 2;
		}
		
		while (change >= 1) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.note100))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.note100));
				debug("1 to inventory :)");
			} else {
				debug("1 to bank.");
				toBank = toBank + 1;
			}
			change = change - 1;
	
		//Coins
		}
		while (change >= 0.5) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin50))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin50));
				debug("0.50 to inventory :)");
			} else {
				debug("0.50 to bank.");
				toBank = toBank + 0.5;
			}
			change = change - 0.5;
		}
		
		while (change >= 0.25) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin25))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin25));
				debug("0.25 to inventory :)");
			} else {
				debug("0.25 to bank.");
				toBank = toBank + 0.25;
			}
			change = change - 0.25;
		}
		
		while (change >= 0.1) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin10))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin10));
				debug("0.10 to inventory :)");
			} else {
				debug("0.10 to bank.");
				toBank = toBank + 0.1;
			}
			change = change - 0.1;
		}
		
		while (change >= 0.05) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin5))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin5));
				debug("0.05 to inventory :)");
			} else {
				debug("0.05 to bank.");
				toBank = toBank + 0.05;
			}
			change = change - 0.05;
		}
		
		while (change >= 0.02) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin2))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin2));
				debug("0.02 to inventory :)");
			} else {
				debug("0.02 to bank.");
				toBank = toBank + 0.02;
			}
			change = change - 0.02;
		}
		
		while (change > 0) {
			if (inventoryHasSpace(entityPlayer, new ItemStack(CoreItems.coin1))) { 
				entityPlayer.inventory.addItemStackToInventory(new ItemStack(CoreItems.coin1));
			} else {
				toBank = toBank + 0.01;
			}
			change = change - 0.01;
		}
		if (toBank >= 0.01) {
			System.out.println("Depositing " + toBank + " to " + entityPlayer.getDisplayName() + "'s account.");
		}
		depositToAccount(entityPlayer, toBank);
	}
	
	//Counts up all the money in the players inventory.
	//When wallets are added, they will have something like this independently, and will just be added to the final balance.
	//That way, getInventoryCash will return all money both in your wallet and in your general inventory.
	public static double getInventoryCash(EntityPlayer player) {
		double balance = 0;
		for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (stack != null) {
				double moneyValue = 0;
				if (stack.getItem() instanceof ItemNote) {
					ItemNote note = (ItemNote) stack.getItem();
					if (note.getMoneyValue() >= 0) {
						moneyValue = note.getMoneyValue();
					}
				} else if (stack.getItem() instanceof ItemCoin) {
					ItemCoin coin = (ItemCoin) stack.getItem();
					if (coin.getMoneyValue() >= 0) {
						moneyValue = coin.getMoneyValue();
					}
				}
				int quantity = stack.stackSize;
				double totalValue = moneyValue * quantity;
				
				debug("There is a money stack with value of " + moneyValue  + ". The stack size is " + quantity + " with a total value of " + totalValue);
				balance = balance + totalValue;
			}
		}
		return balance;
	}
	
	public static void removeAllPlayerCash(EntityPlayer player) {
		debug("Beginning loop to remove all player cash");
		for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (stack != null) {
				if (stack.getItem() instanceof ItemNote || stack.getItem() instanceof ItemCoin) {
					debug("Found note, Removing!");
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
							debug("Nested Loop! Current stack value is: " + (moneyValue * x) + " - The target is " + value);
							if (currentlyPaid + (moneyValue * x) >= value) {
								debug("This is fired if the moneyValue is higher than the value, allegedly");
								if (x == qty) {
									player.inventory.setInventorySlotContents(i, null);
								} else
									player.inventory.decrStackSize(i, x);
								double paidAmount = moneyValue * x;
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
	public static double findCashInInventoryWithChange(EntityPlayer player, double value) {
		double currentlyPaid = 0;
		if (getInventoryCash(player) >= value) {
			for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
				debug("Currently paid: " + currentlyPaid + ", Value: " + value);
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack != null) {
					if (stack.getItem() instanceof ItemNote || stack.getItem() instanceof ItemCoin) {
						int qty = stack.stackSize;
						double noteValue = 0;
						double coinValue = 0;
						if (stack.getItem() instanceof ItemNote) {
							ItemNote note = (ItemNote) stack.getItem();
							noteValue = note.getMoneyValue();
						}
						if (stack.getItem() instanceof ItemCoin) {
							ItemCoin coin = (ItemCoin) stack.getItem();
							coinValue = coin.getMoneyValue();
						}
						double moneyValue = noteValue + coinValue; //How much
						double stackValue = qty * (parseDouble(formatBalance(moneyValue)));
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
	public static boolean giveChangeFromInventory(EntityPlayer player, int x, int y, int z, double value) {
		return false;
	}
	
	public static boolean chargePlayerAnywhere(EntityPlayer player, double value) {
		if (findCashInInventory(player, value) == false) {
			
			double invBalance = getInventoryCash(player);
			double cardBalance = getBalance(player);
			
			double totalBalance = invBalance + cardBalance;
			if (player.inventory.hasItem(CoreItems.debitCardNew)) {
				if (invBalance < value) {
					if (totalBalance >= value) {
						double payAmount = value - invBalance;
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
	
	public static boolean findCoinsInInventory(EntityPlayer player, double value) {
		if (getInventoryCash(player) >= value) {
			for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack != null) {
					if (stack.getItem() instanceof ItemCoin) {
						int qty = stack.stackSize;
						double coinValue = 0;
						ItemCoin coin = (ItemCoin) stack.getItem();
						coinValue = coin.getMoneyValue();
						double currentlyPaid = 0;
						for(int x = 1; x <= qty; x++) {
							debug("Nested Loop! Current stack value is: " + (coinValue * x) + " - The target is " + value);
							if (currentlyPaid + (coinValue * x) >= value) {
								debug("This is fired if the moneyValue is higher than the value, allegedly");
								if (x == qty) {
									player.inventory.setInventorySlotContents(i, null);
								} else
									player.inventory.decrStackSize(i, x);
								double paidAmount = coinValue * x;
								debug("Give change: " + (paidAmount - value));
								depositToAccount(player, (paidAmount-value));
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
							debug("Nested Loop! Current stack value is: " + (noteValue * x) + " - The target is " + value);
							if (currentlyPaid + (noteValue * x) >= value) {
								debug("This is fired if the moneyValue is higher than the value, allegedly");
								if (x == qty) {
									player.inventory.setInventorySlotContents(i, null);
								} else
									player.inventory.decrStackSize(i, x);
								double paidAmount = noteValue * x;
								debug("Give change: " + (paidAmount - value));
								depositToAccount(player, (paidAmount-value));
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
	
	//Basically removes value from the players account
	public static boolean payBalanceByCard(EntityPlayer player, double value) {
		debug("Attempting to pay balance by card");
		double cardBalance = getBalance(player);
		if (value <= cardBalance) {
			//They can pay by card!
			//Open GUI
			//Check PIN
			return chargeBalance(player, value);
		}
		return false;
	}
	
	//Removes money from the players account- for withdrawl etc.
	public static boolean chargeBalance(EntityPlayer player, double amt) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		debug("Charging balance!");
		double cardBalance = getBalance(player);
		if (amt <= cardBalance) {		
			if (player.inventory.getCurrentItem() != null) { //TODO this won't work. Gotta iterate through and find a card instead.
				String victimPlayerUUID = DebitCardItem.checkCardOwner(player, player.inventory.getCurrentItem());
				double currentBalance = 0;
		        if (nbt.hasKey(victimPlayerUUID)) {
		            NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayerUUID);
		            if (playernbt.hasKey("Balance")) {
		                currentBalance = playernbt.getDouble("Balance");
		            }
		            double modifiedBalance = currentBalance - amt;
		            playernbt.setDouble("Balance", modifiedBalance);
		            //TODO nbt.setCompoundTag(player.username, playernbt);
		        } else {
		            NBTTagCompound playernbt = new NBTTagCompound();
		            if (playernbt.hasKey("Balance")) {
		                currentBalance = playernbt.getDouble("Balance");
		            }
		            double modifiedBalance = currentBalance - amt;
		            playernbt.setDouble("Balance", modifiedBalance);
		            //TODO nbt.setCompoundTag(player.username, playernbt);
		        }
		        NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayerUUID);
		        NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
				return true;
			}
		}
		debug(player.getDisplayName() + " did not have enough to withdraw.");
		return false;
	}
	
	//Used for printing the balance ONLY. Do NOT use anywhere money values are actually altered!
	//Simply rounds any double to two decimal places, stops bugs where doubles add micro factions.
	public static String formatBalance(double bal) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setRoundingMode(RoundingMode.HALF_UP);
		String str = nf.format(bal);
		str = str.replace(",", "");
		
		return str;
	}

	//Quick n' easy method of getting the players balance.
	public static double getBalance(EntityPlayer player) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		System.out.println("Calling getBalance");
        return getBalanceViaUUID(player.getUniqueID().toString());
	}
	
	public static double getVictimBalance(EntityPlayer player) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		double balance = 0;
		if (player.inventory.getCurrentItem() != null) {
			String victimPlayerUUID = DebitCardItem.checkCardOwner(player, player.inventory.getCurrentItem());
	        if (nbt.hasKey(victimPlayerUUID)) {
	            NBTTagCompound playernbt = nbt.getCompoundTag(victimPlayerUUID);
	            if (playernbt.hasKey("Balance")) {
	                balance = playernbt.getDouble("Balance");
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
	public static boolean inventoryHasSpace(EntityPlayer player, ItemStack item) {
		for (int x = 35; x >= 0; --x) {
			ItemStack slot = player.inventory.getStackInSlot(x);
			if (slot != null) {
				if (slot.getItem().equals(item.getItem())) {
					int max = slot.getMaxStackSize();
					int slotSize = slot.stackSize;
					int itemSize = item.stackSize;
					
					if ((itemSize + slotSize) <= max) {
						debug("Unfilled compatable stack found; adding to it.");
						return true;
					}	
				}
			} else {
				debug("Emtpy slot found. How useful! ID: " + x);
				return true;
			}
		}
		return false;
	}
	
	//Finds out if the player has a debit card that they own in their inventory.
	public static boolean hasOwnCard(EntityPlayer player) {
		for (int i = player.inventory.getSizeInventory() - 1; i >= 0; -- i) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (stack != null) {
				if (stack.getItem() == CoreItems.debitCardNew) {
					if (player.getDisplayName().equals(stack.stackTagCompound.getString("playerName"))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void depositToAccount(EntityPlayer player, double deposit) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		double currentBalance = 0;
		String playerUUID = player.getUniqueID().toString();
        if (nbt.hasKey(playerUUID)) {
            NBTTagCompound playernbt = nbt.getCompoundTag(playerUUID);
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getDouble("Balance");
            }
            double modifiedBalance = currentBalance + deposit;
            playernbt.setDouble("Balance", modifiedBalance);
            nbt.setTag(playerUUID, playernbt);
        } else {
            NBTTagCompound playernbt = new NBTTagCompound();
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getDouble("Balance");
            }
            double modifiedBalance = currentBalance + deposit;
            playernbt.setDouble("Balance", modifiedBalance);
            nbt.setTag(playerUUID, playernbt);
        }
        NBTTagCompound playernbt = nbt.getCompoundTag(playerUUID);
        NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
        if (deposit >= 0.1) {
        	player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "$" + formatBalance(deposit) + EnumChatFormatting.GREEN + " was sent to your bank account. Your current total balance is " + EnumChatFormatting.GOLD  + "$" + formatBalance(getBalance(player))));
        }
	}
	
	public static void depositToAccountViaUUID(String uuid, double deposit) {
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		double currentBalance = 0;
		String playerUUID = uuid;
        if (nbt.hasKey(playerUUID)) {
            NBTTagCompound playernbt = nbt.getCompoundTag(playerUUID);
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getDouble("Balance");
            }
            double modifiedBalance = currentBalance + deposit;
            playernbt.setDouble("Balance", modifiedBalance);
            nbt.setTag(playerUUID, playernbt);
        } else {
            NBTTagCompound playernbt = new NBTTagCompound();
            if (playernbt.hasKey("Balance")) {
                currentBalance = playernbt.getDouble("Balance");
            }
            double modifiedBalance = currentBalance + deposit;
            playernbt.setDouble("Balance", modifiedBalance);
            nbt.setTag(playerUUID, playernbt);
        }
        NBTTagCompound playernbt = nbt.getCompoundTag(playerUUID);
        NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
        if (deposit >= 0.1) {
        	//TODO player.addChatMessage(EnumChatFormatting.GOLD + "$" + formatBalance(deposit) + EnumChatFormatting.GREEN + " was sent to your bank account. Your current total balance is $" + EnumChatFormatting.GOLD + formatBalance(getBalance(player, player.worldObj)));
        }
	}
	
	public static boolean chargeAccountViaUUID(String uuid, double amt) {
		System.out.println("chargeAccountViaUUID");
		NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
		double cardBalance = getBalanceViaUUID(uuid);
		System.out.println("Amount (" + amt + " is less than/equal to cardBalanace (" + cardBalance + ")");
		if (amt <= cardBalance) {
			System.out.println("True!");
			double currentBalance = 0;
	        if (nbt.hasKey(uuid)) {
	        	System.out.println("hasKey UUID");
	            NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
	            if (playernbt.hasKey("Balance")) {
	            	System.out.println("hasKey balance");
	                currentBalance = playernbt.getDouble("Balance");
	            }
	            double modifiedBalance = currentBalance - amt;
	            playernbt.setDouble("Balance", modifiedBalance);
	            //TODO nbt.setCompoundTag(player.username, playernbt);
	        } else {
	        	System.out.println("Doesn't hasKey UUID");
	            NBTTagCompound playernbt = new NBTTagCompound();
	            if (playernbt.hasKey("Balance")) {
	                currentBalance = playernbt.getDouble("Balance");
	            }
	            double modifiedBalance = currentBalance - amt;
	            playernbt.setDouble("Balance", modifiedBalance);
	            //TODO nbt.setCompoundTag(player.username, playernbt);
	        }
	        NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
	        NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
			return true;
		}
		return false;
	}
	
	public static double getBalanceViaUUID(String uuid) {
		System.out.println("get balance via UUID");
		System.out.println("UUID: " + uuid);
        NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
        double balance = 0;
        if (nbt.hasKey(uuid)) {
        	System.out.println("nbt.hasKey UUID");
            NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
            if (playernbt.hasKey("Balance")) {
            	System.out.println("nbt.hasKey Balance");
                balance = playernbt.getDouble("Balance");
            }
        }
        return balance;
	}
}
