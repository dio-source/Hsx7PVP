package xyz.tomzog.playerClasses;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Cannon extends AbilityTemplate{
    public Cannon() {
        this.classID = 7;
        this.className = "Cannon";
    }
    @Override
    public void playerHitAbility(Player attackedPlayer) {
        if(!checkCooldown()){
            return;
        }
        Location playerLocation = attackedPlayer.getLocation();
        playerLocation.getWorld().createExplosion(playerLocation,2,true);
        cooldownTime = System.currentTimeMillis();
    }

    @Override
    public void activatedAbility() {
        if(!checkCooldown()){
            return;
        }
        player.getWorld().createExplosion(player.getLocation().add(player.getFacing().getDirection().multiply(5)),2.5f,true);
        cooldownTime = System.currentTimeMillis();
    }
}
