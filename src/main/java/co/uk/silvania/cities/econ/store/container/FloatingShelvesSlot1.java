package co.uk.silvania.cities.econ.store.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class FloatingShelvesSlot1 extends Slot {

	public FloatingShelvesSlot1(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		return true;
	}

}
