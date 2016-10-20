package com.silvaniastudios.cities.core.blocks.decorative;

import com.silvaniastudios.cities.core.FlenixCities_Core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.StatCollector;

public class BlockFenceFCC extends BlockFence {
	
	String texture;

	public BlockFenceFCC(String textureName) {
		super(textureName, Material.rock);
		this.texture = textureName;
		this.setHardness(1.0F);
		this.setCreativeTab(FlenixCities_Core.tabDecorative);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + DecorativeBlockFCC.getTextureFromID(texture));
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
