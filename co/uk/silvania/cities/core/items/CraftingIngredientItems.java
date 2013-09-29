package co.uk.silvania.cities.core.items;

import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class CraftingIngredientItems extends Item {

	public CraftingIngredientItems(int id, int stack) {
		super(id);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.maxStackSize = stack;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
}