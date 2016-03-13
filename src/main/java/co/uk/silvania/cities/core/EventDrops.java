package co.uk.silvania.cities.core;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EventDrops {

	@SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event) {
        if (event.source.getSourceOfDamage() instanceof EntityPlayerMP) {
    		Random rand = new Random();
            int chance = rand.nextInt(200);
            if (event.entityLiving instanceof EntityZombie) {
            	if (chance < 20) {
            		event.entityLiving.dropItem(CoreItems.coin1, 1);
            	} else if (chance < 40) {
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(1) + 1);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2, rand.nextInt(3) + 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(2) + 1);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.note100, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.note200, 1);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.note500, 1);
                }
            }
            if (event.entityLiving instanceof EntitySkeleton) {
                if (chance < 40) {
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(1) + 1);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2, rand.nextInt(3) + 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(1) + 1);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.coin50, 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin5, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin10, rand.nextInt(4) + 1);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.coin100, rand.nextInt(1) + 1);
                    event.entityLiving.dropItem(CoreItems.coin50, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(3) + 1);
                    event.entityLiving.dropItem(CoreItems.coin10, rand.nextInt(6) + 1);
                    event.entityLiving.dropItem(CoreItems.coin5, rand.nextInt(4) + 1);
                }
            }
            if (event.entityLiving instanceof EntityCreeper) {
                if (chance < 40) {
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(2) + 1);
                } else if (chance < 60) {
                    event.entityLiving.dropItem(CoreItems.coin50, 1);
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                }
            }
            if (event.entityLiving instanceof EntityEnderman) {
                if (chance < 12) {
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(1) + 1);
                } else if (chance < 26) {
                    event.entityLiving.dropItem(CoreItems.coin2, rand.nextInt(5) + 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(3) + 1);
                } else if (chance < 32) {
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                } else if (chance < 45) {
                    event.entityLiving.dropItem(CoreItems.coin50, 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin5, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin10, rand.nextInt(4) + 1);
                } else if (chance < 52) {
                    event.entityLiving.dropItem(CoreItems.note200, rand.nextInt(1) + 1);
                    event.entityLiving.dropItem(CoreItems.note100, 1);
                }
            }
            if (event.entityLiving instanceof EntityPigZombie) {
                if (chance < 30) {
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(1) + 1);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2, rand.nextInt(3) + 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(2) + 1);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.note100, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.note200, 1);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.note500, 1);
                }
            }
            if (event.entityLiving instanceof EntityWitch) {
                if (chance < 30) {
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(1) + 1);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2, rand.nextInt(3) + 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(2) + 1);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.coin50, 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(1) + 1);
                    event.entityLiving.dropItem(CoreItems.coin5, rand.nextInt (2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin10, rand.nextInt(3) + 1);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                    event.entityLiving.dropItem(CoreItems.coin50, rand.nextInt(1) + 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin10, rand.nextInt(4) + 1);
                    event.entityLiving.dropItem(CoreItems.coin5, rand.nextInt(3) + 1);
                }
            }
            if (event.entityLiving instanceof EntityVillager) {
                if (chance < 20) {
                    event.entityLiving.dropItem(CoreItems.coin50, 1);
                } else if (chance < 48) {
                    event.entityLiving.dropItem(CoreItems.note100, 1);
                    event.entityLiving.dropItem(CoreItems.coin25, 1);
                } else if (chance < 60) {
                    event.entityLiving.dropItem(CoreItems.note200, 1);
                } else if (chance < 63) {
                    event.entityLiving.dropItem(CoreItems.note200, 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(1) + 1);
                    event.entityLiving.dropItem(CoreItems.coin5, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin10, rand.nextInt(3) + 1);
                } else if (chance < 65) {
                    event.entityLiving.dropItem(CoreItems.note100, rand.nextInt(1) + 1);
                    event.entityLiving.dropItem(CoreItems.coin50, rand.nextInt(1) + 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin10, rand.nextInt(4) + 1);
                    event.entityLiving.dropItem(CoreItems.coin5, rand.nextInt(3) + 1);
                }
            } else if (event.entityLiving instanceof IMob) {
                if (chance < 40) {
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(1) + 1);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2, rand.nextInt(3) + 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(1) + 1);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.coin50, 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin5, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin10, rand.nextInt(4) + 1);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.coin100, rand.nextInt(1) + 1);
                    event.entityLiving.dropItem(CoreItems.coin50, rand.nextInt(2) + 1);
                    event.entityLiving.dropItem(CoreItems.coin25, rand.nextInt(3) + 1);
                    event.entityLiving.dropItem(CoreItems.coin10, rand.nextInt(6) + 1);
                    event.entityLiving.dropItem(CoreItems.coin5, rand.nextInt(4) + 1);
                }
            }
        }
    }
}