package me.luna.lunapvp;

import org.bukkit.entity.Player;

public class Generator extends AbilityTemplate{

    @Override
    public void activatedAbility() {
        if(!checkCooldown()){
            return;
        }
    }

    @Override
    public void playerHitAbility(Player attackedPlayer) {
        for(int i = -5;i < 5; i++){

        }
    }
}
