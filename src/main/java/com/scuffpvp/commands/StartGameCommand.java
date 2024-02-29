package com.scuffpvp.commands;

import com.scuffpvp.ScuffPVP;
import com.scuffpvp.abilities.Ability;
import com.scuffpvp.player.PlayerData;
import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.MapController;
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

    /**
     * Creates the command object.
     */
    public StartGameCommand(PlayerManager playerManager, MapController mapController) {
        this.playerManager = playerManager;
        this.mapController = mapController;
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
        try {
            if(!PlayerManager.isGameRunning()) {
                if(mapController.isSelectionRunning() )
                playerManager.setGameRunning(true);
                Utils.broadcastConfirmationMessage("Game started!");
                Location mapLocation = MapController.getSelectedMapLocation();
                if(mapLocation == null) {
                    Utils.sendErrorMessage((Player) sender,"Map not selected!");
                    return true;
                }
                for (Player player : Bukkit.getOnlinePlayers()) {
                    PlayerData playerData = playerManager.getPlayerData(player);
                    for (Ability ability : playerData.getPlayerClass().getAbilities()) {
                        ability.setTimeOfUse(ability.getCooldown());
                    }
                    Utils.clearPotionEffects(player);
                }
                playerManager.setItems();
            } else {
                Utils.sendErrorMessage((Player) sender, "Game already started!");
                return true;
            }
        } catch (Exception E){
            Utils.sendErrorMessage((Player) sender,"stupid idot, not all prlayer s have class (or some other error idk)");
        }
        return true;
    }
}
