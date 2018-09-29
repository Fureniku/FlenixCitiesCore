/*package com.silvaniastudios.cities.core.client.renders;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.cities.core.blocks.BlockWalkwayStairs;
import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class WalkwayStairsTraditionalRenderer implements ISimpleBlockRenderingHandler {
	
	Tessellator tess;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		tess = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		IIcon icon = block.getIcon(7, 0);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		final float FACE_XZ_NORMAL = 0.8944F;
		final float FACE_Y_NORMAL  = 0.4472F;
		
		double a = 0.5;

		//Top
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 1.0F, 0.0F);
		tess.addVertexWithUV(0, 2.0, 0.0625, u1, v1);
		tess.addVertexWithUV(1, 1.0, 0.0625, u1, v0);
		tess.addVertexWithUV(1, 1.0, -0.0625, u0, v0);
		tess.addVertexWithUV(0, 2.0, -0.0625, u0, v1);
		tess.draw();
		
		//Front
		tess.startDrawingQuads();
		tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
		tess.addVertexWithUV(-0.0001, 1.875, 0.0625, u1, v1);
		tess.addVertexWithUV(-0.0001, 2.0,   0.0625, u1, v0);
		tess.addVertexWithUV(-0.0001, 2.0,  -0.0625, u0, v0);
		tess.addVertexWithUV(-0.0001, 1.875,-0.0625, u0, v1);
		tess.draw();
		
		//Back
		tess.startDrawingQuads();
		tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tess.addVertexWithUV(1, 0.875,-0.0625, u1, v1);
		tess.addVertexWithUV(1, 1.0,  -0.0625, u1, v0);
		tess.addVertexWithUV(1, 1.0,   0.0625, u0, v0);
		tess.addVertexWithUV(1, 0.875, 0.0625, u0, v1);
		tess.draw();
		
		//Left
		tess.startDrawingQuads();
		tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
		tess.addVertexWithUV(0, 0.875,  0.0625, u1, v1);
		tess.addVertexWithUV(1, -0.125, 0.0625, u1, v0);
		tess.addVertexWithUV(1, 0.0,    0.0625, u0, v0);
		tess.addVertexWithUV(0, 1.0,    0.0625, u0, v1);
		tess.draw();

		//Right
		tess.startDrawingQuads();
		tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tess.addVertexWithUV(1, 0.875, -0.0625, u1, v1);
		tess.addVertexWithUV(0, 1.875, -0.0625, u1, v0);
		tess.addVertexWithUV(0, 2.0,   -0.0625, u0, v0);
		tess.addVertexWithUV(1, 1.0,   -0.0625, u0, v1);
		tess.draw();

		//Bottom Side
		tess.startDrawingQuads();
		tess.setNormal(0.0F, -1.0F, 0.0F);
		tess.addVertexWithUV(1, 0.875, 0.0625, u1, v1);
		tess.addVertexWithUV(0, 1.875, 0.0625, u1, v0);
		tess.addVertexWithUV(0, 1.875, -0.0625, u0, v0);
		tess.addVertexWithUV(1, 0.875, -0.0625, u0, v1);
		tess.draw();
		
		//SUPPORT RAIL
		//Top
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 1.0F, 0.0F);
		tess.addVertexWithUV(0, 1.0, 0.0625, u1, v1);
		tess.addVertexWithUV(1, 0.0, 0.0625, u1, v0);
		tess.addVertexWithUV(1, 0.0, 0,      u0, v0);
		tess.addVertexWithUV(0, 1.0, 0,      u0, v1);
		tess.draw();
		
		//Front
		tess.startDrawingQuads();
		tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
		tess.addVertexWithUV(-0.0001, 1.0,   0.0625, u1, v1);
		tess.addVertexWithUV(-0.0001, 0.875, 0.0625, u1, v0);
		tess.addVertexWithUV(-0.0001, 0.875, 0,      u0, v0);
		tess.addVertexWithUV(-0.0001, 1.0,   0,      u0, v1);
		tess.draw();
		
		//Back
		tess.startDrawingQuads();
		tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tess.addVertexWithUV(1, 1.0,   0,      u1, v1);
		tess.addVertexWithUV(1, 0.875, 0,      u1, v0);
		tess.addVertexWithUV(1, 0.875, 0.0625, u0, v0);
		tess.addVertexWithUV(1, 1.0,   0.0625, u0, v1);
		tess.draw();
		
		//Left
		tess.startDrawingQuads();
		tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
		tess.addVertexWithUV(0, 0.875,  0.0625, u1, v1);
		tess.addVertexWithUV(1, -0.125, 0.0625, u1, v0);
		tess.addVertexWithUV(1, 0.0,    0.0625, u0, v0);
		tess.addVertexWithUV(0, 1.0,    0.0625, u0, v1);
		tess.draw();

		//Right
		tess.startDrawingQuads();
		tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tess.addVertexWithUV(1, -0.125, -0.0001, u1, v1);
		tess.addVertexWithUV(0, 0.875,  -0.0001, u1, v0);
		tess.addVertexWithUV(0, 1.0,    -0.0001, u0, v0);
		tess.addVertexWithUV(1, 0.0,    -0.0001, u0, v1);
		tess.draw();

		//Bottom Side
		tess.startDrawingQuads();
		tess.setNormal(0.0F, -1.0F, 0.0F);
		tess.addVertexWithUV(1, -0.125, 0.0625, u1, v1);
		tess.addVertexWithUV(0, 0.875,  0.0625, u1, v0);
		tess.addVertexWithUV(0, 0.875,  0,      u0, v0);
		tess.addVertexWithUV(1, -0.125, 0,      u0, v1);
		tess.draw();
		
		
		//Top
		tess.startDrawingQuads();
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tess.addVertexWithUV(0, 2.0, 1.0625, u1, v1);
		tess.addVertexWithUV(1, 1.0, 1.0625, u1, v0);
		tess.addVertexWithUV(1, 1.0, 0.9375, u0, v0);
		tess.addVertexWithUV(0, 2.0, 0.9375, u0, v1);
		tess.draw();
		
		//Front
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
		tess.addVertexWithUV(1.0001, 1.0,   1.0625, u1, v1);
		tess.addVertexWithUV(1.0001, 0.875, 1.0625, u1, v0);
		tess.addVertexWithUV(1.0001, 0.875, 0.9375, u0, v0);
		tess.addVertexWithUV(1.0001, 1.0,   0.9375, u0, v1);
		tess.draw();
		
		//Back
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
		tess.addVertexWithUV(0, 2.0,   0.9375, u1, v1);
		tess.addVertexWithUV(0, 1.875, 0.9375, u1, v0);
		tess.addVertexWithUV(0, 1.875, 1.0625, u0, v0);
		tess.addVertexWithUV(0, 2.0,   1.0625, u0, v1);
		tess.draw();
		
		//Left
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
		tess.addVertexWithUV(0, 1.875, 1.0625, u1, v1);
		tess.addVertexWithUV(1, 0.875, 1.0625, u1, v0);
		tess.addVertexWithUV(1, 1.0,   1.0625, u0, v0);
		tess.addVertexWithUV(0, 2.0,   1.0625, u0, v1);
		tess.draw();
		
		//Right
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
		tess.addVertexWithUV(1, 0.875, 0.9375, u1, v1);
		tess.addVertexWithUV(0, 1.875, 0.9375, u1, v0);
		tess.addVertexWithUV(0, 2.0,   0.9375, u0, v0);
		tess.addVertexWithUV(1, 1.0,   0.9375, u0, v1);
		tess.draw();
		
		//Bottom
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
		tess.addVertexWithUV(1, 0.875, 1.0625, u1, v1);
		tess.addVertexWithUV(0, 1.875, 1.0625, u1, v0);
		tess.addVertexWithUV(0, 1.875, 0.9375, u0, v0);
		tess.addVertexWithUV(1, 0.875, 0.9375, u0, v1);
		tess.draw();
		
		//Support Rail
		//Top
		tess.startDrawingQuads();
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tess.addVertexWithUV(0, 1.0, 1,      u1, v1);
		tess.addVertexWithUV(1, 0.0, 1,      u1, v0);
		tess.addVertexWithUV(1, 0.0, 0.9375, u0, v0);
		tess.addVertexWithUV(0, 1.0, 0.9375, u0, v1);
		tess.draw();
		
		//Front
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
		tess.addVertexWithUV(-0.0001, 0.875, 1,      u1, v1);
		tess.addVertexWithUV(-0.0001, 1.0,   1,      u1, v0);
		tess.addVertexWithUV(-0.0001, 1.0,   0.9375, u0, v0);
		tess.addVertexWithUV(-0.0001, 0.875, 0.9375, u0, v1);
		tess.draw();
		
		//Back
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
		tess.addVertexWithUV(1, 1.0,   0.9375, u1, v1);
		tess.addVertexWithUV(1, 0.875, 0.9375, u1, v0);
		tess.addVertexWithUV(1, 0.875, 1,      u0, v0);
		tess.addVertexWithUV(1, 1.0,   1,      u0, v1);
		tess.draw();
		
		//Left
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
		tess.addVertexWithUV(0, 0.875,  1.0001, u1, v1);
		tess.addVertexWithUV(1, -0.125, 1.0001, u1, v0);
		tess.addVertexWithUV(1, 0.0,    1.0001, u0, v0);
		tess.addVertexWithUV(0, 1.0,    1.0001, u0, v1);
		tess.draw();
		
		//Right
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
		tess.addVertexWithUV(1, -0.125, 0.9375, u1, v1);
		tess.addVertexWithUV(0, 0.875,  0.9375, u1, v0);
		tess.addVertexWithUV(0, 1.0,    0.9375, u0, v0);
		tess.addVertexWithUV(1, 0.0,    0.9375, u0, v1);
		tess.draw();
		
		//Bottom
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
		tess.addVertexWithUV(1, 0.,    1,      u1, v1);
		tess.addVertexWithUV(0, 0.875, 1,      u1, v0);
		tess.addVertexWithUV(0, 0.875, 0.9375, u0, v0);
		tess.addVertexWithUV(1, 0.0,   0.9375, u0, v1);
		tess.draw();
		
		renderBlock(0.75D+a, 0.0D+a,  0.0D+a,  1.00D+a, 0.0625D+a, 1.0D+a, false, renderer, block, 0, 0, 0, 0);
		renderBlock(0.50D+a, 0.25D+a, 0.0D+a, 0.75D+a, 0.3125D+a, 1.0D+a,  false, renderer, block, 0, 0, 0, 0);
		renderBlock(0.25D+a, 0.50D+a, 0.0D+a, 0.50D+a, 0.5625D+a, 1.0D+a,  false, renderer, block, 0, 0, 0, 0);
		renderBlock(0.0D+a,  0.75D+a, 0.0D+a, 0.25D+a, 0.8125D+a, 1.0D+a,  false, renderer, block, 0, 0, 0, 0);
		
		//Handrails (2x)
		
		renderOversizeBlock(0.09375D+a, 0.75D+a, -0.03125D+a, 0.15625D+a, 1.75D+a, 0.03125D+a, false, renderer, block, 0, 0, 0, 0);
		renderOversizeBlock(0.34375D+a, 0.50D+a, -0.03125D+a, 0.40625D+a, 1.50D+a, 0.03125D+a, false, renderer, block, 0, 0, 0, 0);
		renderOversizeBlock(0.59375D+a, 0.25D+a, -0.03125D+a, 0.65625D+a, 1.25D+a, 0.03125D+a, false, renderer, block, 0, 0, 0, 0);
		renderOversizeBlock(0.84375D+a, 0.0D+a,  -0.03125D+a, 0.90625D+a, 1.0D+a,  0.03125D+a, false, renderer, block, 0, 0, 0, 0);

		renderOversizeBlock(0.09375D+a, 0.75D+a, 0.96875D+a, 0.15625D+a, 1.75D+a, 1.03125D+a, false, renderer, block, 0, 0, 0, 0);
		renderOversizeBlock(0.34375D+a, 0.50D+a, 0.96875D+a, 0.40625D+a, 1.50D+a, 1.03125D+a, false, renderer, block, 0, 0, 0, 0);
		renderOversizeBlock(0.59375D+a, 0.25D+a, 0.96875D+a, 0.65625D+a, 1.25D+a, 1.03125D+a, false, renderer, block, 0, 0, 0, 0);
		renderOversizeBlock(0.84375D+a, 0.0D+a,  0.96875D+a, 0.90625D+a, 1.0D+a,  1.03125D+a, false, renderer, block, 0, 0, 0, 0);
		
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int col = 255;
		float f = 0.8F;
		int c = block.colorMultiplier(world, x, y, z);
		float f1 = (float)(c >> 16 & 255) / 255.0F;
		float f2 = (float)(c >> 8 & 255) / 255.0F;
		float f3 = (float)(c & 255) / 255.0F;
		
		tess = Tessellator.instance;
		tess.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
		tess.setColorOpaque_F(f * f1, f * f2, f * f3);
		
		IIcon icon = block.getIcon(7, 0);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		//X/Z 0=NW, 1=SE

		
		
		int meta = world.getBlockMetadata(x, y, z);
		
		boolean connectNorth = checkConnections(world, x, y, z-1);
		boolean connectEast =  checkConnections(world, x+1, y, z);
		boolean connectSouth = checkConnections(world, x, y, z+1);
		boolean connectWest =  checkConnections(world, x-1, y, z);
		
		boolean walkwayBelow = world.getBlock(x, y-1, z) instanceof BlockWalkwayStairs;
		boolean walkwayAbove = world.getBlock(x, y+1, z) instanceof BlockWalkwayStairs;
		
		if (!walkwayBelow) {
			//Main Stairs
			if (meta == 0) { //South-facing
				renderBlock(0.0D, 0.0D,  0.0D,  1.0D, 0.0625D, 0.25D, true, renderer, block, x, y, z, meta);
				renderBlock(0.0D, 0.25D, 0.25D, 1.0D, 0.3125D, 0.50D, true, renderer, block, x, y, z, meta);
				renderBlock(0.0D, 0.50D, 0.50D, 1.0D, 0.5625D, 0.75D, true, renderer, block, x, y, z, meta);
				renderBlock(0.0D, 0.75D, 0.75D, 1.0D, 0.8125D, 1.0D,  true, renderer, block, x, y, z, meta);
			} else if (meta == 1) { //West-facing
				renderBlock(0.75D, 0.0D, 0.0D,  1.00D, 0.0625D, 1.0D, true, renderer, block, x, y, z, meta);
				renderBlock(0.50D, 0.25D, 0.0D, 0.75D, 0.3125D, 1.0D, true, renderer, block, x, y, z, meta);
				renderBlock(0.25D, 0.50D, 0.0D, 0.50D, 0.5625D, 1.0D, true, renderer, block, x, y, z, meta);
				renderBlock(0.0D,  0.75D, 0.0D, 0.25D, 0.8125D, 1.0D, true, renderer, block, x, y, z, meta);
			} else if (meta == 2) { //North-facing
				renderBlock(0.0D, 0.0D, 0.75D,  1.0D, 0.0625D, 1.0D, true, renderer, block, x, y, z, meta);
				renderBlock(0.0D, 0.25D, 0.50D, 1.0D, 0.3125D, 0.75D, true, renderer, block, x, y, z, meta);
				renderBlock(0.0D, 0.50D, 0.25D, 1.0D, 0.5625D, 0.50D, true, renderer, block, x, y, z, meta);
				renderBlock(0.0D, 0.75D, 0.0D,  1.0D, 0.8125D, 0.25D, true, renderer, block, x, y, z, meta);
			} else if (meta == 3) { //East-facing
				renderBlock(0.0D, 0.0D, 0.0D, 0.25D, 0.0625D, 1.0D, true, renderer, block, x, y, z, meta);
				renderBlock(0.25D, 0.25D, 0.0D, 0.50D, 0.3125D, 1.0D, true, renderer, block, x, y, z, meta);
				renderBlock(0.50D, 0.50D, 0.0D, 0.75D, 0.5625D, 1.0D, true, renderer, block, x, y, z, meta);
				renderBlock(0.75D, 0.75D, 0.0D, 1.00D, 0.8125D, 1.0D, true, renderer, block, x, y, z, meta);
			}
		}		
		
		if (meta == 0) { //SOUTH
			if (!connectWest) {
				if (!walkwayAbove) {
					tess.draw();
					
					//Right Handrail
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x - 0.0625, y + 1.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x - 0.0625, y + 2.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 2.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 0, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0.0625, y + 1.9375, z + 1, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + 2.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x - 0.0625, y + 2.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x - 0.0625, y + 1.9375, z + 1, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x - 0.0625, y + 0.9375, z + 0, u1, v1);
					tess.addVertexWithUV(x - 0.0625, y + 1.0625, z + 0, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 0, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375, z + 0, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0625, y + 0.9375, z + 0, u1, v1);
					tess.addVertexWithUV(x - 0.0625, y + 1.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x - 0.0625, y + 2.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x - 0.0625, y + 1.0625, z + 0, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 0.0625, y + 1.9375, z + 1, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375, z + 0, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 0, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 2.0625, z + 1, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + 1.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x - 0.0625, y + 1.9375, z + 1, u0, v0);
					tess.addVertexWithUV(x - 0.0625, y + 0.9375, z + 0, u0, v1);
					tess.draw();
					
					//Right Support Bar
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x - 0,      y + 0.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x - 0,      y + 1.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 0.0625, z + 0, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375, z + 1.0001, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 1.0001, u1, v0);
					tess.addVertexWithUV(x - 0,      y + 1.0625, z + 1.0001, u0, v0);
					tess.addVertexWithUV(x - 0,      y + 0.9375, z + 1.0001, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x - 0,      y + -0.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x - 0,      y + 0.0625, z + 0, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 0.0625, z + 0, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + -0.0625, z + 0, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0001, y + -0.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x - 0.0001, y + 0.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x - 0.0001, y + 1.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x - 0.0001, y + 0.0625, z + 0, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375, z + 1, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + -0.0625, z + 0, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 0.0625, z + 0, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 1, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 0.0625, y + -0.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x - 0,      y + 0.9375, z + 1, u0, v0);
					tess.addVertexWithUV(x - 0,      y + -0.0625, z + 0, u0, v1);
					tess.draw();
					
					tess.startDrawingQuads();
				}
				renderOversizeBlock(-0.03125D, 0.0D,  0.09375D, 0.03125D, 1.125D, 0.15625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(-0.03125D, 0.25D, 0.34375D, 0.03125D, 1.375D, 0.40625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(-0.03125D, 0.50D, 0.59375D, 0.03125D, 1.625D, 0.65625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(-0.03125D, 0.75D, 0.84375D, 0.03125D, 1.875D, 0.90625D, true, renderer, block, x, y, z, meta);
			}
			if (!connectEast) {
				if (!walkwayAbove) {
					//Left Handrail
					//Top
					tess.draw();
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + 2.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 1.0625, y + 2.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 1.0625, y + 1.0625, z + 0, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1.0625, y + 1.9375, z + 1, u1, v1);
					tess.addVertexWithUV(x + 1.0625, y + 2.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 2.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 1.9375, z + 1, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375, z - 0.0001, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625, z - 0.0001, u1, v0);
					tess.addVertexWithUV(x + 1.0625, y + 1.0625, z - 0.0001, u0, v0);
					tess.addVertexWithUV(x + 1.0625, y + 0.9375, z - 0.0001, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + 1.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 2.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625, z + 0, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1.0625, y + 1.9375, z + 1, u1, v1);
					tess.addVertexWithUV(x + 1.0625, y + 0.9375, z + 0, u1, v0);
					tess.addVertexWithUV(x + 1.0625, y + 1.0625, z + 0, u0, v0);
					tess.addVertexWithUV(x + 1.0625, y + 2.0625, z + 1, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1.0625, y + 0.9375, z + 0, u1, v1);
					tess.addVertexWithUV(x + 1.0625, y + 1.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 1.9375, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375, z + 0, u0, v1);
					tess.draw();
					
					//Left Support rail
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0.9375, y + 0.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 1,      y + 1.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 1,      y + 0.0625, z + 0, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1,      y + 0.9375, z + 1, u1, v1);
					tess.addVertexWithUV(x + 1,      y + 1.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375, z + 1, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0.9375, y + -0.0625, z - 0.0001, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + 0.0625,  z - 0.0001, u1, v0);
					tess.addVertexWithUV(x + 1,      y + 0.0625,  z - 0.0001, u0, v0);
					tess.addVertexWithUV(x + 1,      y + -0.0625, z - 0.0001, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 0.9375, y + -0.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375,  z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625,  z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 0.0625,  z + 0, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1.0001, y + 0.9375,  z + 1, u1, v1);
					tess.addVertexWithUV(x + 1.0001, y + -0.0625, z + 0, u1, v0);
					tess.addVertexWithUV(x + 1.0001, y + 0.0625,  z + 0, u0, v0);
					tess.addVertexWithUV(x + 1.0001, y + 1.0625,  z + 1, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1,      y + -0.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x + 1,      y + 0.9375,  z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375,  z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + -0.0625, z + 0, u0, v1);
					tess.draw();
					tess.startDrawingQuads();
				}
				renderOversizeBlock(0.96875D, 0.0D,  0.09375D, 1.03125D, 1.125D, 0.15625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.96875D, 0.25D, 0.34375D, 1.03125D, 1.375D, 0.40625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.96875D, 0.50D, 0.59375D, 1.03125D, 1.625D, 0.65625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.96875D, 0.75D, 0.84375D, 1.03125D, 1.875D, 0.90625D, true, renderer, block, x, y, z, meta);
			}
		}
	
		if (meta == 1) { //WEST
			if (!connectNorth) {
				if (!walkwayAbove) {
					//Handrail tess
					//Handrail tess
					tess.draw();
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0, y + 2.0625, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z - 0.0625, u0, v0);
					tess.addVertexWithUV(x + 0, y + 2.0625, z - 0.0625, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0001, y + 1.9375, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x - 0.0001, y + 2.0625, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x - 0.0001, y + 2.0625, z - 0.0625, u0, v0);
					tess.addVertexWithUV(x - 0.0001, y + 1.9375, z - 0.0625, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1, y + 0.9375, z - 0.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 1.0625, z - 0.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.0625, u0, v0);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.0625, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0, y + 1.9375, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.0625, u0, v0);
					tess.addVertexWithUV(x + 0, y + 2.0625, z + 0.0625, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1, y + 0.9375, z - 0.0625, u1, v1);
					tess.addVertexWithUV(x + 0, y + 1.9375, z - 0.0625, u1, v0);
					tess.addVertexWithUV(x + 0, y + 2.0625, z - 0.0625, u0, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z - 0.0625, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 0, y + 1.9375, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 0, y + 1.9375, z - 0.0625, u0, v0);
					tess.addVertexWithUV(x + 1, y + 0.9375, z - 0.0625, u0, v1);
					tess.draw();
					
					//Support Rail
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 0.0625, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 0.0625, z + 0,      u0, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 0,      u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0001, y + 1.0625, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x - 0.0001, y + 0.9375, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x - 0.0001, y + 0.9375, z + 0,      u0, v0);
					tess.addVertexWithUV(x - 0.0001, y + 1.0625, z + 0,      u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0,      u1, v1);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0,      u1, v0);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.0625, u0, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.0625, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0, y + 0.9375,  z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + -0.0625, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 0.0625,  z + 0.0625, u0, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625,  z + 0.0625, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1, y + -0.0625, z - 0.0001, u1, v1);
					tess.addVertexWithUV(x + 0, y + 0.9375,  z - 0.0001, u1, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625,  z - 0.0001, u0, v0);
					tess.addVertexWithUV(x + 1, y + 0.0625,  z - 0.0001, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1, y + -0.0625, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 0, y + 0.9375,  z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 0, y + 0.9375,  z + 0,      u0, v0);
					tess.addVertexWithUV(x + 1, y + -0.0625, z + 0,      u0, v1);
					tess.draw();				
					
					tess.startDrawingQuads();
				}
				renderOversizeBlock(0.09375D, 0.75D, -0.03125D, 0.15625D, 1.875D, 0.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.34375D, 0.50D, -0.03125D, 0.40625D, 1.625D, 0.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.59375D, 0.25D, -0.03125D, 0.65625D, 1.375D, 0.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.84375D, 0.0D,  -0.03125D, 0.90625D, 1.125D, 0.03125D, true, renderer, block, x, y, z, meta);
			}
			
			if (!connectSouth) {
				if (!walkwayAbove) {
					//Handrail tess
					tess.draw();					
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0, y + 2.0625, z + 1.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 1.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 0, y + 2.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1.0001, y + 1.0625, z + 1.0625, u1, v1);
					tess.addVertexWithUV(x + 1.0001, y + 0.9375, z + 1.0625, u1, v0);
					tess.addVertexWithUV(x + 1.0001, y + 0.9375, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 1.0001, y + 1.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 0, y + 2.0625, z + 0.9375, u1, v1);
					tess.addVertexWithUV(x + 0, y + 1.9375, z + 0.9375, u1, v0);
					tess.addVertexWithUV(x + 0, y + 1.9375, z + 1.0625, u0, v0);
					tess.addVertexWithUV(x + 0, y + 2.0625, z + 1.0625, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0, y + 1.9375, z + 1.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 1.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 1.0625, u0, v0);
					tess.addVertexWithUV(x + 0, y + 2.0625, z + 1.0625, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.9375, u1, v1);
					tess.addVertexWithUV(x + 0, y + 1.9375, z + 0.9375, u1, v0);
					tess.addVertexWithUV(x + 0, y + 2.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 1.0625, u1, v1);
					tess.addVertexWithUV(x + 0, y + 1.9375, z + 1.0625, u1, v0);
					tess.addVertexWithUV(x + 0, y + 1.9375, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.9375, u0, v1);
					tess.draw();
					
					//Support Rail
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 1,      u1, v1);
					tess.addVertexWithUV(x + 1, y + 0.0625, z + 1,      u1, v0);
					tess.addVertexWithUV(x + 1, y + 0.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0001, y + 0.9375, z + 1,      u1, v1);
					tess.addVertexWithUV(x - 0.0001, y + 1.0625, z + 1,      u1, v0);
					tess.addVertexWithUV(x - 0.0001, y + 1.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x - 0.0001, y + 0.9375, z + 0.9375, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.9375, u1, v1);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.9375, u1, v0);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 1,      u0, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 1,      u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0, y + 0.9375, z + 1.0001, u1, v1);
					tess.addVertexWithUV(x + 1, y - 0.0625, z + 1.0001, u1, v0);
					tess.addVertexWithUV(x + 1, y + 0.0625, z + 1.0001, u0, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 1.0001, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1, y - 0.0625, z + 0.9375, u1, v1);
					tess.addVertexWithUV(x + 0, y + 0.9375, z + 0.9375, u1, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 1, y + 0.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1, y - 0.0625, z + 1,      u1, v1);
					tess.addVertexWithUV(x + 0, y + 0.9375, z + 1,      u1, v0);
					tess.addVertexWithUV(x + 0, y + 0.9375, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 1, y - 0.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					tess.startDrawingQuads();
				}
				renderOversizeBlock(0.09375D, 0.75D, 0.96875D, 0.15625D, 1.875D, 1.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.34375D, 0.50D, 0.96875D, 0.40625D, 1.625D, 1.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.59375D, 0.25D, 0.96875D, 0.65625D, 1.375D, 1.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.84375D, 0.0D,  0.96875D, 0.90625D, 1.125D, 1.03125D, true, renderer, block, x, y, z, meta);
			}
		}
		
		if (meta == 2) { //NORTH
			if (!connectWest) {
				if (!walkwayAbove) {
					tess.draw();
					//Right Handrail
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x - 0.0625, y + 2.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x - 0.0625, y + 1.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 2.0625, z + 0, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0.0625, y + 2.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + 1.9375, z + 0, u1, v0);
					tess.addVertexWithUV(x - 0.0625, y + 1.9375, z + 0, u0, v0);
					tess.addVertexWithUV(x - 0.0625, y + 2.0625, z + 0, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x - 0.0625, y + 1.0625, z + 1, u1, v1);
					tess.addVertexWithUV(x - 0.0625, y + 0.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 1, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0625, y + 1.9375, z + 0, u1, v1);
					tess.addVertexWithUV(x - 0.0625, y + 0.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x - 0.0625, y + 1.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x - 0.0625, y + 2.0625, z + 0, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375, z + 1, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + 1.9375, z + 0, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 2.0625, z + 0, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 1, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 0.0625, y + 1.9375, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x - 0.0625, y + 0.9375, z + 1, u0, v0);
					tess.addVertexWithUV(x - 0.0625, y + 1.9375, z + 0, u0, v1);
					tess.draw();
					
					//Right Support Bar
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x - 0,      y + 1.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x - 0,      y + 0.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 0.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 0, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625, z + 0.0001, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375, z + 0.0001, u1, v0);
					tess.addVertexWithUV(x - 0,      y + 0.9375, z + 0.0001, u0, v0);
					tess.addVertexWithUV(x - 0,      y + 1.0625, z + 0.0001, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x - 0,      y + 0.0625,  z + 1, u1, v1);
					tess.addVertexWithUV(x - 0,      y + -0.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + -0.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 0.0625,  z + 1, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0001, y + 0.9375,  z + 0, u1, v1);
					tess.addVertexWithUV(x - 0.0001, y + -0.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x - 0.0001, y + 0.0625,  z + 1, u0, v0);
					tess.addVertexWithUV(x - 0.0001, y + 1.0625,  z + 0, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 0.0625, y + -0.0625, z + 1, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375,  z + 0, u1, v0);
					tess.addVertexWithUV(x + 0.0625, y + 1.0625,  z + 0, u0, v0);
					tess.addVertexWithUV(x + 0.0625, y + 0.0625,  z + 1, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 0.0625, y + 0.9375,  z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.0625, y + -0.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x - 0,      y + -0.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x - 0,      y + 0.9375,  z + 0, u0, v1);
					tess.draw();
					
					tess.startDrawingQuads();
				}
				renderOversizeBlock(-0.03125D, 0.75D, 0.09375D, 0.03125D, 1.875D, 0.15625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(-0.03125D, 0.50D, 0.34375D, 0.03125D, 1.625D, 0.40625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(-0.03125D, 0.25D, 0.59375D, 0.03125D, 1.375D, 0.65625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(-0.03125D, 0.0D,  0.84375D, 0.03125D, 1.125D,  0.90625D, true, renderer, block, x, y, z, meta);
			}
			if (!connectEast) {
				if (!walkwayAbove) {
					//Left Handrail
					//Top
					tess.draw();
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0.9375, y + 2.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 1.0625, y + 1.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 1.0625, y + 2.0625, z + 0, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1.0625, y + 2.0625, z + 0.0001, u1, v1);
					tess.addVertexWithUV(x + 1.0625, y + 1.9375, z + 0.0001, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 1.9375, z + 0.0001, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 2.0625, z + 0.0001, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625, z + 1, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x + 1.0625, y + 0.9375, z + 1, u0, v0);
					tess.addVertexWithUV(x + 1.0625, y + 1.0625, z + 1, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 0.9375, y + 1.9375, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 2.0625, z + 0, u0, v1);
					tess.draw();

					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1.0625, y + 0.9375, z + 1, u1, v1);
					tess.addVertexWithUV(x + 1.0625, y + 1.9375, z + 0, u1, v0);
					tess.addVertexWithUV(x + 1.0625, y + 2.0625, z + 0, u0, v0);
					tess.addVertexWithUV(x + 1.0625, y + 1.0625, z + 1, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1.0625, y + 1.9375, z + 0, u1, v1);
					tess.addVertexWithUV(x + 1.0625, y + 0.9375, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 1.9375, z + 0, u0, v1);
					tess.draw();
					
					//Left Support rail
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + 0.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 1,      y + 0.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 1,      y + 1.0625, z + 0, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1,      y + 1.0625, z + 0, u1, v1);
					tess.addVertexWithUV(x + 1,      y + 0.9375, z + 0, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375, z + 0, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625, z + 0, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0.9375, y + 0.0625,  z + 1.0001, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + -0.0625, z + 1.0001, u1, v0);
					tess.addVertexWithUV(x + 1,      y + -0.0625, z + 1.0001, u0, v0);
					tess.addVertexWithUV(x + 1,      y + 0.0625,  z + 1.0001, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375,  z + 0, u1, v1);
					tess.addVertexWithUV(x + 0.9375, y + -0.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + 0.0625,  z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 1.0625,  z + 0, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1.0001, y + -0.0625, z + 1, u1, v1);
					tess.addVertexWithUV(x + 1.0001, y + 0.9375,  z + 0, u1, v0);
					tess.addVertexWithUV(x + 1.0001, y + 1.0625,  z + 0, u0, v0);
					tess.addVertexWithUV(x + 1.0001, y + 0.0625,  z + 1, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1,      y + 0.9375,  z + 0, u1, v1);
					tess.addVertexWithUV(x + 1,      y + -0.0625, z + 1, u1, v0);
					tess.addVertexWithUV(x + 0.9375, y + -0.0625, z + 1, u0, v0);
					tess.addVertexWithUV(x + 0.9375, y + 0.9375,  z + 0, u0, v1);
					tess.draw();
					tess.startDrawingQuads();
				}
				renderOversizeBlock(0.96875D, 0.75D, 0.09375D, 1.03125D, 1.875D, 0.15625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.96875D, 0.50D, 0.34375D, 1.03125D, 1.625D, 0.40625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.96875D, 0.25D, 0.59375D, 1.03125D, 1.375D, 0.65625D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.96875D, 0.0D,  0.84375D, 1.03125D, 1.125D,  0.90625D, true, renderer, block, x, y, z, meta);
			}
		}
		
		if (meta == 3) { //EAST
			if (!connectNorth) {
				if (!walkwayAbove) {
					//Handrail tess
					tess.draw();
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 2.0625, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 2.0625, z - 0.0625, u0, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625, z - 0.0625, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0001, y + 0.9375, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x - 0.0001, y + 1.0625, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x - 0.0001, y + 1.0625, z - 0.0625, u0, v0);
					tess.addVertexWithUV(x - 0.0001, y + 0.9375, z - 0.0625, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1, y + 1.9375, z - 0.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 2.0625, z - 0.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 2.0625, z + 0.0625, u0, v0);
					tess.addVertexWithUV(x + 1, y + 1.9375, z + 0.0625, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0, y + 0.9375, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 1.9375, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 2.0625, z + 0.0625, u0, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 0.0625, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1, y + 1.9375, z - 0.0625, u1, v1);
					tess.addVertexWithUV(x + 0, y + 0.9375, z - 0.0625, u1, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625, z - 0.0625, u0, v0);
					tess.addVertexWithUV(x + 1, y + 2.0625, z - 0.0625, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1, y + 1.9375, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 0, y + 0.9375, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 0, y + 0.9375, z - 0.0625, u0, v0);
					tess.addVertexWithUV(x + 1, y + 1.9375, z - 0.0625, u0, v1);
					tess.draw();
					
					//Support Rail
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0, y + 0.0625, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0,      u0, v0);
					tess.addVertexWithUV(x + 0, y + 0.0625, z + 0,      u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0001, y + -0.0625, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x - 0.0001, y + 0.0625,  z + 0.0625, u1, v0);
					tess.addVertexWithUV(x - 0.0001, y + 0.0625,  z + 0,      u0, v0);
					tess.addVertexWithUV(x - 0.0001, y + -0.0625, z + 0,      u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0,      u1, v1);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0,      u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.0625, u0, v0);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.0625, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0, y + -0.0625, z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 0.9375,  z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625,  z + 0.0625, u0, v0);
					tess.addVertexWithUV(x + 0, y + 0.0625,  z + 0.0625, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1, y + 0.9375,  z - 0.0001, u1, v1);
					tess.addVertexWithUV(x + 0, y + -0.0625, z - 0.0001, u1, v0);
					tess.addVertexWithUV(x + 0, y + 0.0625,  z - 0.0001, u0, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625,  z - 0.0001, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1, y + 0.9375,  z + 0.0625, u1, v1);
					tess.addVertexWithUV(x + 0, y + -0.0625, z + 0.0625, u1, v0);
					tess.addVertexWithUV(x + 0, y + -0.0625, z + 0,      u0, v0);
					tess.addVertexWithUV(x + 1, y + 0.9375,  z + 0,      u0, v1);
					tess.draw();				
					
					tess.startDrawingQuads();
				}
				renderOversizeBlock(0.09375D, 0.0D,  -0.03125D, 0.15625D, 1.125D,  0.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.34375D, 0.25D, -0.03125D, 0.40625D, 1.375D, 0.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.59375D, 0.50D, -0.03125D, 0.65625D, 1.625D, 0.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.84375D, 0.75D, -0.03125D, 0.90625D, 1.875D, 0.03125D, true, renderer, block, x, y, z, meta);
			}
			
			if (!connectSouth) {
				if (!walkwayAbove) {
					tess.draw();
					//Handrail tess
					
					//Top
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 1.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 2.0625, z + 1.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 2.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0001, y + 0.9375, z + 1.0625, u1, v1);
					tess.addVertexWithUV(x - 0.0001, y + 1.0625, z + 1.0625, u1, v0);
					tess.addVertexWithUV(x - 0.0001, y + 1.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x - 0.0001, y + 0.9375, z + 0.9375, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1, y + 1.9375, z + 0.9375, u1, v1);
					tess.addVertexWithUV(x + 1, y + 2.0625, z + 0.9375, u1, v0);
					tess.addVertexWithUV(x + 1, y + 2.0625, z + 1.0625, u0, v0);
					tess.addVertexWithUV(x + 1, y + 1.9375, z + 1.0625, u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0, y + 0.9375, z + 1.0625, u1, v1);
					tess.addVertexWithUV(x + 1, y + 1.9375, z + 1.0625, u1, v0);
					tess.addVertexWithUV(x + 1, y + 2.0625, z + 1.0625, u0, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 1.0625, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1, y + 1.9375, z + 0.9375, u1, v1);
					tess.addVertexWithUV(x + 0, y + 0.9375, z + 0.9375, u1, v0);
					tess.addVertexWithUV(x + 0, y + 1.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 1, y + 2.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1, y + 1.9375, z + 1.0625, u1, v1);
					tess.addVertexWithUV(x + 0, y + 0.9375, z + 1.0625, u1, v0);
					tess.addVertexWithUV(x + 0, y + 0.9375, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 1, y + 1.9375, z + 0.9375, u0, v1);
					tess.draw();
					
					//Support Rail
					tess.startDrawingQuads();
					tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
					tess.addVertexWithUV(x + 0, y + 0.0625, z + 1,      u1, v1);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 1,      u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 0, y + 0.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					//Back
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x - 0.0001, y - 0.0625, z + 1,      u1, v1);
					tess.addVertexWithUV(x - 0.0001, y + 0.0625, z + 1,      u1, v0);
					tess.addVertexWithUV(x - 0.0001, y + 0.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x - 0.0001, y - 0.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					//Front
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.9375, u1, v1);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.9375, u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 1,      u0, v0);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 1,      u0, v1);
					tess.draw();
					
					//Right
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 0, y - 0.0625, z + 1.0001, u1, v1);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 1.0001, u1, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 1.0001, u0, v0);
					tess.addVertexWithUV(x + 0, y + 0.0625, z + 1.0001, u0, v1);
					tess.draw();
					
					//Left
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.9375, u1, v1);
					tess.addVertexWithUV(x + 0, y - 0.0625, z + 0.9375, u1, v0);
					tess.addVertexWithUV(x + 0, y + 0.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 1, y + 1.0625, z + 0.9375, u0, v1);
					tess.draw();
					
					//Bottom
					tess.startDrawingQuads();
					tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 1,      u1, v1);
					tess.addVertexWithUV(x + 0, y - 0.0625, z + 1,      u1, v0);
					tess.addVertexWithUV(x + 0, y - 0.0625, z + 0.9375, u0, v0);
					tess.addVertexWithUV(x + 1, y + 0.9375, z + 0.9375, u0, v1);
					tess.draw();
					
					tess.startDrawingQuads();
				}
				renderOversizeBlock(0.09375D, 0.0D,  0.96875D, 0.15625D, 1.125D,  1.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.34375D, 0.25D, 0.96875D, 0.40625D, 1.375D, 1.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.59375D, 0.50D, 0.96875D, 0.65625D, 1.625D, 1.03125D, true, renderer, block, x, y, z, meta);
				renderOversizeBlock(0.84375D, 0.75D, 0.96875D, 0.90625D, 1.875D, 1.03125D, true, renderer, block, x, y, z, meta);
			}
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.walkwayTraditionalRenderID;
	}
	
	public boolean checkConnections(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x, y, z).isNormalCube(world, x, y, z)) {
			return true;
		}
		if (world.getBlock(x, y, z) instanceof BlockWalkwayStairs) {
			return true;
		}
		return false;
	}
	
	public void renderBlock(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, boolean existsInWorld, RenderBlocks renderer, Block block, int x, int y, int z, int meta) {
		renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
		if (existsInWorld) {
			renderer.renderStandardBlock(block, x, y, z);
		} else {
			Tessellator tess = Tessellator.instance;
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			
			tess.startDrawingQuads();
			tess.setNormal(0.0F, -1.0F, 0.0F);
			renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
			tess.draw();
			
			tess.startDrawingQuads();
			tess.setNormal(0.0F, 1.0F, 0.0F);
			renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, 0));
			tess.draw();
			
			tess.startDrawingQuads();
			tess.setNormal(0.0F, 0.0F, -1.0F);
			renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, 0));
			tess.draw();
			
			tess.startDrawingQuads();
			tess.setNormal(0.0F, 0.0F, 1.0F);
			renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, 0));
			tess.draw();
			
			tess.startDrawingQuads();
			tess.setNormal(-1.0F, 0.0F, 0.0F);
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, 0));
			tess.draw();
			
			tess.startDrawingQuads();
			tess.setNormal(1.0F, 0.0F, 0.0F);
			renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, 0));
			tess.draw();
			
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
	}
	
	public void renderOversizeBlock(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, boolean existsInWorld, RenderBlocks renderer, Block block, int x, int y, int z, int meta) {
		renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
		
		if (existsInWorld) {
			renderer.setOverrideBlockTexture(block.getIcon(7, meta));
			renderer.renderStandardBlock(block, x, y, z);
			renderer.clearOverrideBlockTexture();
		} else {
			Tessellator tess = Tessellator.instance;
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			
			tess.startDrawingQuads();
			tess.setNormal(0.0F, -1.0F, 0.0F);
			renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
			tess.draw();
			
			tess.startDrawingQuads();
			tess.setNormal(0.0F, 1.0F, 0.0F);
			renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, 0));
			tess.draw();
			
			tess.startDrawingQuads();
			tess.setNormal(0.0F, 0.0F, -1.0F);
			renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, 0));
			tess.draw();
			
			tess.startDrawingQuads();
			tess.setNormal(0.0F, 0.0F, 1.0F);
			renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, 0));
			tess.draw();
			
			tess.startDrawingQuads();
			tess.setNormal(-1.0F, 0.0F, 0.0F);
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, 0));
			tess.draw();
			
			tess.startDrawingQuads();
			tess.setNormal(1.0F, 0.0F, 0.0F);
			renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, 0));
			tess.draw();
			
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
	}
}*/