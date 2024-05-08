package com.scuffpvp.listeners;

import com.scuffpvp.ScuffPVP;
import com.scuffpvp.controllers.GameController;
import com.scuffpvp.controllers.MapController;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {
    private final GameController gameController;

    public PlayerRespawnListener(GameController gameController) {
        this.gameController = gameController;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if(PlayerManager.isGameRunning()) {
            e.setRespawnLocation(gameController.respawn());
        }
        else{
            e.setRespawnLocation(ScuffPVP.SPAWN_AREA);
        }
    }
}
