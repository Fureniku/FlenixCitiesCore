package com.silvaniastudios.cities.core.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class CitiesBlockMetaBase extends CitiesBlockBase implements IMetaBlockName {
	
	public static PropertyEnum<EnumMeta> enumMeta;

	public CitiesBlockMetaBase(String name, PropertyEnum<EnumMeta> enumMeta, Material mat) {
		super(name, mat);
		CitiesBlockMetaBase.enumMeta = enumMeta;
		setDefaultState(this.blockState.getBaseState().withProperty(enumMeta, EnumMeta.id0));
	}
    
	@Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (EnumMeta meta: EnumMeta.values()) {
            items.add(new ItemStack(this, 1, meta.getMetadata()));
        }
    }
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(state.getBlock(), 1, this.getMetaFromState(state));
    }

	@Override
	public String getSpecialName(ItemStack stack) {
		return null;
	}
}