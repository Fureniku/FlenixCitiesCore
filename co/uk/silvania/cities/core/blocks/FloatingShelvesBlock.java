package co.uk.silvania.cities.core.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.blocks.entity.TileEntityFloatingShelves;


public class FloatingShelvesBlock extends BlockContainer {
	

	public FloatingShelvesBlock(int id) {
		super(id, Material.iron);
		this.setHardness(1.0F);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
		this.setLightOpacity(0);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityFloatingShelves();
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float j, float k, float l) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            player.openGui(FlenixCities_Core.instance, 1, world, x, y, z);
        return true;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

		if (l == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 0);
		}

		if (l == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 0);
		}

		if (l == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 0);
		}

		if (l == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 0);
		}
	}	
}