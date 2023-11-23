package com.scuffpvp.listeners;

import com.scuffpvp.player.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final PlayerManager playerManager;

    public JoinListener(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        playerManager.assignPlayer(event.getPlayer());
    }
}
