package me.luna.lunapvp;

import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class GameHandler {
    boolean hasGameStarted = false;

    private void startGame(main p){
        this.hasGameStarted = true;
        this.startGameTimer(p);
    }

    private void shrinkWorldBorder(World w, double size, long timeToShrink){
        w.getWorldBorder().setSize(size,timeToShrink);
    }

    protected void startGameTimer(main p){
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!(p.getServer().getWorld("world").getWorldBorder().getSize() > 500)){
                    shrinkWorldBorder(p.getServer().getWorld("world"),p.getServer().getWorld("world").getWorldBorder().getSize() / 2,120);
                }
                else{
                    shrinkWorldBorder(p.getServer().getWorld("world"),p.getServer().getWorld("world").getWorldBorder().getSize() - 100,120);
                }
            }
        }.runTaskTimer(p,20,1200);
    }
}
