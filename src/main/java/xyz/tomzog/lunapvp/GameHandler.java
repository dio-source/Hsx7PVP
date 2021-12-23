package xyz.tomzog.lunapvp;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class GameHandler {
    private WorldBorder worldBorder;
    private main plugin;
    private Server server;
    private World world;
    public GameHandler(main p) {
        this.server = p.getServer();
        this.world = p.getServer().getWorld("world");
        this.worldBorder = p.getServer().getWorld("world").getWorldBorder();
        this.plugin = p;
    }

    private void teleportPlayers(){
        for(Player player : server.getOnlinePlayers()){
            int highestBlock = server.getWorld("world").getHighestBlockYAt(0,0);
            player.teleport(new Location(player.getWorld(), 0,highestBlock + 1,0));
        }
    }

    private void shrinkWorldBorder(double size, long timeToShrink){
        worldBorder.setSize(size,timeToShrink);

    }

    private void resetPlayer(main plugin){
        for(Player p : server.getOnlinePlayers()){
            p.getInventory().clear();
            p.setHealth(20);
            p.setFoodLevel(25);
        }
    }
    private void initializeWorldBorder() {
        worldBorder.setCenter(0,0);
        worldBorder.setSize(2500);
        worldBorder.setWarningDistance(50);
        worldBorder.setWarningTime(20);
    }

    private void shrinkBorder() {
        if((worldBorder.getSize() > 500)){
            shrinkWorldBorder(worldBorder.getSize() / 2,300);
        }
        shrinkWorldBorder(worldBorder.getSize() - 100,120);
    }
    protected void startGameTimer(main p){
        AirDrop airdrop = new AirDrop();
        initializeWorldBorder();
        teleportPlayers();
        airdrop.w = world;
        resetPlayer(p);
        teleportPlayersEnd(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                shrinkBorder();
                String worldBoderSizeString = Double.toString(worldBorder.getSize());
                airdrop.spawnAirDrop(p.getServer());
                p.getServer().broadcastMessage("Worldborder: " + worldBoderSizeString + "\nHead To 0,0");
            }
        }.runTaskTimer(p,20,8400);
    }
    public void teleportPlayersEnd(main p) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(p, () -> {

            for(Player player : server.getOnlinePlayers()) {
                Random rand = new Random();
                int randomX = rand.nextInt(50 * 2) - 50;
                int randomZ = rand.nextInt(50 * 2) - 50;
                int y = server.getWorld("world_the_end").getHighestBlockYAt(randomX, randomZ);
                player.teleport(new Location(server.getWorld("world_the_end"),randomX, y, randomZ));
            }


        }, 26400);
    }
}