package com.silvaniastudios.cities.core.blocks.decorative;

import com.silvaniastudios.cities.core.FlenixCities;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;

public class BlockFenceFCC extends BlockFence {
	
	String texture;

	public BlockFenceFCC() {
		super(Material.ROCK, MapColor.BLACK);
		this.setHardness(1.0F);
		this.setCreativeTab(FlenixCities.tabDecorative);
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
