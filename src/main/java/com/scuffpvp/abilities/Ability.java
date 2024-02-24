package com.scuffpvp.abilities;

import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

import static org.bukkit.Material.GUNPOWDER;


/**
 * ScuffPVP ability interface.
 */
public abstract class Ability implements Comparable<Ability>{
    private Player caster;
    private PlayerManager playerManager;
    private int priority;
    private long cooldown;
    private long timeOfUse;

    public Ability(Player caster, int cooldown, int priority, PlayerManager playerManager){
        this.caster = caster;
        this.cooldown = cooldown;
        this.priority = priority;
        this.playerManager = playerManager;
    }
    /**
     * Activates the ability.
     */
    public void activate() {
        caster.getInventory().setItem(priority,new ItemStack(GUNPOWDER, (int) (cooldown/20)));
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
        setCooldownItems();
    }

    public void setCooldownItems(){
        long ticksRemaining = getCooldown() - getTimeOfUse();
        int secondsRemaining = (int) (ticksRemaining/20);
        if (ticksRemaining % 20 == 0 && ticksRemaining > 0) {
            caster.getInventory().setItem(priority,new ItemStack(GUNPOWDER,secondsRemaining));
        }
        if (ticksRemaining == 0) {
            caster.getInventory().setItem(priority,getItems()[0]);
        }
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

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
