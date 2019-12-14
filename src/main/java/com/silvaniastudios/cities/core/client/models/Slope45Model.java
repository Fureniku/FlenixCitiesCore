package com.silvaniastudios.cities.core.client.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Slope45Model implements IBakedModel {
	
	private IBakedModel model;
	float p = 1/16F;
	private Slope45OverrideList overrideList;
	String rot;
	
	public Slope45Model(IBakedModel model, String rot) {
		this.model = model;
		this.rot = rot;
		this.overrideList = new Slope45OverrideList(this);
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		List<BakedQuad> quadList = new LinkedList<BakedQuad>();
		//quadList.addAll(model.getQuads(state, side, rand));
		
		if (side == null) {
			return model.getQuads(state, side, rand);
		}
		
		TextureAtlasSprite tex = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusCities.MODID + ":blocks/skyscraper_blocks_1_0");
		
		if (rot.equalsIgnoreCase("floor_north")) {
			quadList.add(RenderHelper.createQuad(tex, EnumFacing.WEST, //bad order
					//SW SE NE NW
					0.0F, 0.0F, 0.0F, 
					0.0F, 0.0F, 1.0F,
					0.0F, 0.0F, 1.0F,
					0.0F, 1.0F, 0.0F));
			quadList.add(RenderHelper.createQuad(tex, EnumFacing.EAST, //good order
					//SW SE NE NW
					1.0F, 0.0F, 1.0F, 
					1.0F, 0.0F, 0.0F,
					1.0F, 1.0F, 0.0F,
					1.0F, 0.0F, 1.0F));
			quadList.add(RenderHelper.createQuad(tex, EnumFacing.NORTH,
					//SW SE NE NW
					1.0F, 0.0F, 0.0F, 
					0.0F, 0.0F, 0.0F,
					0.0F, 1.0F, 0.0F,
					1.0F, 1.0F, 0.0F));
			quadList.add(RenderHelper.createQuad(tex, EnumFacing.DOWN,
					//SW SE NE NW
					1.0F, 0.0F, 1.0F, 
					1.0F, 0.0F, 0.0F,
					0.0F, 0.0F, 0.0F,
					0.0F, 0.0F, 1.0F));
			quadList.add(RenderHelper.createQuad(tex, EnumFacing.SOUTH,
					//SW SE NE NW
					0.0F, 0.0F, 1.0F, 
					1.0F, 0.0F, 1.0F,
					1.0F, 1.0F, 0.5F,
					0.0F, 1.0F, 1.0F));
			
		}
		return quadList;
	}

	@Override
	public boolean isAmbientOcclusion() {
		return model.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return model.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return null;
	}

	@Override
	public ItemOverrideList getOverrides() {
		return this.overrideList;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return model.getItemCameraTransforms();
	}
	
	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
		Matrix4f matrix4f = model.handlePerspective(cameraTransformType).getRight();
		return Pair.of(this, matrix4f);
	}
	
	public Slope45Model setCurrentItemStack(ItemStack stack) {
		return this;
	}
	
	private static class Slope45OverrideList extends ItemOverrideList {
		private Slope45Model model;
		
		public Slope45OverrideList(Slope45Model model) {
			super(Collections.emptyList());
			this.model = model;
		}
		
		@Override
		public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
			return this.model.setCurrentItemStack(stack);
		}
	}

}
