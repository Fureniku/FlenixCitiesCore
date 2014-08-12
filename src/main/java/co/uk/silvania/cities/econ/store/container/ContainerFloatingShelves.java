/*package co.uk.silvania.cities.econ.store.container;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import co.uk.silvania.cities.econ.store.entity.TileEntityFloatingShelves;

public class ContainerFloatingShelves extends Container {
	
	private TileEntityFloatingShelves te;
	private IInventory floatingShelvesInventory;
	public static int tabButton;
	
	public ContainerFloatingShelves(InventoryPlayer invPlayer, TileEntityFloatingShelves te) {
		this.te = te;
		addSlotToContainer(new Slot(te, 0, 8, 50));
		addSlotToContainer(new Slot(te, 1, 8, 72));
		addSlotToContainer(new Slot(te, 2, 8, 94));
		addSlotToContainer(new Slot(te, 3, 8, 116));
		bindPlayerInventory(invPlayer);
		int x = 8;
		int y = 0;
		
		if (tabButton == 2) {
			for (int c = 0; c < 4; c++) {
				for (int r = 0; r < 9; r++) {
					addSlotToContainer(new Slot(te, r + c*9 + 9 + 4, x + r * 18, y + c * 18));
				}
			}
		}
		if (tabButton == 3) {
			for (int c = 0; c < 4; c++) {
				for (int r = 0; r < 9; r++) {
					addSlotToContainer(new Slot(te, r + c*9 + 9 + 40, x + r * 18, y + c * 18));
				}
			}
		}
		if (tabButton == 4) {
			for (int c = 0; c < 4; c++) {
				for (int r = 0; r < 9; r++) {
					addSlotToContainer(new Slot(te, r + c*9 + 9 + 76, x + r * 18, y + c * 18));
				}
			}
		}
		if (tabButton == 5) {
			for (int c = 0; c < 4; c++) {
				for (int r = 0; r < 9; r++) {
					addSlotToContainer(new Slot(te, r + c*9 + 9 + 112, x + r * 18, y + c * 18));
				}
			}
		}

		for (int c = 0; c < 4; c++) {
			for (int r = 0; r < 9; r++) {
				addSlotToContainer(new Slot(te, r + c*9 + 9 + 148, x + r * 18, y + c * 18));
				System.out.println("Highest Slot: " + (r + c*9 + 148));
			}
		}
	}
	
	/*public void setCurrentPage(int page) {
		int currentPage = tabButton;

		// move all slots out of the screen
		for (Slot slot : (List<Slot>)inventorySlots) {
			if (!(slot.inventory instanceof InventoryPlayer)) {
				slot.xDisplayPosition = Integer.MIN_VALUE;
			}
		}

		// "reactivate" the ones for the current page
		int slotNumStart = page * 10;
		for (int i = 0; i < 10; i++) {
			((Slot)inventorySlots.get(i + slotNumStart)).xDisplayPosition = SLOT_X_POSTITIONS[i];
		}
	}*/
	/*
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
		return te.isUseableByPlayer(entityPlayer);
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        if (slotObject != null && slotObject.getHasStack()) {
        	ItemStack stackInSlot = slotObject.getStack();
        	stack = stackInSlot.copy();

        	if (slot < 9) {
        		if (!this.mergeItemStack(stackInSlot, 9, 45, true)) {
        			return null;
        		}
        	}

        	else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
        		return null;
        	}

        	if (stackInSlot.stackSize == 10) {
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
	
	public IInventory getFloatingShelvesInveotry() {
		return this.floatingShelvesInventory;
	}
}*/