package com.silvaniastudios.cities.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.silvaniastudios.cities.core.FlenixCities_Core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RebarBlock extends Block {
	
	public RebarBlock() {
		super(Material.iron);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(1.8F);
		this.setBlockBounds(0.45F, 0.0F, 0.45F, 0.55F, 1.0F, 0.55F);
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon side1;
	@SideOnly(Side.CLIENT)
	public IIcon side2;
	@SideOnly(Side.CLIENT)
	public IIcon side3;
	@SideOnly(Side.CLIENT)
	public IIcon side4;
	@SideOnly(Side.CLIENT)
	public IIcon top;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		side1 = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + 1);
		side2 = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + 2);
		side3 = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + 3);
		side4 = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + 4);
		top = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + 5);
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		if (side == 2) {
			return side1;
		}
		if (side == 3) {
			return side2;
		}
		if (side == 4) {
			return side3;
		}
		if (side == 5) {
			return side4;
		}
		return top;
	}

}
