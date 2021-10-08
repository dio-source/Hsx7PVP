package me.luna.lunapvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class main extends JavaPlugin {
    private EventHandler eventHandler;
    private GameHandler gameHandler;
    private HashMap<Player, AbilityTemplate> playerAbilityHashMap;
    private HashMap<Player, String> playerTeamHashMap;

    public void onEnable() {
        playerAbilityHashMap = new HashMap<>();
        playerTeamHashMap = new HashMap<>();
        eventHandler = new EventHandler();
        gameHandler = new GameHandler();
        this.getServer().getPluginManager().registerEvents(eventHandler, this);
    }

    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp() && label.equalsIgnoreCase("start")){
            gameHandler.startGameTimer(this);
            eventHandler.updateAbilityList(playerAbilityHashMap);
            eventHandler.updateTeamList(playerTeamHashMap);
            new BukkitRunnable() {
                @Override
                public void run() {
                    eventHandler.isPVPAllowed = true;
                    getServer().broadcastMessage("PVP has been enabled");
                }
            }.runTaskLater(this,20);
            return true;
        }
        if(sender instanceof Player){
            if(label.equalsIgnoreCase("ability") && args.length != 0){
                if(args[0].equalsIgnoreCase("Gravity")){
                    Gravity newObjectGravity = new Gravity();
                    newObjectGravity.player = (Player) sender;
                    this.playerAbilityHashMap.put((Player) sender, newObjectGravity);
                    sender.sendMessage("You have picked Gravity");
                    return true;
                }
                else if(args[0].equalsIgnoreCase("Cannon")){
                    Cannon cannonObject = new Cannon();
                    cannonObject.player = (Player) sender;
                    this.playerAbilityHashMap.put((Player) sender, cannonObject);
                    sender.sendMessage("You have picked Cannon");
                    return true;
                }
                else if(args[0].equalsIgnoreCase("Ghost")){
                    Ghost ghostObject = new Ghost();
                    ghostObject.player = (Player) sender;
                    ghostObject.setPlugin(this);
                    this.playerAbilityHashMap.put((Player) sender, ghostObject);
                    sender.sendMessage("You have picked Ghost");
                    return true;
                }
                else if(args[0].equalsIgnoreCase("UltraDamage")){
                    UltraDamage ulatraDamageObject = new UltraDamage();
                    ulatraDamageObject.player = (Player) sender;
                    this.playerAbilityHashMap.put((Player) sender, ulatraDamageObject);
                    sender.sendMessage("You have picked UltraDamage");
                    return true;
                }
            }
            else if(label.equalsIgnoreCase("team") && args.length != 0){
                this.playerTeamHashMap.put((Player) sender, args[0]);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if(command.getName().equalsIgnoreCase("ability") && args.length >= 0){
            if(sender instanceof Player){
                list.add("Gravity");
                list.add("Ghost");
                list.add("Cannon");
                list.add("UltraDamage");
            }
        }
        return list;
    }
}
