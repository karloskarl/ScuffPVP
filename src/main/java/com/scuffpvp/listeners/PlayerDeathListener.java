package com.scuffpvp.listeners;

import com.scuffpvp.controllers.GameController;
import com.scuffpvp.player.PlayerData;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    private final PlayerManager playerManager;
    private final GameController gameController;

    public PlayerDeathListener(PlayerManager playerManger, GameController gameController) {
        this.playerManager = playerManger;
        this.gameController = gameController;
    }

    /**
     * Listens for player death events
     * @param event The death event when it occurs
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        PlayerData playerData = playerManager.getPlayerData(player);
        if(PlayerManager.isGameRunning()) {
            playerData.setLives(playerData.getLives() - 1);
            if (playerData.getLives() == 0) {
                player.setGameMode(GameMode.SPECTATOR);
            }
            gameController.winCheck();
        }
    }
}
