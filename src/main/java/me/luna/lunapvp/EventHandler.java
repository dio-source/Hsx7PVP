package me.luna.lunapvp;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class EventHandler implements Listener {
    private HashMap<Player,AbilityTemplate> playerAbilityHashMap;
    private HashMap<Player,String> playerTeamHashMap;
    public EventHandler(HashMap<Player,AbilityTemplate> playerAbilityHashMap, HashMap<Player,String> playerTeamHashMap){
        this.playerAbilityHashMap = playerAbilityHashMap;
        this.playerTeamHashMap = playerTeamHashMap;
    }

    @org.bukkit.event.EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){
        if(e instanceof Player && e instanceof Player) {
            if(((Player) e).getInventory().getItemInMainHand().getType() != Material.STICK){
                return;
            }
            try{
                if(playerTeamHashMap.get( ((Player) e).getPlayer()) == playerTeamHashMap.get((Player) e.getDamager())){
                    return;
                }
                playerAbilityHashMap.get((Player) e.getDamager()).playerHitAbility((Player) e );
            }
            catch (Exception exception){
                System.out.println("Error in OnPlayerHIT");
            }
        }
    }
    protected void updateAbilityList(HashMap<Player, AbilityTemplate> ){

    }
    @org.bukkit.event.EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().getType() != Material.STICK){
            System.out.println("TEsT123123");
            return;
        }
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            this.playerAbilityHashMap.get(e.getPlayer()).activatedAbility();
            System.out.println("TEsT1");
        }
    }
}
