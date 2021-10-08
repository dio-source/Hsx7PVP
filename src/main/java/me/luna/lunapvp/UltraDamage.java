package me.luna.lunapvp;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UltraDamage extends AbilityTemplate{
    @Override
    public void activatedAbility() {
        if(!checkCooldown()){
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,100,1));
        cooldownTime = System.currentTimeMillis();
    }

    @Override
    public void playerHitAbility(Player attackedPlayer) {
        attackedPlayer.setHealth(attackedPlayer.getHealth() - 4);
    }
}