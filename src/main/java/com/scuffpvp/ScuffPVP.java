package com.scuffpvp;

import com.scuffpvp.listeners.BancroftListener;
import com.scuffpvp.commands.SelectClassCommand;
import com.scuffpvp.commands.SelectMapCommand;
import com.scuffpvp.commands.StartGameCommand;
import com.scuffpvp.listeners.JoinListener;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ScuffPVP extends JavaPlugin {
    public static PlayerManager playerManager;
    public static int globalTimer;
    @Override
    public void onEnable() {
        playerManager = new PlayerManager();
        //registers the commands as executors
        getCommand("class").setExecutor(new SelectClassCommand(playerManager));
        getCommand("map").setExecutor(new SelectMapCommand(playerManager));
        getCommand("start").setExecutor(new StartGameCommand(playerManager));

        getServer().getPluginManager().registerEvents(new BancroftListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new JoinListener(playerManager), this);
    }
}