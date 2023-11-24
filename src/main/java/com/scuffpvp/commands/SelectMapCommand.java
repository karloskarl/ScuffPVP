package com.scuffpvp.commands;

import com.scuffpvp.player.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Class that controls the /map command
 */
public class SelectMapCommand implements CommandExecutor {
    /**
     * The player manager object.
     */
    private final PlayerManager playerManager;

    /**
     * Creates the controller object
     * @param playerManager The playermanager object to be use.
     */
    public SelectMapCommand(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * Controls the /map command
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return Whether the command ran successfully.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
