/*package com.silvaniastudios.cities.core.client.models;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.cities.econ.store.entity.TileEntityAdminShop;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityAdminShopRenderer extends TileEntitySpecialRenderer {
		
	private final FloatingShelvesModel model;
	private final RenderItem renderer;
	
	private ItemStack slot0;
	private ItemStack slot1;
	private ItemStack slot2;
	private ItemStack slot3;
	
	private TileEntityAdminShop shelvesEntity;
		
	public TileEntityAdminShopRenderer() {
		this.model = new FloatingShelvesModel();
		this.renderer = new RenderItem();
		
		renderer.setRenderManager(RenderManager.instance);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.shelvesEntity = (TileEntityAdminShop) te;
		int i = te.getBlockMetadata();
		int meta = 180;
		
		this.slot0 = this.shelvesEntity.getStackInSlot(0);
		this.slot1 = this.shelvesEntity.getStackInSlot(1);
		this.slot2 = this.shelvesEntity.getStackInSlot(2);
		this.slot3 = this.shelvesEntity.getStackInSlot(3);

		float S1xOffset = 0;
		float S1zOffset = 0;
		
		float S2xOffset = 0;
		float S2zOffset = 0;
		
		if (i == 0) {
			meta = 0;
			S1xOffset = 0.25F;
			S1zOffset = 0.25F;
			
			S2zOffset = 0.25F;
			S2xOffset = 0.75F;
		}

		if (i == 3) {
			meta = 90;
			S1xOffset = 0.25F;
			S1zOffset = 0.75F;
			
			S2zOffset = 0.25F;
			S2xOffset = 0.25F;
		}

		if (i == 2) {
			meta = 180;
			S1xOffset = 0.75F;
			S1zOffset = 0.75F;
			
			S2zOffset = 0.75F;
			S2xOffset = 0.25F;
		}

		if (i == 1) {
			meta = 270;
			S1xOffset = 0.75F;
			S1zOffset = 0.25F;
			
			S2zOffset = 0.75F;
			S2xOffset = 0.75F;
		}
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixcities", "textures/entities/floatingshelves.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(meta, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		
		if (slot0 != null) {
			GL11.glPushMatrix();
			ItemStack renderStack = slot0.copy();
			renderStack.stackSize = 1;
			EntityItem entity = new EntityItem(null, 0.0D, 0.0D, 0.0D, renderStack);
			entity.hoverStart = 0.0F;
			GL11.glTranslated((float) x + S1xOffset, (float) y + 0.585F, (float) z + S1zOffset);
			GL11.glRotatef(meta + 180, 0.0F, 1.0F, 0.0F);
			entity.setEntityItemStack(renderStack);
			renderer.doRender(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			GL11.glPopMatrix();
		}
		
		if (slot1 != null) {
			GL11.glPushMatrix();
			ItemStack renderStack = slot1.copy();
			renderStack.stackSize = 1;
			EntityItem entity = new EntityItem(null, 0.0D, 0.0D, 0.0D, renderStack);
			entity.hoverStart = 0.0F;
			GL11.glTranslated((float) x + S2xOffset, (float) y + 0.585F, (float) z + S2zOffset);
			GL11.glRotatef(meta + 180, 0.0F, 1.0F, 0.0F);
			entity.setEntityItemStack(renderStack);
			renderer.doRender(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			GL11.glPopMatrix();
		}
		
		if (slot2 != null) {
			GL11.glPushMatrix();
			ItemStack renderStack = slot2.copy();
			renderStack.stackSize = 1;
			EntityItem entity = new EntityItem(null, 0.0D, 0.0D, 0.0D, renderStack);
			entity.hoverStart = 0.0F;
			GL11.glTranslated((float) x + S1xOffset, (float) y + 0.075F, (float) z + S1zOffset);
			GL11.glRotatef(meta + 180, 0.0F, 1.0F, 0.0F);
			entity.setEntityItemStack(renderStack);
			renderer.doRender(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			GL11.glPopMatrix();
		}
		
		if (slot3 != null) {
			GL11.glPushMatrix();
			ItemStack renderStack = slot3.copy();
			renderStack.stackSize = 1;
			EntityItem entity = new EntityItem(null, 0.0D, 0.0D, 0.0D, renderStack);
			entity.hoverStart = 0.0F;
			GL11.glTranslated((float) x + S2xOffset, (float) y + 0.075F, (float) z + S2zOffset);
			GL11.glRotatef(meta + 180, 0.0F, 1.0F, 0.0F);
			entity.setEntityItemStack(renderStack);
			renderer.doRender(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			GL11.glPopMatrix();
		}
	}

	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
		Tessellator tess = Tessellator.instance;
		float brightness = block.getLightValue(world, i, j, k);
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  (float) modulousModifier,  divModifier);
	}
}*/