package com.silvaniastudios.cities.core.client.models;

import com.silvaniastudios.cities.core.FurenikusCities;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelBakeHandler {
	
public static final ModelBakeHandler instance = new ModelBakeHandler();
	
	private ModelBakeHandler() {};
	
	@SubscribeEvent
	public void onModelBakeEvent(ModelBakeEvent event) {
		System.out.println("model bake event");
		bakeSlope45Model(event, "skyscraper_slope_45_1");
	}
	
	private void bakeSlope45Model(ModelBakeEvent event, String unlocalizedName) {
		System.out.println("Slope 45 baking");
		String[] rotations = new String[] {"floor_north", "floor_east", "floor_south", "floor_west", "side_north", "side_east", "side_south", "side_west", "top_north", "top_east", "top_south", "top_west"};
		
		for (int i = 0; i < rotations.length; i++) {
			System.out.println("do ALL the slopes: " + unlocalizedName + ", " + "rotation=" + rotations[i]);
			ModelResourceLocation mrl = new ModelResourceLocation(FurenikusCities.MODID + ":" + unlocalizedName, "rotation=" + rotations[i] + ",type=standard");
			Object model = event.getModelRegistry().getObject(mrl);
			if (model == null) { System.out.println("Not found :("); }
			if (model instanceof IBakedModel) {
				System.out.println("do a replace");
				IBakedModel existingModel = (IBakedModel) model;
				Slope45Model customModel = new Slope45Model(existingModel, rotations[i]);
				event.getModelRegistry().putObject(mrl, customModel);
			}
		}
	}
}
