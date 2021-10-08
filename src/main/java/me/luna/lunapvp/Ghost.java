package me.luna.lunapvp;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Ghost extends AbilityTemplate{
    private main plugin;


    public void activatedAbility() {
        if(!checkCooldown()){
            return;
        }
        player.setGameMode(GameMode.SPECTATOR);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setGameMode(GameMode.SURVIVAL);
                cooldownTime = System.currentTimeMillis();
            }
        }.runTaskLater(plugin,130);

    }

    protected void setPlugin(main plugin){
        this.plugin = plugin;
    }
    public void playerHitAbility(Player attackedPlayer) {
        if(!checkCooldown()){
            return;
        }
        attackedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 7, 3));
        attackedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 7, 3));
        cooldownTime = System.currentTimeMillis();
    }
}
