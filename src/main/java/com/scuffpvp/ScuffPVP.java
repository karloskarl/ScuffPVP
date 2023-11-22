package com.scuffpvp;

import com.scuffpvp.commands.SelectClassCommand;
import com.scuffpvp.commands.SelectMapCommand;
import com.scuffpvp.commands.StartGameCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ScuffPVP extends JavaPlugin {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Override
    public void onEnable() {
        //registers the commands as executors
        this.getCommand("class").setExecutor(new SelectClassCommand());
        this.getCommand("map").setExecutor(new SelectMapCommand());
        this.getCommand("startGame").setExecutor(new StartGameCommand());

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                //Runs every single tick

            }
        };
        task.runTaskTimer(this, 0L, 1L);
    }
}