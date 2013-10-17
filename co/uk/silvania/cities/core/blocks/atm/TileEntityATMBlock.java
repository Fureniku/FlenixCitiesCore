package co.uk.silvania.cities.core.blocks.atm;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreBlocks;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.NBTConfig;
import co.uk.silvania.cities.core.items.econ.ItemCoin;
import co.uk.silvania.cities.core.items.econ.ItemNote;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityATMBlock extends BlockContainer {	
	
	public static double playerBalance = 0;
    String balString = "";

	public TileEntityATMBlock(int id) {
		super(id, Material.iron);
		this.setHardness(1.0F);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
		this.setLightOpacity(0);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityATMEntity();
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	

    // Huge thanks to "maxpowa" in helping me get this working!!
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float j, float k, float l) {
        if (!world.isRemote) {
            if (player.getHeldItem() != null) {
                if (player.getHeldItem().getItem() == CoreItems.debitCard) {
                    double balance = 0;
                    NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
                    if (nbt.hasKey(player.username)) {
                        NBTTagCompound playernbt = nbt.getCompoundTag(player.username);
                        if (playernbt.hasKey("Balance")) {
                            balance = playernbt.getDouble("Balance");
                        }
                    }
                    player.addChatMessage("Your Balance is: " + balance);
                    player.openGui(FlenixCities_Core.instance, 0, world, x, y, z);
                }
            }
            NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
            ItemStack item = player.getHeldItem();
            // Coin 1
            if (player.getHeldItem() != null) {
                if (player.getHeldItem().getItem() instanceof ItemCoin) {
                    ItemCoin coin = (ItemCoin) player.getHeldItem().getItem();
                    double currentBalance = 0;
                    if (nbt.hasKey(player.username)) {
                        NBTTagCompound playernbt = nbt.getCompoundTag(player.username);
                        if (playernbt.hasKey("Balance")) {
                            currentBalance = playernbt.getDouble("Balance");
                        }
                        double modifiedBalance = currentBalance + coin.getMoneyValue();
                        playernbt.setDouble("Balance", modifiedBalance);
                        nbt.setCompoundTag(player.username, playernbt);
                    } else {
                        NBTTagCompound playernbt = new NBTTagCompound();
                        if (playernbt.hasKey("Balance")) {
                            currentBalance = playernbt.getDouble("Balance");
                        }
                        double modifiedBalance = currentBalance + coin.getMoneyValue();
                        playernbt.setDouble("Balance", modifiedBalance);
                        nbt.setCompoundTag(player.username, playernbt);
                    }
                    NBTTagCompound playernbt = nbt.getCompoundTag(player.username);
                    player.addChatMessage(coin.getMoneyValue() + " " + CityConfig.currencyLargePlural + " Deposited! Your balance is now " + playernbt.getDouble("Balance") + " " + CityConfig.currencyLargePlural);
                    NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
                    --item.stackSize;
                } else if (player.getHeldItem().getItem() instanceof ItemNote) {
                    ItemNote note = (ItemNote) player.getHeldItem().getItem();
                    double currentBalance = 0;
                    if (nbt.hasKey(player.username)) {
                        NBTTagCompound playernbt = nbt.getCompoundTag(player.username);
                        if (playernbt.hasKey("Balance")) {
                            currentBalance = playernbt.getDouble("Balance");
                        }
                        double modifiedBalance = currentBalance + note.getMoneyValue();
                        playernbt.setDouble("Balance", modifiedBalance);
                        nbt.setCompoundTag(player.username, playernbt);
                    } else {
                        NBTTagCompound playernbt = new NBTTagCompound();
                        if (playernbt.hasKey("Balance")) {
                            currentBalance = playernbt.getDouble("Balance");
                        }
                        double modifiedBalance = currentBalance + note.getMoneyValue();
                        playernbt.setDouble("Balance", modifiedBalance);
                        nbt.setCompoundTag(player.username, playernbt);
                    }
                    NBTTagCompound playernbt = nbt.getCompoundTag(player.username);
                    player.addChatMessage(note.getMoneyValue() + " " + CityConfig.currencyLargePlural + " Deposited! Your balance is now " + playernbt.getDouble("Balance") + " " + CityConfig.currencyLargePlural);
                    NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
                    --item.stackSize;
                }
            }
            
            double currentBalance = 0;
            if (nbt.hasKey(player.username)) {
                NBTTagCompound playernbt = nbt.getCompoundTag(player.username);
                if (playernbt.hasKey("Balance")) {
                    currentBalance = playernbt.getDouble("Balance");
                }
            }
            
            ByteArrayOutputStream bt = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bt);
            try {
            	out.writeUTF(balString + currentBalance);
            	Packet250CustomPayload packet = new Packet250CustomPayload("FCitiesPackets", bt.toByteArray());
            	
            	Player par1Player = (Player)player;
            	
            	PacketDispatcher.sendPacketToPlayer(packet, par1Player);
            	System.out.println("Packet sent! Value: " + currentBalance);
            }
            catch (IOException ex) {
            	System.out.println("Packet Failed!");
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
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack)
	{
	    int blockSet = world.getBlockMetadata(x, y, z) / 4;
	    int direction = MathHelper.floor_double((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
	    int newMeta = (blockSet * 4) + direction;
	    world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
	}
    
	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[16];

		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon("FlenixCities:" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return icons[par2];
	}

	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return CoreBlocks.atmBlock.blockID;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(par1, 1, 0));
        list.add(new ItemStack(par1, 1, 4));
        list.add(new ItemStack(par1, 1, 8));
        list.add(new ItemStack(par1, 1, 12));
	}
	
    @Override
    public int damageDropped(int meta) {
    	return meta;
    }
}