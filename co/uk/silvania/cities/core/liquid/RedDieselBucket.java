package co.uk.silvania.cities.core.liquid;

import co.uk.silvania.cities.core.CoreBlocks;
import co.uk.silvania.cities.core.FlenixCities_Core;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RedDieselBucket extends ItemBucket {

    public RedDieselBucket(int id) {
        super(id, CoreBlocks.blockRedDiesel.blockID);
        this.setCreativeTab(FlenixCities_Core.tabCity);
        this.setMaxStackSize(1);
    }
    
    public ItemStack fillCustomBucket(World world, int x, int y, int z) {
    		return null;
    }
    
    public void registerIcons(IconRegister iconRegister) {
    	itemIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":redDieselBucket");
    }
}
