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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LightingBlocks extends Block {
	
	public LightingBlocks() {
		super(Material.glass);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setHardness(0.3F);
		this.setLightLevel(1.0F);
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
    	}
		this.setBlockBoundsForItemRender();
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0) {
			return AxisAlignedBB.getBoundingBox(x, y + 0.96875F, z, x + 1.0F, y + 1.0F, z + 1.0F);
    	} else if (meta == 1) {
    		return AxisAlignedBB.getBoundingBox(x + 0.25F, y + 0.875F, z + 0.25F, x + 0.75F, y + 1.0F, z + 0.75F);
    	} else if (meta == 2) {
    		return AxisAlignedBB.getBoundingBox(x + 0.25F, y + 0.0F, z + 0.25F, x + 0.75F, y + 1.0F, z + 0.75F);
    	} else if (meta == 3) {
    		return AxisAlignedBB.getBoundingBox(x + 0.375F, y + 0.875F, z + 0.375F, x + 0.625F, y + 1.0F, z + 0.625F);
    	} else
    		return AxisAlignedBB.getBoundingBox(x + 0.375F, y + 0.0F, z + 0.375F, x + 0.625F, y + 1.0F, z + 0.625F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return ClientProxy.lightBlockRenderID;
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
	public IIcon icon;
	@SideOnly(Side.CLIENT)
	public IIcon sideIcon;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)));
		sideIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":ceilingTile");	
	}
	
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return icon;
		}
		return sideIcon;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
		for (int meta = 0; meta < 5; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0 || meta == 1 || meta == 3) {
			return world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN, true);
		} else {
			return world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN, true) || world.isSideSolid(x, y - 1, z, ForgeDirection.UP,  true);
		}
    }
    
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0 || meta == 1 || meta == 3) {
			if (world.getBlock(x, y + 1, z).isAir(world, x, y + 1, z)) {
				this.breakBlock(world, x, y, z, block, meta);
			}
		} else {
			if (world.getBlock(x, y + 1, z).isAir(world, x, y + 1, z) && world.getBlock(x, y - 1, z).isAir(world, x, y + 1, z)) {
				this.breakBlock(world, x, y, z, block, meta);
			}
		}
    }
}
