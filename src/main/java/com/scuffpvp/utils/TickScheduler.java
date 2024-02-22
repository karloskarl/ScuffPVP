package com.scuffpvp.utils;

import com.scuffpvp.abilities.Ability;
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
     * The number of game ticks that have occured since plugin load.
     */
    private long gameTimer;

    /**
     * The player manager of the plugin.
     */
    private PlayerManager playerManager;

    /**
     * Constructor with playermanager instance being passed in.
     */
    public TickScheduler(PlayerManager playerManager){
        this.playerManager = playerManager;
        gameTimer = 0L;
    }

    /**
     * Runs every game tick
     */
    @Override
    public void run() {

        gameTimer++;
    }

    /**
     * Checks the ability cooldowns of all players
     */
    public void checkCoolDowns(){
        for(Player player : Bukkit.getOnlinePlayers()){
            PlayerData playerData = playerManager.getPlayerData(player);
            Map<Ability,Long> cooldowns = playerData.getAllCoolDowns();
            for(Ability ability : cooldowns.keySet()){
                if(playerManager.getPlayerData(player).checkCoolDown(ability)){

                }
            }
        }
    }
}
