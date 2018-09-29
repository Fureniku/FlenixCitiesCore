package com.silvaniastudios.cities.econ.atm;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.cities.core.FlenixCities;
import com.silvaniastudios.cities.network.SoundPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiATMNoCard extends GuiScreen {

    private static final ResourceLocation texture = new ResourceLocation("flenixcities", "textures/gui/atm.png");
    
    protected int xSize = 232;
    protected int ySize = 242;
    
    @Override
    public void initGui() {
    	super.initGui();
    	int guiLeft = width / 2;
    	int guiTop = height / 2;
    	buttonList.add(new ATMButton(1, guiLeft + 21, guiTop + 109, 24, 15, "7")); // 7
    	buttonList.add(new ATMButton(2, guiLeft + 53, guiTop + 109, 24, 15, "8")); // 8
    	buttonList.add(new ATMButton(3, guiLeft + 85, guiTop + 109, 24, 15, "9")); // 9
    	buttonList.add(new RedButton(4, guiLeft + 121, guiTop + 109, 24, 15, "")); // Cancel
    	buttonList.add(new ATMButton(5, guiLeft + 21, guiTop + 133, 24, 15, "4")); // 4
    	buttonList.add(new ATMButton(6, guiLeft + 53, guiTop + 133, 24, 15, "5")); // 5
    	buttonList.add(new ATMButton(7, guiLeft + 85, guiTop + 133, 24, 15, "6")); // 6
    	buttonList.add(new YellowButton(8, guiLeft + 121, guiTop + 133, 24, 15, "")); // Clear
    	buttonList.add(new ATMButton(9, guiLeft + 21, guiTop + 157, 24, 15, "1")); // 1
    	buttonList.add(new ATMButton(10, guiLeft + 53, guiTop + 157, 24, 15, "2")); // 2
    	buttonList.add(new ATMButton(11, guiLeft + 85, guiTop + 157, 24, 15, "3")); // 3
    	buttonList.add(new GreenButton(12, guiLeft + 121, guiTop + 157, 24, 15, "")); // Confirm
    	buttonList.add(new ATMButton(13, guiLeft + 53, guiTop + 181, 24, 15, "0")); // 0
    	
    	buttonList.add(new ATMButtonLeft(14, guiLeft - 21, guiTop - 7, 24, 15, "")); //Top-Left
    	buttonList.add(new ATMButtonLeft(16, guiLeft - 21, guiTop + 20, 24, 15, "")); //Mid-Upper Left
    	buttonList.add(new ATMButtonLeft(18, guiLeft - 21, guiTop + 47, 24, 15, "")); //Mid-Lower Left
    	buttonList.add(new ATMButtonLeft(20, guiLeft - 21, guiTop + 74, 24, 15, "")); //Bottom Left
    	
    	buttonList.add(new ATMButtonRight(15, guiLeft + 173, guiTop - 7, 24, 15, "")); //Top-Right
    	buttonList.add(new ATMButtonRight(17, guiLeft + 173, guiTop + 20, 24, 15, "")); //Mid-Upper Right
    	buttonList.add(new ATMButtonRight(19, guiLeft + 173, guiTop + 47, 24, 15, "")); //Mid-Lower Right
    	buttonList.add(new ATMButtonRight(21, guiLeft + 173, guiTop + 74, 24, 15, "")); //Bottom Right
    }
    
    public void actionPerformed(GuiButton guibutton) {
    	FlenixCities.network.sendToServer(new SoundPacket("flenixcities:atmButton"));
    }

    @Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    	int x = (width - xSize) / 2;
    	int y = (height - ySize) / 2;
    	this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    	
    	fontRenderer.drawString("ATM", -21, -30, 0x404040);
    	fontRenderer.drawString("Welcome!", 68, -2, 0x007F0E);
    	fontRenderer.drawString("Please insert your card.", 28, 8, 0x007F0E);
    }
}