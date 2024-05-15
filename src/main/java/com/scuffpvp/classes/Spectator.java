package com.scuffpvp.classes;

import com.scuffpvp.player.PlayerManager;
import org.bukkit.entity.Player;

public class Spectator extends Class{
    public Spectator(Player player, PlayerManager playerManager) {
        super(20, "Spectator", 0.7, playerManager);
    }
}
