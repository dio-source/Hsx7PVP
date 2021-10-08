package me.luna.lunapvp;

import org.bukkit.entity.Player;

public class AbilityTemplate {
    long cooldownTime = 0;
    Player player;



    public void activatedAbility(){

    }

    public void playerHitAbility(Player attackedPlayer){

    }

    protected boolean checkCooldown(){

        System.out.println(System.currentTimeMillis() - cooldownTime);
        if(System.currentTimeMillis() - cooldownTime > 10000){
            return true;
        }
        return false;
    }
}
