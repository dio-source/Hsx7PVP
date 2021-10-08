package me.luna.lunapvp;

import org.bukkit.entity.Player;

public class AbilityTemplate {
    float cooldownTime = 0;
    Player player;



    public void activatedAbility(){

    }

    public void playerHitAbility(Player attackedPlayer){

    }

    protected boolean checkCooldown(){
        if(System.currentTimeMillis() - cooldownTime > 10000){
            System.out.println("TEST1111");
            return true;
        }
        return false;
    }
}
