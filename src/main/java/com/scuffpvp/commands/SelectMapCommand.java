package com.scuffpvp.commands;

import com.scuffpvp.player.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SelectMapCommand implements CommandExecutor {
    private final PlayerManager playerManager;

    public SelectMapCommand(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
