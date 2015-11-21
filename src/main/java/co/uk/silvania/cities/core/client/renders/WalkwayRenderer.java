package co.uk.silvania.cities.core.client.renders;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.cities.core.blocks.BlockWalkway;
import co.uk.silvania.cities.core.client.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class WalkwayRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		renderBlock(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D, renderer, block);
		
		renderBlock(0.9375D, 0.9375D, -0.0625D, 1.0625D, 1.0625D, 1.0625D, renderer, block);
		renderBlock(-0.0625D, 0.9375D, -0.0625D, 0.0625D, 1.0625D, 1.0625D, renderer, block);
		
		renderBlock(0.96875D, 0.0D, 0.09375D, 1.03125D, 1.0D, 0.15625D, renderer, block);
		renderBlock(0.96875D, 0.0D, 0.34375D, 1.03125D, 1.0D, 0.40625D, renderer, block);
		renderBlock(0.96875D, 0.0D, 0.59375D, 1.03125D, 1.0D, 0.65625D, renderer, block);
		renderBlock(0.96875D, 0.0D, 0.84375D, 1.03125D, 1.0D, 0.90625D, renderer, block);
		
		renderBlock(-0.03125D, 0.0D, 0.09375D, 0.03125D, 1.0D, 0.15625D, renderer, block);
		renderBlock(-0.03125D, 0.0D, 0.34375D, 0.03125D, 1.0D, 0.40625D, renderer, block);
		renderBlock(-0.03125D, 0.0D, 0.59375D, 0.03125D, 1.0D, 0.65625D, renderer, block);
		renderBlock(-0.03125D, 0.0D, 0.84375D, 0.03125D, 1.0D, 0.90625D, renderer, block);
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		
		boolean connectNorth = checkConnections(world, x, y, z-1);
		boolean connectEast =  checkConnections(world, x+1, y, z);
		boolean connectSouth = checkConnections(world, x, y, z+1);
		boolean connectWest =  checkConnections(world, x-1, y, z);
		boolean walkwayBelow = world.getBlock(x, y-1, z) instanceof BlockWalkway;
		boolean walkwayAbove = world.getBlock(x, y+1, z) instanceof BlockWalkway;
		
		if (!(world.getBlock(x, y-1, z).isOpaqueCube()) && !walkwayBelow) {
			
			//Baseplate
			renderBlock(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D, true, renderer, block, x, y, z, meta);
			
			//Support beams
			if (meta == 0) { //East-west
				renderBlock(0.0D, -0.25D, 0.25D, 1.0D, 0.0D, 0.75D, true, renderer, block, x, y, z, meta);
				if (connectNorth) { renderBlock(0.25D, -0.25D, 0.0D, 0.75D, 0.0D, 0.25D, true, renderer, block, x, y, z, meta); }
				if (connectSouth) { renderBlock(0.25D, -0.25D, 0.75D, 0.75D, 0.0D, 1.0D, true, renderer, block, x, y, z, meta); }
			} else if (meta == 1 || connectNorth || connectSouth) {
				renderBlock(0.25D, -0.25D, 0.0D, 0.75D, 0.0D, 1.0D, true, renderer, block, x, y, z, meta);
				if (connectEast) { renderBlock(0.75D, -0.25, 0.25D, 1.0D, 0.0D, 0.75D, true, renderer, block, x, y, z, meta); }
				if (connectWest) { renderBlock(0.0D, -0.25, 0.25D, 0.25D, 0.0D, 0.75D, true, renderer, block, x, y, z, meta); }
			}
		}
		
		boolean renderNorth = false;
		boolean renderEast  = false;
		boolean renderSouth = false;
		boolean renderWest  = false;
		
		if (meta == 0) {
			if (!connectNorth) { renderNorth = true; }
			if (!connectSouth) { renderSouth = true; }
			
			if (connectNorth && !connectEast) { renderEast = true; }
			if (connectNorth && !connectWest) { renderWest = true; }
			if (connectSouth && !connectEast) { renderEast = true; }
			if (connectSouth && !connectWest) { renderWest = true; }
			
		} else if (meta == 1) {
			if (!connectEast) { renderEast = true; }
			if (!connectWest) { renderWest = true; }
			
			if (connectEast && !connectNorth) { renderNorth = true; }
			if (connectEast && !connectSouth) { renderSouth = true; }
			if (connectWest && !connectNorth) { renderNorth = true; }
			if (connectWest && !connectSouth) { renderSouth = true; }
		}
		
		if (renderNorth) {
			if (!walkwayAbove) {
				renderBlock(-0.0625D, 0.9375D, -0.0625D, 1.0625D, 1.0625D, 0.0625D, true, renderer, block, x, y, z, meta);
			}
			renderBlock(0.09375D, 0.0D, -0.03125D, 0.15625D, 1.0D, 0.03125D, true, renderer, block, x, y, z, meta);
			renderBlock(0.34375D, 0.0D, -0.03125D, 0.40625D, 1.0D, 0.03125D, true, renderer, block, x, y, z, meta);
			renderBlock(0.59375D, 0.0D, -0.03125D, 0.65625D, 1.0D, 0.03125D, true, renderer, block, x, y, z, meta);
			renderBlock(0.84375D, 0.0D, -0.03125D, 0.90625D, 1.0D, 0.03125D, true, renderer, block, x, y, z, meta);
		}
		
		if (renderSouth) {
			if (!walkwayAbove) {
				renderBlock(-0.0625D, 0.9375D, 0.9375D, 1.0625D, 1.0625D, 1.0625D, true, renderer, block, x, y, z, meta);
			}
			renderBlock(0.09375D, 0.0D, 0.96875D, 0.15625D, 1.0D, 1.03125D, true, renderer, block, x, y, z, meta);
			renderBlock(0.34375D, 0.0D, 0.96875D, 0.40625D, 1.0D, 1.03125D, true, renderer, block, x, y, z, meta);
			renderBlock(0.59375D, 0.0D, 0.96875D, 0.65625D, 1.0D, 1.03125D, true, renderer, block, x, y, z, meta);
			renderBlock(0.84375D, 0.0D, 0.96875D, 0.90625D, 1.0D, 1.03125D, true, renderer, block, x, y, z, meta);
		}
		
		if (renderEast) {
			if (!walkwayAbove) {
				renderBlock(0.9375D, 0.9375D, -0.0625D, 1.0625D, 1.0625D, 1.0625D, true, renderer, block, x, y, z, meta);
			}
			renderBlock(0.96875D, 0.0D, 0.09375D, 1.03125D, 1.0D, 0.15625D, true, renderer, block, x, y, z, meta);
			renderBlock(0.96875D, 0.0D, 0.34375D, 1.03125D, 1.0D, 0.40625D, true, renderer, block, x, y, z, meta);
			renderBlock(0.96875D, 0.0D, 0.59375D, 1.03125D, 1.0D, 0.65625D, true, renderer, block, x, y, z, meta);
			renderBlock(0.96875D, 0.0D, 0.84375D, 1.03125D, 1.0D, 0.90625D, true, renderer, block, x, y, z, meta);
		}
		
		if (renderWest) {
			if (!walkwayAbove) {
				renderBlock(-0.0625D, 0.9375D, -0.0625D, 0.0625D, 1.0625D, 1.0625D, true, renderer, block, x, y, z, meta);
			}
			renderBlock(-0.03125D, 0.0D, 0.09375D, 0.03125D, 1.0D, 0.15625D, true, renderer, block, x, y, z, meta);
			renderBlock(-0.03125D, 0.0D, 0.34375D, 0.03125D, 1.0D, 0.40625D, true, renderer, block, x, y, z, meta);
			renderBlock(-0.03125D, 0.0D, 0.59375D, 0.03125D, 1.0D, 0.65625D, true, renderer, block, x, y, z, meta);
			renderBlock(-0.03125D, 0.0D, 0.84375D, 0.03125D, 1.0D, 0.90625D, true, renderer, block, x, y, z, meta);
		}
		
		
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.walkwayRenderID;
	}
	
	public boolean checkConnections(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x, y, z).isNormalCube(world, x, y, z) || world.getBlock(x, y-1, z).isNormalCube(world, x, y, z)) {
			return true;
		}
		if (world.getBlock(x, y, z) instanceof BlockWalkway) {
			return true;
		}
		return false;
	}
	
	public void renderBlock(double par1, double par2, double par3, double par4, double par5, double par6, RenderBlocks renderer, Block block) {
		renderBlock(par1, par2, par3, par4, par5, par6, false, renderer, block, 0, 0, 0, 0);
	}
	
	public void renderBlock(double par1, double par2, double par3, double par4, double par5, double par6, boolean inWorld, RenderBlocks renderer, Block block, int x, int y, int z, int meta) {
		renderer.setRenderBounds(par1, par2, par3, par4, par5, par6);
		
		if (inWorld) {
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

}
