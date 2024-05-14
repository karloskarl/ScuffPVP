package com.scuffpvp.player;

import com.scuffpvp.ScuffPVP;
import com.scuffpvp.abilities.Ability;
import com.scuffpvp.classes.Class;
import com.scuffpvp.classes.Spectator;
import com.scuffpvp.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps each player to a PlayerData object, created to more easily assign additional data
 * that is not included in the spigot Player object
 */
public class PlayerManager {
    /**
     * Maps each player to a PlayerData object
     */
    private Map<Player, PlayerData> playerMap;
    private static boolean gameRunning;

    /**
     * Initializes the HashMap that pairs a player with a PlayerData object
     */
    public PlayerManager(){
        playerMap = new HashMap<>();
        gameRunning = false;
    }

    /**
     * Puts a player into the map. Assigns an empty PlayerData object
     * @param player to be put into the map
     */
    public void assignPlayer(Player player){
        playerMap.put(player,new PlayerData(player));
    }

    /**
     * Uses the class information in PlayerData to give every player in the world their items
     */
    public void setItems() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            Class playerClass = getPlayerData(player).getPlayerClass();
            playerClass.generateClassItems().forEach((k,v) -> player.getInventory().setItem(k, v));
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerClass.getHealth());
            player.setHealth(playerClass.getHealth());
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(playerClass.getSpeed());
            if(playerClass instanceof Spectator){
                player.setGameMode(GameMode.SPECTATOR);
            }
        }
    }

    /**
     * Returns the PlayerData object assigned to the specified player
     * @param player the player to retrieve PlayerData of
     * @return PlayerData
     */
    public PlayerData getPlayerData(Player player){
        return playerMap.get(player);
    }

    /**
     * Returns if the game is currently running or not
     * @return if the game is currently running or not
     */
    public static boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Sets whether the game is running or not
     * @param running whether the game is running
     */
    public static void setGameRunning(boolean running) {
        gameRunning = running;
    }
}
