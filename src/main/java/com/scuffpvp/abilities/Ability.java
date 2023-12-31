package com.scuffpvp.abilities;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;


/**
 * ScuffPVP ability interface.
 */
public abstract class Ability implements Comparable<Ability>{
    private Player caster;
    private int priority;
    private int cooldown;

    public Ability(Player caster, int cooldown, int priority){
        this.caster = caster;
        this.cooldown = cooldown;
        this.priority = priority;
    }
    /**
     * Activates the ability.
     */
    public abstract void activate();

    /**
     * Cleans up the ability once done.
     */
    public abstract void cleanup();

    /**
     * Gets the caster of the ability
     * @return The caster of the ability
     */
    public Player getCaster(){
        return caster;
    }

    /**
     * Gets the targets of the ability
     * @return the targets of the ability
     */
    public abstract Player[] getTargets();

    public abstract Map<Sound, Float> getSoundMix();

    public abstract ItemStack[] getItems();

    public int getPriority(){
        return priority;
    }

    public int getCooldown(){
        return cooldown;
    }

    @Override
    public int compareTo(Ability o) {
        return getPriority() - o.getPriority();
    }
}
