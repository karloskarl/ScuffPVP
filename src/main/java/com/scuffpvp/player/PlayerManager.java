package com.scuffpvp.player;

import com.scuffpvp.ScuffPVP;
import com.scuffpvp.player.PlayerData;
import org.bukkit.Bukkit;
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

    /**
     * Initializes the HashMap that pairs a player with a PlayerData object
     */
    public PlayerManager(){
        playerMap = new HashMap<>();
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
            getPlayerData(player).getPlayerClass().generateClassItems().forEach((k,v) -> player.getInventory().setItem(k, v));
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
}
