package co.uk.silvania.cities.core.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.CoreBlocks;
import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LightingRotateBlocks extends Block {
	
	public LightingRotateBlocks(int id) {
		super(id, Material.glass);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(0.3F);
		this.setLightValue(1.0F);
	}
	
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	//@SideOnly(Side.CLIENT)
	//private Icon sideIcon;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[16];

		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (CoreBlocks.lightingBlocks.getUnlocalizedName().substring(5)) + "0");
			//sideIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":ceilingTile");	
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(par1, 1, 0));
        list.add(new ItemStack(par1, 1, 4));
        list.add(new ItemStack(par1, 1, 8));
        list.add(new ItemStack(par1, 1, 12));
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
    @Override
    public int damageDropped(int meta) {
    	return meta;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }
    
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack)
	{
	    int blockSet = world.getBlockMetadata(x, y, z) / 4;
	    int direction = MathHelper.floor_double((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
	    int newMeta = (blockSet * 4) + direction;
	    world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
	}
    
    @Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		int meta = block.getBlockMetadata(x, y, z);
    	if (meta == 0) {
        	this.setBlockBounds(0.375F, 0.0F, 0.875F, 0.625F, 1.0F, 1.0F);
    	} else if (meta == 1) {
        	this.setBlockBounds(0.0F, 0.0F, 0.375F, 0.125F, 1.0F, 0.625F);
    	} else if (meta == 2) {
        	this.setBlockBounds(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 0.125F);
    	} else if (meta == 3) {
        	this.setBlockBounds(0.875F, 0.0F, 0.325F, 1.0F, 1.0F, 0.625F);
    	} else if (meta == 4) {
        	this.setBlockBounds(0.375F, 0.25F, 0.875F, 0.625F, 0.75F, 1.0F);
    	} else if (meta == 5) {
    		this.setBlockBounds(0.0F, 0.25F, 0.375F, 0.125F, 0.75F, 0.625F);
    	} else if (meta == 6) {
    		this.setBlockBounds(0.375F, 0.25F, 0.0F, 0.625F, 0.75F, 0.125F);	
    	} else if (meta == 7) {
        	this.setBlockBounds(0.875F, 0.25F, 0.325F, 1.0F, 0.75F, 0.625F);
    	} else if (meta == 8) {
        	this.setBlockBounds(0.0F, 0.0F, 0.9375F, 1.0F, 1.0F, 1.0F);
    	} else if (meta == 9) {
    		this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0625F, 1.0F, 1.0F);
    	} else if (meta == 10) {
    		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);	
    	} else if (meta == 11) {
        	this.setBlockBounds(0.9375F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    	} else if (meta == 12) {
        	this.setBlockBounds(0.25F, 0.25F, 0.9375F, 0.75F, 0.75F, 1.0F);
    	} else if (meta == 13) {
    		this.setBlockBounds(0.0F, 0.25F, 0.25F, 0.0625F, 0.75F, 0.75F);
    	} else if (meta == 14) {
    		this.setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.0625F);	
    	} else if (meta == 15) {
        	this.setBlockBounds(0.9375F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
    	} else
        	this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}
}
