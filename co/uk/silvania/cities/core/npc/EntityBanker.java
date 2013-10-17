package co.uk.silvania.cities.core.npc;

import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.NBTConfig;
import co.uk.silvania.cities.core.npc.ai.NPCAITempt;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBanker extends EntityAnimal {
	
	public EntityBanker(World par1World) {
		super(par1World);
		this.getNavigator().setAvoidsWater(true);
		this.setSize(0.6F, 1.8F);
        this.isImmuneToFire = false;
		float var2 = 0.25F;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new NPCAITempt(this, 1.1D, CoreItems.note10000.itemID, false));
        this.tasks.addTask(2, new NPCAITempt(this, 1.0D, CoreItems.note5000.itemID, false));
        this.tasks.addTask(3, new NPCAITempt(this, 0.9D, CoreItems.note2000.itemID, false));
        this.tasks.addTask(4, new NPCAITempt(this, 0.8D, CoreItems.note1000.itemID, false));
        this.tasks.addTask(5, new EntityAIPanic(this, 0.38F));
        this.tasks.addTask(6, new EntityAIMate(this, var2));
        this.tasks.addTask(7, new EntityAIFollowParent(this, 0.28F));
        this.tasks.addTask(8, new EntityAIWander(this, var2));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));
	}

	public boolean isAIEnabled() {
		return true;
	}
	
	public boolean interact(EntityPlayer player) {
		System.out.println("Try and interact? Ok.");
		World world = Minecraft.getMinecraft().theWorld;
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
                }
            }
		}
		System.out.println("You have successfully interacted!");
		return true;
    }
	
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue(); 
	}
	
	protected String getLivingSound() {
		return "mob.glog.say";
	}
	
	protected String getHurtSound() {
		return "mob.glog.say";
	}
	
	protected String getDeathSound() {
		return "mob.glog.death";
	}
	
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		this.worldObj.playSoundAtEntity(this, "mob.glog.step", 0.15F,  1.0F);
	}
	
	protected int getDropItemId() {
		return CoreItems.note1000.itemID;
	}
	
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}
}
