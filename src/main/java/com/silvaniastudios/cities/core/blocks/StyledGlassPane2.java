package com.silvaniastudios.cities.core.blocks;

import com.silvaniastudios.cities.core.FlenixCities_Core;

import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.material.Material;

public class StyledGlassPane2 extends BlockStainedGlassPane {
	
	protected StyledGlassPane2(String par2Str, String par3Str, Material par4Material, boolean par5) {
		super();
		this.setCreativeTab(FlenixCities_Core.tabCity);
	}
	
	@Override
    public int getRenderBlockPass() {
        return 1;
    }
}

