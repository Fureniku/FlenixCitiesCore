package com.silvaniastudios.cities.econ.store.container;

import com.silvaniastudios.cities.econ.EconUtils;
import com.silvaniastudios.cities.econ.store.entity.TileEntityStockChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerStockChest extends Container {
	
	public TileEntityStockChest te;
	private IInventory stockChestInventory;
	
	public ContainerStockChest(InventoryPlayer invPlayer, TileEntityStockChest tileEntity) {
		te = tileEntity;
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 13; j++) {
				addSlotToContainer(new Slot(te, j + i * 13, 12 + j * 18, 62 + i * 18));
			}
		}
		addSlotToContainer(new Slot(te, 78, 12, 170));
		addSlotToContainer(new Slot(te, 79, 30, 170));
		addSlotToContainer(new Slot(te, 80, 48, 170));
		addSlotToContainer(new Slot(te, 81, 228, 40));
		bindPlayerInventory(invPlayer);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return te.isUseableByPlayer(player);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
        	for (int j = 0; j < 9; j++) {
        		addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 84 + j * 18, 174 + i * 18));
        	}
        }

        for (int i = 0; i < 9; i++) {
        	addSlotToContainer(new Slot(inventoryPlayer, i, 84 + i * 18, 232));
        }
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		if (slot > 117) {
			return null;
		}
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		//null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            
            //Container slots + 1. IMPORTANT!
            int invStart = 83;

            //merges the item into player inventory since its in the tileEntity
            if (slot < invStart) {
                    if (!this.mergeItemStack(stackInSlot, invStart, invStart + 35, true)) {
                            return null;
                    }
            }
            //places it into the tileEntity is possible since its in the player inventory
            else if (!this.mergeItemStack(stackInSlot, 0, invStart - 1, false)) {
                    return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
	        } else {
	                slotObject.onSlotChanged();
	        }
	
	        if (stackInSlot.stackSize == stack.stackSize) {
	                return null;
	        }
	        slotObject.onPickupFromSlot(player, stackInSlot);
			}
		return stack;
    }
	
	@Override
    public void putStackInSlot(int slot, ItemStack item) {
        this.getSlot(slot).putStack(item);
    }
	
	public IInventory getFloatingShelvesInventory() {
		return this.stockChestInventory;
	}
}
