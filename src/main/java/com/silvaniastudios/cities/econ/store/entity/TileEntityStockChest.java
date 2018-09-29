package com.silvaniastudios.cities.econ.store.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStockChest extends TileEntity implements IInventory {
	
	public boolean buying;
	public boolean selling;
	public double buyFundLimit;
	private ItemStack[] inv;
	
	public int invSize = 82;
	
	public String ownerName;
	public String ownerUuid;
	
	public TileEntityStockChest() {
		inv = new ItemStack[invSize];
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inv.length; ++i)
        {
            if (this.inv[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inv[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);
        
		nbt.setString("ownerName", ownerName + "");
		nbt.setString("ownerUuid", ownerUuid + "");
		
		nbt.setBoolean("buying", buying);
		nbt.setBoolean("selling", selling);
		nbt.setDouble("buyFundLimit", buyFundLimit);
		
		return nbt;	
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.inv = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.inv.length)
            {
                this.inv[j] = new ItemStack(nbttagcompound1);
            }
        }
		this.ownerName = nbt.getString("ownerName");
		this.ownerUuid = nbt.getString("ownerUuid");
		
		this.buying = nbt.getBoolean("buying");
		this.selling = nbt.getBoolean("selling");
		this.buyFundLimit = nbt.getDouble("buyFundLimit");
	}
	
	
	/*@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < this.inv.length; i++) {
			ItemStack item = this.inv[i];
			if (item != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				item.writeToNBT(tag);
				items.appendTag(tag);
			}
		}
		nbt.setString("ownerName", ownerName + "");
		nbt.setBoolean("buying", buying);
		nbt.setBoolean("selling", selling);
		nbt.setDouble("buyFundLimit", buyFundLimit);
		nbt.setTag("itemList", items);
		
		//this.world.markBlockForUpdate(pos.getX(), pos.getY(), pos.getZ());
		markDirty();
		
		return new SPacketUpdateTileEntity();
	}*/
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound nbt = pkt.getNbtCompound();
		
		NBTTagList tagList = nbt.getTagList("itemList", 10);
		this.inv = new ItemStack[getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if ((slot >= 0) && (slot < this.inv.length)) {
				this.inv[slot] = new ItemStack(tag);
			}
		}
		this.ownerName = nbt.getString("ownerName");
		this.buying = nbt.getBoolean("buying");
		this.selling = nbt.getBoolean("selling");
		this.buyFundLimit = nbt.getDouble("buyFundLimit");

		//this.world.updateLightByType(EnumSkyBlock.BLOCK, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public int getSizeInventory() {
		return invSize;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.getCount() > getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.getCount() <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.getCount() == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	/*@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}*/

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		return true;
	}

	@Override
	public String getName() {
		return "Stock Chest";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return world.getTileEntity(getPos()) == this && player.getDistanceSq(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) < 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
