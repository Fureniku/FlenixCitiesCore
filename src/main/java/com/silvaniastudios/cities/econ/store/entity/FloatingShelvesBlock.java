package com.silvaniastudios.cities.econ.store.entity;

import com.silvaniastudios.cities.core.FlenixCities;
import com.silvaniastudios.cities.econ.EconUtils;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FloatingShelvesBlock extends BlockContainer implements IStoreBlock {
	
	public EconUtils econ = new EconUtils();

	float minX = 0.0F;
	float minY = 0.0F;
	float minZ = 0.0F;
	float maxX = 1.0F;
	float maxY = 1.0F;
	float maxZ = 1.0F;
	
	public FloatingShelvesBlock() {
		super(Material.IRON);
		this.setCreativeTab(FlenixCities.tabEcon);
		this.setHardness(2.0F);
	}
	
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntityFloatingShelves tileEntity = (TileEntityFloatingShelves) world.getTileEntity(pos);
	    	ItemStack item = player.getHeldItemMainhand();
			/*if (item != null && item.getItem() == CoreItems.storeStockPairer) {
	        	if (player.getUniqueID().toString().equals(tileEntity.ownerUuid)) {
		        	if (item.stackTagCompound == null) {
		        		player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Link it with a stock chest first!"));
		        	} else {
		        		if (item.stackTagCompound.getString("playerUUID").equals(tileEntity.ownerUuid)) {
			        		int stockX = item.stackTagCompound.getInteger("xPos");
			        		int stockY = item.stackTagCompound.getInteger("yPos");
			        		int stockZ = item.stackTagCompound.getInteger("zPos");
			        		if (world.getTileEntity(stockX, stockY, stockZ) instanceof TileEntityStockChest) {
			        			TileEntityStockChest stockEntity = (TileEntityStockChest) world.getTileEntity(stockX, stockY, stockZ);
			        			if (stockEntity.ownerUuid.equals(tileEntity.ownerUuid)) {
			        				if (tileEntity != null) {
						        		tileEntity.stockXPos = stockX;
						        		tileEntity.stockYPos = stockY;
						        		tileEntity.stockZPos = stockZ;
			        				}
			        				System.out.println(player.getDisplayName() + " has linked a stock chest to a shop.");
					        		player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Store successfully linked with Stock Chest at " + stockX + ", " + stockY + ", " + stockZ));
			        			} else {
			        				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "This is not your store!"));
			        			}
			        		}
			        	} else {
			        		player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "This is not your store!"));
			        	}
		        	}
	        	} else {
	        		player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "This is not your store!"));
	        	}
		    } else {
		    	if (tileEntity != null) {
		        	FlenixCities_Core.network.sendTo(new FloatingShelvesPricePacket(tileEntity.ownerName, tileEntity.buyPrice1, tileEntity.sellPrice1, tileEntity.buyPrice2, tileEntity.sellPrice2,
		            			tileEntity.buyPrice3, tileEntity.sellPrice3, tileEntity.buyPrice4, tileEntity.sellPrice4), (EntityPlayerMP) player);
		        	FlenixCities_Core.network.sendTo(new ServerBalancePacket(""+econ.getBalance(player)), (EntityPlayerMP) player);
		    	}
	        }
			if ((item != null && item.getItem() != CoreItems.storeStockPairer) || item == null) {
				player.openGui(FlenixCities_Core.instance, 1, world, x, y, z);
			}*/
		}
        return true;
    }
	
	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	//TODO rotation

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new TileEntityFloatingShelves();
	}
	
	/*@Override
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
    }*/
}