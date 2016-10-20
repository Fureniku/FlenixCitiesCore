package com.silvaniastudios.cities.core.client.npcspawner;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class NPCSpawnerModel extends ModelBase {
	ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
  
	 public NPCSpawnerModel() {
		 textureWidth = 64;
		 textureHeight = 32;
    
		 Shape1 = new ModelRenderer(this, 0, 0);
		 Shape1.addBox(0F, 0F, 0F, 12, 1, 1);
	     Shape1.setRotationPoint(-6F, 23F, 7F);
	     Shape1.setTextureSize(64, 32);
	     Shape1.mirror = true;
	     setRotation(Shape1, 0F, 0F, 0F);
	     Shape2 = new ModelRenderer(this, 0, 0);
	     Shape2.addBox(0F, 0F, 0F, 12, 1, 1);
	     Shape2.setRotationPoint(-6F, 23F, -8F);
	     Shape2.setTextureSize(64, 32);
	     Shape2.mirror = true;
	     setRotation(Shape2, 0F, 0F, 0F);
	     Shape3 = new ModelRenderer(this, 38, 0);
	     Shape3.addBox(0F, 0F, 0F, 1, 1, 12);
	     Shape3.setRotationPoint(-8F, 23F, -6F);
	     Shape3.setTextureSize(64, 32);
	     Shape3.mirror = true;
	     setRotation(Shape3, 0F, 0F, 0F);
	     Shape4 = new ModelRenderer(this, 38, 0);
	     Shape4.addBox(0F, 0F, 0F, 1, 1, 12);
	     Shape4.setRotationPoint(7F, 23F, -6F);
	     Shape4.setTextureSize(64, 32);
	     Shape4.mirror = true;
	     setRotation(Shape4, 0F, 0F, 0F);
	     Shape5 = new ModelRenderer(this, 0, 2);
	     Shape5.addBox(0F, 0F, 0F, 2, 2, 2);
	     Shape5.setRotationPoint(6F, 22F, -8F);
	     Shape5.setTextureSize(64, 32);
	     Shape5.mirror = true;
	     setRotation(Shape5, 0F, 0F, 0F);
	     Shape6 = new ModelRenderer(this, 0, 2);
	     Shape6.addBox(0F, 0F, 0F, 2, 2, 2);
	     Shape6.setRotationPoint(6F, 22F, 6F);
	     Shape6.setTextureSize(64, 32);
	     Shape6.mirror = true;
	     setRotation(Shape6, 0F, 0F, 0F);
	     Shape7 = new ModelRenderer(this, 0, 2);
	     Shape7.addBox(0F, 0F, 0F, 2, 2, 2);
	     Shape7.setRotationPoint(-8F, 22F, 6F);
	     Shape7.setTextureSize(64, 32);
	     Shape7.mirror = true;
	     setRotation(Shape7, 0F, 0F, 0F);
	     Shape8 = new ModelRenderer(this, 0, 2);
	     Shape8.addBox(0F, 0F, 0F, 2, 2, 2);
	     Shape8.setRotationPoint(-8F, 22F, -8F);
	     Shape8.setTextureSize(64, 32);
	     Shape8.mirror = true;
	     setRotation(Shape8, 0F, 0F, 0F);
	     Shape9 = new ModelRenderer(this, 0, 22);
	     Shape9.addBox(0F, 0F, 0F, 8, 2, 8);
	     Shape9.setRotationPoint(-4F, 22F, -4F);
	     Shape9.setTextureSize(64, 32);
	     Shape9.mirror = true;
	     setRotation(Shape9, 0F, 0F, 0F);
	     Shape10 = new ModelRenderer(this, 0, 19);
	     Shape10.addBox(1F, 0F, -0.25F, 19, 1, 2);
	     Shape10.setRotationPoint(-8F, 23F, 7F);
	     Shape10.setTextureSize(64, 32);
	     Shape10.mirror = true;
	     setRotation(Shape10, 0F, 0.7853982F, 0F);
	     Shape11 = new ModelRenderer(this, 0, 16);
	     Shape11.addBox(2F, 0F, -1F, 19, 1, 2);
	     Shape11.setRotationPoint(-8F, 23F, -8F);
	     Shape11.setTextureSize(64, 32);
	     Shape11.mirror = true;
	     setRotation(Shape11, 0F, -0.7853982F, 0F);
	 }
	 
  	 public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		 super.render(entity, f, f1, f2, f3, f4, f5);
		 setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		 Shape1.render(f5);
		 Shape2.render(f5);
		 Shape3.render(f5);
		 Shape4.render(f5);
		 Shape5.render(f5);
		 Shape6.render(f5);
		 Shape7.render(f5);
		 Shape8.render(f5);
		 Shape9.render(f5);
		 Shape10.render(f5);
		 Shape11.render(f5);
	 }
  
	 private void setRotation(ModelRenderer model, float x, float y, float z) {
		 model.rotateAngleX = x;
		 model.rotateAngleY = y;
		 model.rotateAngleZ = z;
	 }
  
	 public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		 super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	 }
}
