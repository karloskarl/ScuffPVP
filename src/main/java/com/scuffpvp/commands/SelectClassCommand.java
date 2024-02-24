package com.scuffpvp.commands;

import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.classes.*;
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
    private final PlayerManager playerManager;

    /**
     * Creates the controller object.
     * @param playerManager The player manager instance to be used.
     */
    public SelectClassCommand(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * Controls the behavior of the /class command.
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return Whether the command was run successfully.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            if(args.length == 0) {
                Utils.sendErrorMessage(player,"Command usage: /class <argument>\nPossible arguments: list, remove, show");
                return false;
            }
            switch (args[0].toLowerCase()) {
                case "bancroft" -> playerManager.getPlayerData(player).setClass(new Bancroft(player));
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
                        return true;
                    } else {
                        Utils.sendErrorMessage(player, "Cannot remove class that doesn't exist!");
                        return false;
                    }
                }
                case "show" -> {
                    if(playerManager.getPlayerData(player).getPlayerClass() != null) {
                        Utils.sendConfirmationMessage(player, playerManager.getPlayerData(player).getPlayerClass().getName());
                        return true;
                    } else {
                        Utils.sendErrorMessage(player, "Cannot show class that doesn't exist!");
                        return false;
                    }
                }
                case "help" -> {
                    Utils.sendConfirmationMessage(player,"Command usage: /class <argument>\nPossible arguments: list, remove, show");
                    return true;
                }
                default -> {
                    Utils.sendErrorMessage(player,"Choose one of the classes!");
                    return false;
                }
            }
            player.sendMessage("Chosen class: " + args[0]);
            return true;
        }
        return false;
    }
}
