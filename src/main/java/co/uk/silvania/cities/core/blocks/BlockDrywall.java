package co.uk.silvania.cities.core.blocks;

import co.uk.silvania.cities.core.FlenixCities_Core;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

public class BlockDrywall extends BlockPane {

	public BlockDrywall(int id, String par2Str, String par3Str, boolean par5) {
		super(id, par2Str, par3Str, Material.rock, par5);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		//this.setLightOpacity(10);
	}
}
