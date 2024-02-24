package com.scuffpvp.listeners;

import com.scuffpvp.player.PlayerData;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    private final PlayerManager playerManager;

    public PlayerDeathListener(PlayerManager playerManger) {
        this.playerManager = playerManger;
    }

    /**
     * Listens for player death events
     * @param event The death event when it occurs
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        PlayerData playerData = playerManager.getPlayerData(event.getEntity());
    }
}
