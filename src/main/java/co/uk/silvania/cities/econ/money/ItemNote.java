package co.uk.silvania.cities.econ.money;

import co.uk.silvania.cities.core.FlenixCities_Core;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNote extends Item {
	
    public double moneyValue;

    public ItemNote(double money) {
        super();
        this.moneyValue = money;
        this.setMaxStackSize(50);
        this.setCreativeTab(FlenixCities_Core.tabEcon);
    }

    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

    public double getMoneyValue() {
    	return moneyValue;
    }

}