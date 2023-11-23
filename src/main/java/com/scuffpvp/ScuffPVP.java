package com.scuffpvp;

import com.scuffpvp.classes.Bancroft;

import com.scuffpvp.classes.ClassManager;
import com.scuffpvp.classes.listeners.BancroftListener;
import com.scuffpvp.commands.SelectClassCommand;
import com.scuffpvp.commands.SelectMapCommand;
import com.scuffpvp.commands.StartGameCommand;
import com.scuffpvp.listeners.JoinListener;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ScuffPVP extends JavaPlugin {
    public static PlayerManager playerManager;
    public static int globalTimer;
    @Override
    public void onEnable() {
        classManager = new ClassManager(this);
        //registers the commands as executors
        getCommand("class").setExecutor(new SelectClassCommand(classManager));
        getCommand("map").setExecutor(new SelectMapCommand(classManager));
        getCommand("start").setExecutor(new StartGameCommand(classManager));

        getServer().getPluginManager().registerEvents(new BancroftListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new JoinListener(playerManager), this);
    }
}