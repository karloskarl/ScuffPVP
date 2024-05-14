package com.scuffpvp.commands;

import com.scuffpvp.classes.Spectator;
import com.scuffpvp.player.PlayerData;
import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.controllers.MapController;
import com.scuffpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartSelectionCommand implements CommandExecutor {
    private MapController mapController;
    private PlayerManager playerManager;

    public StartSelectionCommand(MapController mapController, PlayerManager playerManager) {
        this.mapController = mapController;
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!MapController.getSelectedMap().isBlank()){
            Utils.sendErrorMessage((Player) commandSender,"The map has already been selected! YOU STUPID STUPID FUCKING FUCKASS PIECE OF FUCKING SHIT");
        }
        else if(!mapController.isSelectionRunning()) {
            mapController.clear();
            mapController.startVote();
            for(Player p : Bukkit.getOnlinePlayers()){
                PlayerData playerData = playerManager.getPlayerData(p);
                if(playerData.getPlayerClass() == null){
                    playerData.setClass(new Spectator(p, playerManager));
                }
            }
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
