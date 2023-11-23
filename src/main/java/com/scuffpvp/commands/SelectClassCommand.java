package com.scuffpvp.commands;

import com.scuffpvp.classes.*;
import com.scuffpvp.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SelectClassCommand implements CommandExecutor {
    private final ClassManager classManager;

    public SelectClassCommand(ClassManager classManager) {
        this.classManager = classManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            switch (args[0].toLowerCase()) {
                case "hansa" -> classManager.assignClass(player, new Hansa());
                case "bancroft" -> classManager.assignClass(player, new Bancroft());
                case "mirabelle" -> classManager.assignClass(player, new Mirabelle());
                case "suzuka" -> classManager.assignClass(player, new Suzuka());
                case "esteille" -> classManager.assignClass(player, new Esteille());
                case "glass" -> classManager.assignClass(player, new Glass());
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
                    if(classManager.getPlayerClass(player) != null) {
                        classManager.clearClass(player);
                        Utils.sendConfirmationMessage(player,"Removed class.");
                        return true;
                    } else {
                        Utils.sendErrorMessage(player, "Cannot remove class that doesn't exist!");
                        return false;
                    }
                }
                case "show" -> {
                    if(classManager.getPlayerClass(player) != null) {
                        Utils.sendConfirmationMessage(player, classManager.getPlayerClass(player).getName());
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
