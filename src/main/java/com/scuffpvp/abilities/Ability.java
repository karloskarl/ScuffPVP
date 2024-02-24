package com.scuffpvp.abilities;

import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;


/**
 * ScuffPVP ability interface.
 */
public abstract class Ability implements Comparable<Ability>{
    private Player caster;
    private int priority;
    private long cooldown;
    private long timeOfUse;

    public Ability(Player caster, int cooldown, int priority){
        this.caster = caster;
        this.cooldown = cooldown;
        this.priority = priority;
    }
    /**
     * Activates the ability.
     */
    public void activate() {

    }

    /**
     * Cleans up the ability once done.
     */
    public abstract void cleanup();

    /**
     * Operates every tick
     */
    public void tick() {
        incrementTimeOfUse();
    }

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

    public long getCooldown(){
        return cooldown;
    }

    public long getTimeOfUse(){
        return timeOfUse;
    }

    public void setTimeOfUse(long timeOfUse){
        this.timeOfUse = timeOfUse;
    }

    public void incrementTimeOfUse(){
        timeOfUse++;
    }

    @Override
    public int compareTo(Ability o) {
        return getPriority() - o.getPriority();
    }
}
