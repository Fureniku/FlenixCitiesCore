package co.uk.silvania.cities.econ.store.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.money.ItemCoin;
import co.uk.silvania.cities.econ.money.ItemNote;

public class TileEntityAdminShop extends TileEntity implements IInventory {
	
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
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.items.length; ++i)
        {
            if (this.items[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.items[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);
        
		nbt.setString("ownerName", ownerName + "");
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
        NBTTagList nbttaglist = nbt.getTagList("Items");
        this.items = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.items.length)
            {
                this.items[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
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
		nbt.setTag("ClientAShelfInv", items);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		NBTTagCompound nbt = pkt.data;
		
		NBTTagList tagList = nbt.getTagList("ClientAShelfInv");
		this.items = new ItemStack[getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound)tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if ((slot >= 0) && (slot < this.items.length)) {
				this.items[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
	}
	
	//Selling items TO the player
	public void sellItem(int i, int qty, EntityPlayer entityPlayer) {
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
			itemCost = buyPrice4;
		}
		double totalItemCost = itemCost * qty;
		double invCash = EconUtils.getInventoryCash(entityPlayer);
		
		if (invCash >= totalItemCost) {
			//Two birds, one stone. Charges the player for us, then tells us how much they paid so we can calculate change.
			double paidAmount = EconUtils.findCashInInventoryWithChange(entityPlayer, totalItemCost); //Complex code to charge the player's inventory
			if (paidAmount < 0) {
				entityPlayer.addChatMessage("You do not have enough money to do that!");
				return;
			} else {
				ItemStack item = getStackInSlot(i - 1);
				while (qty >= 1) {
					entityPlayer.inventory.addItemStackToInventory(new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
					qty--;
				}
				System.out.println(entityPlayer.getDisplayName() + " bought " + item.stackSize + " of " + item.getDisplayName() + " from the server, for $" + itemCost);
			}
		}
	}
	
	//Buying items FROM the player
	public void buyItem(int i, int q, EntityPlayer player) {
		ItemStack item = getStackInSlot(i - 1);
		int invQty = 0;
		double itemCost = 0;
		q = item.stackSize;
		
		if (i == 1) {
			itemCost = sellPrice1;
		}
		if (i == 2) {
			itemCost = sellPrice2;
		}
		if (i == 3) {
			itemCost = sellPrice3;
		}
		if (i == 4) {
			itemCost = sellPrice3;
		}
		
		for (int x = player.inventory.getSizeInventory() - 1; x >= 0; -- x) {
			ItemStack stack = player.inventory.getStackInSlot(x);
			if (stack != null) {
				if (stack.getItem() == item.getItem()) {
					invQty = invQty + stack.stackSize;
					if (invQty >= q) {
						invQty = q;
					}
				}
			}
		}
		if (invQty >= q) {
			int remain = q;
			for (int x = player.inventory.getSizeInventory() - 1; x >= 0; -- x) {
				ItemStack stack = player.inventory.getStackInSlot(x);
				if (stack != null) {
					if (remain > 0) {
						if (stack.getItem() == item.getItem()) {
							if (stack.stackSize >= remain) {
								player.inventory.decrStackSize(x, remain);
								EconUtils.giveChange(itemCost, 0, player);
								remain = 0;
								System.out.println(player.getDisplayName() + " sold " + item.stackSize + " of " + item.getDisplayName() + " to the server, for $" + itemCost);
							} else {
								remain = remain - stack.stackSize;
								player.inventory.setInventorySlotContents(x, null);
							}
						}
					}
				}
			}
		}
		((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
	}

	
	public TileEntityAdminShop() {
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