package co.uk.silvania.cities.core.npc;

import co.uk.silvania.cities.core.CityConfig;
import co.uk.silvania.cities.core.CoreItems;
import co.uk.silvania.cities.core.FlenixCities_Core;
import co.uk.silvania.cities.core.npc.ai.NPCAITempt;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBanker extends EntityAnimal {
	
	int heldID;
	int helmetID;
	int chestID;
	int legsID;
	int bootsID;
	int despawnTime;
	boolean wander;
	boolean invincible;
	boolean locked;
	String playerName = "bl";
	String npcName = "ah";
	
	public EntityBanker(World par1World) {
		super(par1World);
		this.getNavigator().setAvoidsWater(true);
		this.setSize(0.6F, 1.8F);
        this.isImmuneToFire = false;
		float var2 = 0.25F;
		this.isEntityInvulnerable();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new NPCAITempt(this, 1.1D, CoreItems.note10000, false));
        this.tasks.addTask(2, new NPCAITempt(this, 1.0D, CoreItems.note5000, false));
        this.tasks.addTask(3, new NPCAITempt(this, 0.9D, CoreItems.note2000, false));
        this.tasks.addTask(4, new NPCAITempt(this, 0.8D, CoreItems.note1000, false));
        this.tasks.addTask(5, new EntityAIPanic(this, 0.38F));
        this.tasks.addTask(6, new EntityAIMate(this, var2));
        this.tasks.addTask(7, new EntityAIFollowParent(this, 0.28F));
        this.tasks.addTask(8, new EntityAIWander(this, var2));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));
	}
		
	@Override
	public boolean interact(EntityPlayer player) {
		//Right now this is just here so I can right-click the mob and printline what his NBT is as an in-game check.
		if (!player.inventory.hasItem(CoreItems.debitCardNew)) {
			player.inventory.addItemStackToInventory(new ItemStack(CoreItems.debitCardNew));
		}
		NBTTagCompound nbt = this.getEntityData();
		readEntityFromNBT(nbt);
		return true;
	}

	public boolean onInteractFirst(EntityPlayer player) {
		System.out.println("Interacting First!");
		player.openGui(FlenixCities_Core.instance, 0, worldObj, 0, 0, 0);
		return true;
	}
	
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		wander = nbt.getBoolean("wander");
		locked = nbt.getBoolean("entityLocked");
		invincible = nbt.getBoolean("Invulnerable");
		
		playerName = nbt.getString("playerName");
		npcName = nbt.getString("npcName");
		despawnTime = nbt.getInteger("despawnTime");
		
		heldID = nbt.getInteger("heldID");
		helmetID = nbt.getInteger("helmetID");
		chestID = nbt.getInteger("chestID");
		legsID = nbt.getInteger("legsID");
		bootsID = nbt.getInteger("bootsID");
		if (CityConfig.debugMode == true) {
			System.out.println("NBT values are set as follows:");
			System.out.println("Wander: " + wander);
			System.out.println("Locked: " + locked);
			System.out.println("Invulnerable: " + invincible);
			System.out.println("Player Name: " + playerName);
			System.out.println("NPC Name: " + npcName);
			System.out.println("Despawn Time: " + despawnTime);
			System.out.println("Held ID: " + heldID);
			System.out.println("Helmet ID: " + helmetID);
			System.out.println("Chest ID: " + chestID);
			System.out.println("Legs ID: " + legsID);
			System.out.println("Boots ID: " + bootsID);
		}
	}
	
	@Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return false;
		/*System.out.println("Overriding");
        if (this.isEntityInvulnerable()) {
            return false;
        }
        else
        {
            this.fleeingTick = 60;

            if (!this.isAIEnabled())
            {
                IAttributeInstance attributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);

                if (attributeinstance.getModifier(field_110179_h) == null)
                {
                    attributeinstance.applyModifier(field_110181_i);
                }
            }

            this.entityToAttack = null;
            return super.attackEntityFrom(par1DamageSource, par2);
        }*/
    }
	
	public boolean isAIEnabled() {
		if (wander == true) {
			if (CityConfig.debugMode == true) {
				System.out.println("Wander enabled. They can walk around.");
			}
			return true;
		} else
			if (CityConfig.debugMode == true) {
				//System.out.println("Wander disabled. No moving!");
			}
			return false;
	}
	
	protected void applyEntityAttributes() {
		if (invincible == false) {
			super.applyEntityAttributes();
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue(); 
			if (CityConfig.debugMode == true) {
				System.out.println("Entity is not invincible. Can be attacked. Status: " + invincible);
			}
		} else {
			System.out.println("Entity is invincible!");
		}
	}
	
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}
}
