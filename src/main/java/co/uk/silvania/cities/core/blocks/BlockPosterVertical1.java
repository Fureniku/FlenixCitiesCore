package co.uk.silvania.cities.core.blocks;

import java.util.List;

import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPosterVertical1 extends Block {

	public BlockPosterVertical1(int id) {
		super(id, Material.cloth);
		//this.setCreativeTab(FlenixCities_Core.tabPosters);
		this.setHardness(0.2F);
		this.setBlockBounds(0.95F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
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
	public int getRenderType() {
		return ClientProxy.PosterRenderID;
	}
	
	@SideOnly(Side.CLIENT)
	private Icon poster1;
	@SideOnly(Side.CLIENT)
	private Icon poster2;
	@SideOnly(Side.CLIENT)
	private Icon poster3;
	@SideOnly(Side.CLIENT)
	private Icon poster4;	

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		poster1 = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + 1);
		poster2 = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + 2);
		poster3 = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + 3);
		poster4 = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + (this.getUnlocalizedName().substring(5)) + 4);
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (meta == 0 || meta == 1 || meta == 2 || meta == 3) {
			return poster1;
		} else if (meta == 4 || meta == 5 || meta == 6 || meta == 7) {
			return poster2;
		} else if (meta == 8 || meta == 9 || meta == 10 || meta == 11) {
			return poster3;
		} else return poster4;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		int meta = block.getBlockMetadata(x, y, z);
    	if (meta == 0 || meta == 4 || meta == 8 || meta == 12) {
        	this.setBlockBounds(0.0F, 0.0F, 0.98F, 1.0F, 1.5F, 1.00F);
    	} else if (meta == 1 || meta == 5 || meta == 9 || meta == 13) {
        	this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.02F, 1.5F, 1.0F);
    	} else if (meta == 2 || meta == 6 || meta == 10 || meta == 14) {
        	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.5F, 0.02F);
    	} else
        	this.setBlockBounds(0.98F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
	}

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity) {
       	super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
    }

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(par1, 1, 0));
        list.add(new ItemStack(par1, 1, 4));
        list.add(new ItemStack(par1, 1, 8));
        list.add(new ItemStack(par1, 1, 12));
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack) {
	    int blockSet = world.getBlockMetadata(x, y, z) / 4;
	    int direction = MathHelper.floor_double((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
	    int newMeta = (blockSet * 4) + direction;
	    world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
	}
}
