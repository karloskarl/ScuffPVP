package com.scuffpvp.utils;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
    public static void sendErrorMessage(Player player, String message) {
        String formattedMessage = ChatColor.RED + "" + ChatColor.BOLD + message;
        player.sendMessage(formattedMessage);
    }

    public static void sendConfirmationMessage(Player player, String message) {
        String formattedMessage = ChatColor.GREEN + "" + ChatColor.BOLD + message;
        player.sendMessage(formattedMessage);
    }
}
