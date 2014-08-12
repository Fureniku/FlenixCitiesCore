package co.uk.silvania.cities.core.blocks;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import co.uk.silvania.cities.core.FlenixCities_Core;

public class StyledGlassPane2 extends BlockPane {
	
	protected StyledGlassPane2(String par2Str, String par3Str, Material par4Material, boolean par5) {
		super(par2Str, par3Str, Material.glass, par5);
		this.setCreativeTab(FlenixCities_Core.tabCity);
	}
	
	@Override
    public int getRenderBlockPass() {
        return 1;
    }
}

