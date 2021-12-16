package me.luna.lunapvp;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
public class GameHandler {
    private void teleportPlayers(main p){
        for(Player player : p.getServer().getOnlinePlayers()){
            int highestBlock = p.getServer().getWorld("world").getHighestBlockYAt(0,0);
            player.teleport(new Location(player.getWorld(), 0,highestBlock,0));
        }
    }
    private void shrinkWorldBorder(World w, double size, long timeToShrink){
        w.getWorldBorder().setSize(size,timeToShrink);

    }

    private void resetPlayer(main plugin){
        for(Player p : plugin.getServer().getOnlinePlayers()){
            p.getInventory().clear();
            p.setHealth(20);
            p.setFoodLevel(25);
        }
    }
    protected void startGameTimer(main p){
    	AirDrop airdrop = new AirDrop();
        p.getServer().getWorld("world").getWorldBorder().setCenter(0,0);
        p.getServer().getWorld("world").getWorldBorder().setSize(2500);
        p.getServer().getWorld("world").getWorldBorder().setWarningDistance(50);
        p.getServer().getWorld("world").getWorldBorder().setWarningTime(20);
        teleportPlayers(p);
        airdrop.w = p.getServer().getWorld("world");
        resetPlayer(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                if((p.getServer().getWorld("world").getWorldBorder().getSize() > 500)){
                    shrinkWorldBorder(p.getServer().getWorld("world"),p.getServer().getWorld("world").getWorldBorder().getSize() / 2,120);
                }
                else{
                    shrinkWorldBorder(p.getServer().getWorld("world"),p.getServer().getWorld("world").getWorldBorder().getSize() - 100,120);
                }
                String worldBoderSizeString = Double.toString(p.getServer().getWorld("world").getWorldBorder().getSize());
                airdrop.spawnAirDrop(p.getServer());
                p.getServer().broadcastMessage("Worldborder: " + worldBoderSizeString + "\nHead To 0,0");
            }
        }.runTaskTimer(p,20,8400);
    }
}
