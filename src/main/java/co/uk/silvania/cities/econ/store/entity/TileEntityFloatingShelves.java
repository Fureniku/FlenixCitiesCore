package co.uk.silvania.cities.econ.store.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.econ.DebitCardItem;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.money.ItemCoin;
import co.uk.silvania.cities.econ.money.ItemNote;

public class TileEntityFloatingShelves extends TileEntity implements IInventory {
	
	public String ownerName;
	public String ownerUuid;
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
	
	public int stockXPos;
	public int stockYPos;
	public int stockZPos;
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.items[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);
        
		nbt.setString("ownerName", ownerName + "");
		nbt.setString("ownerUuid", ownerUuid + "");
		nbt.setDouble("buyPrice1", buyPrice1);
		nbt.setDouble("sellPrice1", sellPrice1);
		nbt.setDouble("buyPrice2", buyPrice2);
		nbt.setDouble("sellPrice2", sellPrice2);
		nbt.setDouble("buyPrice3", buyPrice3);
		nbt.setDouble("sellPrice3", sellPrice3);
		nbt.setDouble("buyPrice4", buyPrice4);
		nbt.setDouble("sellPrice4", sellPrice4);
		
		nbt.setInteger("stockXPos", stockXPos);
		nbt.setInteger("stockYPos", stockYPos);
		nbt.setInteger("stockZPos", stockZPos);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.items = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.items.length)
            {
                this.items[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
		this.ownerName = nbt.getString("ownerName");
		this.ownerUuid = nbt.getString("ownerUuid");
		this.buyPrice1 = nbt.getDouble("buyPrice1");
		this.sellPrice1 = nbt.getDouble("sellPrice1");
		this.buyPrice2 = nbt.getDouble("buyPrice2");
		this.sellPrice2 = nbt.getDouble("sellPrice2");
		this.buyPrice3 = nbt.getDouble("buyPrice3");
		this.sellPrice3 = nbt.getDouble("sellPrice3");
		this.buyPrice4 = nbt.getDouble("buyPrice4");
		this.sellPrice4 = nbt.getDouble("sellPrice4");
		
		this.stockXPos = nbt.getInteger("stockXPos");
		this.stockYPos = nbt.getInteger("stockYPos");
		this.stockZPos = nbt.getInteger("stockZPos");
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < this.items.length; i++) {
			ItemStack item = this.items[i];
			if (item != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				item.writeToNBT(tag);
				items.appendTag(tag);
			}
		}
		nbt.setString("ownerName", ownerName + "");
		nbt.setDouble("buyPrice1", buyPrice1);
		nbt.setDouble("sellPrice1", sellPrice1);
		nbt.setDouble("buyPrice2", buyPrice2);
		nbt.setDouble("sellPrice2", sellPrice2);
		nbt.setDouble("buyPrice3", buyPrice3);
		nbt.setDouble("sellPrice3", sellPrice3);
		nbt.setDouble("buyPrice4", buyPrice4);
		nbt.setDouble("sellPrice4", sellPrice4);
		
		nbt.setTag("ClientAShelfInv", items);
		
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		markDirty();
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound nbt = pkt.func_148857_g();
		
		NBTTagList tagList = nbt.getTagList("ClientAShelfInv", 10);
		this.items = new ItemStack[getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if ((slot >= 0) && (slot < this.items.length)) {
				this.items[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		this.ownerName = nbt.getString("ownerName");
		this.buyPrice1 = nbt.getDouble("buyPrice1");
		this.sellPrice1 = nbt.getDouble("sellPrice1");
		this.buyPrice2 = nbt.getDouble("buyPrice2");
		this.sellPrice2 = nbt.getDouble("sellPrice2");
		this.buyPrice3 = nbt.getDouble("buyPrice3");
		this.sellPrice3 = nbt.getDouble("sellPrice3");
		this.buyPrice4 = nbt.getDouble("buyPrice4");
		this.sellPrice4 = nbt.getDouble("sellPrice4");

		this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
	}
	
	public ItemStack findStockItem(TileEntityStockChest stockChest, ItemStack item, String owner) {
		if (stockChest.ownerName.equalsIgnoreCase(ownerName)) {
		
			for (int i = 0; i < stockChest.invSize - 1; i++) {
				ItemStack stock = stockChest.getStackInSlot(i);
				if (stock != null && item != null) {
					ItemStack itemCopy = item.copy();
					ItemStack stockCopy = stock.copy();
					if (compareItemStacks(itemCopy, stockCopy)) {
						if (stock.stackSize >= item.stackSize) {
							if (CityConfig.debugMode) {
								System.out.println("And there is enough in the stack!");
							}
							int amt = item.stackSize;
							ItemStack retrievedItems = stockChest.decrStackSize(i, amt);
							//stock = stock.splitStack(amt);
							if (stock.stackSize == 0) {
								setInventorySlotContents(i, null);
							}
							System.out.println("amt: " + amt + ", Stock stacksize: " + stock.stackSize + ", Item stacksize:" + item.stackSize);
							stockChest.markDirty();
							stockChest.closeInventory();
							return retrievedItems;
						}
					}
				}
			}
		}
		return null;
	}
	
	public boolean compareItemStacks(ItemStack item1, ItemStack item2) {
		item1.stackSize = 1;
		item2.stackSize = 1;
		
		if (CityConfig.debugMode) {
			System.out.println("Comparing stacks.");
			System.out.println("Stack 1 is " + item1.getItem());
			System.out.println("Stack 2 is " + item2.getItem());
		}
		
		if (item1.getItem().equals(item2.getItem())) {
			if (CityConfig.debugMode) {
				System.out.println("Items match on ID, metadata and NBT");
			}
			return true;
		}
		return false;
	}
	
	//Selling items TO the player
	public void sellItem(int slotId, EntityPlayer entityPlayer) {
		World world = entityPlayer.worldObj;
		TileEntity te = world.getTileEntity(stockXPos, stockYPos, stockZPos);
		TileEntityStockChest stockChest = null;
		EntityPlayer storeOwner = entityPlayer.worldObj.getPlayerEntityByName(ownerName);
		EnumChatFormatting gold = EnumChatFormatting.GOLD;
		EnumChatFormatting green = EnumChatFormatting.GREEN;
		EnumChatFormatting red = EnumChatFormatting.RED;
		EnumChatFormatting darkGreen = EnumChatFormatting.DARK_GREEN;
		
		if (te != null && te instanceof TileEntityStockChest) {
			if (CityConfig.debugMode) {
				System.out.println("Stock chest found");
			}
			stockChest = (TileEntityStockChest) te;
		}
				
		double itemCost = 0;
		if (slotId == 1) {
			itemCost = buyPrice1;
		}
		if (slotId == 2) {
			itemCost = buyPrice2;
		}
		if (slotId == 3) {
			itemCost = buyPrice3;
		}
		if (slotId == 4) {
			itemCost = buyPrice4;
		}
		double invCash = EconUtils.getInventoryCash(entityPlayer);
		boolean hasSpace = EconUtils.inventoryHasSpace(entityPlayer, getStackInSlot(slotId - 1));
		ItemStack item = getStackInSlot(slotId - 1);
		if (item == null) {
			return;
		}
		
		if (stockChest != null)
			if (stockChest.selling) {
				if (invCash >= itemCost && hasSpace) {
					ItemStack stockItem = findStockItem(stockChest, item.copy(), ownerName);
					if (stockItem != null) {
					//Two birds, one stone. Charges the player for us, then tells us how much they paid so we can calculate change.
						double paidAmount = EconUtils.findCashInInventoryWithChange(entityPlayer, itemCost); //Complex code to charge the player's inventory
						if (paidAmount < 0) {
							return;
						} else {
							entityPlayer.inventory.addItemStackToInventory(stockItem);
			
							//setInventorySlotContents(slotId - 1, null);
							System.out.println(entityPlayer.getDisplayName() + " bought " + item.stackSize + " " + item.getDisplayName() + " from " + ownerName + " for $" + EconUtils.formatBalance(itemCost));
				
							if (storeOwner != null) {
								storeOwner.addChatComponentMessage(new ChatComponentText(gold + entityPlayer.getDisplayName() + green + " bought " + gold + item.stackSize + " " + item.getDisplayName() + green + " from you for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
							}
							
							EconUtils.depositToAccountViaUUID(ownerUuid, worldObj, itemCost);
							entityPlayer.addChatComponentMessage(new ChatComponentText(green + "You bought " + gold + item.stackSize + " " + item.getDisplayName() + green + " from " + gold + ownerName + green + " for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
						}
					} else {
						entityPlayer.addChatComponentMessage(new ChatComponentText(red + item.getDisplayName() + " is currently out of stock."));
		
						if (storeOwner != null) {
							storeOwner.addChatComponentMessage(new ChatComponentText(red + "Your " + item.getDisplayName() + " at " + gold + xCoord + ", " + yCoord + ", " + zCoord + red + " is currently out of stock."));
						}
					}
				}
				if (invCash < itemCost) {
					double bankBalance = EconUtils.getBalance(entityPlayer, entityPlayer.getEntityWorld());
					if (bankBalance >= itemCost && hasSpace) {
						if (EconUtils.hasOwnCard(entityPlayer)) {
							ItemStack stockItem = findStockItem(stockChest, item.copy(), ownerName);
							if (stockItem != null) {
								if (EconUtils.chargePlayerAnywhere(entityPlayer, itemCost)) {
									entityPlayer.inventory.addItemStackToInventory(stockItem);
									
									//setInventorySlotContents(slotId - 1, null);
									
									if (storeOwner != null) {
										storeOwner.addChatComponentMessage(new ChatComponentText(gold + entityPlayer.getDisplayName() + green + " bought " + gold + item.stackSize + " " + item.getDisplayName() + green + " from you for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
									}
									
									EconUtils.depositToAccountViaUUID(ownerUuid, worldObj, itemCost);
									
									entityPlayer.addChatComponentMessage(new ChatComponentText(green + "You bought " + gold + item.stackSize + " " + item.getDisplayName() + green + " from " + gold + ownerName + green + " for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
									entityPlayer.addChatComponentMessage(new ChatComponentText(green + "You didn't have enough money with you, so the cost was split between your cash, and your bank balance. Your remaining bank balance is " + gold  + "$" + EconUtils.formatBalance(EconUtils.getBalance(entityPlayer, worldObj))));
								}
							}
						}
					} else if (bankBalance >= itemCost && hasSpace) {
						if (EconUtils.hasOwnCard(entityPlayer)) {
							if (EconUtils.payBalanceByCard(entityPlayer, itemCost)) {
								ItemStack stockItem = findStockItem(stockChest, item.copy(), ownerName);
								if (stockItem != null) {
									entityPlayer.inventory.addItemStackToInventory(stockItem);
									
									if (storeOwner != null) {
										storeOwner.addChatComponentMessage(new ChatComponentText(gold + entityPlayer.getDisplayName() + green + " bought " + gold + item.stackSize + " " + item.getDisplayName() + green + " from you for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
									}
									
									EconUtils.depositToAccountViaUUID(ownerUuid, worldObj, itemCost);
									
									entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You bought " + EnumChatFormatting.GOLD + item.stackSize + " " + item.getDisplayName() + EnumChatFormatting.GREEN + " from the server for " + EnumChatFormatting.DARK_GREEN + "$" + EconUtils.formatBalance(itemCost) + "!"));
									entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You didn't have enough money with you, so it was charged to your bank account instead. Your remaining bank balance is $" + EnumChatFormatting.GOLD + EconUtils.formatBalance(EconUtils.getBalance(entityPlayer, worldObj))));
								}
							}
						}
					} else {
						if (hasSpace) {
							entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough money to do that! Next time, why not pay by card?"));
						}
					}
				}
			} else {
				entityPlayer.addChatComponentMessage(new ChatComponentText(red + "This shop is not currently selling stock."));
			}
		if (!hasSpace) {
			entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough free inventory space to buy that!"));
		}
	}
	
	public boolean hasOwnCard(EntityPlayer player) {
		for (int i = player.inventory.getSizeInventory(); i >= 0; --i) {
			ItemStack item = player.inventory.getStackInSlot(i);
			if (item.getItem() == CoreItems.debitCardNew) {
				return (DebitCardItem.checkCardOwner(player).equalsIgnoreCase(player.getUniqueID().toString()));
			}
		}
		return false;
	}
	
	//TODO Bugs to fix before release:
	//- Fund limit isn't altered when shop buys from player ///
	//- When selling an item to the shop, the stack directly in the shop goes up (dupe)
	//- The GUI updating on the stock chest is buggy.
	
	//Buying items FROM the player
	public void buyItem(int slotId, EntityPlayer entityPlayer) {
		World world = entityPlayer.worldObj;
		TileEntity te = world.getTileEntity(stockXPos, stockYPos, stockZPos);
		TileEntityStockChest stockChest = null;
		EntityPlayer storeOwner = world.getPlayerEntityByName(ownerName);
		EnumChatFormatting gold = EnumChatFormatting.GOLD;
		EnumChatFormatting green = EnumChatFormatting.GREEN;
		EnumChatFormatting red = EnumChatFormatting.RED;
		EnumChatFormatting darkGreen = EnumChatFormatting.DARK_GREEN;
		
		if (te != null && te instanceof TileEntityStockChest) {
			if (CityConfig.debugMode) {
				System.out.println("Stock chest found");
			}
			stockChest = (TileEntityStockChest) te;
		}
		
		ItemStack item = getStackInSlot(slotId - 1);
		int invQty = 0;
		double itemCost = 0;
		
		if (slotId == 1) {
			itemCost = sellPrice1;
		}
		if (slotId == 2) {
			itemCost = sellPrice2;
		}
		if (slotId == 3) {
			itemCost = sellPrice3;
		}
		if (slotId == 4) {
			itemCost = sellPrice4;
		}
		if (stockChest != null) {
			if (stockChest.buying) {
				if (stockChest.buyFundLimit >= itemCost) {
					if (EconUtils.getBalanceViaUUID(ownerUuid, world) >= itemCost) {
						for (int x = entityPlayer.inventory.getSizeInventory() - 1; x >= 0; -- x) {
							ItemStack stack = entityPlayer.inventory.getStackInSlot(x);
							if (stack != null) {
								if (stack.getItem() == item.getItem()) {
									invQty = invQty + stack.stackSize;
									if (invQty >= item.stackSize) {
										invQty = item.stackSize;
									}
								}
							}
						}
						if (invQty >= item.stackSize) {
							int insertSlot = findEmptySlot(item, stockChest);
							if (insertSlot >= 0) {
								int remain = item.stackSize;
								for (int x = entityPlayer.inventory.getSizeInventory() - 1; x >= 0; -- x) {
									ItemStack stack = entityPlayer.inventory.getStackInSlot(x);
									if (stack != null) {
										if (remain > 0) {
											if (stack.getItem() == item.getItem()) {
												if (stack.stackSize >= remain) {
													if (stockChest.getStackInSlot(insertSlot) != null) {
														int size = stockChest.getStackInSlot(insertSlot).stackSize;
														ItemStack insertItem = item.copy();
														insertItem.stackSize = insertItem.stackSize + size;
														if (insertItem.stackSize > insertItem.getMaxStackSize()) {
															insertItem.stackSize = insertItem.getMaxStackSize();
														}
														stockChest.setInventorySlotContents(insertSlot, insertItem);
													} else {
														stockChest.setInventorySlotContents(insertSlot, item);
													}
													entityPlayer.inventory.decrStackSize(x, remain);
													EconUtils.chargeAccountViaUUID(ownerUuid, world, itemCost);
													EconUtils.giveChange(itemCost, 0, entityPlayer);
													stockChest.buyFundLimit = stockChest.buyFundLimit - itemCost;
													stockChest.markDirty();
													stockChest.getDescriptionPacket();
													remain = 0;
													System.out.println(entityPlayer.getDisplayName() + " sold " + item.stackSize + " " + item.getDisplayName() + " to the server, for $" + EconUtils.formatBalance(itemCost));
													entityPlayer.addChatComponentMessage(new ChatComponentText(green + "You sold " + gold + item.stackSize + " " + item.getDisplayName() + green + " to " + ownerName + " for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
													if (storeOwner != null) {
														storeOwner.addChatComponentMessage(new ChatComponentText(green + entityPlayer.getDisplayName() + " has sold " + gold + item.stackSize + " " + item.getDisplayName() + green + " to you for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
													}
													setInventorySlotContents(slotId - 1, item);
												} else {
													remain = remain - stack.stackSize;
													entityPlayer.inventory.setInventorySlotContents(x, null);
												}
											}
										}
									}
								}
							} else {
								entityPlayer.addChatComponentMessage(new ChatComponentText(red + "This shop's stock is full! It can't take any more items."));
								if (storeOwner != null) {
									storeOwner.addChatComponentMessage(new ChatComponentText(red + "Your Stock Chest at " + gold + stockXPos + ", " + stockYPos + ", " + stockZPos + red + " has run out space for buying items."));
								}
							}
						} else {
							entityPlayer.addChatComponentMessage(new ChatComponentText(red + "You don't have enough of these to sell right now."));
						}
					} else {
						entityPlayer.addChatComponentMessage(new ChatComponentText(red + "This shop is out of funds. Please try later."));
						if (storeOwner != null) {
							storeOwner.addChatComponentMessage(new ChatComponentText(red + entityPlayer.getDisplayName() + " is trying to sell to you, but you don't have enough money in your bank."));
						}
					}
				} else {
					entityPlayer.addChatComponentMessage(new ChatComponentText(red + "This shop is out of funds. Please try later."));
					if (storeOwner != null) {
						storeOwner.addChatComponentMessage(new ChatComponentText(red + "Your Stock Chest at " + gold + stockXPos + ", " + stockYPos + ", " + stockZPos + red + " has run out of funds."));
					}
				}
			} else {
				entityPlayer.addChatComponentMessage(new ChatComponentText(red + "This shop is not currently buying stock."));
			}
		}
		((EntityPlayerMP) entityPlayer).sendContainerToPlayer(entityPlayer.inventoryContainer);
	}
	
	public int findEmptySlot(ItemStack item, TileEntityStockChest stockChest) {
		for (int i = 0; i < stockChest.getSizeInventory(); i++) {
			if (stockChest.getStackInSlot(i) != null) {
				if (item.getItem().equals(stockChest.getStackInSlot(i).getItem())) {
					int space = stockChest.getStackInSlot(i).getMaxStackSize() - stockChest.getStackInSlot(i).stackSize;
					if (space >= item.stackSize) {
						return i;
					}
				}
			}
		}
		for (int i = 0; i < stockChest.getSizeInventory(); i++) {
			if (stockChest.getStackInSlot(i) == null) {
				return i;
			}
		}
		return -1;
	}

	
	public TileEntityFloatingShelves() {
		items = new ItemStack[4]; //Amount of stacks
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
		System.out.println("decrStackSize");
		ItemStack itemStack = getStackInSlot(i);
		
		if (itemStack != null) {
			if (itemStack.stackSize <= amount) {
				setInventorySlotContents(i, null);
			} else {
				itemStack = itemStack.splitStack(amount);
			}
		}
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		System.out.println("getStackInSlotOnClosing");
		ItemStack itemStack = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		System.out.println("Setting stack contents for floating shelves");
		if (isItemValidForSlot(i, itemStack)) {
			items[i] = itemStack;
		
			if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
				itemStack.stackSize = getInventoryStackLimit();
			}
		
			//onInventoryChanged();
		}
	}
	
	public void addMoneyToShopInventory(int i, ItemStack itemStack, double amount) {
		if (isItemValidForSlot(i, itemStack)) {
			items[i] = itemStack;
		
			if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
				itemStack.stackSize = getInventoryStackLimit();
			}
		
			//onInventoryChanged();
		}
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

	//Checks that the item is valid. For shelves, use instanceof to see if it's item or block.
	@Override
	public boolean isItemValidForSlot(int i, ItemStack item) {
		ItemStack slot1 = getStackInSlot(0);
		ItemStack slot2 = getStackInSlot(1);
		ItemStack slot3 = getStackInSlot(2);
		ItemStack slot4 = getStackInSlot(3);
		if (item != null) {
			if (i > 3 && i <= 39) {
				return item.getItem() == slot1.getItem();
			}
			if (i > 39 && i <= 75) {
				return item.getItem() == slot2.getItem();
			}
			if (i > 75 && i <= 111) {
				return item.getItem() == slot3.getItem();
			}
			if (i > 111 && i <= 147) {
				return item.getItem() == slot4.getItem();
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

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
}