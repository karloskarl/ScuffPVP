package com.scuffpvp.commands;

import com.scuffpvp.classes.ClassManager;
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
        if(sender instanceof Player) {
            Player player = (Player) sender;

            return true;
        }
        return false;
    }
}
