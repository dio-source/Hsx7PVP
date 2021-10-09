package me.luna.lunapvp;

import org.bukkit.Location;
import org.bukkit.Material;

public class Miner extends AbilityTemplate{
    @Override
    public void activatedAbility() {
        if(!checkCooldown()){
            return;
        }
        for(int z = -3; z <3; z++){
            for(int x = -3; x < 3; x++){
                for(int y = -3; y < 3; y++){
                    if(z == 0 && x ==0){

                    }
                    else {
                        player.getWorld().getBlockAt(player.getLocation().add(x, y, z)).breakNaturally();
                    }
                }
            }
        }
        cooldownTime = System.currentTimeMillis();
    }
}
