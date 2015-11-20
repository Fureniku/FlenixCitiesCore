package co.uk.silvania.cities.core.client.renders;

import org.lwjgl.opengl.GL11;

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
		renderBlock(0.96875D, 0.0D, 0.59375D, 1.03125D, 1.0D, 0.06525D, renderer, block);
		renderBlock(0.96875D, 0.0D, 0.84375D, 1.03125D, 1.0D, 0.90625D, renderer, block);
		
		renderBlock(-0.03125D, 0.0D, 0.09375D, 0.03125D, 1.0D, 0.15625D, renderer, block);
		renderBlock(-0.03125D, 0.0D, 0.34375D, 0.03125D, 1.0D, 0.40625D, renderer, block);
		renderBlock(-0.03125D, 0.0D, 0.59375D, 0.03125D, 1.0D, 0.65625D, renderer, block);
		renderBlock(-0.03125D, 0.0D, 0.84375D, 0.03125D, 1.0D, 0.90625D, renderer, block);
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return 0;
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
