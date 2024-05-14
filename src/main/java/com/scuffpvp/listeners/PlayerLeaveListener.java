package com.scuffpvp.listeners;

import com.scuffpvp.controllers.GameController;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    private GameController gameController;

    public PlayerLeaveListener(GameController gameController) {
        this.gameController = gameController;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if(Bukkit.getOnlinePlayers().isEmpty()){
            gameController.endGame();
        }
    }
}
