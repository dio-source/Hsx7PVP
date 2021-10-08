package me.luna.lunapvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class main extends JavaPlugin {
    private EventHandler eventHandler;
    private GameHandler gameHandler;
    private HashMap<Player, AbilityTemplate> playerAbilityHashMap;
    private HashMap<Player, String> playerTeamHashMap;

    public void onEnable() {
        playerAbilityHashMap = new HashMap<>();
        playerTeamHashMap = new HashMap<>();
        gameHandler = new GameHandler();
        this.getServer().getPluginManager().registerEvents(new EventHandler(playerAbilityHashMap,playerTeamHashMap), this);
    }

    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp() && label.equalsIgnoreCase("start")){
            gameHandler.startGameTimer(this);
            eventHandler.
            return true;
        }
        if(sender instanceof Player){
            if(label.equalsIgnoreCase("ability") && args.length != 0){
                if(args[0].equalsIgnoreCase("Gravity")){
                    this.playerAbilityHashMap.put((Player) sender, new Gravity());
                    return true;
                }
            }
        }
        return false;
    }
}
