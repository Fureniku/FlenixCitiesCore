package com.silvaniastudios.cities.core;

import java.util.ArrayList;

import com.silvaniastudios.cities.core.blocks.BlockFence;
import com.silvaniastudios.cities.core.blocks.BlockGlassFakeWall;
import com.silvaniastudios.cities.core.blocks.BlockRebar;
import com.silvaniastudios.cities.core.blocks.BlockSlope;
import com.silvaniastudios.cities.core.blocks.BlockSteepSlope;
import com.silvaniastudios.cities.core.blocks.BlockWalkwayStairs;
import com.silvaniastudios.cities.core.blocks.BlockWallDecorativeConnecting;
import com.silvaniastudios.cities.core.blocks.CeilingLightRotatable;
import com.silvaniastudios.cities.core.blocks.CitiesBlockBase;
import com.silvaniastudios.cities.core.blocks.DecorativeSigns;
import com.silvaniastudios.cities.core.blocks.GenericDecorativeBlock;
import com.silvaniastudios.cities.core.blocks.GenericDecorativeRotatable16;
import com.silvaniastudios.cities.core.blocks.LampConeBlock;
import com.silvaniastudios.cities.core.blocks.LampOrnateBlock;
import com.silvaniastudios.cities.core.blocks.LampSquareBlock;
import com.silvaniastudios.cities.core.blocks.SkyscraperBlocks;
import com.silvaniastudios.cities.core.blocks.PillarBlock;
import com.silvaniastudios.cities.core.blocks.StyledGlass;
import com.silvaniastudios.cities.core.items.CitiesItemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class CoreBlocks {
	
	public static ArrayList<CitiesBlockBase> blockList = new ArrayList<CitiesBlockBase>();
	public static ArrayList<CitiesBlockBase> researchBlocks = new ArrayList<CitiesBlockBase>();
	
	/** BASE **/
	public static SkyscraperBlocks skyscraper_blocks_1 = new SkyscraperBlocks("skyscraper_blocks_1", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks skyscraper_blocks_2 = new SkyscraperBlocks("skyscraper_blocks_2", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks skyscraper_blocks_3 = new SkyscraperBlocks("skyscraper_blocks_3", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks skyscraper_blocks_4 = new SkyscraperBlocks("skyscraper_blocks_4", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks skyscraper_blocks_5 = new SkyscraperBlocks("skyscraper_blocks_5", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks skyscraper_blocks_6 = new SkyscraperBlocks("skyscraper_blocks_6", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks skyscraper_blocks_7 = new SkyscraperBlocks("skyscraper_blocks_7", FurenikusCities.tabBlocks);
	
	public static PillarBlock skyscraper_pillar_1 = new PillarBlock("skyscraper_pillar_1", FurenikusCities.tabBlocks);
	public static PillarBlock skyscraper_pillar_2 = new PillarBlock("skyscraper_pillar_2", FurenikusCities.tabBlocks);
	public static PillarBlock ornate_pillar_light = new PillarBlock("ornate_pillar_light", FurenikusCities.tabBlocks);
	public static PillarBlock ornate_pillar_dark  = new PillarBlock("ornate_pillar_dark", FurenikusCities.tabBlocks);
	public static PillarBlock floor_pillar = new PillarBlock("floor_pillar", FurenikusCities.tabBlocks);
	
	public static SkyscraperBlocks white_ornate = new SkyscraperBlocks("white_ornate", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks dark_ornate = new SkyscraperBlocks("dark_ornate", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks floor_block = new SkyscraperBlocks("floor_block", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks plastic_block = new SkyscraperBlocks("plastic_block", FurenikusCities.tabBlocks);
	
	public static SkyscraperBlocks floor_ceiling_tile = new SkyscraperBlocks("floor_ceiling_tile", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks wool_ceiling_tile = new SkyscraperBlocks("wool_ceiling_tile", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks wool_wood_oak = new SkyscraperBlocks("wool_wood_oak", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks wool_wood_spruce = new SkyscraperBlocks("wool_wood_spruce", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks wool_wood_birch = new SkyscraperBlocks("wool_wood_birch", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks wool_wood_jungle = new SkyscraperBlocks("wool_wood_jungle", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks wool_wood_acacia = new SkyscraperBlocks("wool_wood_acacia", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks wool_wood_dark_oak = new SkyscraperBlocks("wool_wood_dark_oak", FurenikusCities.tabBlocks);
	public static SkyscraperBlocks wool_stone = new SkyscraperBlocks("wool_stone", FurenikusCities.tabBlocks);
	
	public static StyledGlass styled_glass = new StyledGlass("styled_glass", FurenikusCities.tabBlocks);
	public static StyledGlass styled_glass_white = new StyledGlass("styled_glass_white", FurenikusCities.tabBlocks);
	
	public static BlockGlassFakeWall styled_glass_fake_off = new BlockGlassFakeWall("styled_glass_fake_off", FurenikusCities.tabBlocks, false);
	public static BlockGlassFakeWall styled_glass_white_fake_off = new BlockGlassFakeWall("styled_glass_white_fake_off", FurenikusCities.tabBlocks, false);
	public static BlockGlassFakeWall styled_glass_fake_on = new BlockGlassFakeWall("styled_glass_fake_on", FurenikusCities.tabBlocks, true);
	public static BlockGlassFakeWall styled_glass_white_fake_on = new BlockGlassFakeWall("styled_glass_white_fake_on", FurenikusCities.tabBlocks, true);
	
	/** SLOPES **/
	public static BlockSlope skyscraper_white_slope = new BlockSlope("skyscraper_white_slope");
	public static BlockSlope skyscraper_light_grey_slope = new BlockSlope("skyscraper_light_grey_slope");
	public static BlockSlope skyscraper_dark_grey_slope = new BlockSlope("skyscraper_dark_grey_slope");
	public static BlockSlope skyscraper_black_slope = new BlockSlope("skyscraper_black_slope");
	public static BlockSlope skyscraper_orange_slope = new BlockSlope("skyscraper_orange_slope");
	public static BlockSlope skyscraper_magenta_slope = new BlockSlope("skyscraper_magenta_slope");
	public static BlockSlope skyscraper_light_blue_slope = new BlockSlope("skyscraper_light_blue_slope");
	public static BlockSlope skyscraper_yellow_slope = new BlockSlope("skyscraper_yellow_slope");
	public static BlockSlope skyscraper_light_green_slope = new BlockSlope("skyscraper_light_green_slope");
	public static BlockSlope skyscraper_purple_slope = new BlockSlope("skyscraper_purple_slope");
	public static BlockSlope skyscraper_cyan_slope = new BlockSlope("skyscraper_cyan_slope");
	public static BlockSlope skyscraper_pink_slope = new BlockSlope("skyscraper_pink_slope");
	public static BlockSlope skyscraper_blue_slope = new BlockSlope("skyscraper_blue_slope");
	public static BlockSlope skyscraper_brown_slope = new BlockSlope("skyscraper_brown_slope");
	public static BlockSlope skyscraper_green_slope = new BlockSlope("skyscraper_green_slope");
	public static BlockSlope skyscraper_red_slope = new BlockSlope("skyscraper_red_slope");
	
	public static BlockSlope skyscraper_pastel_dark_blue_slope = new BlockSlope("skyscraper_pastel_dark_blue_slope");
	public static BlockSlope skyscraper_pastel_brown_slope = new BlockSlope("skyscraper_pastel_brown_slope");
	public static BlockSlope skyscraper_pastel_green_slope = new BlockSlope("skyscraper_pastel_green_slope");
	public static BlockSlope skyscraper_pastel_pink_slope = new BlockSlope("skyscraper_pastel_pink_slope");
	public static BlockSlope skyscraper_pastel_orange_slope = new BlockSlope("skyscraper_pastel_orange_slope");
	public static BlockSlope skyscraper_pastel_magenta_slope = new BlockSlope("skyscraper_pastel_magenta_slope");
	public static BlockSlope skyscraper_pastel_red_slope = new BlockSlope("skyscraper_pastel_red_slope");
	public static BlockSlope skyscraper_pastel_yellow_slope = new BlockSlope("skyscraper_pastel_yellow_slope");
	public static BlockSlope skyscraper_pastel_light_blue_slope = new BlockSlope("skyscraper_pastel_light_blue_slope");
	public static BlockSlope skyscraper_pastel_light_green_slope = new BlockSlope("skyscraper_pastel_light_green_slope");
	public static BlockSlope skyscraper_pastel_cyan_slope = new BlockSlope("skyscraper_pastel_cyan_slope");
	public static BlockSlope skyscraper_pastel_purple_slope = new BlockSlope("skyscraper_pastel_purple_slope");
	
	public static BlockSteepSlope skyscraper_white_steep_slope = new BlockSteepSlope("skyscraper_white_steep_slope");
	public static BlockSteepSlope skyscraper_light_grey_steep_slope = new BlockSteepSlope("skyscraper_light_grey_steep_slope");
	public static BlockSteepSlope skyscraper_dark_grey_steep_slope = new BlockSteepSlope("skyscraper_dark_grey_steep_slope");
	public static BlockSteepSlope skyscraper_black_steep_slope = new BlockSteepSlope("skyscraper_black_steep_slope");
	public static BlockSteepSlope skyscraper_orange_steep_slope = new BlockSteepSlope("skyscraper_orange_steep_slope");
	public static BlockSteepSlope skyscraper_magenta_steep_slope = new BlockSteepSlope("skyscraper_magenta_steep_slope");
	public static BlockSteepSlope skyscraper_light_blue_steep_slope = new BlockSteepSlope("skyscraper_light_blue_steep_slope");
	public static BlockSteepSlope skyscraper_yellow_steep_slope = new BlockSteepSlope("skyscraper_yellow_steep_slope");
	public static BlockSteepSlope skyscraper_light_green_steep_slope = new BlockSteepSlope("skyscraper_light_green_steep_slope");
	public static BlockSteepSlope skyscraper_purple_steep_slope = new BlockSteepSlope("skyscraper_purple_steep_slope");
	public static BlockSteepSlope skyscraper_cyan_steep_slope = new BlockSteepSlope("skyscraper_cyan_steep_slope");
	public static BlockSteepSlope skyscraper_pink_steep_slope = new BlockSteepSlope("skyscraper_pink_steep_slope");
	public static BlockSteepSlope skyscraper_blue_steep_slope = new BlockSteepSlope("skyscraper_blue_steep_slope");
	public static BlockSteepSlope skyscraper_brown_steep_slope = new BlockSteepSlope("skyscraper_brown_steep_slope");
	public static BlockSteepSlope skyscraper_green_steep_slope = new BlockSteepSlope("skyscraper_green_steep_slope");
	public static BlockSteepSlope skyscraper_red_steep_slope = new BlockSteepSlope("skyscraper_red_steep_slope");
	
	public static BlockSteepSlope skyscraper_pastel_dark_blue_steep_slope = new BlockSteepSlope("skyscraper_pastel_dark_blue_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_brown_steep_slope = new BlockSteepSlope("skyscraper_pastel_brown_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_green_steep_slope = new BlockSteepSlope("skyscraper_pastel_green_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_pink_steep_slope = new BlockSteepSlope("skyscraper_pastel_pink_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_orange_steep_slope = new BlockSteepSlope("skyscraper_pastel_orange_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_magenta_steep_slope = new BlockSteepSlope("skyscraper_pastel_magenta_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_red_steep_slope = new BlockSteepSlope("skyscraper_pastel_red_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_yellow_steep_slope = new BlockSteepSlope("skyscraper_pastel_yellow_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_light_blue_steep_slope = new BlockSteepSlope("skyscraper_pastel_light_blue_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_light_green_steep_slope = new BlockSteepSlope("skyscraper_pastel_light_green_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_cyan_steep_slope = new BlockSteepSlope("skyscraper_pastel_cyan_steep_slope");
	public static BlockSteepSlope skyscraper_pastel_purple_steep_slope = new BlockSteepSlope("skyscraper_pastel_purple_steep_slope");
	
	/** FENCES **/
	public static BlockFence fence_oak_laminate = new BlockFence("fence_oak_laminate", Material.WOOD);
	public static BlockFence fence_spruce_laminate = new BlockFence("fence_spruce_laminate", Material.WOOD);
	public static BlockFence fence_birch_laminate = new BlockFence("fence_birch_laminate", Material.WOOD);
	public static BlockFence fence_jungle_laminate = new BlockFence("fence_jungle_laminate", Material.WOOD);
	public static BlockFence fence_acacia_laminate = new BlockFence("fence_acacia_laminate", Material.WOOD);
	public static BlockFence fence_dark_oak_laminate = new BlockFence("fence_dark_oak_laminate", Material.WOOD);
	
	public static BlockFence fence_oak_brick = new BlockFence("fence_oak_brick", Material.WOOD);
	public static BlockFence fence_spruce_brick = new BlockFence("fence_spruce_brick", Material.WOOD);
	public static BlockFence fence_birch_brick = new BlockFence("fence_birch_brick", Material.WOOD);
	public static BlockFence fence_jungle_brick = new BlockFence("fence_jungle_brick", Material.WOOD);
	public static BlockFence fence_acacia_brick = new BlockFence("fence_acacia_brick", Material.WOOD);
	public static BlockFence fence_dark_oak_brick = new BlockFence("fence_dark_oak_brick", Material.WOOD);

	public static BlockFence fence_skyscraper_white = new BlockFence("fence_skyscraper_white", Material.ROCK);
	public static BlockFence fence_skyscraper_light_grey = new BlockFence("fence_skyscraper_light_grey", Material.ROCK);
	public static BlockFence fence_skyscraper_dark_grey = new BlockFence("fence_skyscraper_dark_grey", Material.ROCK);
	public static BlockFence fence_skyscraper_black = new BlockFence("fence_skyscraper_black", Material.ROCK);
	
	/** STAIRS **/
	public static BlockWalkwayStairs walkway_stairs_oak_laminate = new BlockWalkwayStairs("walkway_stairs_oak_laminate", Material.WOOD);
	public static BlockWalkwayStairs walkway_stairs_spruce_laminate = new BlockWalkwayStairs("walkway_stairs_spruce_laminate", Material.WOOD);
	public static BlockWalkwayStairs walkway_stairs_birch_laminate = new BlockWalkwayStairs("walkway_stairs_birch_laminate", Material.WOOD);
	public static BlockWalkwayStairs walkway_stairs_jungle_laminate = new BlockWalkwayStairs("walkway_stairs_jungle_laminate", Material.WOOD);
	public static BlockWalkwayStairs walkway_stairs_acacia_laminate = new BlockWalkwayStairs("walkway_stairs_acacia_laminate", Material.WOOD);
	public static BlockWalkwayStairs walkway_stairs_dark_oak_laminate = new BlockWalkwayStairs("walkway_stairs_dark_oak_laminate", Material.WOOD);
	
	public static BlockWalkwayStairs walkway_stairs_oak_brick = new BlockWalkwayStairs("walkway_stairs_oak_brick", Material.WOOD);
	public static BlockWalkwayStairs walkway_stairs_spruce_brick = new BlockWalkwayStairs("walkway_stairs_spruce_brick", Material.WOOD);
	public static BlockWalkwayStairs walkway_stairs_birch_brick = new BlockWalkwayStairs("walkway_stairs_birch_brick", Material.WOOD);
	public static BlockWalkwayStairs walkway_stairs_jungle_brick = new BlockWalkwayStairs("walkway_stairs_jungle_brick", Material.WOOD);
	public static BlockWalkwayStairs walkway_stairs_acacia_brick = new BlockWalkwayStairs("walkway_stairs_acacia_brick", Material.WOOD);
	public static BlockWalkwayStairs walkway_stairs_dark_oak_brick = new BlockWalkwayStairs("walkway_stairs_dark_oak_brick", Material.WOOD);

	public static BlockWalkwayStairs walkway_stairs_skyscraper_white = new BlockWalkwayStairs("walkway_stairs_skyscraper_white", Material.ROCK);
	public static BlockWalkwayStairs walkway_stairs_skyscraper_light_grey = new BlockWalkwayStairs("walkway_stairs_skyscraper_light_grey", Material.ROCK);
	public static BlockWalkwayStairs walkway_stairs_skyscraper_dark_grey = new BlockWalkwayStairs("walkway_stairs_skyscraper_dark_grey", Material.ROCK);
	public static BlockWalkwayStairs walkway_stairs_skyscraper_black = new BlockWalkwayStairs("walkway_stairs_skyscraper_black", Material.ROCK);

	/**LIGHTS**/
	public static CeilingLightRotatable ceiling_light_1 = new CeilingLightRotatable("ceiling_light_1");
	
	public static LampSquareBlock lamp_block_white = new LampSquareBlock("lamp_block_white");
	public static LampSquareBlock lamp_block_orange = new LampSquareBlock("lamp_block_orange");
	public static LampSquareBlock lamp_block_magenta = new LampSquareBlock("lamp_block_magenta");
	public static LampSquareBlock lamp_block_light_blue = new LampSquareBlock("lamp_block_light_blue");
	public static LampSquareBlock lamp_block_yellow = new LampSquareBlock("lamp_block_yellow");
	public static LampSquareBlock lamp_block_lime = new LampSquareBlock("lamp_block_lime");
	public static LampSquareBlock lamp_block_pink = new LampSquareBlock("lamp_block_pink");
	public static LampSquareBlock lamp_block_gray = new LampSquareBlock("lamp_block_gray");
	public static LampSquareBlock lamp_block_light_gray = new LampSquareBlock("lamp_block_light_gray");
	public static LampSquareBlock lamp_block_cyan = new LampSquareBlock("lamp_block_cyan");
	public static LampSquareBlock lamp_block_purple = new LampSquareBlock("lamp_block_purple");
	public static LampSquareBlock lamp_block_blue = new LampSquareBlock("lamp_block_blue");
	public static LampSquareBlock lamp_block_brown = new LampSquareBlock("lamp_block_brown");
	public static LampSquareBlock lamp_block_green = new LampSquareBlock("lamp_block_green");
	public static LampSquareBlock lamp_block_red = new LampSquareBlock("lamp_block_red");
	public static LampSquareBlock lamp_block_black = new LampSquareBlock("lamp_block_black");
	
	public static LampSquareBlock lamp_block_skyscraper_white = new LampSquareBlock("lamp_block_skyscraper_white");
	public static LampSquareBlock lamp_block_skyscraper_light_grey = new LampSquareBlock("lamp_block_skyscraper_light_grey");
	public static LampSquareBlock lamp_block_skyscraper_dark_grey = new LampSquareBlock("lamp_block_skyscraper_dark_grey");
	public static LampSquareBlock lamp_block_skyscraper_black = new LampSquareBlock("lamp_block_skyscraper_black");
	
	public static LampConeBlock lamp_block_cone_white = new LampConeBlock("lamp_block_cone_white");
	public static LampConeBlock lamp_block_cone_orange = new LampConeBlock("lamp_block_cone_orange");
	public static LampConeBlock lamp_block_cone_magenta = new LampConeBlock("lamp_block_cone_magenta");
	public static LampConeBlock lamp_block_cone_light_blue = new LampConeBlock("lamp_block_cone_light_blue");
	public static LampConeBlock lamp_block_cone_yellow = new LampConeBlock("lamp_block_cone_yellow");
	public static LampConeBlock lamp_block_cone_lime = new LampConeBlock("lamp_block_cone_lime");
	public static LampConeBlock lamp_block_cone_pink = new LampConeBlock("lamp_block_cone_pink");
	public static LampConeBlock lamp_block_cone_gray = new LampConeBlock("lamp_block_cone_gray");
	public static LampConeBlock lamp_block_cone_light_gray = new LampConeBlock("lamp_block_cone_light_gray");
	public static LampConeBlock lamp_block_cone_cyan = new LampConeBlock("lamp_block_cone_cyan");
	public static LampConeBlock lamp_block_cone_purple = new LampConeBlock("lamp_block_cone_purple");
	public static LampConeBlock lamp_block_cone_blue = new LampConeBlock("lamp_block_cone_blue");
	public static LampConeBlock lamp_block_cone_brown = new LampConeBlock("lamp_block_cone_brown");
	public static LampConeBlock lamp_block_cone_green = new LampConeBlock("lamp_block_cone_green");
	public static LampConeBlock lamp_block_cone_red = new LampConeBlock("lamp_block_cone_red");
	public static LampConeBlock lamp_block_cone_black = new LampConeBlock("lamp_block_cone_black");
	
	public static LampConeBlock lamp_block_cone_skyscraper_white = new LampConeBlock("lamp_block_cone_skyscraper_white");
	public static LampConeBlock lamp_block_cone_skyscraper_light_grey = new LampConeBlock("lamp_block_cone_skyscraper_light_grey");
	public static LampConeBlock lamp_block_cone_skyscraper_dark_grey = new LampConeBlock("lamp_block_cone_skyscraper_dark_grey");
	public static LampConeBlock lamp_block_cone_skyscraper_black = new LampConeBlock("lamp_block_cone_skyscraper_black");
	
	public static LampOrnateBlock lamp_ornate_skyscraper_white = new LampOrnateBlock("lamp_ornate_skyscraper_white");
	public static LampOrnateBlock lamp_ornate_skyscraper_light_grey = new LampOrnateBlock("lamp_ornate_skyscraper_light_grey");
	public static LampOrnateBlock lamp_ornate_skyscraper_dark_grey = new LampOrnateBlock("lamp_ornate_skyscraper_dark_grey");
	public static LampOrnateBlock lamp_ornate_skyscraper_black = new LampOrnateBlock("lamp_ornate_skyscraper_black");
	
	/**SIGNS**/
	public static DecorativeSigns sign_fire_exit = new DecorativeSigns("sign_fire_exit", 10, 3.25, 1, 0.1, 0);
	public static DecorativeSigns sign_no_smoking = new DecorativeSigns("sign_no_smoking", 3, 5, 5.5, 0.1, 0);
	
	/**GENERIC DECORATIVE**/
	public static BlockWallDecorativeConnecting wall_slats_big_skyscraper_1 = new BlockWallDecorativeConnecting("wall_slats_big_skyscraper_1");
	public static BlockWallDecorativeConnecting wall_slats_big_skyscraper_2 = new BlockWallDecorativeConnecting("wall_slats_big_skyscraper_2");
	public static BlockWallDecorativeConnecting wall_slats_big_wood = new BlockWallDecorativeConnecting("wall_slats_big_wood");
	
	public static BlockWallDecorativeConnecting wall_slats_small_skyscraper_1 = new BlockWallDecorativeConnecting("wall_slats_small_skyscraper_1");
	public static BlockWallDecorativeConnecting wall_slats_small_skyscraper_2 = new BlockWallDecorativeConnecting("wall_slats_small_skyscraper_2");
	public static BlockWallDecorativeConnecting wall_slats_small_wood = new BlockWallDecorativeConnecting("wall_slats_small_wood");
	
	public static GenericDecorativeRotatable16 computer_1 = new GenericDecorativeRotatable16("computer_1", 14, 11, 12);
	public static GenericDecorativeRotatable16 computer_2 = new GenericDecorativeRotatable16("computer_2", 14, 11, 12);
	public static GenericDecorativeRotatable16 computer_3 = new GenericDecorativeRotatable16("computer_3", 14, 11, 12);
	public static GenericDecorativeRotatable16 computer_4 = new GenericDecorativeRotatable16("computer_4", 14, 11, 12);
	
	public static GenericDecorativeBlock plant_1 = new GenericDecorativeBlock("plant_1", FurenikusCities.tabDecorative);
	
	/**OTHER**/
	public static BlockRebar rebar = new BlockRebar("rebar", Material.IRON);
	//TODO Iron girders
	
	/**RESEARCH**/
	public static SkyscraperBlocks rsch_ceramic_tile = new SkyscraperBlocks("rsch_ceramic_tile", FurenikusCities.tabResearch);
	public static SkyscraperBlocks rsch_ceramic_wall = new SkyscraperBlocks("rsch_ceramic_wall", FurenikusCities.tabResearch);
	public static SkyscraperBlocks rsch_decorative = new SkyscraperBlocks("rsch_decorative", FurenikusCities.tabResearch);
	public static SkyscraperBlocks rsch_grill = new SkyscraperBlocks("rsch_grill", FurenikusCities.tabResearch);
	public static PillarBlock research_pillar_1 = new PillarBlock("research_pillar_1", FurenikusCities.tabResearch);
	
	public static BlockFence fence_steel_checkerplate = new BlockFence("fence_steel_checkerplate", Material.IRON);
	public static BlockFence fence_steel_hazard = new BlockFence("fence_steel_hazard", Material.IRON);
	
	public static BlockWalkwayStairs walkway_stairs_steel_checkerplate = new BlockWalkwayStairs("walkway_stairs_steel_checkerplate", Material.IRON);
	public static BlockWalkwayStairs walkway_stairs_steel_hazard = new BlockWalkwayStairs("walkway_stairs_steel_hazard", Material.IRON);
	
	public static void register(IForgeRegistry<Block> registry) {
		blockList.add(skyscraper_blocks_1);
		blockList.add(skyscraper_blocks_2);
		blockList.add(skyscraper_blocks_3);
		blockList.add(skyscraper_blocks_4);
		blockList.add(skyscraper_blocks_5);
		blockList.add(skyscraper_blocks_6);
		blockList.add(skyscraper_blocks_7);
		
		blockList.add(skyscraper_pillar_1);
		blockList.add(skyscraper_pillar_2);
		blockList.add(ornate_pillar_light);
		blockList.add(ornate_pillar_dark);
		blockList.add(floor_pillar);
		
		blockList.add(skyscraper_white_slope);
		blockList.add(skyscraper_light_grey_slope);
		blockList.add(skyscraper_dark_grey_slope);
		blockList.add(skyscraper_black_slope);
		blockList.add(skyscraper_orange_slope);
		blockList.add(skyscraper_magenta_slope);
		blockList.add(skyscraper_light_blue_slope);
		blockList.add(skyscraper_yellow_slope);
		blockList.add(skyscraper_light_green_slope);
		blockList.add(skyscraper_purple_slope);
		blockList.add(skyscraper_cyan_slope);
		blockList.add(skyscraper_pink_slope);
		blockList.add(skyscraper_blue_slope);
		blockList.add(skyscraper_brown_slope);
		blockList.add(skyscraper_green_slope);
		blockList.add(skyscraper_red_slope);
		
		blockList.add(skyscraper_pastel_dark_blue_slope);
		blockList.add(skyscraper_pastel_brown_slope);
		blockList.add(skyscraper_pastel_green_slope);
		blockList.add(skyscraper_pastel_pink_slope);
		blockList.add(skyscraper_pastel_orange_slope);
		blockList.add(skyscraper_pastel_magenta_slope);
		blockList.add(skyscraper_pastel_red_slope);
		blockList.add(skyscraper_pastel_yellow_slope);
		blockList.add(skyscraper_pastel_light_blue_slope);
		blockList.add(skyscraper_pastel_light_green_slope);
		blockList.add(skyscraper_pastel_cyan_slope);
		blockList.add(skyscraper_pastel_purple_slope);
		
		blockList.add(skyscraper_white_steep_slope);
		blockList.add(skyscraper_light_grey_steep_slope);
		blockList.add(skyscraper_dark_grey_steep_slope);
		blockList.add(skyscraper_black_steep_slope);
		blockList.add(skyscraper_orange_steep_slope);
		blockList.add(skyscraper_magenta_steep_slope);
		blockList.add(skyscraper_light_blue_steep_slope);
		blockList.add(skyscraper_yellow_steep_slope);
		blockList.add(skyscraper_light_green_steep_slope);
		blockList.add(skyscraper_purple_steep_slope);
		blockList.add(skyscraper_cyan_steep_slope);
		blockList.add(skyscraper_pink_steep_slope);
		blockList.add(skyscraper_blue_steep_slope);
		blockList.add(skyscraper_brown_steep_slope);
		blockList.add(skyscraper_green_steep_slope);
		blockList.add(skyscraper_red_steep_slope);
		
		blockList.add(skyscraper_pastel_dark_blue_steep_slope);
		blockList.add(skyscraper_pastel_brown_steep_slope);
		blockList.add(skyscraper_pastel_green_steep_slope);
		blockList.add(skyscraper_pastel_pink_steep_slope);
		blockList.add(skyscraper_pastel_orange_steep_slope);
		blockList.add(skyscraper_pastel_magenta_steep_slope);
		blockList.add(skyscraper_pastel_red_steep_slope);
		blockList.add(skyscraper_pastel_yellow_steep_slope);
		blockList.add(skyscraper_pastel_light_blue_steep_slope);
		blockList.add(skyscraper_pastel_light_green_steep_slope);
		blockList.add(skyscraper_pastel_cyan_steep_slope);
		blockList.add(skyscraper_pastel_purple_steep_slope);
		
		blockList.add(rebar);
		blockList.add(white_ornate);
		blockList.add(dark_ornate);
		blockList.add(floor_block);
		blockList.add(plastic_block);
		blockList.add(floor_ceiling_tile);
		blockList.add(wool_ceiling_tile);
		blockList.add(wool_wood_oak);
		blockList.add(wool_wood_spruce);
		blockList.add(wool_wood_birch);
		blockList.add(wool_wood_jungle);
		blockList.add(wool_wood_acacia);
		blockList.add(wool_wood_dark_oak);
		blockList.add(wool_stone);
		
		blockList.add(fence_oak_laminate);
		blockList.add(fence_spruce_laminate);
		blockList.add(fence_birch_laminate);
		blockList.add(fence_jungle_laminate);
		blockList.add(fence_acacia_laminate);
		blockList.add(fence_dark_oak_laminate);
		blockList.add(fence_oak_brick);
		blockList.add(fence_spruce_brick);
		blockList.add(fence_birch_brick);
		blockList.add(fence_jungle_brick);
		blockList.add(fence_acacia_brick);
		blockList.add(fence_dark_oak_brick);
		blockList.add(fence_skyscraper_white);
		blockList.add(fence_skyscraper_light_grey);
		blockList.add(fence_skyscraper_dark_grey);
		blockList.add(fence_skyscraper_black);
		
		blockList.add(walkway_stairs_oak_laminate);
		blockList.add(walkway_stairs_spruce_laminate);
		blockList.add(walkway_stairs_birch_laminate);
		blockList.add(walkway_stairs_jungle_laminate);
		blockList.add(walkway_stairs_acacia_laminate);
		blockList.add(walkway_stairs_dark_oak_laminate);
		blockList.add(walkway_stairs_oak_brick);
		blockList.add(walkway_stairs_spruce_brick);
		blockList.add(walkway_stairs_birch_brick);
		blockList.add(walkway_stairs_jungle_brick);
		blockList.add(walkway_stairs_acacia_brick);
		blockList.add(walkway_stairs_dark_oak_brick);
		blockList.add(walkway_stairs_skyscraper_white);
		blockList.add(walkway_stairs_skyscraper_light_grey);
		blockList.add(walkway_stairs_skyscraper_dark_grey);
		blockList.add(walkway_stairs_skyscraper_black);
		
		blockList.add(ceiling_light_1);
		
		blockList.add(lamp_block_white);
		blockList.add(lamp_block_orange);
		blockList.add(lamp_block_magenta);
		blockList.add(lamp_block_light_blue);
		blockList.add(lamp_block_yellow);
		blockList.add(lamp_block_lime);
		blockList.add(lamp_block_pink);
		blockList.add(lamp_block_gray);
		blockList.add(lamp_block_light_gray);
		blockList.add(lamp_block_cyan);
		blockList.add(lamp_block_purple);
		blockList.add(lamp_block_blue);
		blockList.add(lamp_block_brown);
		blockList.add(lamp_block_green);
		blockList.add(lamp_block_red);
		blockList.add(lamp_block_black);
		
		blockList.add(lamp_block_skyscraper_white);
		blockList.add(lamp_block_skyscraper_light_grey);
		blockList.add(lamp_block_skyscraper_dark_grey);
		blockList.add(lamp_block_skyscraper_black);
		
		blockList.add(lamp_block_cone_white);
		blockList.add(lamp_block_cone_orange);
		blockList.add(lamp_block_cone_magenta);
		blockList.add(lamp_block_cone_light_blue);
		blockList.add(lamp_block_cone_yellow);
		blockList.add(lamp_block_cone_lime);
		blockList.add(lamp_block_cone_pink);
		blockList.add(lamp_block_cone_gray);
		blockList.add(lamp_block_cone_light_gray);
		blockList.add(lamp_block_cone_cyan);
		blockList.add(lamp_block_cone_purple);
		blockList.add(lamp_block_cone_blue);
		blockList.add(lamp_block_cone_brown);
		blockList.add(lamp_block_cone_green);
		blockList.add(lamp_block_cone_red);
		blockList.add(lamp_block_cone_black);
		
		blockList.add(lamp_block_cone_skyscraper_white);
		blockList.add(lamp_block_cone_skyscraper_light_grey);
		blockList.add(lamp_block_cone_skyscraper_dark_grey);
		blockList.add(lamp_block_cone_skyscraper_black);
		
		blockList.add(lamp_ornate_skyscraper_white);
		blockList.add(lamp_ornate_skyscraper_light_grey);
		blockList.add(lamp_ornate_skyscraper_dark_grey);
		blockList.add(lamp_ornate_skyscraper_black);
		
		blockList.add(sign_fire_exit);
		blockList.add(sign_no_smoking);
		
		blockList.add(wall_slats_big_skyscraper_1);
		blockList.add(wall_slats_big_skyscraper_2);
		blockList.add(wall_slats_big_wood);
		
		blockList.add(wall_slats_small_skyscraper_1);
		blockList.add(wall_slats_small_skyscraper_2);
		blockList.add(wall_slats_small_wood);
		
		blockList.add(computer_1);
		blockList.add(computer_2);
		blockList.add(computer_3);
		blockList.add(computer_4);
		blockList.add(plant_1);
		
		researchBlocks.add(rsch_ceramic_tile);
		researchBlocks.add(rsch_ceramic_wall);
		researchBlocks.add(rsch_decorative);
		researchBlocks.add(rsch_grill);
		researchBlocks.add(research_pillar_1);
		researchBlocks.add(fence_steel_checkerplate);
		researchBlocks.add(fence_steel_hazard);
		
		blockList.add(walkway_stairs_steel_checkerplate);
		blockList.add(walkway_stairs_steel_hazard);
		
		for (int i = 0; i < blockList     .size(); i++) { registry.register(blockList.get(i)); }
		for (int i = 0; i < researchBlocks.size(); i++) { registry.register(researchBlocks.get(i)); }

		registry.registerAll(styled_glass, styled_glass_white, styled_glass_fake_off, styled_glass_white_fake_off, styled_glass_fake_on, styled_glass_white_fake_on);
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		//base stuff
		for (int i = 0; i < blockList     .size(); i++) { registry.register(new CitiesItemBlock(blockList     .get(i)).setRegistryName(blockList     .get(i).getRegistryName())); }
		for (int i = 0; i < researchBlocks.size(); i++) { registry.register(new CitiesItemBlock(researchBlocks.get(i)).setRegistryName(researchBlocks.get(i).getRegistryName())); }
		
		registry.register(new CitiesItemBlock(styled_glass).setRegistryName(styled_glass.getRegistryName()));
		registry.register(new CitiesItemBlock(styled_glass_white).setRegistryName(styled_glass_white.getRegistryName()));
		registry.register(new CitiesItemBlock(styled_glass_fake_off).setRegistryName(styled_glass_fake_off.getRegistryName()));
		registry.register(new CitiesItemBlock(styled_glass_white_fake_off).setRegistryName(styled_glass_white_fake_off.getRegistryName()));
		registry.register(new CitiesItemBlock(styled_glass_fake_on).setRegistryName(styled_glass_fake_on.getRegistryName()));
		registry.register(new CitiesItemBlock(styled_glass_white_fake_on).setRegistryName(styled_glass_white_fake_on.getRegistryName()));
	}
	
	public static void registerModels() {
		//base stuff
		for (int i = 0; i < blockList     .size(); i++) { blockList     .get(i).initModel(); }
		for (int i = 0; i < researchBlocks.size(); i++) { researchBlocks.get(i).initModel(); }
		
		styled_glass.initModel();
		styled_glass_white.initModel();
		styled_glass_fake_off.initModel();
		styled_glass_white_fake_off.initModel();
		styled_glass_fake_on.initModel();
		styled_glass_white_fake_on.initModel();
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModel(Block block) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(FurenikusCities.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
