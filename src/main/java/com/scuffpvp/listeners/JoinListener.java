package com.scuffpvp.listeners;

import com.scuffpvp.ScuffPVP;
import com.scuffpvp.controllers.GameController;
import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.Utils;
import jdk.jshell.execution.Util;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Class that holds a listener that listens for players joining the server
 */
public class JoinListener implements Listener {
    private final PlayerManager playerManager;
    private final GameController gameController;

    /**
     * Creates the listener object
     * @param playerManager the playermanager object to use to assign
     */
    public JoinListener(PlayerManager playerManager, GameController gameController){
        this.playerManager = playerManager;
        this.gameController = gameController;
    }

    /**
     * Assigns the player to the specified playermanager object
     * @param event The event of a player joining the server.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        playerManager.assignPlayer(player);
        Utils.clearPotionEffects(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PotionEffect.INFINITE_DURATION, 100, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 100, true));
        player.getInventory().clear();
        playerManager.getPlayerData(player).setLives(0);
        playerManager.getPlayerData(player).clearClass();
        gameController.displayLivesUpdate();
        if(PlayerManager.isGameRunning()) {
            player.setGameMode(GameMode.SPECTATOR);
        } else {
            player.setGameMode(GameMode.ADVENTURE);
            player.teleport(ScuffPVP.SPAWN_AREA);
        }
    }
}