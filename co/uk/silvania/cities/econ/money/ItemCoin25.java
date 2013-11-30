package co.uk.silvania.cities.econ.money;

import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemCoin25 extends ItemCoin {

	public ItemCoin25(int id) {
		super(id);
		this.setMaxStackSize(50);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
	}
	

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}


	@Override
	public double getMoneyValue() {
		return 0.25;
	}
}
