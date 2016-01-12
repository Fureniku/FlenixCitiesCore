package co.uk.silvania.cities.econ.store.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.network.AdminShopPricePacket;
import co.uk.silvania.cities.network.ServerBalancePacket;

public class AdminShopBlock extends BlockContainer implements IStoreBlock {

	float minX = 0.0F;
	float minY = 0.0F;
	float minZ = 0.0F;
	float maxX = 1.0F;
	float maxY = 1.0F;
	float maxZ = 1.0F;
	
	public AdminShopBlock() {
		super(Material.iron);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
		this.setHardness(2.0F);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		int meta = block.getBlockMetadata(x, y, z);
		if (meta == 0) {
			minX = 0.0F;
			minZ = 0.0F;
			maxX = 1.0F;
			maxZ = 0.5F;
		} else if (meta == 1) {
			minZ = 0.0F;
			minX = 0.5F;
			maxZ = 1.0F;
			maxX = 1.0F;
		} else if (meta == 2) {
			minZ = 0.5F;
			minX = 0.0F;
			maxZ = 1.0F;
			maxX = 1.0F;
		} else {
			minX = 0.0F;
			minZ = 0.0F;
			maxX = 0.5F;
			maxZ = 1.0F;
		}
		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity) {
        AxisAlignedBB axisalignedbb1 = this.getCollisionBoundingBoxFromPool(world, x, y, z);

        if (axisalignedbb1 != null && axis.intersectsWith(axisalignedbb1)) {
            list.add(axisalignedbb1);
        }
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float j, float k, float l) {
		TileEntityAdminShop tileEntity = (TileEntityAdminShop) world.getTileEntity(x, y, z);
        if (tileEntity != null) {
        	String ownerName = tileEntity.ownerName;
        	//String userName = player.getDisplayName()
        	
        	if (!world.isRemote) {
        		FlenixCities_Core.network.sendTo(new AdminShopPricePacket(tileEntity.ownerName, tileEntity.buyPrice1, tileEntity.sellPrice1, tileEntity.buyPrice2, tileEntity.sellPrice2,
            			tileEntity.buyPrice3, tileEntity.sellPrice3, tileEntity.buyPrice4, tileEntity.sellPrice4), (EntityPlayerMP) player);
        		FlenixCities_Core.network.sendTo(new ServerBalancePacket(""+EconUtils.getBalance(player)), (EntityPlayerMP) player);
        	}
        }
        player.openGui(FlenixCities_Core.instance, 3, world, x, y, z);
        return true;
    }
	
	
	@Override
	public int getRenderType() {
		return -1;
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
			EntityPlayer player = (EntityPlayer) entity;
			String name = player.getDisplayName();
			TileEntityAdminShop tileEntity = (TileEntityAdminShop) world.getTileEntity(x, y, z);
			tileEntity.ownerName = name;
		}
	}	

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new TileEntityAdminShop();
	}
	
	@Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, par6);
    }

    private void dropItems(World world, int x, int y, int z){
    	Random rand = new Random();

    	TileEntity tileEntity = world.getTileEntity(x, y, z);
    	if (!(tileEntity instanceof IInventory)) {
    		return;
    	}
    	IInventory inventory = (IInventory) tileEntity;

    	for (int i = 0; i < inventory.getSizeInventory(); i++) {
    		ItemStack item = inventory.getStackInSlot(i);

    		if (item != null && item.stackSize > 0) {
    			float rx = rand.nextFloat() * 0.8F + 0.1F;
    			float ry = rand.nextFloat() * 0.8F + 0.1F;
    			float rz = rand.nextFloat() * 0.8F + 0.1F;
    			
    			EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
    			
    			if (item.hasTagCompound()) {
    				entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
    			}

    			float factor = 0.05F;
    			entityItem.motionX = rand.nextGaussian() * factor;
    			entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
    			entityItem.motionZ = rand.nextGaussian() * factor;
    			world.spawnEntityInWorld(entityItem);
    			item.stackSize = 0;
    		}
    	}
    }

}