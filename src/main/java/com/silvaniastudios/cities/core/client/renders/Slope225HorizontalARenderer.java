/*package com.silvaniastudios.cities.core.client.renders;

import com.silvaniastudios.cities.core.blocks.decorative.CornerPostFCC;
import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class Slope225HorizontalARenderer extends BlockRenderCore implements ISimpleBlockRenderingHandler {
	
	CornerPostRenderer cornerPostRenderer = new CornerPostRenderer();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		IIcon icon = block.getIcon(0, 0);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		final float FACE_XZ_NORMAL = 0.8944F;
		final float FACE_Y_NORMAL  = 0.4472F;
		
		int x = 0, y = 0, z = 0;
		tess.startDrawingQuads();
		tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
		tess.addVertexWithUV(x + 1, y + 0,   z + 1, u1, v1);
		tess.addVertexWithUV(x + 1, y + 0.5, z,     u1, v0);
		tess.addVertexWithUV(x,     y + 0.5, z,     u0, v0);
		tess.addVertexWithUV(x,     y + 0,   z + 1, u0, v1);
		tess.draw();
		
		//Back
		tess.startDrawingQuads();
		tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tess.addVertexWithUV(x,     y + 0,   z, u1, v1);
		tess.addVertexWithUV(x,     y + 0.5, z, u1, v0);
		tess.addVertexWithUV(x + 1, y + 0.5, z, u0, v0);
		tess.addVertexWithUV(x + 1, y + 0,   z, u0, v1);
		tess.draw();
		
		//Left
		tess.startDrawingQuads();
		tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
		tess.addVertexWithUV(x, y + 0,   z + 1, u1, v1);
		tess.addVertexWithUV(x, y + 0,   z + 1, u1, v0);
		tess.addVertexWithUV(x, y + 0.5, z + 0, u0, v0);
		tess.addVertexWithUV(x, y + 0,   z + 0, u0, v1);
		tess.draw();
		
		//Right
		tess.startDrawingQuads();
		tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tess.addVertexWithUV(x + 1, y + 0,   z + 0, u1, v1);
		tess.addVertexWithUV(x + 1, y + 0.5, z + 0, u1, v0);
		tess.addVertexWithUV(x + 1, y + 0,   z + 1, u0, v0);
		tess.addVertexWithUV(x + 1, y + 0,   z + 1, u0, v1);
		tess.draw();
		
		//Bottom
		tess.startDrawingQuads();
		tess.setNormal(0.0F, -1.0F, 0.0F);
		tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
		tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
		tess.addVertexWithUV(x,     y, z + 1, u0, v0);
		tess.addVertexWithUV(x,     y, z + 0, u0, v1);
		tess.draw();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		
		int light = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
		tess.setBrightness(light);
        
        float f = 0.8F;
        int c = block.colorMultiplier(world, x, y, z);
        float f1 = (float)(c >> 16 & 255) / 255.0F;
        float f2 = (float)(c >> 8 & 255) / 255.0F;
        float f3 = (float)(c & 255) / 255.0F;

        tess.setColorOpaque_F(f * f1, f * f2, f * f3);
		
		IIcon icon = prepBlockAndGetIcon(block, world, x, y, z, renderer);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		if (world.getBlock(x, y+1, z) instanceof CornerPostFCC) { cornerPostRenderer.renderCornerPosts(world, x, y, z,  1, renderer, world.getBlock(x, y+1, z), -1, true); }
		if (world.getBlock(x, y-1, z) instanceof CornerPostFCC) { cornerPostRenderer.renderCornerPosts(world, x, y, z, -1, renderer, world.getBlock(x, y-1, z), -1, true); }
		
		tess.draw();
		if (meta == 0)  { //Facing EAST
			//Right (south)
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0,   z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 0,   z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0,   z + 1, u0, v1);
			tess.draw();
			
			//Left (north)
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0,   z, u1, v1);
			tess.addVertexWithUV(x,     y + 0,   z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0.5, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0,   z, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0,   z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 0, u0, v0);
			tess.addVertexWithUV(x,     y + 0,   z + 0, u0, v1);
			tess.draw();
			
			//Back (east)
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0,   z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0,   z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 1)  {
			//
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0,   z + 1, u1, v1);
			tess.addVertexWithUV(x,     y + 0.5, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 0.5, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0,   z + 1, u0, v1);
			tess.draw();
			
			//
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0,   z, u1, v1);
			tess.addVertexWithUV(x,     y + 0.5, z, u1, v0);
			tess.addVertexWithUV(x,     y + 0.5, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0,   z, u0, v1);
			tess.draw();
			
			//
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 0,   z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 0.5, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 0.5, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0,   z + 0, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0,   z + 0, u1, v1);
			tess.addVertexWithUV(x,     y + 0.5, z + 0, u1, v0);
			tess.addVertexWithUV(x,     y + 0.5, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0,   z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 2)  {
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0,   z,     u1, v0);
			tess.addVertexWithUV(x,     y + 0,   z,     u0, v0);
			tess.addVertexWithUV(x,     y + 0.5, z + 1, u0, v1);
			tess.draw();
			
			//
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0,   z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 0.5, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0,   z + 1, u0, v1);
			tess.draw();
			
			//
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 0,   z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 0.5, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 0,   z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0,   z + 0, u0, v1);
			tess.draw();
			
			//
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0,   z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0,   z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0,   z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 3)  {
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0,   z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0.5, z,     u1, v0);
			tess.addVertexWithUV(x,     y + 0.5, z,     u0, v0);
			tess.addVertexWithUV(x,     y + 0,   z + 1, u0, v1);
			tess.draw();
			
			//
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0,   z, u1, v1);
			tess.addVertexWithUV(x,     y + 0.5, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0.5, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0,   z, u0, v1);
			tess.draw();
			
			//
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 0,   z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 0,   z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 0.5, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0,   z + 0, u0, v1);
			tess.draw();
			
			//
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0,   z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0,   z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0,   z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 4)  {
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 1, z,     u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z,     u0, v1);
			tess.draw();
			
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1,   z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1,   z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 1,   z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 1,   z, u1, v1);
			tess.addVertexWithUV(x,     y + 1,   z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1,   z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0.5, z, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1,   z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1,   z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0.5,   z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1,   z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 1,   z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 5)  {
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 1, z,     u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z,     u0, v1);
			tess.draw();
			
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1,   z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1,   z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1,   z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0.5, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0.5, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1,   z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1,   z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1,   z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 0.5, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1,   z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1,   z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0.5, z + 0, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1,   z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1,   z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 0.5, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0.5, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 6)  {
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 1, z,     u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z,     u0, v1);
			tess.draw();
			
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1,   z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1,   z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0.5, z + 1, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 0.5, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1,   z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1,   z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 1,   z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1,   z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1,   z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1,   z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1,   z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 0.5, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 1,   z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 7) {
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 1, z,     u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z,     u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0.5, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1,   z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1,   z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0.5, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 1,   z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1,   z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1,   z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0.5, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1,   z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1,   z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1,   z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0.5, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1,   z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1,   z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0.5, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 8) {
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 9) {
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u0, v1);
			tess.draw();
		}
		if (meta == 10) {
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u0, v1);
			tess.draw();
		}
		if (meta == 11) {			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 12) {
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v1);
			tess.draw();
		}
		if (meta == 13) {
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 14) {
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u0, v1);
			tess.draw();
		}
		if (meta == 15)  {
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		tess.startDrawingQuads();
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.slope45RenderID;
	}
}*/