package com.scuffpvp.commands;

import com.scuffpvp.classes.Bancroft;
import com.scuffpvp.classes.ClassManager;
import com.scuffpvp.classes.Hansa;
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
            switch (args[0]) {
                case "Hansa" -> classManager.assignClass(player, new Hansa());
                case "Bancroft" -> classManager.assignClass(player, new Bancroft());
            }
            player.sendMessage("Chosen class: " + args[0]);
            return true;
        }
        return false;
    }
}
