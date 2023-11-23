package com.scuffpvp.classes;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ClassManager {
    private Map<String, Class> playerClasses;

    public ClassManager() {
        this.playerClasses = new HashMap<>();
    }

    public void assignClass(Player player, Class playerClass) {
        playerClasses.put(player.getName(), playerClass);
    }

    public Class getPlayerClass(Player player) {
        return playerClasses.get(player.getName());
    }
}
