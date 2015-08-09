package co.uk.silvania.cities.econ.store.container;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import co.uk.silvania.cities.econ.store.entity.TileEntityStockChest;

public class GuiStockChest extends GuiContainer {
	
	private TileEntityStockChest stockEntity;

	public GuiStockChest(InventoryPlayer invPlayer, TileEntityStockChest stockEntity) {
		super(new ContainerStockChest(invPlayer, stockEntity));
		this.stockEntity = stockEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

	}
}