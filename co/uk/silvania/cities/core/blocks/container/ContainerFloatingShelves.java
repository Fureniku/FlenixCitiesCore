package co.uk.silvania.cities.core.blocks.container;

import java.util.HashSet;
import java.util.Set;

import co.uk.silvania.cities.core.blocks.entity.TileEntityFloatingShelves;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFloatingShelves extends Container {

	protected TileEntityFloatingShelves tileEntity;
	private IInventory FloatingShelvesInventory;

	public ContainerFloatingShelves (InventoryPlayer inventoryPlayer, TileEntityFloatingShelves te) {
		tileEntity = te;
		//Main Storage
		addSlotToContainer(new Slot(tileEntity, 1, -32, 63));
		addSlotToContainer(new Slot(tileEntity, 1, -32, 85));
		addSlotToContainer(new Slot(tileEntity, 1, -32, 107));
		addSlotToContainer(new Slot(tileEntity, 1, -32, 129));

	}

    @Override
    public boolean canInteractWith(EntityPlayer player) {
            return tileEntity.isUseableByPlayer(player);
    }


    public static Set validItems = new HashSet();
    
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
    public IInventory getFloatingShelvesInventory()
    {
        return this.FloatingShelvesInventory;
    }
}