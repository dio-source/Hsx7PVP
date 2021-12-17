package me.luna.lunapvp;

import java.util.Random;
import org.bukkit.entity.Player;

public class playerInstance {
	private AbilityTemplate ability;
	private boolean playerDead = false;
	private Player inGamePlayer;
	private String teamID = "";
	
	protected playerInstance() {
		Random rand = new Random();
		int randomInt = rand.nextInt(999) * rand.nextInt(999) *rand.nextInt(999);
		this.teamID = Integer.toBinaryString(randomInt);
	}
	protected void setPlayerDeathStatus(boolean isPlayerDead) {
		this.playerDead = isPlayerDead;
	}
	protected void updateClassDetails(Player p, AbilityTemplate ability) {
		this.ability = ability;
		this.ability.player = p;
		this.inGamePlayer = p;
	}
	protected boolean isPlayerDead() {
		return this.playerDead;
	}
	
	protected Player getPlayer() {
		return this.inGamePlayer;
	}
	
	protected AbilityTemplate getAbility() {
		return ability;
	}
}
