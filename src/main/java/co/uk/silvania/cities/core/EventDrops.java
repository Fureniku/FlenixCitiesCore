package co.uk.silvania.cities.core;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EventDrops {

	@SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event) {
        if (event.source.getSourceOfDamage() instanceof EntityPlayer) {
    		Random rand = new Random();
            int chance = rand.nextInt(200);
            if (event.entityLiving instanceof EntityZombie) {
                if (chance < 30) {
                    event.entityLiving.dropItem(CoreItems.coin25, 2);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2, 4);
                    event.entityLiving.dropItem(CoreItems.coin25, 3);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.note100, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.note200, 1);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.note500, 1);
                }
            }
            if (event.entityLiving instanceof EntitySkeleton) {
                if (chance < 30) {
                    event.entityLiving.dropItem(CoreItems.coin25, 2);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2, 4);
                    event.entityLiving.dropItem(CoreItems.coin25, 3);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.coin50, 1);
                    event.entityLiving.dropItem(CoreItems.coin25, 3);
                    event.entityLiving.dropItem(CoreItems.coin5, 3);
                    event.entityLiving.dropItem(CoreItems.coin10, 5);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.coin100, 2);
                    event.entityLiving.dropItem(CoreItems.coin50, 3);
                    event.entityLiving.dropItem(CoreItems.coin25, 4);
                    event.entityLiving.dropItem(CoreItems.coin10, 7);
                    event.entityLiving.dropItem(CoreItems.coin5, 5);
                }
            }
            if (event.entityLiving instanceof EntityCreeper) {
                if (chance < 40) {
                    event.entityLiving.dropItem(CoreItems.coin25, 3);
                } else if (chance < 60) {
                    event.entityLiving.dropItem(CoreItems.coin50, 1);
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                }
            }
            if (event.entityLiving instanceof EntityEnderman) {
                if (chance < 12) {
                    event.entityLiving.dropItem(CoreItems.coin25, 2);
                } else if (chance < 26) {
                    event.entityLiving.dropItem(CoreItems.coin2, 6);
                    event.entityLiving.dropItem(CoreItems.coin25, 4);
                } else if (chance < 32) {
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                } else if (chance < 45) {
                    event.entityLiving.dropItem(CoreItems.coin50, 1);
                    event.entityLiving.dropItem(CoreItems.coin25, 3);
                    event.entityLiving.dropItem(CoreItems.coin5, 3);
                    event.entityLiving.dropItem(CoreItems.coin10, 5);
                } else if (chance < 52) {
                    event.entityLiving.dropItem(CoreItems.note200, 2);
                    event.entityLiving.dropItem(CoreItems.note100, 1);
                }
            }
            if (event.entityLiving instanceof EntityPigZombie) {
                if (chance < 30) {
                    event.entityLiving.dropItem(CoreItems.coin25, 2);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2, 4);
                    event.entityLiving.dropItem(CoreItems.coin25, 3);
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
                    event.entityLiving.dropItem(CoreItems.coin25, 2);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2, 4);
                    event.entityLiving.dropItem(CoreItems.coin25, 3);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.coin50, 1);
                    event.entityLiving.dropItem(CoreItems.coin25, 2);
                    event.entityLiving.dropItem(CoreItems.coin5, 3);
                    event.entityLiving.dropItem(CoreItems.coin10, 4);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.coin100, 1);
                    event.entityLiving.dropItem(CoreItems.coin50, 2);
                    event.entityLiving.dropItem(CoreItems.coin25, 3);
                    event.entityLiving.dropItem(CoreItems.coin10, 5);
                    event.entityLiving.dropItem(CoreItems.coin5, 4);
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
                    event.entityLiving.dropItem(CoreItems.coin25, 2);
                    event.entityLiving.dropItem(CoreItems.coin5, 3);
                    event.entityLiving.dropItem(CoreItems.coin10, 4);
                } else if (chance < 65) {
                    event.entityLiving.dropItem(CoreItems.note100, 2);
                    event.entityLiving.dropItem(CoreItems.coin50, 2);
                    event.entityLiving.dropItem(CoreItems.coin25, 3);
                    event.entityLiving.dropItem(CoreItems.coin10, 5);
                    event.entityLiving.dropItem(CoreItems.coin5, 4);
                }
            }
        }
    }
}