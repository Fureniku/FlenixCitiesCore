/*package com.silvaniastudios.cities.core.client.renders;

import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class CornerPostRenderer extends BlockRenderCore implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		IIcon icon = block.getIcon(0, 0);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		renderCornerPosts(null, 0, 0, 0, 0, renderer, block, metadata, false);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		
		IIcon icon = prepBlockAndGetIcon(block, world, x, y, z, renderer);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();

		renderCornerPosts(world, x, y, z, 0, renderer, block, meta, true);
		
		return true;
	}
	
	//yOffset is the location of THIS block relative to the block it's modifying.
	//For example if there's a block below which is using this, it'll be 1. If the block is above, it'll be -1.
	//If it's not a block modifier and we're actually rendering a corner post, it should be 0.
	//Block should be THIS block, NOT the affected block.
	//Blocks which are using this should get a new block instance for this block (above/below) instead of passing itself.
	public void renderCornerPosts(IBlockAccess world, int x, int y, int z, int yOffset, RenderBlocks renderer, Block block, int meta, boolean inWorld) {
		if (meta < 0 && inWorld) {
			meta = world.getBlockMetadata(x, y+yOffset, z);
		}
		double barLow = yOffset == 1 ? 0.0625 : 0;
		double barHigh = yOffset == -1 ? 0.9375 : 1;
		if (meta == 0 || meta == 4 || meta == 7 || meta == 8 || meta == 10 || meta == 11 || meta == 12 || meta == 13) {
			renderBlock(0.8125, barLow, 0.0625, 0.9375, barHigh, 0.1875, inWorld, renderer, block, x, y, z, meta);
		}
		if (meta == 1 || meta == 4 || meta == 5 || meta == 8 || meta == 9 || meta == 11 || meta == 12 || meta == 14) {
			renderBlock(0.8125, barLow, 0.8125, 0.9375, barHigh, 0.9375, inWorld, renderer, block, x, y, z, meta);
		}
		if (meta == 2 || meta == 5 || meta == 6 || meta == 8 || meta == 9 || meta == 10 || meta == 12 || meta == 13) {
			renderBlock(0.0625, barLow, 0.8125, 0.1875, barHigh, 0.9375, inWorld, renderer, block, x, y, z, meta);
		}
		if (meta == 3 || meta == 6 || meta == 7 || meta == 9 || meta == 10 || meta == 11 || meta == 12 || meta == 14) {
			renderBlock(0.0625, barLow, 0.0625, 0.1875, barHigh, 0.1875, inWorld, renderer, block, x, y, z, meta);
		}
	}

	@Override
	public int getRenderId() {
		return ClientProxy.cornerPostRenderID;
	}
}*/