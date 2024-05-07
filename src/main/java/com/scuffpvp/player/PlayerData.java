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
    private Class chosenClass;
    private int lives;
    private Player player;

    /**
     * Initializes a new HashMap to store the player's cooldown timers
     * Specifies which player is represented by this instance of PlayerData
     * @param player the player to represent
     */
    public PlayerData(Player player){
        this.player = player;
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
     * Gets how many lives a player has
     * @return lives
     */
    public int getLives(){
        return lives;
    }

    /**
     * Sets the number of lives a player has
     * @param lives lives
     */
    public void setLives(int lives){
        this.lives = lives;
    }
}
