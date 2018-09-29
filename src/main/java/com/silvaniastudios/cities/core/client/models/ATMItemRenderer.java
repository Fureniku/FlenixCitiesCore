/*package com.silvaniastudios.cities.core.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ATMItemRenderer implements IItemRenderer {
	
	private ATMModel model;
	
	public ATMItemRenderer() {
		model = new ATMModel();
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
				renderATM(180);
				return;
			}
        	case EQUIPPED: {
        		renderATMHeld(270);
        		return;
        	}
	        case EQUIPPED_FIRST_PERSON: {
	            renderATMHeld(270);
	            return;
	        }
            case INVENTORY: {
                renderATM(0);
                return;
            }
            default:
                return;
		}
	}
	
	private void renderATM(int rot) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixcities", "textures/entities/atmgrey.png"));
        model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}
	
	private void renderATMHeld(int rot) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 1.5F, 0.5F);
        GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixcities", "textures/entities/atmgrey.png"));
        model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}
}*/
