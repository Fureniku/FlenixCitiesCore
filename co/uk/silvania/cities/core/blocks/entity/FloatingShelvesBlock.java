package co.uk.silvania.cities.core.blocks.entity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.econ.EconUtils;


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
        TileEntityFloatingShelves tileEntity = (TileEntityFloatingShelves) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
        	String ownerName = tileEntity.ownerName;
        	String userName = player.username;
        	System.out.println("Final balance: " + EconUtils.getInventoryCash(player));
        	if (userName.equalsIgnoreCase(ownerName)) {
                player.openGui(FlenixCities_Core.instance, 1, world, x, y, z);
        	} else {
                player.openGui(FlenixCities_Core.instance, 1, world, x, y, z);
        	}
        }
        return true;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack par6ItemStack) {
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 0, 0);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 1, 0);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 0);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 0);
		}
		
		if (entity instanceof EntityPlayer) {
			System.out.println("It's a player!");
			EntityPlayer player = (EntityPlayer) entity;
			String name = player.username;
			System.out.println("His name is " + name);
			TileEntityFloatingShelves tileEntity = (TileEntityFloatingShelves) world.getBlockTileEntity(x, y, z);
			tileEntity.ownerName = name;
			System.out.println("And we've possibly set it in the TE? " + tileEntity.ownerName);
		}
	}	
}