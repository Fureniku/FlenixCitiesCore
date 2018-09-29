/*package com.silvaniastudios.cities.core.client.models;

import org.lwjgl.opengl.GL11;



import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class TileEntityATMRenderer extends TileEntitySpecialRenderer {
	
	private ATMModel model;
		
	public TileEntityATMRenderer() {
		this.model = new ATMModel();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
	    int rotation = 180;
	    switch(te.getBlockMetadata() % 4) {
	        case 0:
	            rotation = 180;
	            break;
	        case 3:
	            rotation = 270;
	            break;
	        case 2:
	            rotation = 0;
	            break;
	        case 1:
	            rotation = 90;
	            break;
	    }

	    String texture = "textures/entities/atm.png";
	    switch(te.getBlockMetadata() / 4) {
	        case 0:
	            texture = "blockset1";
	            break;
	        case 1:
	            texture = "blockset2";
	            break;
	        case 2:
	            texture = "blockset3";
	            break;
	        case 3:
	            texture = "blockset4";
	            break;
	    }

		
		GL11.glPushMatrix();
		int i = te.getBlockMetadata();
		if (i == 0 || i == 1 || i == 2 || i == 3) {
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixcities", "textures/entities/atmstone.png"));
		}
		if (i == 4 || i == 5 || i == 6 || i == 7) {
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixcities", "textures/entities/atmbrick.png"));
		}
		if (i == 8 || i == 9 || i == 10 || i == 11) {
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixcities", "textures/entities/atmwhite.png"));
		}
		if (i == 12 || i == 13 || i == 14 || i == 15) {
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixcities", "textures/entities/atmgrey.png"));
		}
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		//GL11.glRotatef(((TileEntityBarrierEntity)tile).getRotationPivot()), 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
		Tessellator tess = Tessellator.instance;
		float brightness = block.getLightValue(world, i, j, k);
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  (float) modulousModifier,  divModifier);
	}
}*/