package com.scuffpvp.classes;

import com.scuffpvp.ScuffPVP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ClassManager {
    private Map<String, Class> playerClasses;
    private ScuffPVP plugin;

    public ClassManager(ScuffPVP plugin) {
        this.plugin = plugin;
        this.playerClasses = new HashMap<>();
    }

    public void assignClass(Player player, Class playerClass) {
        playerClasses.remove(player.getName());
        playerClasses.put(player.getName(), playerClass);
    }

    public void setItems() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            for(int i = 0; i < 40; i++){
                ItemStack currentItem = playerClasses.get(player.getName()).getItems().get(i);
                if(currentItem != null) {
                    player.getInventory().setItem(i, currentItem);
                }
            }
        }
    }

    public Class getPlayerClass(Player player) {
        return playerClasses.get(player.getName());
    }

    public void clearClass(Player player) {
        playerClasses.remove(player.getName());
    }
}