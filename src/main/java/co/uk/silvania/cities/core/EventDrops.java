package co.uk.silvania.cities.core;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EventDrops {

    @ForgeSubscribe
    public void onEntityDrop(LivingDropsEvent event) {
        if (event.source.getSourceOfDamage() instanceof EntityPlayer) {
    		Random rand = new Random();
            int chance = rand.nextInt(200);
            if (event.entityLiving instanceof EntityZombie) {
                if (chance < 30) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.note100.itemID, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.note200.itemID, 1);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.note500.itemID, 1);
                }
            }
            if (event.entityLiving instanceof EntitySkeleton) {
                if (chance < 30) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 5);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 7);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 5);
                }
            }
            if (event.entityLiving instanceof EntityCreeper) {
                if (chance < 40) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (chance < 60) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                }
            }
            if (event.entityLiving instanceof EntityEnderman) {
                if (chance < 12) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (chance < 26) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 6);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 4);
                } else if (chance < 32) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                } else if (chance < 45) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 5);
                } else if (chance < 52) {
                    event.entityLiving.dropItem(CoreItems.note200.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.note100.itemID, 1);
                }
            }
            if (event.entityLiving instanceof EntityPigZombie) {
                if (chance < 30) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.note100.itemID, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.note200.itemID, 1);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.note500.itemID, 1);
                }
            }
            if (event.entityLiving instanceof EntityWitch) {
                if (chance < 30) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (chance < 58) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (chance < 70) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                } else if (chance < 75) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 4);
                } else if (chance < 80) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 5);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 4);
                }
            }
            if (event.entityLiving instanceof EntityVillager) {
                if (chance < 20) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                } else if (chance < 48) {
                    event.entityLiving.dropItem(CoreItems.note100.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 1);
                } else if (chance < 60) {
                    event.entityLiving.dropItem(CoreItems.note200.itemID, 1);
                } else if (chance < 63) {
                    event.entityLiving.dropItem(CoreItems.note200.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 4);
                } else if (chance < 65) {
                    event.entityLiving.dropItem(CoreItems.note100.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 5);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 4);
                }
            }
        }
    }
}