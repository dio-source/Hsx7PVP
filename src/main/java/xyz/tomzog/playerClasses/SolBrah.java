package xyz.tomzog.playerClasses;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SolBrah extends AbilityTemplate {

    public SolBrah() {
        this.classID = 8;
        this.className = "SolBrah";
    }

    public void cookedMeat(){
        if (!checkCooldown()) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 500, 1));
        item.setAmount(item.getAmount() - 1);
        cooldownTime = System.currentTimeMillis();
    }

    public void rawMeat(){
        if (!checkCooldown()) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
        item.setAmount(item.getAmount() - 1);
        cooldownTime = System.currentTimeMillis();
    }

    public void activatedAbility() {
        if (!checkCooldown()) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
        cooldownTime = System.currentTimeMillis();
    }

    }
