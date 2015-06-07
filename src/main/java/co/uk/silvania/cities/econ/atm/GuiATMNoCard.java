package co.uk.silvania.cities.econ.atm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiATMNoCard extends GuiScreen {

    private static final ResourceLocation texture = new ResourceLocation("flenixcities", "textures/gui/atm.png");

    
    public GuiATMNoCard () {
    	System.out.println("ATM No card CONSTRUCTER");
    }
    
    protected int xSize = 232;
    protected int ySize = 242;

    @Override
    public void drawScreen(int par1, int par2, float par3) {
    	System.out.println("ATM No card DRAW BACKGROUND");
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    	int x = (width - xSize) / 2;
    	int y = (height - ySize) / 2;
    	this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    	
    	System.out.println("ATM No card DRAW FOREGROUND");
    	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
    	//fontRendererObj.drawString("Welcome!", 68, -2, 0x007F0E);
    	//fontRendererObj.drawString("Please insert your card.", 28, 8, 0x007F0E);
    	fontRendererObj.drawString("Currently out of order.", 28, 8, 0x007F0E);
    }
    
    /*protected void drawGuiContainerForegroundLayer(int param1, int param2) {
    	System.out.println("ATM No card DRAW FOREGROUND");
    	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
    	//fontRendererObj.drawString("Welcome!", 68, -2, 0x007F0E);
    	//fontRendererObj.drawString("Please insert your card.", 28, 8, 0x007F0E);
    	fontRendererObj.drawString("Currently out of order.", 28, 8, 0x007F0E);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	System.out.println("ATM No card DRAW BACKGROUND");
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    	int x = (width - xSize) / 2;
    	int y = (height - ySize) / 2;
    	this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }*/
}