package co.uk.silvania.cities.core.client;

import org.lwjgl.opengl.GL11;


import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PosterRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		
	}

    @Override
    @SideOnly(Side.CLIENT)
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    	int meta = world.getBlockMetadata(x, y, z);
    	int side = 0;
    	Icon icon = block.getIcon(side, meta);
    	Icon b = block.getIcon(1, meta);
    	
    	int brightness = Block.blocksList[Block.stone.blockID].getMixedBrightnessForBlock(world, x, y, z);
    	
    	float u = icon.getMinU();
    	float v = icon.getMinV();
    	float U = icon.getMaxU();
    	float V = icon.getMaxV();
    	
		Tessellator tess = Tessellator.instance;
		tess.addTranslation(x, y, z);
		tess.setBrightness(brightness - 2);
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		

		if (meta == 0) {
			//Front
			tess.addVertexWithUV(0.0, 0.0, 0.99, U, V);
			tess.addVertexWithUV(0.0, 1.5, 0.99, U, v);
			tess.addVertexWithUV(1.0, 1.5, 0.99, u, v);
			tess.addVertexWithUV(1.0, 0.0, 0.99, u, V);
			
			//Back
			tess.addVertexWithUV(1.0, 0.0, 0.99, U, V);
			tess.addVertexWithUV(1.0, 1.5, 0.99, U, v);
			tess.addVertexWithUV(0.0, 1.5, 0.99, u, v);
			tess.addVertexWithUV(0.0, 0.0, 0.99, U, V);
		}
		if (meta == 1) {
			//Front
			tess.addVertexWithUV(0.01, 0.0, 0.0, U, V);
			tess.addVertexWithUV(0.01, 1.5, 0.0, U, v);
			tess.addVertexWithUV(0.01, 1.5, 1.0, u, v);
			tess.addVertexWithUV(0.01, 0.0, 1.0, u, V);
			
			//Back
			tess.addVertexWithUV(0.01, 0.0, 1.0, U, V);
			tess.addVertexWithUV(0.01, 1.5, 1.0, U, v);
			tess.addVertexWithUV(0.01, 1.5, 0.0, u, v);
			tess.addVertexWithUV(0.01, 0.0, 0.0, U, V);
		}
		if (meta == 2) {
			//Front
			tess.addVertexWithUV(1.0, 0.0, 0.01, U, V);
			tess.addVertexWithUV(1.0, 1.5, 0.01, U, v);
			tess.addVertexWithUV(0.0, 1.5, 0.01, u, v);
			tess.addVertexWithUV(0.0, 0.0, 0.01, u, V);
			
			//Back
			tess.addVertexWithUV(0.0, 0.0, 0.01, U, V);
			tess.addVertexWithUV(0.0, 1.5, 0.01, U, v);
			tess.addVertexWithUV(1.0, 1.5, 0.01, u, v);
			tess.addVertexWithUV(1.0, 0.0, 0.01, U, V);
		}
		if (meta == 3) {
			//Front
			tess.addVertexWithUV(0.99, 0.0, 1, U, V);
			tess.addVertexWithUV(0.99, 1.5, 1, U, v);
			tess.addVertexWithUV(0.99, 1.5, 0, u, v);
			tess.addVertexWithUV(0.99, 0.0, 0, u, V);
			
			//Back
			tess.addVertexWithUV(0.99, 0.0, 0, U, V);
			tess.addVertexWithUV(0.99, 1.5, 0, U, v);
			tess.addVertexWithUV(0.99, 1.5, 1, u, v);
			tess.addVertexWithUV(0.99, 0.0, 1, U, V);
		}
		tess.addTranslation(-x, -y, -z);
        return true;
    }

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.PosterRenderID;
	}
}