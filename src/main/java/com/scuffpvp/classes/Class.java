package com.scuffpvp.classes;

import com.scuffpvp.abilities.Ability;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public abstract class Class {
    private int health;
    private String name;
    private Map<Integer,ItemStack> items;
    private double speed;


    public Class(int health, String name, double speed) {
        this.health = health;
        this.name = name;
        this.items = generateClassItems();
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

    public double getSpeed() {
        return speed;
    }

}