package com.scuffpvp.commands;

import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.classes.*;
import com.scuffpvp.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SelectClassCommand implements CommandExecutor {
    private final PlayerManager playerManager;

    public SelectClassCommand(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            switch (args[0].toLowerCase()) {
                case "hansa" -> playerManager.getPlayerData(player).setClass(new Hansa());
                case "bancroft" -> playerManager.getPlayerData(player).setClass(new Bancroft());
                case "mirabelle" -> playerManager.getPlayerData(player).setClass(new Mirabelle());
                case "suzuka" -> playerManager.getPlayerData(player).setClass(new Suzuka());
                case "esteille" -> playerManager.getPlayerData(player).setClass(new Esteille());
                case "glass" -> playerManager.getPlayerData(player).setClass(new Glass());
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
