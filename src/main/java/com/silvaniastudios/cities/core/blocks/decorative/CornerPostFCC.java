package com.silvaniastudios.cities.core.blocks.decorative;

import java.util.List;

import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CornerPostFCC extends DecorativeBlockFCC {

	public CornerPostFCC(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return ClientProxy.cornerPostRenderID;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
		int meta = item.getItemDamage();
		
		int itemDamage = 0;
		int direction = MathHelper.floor_double((entity.rotationYaw * 4F) / 360F + 0.5D) & 3;
		if (direction < 3) { direction++; } else { direction = 0; }
		if (meta <= 11) {
			itemDamage = direction + meta;
		} else {
			itemDamage = meta;
		}
		
		world.setBlockMetadataWithNotify(x, y, z, itemDamage, 3);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 4));
		list.add(new ItemStack(item, 1, 8));
		list.add(new ItemStack(item, 1, 12));
		list.add(new ItemStack(item, 1, 13));
		list.add(new ItemStack(item, 1, 14));
		list.add(new ItemStack(item, 1, 15));
	}

	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
