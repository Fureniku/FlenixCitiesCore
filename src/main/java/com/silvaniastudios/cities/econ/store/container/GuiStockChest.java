package com.silvaniastudios.cities.econ.store.container;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.silvaniastudios.cities.core.FlenixCities_Core;
import com.silvaniastudios.cities.econ.store.entity.TileEntityStockChest;
import com.silvaniastudios.cities.network.StockChestUpdatePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiStockChest extends GuiContainer {
	
	private TileEntityStockChest stockEntity;
	private static final ResourceLocation texture = new ResourceLocation("flenixcities", "textures/gui/stockChest.png");
	
	public GuiTextField buyLimit;
	public GuiButton buyToggle;
	public GuiButton sellToggle;
	
	String ownerName = "";
	boolean toggleBuy;
	boolean toggleSell;
	
	double buyFundLimit;

	public GuiStockChest(InventoryPlayer invPlayer, TileEntityStockChest stockEntity) {
		super(new ContainerStockChest(invPlayer, stockEntity));
		this.stockEntity = stockEntity;
		
		xSize = 256;
		ySize = 256;
		
		ownerName = stockEntity.ownerName;
		toggleBuy = stockEntity.buying;
		toggleSell = stockEntity.selling;
		
		buyFundLimit = stockEntity.buyFundLimit;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		buttonList.add(new GuiButton(1, guiLeft + 8, guiTop + 16, 30, 20, ""));
		buttonList.add(new GuiButton(2, guiLeft + 8, guiTop + 38, 30, 20, ""));
		buttonList.add(new GuiButton(3, guiLeft + 6, guiTop + 229, 74, 20, "Save Changes"));
		
		buyLimit = new GuiTextField(this.fontRendererObj, 160, 18, 84, 14);
		buyLimit.setFocused(true);
		buyLimit.setText("" + buyFundLimit);
	}
	
	public void actionPerformed(GuiButton button) {
		switch(button.id) {
		case 1:
			toggleBuy = !toggleBuy;
			updateTileEntity();
			break;
		case 2:
			toggleSell = !toggleSell;
			updateTileEntity();
			break;
		case 3:
			updateTileEntity();
			break;
		}
	}
	
	@Override
	protected void keyTyped(char c, int keyCode) {
		if (keyCode == Keyboard.KEY_ESCAPE) {
			updateTileEntity();
			Minecraft.getMinecraft().displayGuiScreen(null);
		} else {
			buyLimit.textboxKeyTyped(c, keyCode);
		}
	}
	
	public void updateTileEntity() {
		FlenixCities_Core.network.sendToServer(new StockChestUpdatePacket("" + toggleBuy, "" + toggleSell, buyLimit.getText()));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		int addonBuy = 0;
		int addonSell = 0;
		String tBuy = "";
		String tSell = "";
		
		if (toggleBuy) { addonBuy = 1; }
		if (toggleSell) { addonSell = 1; }
		if (toggleBuy) { tBuy = "True"; } else { tBuy = "False"; }
		if (toggleSell) { tSell = "True"; } else { tSell = "False"; }
		
		fontRendererObj.drawString("Owner: " + ownerName, 8, 6, 4210752);
		fontRendererObj.drawString("Buying Fund Limit", 160, 6, 4210752);
		fontRendererObj.drawString("" + tBuy, 10 + addonBuy, 22, 0xE0E0E0, true);
		fontRendererObj.drawString("" + tSell, 10 + addonSell, 44, 0xE0E0E0, true);
		fontRendererObj.drawString("Buying (From players)", 42, 22, 4210752);
		fontRendererObj.drawString("Selling (To players)", 42, 44, 4210752);
		fontRendererObj.drawString("Display Item:", 160, 44, 4210752);
		buyLimit.drawTextBox();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}