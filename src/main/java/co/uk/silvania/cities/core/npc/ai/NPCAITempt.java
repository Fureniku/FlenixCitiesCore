package co.uk.silvania.cities.core.npc.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class NPCAITempt extends EntityAIBase
{

    private EntityCreature temptedEntity;
    private double temptedSpeed;
    private double playerPosX;
    private double playerPosY;
    private double playerPosZ;
    private double playerRotationPitch;
    private double playerRotationYaw;

    private EntityPlayer temptingPlayer;

    private int delayTemptCounter;
    private boolean field_75287_j;

    private int temptItem;

    private boolean scaredByPlayerMovement;
    private boolean avoidWater;

    public NPCAITempt(EntityCreature creature, double speed, int item, boolean fear)
    {
        this.temptedEntity = creature;
        this.temptedSpeed = speed;
        this.temptItem = item;
        this.scaredByPlayerMovement = fear;
        this.setMutexBits(3);
    }

    public boolean shouldExecute()
    {
        if (this.delayTemptCounter > 0)
        {
            --this.delayTemptCounter;
            return false;
        }
        else
        {
            this.temptingPlayer = this.temptedEntity.worldObj.getClosestPlayerToEntity(this.temptedEntity, 10.0D);

            if (this.temptingPlayer == null)
            {
                return false;
            }
            else
            {
                ItemStack itemstack = this.temptingPlayer.getCurrentEquippedItem();
                return itemstack == null ? false : itemstack.itemID == this.temptItem;
            }
        }
    }

    public boolean continueExecuting()
    {
        if (this.scaredByPlayerMovement)
        {
            if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 36.0D)
            {
                if (this.temptingPlayer.getDistanceSq(this.playerPosX, this.playerPosY, this.playerPosZ) > 0.010000000000000002D)
                {
                    return false;
                }

                if (Math.abs((double)this.temptingPlayer.rotationPitch - this.playerRotationPitch) > 5.0D || Math.abs((double)this.temptingPlayer.rotationYaw - this.playerRotationYaw) > 5.0D)
                {
                    return false;
                }
            }
            else
            {
                this.playerPosX = this.temptingPlayer.posX;
                this.playerPosY = this.temptingPlayer.posY;
                this.playerPosZ = this.temptingPlayer.posZ;
            }

            this.playerRotationPitch = (double)this.temptingPlayer.rotationPitch;
            this.playerRotationYaw = (double)this.temptingPlayer.rotationYaw;
        }

        return this.shouldExecute();
    }

    public void startExecuting() {
        this.playerPosX = this.temptingPlayer.posX;
        this.playerPosY = this.temptingPlayer.posY;
        this.playerPosZ = this.temptingPlayer.posZ;
        this.field_75287_j = true;
        this.avoidWater = this.temptedEntity.getNavigator().getAvoidsWater();
        this.temptedEntity.getNavigator().setAvoidsWater(true);
    }

    public void resetTask() {
        this.temptingPlayer = null;
        this.temptedEntity.getNavigator().clearPathEntity();
        this.delayTemptCounter = 100;
        this.field_75287_j = false;
        this.temptedEntity.getNavigator().setAvoidsWater(this.avoidWater);
    }

    public void updateTask() {
        //this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, 30.0F, (float)this.temptedEntity.getVerticalFaceSpeed());

        if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25D) {
            this.temptedEntity.getNavigator().clearPathEntity();
        } else {
            this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.temptedSpeed);
        }
    }

    public boolean func_75277_f() {
        return this.field_75287_j;
    }
}
