package com.scuffpvp;

import com.scuffpvp.classes.Bancroft;

import com.scuffpvp.classes.ClassManager;
import com.scuffpvp.classes.listeners.BancroftListener;
import com.scuffpvp.commands.SelectClassCommand;
import com.scuffpvp.commands.SelectMapCommand;
import com.scuffpvp.commands.StartGameCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ScuffPVP extends JavaPlugin {
    public static ClassManager classManager;
    @Override
    public void onEnable() {
        classManager = new ClassManager(this);
        //registers the commands as executors
        getCommand("class").setExecutor(new SelectClassCommand(classManager));
        getCommand("map").setExecutor(new SelectMapCommand(classManager));
        getCommand("start").setExecutor(new StartGameCommand(classManager));

        getServer().getPluginManager().registerEvents(new BancroftListener(classManager), this);

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                //Runs every single tick

            }
        };
        task.runTaskTimer(this, 0L, 1L);
    }
}