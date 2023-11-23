package com.scuffpvp.player;

import com.scuffpvp.ScuffPVP;
import com.scuffpvp.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    Map<Player, PlayerData> playerMap;

    public PlayerManager(){
        playerMap = new HashMap<>();
    }

    public void assignPlayer(Player player){
        playerMap.put(player,new PlayerData(player));
    }

    public void setItems() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            for(int i = 0; i < 40; i++){
                ItemStack currentItem = playerMap.get(player).getPlayerClass().getItems().get(i);
                if(currentItem != null) {
                    player.getInventory().setItem(i, currentItem);
                }
            }
        }
    }

    public PlayerData getPlayerData(Player player){
        return playerMap.get(player);
    }
}
