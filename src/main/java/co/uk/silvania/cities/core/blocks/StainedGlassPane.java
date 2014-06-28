package co.uk.silvania.cities.core.blocks;

import java.util.List;

import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class StainedGlassPane extends BlockPane {

	public StainedGlassPane(int par1, String par2Str, String par3Str, boolean par5) {
		super(par1, par2Str, par3Str, Material.glass, par5);
		this.setCreativeTab(FlenixCities_Core.tabCity);
	}
	
    public int getRenderBlockPass() {
        return 1;
    }
}

