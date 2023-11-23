package com.scuffpvp.classes;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public abstract class Class implements Listener {
    private int health;
    private String name;
    private Map<Integer,ItemStack> items;

    public Class(int health, String name) {
        this.health = health;
        this.name = name;
        this.items = generateClassItems();
    }

    void setItems(Player player) {
        for(int i = 0; i < 40; i++){
            ItemStack currentItem = items.get(i);
            if(currentItem != null) {
                player.getInventory().setItem(i, currentItem);
            }
        }
    }

    public abstract Map<Integer, ItemStack> generateClassItems();

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, ItemStack> getItems() {
        return items;
    }
}