package co.uk.silvania.cities.core.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.FlenixCities_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LightingBlocks extends Block {
	
	public LightingBlocks() {
		super(Material.glass);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(0.3F);
		this.setLightLevel(1.0F);
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[16];

		for(int i = 0; i < 6; i++) {
			icons[i] = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
			sideIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":ceilingTile");	
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return icons[meta];
		}
		if (side == 1) {
			return icons[meta];
		}
		return sideIcon;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
		for (int meta = 0; meta < 5; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
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
    
    @Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		int meta = block.getBlockMetadata(x, y, z);
    	if (meta == 0) {
        	this.setBlockBounds(0.0F, 0.96875F, 0.0F, 1.0F, 1.0F, 1.0F);
    	} else if (meta == 1) {
        	this.setBlockBounds(0.25F, 0.875F, 0.25F, 0.75F, 1.0F, 0.75F);
    	} else if (meta == 2) {
        	this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
    	} else if (meta == 3) {
        	this.setBlockBounds(0.375F, 0.875F, 0.375F, 0.625F, 1.0F, 0.625F);
    	} else if (meta == 4) {
        	this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
    	/*} else if (meta == 5) {
        	this.setBlockBounds(F, F, F, F, F, F);
    	} else if (meta == 6) {
        	this.setBlockBounds(F, F, F, F, F, F);    		
    	} else if (meta == 7) {
        	this.setBlockBounds(F, F, F, F, F, F);
    	} else if (meta == 8) {
        	this.setBlockBounds(F, F, F, F, F, F);
    	} else if (meta == 9) {
        	this.setBlockBounds(F, F, F, F, F, F);
    	} else if (meta == 10) {
        	this.setBlockBounds(F, F, F, F, F, F);
    	} else if (meta == 11) {
        	this.setBlockBounds(F, F, F, F, F, F);
    	} else if (meta == 12) {
        	this.setBlockBounds(F, F, F, F, F, F);
    	} else if (meta == 13) {
        	this.setBlockBounds(F, F, F, F, F, F);
    	} else if (meta == 14) {
        	this.setBlockBounds(F, F, F, F, F, F);*/
    	} else
        	this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}
}
