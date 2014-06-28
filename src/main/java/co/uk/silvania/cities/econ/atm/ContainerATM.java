package co.uk.silvania.cities.econ.atm;

import java.util.HashSet;
import java.util.Set;



import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerATM extends Container {

	protected TileEntityATMEntity tileEntity;
	private IInventory ATMInventory;

	public ContainerATM (InventoryPlayer inventoryPlayer, TileEntityATMEntity te) {
		tileEntity = te;
	}

    @Override
    public boolean canInteractWith(EntityPlayer player) {
            return tileEntity.isUseableByPlayer(player);
    }

    public static Set validItems = new HashSet();
    
    public IInventory getATMInventory()
    {
        return this.ATMInventory;
    }
}