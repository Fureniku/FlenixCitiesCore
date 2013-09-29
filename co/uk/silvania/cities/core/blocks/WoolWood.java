package co.uk.silvania.cities.core.blocks;

import java.util.List;

import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class WoolWood extends Block {

	public WoolWood(int id) {
		super(id, Material.rock);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(2.2F);
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
		if (side == 1) {
			return Block.cloth.getIcon(side, metadata);
		} 
		return Block.planks.getIcon(side, 0);
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
		for (int var4 = 0; var4 < 16; ++var4) {
			list.add(new ItemStack(par1, 1, var4));
		}
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return null;
				
	}
}