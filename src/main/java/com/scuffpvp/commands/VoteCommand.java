package com.scuffpvp.commands;

import com.scuffpvp.utils.MapController;
import com.scuffpvp.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {
    private MapController mapController;

    public VoteCommand(MapController mapController) {
        this.mapController = mapController;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(mapController.isSelectionRunning() && commandSender instanceof Player player){
            if(args.length < 1) {
                Utils.sendErrorMessage(player,"Command usage: /vote <choice>\n");
                return true;
            }
            int selectedMap = -1;
            String mapName = "";
            for(int i = 0; i < args.length; i++){
                mapName = mapName + args[i] + " ";
            }
            mapName = mapName.substring(0,mapName.length()-1);
            try {
                selectedMap = Integer.valueOf(mapName) - 1;
                if(selectedMap < 0 || selectedMap > MapController.MAPS.length) {
                    Utils.sendErrorMessage(player, "Selected index not an option. Range: [1-" + MapController.MAPS.length + "]\n");
                    return true;
                }
            } catch (NumberFormatException e){
                switch(mapName){
                    case "Abandoned Castle":
                        selectedMap = 0;
                        break;
                    case "Chateau":
                        selectedMap = 1;
                        break;
                    case "Snowy Village":
                        selectedMap = 2;
                        break;
                    case "Cage Match":
                        selectedMap = 3;
                        break;
                    case "list":
                        Utils.sendConfirmationMessage(player,"Available maps:\n1: Abandoned Castle\n2: Chateau\n3: Snowy Village\n4: Cage Match");
                        return true;
                    default:
                        Utils.sendErrorMessage(player, "Selected map is not in the available choices! Use /vote list to list options.");
                        return true;
                }
            }
            mapController.addVote(player, selectedMap);
            if(mapController.hasPlayerVoted(player)) {
                Utils.broadcastConfirmationMessage(player.getName() + " changed their vote to " + MapController.MAPS[selectedMap]);
            } else {
                Utils.broadcastConfirmationMessage(player.getName() + " voted for " + MapController.MAPS[selectedMap]);
            }
        } else {
            Utils.sendErrorMessage((Player) commandSender, "Map selection is not active!");
        }
        return true;
    }
}
