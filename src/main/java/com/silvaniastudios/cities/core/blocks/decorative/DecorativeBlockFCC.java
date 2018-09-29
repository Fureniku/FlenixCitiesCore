/*package com.silvaniastudios.cities.core.blocks.decorative;

import com.silvaniastudios.cities.core.FlenixCities_Core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;

public class DecorativeBlockFCC extends Block {
	
	String blockType;
	String texture;

	public DecorativeBlockFCC(String textureName, String blockType) {
		super(Material.rock);
		this.blockType = blockType;
		this.texture = textureName;
		this.setHardness(1.0F);
		this.setCreativeTab(FlenixCities_Core.tabDecorative);
		this.useNeighborBrightness = true;
		this.opaque = true;
        this.lightOpacity = 255;
	}
	
	@Override public boolean renderAsNormalBlock() { return false; }
	@Override public boolean isOpaqueCube() { return false; }
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + getTextureFromID(texture));
	}
	
	public String getBlockNameFromMeta(int meta) {
		return "";
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
	
	public static String getTextureFromID(String id) {
		if (id.equalsIgnoreCase("white")) { return "skyscraperBlocks0"; }
		if (id.equalsIgnoreCase("lightGrey")) { return "skyscraperBlocks1"; }
		if (id.equalsIgnoreCase("darkGrey")) { return "skyscraperBlocks2"; }
		if (id.equalsIgnoreCase("black")) { return "skyscraperBlocks3"; }
		if (id.equalsIgnoreCase("orange")) { return "skyscraperBlocks20"; }
		if (id.equalsIgnoreCase("magenta")) { return "skyscraperBlocks21"; }
		if (id.equalsIgnoreCase("lightBlue")) { return "skyscraperBlocks22"; }
		if (id.equalsIgnoreCase("yellow")) { return "skyscraperBlocks23"; }
		if (id.equalsIgnoreCase("pink")) { return "skyscraperBlocks33"; }
		if (id.equalsIgnoreCase("cyan")) { return "skyscraperBlocks32"; }
		if (id.equalsIgnoreCase("purple")) { return "skyscraperBlocks31"; }
		if (id.equalsIgnoreCase("darkGreen")) { return "skyscraperBlocks30"; }
		if (id.equalsIgnoreCase("blue")) { return "skyscraperBlocks40"; }
		if (id.equalsIgnoreCase("brown")) { return "skyscraperBlocks41"; }
		if (id.equalsIgnoreCase("green")) { return "skyscraperBlocks42"; }
		if (id.equalsIgnoreCase("red")) { return "skyscraperBlocks43"; }
		return "skyscraperBlocks0";
	}
}*/
