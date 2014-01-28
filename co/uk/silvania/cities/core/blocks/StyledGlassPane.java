package co.uk.silvania.cities.core.blocks;

import java.util.List;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StyledGlassPane extends BlockPane {
	
	public StyledGlassPane(int par1, String par2Str, String par3Str, boolean par5) {
		super(par1, par2Str, par3Str, Material.glass, par5);
		this.setCreativeTab(FlenixCities_Core.tabCity);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[16];

		for(int i = 0; i < 12; i++) {
			icons[i] = iconRegister.registerIcon(FlenixCities_Core.modid + ":styledGlass" + i);
		}
	}
	
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    @Override
    public int damageDropped(int meta) {
    	return meta;
    }
    
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return icons[par2];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
		for (int var4 = 0; var4 < 12; ++var4) {
			list.add(new ItemStack(par1, 1, var4));
		}
	}
}
