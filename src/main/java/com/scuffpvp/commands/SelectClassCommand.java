package com.scuffpvp.commands;

import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.classes.*;
import com.scuffpvp.controllers.MapController;
import com.scuffpvp.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class that controls the /class command
 */
public class SelectClassCommand implements CommandExecutor {
    /**
     * The player manager object.
     */
    private PlayerManager playerManager;
    private MapController mapController;

    /**
     * Creates the controller object.
     * @param playerManager The player manager instance to be used.
     */
    public SelectClassCommand(PlayerManager playerManager, MapController mapController) {
        this.playerManager = playerManager;
        this.mapController = mapController;
    }

    /**
     * Controls the behavior of the /class command.
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return value not used
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(mapController.isSelectionRunning() && sender instanceof Player player) {
            if(args.length == 0) {
                Utils.sendErrorMessage(player,"Command usage: /class <argument>\nPossible arguments: list, remove, show, <class name>");
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "bancroft" -> playerManager.getPlayerData(player).setClass(new Bancroft(player, playerManager));
                case "list" -> {
                    player.sendMessage("""
                            Hansa
                            Bancroft
                            Mirabelle
                            Suzuka
                            Esteille
                            Glass""");
                    return true;
                }
                case "remove" -> {
                    if(playerManager.getPlayerData(player).getPlayerClass() != null) {
                        playerManager.getPlayerData(player).clearClass();
                        Utils.sendConfirmationMessage(player,"Removed class.");
                    } else {
                        Utils.sendErrorMessage(player, "Cannot remove class that doesn't exist!");
                    }
                    return true;
                }
                case "show" -> {
                    if(playerManager.getPlayerData(player).getPlayerClass() != null) {
                        Utils.sendConfirmationMessage(player, playerManager.getPlayerData(player).getPlayerClass().getName());
                    } else {
                        Utils.sendErrorMessage(player, "Cannot show class that doesn't exist!");
                    }
                    return true;
                }
                case "help" -> {
                    Utils.sendConfirmationMessage(player,"Command usage: /class <argument>\nPossible arguments: list, remove, show");
                    return true;
                }
                default -> {
                    Utils.sendErrorMessage(player,"Choose one of the classes!");
                    return true;
                }
            }
            Utils.broadcastConfirmationMessage(player.getName() + " has chosen the class " + args[0]);
        } else {
            Utils.sendErrorMessage((Player) sender, "Class selection is not active!");
        }
        return true;
    }
}
