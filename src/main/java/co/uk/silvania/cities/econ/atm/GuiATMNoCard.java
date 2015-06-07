package co.uk.silvania.cities.econ.atm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiATMNoCard extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation("flenixcities", "textures/gui/atm.png");

    
    public GuiATMNoCard (InventoryPlayer inventoryPlayer, TileEntityATMEntity tileEntity) {
    	super(new ContainerATM(inventoryPlayer, tileEntity));
    }
    
    protected int xSize = 232;
    protected int ySize = 242;

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
    	fontRendererObj.drawString("ATM", -21, -30, 0x404040);
    	fontRendererObj.drawString("Welcome!", 68, -2, 0x007F0E);
    	fontRendererObj.drawString("Please insert your card.", 28, 8, 0x007F0E);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    	int x = (width - xSize) / 2;
    	int y = (height - ySize) / 2;
    	this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}