/*package co.uk.silvania.cities.econ.store.container;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.ClientPacketHandler;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.store.entity.TileEntityAutomaticShop;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAutomaticShop extends GuiContainer {
	
	int sellMode = 0;
	int x;
	int y;
	int z;
	String stockPrice = ClientPacketHandler.automaticStockPrice;
	String saleQuantity = ClientPacketHandler.automaticSaleQuantity;
	
	private ItemStack slot;
	
	private TileEntityAutomaticShop shopEntity;

	public GuiAutomaticShop(InventoryPlayer invPlayer, TileEntityAutomaticShop shopEntity) {
		super(new ContainerAutomaticShop(invPlayer, shopEntity));
		this.shopEntity = shopEntity;
		
		x = shopEntity.xCoord;
		y = shopEntity.yCoord;
		z = shopEntity.zCoord;
		
		this.slot = this.shopEntity.getStackInSlot(0);

		
		xSize = 241;
		ySize = 134;
	}

	private static final ResourceLocation texture = new ResourceLocation("flenixcities", "textures/gui/automaticshop.png");
	public GuiTextField stockPriceText;
	public GuiTextField saleQuantityText;
	public GuiButton buyButton;
	
	int slot1Qty = 1;

	
	public boolean isShopOwner() {
		if (mc.thePlayer.capabilities.isCreativeMode) {
			return true;
		}
		return false;
	}
	
	public void unfocusAllTextInputs() {
		updateTileEntity();
		stockPriceText.setFocused(false);
		saleQuantityText.setFocused(false);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.slot = this.shopEntity.getStackInSlot(0);

		
		buttonList.add(new InvisibleButton(1, guiLeft + 34, guiTop + 51, 38, 14, "")); //Buy 1
		buttonList.add(new InvisibleButton(2, guiLeft + 34, guiTop + 73, 38, 14, "")); //Buy 2
		buttonList.add(new InvisibleButton(3, guiLeft + 34, guiTop + 95, 38, 14, "")); //Buy 3
		buttonList.add(new InvisibleButton(4, guiLeft + 34, guiTop + 117, 38, 14, "")); //Buy 4
		buttonList.add(new InvisibleButton(5, guiLeft + 90, guiTop + 51, 38, 14, "")); //Sell 1
		buttonList.add(new InvisibleButton(6, guiLeft + 90, guiTop + 73, 38, 14, "")); //Sell 2
		buttonList.add(new InvisibleButton(7, guiLeft + 90, guiTop + 95, 38, 14, "")); //Sell 3
		buttonList.add(new InvisibleButton(8, guiLeft + 90, guiTop + 117, 38, 14, "")); //Sell 4
		
		buttonList.add(new InvisibleButton(9, guiLeft - 23, guiTop + 14, 26, 28, "")); //Buy View Tab
		buttonList.add(new InvisibleButton(10, guiLeft - 23, guiTop + 43, 26, 28, "")); //Sell Overview Tab
		
		if (isShopOwner()) {
			buttonList.add(new GuiButton(11, guiLeft + 177, guiTop + 82, 30, 20, "Buy"));
			buttonList.add(new GuiButton(11, guiLeft + 207, guiTop + 82, 30, 20, "Sell"));
		}
		
		buyButton = new GuiButton(16, guiLeft + 187, guiTop + 48, 30, 20, "Buy");

		buttonList.add(buyButton);
		
		buttonList.add(new InvisibleButton(24, guiLeft + 146, guiTop + 51, 36, 14, ""));
		buttonList.add(new InvisibleButton(25, guiLeft + 146, guiTop + 73, 36, 14, ""));
		buttonList.add(new InvisibleButton(26, guiLeft + 146, guiTop + 95, 36, 14, ""));
		buttonList.add(new InvisibleButton(27, guiLeft + 146, guiTop + 117, 36, 14, ""));
		
		stockPriceText = new GuiTextField(this.fontRenderer, 34, 51, 38, 14);
		saleQuantityText = new GuiTextField(this.fontRenderer, 34, 73, 38, 14);
		
		stockPriceText.setFocused(true);
		stockPriceText.setText("" + stockPrice);
		saleQuantityText.setText("" + saleQuantity);
	}
	
	public void actionPerformed(GuiButton button) {
		if (sellMode == 1) {
			switch(button.id) {
			case 1:
				unfocusAllTextInputs();
				stockPriceText.setFocused(true);
				break;
			case 2:
				unfocusAllTextInputs();
				saleQuantityText.setFocused(true);
				break;
			}
		}
		switch(button.id) {
		case 9:
			if (isShopOwner()) {
				sellMode = 0;
				sendSalePacket("buttonSwitch", sellMode, 0);
			}
			break;
		case 10:
			if (isShopOwner()) {
				sellMode = 1;
				sendSalePacket("buttonSwitch", sellMode, 0);
			}
			break;
		}
		if (sellMode == 0) {
			switch(button.id) {
			case 16:
				sendSalePacket("salePacket", 1, EconUtils.parseInt(saleQuantityText.getText()));
				break;
			}
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		if (slot == null || stockPriceText.getText().equals("0") || stockPriceText.getText().equals("0.0") || (EconUtils.parseDouble(stockPriceText.getText()) > EconUtils.parseDouble(stockPriceText.getText()))) {
			buyButton.enabled = false;
		}
    	
		if (isShopOwner()) {
			stockPriceText.drawTextBox();
			saleQuantityText.drawTextBox();
		} else {
			NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			nf.setRoundingMode(RoundingMode.HALF_UP);
			
			String price = nf.format(EconUtils.parseDouble("" + stockPrice));
			
			
			fontRenderer.drawString("$" + price, 51 - fontRenderer.getStringWidth(price) / 2, 54, 4210752);

		}
	    
		fontRenderer.drawString("Automatic Store", 5, 19, 4210752);
		fontRenderer.drawString("Server Shop", 34, 5, 4210752);
    	//fontRenderer.drawString("You have: " + EconUtils.reqClientInventoryBalance(), 100, 5, 4210752);
	}
	
	public int focus() {
		if (stockPriceText.isFocused()) {
			return 1;
		} else if (saleQuantityText.isFocused()) {
			return 2;
		}
		return 0;
	}
	
	@Override
	protected void keyTyped(char c, int keyCode) {
		if (keyCode == Keyboard.KEY_ESCAPE) {
			updateTileEntity();
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		if (isShopOwner()) {
			if (keyCode == Keyboard.KEY_RETURN) {
				unfocusAllTextInputs();
			}
			//Tab between the 8 boxes
			if (focus() == 0) {
				stockPriceText.setFocused(false);
				saleQuantityText.textboxKeyTyped(c, keyCode);
				if (keyCode == Keyboard.KEY_TAB) {
					updateTileEntity();
					stockPriceText.setFocused(true);
					saleQuantityText.setFocused(false);
				}
			} else if (focus() == 1) {
				saleQuantityText.setFocused(false);
				stockPriceText.textboxKeyTyped(c, keyCode);
				if (keyCode == Keyboard.KEY_TAB) {
					updateTileEntity();
					saleQuantityText.setFocused(true);
					stockPriceText.setFocused(false);
				}
			}
		}
	}
	
	public void updateTileEntity() {
        ByteArrayOutputStream bt = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bt);
        if (isShopOwner()) {
			try {
	        	if (CityConfig.debugMode == true) {
	        		System.out.println("Sending shop values to server");
	        	}
				out.writeUTF("Automatic Store");
				out.writeUTF(stockPriceText.getText());
				out.writeUTF(saleQuantityText.getText());
				out.writeUTF("");
				out.writeUTF("");
				out.writeUTF("");
				out.writeUTF("");
				out.writeUTF("");
				
				out.writeInt(x);
				out.writeInt(y);
				out.writeInt(z);
				Packet250CustomPayload packet = new Packet250CustomPayload("FCShopPacket", bt.toByteArray());
	            	
				PacketDispatcher.sendPacketToServer(packet);
				if (CityConfig.debugMode == true) {
					System.out.println("Automatic Store packet sent!");
				}
			}
			catch (IOException ex) {
				System.out.println("Packet Failed!");
			}
        }
	}
	
	public void sendSalePacket(String id, int itemId, int itemQty) {
        ByteArrayOutputStream bt = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bt);
		try {
	       	if (CityConfig.debugMode == true) {
	       		System.out.println("Sending sale packet to server");
	       	}
			out.writeUTF(id);
			out.writeInt(itemId);
			out.writeInt(itemQty);
			
			out.writeInt(x);
			out.writeInt(y);
			out.writeInt(z);
			Packet250CustomPayload packet = new Packet250CustomPayload("FCSalePacket", bt.toByteArray());
	           	
			PacketDispatcher.sendPacketToServer(packet);
			if (CityConfig.debugMode == true) {
				System.out.println("Floating Shelves packet sent: " + id + " " + itemId + " " + itemQty + " " + x + " " + y + " " + z);
			}
		}
		catch (IOException ex) {
			System.out.println("Packet Failed!");
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		if (isShopOwner()) {
			drawTexturedModalRect(guiLeft + 80, guiTop + 39, 0, 223, 2, 32);
			drawTexturedModalRect(guiLeft + 80, guiTop + 71, 0, 223, 2, 32);
			drawTexturedModalRect(guiLeft + 80, guiTop + 103, 0, 223, 2, 30);
			drawTexturedModalRect(guiLeft + 136, guiTop + 39, 0, 223, 2, 32);
			drawTexturedModalRect(guiLeft + 136, guiTop + 71, 0, 223, 2, 32);
			drawTexturedModalRect(guiLeft + 136, guiTop + 103, 0, 223, 2, 30);
			//Tabs?
			drawTexturedModalRect(guiLeft - 23, guiTop + 14, 62, 223, 26, 28);
			drawTexturedModalRect(guiLeft - 23, guiTop + 43, 88, 223, 26, 28);
			
			if (sellMode == 0) {
				//Click Tab 1
				drawTexturedModalRect(guiLeft - 26, guiTop + 14, 2, 223, 30, 28);
			} else if (sellMode == 1) {
				drawTexturedModalRect(guiLeft - 26, guiTop + 43, 32, 223, 30, 28);
			}
		}
		//Stock Slot Boxes
		drawTexturedModalRect(guiLeft + 7, guiTop + 49, 114, 223, 18, 18);
		drawTexturedModalRect(guiLeft + 7, guiTop + 71, 114, 223, 18, 18);
		drawTexturedModalRect(guiLeft + 7, guiTop + 93, 114, 223, 18, 18);
		drawTexturedModalRect(guiLeft + 7, guiTop + 115, 114, 223, 18, 18);
	}
	
	public boolean canPlayerAfford(int qty, double price) {
		double salePrice = qty * price;
		double invCash = ClientPacketHandler.invBalance;
    	
		if (invCash >= salePrice) {
			return true;
		}
		return false;
	}
}*/