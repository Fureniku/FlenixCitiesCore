package co.uk.silvania.cities.core.blocks;

import java.util.List;

import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockWalkway extends Block {

	public BlockWalkway(Material mat, SoundType sound) {
		super(mat);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.setStepSound(sound);
		this.setHardness(2.0F);
		this.setResistance(12.0F);
	}
	
	@Override public boolean renderAsNormalBlock() { return false; }
	@Override public boolean isOpaqueCube() { return false; }
	
	@Override
	public int getRenderType() {
		return ClientProxy.walkwayRenderID;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
		int rot = MathHelper.floor_double(entity.getRotationYawHead());
		while (rot > 360) {
			rot = rot - 360;
		}
		while (rot < 0) {
			rot = rot + 360;
		}
		
		System.out.println("Rotation: " + rot);
		
		if (rot >= 90 && rot <= 270) {
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
		} else {
			world.setBlockMetadataWithNotify(x, y, z, 1, 3);
		}
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity) {	
		int meta = world.getBlockMetadata(x, y, z);
		
		boolean connectNorth; //z-1
		boolean connectEast;  //x+1
		boolean connectSouth; //z+1
		boolean connectWest;  //x-1
		
		//TODO Get rendering working first, then add these.
	}
	
	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
	public IIcon getIcon(int side, int metadata) {
		return Blocks.planks.getIcon(side, 0);
	}
}
