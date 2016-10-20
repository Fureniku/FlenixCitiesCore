package com.silvaniastudios.cities.core.blocks;

import com.silvaniastudios.cities.core.FlenixCities_Core;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

public class StainedGlassPane extends BlockPane {

	public StainedGlassPane(String par2Str, String par3Str, boolean par5) {
		super(par2Str, par3Str, Material.glass, par5);
		this.setCreativeTab(FlenixCities_Core.tabCity);
	}
	
	@Override
    public int getRenderBlockPass() {
        return 1;
    }
}

