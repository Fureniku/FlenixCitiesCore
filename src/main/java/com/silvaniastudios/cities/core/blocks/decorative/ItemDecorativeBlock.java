/*package com.silvaniastudios.cities.core.blocks.decorative;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemDecorativeBlock extends ItemBlock {
	
	public ItemDecorativeBlock(Block block) {
		super(block);
		this.hasSubtypes = true;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack item) {
		String name = "";
		switch (item.getItemDamage()) {
			case 0: { name = "0"; break; }
			case 1: { name = "1"; break; }
			case 2: { name = "2"; break; }
			case 3: { name = "3"; break; }
			case 4: { name = "4"; break; }
			case 5: { name = "5"; break; }
			case 6: { name = "6"; break; }
			case 7: { name = "7"; break; }
			case 8: { name = "8"; break; }
			case 9: { name = "9"; break; }
			case 10: { name = "10"; break; }
			case 11: { name = "11"; break; }
			case 12: { name = "12"; break; }
			case 13: { name = "13"; break; }
			case 14: { name = "14"; break; }
			case 15: { name = "15"; break; }
		}
		return "";
	}
	
	@Override
    public String getItemStackDisplayName(ItemStack item) {
		Block block = Block.getBlockFromItem(item.getItem());
		
		
		if (block instanceof  BlockFenceFCC) {  return StatCollector.translateToLocal("material." + ((BlockFenceFCC) block).texture) + " " +  StatCollector.translateToLocal("blockType.fence"); }
		if (block instanceof  Slope45FCC) {     return StatCollector.translateToLocal("material." + ((Slope45FCC) block).texture) + " " +  StatCollector.translateToLocal("blockType.slope45"); }
		if (block instanceof  BlockStairsFCC) { return StatCollector.translateToLocal("material." + ((BlockStairsFCC) block).texture) + " " +  StatCollector.translateToLocal("blockType.stairs"); }
		//if (block instanceof  Panel) { return StatCollector.translateToLocal("material." + ((Panel) block).texture) + " " +  StatCollector.translateToLocal("blockType.panel"); }
		//if (block instanceof  Cover) { return StatCollector.translateToLocal("material." + ((Cover) block).texture) + " " +  StatCollector.translateToLocal("blockType.cover"); }
		
		if (block instanceof WalkwayFenceFCC) {
			return StatCollector.translateToLocal("material." + ((DecorativeBlockFCC) block).texture) + " " + StatCollector.translateToLocal("blockType." + ((WalkwayFenceFCC) block).getBlockNameFromMeta(item.getItemDamage()));
		}
		
		if (block instanceof Slope225HorizontalAFCC) { return StatCollector.translateToLocal("material." + ((Slope225HorizontalAFCC) block).texture) + " " +  StatCollector.translateToLocal("blockType.slope225a"); }
		if (block instanceof Slope225HorizontalBFCC) { return StatCollector.translateToLocal("material." + ((Slope225HorizontalBFCC) block).texture) + " " +  StatCollector.translateToLocal("blockType.slope225b"); }
		if (block instanceof Slope225VerticalFCC) {
			if (item.getItemDamage() <= 7) {
				return StatCollector.translateToLocal("material." + ((Slope225VerticalFCC) block).texture) + " " +  StatCollector.translateToLocal("blockType.slope225Verticala");
			} else {
				return StatCollector.translateToLocal("material." + ((Slope225VerticalFCC) block).texture) + " " +  StatCollector.translateToLocal("blockType.slope225Verticalb");
			}
		}
		if (block instanceof DecorativeBlockFCC) { return StatCollector.translateToLocal("material." + ((DecorativeBlockFCC) block).texture) + " " + StatCollector.translateToLocal("blockType." + ((DecorativeBlockFCC) block).blockType); }
		
		return super.getItemStackDisplayName(item);
    }
}*/
