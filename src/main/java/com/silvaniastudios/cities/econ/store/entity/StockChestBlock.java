package com.silvaniastudios.cities.econ.store.entity;

import com.silvaniastudios.cities.core.FlenixCities;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StockChestBlock extends BlockContainer {

	public TileEntityStockChest te;
	
	public StockChestBlock() {
		super(Material.IRON);
		this.setCreativeTab(FlenixCities.tabEcon);
		this.setHardness(2.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityStockChest();
	}
	
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
	        TileEntityStockChest tileEntity = (TileEntityStockChest) world.getTileEntity(pos);
	    	ItemStack item = player.getHeldItemMainhand();
	        /*if (item != null && item.getItem() == CoreItems.storeStockPairer) {
	        	if (player.getUniqueID().toString().equals(tileEntity.ownerUuid)) {
		        	if (item.stackTagCompound == null) {
		    			if (!world.isRemote) {
		    				item.stackTagCompound = new NBTTagCompound();
		    				item.stackTagCompound.setString("playerName", tileEntity.ownerName);
		    				item.stackTagCompound.setString("playerUUID", tileEntity.ownerUuid);
		    				item.stackTagCompound.setInteger("xPos", x);
		    				item.stackTagCompound.setInteger("yPos", y);
		    				item.stackTagCompound.setInteger("zPos", z);
		    				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Stock Chest location successfully saved."));
		    			}
		    		} else {
		    			if (!world.isRemote && item.stackTagCompound.getString("playerUUID").equals(tileEntity.ownerUuid)) {
		    				item.stackTagCompound.setInteger("xPos", x);
		    				item.stackTagCompound.setInteger("yPos", y);
		    				item.stackTagCompound.setInteger("zPos", z);
		    				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Stock Chest location successfully saved."));
		    			}
		    		}
	        	} else {
	        		player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "This stock chest belongs to " + tileEntity.ownerName));
	        	}
	        } else {
		        if (player.getUniqueID().toString().equals(tileEntity.ownerUuid) || player.capabilities.isCreativeMode) {
		        	player.openGui(FlenixCities_Core.instance, 4, world, x, y, z);
		        } else {
		        	player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "This stock chest belongs to " + tileEntity.ownerName));
		        }
	        }*/
		}
        return true;
    }
	
	@Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {		
		if (placer instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) placer;
			String name = player.getName();
			String uuid = player.getUniqueID().toString();
			TileEntityStockChest tileEntity = (TileEntityStockChest) world.getTileEntity(pos);
			System.out.println("Placing Stock Chest");
			System.out.println(name);
			System.out.println(uuid);
			tileEntity.ownerName = name;
			tileEntity.ownerUuid = uuid;
		}
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
