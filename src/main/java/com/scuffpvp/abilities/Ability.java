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

    /**
     * Creates an ability object
     * @param caster the caster of the ability
     * @param cooldown the cooldown length in ticks
     * @param priority the item slot of the ability
     * @param playerManager the player manager object
     */
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
        //sets the item to gunpowder right after activation
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

    /**
     * Sets the necessary amount of cooldown items (gunpowder)
     */
    public void setCooldownItems(){
        long ticksRemaining = getCooldown() - getTimeOfUse();
        if(PlayerManager.isGameRunning()) {
            if (ticksRemaining % 20 == 0 && ticksRemaining > 0) {
                caster.getInventory().setItem(priority, new ItemStack(GUNPOWDER, (int) (ticksRemaining/20)));
            }
            if (ticksRemaining == 0) {
                caster.getInventory().setItem(priority, getItems()[0]);
            }
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

    /**
     * Returns the sound mix of the ability
     * @return
     */
    public abstract Map<Sound, Float> getSoundMix();

    /**
     * Returns the items of the ability
     * @return the items of the ability
     */
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
