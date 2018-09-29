package com.silvaniastudios.cities.core.items;

import com.silvaniastudios.cities.core.blocks.IMetaBlockName;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class CitiesItemBlock extends ItemBlock {

	public CitiesItemBlock(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int dmg) {
		return dmg;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if (stack.getItem() instanceof IMetaBlockName) {
			return super.getUnlocalizedName(stack) + "." + ((IMetaBlockName) this.block).getSpecialName(stack);
		}
		return super.getUnlocalizedName(stack);
	}
}