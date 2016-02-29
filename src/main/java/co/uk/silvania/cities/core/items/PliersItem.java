package co.uk.silvania.cities.core.items;

import java.util.Set;

import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemTool;

public class PliersItem extends ItemTool {

	public PliersItem() {
		super(1.0F, ToolMaterial.IRON, null);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setMaxDamage(256);
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

}
