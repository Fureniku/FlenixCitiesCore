package co.uk.silvania.cities.core.items.econ;

import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemNote20 extends ItemNote {

	public ItemNote20(int id) {
		super(id);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
		this.setMaxStackSize(50);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
    @Override
    public double getMoneyValue() {
        return 20.00;
    }
}
