package co.uk.silvania.cities.core.blocks;

import java.util.List;

import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWalkway extends Block {
	
	Block textureBlock;
	int textureMeta;
	String textureName;

	public BlockWalkway(Material mat, SoundType sound, Block block, int meta, String icon) {
		super(mat);
		this.setCreativeTab(FlenixCities_Core.tabCity);
		this.textureBlock = block;
		this.textureMeta = meta;
		this.textureName = icon;
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
		
		if ((rot >= 45 && rot <= 135) || (rot >= 225 && rot <= 315)) {
			world.setBlockMetadataWithNotify(x, y, z, 0, 3); //Facing east/west
		} else {
			world.setBlockMetadataWithNotify(x, y, z, 1, 3);
		}
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity) {
		boolean hasCollision = false;
		if (!(world.getBlock(x, y-1, z) instanceof BlockWalkway)) {
			hasCollision = true;
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		
		int meta = world.getBlockMetadata(x, y, z);
		
		boolean connectNorth = checkConnections(world, x, y, z-1); //z-1
		boolean connectEast  = checkConnections(world, x+1, y, z);  //x+1
		boolean connectSouth = checkConnections(world, x, y, z+1); //z+1
		boolean connectWest  = checkConnections(world, x-1, y, z);  //x-1
		
		boolean hitboxNorth = false;
		boolean hitboxEast  = false;
		boolean hitboxSouth = false;
		boolean hitboxWest  = false;
		
		if (meta == 0) {
			if (!connectNorth) { hitboxNorth = true; }
			if (!connectSouth) { hitboxSouth = true; }
			
			if (connectNorth && !connectEast) { hitboxEast = true; }
			if (connectNorth && !connectWest) { hitboxWest = true; }
			if (connectSouth && !connectEast) { hitboxEast = true; }
			if (connectSouth && !connectWest) { hitboxWest = true; }
			
		} else if (meta == 1) {
			if (!connectEast) { hitboxEast = true; }
			if (!connectWest) { hitboxWest = true; }
			
			if (connectEast && !connectNorth) { hitboxNorth = true; }
			if (connectEast && !connectSouth) { hitboxSouth = true; }
			if (connectWest && !connectNorth) { hitboxNorth = true; }
			if (connectWest && !connectSouth) { hitboxSouth = true; }
		}
		
		if (hitboxNorth) {
			hasCollision = true;
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		
		if (hitboxSouth) {
			hasCollision = true;
			setBlockBounds(0.0F, 0.0F, 0.9375F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		
		if (hitboxEast) {
			hasCollision = true;
			setBlockBounds(0.9375F, 0.0F, 0.0625F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		
		if (hitboxWest) {
			hasCollision = true;
			setBlockBounds(0.0F, 0.0F, 0.0F, 0.0625F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}

		if (!hasCollision) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
		}
		
		setBlockBoundsForItemRender();
	}
	
	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public boolean checkConnections(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z).isNormalCube(world, x, y, z) || world.getBlock(x, y-1, z).isNormalCube(world, x, y, z)) {
			return true;
		}
		if (world.getBlock(x, y, z) instanceof BlockWalkway) {
			return true;
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(FlenixCities_Core.modid + ":" + textureName);
	}
	
	@Override
	public IIcon getIcon(int side, int metadata) {
		if (textureBlock != null) {
			return textureBlock.getIcon(0, textureMeta);
		} else {
			return blockIcon;
		}
	}
}
