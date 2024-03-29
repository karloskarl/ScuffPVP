package com.scuffpvp.commands;

import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.MapController;
import com.scuffpvp.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartSelectionCommand implements CommandExecutor {
    private MapController mapController;

    public StartSelectionCommand(MapController mapController) {
        this.mapController = mapController;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(mapController.isSelectionRunning()) {
            mapController.startVote();
            Utils.broadcastConfirmationMessage("Map vote started! Use /vote list to see the options and /vote <vote> to submit a vote.");
        } else {
            if (PlayerManager.isGameRunning()) {
                Utils.sendErrorMessage((Player) commandSender,"Game is running!");
                return true;
            }
            Utils.sendErrorMessage((Player) commandSender,"Selection process already running!");
        }
        return true;
    }
}
