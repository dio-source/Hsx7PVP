package me.luna.lunapvp;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashMap;

public class EventHandler implements Listener {
    private HashMap<Player,AbilityTemplate> playerAbilityHashMap;
    private HashMap<Player,String> playerTeamHashMap;
    public boolean isPVPAllowed = false;
    @org.bukkit.event.EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player || isPVPAllowed) {
            if(((Player) e.getEntity()).getInventory().getItemInMainHand().getType() != Material.STICK){
                return;
            }
            try{
                if(playerTeamHashMap.get( ((Player) e.getEntity())) == playerTeamHashMap.get((Player) e.getDamager())){
                    return;
                }
                playerAbilityHashMap.get((Player) e.getDamager()).playerHitAbility((Player) e.getEntity());
            }
            catch (Exception exception){
                System.out.println("Error in OnPlayerHIT");
            }
        }
        else if(!isPVPAllowed){
            e.setCancelled(true);
        }
    }
    protected void updateAbilityList(HashMap<Player, AbilityTemplate> abilityHashMap){
        this.playerAbilityHashMap = abilityHashMap;
    }

    protected void updateTeamList(HashMap<Player, String> teamHashMap){
        this.playerTeamHashMap = teamHashMap;
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

    @org.bukkit.event.EventHandler
    public void teleportPreventionEvent(PlayerTeleportEvent e){
        if(e.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE){
            e.setCancelled(true);
        }
    }
}
