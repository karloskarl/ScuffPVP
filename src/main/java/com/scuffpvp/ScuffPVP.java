package com.scuffpvp;

import com.scuffpvp.listeners.*;
import com.scuffpvp.commands.SelectClassCommand;
import com.scuffpvp.commands.SelectMapCommand;
import com.scuffpvp.commands.StartGameCommand;
import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.TickScheduler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Main plugin class
 */
public class ScuffPVP extends JavaPlugin {
    public static final Location SPAWN_AREA = new Location(Bukkit.getWorld("ScuffPVP"),30,-60,-14);
    public static final Location CLASS_SELECT = new Location(Bukkit.getWorld("ScuffPVP"),30,-60,-24);
    public static final Location CAGE_MATCH = new Location(Bukkit.getWorld("ScuffPVP"),20,-60,-24);


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

        getServer().getPluginManager().registerEvents(new InteractionListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new JoinListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new ItemDropListener(playerManager),this);

        new TickScheduler(playerManager).runTaskTimer(this, 0L,1L);

        for(Player player : Bukkit.getOnlinePlayers()){
            playerManager.assignPlayer(player);
            player.teleport(SPAWN_AREA);
            player.setGameMode(GameMode.ADVENTURE);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PotionEffect.INFINITE_DURATION, 100, true));
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