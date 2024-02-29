package com.scuffpvp;

import com.scuffpvp.commands.StartSelectionCommand;
import com.scuffpvp.commands.VoteCommand;
import com.scuffpvp.listeners.*;
import com.scuffpvp.commands.SelectClassCommand;
import com.scuffpvp.commands.StartGameCommand;
import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.MapController;
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
    public static final Location TEST_SPAWN_AREA = new Location(Bukkit.getWorld("ScuffPVP"),30,-60,-14);
    public static final Location TEST_CLASS_SELECT = new Location(Bukkit.getWorld("ScuffPVP"),30,-60,-24);
    public static final Location CAGE_MATCH = new Location(Bukkit.getWorld("ScuffPVP"),20,-60,-24);
    public static final Location ABANDONED_CASTLE = new Location(Bukkit.getWorld("ScuffPVP"),1087,81,20);
    public static final Location CHATEAU = new Location(Bukkit.getWorld("ScuffPVP"),2062,153,110);
    public static final Location SNOWY_VILLAGE = new Location(Bukkit.getWorld("ScuffPVP"),3127,212,144);
    public static final Location SPAWN_AREA = new Location(Bukkit.getWorld("ScuffPVP"),5028,245,28);

    /**
     * Method is run on plugin enable (or when using /reload)
     */
    @Override
    public void onEnable() {
        PlayerManager playerManager = new PlayerManager();
        MapController mapController = new MapController();

        //registers the commands as executors
        getCommand("class").setExecutor(new SelectClassCommand(playerManager, mapController));
        getCommand("start").setExecutor(new StartGameCommand(playerManager, mapController));
        getCommand("vote").setExecutor(new VoteCommand(mapController));
        getCommand("startSelection").setExecutor(new StartSelectionCommand(mapController));
        //registers the listeners
        getServer().getPluginManager().registerEvents(new InteractionListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new JoinListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new ItemDropListener(playerManager),this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(playerManager),this);

        new TickScheduler(playerManager, mapController).runTaskTimer(this, 0L,1L);

        for(Player player : Bukkit.getOnlinePlayers()){
            playerManager.assignPlayer(player);
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