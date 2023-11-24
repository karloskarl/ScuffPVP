package com.scuffpvp.classes;

import com.scuffpvp.abilities.Ability;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Class {
    private int health;
    private String name;
    private ArrayList<Ability> abilities;
    private double speed;


    public Class(int health, String name, double speed) {
        this.health = health;
        this.name = name;
        abilities = new ArrayList<>();
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public double getSpeed() {
        return speed;
    }

    public Integer getCoolDown(Ability ability){
        return ability.getCooldown();
    }

    public Map<Integer, ItemStack> generateClassItems() {
        Map<Integer, ItemStack> items = new HashMap<>();
        Collections.sort(abilities);
        int slot = 0;
        for (Ability ability : abilities) {
            for (ItemStack item : ability.getItems()) {
                items.put(slot, item);
                slot++;
            }
        }
        return items;
    }

    public void addAbility(Ability ability){
        abilities.add(ability);
    }
}