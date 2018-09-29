/*package com.silvaniastudios.cities.core.client.models;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.cities.econ.store.entity.TileEntityStockChest;


public class StockChestRenderer extends TileEntitySpecialRenderer {
		
	private final RenderItem renderer;
	private ItemStack item;
	private TileEntityStockChest tileEntity;
		
	public StockChestRenderer() {
		renderer = new RenderItem();
		renderer.setRenderManager(RenderManager.instance);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		tileEntity = (TileEntityStockChest) te;
		
		item = tileEntity.getStackInSlot(tileEntity.invSize - 1);
		
		if (item != null) {
			GL11.glPushMatrix();
			ItemStack itemRender = item.copy();
			itemRender.stackSize = 0;
			EntityItem entity = new EntityItem(null, 0.0D, 0.0D, 0.0D, itemRender);
			GL11.glTranslated((float) x + 0.5, (float) y + 1.2F, (float) z + 0.5);
			entity.hoverStart = 0.0F;
			entity.setEntityItemStack(itemRender);
			renderer.doRender(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			GL11.glPopMatrix();
		}
	}
}*/