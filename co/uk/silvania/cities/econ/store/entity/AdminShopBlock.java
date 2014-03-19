package co.uk.silvania.cities.econ.store.entity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.econ.EconUtils;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class AdminShopBlock extends BlockContainer {

	float minX = 0.0F;
	float minY = 0.0F;
	float minZ = 0.0F;
	float maxX = 1.0F;
	float maxY = 1.0F;
	float maxZ = 1.0F;
	
	public AdminShopBlock(int id) {
		super(id, Material.iron);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		int meta = block.getBlockMetadata(x, y, z);
		if (meta == 0) {
			minZ = 0.5F;
			minX = 0.0F;
			maxZ = 1.0F;
			maxY = 1.0F;
		} else if (meta == 1) {
			minZ = 0.0F;
			minX = 0.5F;
			maxZ = 1.0F;
			maxY = 1.0F;
		} else if (meta == 2) {
			minX = 0.0F;
			minZ = 0.0F;
			maxX = 1.0F;
			maxZ = 0.5F;
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
		return AxisAlignedBB.getAABBPool().getAABB((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
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
		TileEntityAdminShop tileEntity = (TileEntityAdminShop) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
        	String ownerName = tileEntity.ownerName;
        	String userName = player.username;
        	
        	ByteArrayOutputStream bt = new ByteArrayOutputStream();
        	DataOutputStream out = new DataOutputStream(bt);
        	try {
        		out.writeUTF("" + tileEntity.ownerName);
        		out.writeDouble(tileEntity.buyPrice1);
        		out.writeDouble(tileEntity.sellPrice1);
        		out.writeDouble(tileEntity.buyPrice2);
        		out.writeDouble(tileEntity.sellPrice2);
        		out.writeDouble(tileEntity.buyPrice3);
        		out.writeDouble(tileEntity.sellPrice3);
        		out.writeDouble(tileEntity.buyPrice4);
        		out.writeDouble(tileEntity.sellPrice4);
        		
        		Packet250CustomPayload packet = new Packet250CustomPayload("FCShopPacket", bt.toByteArray());
            	
            	Player par1Player = (Player)player;
            	
            	PacketDispatcher.sendPacketToPlayer(packet, par1Player);
            }
            catch (IOException ex) {
            	System.out.println("Packet Failed!");
            }
        	
            player.openGui(FlenixCities_Core.instance, 1, world, x, y, z);        	
        	EconUtils.triggerServerInventoryBalancePacket(player, world);
        }
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
			String name = player.username;
			TileEntityAdminShop tileEntity = (TileEntityAdminShop) world.getBlockTileEntity(x, y, z);
			tileEntity.ownerName = name;
		}
	}	

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityAdminShop();
	}
}