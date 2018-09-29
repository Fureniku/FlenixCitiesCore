package com.silvaniastudios.cities.econ.store.container;

import com.silvaniastudios.cities.econ.store.entity.TileEntityAdminShop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAdminShop extends Container {
	
	public TileEntityAdminShop te;
	private IInventory adminShopInventory;
	public static int tabButton;
	
	public ContainerAdminShop(InventoryPlayer invPlayer, TileEntityAdminShop te) {
		this.te = te;
		addSlotToContainer(new Slot(te, 0, 8, 50));
		addSlotToContainer(new Slot(te, 1, 8, 72));
		addSlotToContainer(new Slot(te, 2, 8, 94));
		addSlotToContainer(new Slot(te, 3, 8, 116));
		bindPlayerInventory(invPlayer);
	}

	
	protected void bindPlayerInventory(InventoryPlayer invPlayer) {
		//C = vertical inventory slots, "columns"
		//R = horizontal inventory slots, "rows"
		for (int c = 0; c < 3; c++) {
			for (int r = 0; r < 9; r++) {
				addSlotToContainer(new Slot(invPlayer, r + c * 9 + 9, 8 + r * 18, 141 + c * 18));
			}
		}
		//H = hotbar slots
		for (int h = 0; h <9; h++) {
			addSlotToContainer(new Slot(invPlayer, h, 8 + h * 18, 199));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return true;
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
                ItemStack stackInSlot = slotObject.getStack();
                stack = stackInSlot.copy();
                
                //Container slots + 1. IMPORTANT!
                int invStart = 4;

                //merges the item into player inventory since its in the tileEntity
                if (slot < invStart) {
                        if (!this.mergeItemStack(stackInSlot, invStart, invStart + 36, true)) {
                                return null;
                        }
                }
                //places it into the tileEntity is possible since its in the player inventory
                else if (!this.mergeItemStack(stackInSlot, 0, invStart - 1, false)) {
                        return null;
                }

                if (stackInSlot.getCount() == 0) {
                        slotObject.putStack(null);
                } else {
                        slotObject.onSlotChanged();
                }

                if (stackInSlot.getCount() == stack.getCount()) {
                        return null;
                }
                slotObject.onTake(player, stackInSlot);
        }
        return stack;
	}
	
	@Override
    public void putStackInSlot(int slot, ItemStack item) {
        this.getSlot(slot).putStack(item);
    }
	
	public IInventory getFloatingShelvesInventory() {
		return this.adminShopInventory;
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		if (player.capabilities.isCreativeMode) {
			super.slotClick(slotId, dragType, clickTypeIn, player);
		}
		return null;
	}
}