package com.scuffpvp.commands;

import com.scuffpvp.classes.ClassManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartGameCommand implements CommandExecutor {
    private final ClassManager classManager;

    public StartGameCommand(ClassManager classManager) {
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
