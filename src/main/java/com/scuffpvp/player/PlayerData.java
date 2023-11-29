package com.scuffpvp.player;

import com.scuffpvp.abilities.Ability;
import com.scuffpvp.classes.Class;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores additional data for players, that is not included in the spigot Player object
 */
public class PlayerData {
    private Map<Ability,Long> cooldowns;
    private Class chosenClass;
    private Player player;

    /**
     * Initializes a new HashMap to store the player's cooldown timers
     * Specifies which player is represented by this instance of PlayerData
     * @param player the player to represent
     */
    public PlayerData(Player player){
        cooldowns = new HashMap<>();
        this.player = player;
    }

    /**
     * Initializes a cooldown timer with a given name
     * @param ability ability to cool down
     */
    public void setCoolDown(Ability ability){
        cooldowns.put(ability,System.currentTimeMillis());
    }

    /**
     * Checks if a given cooldown timer has reached the cooldown time
     * @param ability ability to cool down
     * @return true or false whether the timer has completed or not
     */
    public boolean checkCoolDown(Ability ability){
        Long firstTime = cooldowns.get(ability);
        if(firstTime == null){
            setCoolDown(ability);
            return true;
        }
        long secondTime = System.currentTimeMillis();
        player.sendMessage(String.valueOf(firstTime - secondTime));
        return secondTime - firstTime >= ability.getCooldown();
    }

    /**
     * Returns the class of the player
     * @return the player class
     */
    public Class getPlayerClass() {
        return chosenClass;
    }

    /**
     * Sets the class of the player
     * @param playerClass class to give the player
     */
    public void setClass(Class playerClass) {
        chosenClass = playerClass;
    }

    /**
     * Removes the class from the player
     */
    public void clearClass() {
        chosenClass = null;
    }

    /**
     * Returns the map of cooldowns
     * @return the map of cooldowns
     */
    public Map<Ability,Long> getAllCoolDowns(){
        return cooldowns;
    }

    public void onDeath(){
        cooldowns.forEach((k,v) -> cooldowns.put(k,(long)-1));
    }
}
