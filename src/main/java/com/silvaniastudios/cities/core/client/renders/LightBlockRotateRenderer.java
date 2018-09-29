/*package com.silvaniastudios.cities.core.client.renders;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.cities.core.CoreBlocks;
import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class LightBlockRotateRenderer implements ISimpleBlockRenderingHandler {
	
	Tessellator tess;

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		tess = Tessellator.instance;
		
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		
		IIcon light = CoreBlocks.lightingBlocks.getIcon(0, meta);
		IIcon side = CoreBlocks.lightingBlocks.getIcon(2, meta);
		
		double u0 = (double) light.getMinU();
		double u1 = (double) light.getMaxU();
		
		double v0 = (double) light.getMinV();
		double v1 = (double) light.getMaxV();
		
		double u2 = (double) side.getMinU();
		double u3 = (double) side.getMaxU();
		
		double v2 = (double) side.getMinV();
		double v3 = (double) side.getMaxV();
		
		tess.startDrawingQuads();
		
		if (meta == 0) {  		 renderItem(tess, 0.375, 0.0, 0.875, 0.625, 1.0, 1.0, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 1) {  renderItem(tess, 0.0, 0.0, 0.375, 0.125, 1.0, 0.625, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 2) {  renderItem(tess, 0.375, 0.0, 0.0, 0.625, 1.0, 0.125, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 3) {  renderItem(tess, 0.875, 0.0, 0.325, 1.0, 1.0, 0.625, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 4) {  renderItem(tess, 0.375, 0.25, 0.875, 0.625, 0.75, 1.0, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 5) {  renderItem(tess, 0.0, 0.25, 0.375, 0.125, 0.75, 0.625, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 6) {  renderItem(tess, 0.375, 0.25, 0.0, 0.625, 0.75, 0.125, u0, u1, u2, u3, v0, v1, v2, v3);	
    	} else if (meta == 7) {  renderItem(tess, 0.875, 0.25, 0.325, 1.0, 0.75, 0.625, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 8) {  renderItem(tess, 0.0, 0.0, 0.9375, 1.0, 1.0, 1.0, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 9) {  renderItem(tess, 0.0, 0.0, 0.0, 0.0625, 1.0, 1.0, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 10) { renderItem(tess, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0625, u0, u1, u2, u3, v0, v1, v2, v3);	
    	} else if (meta == 11) { renderItem(tess, 0.9375, 0.0, 0.0, 1.0, 1.0, 1.0, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 12) { renderItem(tess, 0.25, 0.25, 0.9375, 0.75, 0.75, 1.0, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 13) { renderItem(tess, 0.0, 0.25, 0.25, 0.0625, 0.75, 0.75, u0, u1, u2, u3, v0, v1, v2, v3);
    	} else if (meta == 14) { renderItem(tess, 0.25, 0.25, 0.0, 0.75, 0.75, 0.0625, u0, u1, u2, u3, v0, v1, v2, v3);	
    	} else if (meta == 15) { renderItem(tess, 0.9375, 0.25, 0.25, 1.0, 0.75, 0.75, u0, u1, u2, u3, v0, v1, v2, v3);
    	} 
		
		tess.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		tess = Tessellator.instance;
		
		tess.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        
		float f = 0.8F;
		int c = block.colorMultiplier(world, x, y, z);
		float f1 = (float)(c >> 16 & 255) / 255.0F;
		float f2 = (float)(c >> 8 & 255) / 255.0F;
		float f3 = (float)(c & 255) / 255.0F;

		tess.setColorOpaque_F(f * f1, f * f2, f * f3);
		
		int meta = renderer.blockAccess.getBlockMetadata(x, y, z);
		IIcon icon = block.getIcon(0, meta);
		IIcon side = block.getIcon(1, meta);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		double u2 = (double) side.getMinU();
		double u3 = (double) side.getMaxU();
		
		double v2 = (double) side.getMinV();
		double v3 = (double) side.getMaxV();		
		
		if (meta == 0) {  		 renderBlock(tess, x + 0.375, 	y + 0.0, 	z + 0.875,	x + 0.625, 	y + 1.0,	z + 1.0, 	u0, u1, u2, u3, v0, v1, v2, v3, "NORTH");
    	} else if (meta == 1) {  renderBlock(tess, x + 0.0, 	y + 0.0, 	z + 0.375,	x + 0.125, 	y + 1.0, 	z + 0.625, 	u0, u1, u2, u3, v0, v1, v2, v3, "EAST");
    	} else if (meta == 2) {  renderBlock(tess, x + 0.375, 	y + 0.0, 	z + 0.0, 	x + 0.625, 	y + 1.0, 	z + 0.125, 	u0, u1, u2, u3, v0, v1, v2, v3, "SOUTH");
    	} else if (meta == 3) {  renderBlock(tess, x + 0.875, 	y + 0.0, 	z + 0.325,	x + 1.0, 	y + 1.0,	z + 0.625, 	u0, u1, u2, u3, v0, v1, v2, v3, "WEST");
    	} else if (meta == 4) {  renderBlock(tess, x + 0.375, 	y + 0.25, 	z + 0.875,	x + 0.625, 	y + 0.75,	z + 1.0, 	u0, u1, u2, u3, v0, v1, v2, v3, "NORTH");
    	} else if (meta == 5) {  renderBlock(tess, x + 0.0, 	y + 0.25, 	z + 0.375, 	x + 0.125, 	y + 0.75, 	z + 0.625, 	u0, u1, u2, u3, v0, v1, v2, v3, "EAST");
    	} else if (meta == 6) {  renderBlock(tess, x + 0.375, 	y + 0.25, 	z + 0.0, 	x + 0.625, 	y + 0.75, 	z + 0.125, 	u0, u1, u2, u3, v0, v1, v2, v3, "SOUTH");	
    	} else if (meta == 7) {  renderBlock(tess, x + 0.875, 	y + 0.25, 	z + 0.325, 	x + 1.0, 	y + 0.75, 	z + 0.625, 	u0, u1, u2, u3, v0, v1, v2, v3, "WEST");
    	} else if (meta == 8) {  renderBlock(tess, x + 0.0, 	y + 0.0, 	z + 0.9375, x + 1.0, 	y + 1.0, 	z + 1.0, 	u0, u1, u2, u3, v0, v1, v2, v3, "NORTH");
    	} else if (meta == 9) {  renderBlock(tess, x + 0.0, 	y + 0.0, 	z + 0.0, 	x + 0.0625, y + 1.0, 	z + 1.0, 	u0, u1, u2, u3, v0, v1, v2, v3, "EAST");
    	} else if (meta == 10) { renderBlock(tess, x + 0.0, 	y + 0.0, 	z + 0.0, 	x + 1.0, 	y + 1.0, 	z + 0.0625, u0, u1, u2, u3, v0, v1, v2, v3, "SOUTH");	
    	} else if (meta == 11) { renderBlock(tess, x + 0.9375, 	y + 0.0, 	z + 0.0,	x + 1.0, 	y + 1.0, 	z + 1.0, 	u0, u1, u2, u3, v0, v1, v2, v3, "WEST");
    	} else if (meta == 12) { renderBlock(tess, x + 0.25, 	y + 0.25, 	z + 0.9375, x + 0.75, 	y + 0.75, 	z + 1.0, 	u0, u1, u2, u3, v0, v1, v2, v3, "NORTH");
    	} else if (meta == 13) { renderBlock(tess, x + 0.0, 	y + 0.25, 	z + 0.25, 	x + 0.0625, y + 0.75, 	z + 0.75, 	u0, u1, u2, u3, v0, v1, v2, v3, "EAST");
    	} else if (meta == 14) { renderBlock(tess, x + 0.25, 	y + 0.25, 	z + 0.0, 	x + 0.75, 	y + 0.75, 	z + 0.0625, u0, u1, u2, u3, v0, v1, v2, v3, "SOUTH");	
    	} else if (meta == 15) { renderBlock(tess, x + 0.9375, 	y + 0.25, 	z + 0.25, 	x + 1.0, 	y + 0.75, 	z + 0.75, 	u0, u1, u2, u3, v0, v1, v2, v3, "WEST");
    	} 		
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.lightBlockRotateRenderID;
	}
	
	public void renderBlock(Tessellator tess, double xl, double yl, double zl, double xh, double yh, double zh, double u0, double u1, double u2, double u3, double v0, double v1, double v2, double v3, String dir) {
		int col = 255;
		
		if (dir.contains("NORTH")) {
			//Top Side
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zl, u3, v3);
			tess.addVertexWithUV(xl, yh, zh, u3, v2);
			tess.addVertexWithUV(xh, yh, zh, u2, v2);
			tess.addVertexWithUV(xh, yh, zl, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xh, yh, zl, u1, v1);
			tess.addVertexWithUV(xh, yl, zl, u1, v0);
			tess.addVertexWithUV(xl, yl, zl, u0, v0);
			tess.addVertexWithUV(xl, yh, zl, u0, v1);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xh, yh, zh, u3, v3);
			tess.addVertexWithUV(xh, yl, zh, u3, v2);
			tess.addVertexWithUV(xh, yl, zl, u2, v2);
			tess.addVertexWithUV(xh, yh, zl, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zh, u3, v3);
			tess.addVertexWithUV(xl, yl, zh, u3, v2);
			tess.addVertexWithUV(xh, yl, zh, u2, v2);
			tess.addVertexWithUV(xh, yh, zh, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zl, u3, v3);
			tess.addVertexWithUV(xl, yl, zl, u3, v2);
			tess.addVertexWithUV(xl, yl, zh, u2, v2);
			tess.addVertexWithUV(xl, yh, zh, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yl, zh, u3, v3);
			tess.addVertexWithUV(xl, yl, zl, u3, v2);
			tess.addVertexWithUV(xh, yl, zl, u2, v2);
			tess.addVertexWithUV(xh, yl, zh, u2, v3);
			
		} else if (dir.contains("EAST")) {
			//Top Side
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zl, u3, v3);
			tess.addVertexWithUV(xl, yh, zh, u3, v2);
			tess.addVertexWithUV(xh, yh, zh, u2, v2);
			tess.addVertexWithUV(xh, yh, zl, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xh, yh, zl, u3, v3);
			tess.addVertexWithUV(xh, yl, zl, u3, v2);
			tess.addVertexWithUV(xl, yl, zl, u2, v2);
			tess.addVertexWithUV(xl, yh, zl, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xh, yh, zh, u1, v1);
			tess.addVertexWithUV(xh, yl, zh, u1, v0);
			tess.addVertexWithUV(xh, yl, zl, u0, v0);
			tess.addVertexWithUV(xh, yh, zl, u0, v1);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zh, u3, v3);
			tess.addVertexWithUV(xl, yl, zh, u3, v2);
			tess.addVertexWithUV(xh, yl, zh, u2, v2);
			tess.addVertexWithUV(xh, yh, zh, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zl, u3, v3);
			tess.addVertexWithUV(xl, yl, zl, u3, v2);
			tess.addVertexWithUV(xl, yl, zh, u2, v2);
			tess.addVertexWithUV(xl, yh, zh, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yl, zh, u3, v3);
			tess.addVertexWithUV(xl, yl, zl, u3, v2);
			tess.addVertexWithUV(xh, yl, zl, u2, v2);
			tess.addVertexWithUV(xh, yl, zh, u2, v3);
			
		} else if (dir.contains("SOUTH")) {
			//Top Side
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zl, u3, v3);
			tess.addVertexWithUV(xl, yh, zh, u3, v2);
			tess.addVertexWithUV(xh, yh, zh, u2, v2);
			tess.addVertexWithUV(xh, yh, zl, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xh, yh, zl, u3, v3);
			tess.addVertexWithUV(xh, yl, zl, u3, v2);
			tess.addVertexWithUV(xl, yl, zl, u2, v2);
			tess.addVertexWithUV(xl, yh, zl, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xh, yh, zh, u3, v3);
			tess.addVertexWithUV(xh, yl, zh, u3, v2);
			tess.addVertexWithUV(xh, yl, zl, u2, v2);
			tess.addVertexWithUV(xh, yh, zl, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zh, u1, v1);
			tess.addVertexWithUV(xl, yl, zh, u1, v0);
			tess.addVertexWithUV(xh, yl, zh, u0, v0);
			tess.addVertexWithUV(xh, yh, zh, u0, v1);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zl, u3, v3);
			tess.addVertexWithUV(xl, yl, zl, u3, v2);
			tess.addVertexWithUV(xl, yl, zh, u2, v2);
			tess.addVertexWithUV(xl, yh, zh, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yl, zh, u3, v3);
			tess.addVertexWithUV(xl, yl, zl, u3, v2);
			tess.addVertexWithUV(xh, yl, zl, u2, v2);
			tess.addVertexWithUV(xh, yl, zh, u2, v3);
			
		} else if (dir.contains("WEST")) {
			//Top Side
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zl, u3, v3);
			tess.addVertexWithUV(xl, yh, zh, u3, v2);
			tess.addVertexWithUV(xh, yh, zh, u2, v2);
			tess.addVertexWithUV(xh, yh, zl, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xh, yh, zl, u3, v3);
			tess.addVertexWithUV(xh, yl, zl, u3, v2);
			tess.addVertexWithUV(xl, yl, zl, u2, v2);
			tess.addVertexWithUV(xl, yh, zl, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xh, yh, zh, u3, v3);
			tess.addVertexWithUV(xh, yl, zh, u3, v2);
			tess.addVertexWithUV(xh, yl, zl, u2, v2);
			tess.addVertexWithUV(xh, yh, zl, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zh, u3, v3);
			tess.addVertexWithUV(xl, yl, zh, u3, v2);
			tess.addVertexWithUV(xh, yl, zh, u2, v2);
			tess.addVertexWithUV(xh, yh, zh, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yh, zl, u1, v1);
			tess.addVertexWithUV(xl, yl, zl, u1, v0);
			tess.addVertexWithUV(xl, yl, zh, u0, v0);
			tess.addVertexWithUV(xl, yh, zh, u0, v1);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(xl, yl, zh, u3, v3);
			tess.addVertexWithUV(xl, yl, zl, u3, v2);
			tess.addVertexWithUV(xh, yl, zl, u2, v2);
			tess.addVertexWithUV(xh, yl, zh, u2, v3);
		}
	}
	
	public void renderItem(Tessellator tess, double xl, double yl, double zl, double xh, double yh, double zh, double u0, double u1, double u2, double u3, double v0, double v1, double v2, double v3) {
		final float FACE_XZ_NORMAL = 0.8944F;
		final float FACE_Y_NORMAL  = 0.4472F;
		
		//Top Side
		tess.setNormal(0.0F, 1.0F, 0.0F);
		tess.addVertexWithUV(xl, yh, zl, u3, v3);
		tess.addVertexWithUV(xl, yh, zh, u3, v2);
		tess.addVertexWithUV(xh, yh, zh, u2, v2);
		tess.addVertexWithUV(xh, yh, zl, u2, v3);
		tess.draw();
		
		//North Side
		tess.startDrawingQuads();
		tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
		tess.addVertexWithUV(xh, yh, zl, u3, v3);
		tess.addVertexWithUV(xh, yl, zl, u3, v2);
		tess.addVertexWithUV(xl, yl, zl, u2, v2);
		tess.addVertexWithUV(xl, yh, zl, u2, v3);
		tess.draw();
		
		//East Side
		tess.startDrawingQuads();
		tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tess.addVertexWithUV(xh, yh, zh, u3, v3);
		tess.addVertexWithUV(xh, yl, zh, u3, v2);
		tess.addVertexWithUV(xh, yl, zl, u2, v2);
		tess.addVertexWithUV(xh, yh, zl, u2, v3);
		tess.draw();
		
		//South Side
		tess.startDrawingQuads();
		tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
		tess.addVertexWithUV(xl, yh, zh, u3, v3);
		tess.addVertexWithUV(xl, yl, zh, u3, v2);
		tess.addVertexWithUV(xh, yl, zh, u2, v2);
		tess.addVertexWithUV(xh, yh, zh, u2, v3);
		tess.draw();

		//West Side
		tess.startDrawingQuads();
		tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tess.addVertexWithUV(xl, yh, zl, u1, v1);
		tess.addVertexWithUV(xl, yl, zl, u1, v0);
		tess.addVertexWithUV(xl, yl, zh, u0, v0);
		tess.addVertexWithUV(xl, yh, zh, u0, v1);
		tess.draw();

		//Bottom Side
		tess.startDrawingQuads();
		tess.setNormal(0.0F, -1.0F, 0.0F);
		tess.addVertexWithUV(xl, yl, zh, u1, v1);
		tess.addVertexWithUV(xl, yl, zl, u1, v0);
		tess.addVertexWithUV(xh, yl, zl, u0, v0);
		tess.addVertexWithUV(xh, yl, zh, u0, v1);
	}
}*/
