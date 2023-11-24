package com.scuffpvp.commands;

import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Class to control the /start command
 */
public class StartGameCommand implements CommandExecutor {
    private final PlayerManager playerManager;

    /**
     * Creates the command object.
     */
    public StartGameCommand(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * Starts the game.
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return Whether the command was succesfully used.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            playerManager.setItems();
        } catch (NullPointerException E){
            Utils.broadcastErrorMessage("stupid idot, not all prlayer s have class");
        }
        return true;
    }
}
