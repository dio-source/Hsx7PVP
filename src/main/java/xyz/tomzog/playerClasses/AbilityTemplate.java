package xyz.tomzog.playerClasses;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import xyz.tomzog.lunapvp.main;
import xyz.tomzog.lunapvp.EventHandler;

public class AbilityTemplate {
    long cooldownTime = 0;
    protected Player player;
    String className = "";
    main plugin;
    int classID = 0;

    public void setPlugin(main p) {
        this.plugin = p;
    }
    public void activatedAbility(){

    }

    protected boolean isPlayerHoldingStick() {
        if (player.getInventory().getItemInMainHand().getType() == Material.STICK) {
            return true;
        }
        return false;
    }
    public void playerHitAbility(Player attackedPlayer){

    }
    public void setPlayer(Player p) {
        this.player = p;
    }
    public String getClassName() {
        return this.className;
    }
    public Player getPlayer(){
        return this.player;
    }
    protected boolean checkCooldown(){

        System.out.println(System.currentTimeMillis() - cooldownTime);
        if(System.currentTimeMillis() - cooldownTime > 10000){
            return true;
        }
        return false;
    }
}
