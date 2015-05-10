package co.uk.silvania.cities.core.blocks;

import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.material.Material;
import co.uk.silvania.cities.core.FlenixCities_Core;

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

