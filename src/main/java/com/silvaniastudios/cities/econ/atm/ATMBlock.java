package com.silvaniastudios.cities.econ.atm;

import com.silvaniastudios.cities.core.CoreItems;
import com.silvaniastudios.cities.core.FlenixCities;
import com.silvaniastudios.cities.core.NBTConfig;
import com.silvaniastudios.cities.core.blocks.CitiesBlockBase;
import com.silvaniastudios.cities.econ.EconUtils;
import com.silvaniastudios.cities.econ.money.ItemMoney;
import com.silvaniastudios.cities.network.ServerBalancePacket;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ATMBlock extends CitiesBlockBase {	
	
	public EconUtils econ = new EconUtils();
	
	SoundEvent atmSound = new SoundEvent(new ResourceLocation(FlenixCities.MODID, "cardInsert"));
	
	public ATMBlock(String name) {
		super(name, Material.IRON);
		this.setHardness(1.0F);
		this.setCreativeTab(FlenixCities.tabEcon);
		this.setLightOpacity(0);
	}

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
        	if (player.isSneaking()) {
        		econ.depositAllCash(player);
        	} else if (player.getHeldItemMainhand() != null) {
        		if (player.getHeldItemMainhand().getItem() == CoreItems.debitCard) {
        			world.playSound(player, pos, atmSound, SoundCategory.BLOCKS, 1.0F, 1.0F);
        			FlenixCities.proxy.openGui(0);
        			econ.getBalance(player);                 
        		} else {  
        			NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig());
        			ItemStack item = player.getHeldItemMainhand();
        			String uuid = player.getUniqueID().toString();
        			// Coin 1

        			if (player.getHeldItemMainhand().getItem() instanceof ItemMoney) {
        				ItemMoney money = (ItemMoney) player.getHeldItemMainhand().getItem();
        				int currentBalance = 0;
        				if (nbt.hasKey(uuid)) {
        					NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
        					if (playernbt.hasKey("Balance")) {
        						currentBalance = playernbt.getInteger("Balance");
        					}
        					int modifiedBalance = currentBalance + money.getMoneyValue();
        					playernbt.setInteger("Balance", modifiedBalance);
        					nbt.setTag(uuid, playernbt);
        				} else {
        					NBTTagCompound playernbt = new NBTTagCompound();
        					if (playernbt.hasKey("Balance")) {
        						currentBalance = playernbt.getInteger("Balance");
        					}
        					int modifiedBalance = currentBalance + money.getMoneyValue();
        					playernbt.setInteger("Balance", modifiedBalance);
        					nbt.setTag(uuid, playernbt);
        				}
        				NBTTagCompound playernbt = nbt.getCompoundTag(uuid);
        				player.sendMessage(new TextComponentString(TextFormatting.GOLD + "$" + econ.formatBalance(money.getMoneyValue()) + TextFormatting.GREEN + " Deposited! Your balance is now " + TextFormatting.GOLD + "$" + econ.formatBalance(playernbt.getInteger("Balance"))));
        				player.sendMessage(new TextComponentString(TextFormatting.ITALIC + "Next time, try shift-right clicking with an empty hand to deposit " + TextFormatting.ITALIC + "all your money!"));
        				NBTConfig.saveConfig(nbt, NBTConfig.getWorldConfig());
        				item.setCount(item.getCount()-1);
        			} else {
        				FlenixCities.proxy.openGui(1);
        			}
        		}
        	} else {
        		FlenixCities.proxy.openGui(1);
        	}
        
        	EntityPlayerMP playerMP = (EntityPlayerMP) player;
        	FlenixCities.network.sendTo(new ServerBalancePacket(""+econ.getBalance(player)), playerMP); //TODO
       		System.out.println("Current Balance Packet Sent! Balance: $" + econ.getBalance(player));
       	}
        return true;
    }
		
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
	    int blockSet = this.getMetaFromState(state);
	    int direction = MathHelper.floor((placer.rotationYaw * 4F) / 360F + 0.5D) & 3;
	    int newMeta = (blockSet * 4) + direction;
	    world.setBlockState(pos, this.getStateFromMeta(newMeta), 1);
	}
    
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
        list.add(new ItemStack(this, 1, 0));
        list.add(new ItemStack(this, 1, 4));
        list.add(new ItemStack(this, 1, 8));
        list.add(new ItemStack(this, 1, 12));
	}
	
    @Override
    public int damageDropped(IBlockState state) {
    	return this.getMetaFromState(state);
    }
}