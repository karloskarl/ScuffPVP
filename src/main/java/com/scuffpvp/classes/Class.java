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
    /**
     * Maximum health value
     */
    private int health;
    /**
     * Class name
     */
    private String name;
    /**
     * Class abilities
     */
    private ArrayList<Ability> abilities;
    /**
     * Class Movement speed
     */
    private double speed;


    /**
     * Class constructor
     * @param health Maximum health value
     * @param name Class name
     * @param speed Movement speed of the class
     */
    public Class(int health, String name, double speed) {
        this.health = health;
        this.name = name;
        abilities = new ArrayList<>();
    }

    /**
     * Returns the maximum health of the class.
     * @return The maximum health of the class.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the name of the class.
     * @return The name of the class.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the abilities of the class
     * @return The abilities of the class
     */
    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    /**
     * Returns the movement speed of the class
     * @return The movement speed of the class
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Returns the cooldown of a specific ability
     * @param ability The ability whose cooldown is checked
     * @return The cooldown of the specified ability
     */
    public Long getCoolDown(Ability ability){
        return ability.getCooldown();
    }

    /**
     * Generates the items for the class
     * @return The generated items in a map that represents the slot and the items
     */
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

    /**
     * adds an ability to a class
     * @param ability
     */
    public void addAbility(Ability ability){
        abilities.add(ability);
    }
}