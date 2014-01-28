package co.uk.silvania.cities.core.blocks.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFloatingShelves extends TileEntity implements IInventory {
	
	private ItemStack[] items;
	
	public TileEntityFloatingShelves() {
		items = new ItemStack[3]; //Amount of stacks
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
		items[i] = itemStack;
		
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
		
		onInventoryChanged();
		
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
		return item.itemID == Block.anvil.blockID;
	}
}

/*package co.uk.silvania.cities.core.blocks.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityFloatingShelves extends TileEntity {
	
	public String ownerName;
	public static String pOwnerName;
	
	public String item1;
	public int item1Qty;
	public double item1BuyPrice;
	public double item1SellPrice;
	
	public String item2;
	public int item2Qty;
	public double item2BuyPrice;
	public double item2SellPrice;
	
	public String item3;
	public int item3Qty;
	public double item3BuyPrice;
	public double item3SellPrice;
	
	public String item4;
	public int item4Qty;
	public double item4BuyPrice;
	public double item4SellPrice;
    
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("ownerName", ownerName);
		
		nbt.setString("item1", "Item 1");
		nbt.setInteger("item1Qty", 4);
		nbt.setDouble("item1BuyPrice", 2.5);
		nbt.setDouble("item1SellPrice", 5.0);
		
		nbt.setString("item2", "Item 2");
		nbt.setInteger("item2Qty", 1);
		nbt.setDouble("item2BuyPrice", 50.0);
		nbt.setDouble("item2SellPrice", 85.0);
		
		nbt.setString("item3", "Item 3");
		nbt.setInteger("item3Qty", 64);
		nbt.setDouble("item3BuyPrice", 2.5);
		nbt.setDouble("item3SellPrice", 4.99);
		
		nbt.setString("item4", "Item 4");
		nbt.setInteger("item4Qty", 16);
		nbt.setDouble("item4BuyPrice", 10);
		nbt.setDouble("item4SellPrice", 12);
		
		System.out.println("Writing! Name: " + ownerName);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.ownerName = nbt.getString("ownerName");
		
		this.item1 = nbt.getString("item1");
		this.item1Qty = nbt.getInteger("item1Qty");
		this.item1BuyPrice = nbt.getDouble("item1BuyPrice");
		this.item1SellPrice = nbt.getDouble("item1SellPrice");
		
		this.item2 = nbt.getString("item2");
		this.item2Qty = nbt.getInteger("item2Qty");
		this.item2BuyPrice = nbt.getDouble("item2BuyPrice");
		this.item2SellPrice = nbt.getDouble("item2SellPrice");
		
		this.item3 = nbt.getString("item3");
		this.item3Qty = nbt.getInteger("item3Qty");
		this.item3BuyPrice = nbt.getDouble("item3BuyPrice");
		this.item3SellPrice = nbt.getDouble("item3SellPrice");
		
		this.item4 = nbt.getString("item4");
		this.item4Qty = nbt.getInteger("item4Qty");
		this.item4BuyPrice = nbt.getDouble("item4BuyPrice");
		this.item4SellPrice = nbt.getDouble("item4SellPrice");
		
		pOwnerName = ownerName;
		System.out.println("Reading! Name: " + ownerName + " " + pOwnerName + ".");
	}
	
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }
}*/