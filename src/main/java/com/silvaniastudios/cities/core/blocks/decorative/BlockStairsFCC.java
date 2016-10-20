package com.silvaniastudios.cities.core.blocks.decorative;

import java.util.List;

import com.silvaniastudios.cities.core.FlenixCities_Core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockStairsFCC extends BlockStairs {
	
	String texture;
	
	public BlockStairsFCC(Block block, String textureName) {
		super(block, 0);
		this.texture = textureName;
		this.setHardness(1.0F);
		this.setCreativeTab(FlenixCities_Core.tabDecorative);
		this.useNeighborBrightness = true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + DecorativeBlockFCC.getTextureFromID(texture));
	}
	
	@SideOnly(Side.CLIENT) @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return blockIcon;
    }
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
