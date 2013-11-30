package co.uk.silvania.cities.core.blocks;

import java.util.Random;

import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;

public class SapphireOre extends BlockOre {

	public SapphireOre(int id) {
		super(id);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(1.8F);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	@Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return CoreItems.sapphireItem.itemID;
    }
}
