package co.uk.silvania.cities.econ.store.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.money.ItemCoin;
import co.uk.silvania.cities.econ.money.ItemNote;
import co.uk.silvania.cities.econ.store.container.ContainerFloatingShelves;

public class TileEntityFloatingShelves extends TileEntity implements IInventory {
	
	public String ownerName;
	public String userName;
	private ItemStack[] items;
	public double buyPrice1;
	public double sellPrice1;
	public double buyPrice2;
	public double sellPrice2;
	public double buyPrice3;
	public double sellPrice3;
	public double buyPrice4;
	public double sellPrice4;
	public int tabButton;
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);		
		nbt.setString("ownerName", ownerName);
		nbt.setDouble("buyPrice1", buyPrice1);
		nbt.setDouble("sellPrice1", sellPrice1);
		nbt.setDouble("buyPrice2", buyPrice2);
		nbt.setDouble("sellPrice2", sellPrice2);
		nbt.setDouble("buyPrice3", buyPrice3);
		nbt.setDouble("sellPrice3", sellPrice3);
		nbt.setDouble("buyPrice4", buyPrice4);
		nbt.setDouble("sellPrice4", sellPrice4);
		NBTTagList nbtTagList = new NBTTagList();
		//Credit to Lumien
		for (int i = 0; i < this.items.length; ++i) {
			if (this.items[i] != null) {
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot",  (byte)i);
				this.items[i].writeToNBT(compound);
				nbtTagList.appendTag(compound);
			}
		}
		nbt.setTag("Items",  nbtTagList);

		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbtTagList = nbt.getTagList("Items");
		
		this.ownerName = nbt.getString("ownerName");
		this.buyPrice1 = nbt.getDouble("buyPrice1");
		this.sellPrice1 = nbt.getDouble("sellPrice1");
		this.buyPrice2 = nbt.getDouble("buyPrice2");
		this.sellPrice2 = nbt.getDouble("sellPrice2");
		this.buyPrice3 = nbt.getDouble("buyPrice3");
		this.sellPrice3 = nbt.getDouble("sellPrice3");
		this.buyPrice4 = nbt.getDouble("buyPrice4");
		this.sellPrice4 = nbt.getDouble("sellPrice4");
		for (int i = 0; i < nbtTagList.tagCount(); i++) {
			NBTTagCompound nbt1 = (NBTTagCompound)nbtTagList.tagAt(i);
			int j = nbt1.getByte("Slot") & 255;
			
			if (j >= 0 && j < this.items.length) {
				this.items[j] = ItemStack.loadItemStackFromNBT(nbt1);
			}
		}

	}
	
	public int getQuantity(int i) {
		int qty;
		int startSlot;
		int endSlot;
		if (i == 0) {
			
		} else if (i == 1) {
			
		} else if (i == 2) {
			
		} else if (i == 3) {
			
		}
		//For loop.
		return 0;
	}
	
	public boolean isShopOpen() {
		return true;
	}
	
	public void sellItem(int i, int q, EntityPlayer entityPlayer) {
		System.out.println("We are beginning!");
		if (entityPlayer.username.equals(userName)) {
			System.out.println("It's the correct player; name matches packet ID");
			double itemCost = 0;
			if (i == 1) {
				itemCost = buyPrice1;
			}
			if (i == 2) {
				itemCost = buyPrice2;
			}
			if (i == 3) {
				itemCost = buyPrice3;
			}
			if (i == 4) {
				itemCost = buyPrice3;
			}
			double totalItemCost = itemCost * q;
			double invCash = EconUtils.getInventoryCash(entityPlayer);
			
			if (invCash >= totalItemCost) {
				//Two birds, one stone. Charges the player for us, then tells us how much they paid so we can calculate change.
				double paidAmount = EconUtils.findCashInInventoryWithChange(entityPlayer, totalItemCost); //Complex code to charge the player's inventory
				if (paidAmount == 0) {
					//They didn't pay enough. Don't take the money, and tell 'em to piss off.
					return;
				} else {
					//Checks the shop has enough in stock. If not, it'll cancel the transaction.
					if (q >= getQuantity(i)) {
						
						//Check if shop has enough to give some change.
						//If so, it will give change. Server may split notes if required.
						//If not, it cancels the transaction.
						if (giveChangeFromInventory(paidAmount - totalItemCost, entityPlayer)) {

							//For loop. Start in the last slot of the inventory of the item they are buying, loops back until the first.
						} else {
							//Do not take cash, do not give items, send message to player that the shop does not have enough change to give.
						}
					} else {
						//Not enough items in stock to complete the sale. Inform the buyer, and tell them how many there are.
						
					}
				}
			}
		}
	}
	
	int startSlot = 148; 
	int endSlot = 184;
	
	public boolean giveChangeFromInventory(double value, EntityPlayer player) {
		if (getTileEntityInventoryCash() >= value) {
			double nearestAmount = findCashInTileEntityInventory(value); //Remove the amount of change from the TileEntity.
			if (nearestAmount == value) {
				return true;
			}
			if (nearestAmount >= value) {
				double toPlayer = value;
				double toShop = nearestAmount - value;
				EconUtils.giveChange(toPlayer, 0, player);
				//Double For loop to find the first viable slot.
				//First, find slot
				double storedShopChange = toShop;
				for (int i = startSlot; i <= endSlot; ++i) {
					System.out.println("");
					ItemStack item = getStackInSlot(i);
					//Then, find contents of slot. If null, it's fine; use it.
					//Else, if it's instance of itemcoin/itemnote, find it's value.
					if (item != null) {
						double moneyValue = 0;
						if (item.getItem() instanceof ItemNote) {
							ItemNote note = (ItemNote) item.getItem();
							moneyValue = note.getMoneyValue();
						}
						if (item.getItem() instanceof ItemCoin) {
							ItemCoin coin= (ItemCoin) item.getItem();
							moneyValue = coin.getMoneyValue();
						}
						//Once you have it's value, compare to the amount you're depositing.
						//If it's value is LOWER than your remaining deposit amount, add 1 to it, take the value off of the amount to go, repeat loop.
						//If the value is HIGHER, move on to the next slot.
						if (moneyValue <= storedShopChange) {
							if (moneyValue == storedShopChange) {
								setInventorySlotContents(i, new ItemStack(item.getItem(), item.stackSize + 1, 0));
							}
							setInventorySlotContents(i, item);
							storedShopChange = storedShopChange - moneyValue;
						}
					} else {
						setInventorySlotContents(i, item);
					}
				}
				return true;
			}
		}
		return false;
	}

	public double findCashInTileEntityInventory(double value) {
		if (getTileEntityInventoryCash() >= value) {
			for (int i = endSlot; i >= startSlot; --i) {
				ItemStack item = getStackInSlot(i);
				if (item != null) {
					if (item.getItem() instanceof ItemNote || item.getItem() instanceof ItemCoin) {
						int qty = item.stackSize;
						double noteValue = 0;
						double coinValue = 0;
						if (item.getItem() instanceof ItemNote) {
							//These are inside if statements in order to avoid bad casting.
							ItemNote note = (ItemNote) item.getItem();
							noteValue = note.getMoneyValue();
						}
						if (item.getItem() instanceof ItemCoin) {
							ItemCoin coin = (ItemCoin) item.getItem();
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
								decrStackSize(i, x);
								double reqAmount = moneyValue * x;
								System.out.println("Available for change: " + (reqAmount - value));
								//If second loop pays enough, we return; we don't need to do anything else as the balance has been paid.
								return reqAmount;
							}
						}
						//If second loop fails, this part is ran.
						currentlyPaid = currentlyPaid + (moneyValue * qty);
						decrStackSize(i, qty);
					}
				}
			}
		}
		//If container doesn't have enough to cover the change, we'll cancel the transaction and warn the buyer.
		return 0.0;
	}
	
	public double getTileEntityInventoryCash() {
		double balance = 0;
		for (int i = endSlot; i >= startSlot; -- i) {
			ItemStack item = getStackInSlot(i);
			if (item != null) {
				if (item.getItem() instanceof ItemNote) {
					int quantity = item.stackSize;
					ItemNote note = (ItemNote) item.getItem();
					double noteValue = note.getMoneyValue();
					
					double totalValue = noteValue * quantity;
					if (CityConfig.debugMode == true) {
						System.out.println("There is a note stack with value of " + noteValue  + " in the shop. The stack size is " + quantity + " with a total value of " + totalValue);
					}
					balance = balance + totalValue;
				} else if (item.getItem() instanceof ItemCoin) {
					int quantity = item.stackSize;
					ItemCoin coin = (ItemCoin) item.getItem();
					double coinValue = coin.getMoneyValue();
					
					double totalValue = coinValue * quantity;
					
					if (CityConfig.debugMode == true) {
						System.out.println("There is a coin stack with value of " + coinValue  + "in the shop. The stack size is " + quantity + " with a total value of " + totalValue);
					}
					balance = balance + totalValue;
				}
			}
		}
		return balance;
	}
	
	@Override
	public void updateEntity() {
		ContainerFloatingShelves.tabButton = tabButton;
	}
	
	public TileEntityFloatingShelves() {
		items = new ItemStack[256]; //Amount of stacks
	}

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int amount) {
		ItemStack itemStack = getStackInSlot(i);
		
		if (itemStack != null) {
			if (itemStack.stackSize <= amount) {
				setInventorySlotContents(i, null);
			} else {
				itemStack = itemStack.splitStack(amount);
				onInventoryChanged(); //Update the inventory. TODO use this for card removal
			}
		}
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack itemStack = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		if (isItemValidForSlot(i, itemStack)) {
			items[i] = itemStack;
		
			if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
				itemStack.stackSize = getInventoryStackLimit();
			}
		
			onInventoryChanged();
		}
	}
	
	public void addMoneyToShopInventory(int i, ItemStack itemStack, double amount) {
		if (isItemValidForSlot(i, itemStack)) {
			items[i] = itemStack;
		
			if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
				itemStack.stackSize = getInventoryStackLimit();
			}
		
			onInventoryChanged();
		}
	}

	@Override
	public String getInvName() {
		return "Floating Shelves";
	}

	@Override //Apparently not used...
	public boolean isInvNameLocalized() {
		return true;
	}

	//Max size of stacks placed inside.
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	//Checks if the player can use it.
	//Wouldn't work for owner only- if I did that, no one else would be able to interact (Maybe? Need to test that.)
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
		return entityPlayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	//Not used
	@Override
	public void openChest() {}

	//Not used
	@Override
	public void closeChest() {}

	//Checks that the item is valid. For shelves, use instanceof to see if it's item or block.
	@Override
	public boolean isItemValidForSlot(int i, ItemStack item) {
		ItemStack slot1 = getStackInSlot(0);
		ItemStack slot2 = getStackInSlot(1);
		ItemStack slot3 = getStackInSlot(2);
		ItemStack slot4 = getStackInSlot(3);
		if (item != null) {
			if (i > 3 && i <= 39) {
				return item.itemID == slot1.itemID;
			}
			if (i > 39 && i <= 75) {
				return item.itemID == slot2.itemID;
			}
			if (i > 75 && i <= 111) {
				return item.itemID == slot3.itemID;
			}
			if (i > 111 && i <= 147) {
				return item.itemID == slot4.itemID;
			}
			if (i > 147 && i <= 184) {
				if (item.getItem() instanceof ItemCoin || item.getItem() instanceof ItemNote) {
					return true;
				} else {
					return false;
				}
			} if (i > 184) {
				return false;
			}
		}
		return true;
	}
}