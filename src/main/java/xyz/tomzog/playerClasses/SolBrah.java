package xyz.tomzog.playerClasses;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.Array;
import java.util.LinkedList;

public class SolBrah extends AbilityTemplate {

    Material[] rawBefe= new Material[7];
    Material[] litBefe= new Material[7];
    public SolBrah() {
        this.classID = 8;
        this.className = "SolBrah";
        createRawBefeArray();
        createLitBefeArray();
    }

    private void createRawBefeArray(){
        this.rawBefe[0] = Material.BEEF;
        this.rawBefe[1] = Material.CHICKEN;
        this.rawBefe[2]= Material.MUTTON;
        this.rawBefe[3] = Material.PORKCHOP;
        this.rawBefe[4] = Material.COD;
        this.rawBefe[5] = Material.SALMON;
        this.rawBefe[6] = Material.RABBIT;
    }

    private void createLitBefeArray(){
        this.litBefe[0] = Material.COOKED_BEEF;
        this.litBefe[1] = Material.COOKED_CHICKEN;
        this.litBefe[2]= Material.COOKED_MUTTON;
        this.litBefe[3] = Material.COOKED_PORKCHOP;
        this.litBefe[4] = Material.COOKED_COD;
        this.litBefe[5] = Material.COOKED_SALMON;
        this.litBefe[6] = Material.COOKED_RABBIT;
    }
    public void cookedMeat() {
        if (!checkCooldown()) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 500, 1));
        item.setAmount(item.getAmount() - 1);
        cooldownTime = System.currentTimeMillis();
    }

    public void rawMeat() {
        if (!checkCooldown()) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
        item.setAmount(item.getAmount() - 1);
        cooldownTime = System.currentTimeMillis();
    }

    public void activatedAbility() {
        Material mat = player.getInventory().getItemInMainHand().getType();
        if (!checkCooldown()) {
            return;
        }
        for(Material m : rawBefe){
            if(mat == m){
                rawMeat();
                return;
            }
        }

        for(Material m : litBefe){
            if(mat == m){
                rawMeat();
                return;
            }
        }
    }
}
