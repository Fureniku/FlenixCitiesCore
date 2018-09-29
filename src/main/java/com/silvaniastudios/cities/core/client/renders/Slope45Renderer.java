/*package com.silvaniastudios.cities.core.client.renders;

import com.silvaniastudios.cities.core.blocks.decorative.CornerPostFCC;
import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class Slope45Renderer extends BlockRenderCore implements ISimpleBlockRenderingHandler {
	
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
		tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
		tess.addVertexWithUV(x + 1, y + 1, z,     u1, v0);
		tess.addVertexWithUV(x,     y + 1, z,     u0, v0);
		tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
		tess.draw();
		
		//Back
		tess.startDrawingQuads();
		tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tess.addVertexWithUV(x,     y + 0, z, u1, v1);
		tess.addVertexWithUV(x,     y + 1, z, u1, v0);
		tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
		tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
		tess.draw();
		
		//Left
		tess.startDrawingQuads();
		tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
		tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
		tess.addVertexWithUV(x, y + 0, z + 1, u1, v0);
		tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
		tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
		tess.draw();
		
		//Right
		tess.startDrawingQuads();
		tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
		tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
		tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
		tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
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
		    	
    	renderer.enableAO = true;
        boolean flag = false;
        float f3_2 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        float f6 = 0.0F;
        boolean flag1 = true;
    	
    	boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        int i1;
        float f7;
        
        int l = block.getMixedBrightnessForBlock(world, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(world, x - 1, y, z);
        renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(world, x + 1, y, z);
        renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(world, x, y, z - 1);
        renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(world, x, y, z + 1);
        renderer.aoLightValueScratchXYNP = world.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
        renderer.aoLightValueScratchXYPP = world.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
        renderer.aoLightValueScratchYZPN = world.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
        renderer.aoLightValueScratchYZPP = world.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
        flag2 = world.getBlock(x + 1, y + 1, z).getCanBlockGrass();
        flag3 = world.getBlock(x - 1, y + 1, z).getCanBlockGrass();
        flag4 = world.getBlock(x, y + 1, z + 1).getCanBlockGrass();
        flag5 = world.getBlock(x, y + 1, z - 1).getCanBlockGrass();

        if (!flag5 && !flag3) {
        	renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
        	renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
        } else {
        	renderer.aoLightValueScratchXYZNPN = world.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
        	renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(world, x - 1, y, z - 1);
        }

        if (!flag5 && !flag2) {
        	renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
        	renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
        } else {
        	renderer.aoLightValueScratchXYZPPN = world.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
        	renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(world, x + 1, y, z - 1);
        }

        if (!flag4 && !flag3) {
        	renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
        	renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
        } else {
        	renderer.aoLightValueScratchXYZNPP = world.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
        	renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(world, x - 1, y, z + 1);
        }

        if (!flag4 && !flag2) {
        	renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
        	renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
        } else {
        	renderer.aoLightValueScratchXYZPPP = world.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
        	renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(world, x + 1, y, z + 1);
        }


        i1 = l;

        f7 = world.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
        f6 = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
        f3_2 = (renderer.aoLightValueScratchYZPP + f7 + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
        f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
        f5 = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
        renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, i1);
        renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, i1);
        renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, i1);
        renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
        renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = f1;
        renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = f2;
        renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = f3;
        renderer.colorRedTopLeft *= f3_2;
        renderer.colorGreenTopLeft *= f3_2;
        renderer.colorBlueTopLeft *= f3_2;
        renderer.colorRedBottomLeft *= f4;
        renderer.colorGreenBottomLeft *= f4;
        renderer.colorBlueBottomLeft *= f4;
        renderer.colorRedBottomRight *= f5;
        renderer.colorGreenBottomRight *= f5;
        renderer.colorBlueBottomRight *= f5;
        renderer.colorRedTopRight *= f6;
        renderer.colorGreenTopRight *= f6;
        renderer.colorBlueTopRight *= f6;
        flag = true;
		
		tess.draw();
		if (meta == 0) {
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 0, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Left/slope
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.9F, 0.9F, 0.9F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
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
		if (meta == 1) {
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right/slope
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.9F, 0.9F, 0.9F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
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
		if (meta == 2) {
			//Front/slope
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.9F, 0.9F, 0.9F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z,     u1, v0);
			tess.addVertexWithUV(x,     y + 0, z,     u0, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
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
		if (meta == 3) {
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z,     u1, v0);
			tess.addVertexWithUV(x,     y + 1, z,     u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Back/slope
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.9F, 0.9F, 0.9F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
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
		if (meta == 4) {
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
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 1, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y,     z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y,     z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 5) {
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
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 6) {
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
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y,     z + 1, u1, v0);
			tess.addVertexWithUV(x,     y,     z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 1, z + 0, u0, v1);
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
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y,     z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y,     z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 8) {
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
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
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.7F, 0.7F, 0.7F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 9) {
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
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
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.7F, 0.7F, 0.7F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
		}
		if (meta == 10) {
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
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
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u0, v1);
			tess.draw();
		}
		if (meta == 11) {			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
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
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.setBrightness(light);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
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