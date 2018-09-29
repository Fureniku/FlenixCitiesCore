/*package com.silvaniastudios.cities.core.client.renders;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.cities.core.blocks.decorative.WalkwayFenceFCC;
import com.silvaniastudios.cities.core.blocks.decorative.WalkwayStairsFCC;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockRenderCore implements ISimpleBlockRenderingHandler {
	
	Tessellator tess = Tessellator.getInstance();
	
	public boolean checkConnections(IBlockAccess world, int x, int y, int z, int targetMeta, int meta, boolean checkBelow) {	
		if (world.getBlock(x, y, z).isNormalCube(world, x, y, z)) {
			return true;
		}
		if ((meta <= 1 || meta == 4 || meta == 5 || meta == 8 || meta == 9) && checkBelow) {
			if (world.getBlock(x, y-1, z).isNormalCube(world, x, y, z)) {
				return true;
			}
		}
		
		if (world.getBlock(x, y, z) instanceof WalkwayFenceFCC || world.getBlock(x, y, z) instanceof WalkwayStairsFCC) {
			return true;
		}
		
		
		
		if (targetMeta >= 0) {
			if (world.getBlock(x, y - 1, z) instanceof WalkwayStairsFCC) {
				int metaBelow = world.getBlockMetadata(x, y - 1, z);
				if (metaBelow == targetMeta || metaBelow == (targetMeta + 2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void renderBlock(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, boolean existsInWorld, RenderBlocks renderer, Block block, int x, int y, int z, int meta) {
		renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
		if (existsInWorld) {
			renderer.renderStandardBlock(block, x, y, z);
		} else {
			Tessellator tess = Tessellator.getInstance();
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
	
	public void renderOtherBlock(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, boolean existsInWorld, RenderBlocks renderer, Block block, int x, int y, int z, int meta, IIcon icon) {
		renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.setOverrideBlockTexture(icon);
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
		renderer.clearOverrideBlockTexture();
	}
	
	public void renderLeavesBlock(double x1, double x2, double x3, double x4, double y1, double y2, double y3, double y4, double z1, double z2, double z3, double z4, boolean existsInWorld, RenderBlocks renderer, Block block, int x, int y, int z, int meta) {
		IIcon icon = Blocks.leaves.getIcon(0, 0);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		tess.setColorOpaque_I(7979098);
		
		if (existsInWorld) {
			tess.addVertexWithUV(x1 + x, y1 + y, z1 + z, u1, v1);
			tess.addVertexWithUV(x2 + x, y2 + y, z2 + z, u1, v0);
			tess.addVertexWithUV(x3 + x, y3 + y, z3 + z, u0, v0);
			tess.addVertexWithUV(x4 + x, y4 + y, z4 + z, u0, v1);
		} else {
			tess.startDrawingQuads();
			tess.addVertexWithUV(x1, y1, z1, u1, v1);
			tess.addVertexWithUV(x2, y2, z2, u1, v0);
			tess.addVertexWithUV(x3, y3, z3, u0, v0);
			tess.addVertexWithUV(x4, y4, z4, u0, v1);
			tess.draw();
		}
	}
	
	public IIcon prepBlockAndGetIcon(Block block, IBlockAccess world, int x, int y, int z, RenderBlocks renderer) {
		int col = 255;
		float f = 0.8F;
		int c = block.colorMultiplier(world, x, y, z);
		float f1 = (float)(c >> 16 & 255) / 255.0F;
		float f2 = (float)(c >> 8 & 255) / 255.0F;
		float f3 = (float)(c & 255) / 255.0F;
		
		tess.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
		tess.setColorOpaque_F(f * f1, f * f2, f * f3);
		
		return block.getIcon(0, 0);
	}

	@Override public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
	@Override public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) { return false; }
	@Override public boolean shouldRender3DInInventory(int modelId) { return true; }
	@Override public int getRenderId() { return 0; }
	
	
	public void renderBottomFace(double x1, double x2, double x3, double x4, double y1, double y2, double y3, double y4, double z1, double z2, double z3, double z4, IIcon icon, RenderBlocks renderer, int x, int y, int z) {
        Tessellator tessellator = Tessellator.instance;

        if (renderer.hasOverrideBlockTexture()) {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = (double)icon.getInterpolatedU(renderer.renderMinX * 16.0D);
        double d4 = (double)icon.getInterpolatedU(renderer.renderMaxX * 16.0D);
        double d5 = (double)icon.getInterpolatedV(renderer.renderMinZ * 16.0D);
        double d6 = (double)icon.getInterpolatedV(renderer.renderMaxZ * 16.0D);

        if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D) {
            d3 = (double)icon.getMinU();
            d4 = (double)icon.getMaxU();
        }

        if (renderer.renderMinZ < 0.0D || renderer.renderMaxZ > 1.0D) {
            d5 = (double)icon.getMinV();
            d6 = (double)icon.getMaxV();
        }

        double d7 = d4;
        double d8 = d3;
        double d9 = d5;
        double d10 = d6;

        if (renderer.uvRotateBottom == 2) {
            d3 = (double)icon.getInterpolatedU(renderer.renderMinZ * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxX * 16.0D);
            d4 = (double)icon.getInterpolatedU(renderer.renderMaxZ * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinX * 16.0D);
            d9 = d5;
            d10 = d6;
            d7 = d3;
            d8 = d4;
            d5 = d6;
            d6 = d9;
        } else if (renderer.uvRotateBottom == 1) {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxZ * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinZ * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMaxX * 16.0D);
            d7 = d4;
            d8 = d3;
            d3 = d4;
            d4 = d8;
            d9 = d6;
            d10 = d5;
        } else if (renderer.uvRotateBottom == 3) {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxX * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinZ * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxZ * 16.0D);
            d7 = d4;
            d8 = d3;
            d9 = d5;
            d10 = d6;
        }
        
        double xA = x1 + renderer.renderMinX;
        double xC = x3 + renderer.renderMaxX;
        double d13 = y1 + renderer.renderMaxY;
        double d14 = z1 + renderer.renderMinZ;
        double d15 = z3 + renderer.renderMaxZ;

        if (renderer.renderFromInside)
        {
            xA = x1 + renderer.renderMaxX;
            xC = x1 + renderer.renderMinX;
        }

        if (renderer.enableAO)
        {
            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            tessellator.setBrightness(renderer.brightnessTopLeft);
            tessellator.addVertexWithUV(x+1, y+0, z+0, d4, d6);
            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            tessellator.setBrightness(renderer.brightnessBottomLeft);
            tessellator.addVertexWithUV(x+1, y+0, z+1, d7, d9);
            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            tessellator.setBrightness(renderer.brightnessBottomRight);
            tessellator.addVertexWithUV(x+0, y+0, z+1, d3, d5);
            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            tessellator.setBrightness(renderer.brightnessTopRight);
            tessellator.addVertexWithUV(x+0, y+0, z+0, d8, d10);
        } else {
            tessellator.addVertexWithUV(x+1, y+0, z+0, d4, d6);
            tessellator.addVertexWithUV(x+1, y+0, z+1, d7, d9);
            tessellator.addVertexWithUV(x+0, y+0, z+1, d3, d5);
            tessellator.addVertexWithUV(x+0, y+0, z+0, d8, d10);
        }*/

        /*double xA = x1 + x;
        double xB = x2 + x;
        double xC = x3 + x;
        double xD = x4 + x;
        
        double yA = y1 + y;
        double yB = y2 + y;
        double yC = y3 + y;
        double yD = y4 + y;
        
        double zA = z1 + z;
        double zB = z2 + z;
        double zC = z3 + z;
        double zD = z4 + z;


        if (renderer.renderFromInside) {
            xA = x1 + renderer.renderMaxX;
            xB = x1 + renderer.renderMaxX;
            xC = x1 + renderer.renderMinX;
            xD = x1 + renderer.renderMinX;
        }

        if (renderer.enableAO) {
            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            tessellator.setBrightness(renderer.brightnessTopLeft);
            tessellator.addVertexWithUV(xA, yA, zA, d8, d10);
            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            tessellator.setBrightness(renderer.brightnessBottomLeft);
            tessellator.addVertexWithUV(xB, yB, zB, d3, d5);
            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            tessellator.setBrightness(renderer.brightnessBottomRight);
            tessellator.addVertexWithUV(xC, yC, zC, d7, d9);
            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            tessellator.setBrightness(renderer.brightnessTopRight);
            tessellator.addVertexWithUV(xD, yD, zD, d4, d6);
        } else {
            tessellator.addVertexWithUV(xA, yA, zA, d8, d10);
            tessellator.addVertexWithUV(xB, yB, zB, d3, d5);
            tessellator.addVertexWithUV(xC, yC, zC, d7, d9);
            tessellator.addVertexWithUV(xD, yD, zD, d4, d6);
        }*/
  //  }
//}
