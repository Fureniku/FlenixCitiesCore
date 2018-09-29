/*package com.silvaniastudios.cities.core.client.renders;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.cities.core.blocks.BlockWalkway;
import com.silvaniastudios.cities.core.blocks.BlockWalkwayStairs;
import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class WalkwayTraditionalRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		renderBlock(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D, false, renderer, block, 0, 0, 0, 0);
		
		renderBlock(0.9375D, 0.9375D, -0.0625D, 1.0625D, 1.0625D, 1.0625D, false, renderer, block, 0, 0, 0, 0);
		renderBlock(-0.0625D, 0.9375D, -0.0625D, 0.0625D, 1.0625D, 1.0625D, false, renderer, block, 0, 0, 0, 0);
		
		renderBlock(0.96875D, 0.0D, 0.09375D, 1.03125D, 1.0D, 0.15625D, false, renderer, block, 0, 0, 0, 0);
		renderBlock(0.96875D, 0.0D, 0.34375D, 1.03125D, 1.0D, 0.40625D, false, renderer, block, 0, 0, 0, 0);
		renderBlock(0.96875D, 0.0D, 0.59375D, 1.03125D, 1.0D, 0.65625D, false, renderer, block, 0, 0, 0, 0);
		renderBlock(0.96875D, 0.0D, 0.84375D, 1.03125D, 1.0D, 0.90625D, false, renderer, block, 0, 0, 0, 0);
		
		renderBlock(-0.03125D, 0.0D, 0.09375D, 0.03125D, 1.0D, 0.15625D, false, renderer, block, 0, 0, 0, 0);
		renderBlock(-0.03125D, 0.0D, 0.34375D, 0.03125D, 1.0D, 0.40625D, false, renderer, block, 0, 0, 0, 0);
		renderBlock(-0.03125D, 0.0D, 0.59375D, 0.03125D, 1.0D, 0.65625D, false, renderer, block, 0, 0, 0, 0);
		renderBlock(-0.03125D, 0.0D, 0.84375D, 0.03125D, 1.0D, 0.90625D, false, renderer, block, 0, 0, 0, 0);
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		
		boolean connectNorth = checkConnections(world, x, y, z-1, 0, meta);
		boolean connectEast =  checkConnections(world, x+1, y, z, 1, meta);
		boolean connectSouth = checkConnections(world, x, y, z+1, 0, meta);
		boolean connectWest =  checkConnections(world, x-1, y, z, 1, meta);
		
		boolean connectNorthEast = checkConnections(world, x+1, y, z-1, -1, meta);
		boolean connectNorthWest = checkConnections(world, x-1, y, z-1, -1, meta);
		boolean connectSouthEast = checkConnections(world, x+1, y, z+1, -1, meta);
		boolean connectSouthWest = checkConnections(world, x-1, y, z+1, -1, meta);
		
		boolean walkwayBelow = world.getBlock(x, y-1, z) instanceof BlockWalkway;
		boolean walkwayAbove = world.getBlock(x, y+1, z) instanceof BlockWalkway;
		
		boolean renderPlatform = false;
		boolean renderSnow = (meta >= 4 && meta <= 7); //Meta's 4-7 render snow.
		
		if ((!(world.getBlock(x, y-1, z).isOpaqueCube()) && !walkwayBelow) || (world.getBlock(x, y-1, z).isOpaqueCube() && meta >= 12)) {
			renderPlatform = true;
			renderBlock(0.0D, -0.125D, 0.0D, 1.0D, 0.0D, 1.0D, true, renderer, block, x, y, z, meta);
		}
		
		if (renderSnow) {
			renderSnowBlock(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D, true, renderer, block, x, y, z, meta);
		}
		
		boolean renderNorth = false; //X 0-1,  Z 0
		boolean renderEast  = false; //X 1,    Z 0-1
		boolean renderSouth = false; //X 0-1,  Z 1
		boolean renderWest  = false; //X 0,    Z 0-1
		
		boolean renderNorthEast = false;
		boolean renderNorthWest = false;
		boolean renderSouthEast = false;
		boolean renderSouthWest = false;
		
		if ((meta % 2) == 0 || meta == 0) { //Even metadata (0, 2, 4 etc)
			if (!connectNorth) { renderNorth = true; }
			if (!connectSouth) { renderSouth = true; }
			
			if (connectNorth && !connectEast) { renderEast = true; }
			if (connectNorth && !connectWest) { renderWest = true; }
			if (connectSouth && !connectEast) { renderEast = true; }
			if (connectSouth && !connectWest) { renderWest = true; }
			
		} else { //Odd metadata
			if (!connectEast) { renderEast = true; }
			if (!connectWest) { renderWest = true; }
			
			if (connectEast && !connectNorth) { renderNorth = true; }
			if (connectEast && !connectSouth) { renderSouth = true; }
			if (connectWest && !connectNorth) { renderNorth = true; }
			if (connectWest && !connectSouth) { renderSouth = true; }
		}
		
		if (renderNorth) {
			if (!walkwayAbove) {
				//Alter sizes based on connections.
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderEast) { cnct1 = 0.0625D; }
				if (renderWest) { cnct2 = 0.0625D; }
				
				renderOversizeBlock(0.0D + cnct2, 0.9375D, -0.0625D, 1.0D - cnct1, 1.0625D, 0.0625D, true, renderer, block, x, y, z, meta);
				
				if (renderSnow) {
					renderSnowBlock(0.0D + cnct2, 1.0625D, -0.0625D, 1.0D - cnct1, 1.125D, 0.0625D, true, renderer, block, x, y, z, meta);
				}
			}
			
			renderOversizeBlock(0.09375D, 0.0D, -0.03125D, 0.15625D, 1.0D, 0.03125D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(0.34375D, 0.0D, -0.03125D, 0.40625D, 1.0D, 0.03125D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(0.59375D, 0.0D, -0.03125D, 0.65625D, 1.0D, 0.03125D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(0.84375D, 0.0D, -0.03125D, 0.90625D, 1.0D, 0.03125D, true, renderer, block, x, y, z, meta);
		}
		
		if (renderSouth) {
			if (!walkwayAbove) {
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderEast) { cnct1 = 0.0625D; }
				if (renderWest) { cnct2 = 0.0625D; }
				
				renderOversizeBlock(0.0D + cnct2, 0.9375D, 0.9375D, 1.0D - cnct1, 1.0625D, 1.0625D, true, renderer, block, x, y, z, meta);
				
				if (renderSnow) {
					renderSnowBlock(0.0D + cnct2, 1.0625D, 0.9375D, 1.0D - cnct1, 1.125D, 1.0625D, true, renderer, block, x, y, z, meta);
				}
			}
			renderOversizeBlock(0.09375D, 0.0D, 0.96875D, 0.15625D, 1.0D, 1.03125D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(0.34375D, 0.0D, 0.96875D, 0.40625D, 1.0D, 1.03125D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(0.59375D, 0.0D, 0.96875D, 0.65625D, 1.0D, 1.03125D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(0.84375D, 0.0D, 0.96875D, 0.90625D, 1.0D, 1.03125D, true, renderer, block, x, y, z, meta);
		}
		
		if (renderEast) {
			if (!walkwayAbove) {
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderNorth) { cnct1 = 0.0625D; }
				if (renderSouth) { cnct2 = 0.0625D; }
				
				renderOversizeBlock(0.9375D, 0.9375D, 0.0D + cnct1, 1.0625D, 1.0625D, 1.0D - cnct2, true, renderer, block, x, y, z, meta);
				
				if (renderSnow) {
					renderSnowBlock(0.9375D, 1.0625D, 0.0D + cnct1, 1.0625D, 1.125D, 1.0D - cnct2, true, renderer, block, x, y, z, meta);
				}
			}
			renderOversizeBlock(0.96875D, 0.0D, 0.09375D, 1.03125D, 1.0D, 0.15625D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(0.96875D, 0.0D, 0.34375D, 1.03125D, 1.0D, 0.40625D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(0.96875D, 0.0D, 0.59375D, 1.03125D, 1.0D, 0.65625D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(0.96875D, 0.0D, 0.84375D, 1.03125D, 1.0D, 0.90625D, true, renderer, block, x, y, z, meta);
		}
		
		if (renderWest) { //-0.625D 1.0625D
			if (!walkwayAbove) {
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderNorth) { cnct1 = 0.0625D; }
				if (renderSouth) { cnct2 = 0.0625D; }
				
				renderOversizeBlock(-0.0625D, 0.9375D, 0.0D + cnct1, 0.0625D, 1.0625D, 1.0D - cnct2, true, renderer, block, x, y, z, meta);
				
				if (renderSnow) {
					renderSnowBlock(-0.0625D, 1.0625D, 0.0D + cnct1, 0.0625D, 1.125D, 1.0D - cnct2, true, renderer, block, x, y, z, meta);
				}
			}
			renderOversizeBlock(-0.03125D, 0.0D, 0.09375D, 0.03125D, 1.0D, 0.15625D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(-0.03125D, 0.0D, 0.34375D, 0.03125D, 1.0D, 0.40625D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(-0.03125D, 0.0D, 0.59375D, 0.03125D, 1.0D, 0.65625D, true, renderer, block, x, y, z, meta);
			renderOversizeBlock(-0.03125D, 0.0D, 0.84375D, 0.03125D, 1.0D, 0.90625D, true, renderer, block, x, y, z, meta);
		}
		
		//Notches in the corners.
		if (!walkwayAbove) {
			if (renderNorth && renderEast) {
				renderOversizeBlock(0.9375D, 0.9375D, -0.0625D, 1.0625D, 1.0625D, 0.0625D, true, renderer, block, x, y, z, meta);
				if (renderSnow) {
					renderSnowBlock(0.9375D, 1.0625D, -0.0625D, 1.0625D, 1.125D, 0.0625D, true, renderer, block, x, y, z, meta);
				}
			}
			
			if (renderNorth && renderWest) {
				renderOversizeBlock(-0.0625D, 0.9375D, -0.0625D, 0.0625D, 1.0625D, 0.0625D, true, renderer, block, x, y, z, meta);
				if (renderSnow) {
					renderSnowBlock(-0.0625D, 1.0625D, -0.0625D, 0.0625D, 1.125D, 0.0625D, true, renderer, block, x, y, z, meta);
				}
			}
			
			if (renderSouth && renderEast) {
				renderOversizeBlock(0.9375D, 0.9375D, 0.9375D, 1.0625D, 1.0625D, 1.0625D, true, renderer, block, x, y, z, meta);
				if (renderSnow) {
					renderSnowBlock(0.9375D, 1.0625D, 0.9375D, 1.0625D, 1.125D, 1.0625D, true, renderer, block, x, y, z, meta);
				}
			}
			
			if (renderSouth && renderWest) {
				renderOversizeBlock(-0.0625D, 0.9375D, 0.9375D, 0.0625D, 1.0625D, 1.0625D, true, renderer, block, x, y, z, meta);
				if (renderSnow) {
					renderSnowBlock(-0.0625D, 1.0625D, 0.9375D, 0.0625D, 1.125D, 1.0625D, true, renderer, block, x, y, z, meta);
				}
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
	
	//North: Z1 - Z0, East: X0 - X1, South: Z0 - Z1, West = X1 - X0
	public boolean checkConnections(IBlockAccess world, int x, int y, int z, int targetMeta, int meta) {	
		
		if (world.getBlock(x, y, z).isNormalCube(world, x, y, z)) {
			return true;
		}
		if (meta <= 1 || meta == 4 || meta == 5 || meta == 8 || meta == 9) {
			if (world.getBlock(x, y-1, z).isNormalCube(world, x, y, z)) {
				return true;
			}
		}
		if (world.getBlock(x, y, z) instanceof BlockWalkway || world.getBlock(x, y, z) instanceof BlockWalkwayStairs) {
			return true;
		}
		
		if (targetMeta >= 0) {
			if (world.getBlock(x, y - 1, z) instanceof BlockWalkwayStairs) {
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
		renderer.setOverrideBlockTexture(block.getIcon(7, meta));
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
	
	public void renderSnowBlock(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, boolean existsInWorld, RenderBlocks renderer, Block block, int x, int y, int z, int meta) {
		renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.setOverrideBlockTexture(Blocks.snow.getIcon(0, 0));
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
}*/