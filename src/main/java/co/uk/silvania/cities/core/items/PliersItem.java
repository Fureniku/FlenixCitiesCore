package co.uk.silvania.cities.core.items;

import java.util.Set;

import co.uk.silvania.cities.core.FlenixCities_Core;
import net.minecraft.item.ItemTool;

public class PliersItem extends ItemTool {

	public PliersItem() {
		super(1.0F, ToolMaterial.IRON, null);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setMaxDamage(256);
		this.setMaxStackSize(1);
	}

}
