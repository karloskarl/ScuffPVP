package com.scuffpvp.commands;

import com.scuffpvp.abilities.Ability;
import com.scuffpvp.classes.Class;
import com.scuffpvp.classes.Spectator;
import com.scuffpvp.controllers.GameController;
import com.scuffpvp.player.PlayerData;
import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.controllers.MapController;
import com.scuffpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class to control the /start command
 */
public class StartGameCommand implements CommandExecutor {
    private PlayerManager playerManager;
    private MapController mapController;
    private GameController gameController;

    /**
     * Creates the command object.
     */
    public StartGameCommand(PlayerManager playerManager, MapController mapController, GameController gameController) {
        this.playerManager = playerManager;
        this.mapController = mapController;
        this.gameController = gameController;
    }

    /**
     * Starts the game.
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return value not used
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(Bukkit.getOnlinePlayers().size() <= 1) {
            Utils.sendErrorMessage((Player) sender, "heh, loser");
            mapController.setMap("");
            return true;
        }
        if(PlayerManager.isGameRunning()) {
            Utils.sendErrorMessage((Player) sender, "Game already started!");
            return true;
        }
        if(!mapController.isSelectionRunning() ) {
            Utils.sendErrorMessage((Player) sender, "Map selection isn't running!");
            return true;
        }
        int spectatorCount = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerData playerData = playerManager.getPlayerData(player);
            Class playerClass = playerData.getPlayerClass();
            if(playerClass instanceof Spectator) {
                spectatorCount++;
                if(spectatorCount >= Bukkit.getOnlinePlayers().size() - 1) {
                    Utils.broadcastErrorMessage("To few people have chosen a class!");
                    gameController.endGame();
                    return true;
                }
            } else {
                for (Ability ability : playerClass.getAbilities()) {
                    ability.setTimeOfUse(ability.getCooldown());
                }
                Utils.clearPotionEffects(player);
            }
        }
        Location mapLocation = MapController.getSelectedMapLocation();
        if(mapLocation == null) {
            mapController.terminateVotingProcess();
            return true;
        }
        playerManager.setGameRunning(true);
        gameController.displayLivesUpdate();
        Utils.broadcastConfirmationMessage("Game started!");
        playerManager.setItems();
        gameController.startGame();
        return true;
    }
}
