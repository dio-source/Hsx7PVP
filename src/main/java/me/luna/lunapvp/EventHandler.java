package me.luna.lunapvp;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashMap;

public class EventHandler implements Listener {
    private HashMap<Player,AbilityTemplate> playerAbilityHashMap;
    private HashMap<Player,String> playerTeamHashMap;
    public boolean isPVPAllowed = false;
    @org.bukkit.event.EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player && isPVPAllowed) {
            Player damager = (Player) e.getDamager();
            Player reciever = (Player) e.getEntity();
            if(damager.getInventory().getItemInMainHand().getType() != Material.STICK){
                return;
            }
            try{
                if(playerTeamHashMap.get(reciever) == playerTeamHashMap.get(damager)){
                    playerAbilityHashMap.get(damager).playerHitAbility(reciever);
                    return;
                }
                playerAbilityHashMap.get(damager).playerHitAbility(reciever);
            }
            catch (Exception exception){
                System.out.println("Error in OnPlayerHIT");
            }
        }
        else if(e.getEntity() instanceof Player && e.getDamager() instanceof Player && !isPVPAllowed){
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
            return;
        }
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            this.playerAbilityHashMap.get(e.getPlayer()).activatedAbility();
        }
    }

    @org.bukkit.event.EventHandler
    public void teleportPreventionEvent(PlayerTeleportEvent e){
        if(e.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE){
            e.setCancelled(true);
        }
    }

    @org.bukkit.event.EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        p.setGameMode(GameMode.SPECTATOR);
    }
}
