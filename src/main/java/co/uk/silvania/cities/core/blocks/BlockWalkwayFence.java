package co.uk.silvania.cities.core.blocks;

import java.util.List;

import co.uk.silvania.cities.core.client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockWalkwayFence extends Block {

	protected BlockWalkwayFence(Material mat, SoundType sound) {
		super(mat);
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
	    int meta = MathHelper.floor_double((entity.rotationYaw * 4F) / 360F + 0.5D) & 3;
	    world.setBlockMetadataWithNotify(x, y, z, meta, 3);
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity) {
		//Floor panel, always a full panel.
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		
		int meta = world.getBlockMetadata(x, y, z);
		
		boolean connectNorth;
		boolean connectEast;
		boolean connectSouth;
		boolean connectWest;
	}

}
