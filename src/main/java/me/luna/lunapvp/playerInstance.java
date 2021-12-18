package me.luna.lunapvp;

import java.util.Random;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.luna.playerClasses.AbilityTemplate;

public class playerInstance {
	private AbilityTemplate ability;
	private boolean playerDead = false;
	private UUID playerUUID;
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
		this.ability.setPlayer(p);
		this.playerUUID = p.getUniqueId();
	}
	protected boolean isPlayerDead() {
		return this.playerDead;
	}
	protected String getTeamID() {
		return this.teamID;
	}
	protected void setTeamID(String team) {
		this.teamID = team;
	}
	protected UUID getPlayer() {
		return this.playerUUID;
	}
	
	protected AbilityTemplate getAbility() {
		return ability;
	}
}
