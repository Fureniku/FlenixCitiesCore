/*package com.silvaniastudios.cities.core.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class AdminShelvesItemRenderer implements IItemRenderer {
	
	private FloatingShelvesModel model;
	
	public AdminShelvesItemRenderer() {
		model = new FloatingShelvesModel();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
			case ENTITY: {
				renderShelves(180);
				return;
			}
        	case EQUIPPED: {
        		renderShelvesHeld(180);
        		return;
        	}
	        case EQUIPPED_FIRST_PERSON: {
	            renderShelvesHeld(180);
	            return;
	            }
            case INVENTORY: {
                renderShelves(0);
                return;
            }
            default:
                return;
		}
	}
	
	private void renderShelves(int rot) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixcities", "textures/entities/floatingshelves.png"));
        model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}
	
	private void renderShelvesHeld(int rot) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 2.0F, 0.0F);
        GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixcities", "textures/entities/floatingshelves.png"));
        model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}
}*/
