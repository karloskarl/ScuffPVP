package com.scuffpvp;

import com.scuffpvp.listeners.ItemInteractionListener;
import com.scuffpvp.commands.SelectClassCommand;
import com.scuffpvp.commands.SelectMapCommand;
import com.scuffpvp.commands.StartGameCommand;
import com.scuffpvp.listeners.JoinListener;
import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.TickScheduler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class
 */
public class ScuffPVP extends JavaPlugin {
    /**
     * The playerManager instance controlling the server.
     */
    public static PlayerManager playerManager;

    /**
     * Method is run on plugin enable (or when using /reload)
     */
    @Override
    public void onEnable() {
        playerManager = new PlayerManager();
        //registers the commands as executors
        getCommand("class").setExecutor(new SelectClassCommand(playerManager));
        getCommand("map").setExecutor(new SelectMapCommand(playerManager));
        getCommand("start").setExecutor(new StartGameCommand(playerManager));

        getServer().getPluginManager().registerEvents(new ItemInteractionListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new JoinListener(playerManager), this);
        new TickScheduler(playerManager).runTaskTimer(this, 0L,1L);

        for(Player player : Bukkit.getOnlinePlayers()){
            playerManager.assignPlayer(player);
        }
    }

    /**
     * Method is run on plugin disable
     */
    @Override
    public void onDisable() {
        super.onDisable();
    }
}