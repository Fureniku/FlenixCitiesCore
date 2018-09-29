package com.silvaniastudios.cities.core;

import com.silvaniastudios.cities.core.blocks.BlockRebar;
import com.silvaniastudios.cities.core.blocks.BlockWalkway;
import com.silvaniastudios.cities.core.blocks.SkyscraperBlocks;
import com.silvaniastudios.cities.core.blocks.StyledGlass;
import com.silvaniastudios.cities.core.items.CitiesItemBlock;
import com.silvaniastudios.cities.econ.atm.ATMBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class CoreBlocks {
	
	/** BASE **/
	public static SkyscraperBlocks skyscraper_blocks_1 = new SkyscraperBlocks("skyscraper_blocks_1", FlenixCities.tabDecorative);
	public static SkyscraperBlocks skyscraper_blocks_2 = new SkyscraperBlocks("skyscraper_blocks_2", FlenixCities.tabDecorative);
	public static SkyscraperBlocks skyscraper_blocks_3 = new SkyscraperBlocks("skyscraper_blocks_3", FlenixCities.tabDecorative);
	public static SkyscraperBlocks skyscraper_blocks_4 = new SkyscraperBlocks("skyscraper_blocks_4", FlenixCities.tabDecorative);
	public static SkyscraperBlocks skyscraper_blocks_5 = new SkyscraperBlocks("skyscraper_blocks_5", FlenixCities.tabDecorative);
	public static SkyscraperBlocks skyscraper_blocks_6 = new SkyscraperBlocks("skyscraper_blocks_6", FlenixCities.tabDecorative);
	public static SkyscraperBlocks skyscraper_blocks_7 = new SkyscraperBlocks("skyscraper_blocks_7", FlenixCities.tabDecorative);
	
	public static BlockRebar rebar = new BlockRebar("rebar", Material.IRON);
	
	public static SkyscraperBlocks white_ornate = new SkyscraperBlocks("white_ornate", FlenixCities.tabDecorative);
	public static SkyscraperBlocks dark_ornate = new SkyscraperBlocks("dark_ornate", FlenixCities.tabDecorative);
	public static SkyscraperBlocks floor_block = new SkyscraperBlocks("floor_block", FlenixCities.tabDecorative);
	public static SkyscraperBlocks plastic_block = new SkyscraperBlocks("plastic_block", FlenixCities.tabDecorative);
	
	public static SkyscraperBlocks floor_ceiling_tile = new SkyscraperBlocks("floor_ceiling_tile", FlenixCities.tabDecorative);
	public static SkyscraperBlocks wool_ceiling_tile = new SkyscraperBlocks("wool_ceiling_tile", FlenixCities.tabDecorative);
	public static SkyscraperBlocks wool_wood_oak = new SkyscraperBlocks("wool_wood_oak", FlenixCities.tabDecorative);
	public static SkyscraperBlocks wool_wood_spruce = new SkyscraperBlocks("wool_wood_spruce", FlenixCities.tabDecorative);
	public static SkyscraperBlocks wool_wood_birch = new SkyscraperBlocks("wool_wood_birch", FlenixCities.tabDecorative);
	public static SkyscraperBlocks wool_wood_jungle = new SkyscraperBlocks("wool_wood_jungle", FlenixCities.tabDecorative);
	public static SkyscraperBlocks wool_wood_acacia = new SkyscraperBlocks("wool_wood_acacia", FlenixCities.tabDecorative);
	public static SkyscraperBlocks wool_wood_dark_oak = new SkyscraperBlocks("wool_wood_dark_oak", FlenixCities.tabDecorative);
	public static SkyscraperBlocks wool_stone = new SkyscraperBlocks("wool_stone", FlenixCities.tabDecorative);
	
	public static StyledGlass styled_glass = new StyledGlass("styled_glass", FlenixCities.tabDecorative);
	public static StyledGlass styled_glass_white = new StyledGlass("styled_glass_white", FlenixCities.tabDecorative);
	
	public static BlockWalkway walkway_oak_laminate = new BlockWalkway("walkway_oak_laminate", Material.WOOD);
	public static BlockWalkway walkway_spruce_laminate = new BlockWalkway("walkway_spruce_laminate", Material.WOOD);
	public static BlockWalkway walkway_birch_laminate = new BlockWalkway("walkway_birch_laminate", Material.WOOD);
	public static BlockWalkway walkway_jungle_laminate = new BlockWalkway("walkway_jungle_laminate", Material.WOOD);
	public static BlockWalkway walkway_acacia_laminate = new BlockWalkway("walkway_acacia_laminate", Material.WOOD);
	public static BlockWalkway walkway_dark_oak_laminate = new BlockWalkway("walkway_dark_oak_laminate", Material.WOOD);
	
	public static BlockWalkway walkway_oak_brick = new BlockWalkway("walkway_oak_brick", Material.WOOD);
	public static BlockWalkway walkway_spruce_brick = new BlockWalkway("walkway_spruce_brick", Material.WOOD);
	public static BlockWalkway walkway_birch_brick = new BlockWalkway("walkway_birch_brick", Material.WOOD);
	public static BlockWalkway walkway_jungle_brick = new BlockWalkway("walkway_jungle_brick", Material.WOOD);
	public static BlockWalkway walkway_acacia_brick = new BlockWalkway("walkway_acacia_brick", Material.WOOD);
	public static BlockWalkway walkway_dark_oak_brick = new BlockWalkway("walkway_dark_oak_brick", Material.WOOD);

	public static BlockWalkway walkway_skyscraper_white = new BlockWalkway("walkway_skyscraper_white", Material.WOOD);
	public static BlockWalkway walkway_skyscraper_light_grey = new BlockWalkway("walkway_skyscraper_light_grey", Material.WOOD);
	public static BlockWalkway walkway_skyscraper_dark_grey = new BlockWalkway("walkway_skyscraper_dark_grey", Material.WOOD);
	public static BlockWalkway walkway_skyscraper_black = new BlockWalkway("walkway_skyscraper_black", Material.WOOD);
	
	public static ATMBlock atmBlock = new ATMBlock("atmBlock");
	
	/**RESEARCH**/
	public static SkyscraperBlocks rsch_ceramic_tile = new SkyscraperBlocks("rsch_ceramic_tile", FlenixCities.tabResearch);
	public static SkyscraperBlocks rsch_ceramic_wall = new SkyscraperBlocks("rsch_ceramic_wall", FlenixCities.tabResearch);
	public static SkyscraperBlocks rsch_decorative = new SkyscraperBlocks("rsch_decorative", FlenixCities.tabResearch);
	public static SkyscraperBlocks rsch_grill = new SkyscraperBlocks("rsch_grill", FlenixCities.tabResearch);
	
	public static BlockWalkway walkway_steel_checkerplate = new BlockWalkway("walkway_steel_checkerplate", Material.WOOD);
	public static BlockWalkway walkway_steel_hazard = new BlockWalkway("walkway_steel_hazard", Material.WOOD);
	
	
	
	
	public static void register(IForgeRegistry<Block> registry) {
		//Base stuff
		registry.registerAll(
				skyscraper_blocks_1,
				skyscraper_blocks_2,
				skyscraper_blocks_3,
				skyscraper_blocks_4,
				skyscraper_blocks_5,
				skyscraper_blocks_6,
				skyscraper_blocks_7,
				rebar,
				white_ornate,
				dark_ornate,
				floor_block,
				plastic_block,
				floor_ceiling_tile,
				wool_ceiling_tile,
				wool_wood_oak,
				wool_wood_spruce,
				wool_wood_birch,
				wool_wood_jungle,
				wool_wood_acacia,
				wool_wood_dark_oak,
				wool_stone,
				styled_glass,
				styled_glass_white,
				
				walkway_oak_laminate,
				walkway_spruce_laminate,
				walkway_birch_laminate,
				walkway_jungle_laminate,
				walkway_acacia_laminate,
				walkway_dark_oak_laminate,
				walkway_oak_brick,
				walkway_spruce_brick,
				walkway_birch_brick,
				walkway_jungle_brick,
				walkway_acacia_brick,
				walkway_dark_oak_brick,
				walkway_skyscraper_white,
				walkway_skyscraper_light_grey,
				walkway_skyscraper_dark_grey,
				walkway_skyscraper_black,
				atmBlock
		);
		//research stuff
		registry.registerAll(
				rsch_ceramic_tile,
				rsch_ceramic_wall,
				rsch_decorative,
				rsch_grill,
				walkway_steel_checkerplate,
				walkway_steel_hazard
		);
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		//base stuff
		registry.register(new CitiesItemBlock(skyscraper_blocks_1).setRegistryName(skyscraper_blocks_1.getRegistryName()));
		registry.register(new CitiesItemBlock(skyscraper_blocks_2).setRegistryName(skyscraper_blocks_2.getRegistryName()));
		registry.register(new CitiesItemBlock(skyscraper_blocks_3).setRegistryName(skyscraper_blocks_3.getRegistryName()));
		registry.register(new CitiesItemBlock(skyscraper_blocks_4).setRegistryName(skyscraper_blocks_4.getRegistryName()));
		registry.register(new CitiesItemBlock(skyscraper_blocks_5).setRegistryName(skyscraper_blocks_5.getRegistryName()));
		registry.register(new CitiesItemBlock(skyscraper_blocks_6).setRegistryName(skyscraper_blocks_6.getRegistryName()));
		registry.register(new CitiesItemBlock(skyscraper_blocks_7).setRegistryName(skyscraper_blocks_7.getRegistryName()));
		registry.register(new CitiesItemBlock(rebar).setRegistryName(rebar.getRegistryName()));
		
		registry.register(new CitiesItemBlock(white_ornate).setRegistryName(white_ornate.getRegistryName()));
		registry.register(new CitiesItemBlock(dark_ornate).setRegistryName(dark_ornate.getRegistryName()));
		registry.register(new CitiesItemBlock(floor_block).setRegistryName(floor_block.getRegistryName()));
		registry.register(new CitiesItemBlock(plastic_block).setRegistryName(plastic_block.getRegistryName()));
		registry.register(new CitiesItemBlock(floor_ceiling_tile).setRegistryName(floor_ceiling_tile.getRegistryName()));
		registry.register(new CitiesItemBlock(wool_ceiling_tile).setRegistryName(wool_ceiling_tile.getRegistryName()));
		registry.register(new CitiesItemBlock(wool_wood_oak).setRegistryName(wool_wood_oak.getRegistryName()));
		registry.register(new CitiesItemBlock(wool_wood_spruce).setRegistryName(wool_wood_spruce.getRegistryName()));
		registry.register(new CitiesItemBlock(wool_wood_birch).setRegistryName(wool_wood_birch.getRegistryName()));
		registry.register(new CitiesItemBlock(wool_wood_jungle).setRegistryName(wool_wood_jungle.getRegistryName()));
		registry.register(new CitiesItemBlock(wool_wood_acacia).setRegistryName(wool_wood_acacia.getRegistryName()));
		registry.register(new CitiesItemBlock(wool_wood_dark_oak).setRegistryName(wool_wood_dark_oak.getRegistryName()));
		registry.register(new CitiesItemBlock(wool_stone).setRegistryName(wool_stone.getRegistryName()));
		
		registry.register(new CitiesItemBlock(styled_glass).setRegistryName(styled_glass.getRegistryName()));
		registry.register(new CitiesItemBlock(styled_glass_white).setRegistryName(styled_glass_white.getRegistryName()));
		
		registry.register(new CitiesItemBlock(walkway_oak_laminate).setRegistryName(walkway_oak_laminate.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_spruce_laminate).setRegistryName(walkway_spruce_laminate.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_birch_laminate).setRegistryName(walkway_birch_laminate.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_jungle_laminate).setRegistryName(walkway_jungle_laminate.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_acacia_laminate).setRegistryName(walkway_acacia_laminate.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_dark_oak_laminate).setRegistryName(walkway_dark_oak_laminate.getRegistryName()));
		
		registry.register(new CitiesItemBlock(walkway_oak_brick).setRegistryName(walkway_oak_brick.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_spruce_brick).setRegistryName(walkway_spruce_brick.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_birch_brick).setRegistryName(walkway_birch_brick.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_jungle_brick).setRegistryName(walkway_jungle_brick.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_acacia_brick).setRegistryName(walkway_acacia_brick.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_dark_oak_brick).setRegistryName(walkway_dark_oak_brick.getRegistryName()));
		
		registry.register(new CitiesItemBlock(walkway_skyscraper_white).setRegistryName(walkway_skyscraper_white.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_skyscraper_light_grey).setRegistryName(walkway_skyscraper_light_grey.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_skyscraper_dark_grey).setRegistryName(walkway_skyscraper_dark_grey.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_skyscraper_black).setRegistryName(walkway_skyscraper_black.getRegistryName()));
		
		registry.register(new CitiesItemBlock(atmBlock).setRegistryName(atmBlock.getRegistryName()));
		
		//research stuff
		registry.register(new CitiesItemBlock(rsch_ceramic_tile).setRegistryName(rsch_ceramic_tile.getRegistryName()));
		registry.register(new CitiesItemBlock(rsch_ceramic_wall).setRegistryName(rsch_ceramic_wall.getRegistryName()));
		registry.register(new CitiesItemBlock(rsch_decorative).setRegistryName(rsch_decorative.getRegistryName()));
		registry.register(new CitiesItemBlock(rsch_grill).setRegistryName(rsch_grill.getRegistryName()));
		
		registry.register(new CitiesItemBlock(walkway_steel_checkerplate).setRegistryName(walkway_steel_checkerplate.getRegistryName()));
		registry.register(new CitiesItemBlock(walkway_steel_hazard).setRegistryName(walkway_steel_hazard.getRegistryName()));
	}
	
	public static void registerModels() {
		//base stuff
		skyscraper_blocks_1.initModel();
		skyscraper_blocks_2.initModel();
		skyscraper_blocks_3.initModel();
		skyscraper_blocks_4.initModel();
		skyscraper_blocks_5.initModel();
		skyscraper_blocks_6.initModel();
		skyscraper_blocks_7.initModel();
		rebar.initModel();
		
		white_ornate.initModel();
		dark_ornate.initModel();
		floor_block.initModel();
		plastic_block.initModel();
		
		floor_ceiling_tile.initModel();
		wool_ceiling_tile.initModel();
		wool_wood_oak.initModel();
		wool_wood_spruce.initModel();
		wool_wood_birch.initModel();
		wool_wood_jungle.initModel();
		wool_wood_acacia.initModel();
		wool_wood_dark_oak.initModel();
		wool_stone.initModel();
		
		styled_glass.initModel();
		styled_glass_white.initModel();
		
		walkway_oak_laminate.initModel();
		walkway_spruce_laminate.initModel();
		walkway_birch_laminate.initModel();
		walkway_jungle_laminate.initModel();
		walkway_acacia_laminate.initModel();
		walkway_dark_oak_laminate.initModel();
		
		walkway_oak_brick.initModel();
		walkway_spruce_brick.initModel();
		walkway_birch_brick.initModel();
		walkway_jungle_brick.initModel();
		walkway_acacia_brick.initModel();
		walkway_dark_oak_brick.initModel();
		
		walkway_skyscraper_white.initModel();
		walkway_skyscraper_light_grey.initModel();
		walkway_skyscraper_dark_grey.initModel();
		walkway_skyscraper_black.initModel();
		
		atmBlock.initModel();
		
		//research stuff
		rsch_ceramic_tile.initModel();
		rsch_ceramic_wall.initModel();
		rsch_decorative.initModel();
		rsch_grill.initModel();
		
		walkway_steel_checkerplate.initModel();
		walkway_steel_hazard.initModel();
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModel(Block block) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(FlenixCities.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}

	
	/*private static CityConfig config;
	
	public static Block floatingShelvesBlock;
	public static Block npcSpawnerBlock;
	public static Block adminShopBlock;
	public static Block stockChest;
		
	public static Block oakWalkwayStairs;
	public static Block spruceWalkwayStairs;
	public static Block birchWalkwayStairs;
	public static Block jungleWalkwayStairs;
	public static Block acaciaWalkwayStairs;
	public static Block darkOakWalkwayStairs;
	
	public static Block whiteWalkwayStairs;
	public static Block lightGreyWalkwayStairs;
	public static Block darkGreyWalkwayStairs;
	public static Block blackWalkwayStairs;
	
	public static void initEconBlocks() {
		floatingShelvesBlock = new FloatingShelvesBlock().setBlockName("floatingShelvesBlock");	
		stockChest = new StockChestBlock().setBlockName("stockChest");
	}
	
	public static void initSpecialBlocks() {
		npcSpawnerBlock = new NPCSpawnerBlock().setBlockName("npcSpawnerBlock");
		adminShopBlock = new AdminShopBlock().setBlockName("adminShopBlock");
	}
	
	public static void initDecorativeGennedBlocks() {
		if (CityConfig.extraDecorativeBlocks) {
			ArrayList<String> materials = new ArrayList<String>();
			
			materials.add("white");
			materials.add("lightGrey");
			materials.add("darkGrey");
			materials.add("black");
			materials.add("orange");
			materials.add("magenta");
			materials.add("lightBlue");
			materials.add("yellow");
			materials.add("pink");
			materials.add("cyan");
			materials.add("purple");
			materials.add("darkGreen");
			materials.add("blue");
			materials.add("brown");
			materials.add("green");
			materials.add("red");
			
			for (int i = 0; i < materials.size(); i++) {
				String mat = materials.get(i);
				
				Block block;
				
				if (mat.equalsIgnoreCase("white") || mat.equalsIgnoreCase("lightGrey") || mat.equalsIgnoreCase("darkGrey") || mat.equalsIgnoreCase("black")) {
					block = skyscraperBlocks;
				} else if (mat.equalsIgnoreCase("orange") || mat.equalsIgnoreCase("magenta") || mat.equalsIgnoreCase("lightBlue") || mat.equalsIgnoreCase("yellow")) {
					block = skyscraperBlocks2;
				} else if (mat.equalsIgnoreCase("pink") || mat.equalsIgnoreCase("cyan") || mat.equalsIgnoreCase("purple") || mat.equalsIgnoreCase("darkGreen")) {
					block = skyscraperBlocks3;
				} else {
					block = skyscraperBlocks4;
				}
				
				Block fence = new BlockFenceFCC(mat).setBlockName(mat + "Fence");
				Block stairs = new BlockStairsFCC(block, mat).setBlockName(mat + "Stairs");
				//Block panel = new BlockSlabFCC(mat).setBlockName(mat + "Panel");
				Block walkwayStairs = new WalkwayStairsFCC(mat, "walkwayStairs").setBlockName(mat + "WalkwayStairs");
				Block railing = new RailingFCC(mat, "railing").setBlockName(mat + "Railing");
				Block openStairs = new OpenStairsFCC(mat, "openStairs").setBlockName(mat + "OpenStairs");
				Block slope45 = new Slope45FCC(block, mat).setBlockName(mat + "Slope45");
				Block slope225Low = new Slope225HorizontalAFCC(mat).setBlockName(mat + "Slope225Low");
				Block slope225High = new Slope225HorizontalBFCC(mat).setBlockName(mat + "Slope225High");
				Block slope225Vertical = new Slope225VerticalFCC(mat).setBlockName(mat + "Slope225Vertical");
				Block cornerPost = new CornerPostFCC(mat, "cornerPost").setBlockName(mat + "CornerPost");
			}
		}
	}*/
}
