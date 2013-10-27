package co.uk.silvania.cities.core;

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
    public static double rand;

    @ForgeSubscribe
    public void onEntityDrop(LivingDropsEvent event) {
        if (event.source.getDamageType().equals("player")) {
            rand = Math.random();
            if (event.entityLiving instanceof EntityZombie) {
                if (rand < 0.50D) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (rand < 0.70D) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (rand < 0.80D) {
                    event.entityLiving.dropItem(CoreItems.note100.itemID, 1);
                } else if (rand < 0.50D) {
                    event.entityLiving.dropItem(CoreItems.note200.itemID, 1);
                } else if (rand < 0.40D) {
                    event.entityLiving.dropItem(CoreItems.note500.itemID, 1);
                }
            }
            if (event.entityLiving instanceof EntitySkeleton) {
                if (rand < 0.50D) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (rand < 0.70D) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (rand < 0.80D) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                } else if (rand < 0.50D) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 5);
                } else if (rand < 0.40D) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 7);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 5);
                }
            }
            if (event.entityLiving instanceof EntityCreeper) {
                if (rand < 0.90D) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (rand < 0.70D) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                }
            }
            if (event.entityLiving instanceof EntityEnderman) {
                if (rand < 0.90D) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (rand < 0.70D) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 6);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 4);
                } else if (rand < 0.80D) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                } else if (rand < 0.50D) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 5);
                } else if (rand < 0.40D) {
                    event.entityLiving.dropItem(CoreItems.note200.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.note100.itemID, 1);
                }
            }
            if (event.entityLiving instanceof EntityPigZombie) {
                if (rand < 0.50D) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (rand < 0.70D) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (rand < 0.80D) {
                    event.entityLiving.dropItem(CoreItems.note100.itemID, 1);
                } else if (rand < 0.50D) {
                    event.entityLiving.dropItem(CoreItems.note200.itemID, 1);
                } else if (rand < 0.40D) {
                    event.entityLiving.dropItem(CoreItems.note500.itemID, 1);
                }
            }
            if (event.entityLiving instanceof EntitySpider) {
                if (rand < 0.90D) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (rand < 0.70D) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (rand < 0.80D) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                } else if (rand < 0.50D) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 4);
                } else if (rand < 0.40D) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 5);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 4);
                }
            }
            if (event.entityLiving instanceof EntityWitch) {
                if (rand < 0.90D) {
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                } else if (rand < 0.70D) {
                    event.entityLiving.dropItem(CoreItems.coin2.itemID, 4);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                } else if (rand < 0.80D) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                } else if (rand < 0.50D) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 4);
                } else if (rand < 0.40D) {
                    event.entityLiving.dropItem(CoreItems.coin100.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 5);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 4);
                }
            }
            if (event.entityLiving instanceof EntityVillager) {
                if (rand < 0.90D) {
                    event.entityLiving.dropItem(CoreItems.coin50.itemID, 1);
                } else if (rand < 0.70D) {
                    event.entityLiving.dropItem(CoreItems.note100.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 1);
                } else if (rand < 0.80D) {
                    event.entityLiving.dropItem(CoreItems.note200.itemID, 1);
                } else if (rand < 0.50D) {
                    event.entityLiving.dropItem(CoreItems.note200.itemID, 1);
                    event.entityLiving.dropItem(CoreItems.coin25.itemID, 2);
                    event.entityLiving.dropItem(CoreItems.coin5.itemID, 3);
                    event.entityLiving.dropItem(CoreItems.coin10.itemID, 4);
                } else if (rand < 0.40D) {
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