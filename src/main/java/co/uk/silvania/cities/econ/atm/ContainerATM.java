package co.uk.silvania.cities.econ.atm;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerATM extends Container {

	protected TileEntityATMEntity tileEntity;

	public ContainerATM (InventoryPlayer inventoryPlayer, TileEntityATMEntity te) {
		tileEntity = te;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		// TODO Auto-generated method stub
		return true;
	}


}