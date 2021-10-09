package me.luna.lunapvp;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Medusa extends AbilityTemplate{

    public void playerHitAbility(Player attackedPlayer) {
        if(!checkCooldown()){
            return;
        }
        for(int z = -2; z < 2; z++){
            for(int x = -2; x < 2;x++){
                for(int y =-2; y < 2;y++){
                    player.getWorld().getBlockAt(attackedPlayer.getLocation().add(x,y,z)).setType(Material.STONE);
                }
            }
        }
        player.teleport(new Location(player.getWorld(), player.getLocation().getX(),player.getWorld().getHighestBlockYAt(player.getLocation()),player.getLocation().getZ()));
        cooldownTime = System.currentTimeMillis();
    }

    @Override
    public void activatedAbility() {
        player.getEyeLocation().getBlock().setType(Material.STONE);
    }
}
