package co.uk.silvania.cities.econ.atm;

import java.util.List;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.NBTConfig;
import co.uk.silvania.cities.econ.EconUtils;
import co.uk.silvania.cities.econ.money.ItemCoin;
import co.uk.silvania.cities.econ.money.ItemNote;
import co.uk.silvania.cities.network.ServerBalancePacket;
import co.uk.silvania.cities.network.SoundPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityATMBlock extends BlockContainer {	
	
	public static double playerBalance = 0;

	public TileEntityATMBlock() {
		super(Material.iron);
		this.setHardness(1.0F);
		this.setCreativeTab(FlenixCities_Core.tabEcon);
		this.setLightOpacity(0);
	}

	@Override
	public int getRenderType() {
		return -1;
	}	

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float j, float k, float l) {
        if (!world.isRemote) {
        	if (player.isSneaking()) {
        		EconUtils.depositAllCash(player, world);
        	} else if (player.getHeldItem() != null) {
        		if (player.getHeldItem().getItem() == CoreItems.debitCardNew) {
        			world.playSoundEffect(20, 70, 20, "FlenixCities:block.atm.cardInsert", 1, 1);
        			player.openGui(FlenixCities_Core.instance, 0, world, x, y, z);
        			EconUtils.getBalance(player, world);                 
        		} else {  
        			NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
        			ItemStack item = player.getHeldItem();
        			String uuid = player.getUniqueID().toString();
        			// Coin 1

        			if (player.getHeldItem().getItem() instanceof ItemCoin) {
        				ItemCoin coin = (ItemCoin) player.getHeldItem().getItem();
        				double currentBalance = 0;
        				if (nbt.hasKey(uuid)) {
        					NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
        					if (playernbt.hasKey("Balance")) {
        						currentBalance = playernbt.getDouble("Balance");
        					}
        					double modifiedBalance = currentBalance + coin.getMoneyValue();
        					playernbt.setDouble("Balance", modifiedBalance);
        					nbt.setTag(uuid, playernbt);
        				} else {
        					NBTTagCompound playernbt = new NBTTagCompound();
        					if (playernbt.hasKey("Balance")) {
        						currentBalance = playernbt.getDouble("Balance");
        					}
        					double modifiedBalance = currentBalance + coin.getMoneyValue();
        					playernbt.setDouble("Balance", modifiedBalance);
        					nbt.setTag(uuid, playernbt);
        				}
        				NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
        				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "$" + EconUtils.formatBalance(coin.getMoneyValue()) + EnumChatFormatting.GREEN + " Deposited! Your balance is now " + EnumChatFormatting.GOLD + "$" + EconUtils.formatBalance(playernbt.getDouble("Balance"))));
        				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Next time, try shift-right clicking with an empty hand to deposit " + EnumChatFormatting.ITALIC + "all your money!"));
        				NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
        				--item.stackSize;
        			} else if (player.getHeldItem().getItem() instanceof ItemNote) {
	                    ItemNote note = (ItemNote) player.getHeldItem().getItem();
	                    double currentBalance = 0;
	                    if (nbt.hasKey(uuid)) {
	                        NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
	                        if (playernbt.hasKey("Balance")) {
	                            currentBalance = playernbt.getDouble("Balance");
	                        }
	                        double modifiedBalance = currentBalance + note.getMoneyValue();
	                        playernbt.setDouble("Balance", modifiedBalance);
	                        nbt.setTag(uuid, playernbt);
	                    } else {
	                    	NBTTagCompound playernbt = new NBTTagCompound();
	                        if (playernbt.hasKey("Balance")) {
	                            currentBalance = playernbt.getDouble("Balance");
	                        }
	                        double modifiedBalance = currentBalance + note.getMoneyValue();
	                        playernbt.setDouble("Balance", modifiedBalance);
	                        nbt.setTag(uuid, playernbt);
	                    }
	                    NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
	                    player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "$" + EconUtils.formatBalance(note.getMoneyValue()) + EnumChatFormatting.GREEN + " Deposited! Your balance is now " + EnumChatFormatting.GOLD + "$" + EconUtils.formatBalance(playernbt.getDouble("Balance"))));
	                    player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Next time, try shift-right clicking with an empty hand to deposit " + EnumChatFormatting.ITALIC + "all your money!"));
	                    NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig(world));
	                    --item.stackSize;
        			} else {
        				player.openGui(FlenixCities_Core.instance, 2, world, x, y, z);
        			}
        		}
        	} else {
        		player.openGui(FlenixCities_Core.instance, 2, world, x, y, z);
        	}
        
        	EntityPlayerMP playerMP = (EntityPlayerMP) player;
        	FlenixCities_Core.network.sendTo(new ServerBalancePacket(""+EconUtils.getBalance(player, world)), playerMP); //TODO
       		System.out.println("Current Balance Packet Sent! Balance: $" + EconUtils.getBalance(player, world));
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
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[16];

		for(int i = 0; i < 4; i++) {
			icons[i] = iconRegister.registerIcon("FlenixCities:" + (this.getUnlocalizedName().toLowerCase().substring(5)) + i);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2) {
		return icons[par2];
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 4));
        list.add(new ItemStack(item, 1, 8));
        list.add(new ItemStack(item, 1, 12));
	}
	
    @Override
    public int damageDropped(int meta) {
    	return meta;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new TileEntityATMEntity();
	}
}