package co.uk.silvania.cities.core.blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.CoreBlocks;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LightingRotateBlocks extends Block {
	
	public LightingRotateBlocks() {
		super(Material.glass);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(0.3F);
		this.setLightLevel(1.0F);
	}
	
	
	@SideOnly(Side.CLIENT)
	private IIcon icons;
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (CoreBlocks.lightingBlocks.getUnlocalizedName().substring(5)));
		sideIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":ceilingTile");	
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return icons;
		}
		return sideIcon;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 4));
        list.add(new ItemStack(item, 1, 8));
        list.add(new ItemStack(item, 1, 12));
	}
	
	@Override
	public int getRenderType() {
		return ClientProxy.lightBlockRotateRenderID;
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
    	}
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    	int placedMeta = meta;
        if (side == 2 && world.isSideSolid(x, y, z + 1, NORTH, true)) {
        	placedMeta += 0;
        }

        if (side == 3 && world.isSideSolid(x, y, z - 1, SOUTH, true)) {
        	placedMeta += 2;
        }

        if (side == 4 && world.isSideSolid(x + 1, y, z, WEST, true)) {
        	placedMeta += 3; //2
        }

        if (side == 5 && world.isSideSolid(x - 1, y, z, EAST, true)) {
        	placedMeta += 1; //1
        }

        return placedMeta;
    }
}
