package com.scuffpvp.utils;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
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

    public static double getDistance(Entity p1, Entity p2){
        Location p1pos = p1.getLocation();
        Location p2pos = p2.getLocation();
        return Math.sqrt(Math.pow(p1pos.getX() - p2pos.getX(),2)+Math.pow(p1pos.getY() - p2pos.getY(),2)+Math.pow(p1pos.getZ() - p2pos.getZ(),2));
    }
}
