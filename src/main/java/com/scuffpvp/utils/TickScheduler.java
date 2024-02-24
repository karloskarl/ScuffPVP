package com.scuffpvp.utils;

import com.scuffpvp.abilities.Ability;
import com.scuffpvp.classes.Class;
import com.scuffpvp.player.PlayerData;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

/**
 * BukkitRunnable whose run() method runs every tick.
 */
public class TickScheduler extends BukkitRunnable {
    /**
     * The player manager of the plugin.
     */
    private PlayerManager playerManager;

    /**
     * Constructor with playermanager instance being passed in.
     */
    public TickScheduler(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

    /**
     * Runs every game tick
     */
    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            if(playerManager.getPlayerData(player).getPlayerClass() != null) {
                for (Ability ability : playerManager.getPlayerData(player).getPlayerClass().getAbilities()) {
                    ability.tick();
                }
            }
        }
    }
}
