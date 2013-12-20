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
	private IInventory ATMInventory;

	public ContainerFloatingShelves (InventoryPlayer inventoryPlayer, TileEntityFloatingShelves te) {
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