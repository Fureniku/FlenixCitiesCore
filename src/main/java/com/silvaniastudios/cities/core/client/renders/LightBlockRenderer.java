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

public class LightBlockRenderer implements ISimpleBlockRenderingHandler {
	
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
		
		final float FACE_XZ_NORMAL = 0.8944F;
		final float FACE_Y_NORMAL  = 0.4472F;
		
		tess.startDrawingQuads();

		if (meta == 0) {		
			double a = 0.96875; //base height
			//Top Side
			tess.setNormal(0.0F, 1.0F, 0.0F);
			tess.addVertexWithUV(0, 1, 0, u3, v3);
			tess.addVertexWithUV(0, 1, 1, u3, v2);
			tess.addVertexWithUV(1, 1, 1, u2, v2);
			tess.addVertexWithUV(1, 1, 0, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
			tess.addVertexWithUV(1, 1, 0, u3, v3);
			tess.addVertexWithUV(1, a, 0, u3, v2);
			tess.addVertexWithUV(0, a, 0, u2, v2);
			tess.addVertexWithUV(0, 1, 0, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
			tess.addVertexWithUV(1, 1, 1, u3, v3);
			tess.addVertexWithUV(1, a, 1, u3, v2);
			tess.addVertexWithUV(1, a, 0, u2, v2);
			tess.addVertexWithUV(1, 1, 0, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
			tess.addVertexWithUV(0, 1, 1, u3, v3);
			tess.addVertexWithUV(0, a, 1, u3, v2);
			tess.addVertexWithUV(1, a, 1, u2, v2);
			tess.addVertexWithUV(1, 1, 1, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
			tess.addVertexWithUV(0, 1, 0, u3, v3);
			tess.addVertexWithUV(0, a, 0, u3, v2);
			tess.addVertexWithUV(0, a, 1, u2, v2);
			tess.addVertexWithUV(0, 1, 1, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, -1.0F, 0.0F);
			tess.addVertexWithUV(0, a, 1, u1, v1);
			tess.addVertexWithUV(0, a, 0, u1, v0);
			tess.addVertexWithUV(1, a, 0, u0, v0);
			tess.addVertexWithUV(1, a, 1, u0, v1);
			
		} else if (meta == 1) {
			double a = 0.875; //base height
			//Top Side
			tess.setNormal(0.0F, 1.0F, 0.0F);
			tess.addVertexWithUV(0.25, 1, 0.25, u3, v3);
			tess.addVertexWithUV(0.25, 1, 0.75, u3, v2);
			tess.addVertexWithUV(0.75, 1, 0.75, u2, v2);
			tess.addVertexWithUV(0.75, 1, 0.25, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
			tess.addVertexWithUV(0.75, 1, 0.25, u3, v3);
			tess.addVertexWithUV(0.75, a, 0.25, u3, v2);
			tess.addVertexWithUV(0.25, a, 0.25, u2, v2);
			tess.addVertexWithUV(0.25, 1, 0.25, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
			tess.addVertexWithUV(0.75, 1, 0.75, u3, v3);
			tess.addVertexWithUV(0.75, a, 0.75, u3, v2);
			tess.addVertexWithUV(0.75, a, 0.25, u2, v2);
			tess.addVertexWithUV(0.75, 1, 0.25, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
			tess.addVertexWithUV(0.25, 1, 0.75, u3, v3);
			tess.addVertexWithUV(0.25, a, 0.75, u3, v2);
			tess.addVertexWithUV(0.75, a, 0.75, u2, v2);
			tess.addVertexWithUV(0.75, 1, 0.75, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
			tess.addVertexWithUV(0.25, 1, 0.25, u3, v3);
			tess.addVertexWithUV(0.25, a, 0.25, u3, v2);
			tess.addVertexWithUV(0.25, a, 0.75, u2, v2);
			tess.addVertexWithUV(0.25, 1, 0.75, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, -1.0F, 0.0F);
			tess.addVertexWithUV(0.25, a, 0.75, u1, v1);
			tess.addVertexWithUV(0.25, a, 0.25, u1, v0);
			tess.addVertexWithUV(0.75, a, 0.25, u0, v0);
			tess.addVertexWithUV(0.75, a, 0.75, u0, v1);
		
		} else if (meta == 2) {
			double a = 0.0; //base height
			//Top Side
			tess.setNormal(0.0F, 1.0F, 0.0F);
			tess.addVertexWithUV(0.25, 1, 0.25, u1, v1);
			tess.addVertexWithUV(0.25, 1, 0.75, u1, v0);
			tess.addVertexWithUV(0.75, 1, 0.75, u0, v0);
			tess.addVertexWithUV(0.75, 1, 0.25, u0, v1);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
			tess.addVertexWithUV(0.75, 1, 0.25, u3, v3);
			tess.addVertexWithUV(0.75, a, 0.25, u3, v2);
			tess.addVertexWithUV(0.25, a, 0.25, u2, v2);
			tess.addVertexWithUV(0.25, 1, 0.25, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
			tess.addVertexWithUV(0.75, 1, 0.75, u3, v3);
			tess.addVertexWithUV(0.75, a, 0.75, u3, v2);
			tess.addVertexWithUV(0.75, a, 0.25, u2, v2);
			tess.addVertexWithUV(0.75, 1, 0.25, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
			tess.addVertexWithUV(0.25, 1, 0.75, u3, v3);
			tess.addVertexWithUV(0.25, a, 0.75, u3, v2);
			tess.addVertexWithUV(0.75, a, 0.75, u2, v2);
			tess.addVertexWithUV(0.75, 1, 0.75, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
			tess.addVertexWithUV(0.25, 1, 0.25, u3, v3);
			tess.addVertexWithUV(0.25, a, 0.25, u3, v2);
			tess.addVertexWithUV(0.25, a, 0.75, u2, v2);
			tess.addVertexWithUV(0.25, 1, 0.75, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, -1.0F, 0.0F);
			tess.addVertexWithUV(0.25, a, 0.75, u1, v1);
			tess.addVertexWithUV(0.25, a, 0.25, u1, v0);
			tess.addVertexWithUV(0.75, a, 0.25, u0, v0);
			tess.addVertexWithUV(0.75, a, 0.75, u0, v1);
		
		} else if (meta == 3) {
			double a = 0.875; //base height
			//Top Side
			tess.setNormal(0.0F, 1.0F, 0.0F);
			tess.addVertexWithUV(0.375, 1, 0.375, u3, v3);
			tess.addVertexWithUV(0.375, 1, 0.625, u3, v2);
			tess.addVertexWithUV(0.625, 1, 0.625, u2, v2);
			tess.addVertexWithUV(0.625, 1, 0.375, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
			tess.addVertexWithUV(0.625, 1, 0.375, u3, v3);
			tess.addVertexWithUV(0.625, a, 0.375, u3, v2);
			tess.addVertexWithUV(0.375, a, 0.375, u2, v2);
			tess.addVertexWithUV(0.375, 1, 0.375, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
			tess.addVertexWithUV(0.625, 1, 0.625, u3, v3);
			tess.addVertexWithUV(0.625, a, 0.625, u3, v2);
			tess.addVertexWithUV(0.625, a, 0.375, u2, v2);
			tess.addVertexWithUV(0.625, 1, 0.375, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
			tess.addVertexWithUV(0.375, 1, 0.625, u3, v3);
			tess.addVertexWithUV(0.375, a, 0.625, u3, v2);
			tess.addVertexWithUV(0.625, a, 0.625, u2, v2);
			tess.addVertexWithUV(0.625, 1, 0.625, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
			tess.addVertexWithUV(0.375, 1, 0.375, u3, v3);
			tess.addVertexWithUV(0.375, a, 0.375, u3, v2);
			tess.addVertexWithUV(0.375, a, 0.625, u2, v2);
			tess.addVertexWithUV(0.375, 1, 0.625, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, -1.0F, 0.0F);
			tess.addVertexWithUV(0.375, a, 0.625, u1, v1);
			tess.addVertexWithUV(0.375, a, 0.375, u1, v0);
			tess.addVertexWithUV(0.625, a, 0.375, u0, v0);
			tess.addVertexWithUV(0.625, a, 0.625, u0, v1);
		
		} else if (meta == 4) {
			double a = 0.0; //base height
			//Top Side
			tess.setNormal(0.0F, 1.0F, 0.0F);
			tess.addVertexWithUV(0.375, 1, 0.375, u1, v1);
			tess.addVertexWithUV(0.375, 1, 0.625, u1, v0);
			tess.addVertexWithUV(0.625, 1, 0.625, u0, v0);
			tess.addVertexWithUV(0.625, 1, 0.375, u0, v1);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
			tess.addVertexWithUV(0.625, 1, 0.375, u3, v3);
			tess.addVertexWithUV(0.625, a, 0.375, u3, v2);
			tess.addVertexWithUV(0.375, a, 0.375, u2, v2);
			tess.addVertexWithUV(0.375, 1, 0.375, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
			tess.addVertexWithUV(0.625, 1, 0.625, u3, v3);
			tess.addVertexWithUV(0.625, a, 0.625, u3, v2);
			tess.addVertexWithUV(0.625, a, 0.375, u2, v2);
			tess.addVertexWithUV(0.625, 1, 0.375, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
			tess.addVertexWithUV(0.375, 1, 0.625, u3, v3);
			tess.addVertexWithUV(0.375, a, 0.625, u3, v2);
			tess.addVertexWithUV(0.625, a, 0.625, u2, v2);
			tess.addVertexWithUV(0.625, 1, 0.625, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
			tess.addVertexWithUV(0.375, 1, 0.375, u3, v3);
			tess.addVertexWithUV(0.375, a, 0.375, u3, v2);
			tess.addVertexWithUV(0.375, a, 0.625, u2, v2);
			tess.addVertexWithUV(0.375, 1, 0.625, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setNormal(0.0F, -1.0F, 0.0F);
			tess.addVertexWithUV(0.375, a, 0.625, u1, v1);
			tess.addVertexWithUV(0.375, a, 0.375, u1, v0);
			tess.addVertexWithUV(0.625, a, 0.375, u0, v0);
			tess.addVertexWithUV(0.625, a, 0.625, u0, v1);
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
		IIcon side = block.getIcon(2, meta);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		double u2 = (double) side.getMinU();
		double u3 = (double) side.getMaxU();
		
		double v2 = (double) side.getMinV();
		double v3 = (double) side.getMaxV();
		
		int col = 255;
		
		if (meta == 0) {
			double a = 0.96875;
			//Top Side
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u3, v3);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u3, v2);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u2, v2);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u3, v3);
			tess.addVertexWithUV(x + 1, y + a, z + 0, u3, v2);
			tess.addVertexWithUV(x + 0, y + a, z + 0, u2, v2);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u3, v3);
			tess.addVertexWithUV(x + 1, y + a, z + 1, u3, v2);
			tess.addVertexWithUV(x + 1, y + a, z + 0, u2, v2);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u3, v3);
			tess.addVertexWithUV(x + 0, y + a, z + 1, u3, v2);
			tess.addVertexWithUV(x + 1, y + a, z + 1, u2, v2);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u3, v3);
			tess.addVertexWithUV(x + 0, y + a, z + 0, u3, v2);
			tess.addVertexWithUV(x + 0, y + a, z + 1, u2, v2);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0, y + a, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + a, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + a, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + a, z + 1, u0, v1);
			
		} else if (meta == 1) {
			double a = 0.875;
			//Top Side
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.25, u3, v3);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.75, u3, v2);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.75, u2, v2);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.25, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.25, u3, v3);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.25, u3, v2);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.25, u2, v2);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.25, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.75, u3, v3);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.75, u3, v2);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.25, u2, v2);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.25, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.75, u3, v3);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.75, u3, v2);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.75, u2, v2);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.75, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.25, u3, v3);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.25, u3, v2);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.75, u2, v2);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.75, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.75, u1, v1);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.25, u1, v0);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.25, u0, v0);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.75, u0, v1);
		} else if (meta == 2) {
			double a = 0.0;
			//Top Side
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.25, u3, v3);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.75, u3, v2);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.75, u2, v2);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.25, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.25, u3, v3);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.25, u3, v2);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.25, u2, v2);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.25, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.75, u3, v3);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.75, u3, v2);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.25, u2, v2);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.25, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.75, u3, v3);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.75, u3, v2);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.75, u2, v2);
			tess.addVertexWithUV(x + 0.75, y + 1, z + 0.75, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.25, u3, v3);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.25, u3, v2);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.75, u2, v2);
			tess.addVertexWithUV(x + 0.25, y + 1, z + 0.75, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.75, u1, v1);
			tess.addVertexWithUV(x + 0.25, y + a, z + 0.25, u1, v0);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.25, u0, v0);
			tess.addVertexWithUV(x + 0.75, y + a, z + 0.75, u0, v1);
		} else if (meta == 3) {
			double a = 0.875;
			//Top Side
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.375, u3, v3);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.625, u3, v2);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.625, u2, v2);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.375, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.375, u3, v3);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.375, u3, v2);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.375, u2, v2);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.375, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.625, u3, v3);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.625, u3, v2);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.375, u2, v2);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.375, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.625, u3, v3);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.625, u3, v2);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.625, u2, v2);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.625, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.375, u3, v3);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.375, u3, v2);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.625, u2, v2);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.625, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.625, u1, v1);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.375, u1, v0);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.375, u0, v0);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.625, u0, v1);
		} else if (meta == 4) {
			double a = 0.0;
			//Top Side
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.375, u3, v3);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.625, u3, v2);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.625, u2, v2);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.375, u2, v3);
			tess.draw();
			
			//North Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.375, u3, v3);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.375, u3, v2);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.375, u2, v2);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.375, u2, v3);
			tess.draw();
			
			//East Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.625, u3, v3);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.625, u3, v2);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.375, u2, v2);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.375, u2, v3);
			tess.draw();
			
			//South Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.625, u3, v3);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.625, u3, v2);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.625, u2, v2);
			tess.addVertexWithUV(x + 0.625, y + 1, z + 0.625, u2, v3);
			tess.draw();
	
			//West Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.375, u3, v3);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.375, u3, v2);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.625, u2, v2);
			tess.addVertexWithUV(x + 0.375, y + 1, z + 0.625, u2, v3);
			tess.draw();
	
			//Bottom Side
			tess.startDrawingQuads();
			tess.setColorOpaque(col, col, col);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.625, u1, v1);
			tess.addVertexWithUV(x + 0.375, y + a, z + 0.375, u1, v0);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.375, u0, v0);
			tess.addVertexWithUV(x + 0.625, y + a, z + 0.625, u0, v1);
		}

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.lightBlockRenderID;
	}
}*/
