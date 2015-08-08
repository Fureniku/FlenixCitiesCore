package co.uk.silvania.cities.econ.store.entity;

import java.util.logging.Logger;

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
	
	public void sellItem2(int i, int qty, EntityPlayer player) {
		System.out.println("Trying to sell an item to the player! Slot: " + i + ", qty: " + qty + " (should be 1), player: " + player);
	}
	
	//Selling items TO the player
	public void sellItem(int slotId, EntityPlayer entityPlayer) {
		EntityPlayer storeOwner = entityPlayer.worldObj.getPlayerEntityByName(ownerName);
		EnumChatFormatting gold = EnumChatFormatting.GOLD;
		EnumChatFormatting green = EnumChatFormatting.GREEN;
		EnumChatFormatting red = EnumChatFormatting.GOLD;
		EnumChatFormatting darkGreen = EnumChatFormatting.DARK_GREEN;
		
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
		int giveAmount = 1;
		boolean hasSpace = EconUtils.inventoryHasSpace(entityPlayer, getStackInSlot(slotId - 1));
		ItemStack item = getStackInSlot(slotId - 1);
		if (item == null) {
			return;
		}
		
		if (invCash >= itemCost && hasSpace) {
			//Two birds, one stone. Charges the player for us, then tells us how much they paid so we can calculate change.
			double paidAmount = EconUtils.findCashInInventoryWithChange(entityPlayer, itemCost); //Complex code to charge the player's inventory
			if (paidAmount < 0) {
				return;
			} else {
				while (giveAmount >= 1) {
					entityPlayer.inventory.addItemStackToInventory(item.copy());
					giveAmount--;
				}
				
               	setInventorySlotContents(slotId - 1, null);
				System.out.println(entityPlayer.getDisplayName() + " bought " + item.stackSize + " " + item.getDisplayName() + " from "/*TODO seller name*/ + ", for $" + EconUtils.formatBalance(itemCost));

				if (storeOwner != null) {
					storeOwner.addChatComponentMessage(new ChatComponentText(gold + entityPlayer.getDisplayName() + green + " bought " + gold + item.stackSize + " " + item.getDisplayName() + green + " from you for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
				}
				
				EconUtils.depositToAccountViaUUID(ownerUuid, worldObj, itemCost);
				
				entityPlayer.addChatComponentMessage(new ChatComponentText(green + "You bought " + gold + item.stackSize + " " + item.getDisplayName() + green + " from " + gold + ownerName + green + " for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
			}
		}
			if (invCash < itemCost) {
				double bankBalance = EconUtils.getBalance(entityPlayer, entityPlayer.getEntityWorld());
				if (bankBalance >= itemCost && hasSpace) {
					if (EconUtils.hasOwnCard(entityPlayer)) {
						if (EconUtils.chargePlayerAnywhere(entityPlayer, itemCost)) {
							while (giveAmount >= 1) {
								entityPlayer.inventory.addItemStackToInventory(new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
								giveAmount--;
							}
							
							setInventorySlotContents(slotId - 1, null);
							
							if (storeOwner != null) {
								storeOwner.addChatComponentMessage(new ChatComponentText(gold + entityPlayer.getDisplayName() + green + " bought " + gold + item.stackSize + " " + item.getDisplayName() + green + " from you for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
							}
							
							EconUtils.depositToAccountViaUUID(ownerUuid, worldObj, itemCost);
							
							entityPlayer.addChatComponentMessage(new ChatComponentText(green + "You bought " + gold + item.stackSize + " " + item.getDisplayName() + green + " from " + gold + ownerName + green + " for " + darkGreen + "$" + EconUtils.formatBalance(itemCost) + "!"));
							entityPlayer.addChatComponentMessage(new ChatComponentText(green + "You didn't have enough money with you, so the cost was split between your cash, and your bank balance. Your remaining bank balance is " + gold  + "$" + EconUtils.formatBalance(EconUtils.getBalance(entityPlayer, worldObj))));
						}
					}
				} else if (bankBalance >= itemCost && hasSpace) {
					if (EconUtils.hasOwnCard(entityPlayer)) {
						if (EconUtils.payBalanceByCard(entityPlayer, itemCost)) {
							while (giveAmount >= 1) {
								entityPlayer.inventory.addItemStackToInventory(new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
								giveAmount--;
							}
							entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You bought " + EnumChatFormatting.GOLD + item.stackSize + " " + item.getDisplayName() + EnumChatFormatting.GREEN + " from the server for " + EnumChatFormatting.DARK_GREEN + "$" + EconUtils.formatBalance(itemCost) + "!"));
							entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You didn't have enough money with you, so it was charged to your bank account instead. Your remaining bank balance is $" + EnumChatFormatting.GOLD + EconUtils.formatBalance(EconUtils.getBalance(entityPlayer, worldObj))));
						}
					}
				} else {
					if (hasSpace) {
						entityPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough money to do that! Next time, why not pay by card?"));
					}
				}
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
	
	//Buying items FROM the player
	public void buyItem(int slotId, EntityPlayer player) {
		player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "This feature is not yet implemented."));
		/*ItemStack item = getStackInSlot(slotId - 1);
		int ss = item.stackSize;
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
		
		for (int x = player.inventory.getSizeInventory() - 1; x >= 0; -- x) {
			ItemStack stack = player.inventory.getStackInSlot(x);
			if (stack != null) {
				if (stack.getItem() == item.getItem()) {
					invQty = invQty + stack.stackSize;
					if (invQty >= ss) {
						invQty = ss;
					}
				}
			}
		}
		if (invQty >= ss) {
			int remain = ss;
			for (int x = player.inventory.getSizeInventory() - 1; x >= 0; -- x) {
				ItemStack stack = player.inventory.getStackInSlot(x);
				if (stack != null) {
					if (remain > 0) {
						if (stack.getItem() == item.getItem()) {
							if (stack.stackSize >= remain) {
								player.inventory.decrStackSize(x, remain);
								EconUtils.giveChange(itemCost, 0, player);
								remain = 0;
								System.out.println(player.getDisplayName() + " sold " + item.stackSize + " " + item.getDisplayName() + " to the server, for $" + EconUtils.formatBalance(itemCost));
								player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You sold " + EnumChatFormatting.GOLD + ss + " " + item.getDisplayName() + EnumChatFormatting.GREEN + " to the server for " + EnumChatFormatting.DARK_GREEN + "$" + EconUtils.formatBalance(itemCost) + "!"));
							} else {
								remain = remain - stack.stackSize;
								player.inventory.setInventorySlotContents(x, null);
							}
						}
					}
				}
			}
		} else {
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "You don't have enough of these to sell right now."));
		}
		((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);*/
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