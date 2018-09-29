/*package com.silvaniastudios.cities.core.blocks.decorative;

import java.util.List;

import com.silvaniastudios.cities.core.FlenixCities_Core;
import com.silvaniastudios.cities.core.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Slope225VerticalFCC extends Block {

	String texture;
	
	public Slope225VerticalFCC(String textureName) {
		super(Material.rock);
		this.texture = textureName;
		this.setHardness(1.0F);
		this.setCreativeTab(FlenixCities_Core.tabDecorative);
		this.useNeighborBrightness = true;
		this.opaque = true;
        this.lightOpacity = 255;
	}
	
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	if (meta == 0 || meta == 4) { setBlockBounds(0.5F, 0.0F,  0.0F,  1.0F, 1.0F, 1.0F); }
    	if (meta == 1 || meta == 5) { setBlockBounds(0.0F, 0.0F,  0.0F,  0.5F, 1.0F, 1.0F); }
    	if (meta == 2 || meta == 6) { setBlockBounds(0.0F, 0.0F,  0.5F,  1.0F, 1.0F, 1.0F); }
    	if (meta == 3 || meta == 7) { setBlockBounds(0.0F, 0.0F,  0.0F,  1.0F, 1.0F, 0.5F); }
    	if (meta > 7) { setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F); }
    }
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity) {
		boolean hasCollision = false;
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 0 || meta == 4) {
			setBlockBounds(0.5F, 0.0F,  0.0F,  1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		} else if (meta == 1 || meta == 5) {
			setBlockBounds(0.0F, 0.0F,  0.0F,  0.5F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		} else if (meta == 2 || meta == 6) {
			setBlockBounds(0.0F, 0.0F,  0.5F,  1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		} else if (meta == 3 || meta == 7) {
			setBlockBounds(0.0F, 0.0F,  0.0F,  1.0F, 1.0F, 0.5F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		} else {
			setBlockBounds(0.0F, 0.0F,  0.0F,  1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		setBlockBoundsForItemRender();
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.slope225VerticalRenderID;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + DecorativeBlockFCC.getTextureFromID(texture));
	}
	
	public void onBlockPlacedBy(World block, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
        int rot = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int meta = block.getBlockMetadata(x, y, z);
        int add = 0;
        if (item.getItemDamage() >= 8) {
        	add = 8;
        }

        System.out.println("Meta PRE: " + block.getBlockMetadata(x, y, z));
        
        if (rot == 0) {
        	block.setBlockMetadataWithNotify(x, y, z, (2 | meta) + add, 2); 
        }

        if (rot == 1) {
        	block.setBlockMetadataWithNotify(x, y, z, (1 | meta) + add, 2);
        }

        if (rot == 2) {
        	block.setBlockMetadataWithNotify(x, y, z, (3 | meta) + add, 2);
        }

        if (rot == 3) {
        	block.setBlockMetadataWithNotify(x, y, z, (0 | meta) + add, 2);
        }
        System.out.println("Meta POST: " + block.getBlockMetadata(x, y, z));
    }
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        return side != 0 && (side == 1 || (double)hitY <= 0.5D) ? meta : meta | 4;
    }
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 8));
	}
}*/
