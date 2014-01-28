package co.uk.silvania.cities.core.blocks.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import co.uk.silvania.cities.core.blocks.entity.TileEntityFloatingShelves;

public class ContainerFloatingShelves extends Container {
	
	private TileEntityFloatingShelves te;
	
	public ContainerFloatingShelves(InventoryPlayer invPlayer, TileEntityFloatingShelves te) {
		this.te = te;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return te.isUseableByPlayer(entityPlayer);
	}
	
	
}