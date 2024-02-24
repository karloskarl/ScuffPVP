package com.scuffpvp.listeners;

import com.scuffpvp.player.PlayerManager;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDropListener implements Listener {
    private final PlayerManager playerManager;

    public ItemDropListener(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }
}
