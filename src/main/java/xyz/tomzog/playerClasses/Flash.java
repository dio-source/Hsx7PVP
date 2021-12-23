package xyz.tomzog.playerClasses;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Flash extends AbilityTemplate {
    public Flash() {
        this.classID = 9;
        this.className = "Flash";
    }

    @Override
    public void playerHitAbility(Player attackedPlayer) {
        if (!checkCooldown()) {
            return;
        }
        attackedPlayer.setWalkSpeed(0f);
        attackedPlayer.setFlySpeed(0f);
        attackedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 1000));
        attackedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100,1000));
        attackedPlayer.setNoDamageTicks(100);

        new BukkitRunnable() {
            @Override
            public void run() {
                attackedPlayer.setWalkSpeed(0.2f);
                attackedPlayer.setFlySpeed(0.2f);
            }
        }.runTaskLater(this.plugin, 100);
    }

    public void activatedAbility() {
        if(!checkCooldown()){
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,160,2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,160,2));

        new BukkitRunnable() {
            @Override
            public void run() {
                player.removePotionEffect(PotionEffectType.SPEED);
                player.removePotionEffect(PotionEffectType.JUMP);
                cooldownTime = System.currentTimeMillis();
            }
        }.runTaskLater(this.plugin,160);

    }

}
