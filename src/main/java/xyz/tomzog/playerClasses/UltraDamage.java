package xyz.tomzog.playerClasses;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UltraDamage extends AbilityTemplate{
    public UltraDamage() {
        this.classID = 2;
        this.className = "UltraDamage";
    }
    @Override
    public void activatedAbility() {
        this.isPlayerHoldingStick();
        if(!checkCooldown()){
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,100,0));
        cooldownTime = System.currentTimeMillis();
    }

    @Override
    public void playerHitAbility(Player attackedPlayer) {
        attackedPlayer.damage(4);
    }
}
