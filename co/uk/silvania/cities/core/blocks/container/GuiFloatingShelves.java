package co.uk.silvania.cities.core.blocks.container;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.cities.core.blocks.entity.TileEntityFloatingShelves;



public class GuiFloatingShelves extends GuiContainer {
	
    private static final ResourceLocation texture = new ResourceLocation("flenixcities", "textures/gui/floatingshelvesbuy.png");

        public GuiFloatingShelves (InventoryPlayer inventoryPlayer, TileEntityFloatingShelves tileEntity, World world, int x, int y, int z) {
                                super(new ContainerFloatingShelves(inventoryPlayer, tileEntity));
        }
        
        /** The X size of the inventory window in pixels. */
        protected int xSize = 256;

        /** The Y size of the inventory window in pixels. */
        protected int ySize = 140;

        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        	//GUI Title
        	fontRenderer.drawString("Floating Shelves", -35, 32, 4210752);
        	//Temporary. This will be replaced with an NBT value from the block, stating the name of whoever placed it.
        	fontRenderer.drawString("%Name%'s Shelf", -6, 18, 4210752);
        	//Titles. Always will be these.
        	fontRenderer.drawString("Buy", 4, 45, 0x00A012);
        	fontRenderer.drawString("Sell", 61, 45, 0xA80000);
        	
        	//Slot 1
        	fontRenderer.drawString("null", 4, 67, 0x00A012);
        	fontRenderer.drawString("null", 61, 67, 0xA80000);
        	
        	//Slot 2
        	fontRenderer.drawString("null", 4, 89, 0x00A012);
        	fontRenderer.drawString("null", 61, 89, 0xA80000);
        	
        	//Slot 3
        	fontRenderer.drawString("null", 4, 111, 0x00A012);
        	fontRenderer.drawString("null", 61, 111, 0xA80000);
        	
        	//Slot 4
        	fontRenderer.drawString("null", 4, 133, 0x00A012);
        	fontRenderer.drawString("null", 61, 133, 0xA80000);
        	
        	//Buys
        	fontRenderer.drawString("Buy", 105, 67, 0x000000);
        	fontRenderer.drawString("Buy", 105, 89, 0x000000);
        	fontRenderer.drawString("Buy", 105, 111, 0x000000);
        	fontRenderer.drawString("Buy", 105, 133, 0x000000);
        	
        	//Sells
        	fontRenderer.drawString("Sell", 141, 67, 0x000000);
        	fontRenderer.drawString("Sell", 141, 89, 0x000000);
        	fontRenderer.drawString("Sell", 141, 111, 0x000000);
        	fontRenderer.drawString("Sell", 141, 133, 0x000000);
        	
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.func_110434_K().func_110577_a(texture);
                int x = (width - xSize) / 2;
                int y = (height - ySize) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        }
}