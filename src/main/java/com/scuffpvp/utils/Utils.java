package com.scuffpvp.utils;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Random;

/**
 * The Util class contains various miscellaneous utilities used by other parts of the program.
 */
public class Utils {
    public static final String ERROR_MESSAGE = "YOUR DUMB, PUSSY!";
    /**
     * Sends an error message to the specified player in game chat.
     * @param player The specified player
     * @param message The message to be sent
     */
    public static void sendErrorMessage(Player player, String message) {
        String formattedMessage = ChatColor.RED + "" + ChatColor.BOLD + message;
        player.sendMessage(formattedMessage);
    }

    /**
     * Sends an error message to every player
     * @param message The message to be sent
     */
    public static void broadcastErrorMessage(String message) {
        String formattedMessage = ChatColor.RED + "" + ChatColor.BOLD + message;
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            player.sendMessage(formattedMessage);
        }
    }

    /**
     * Sends a confirmation message to the specified player in game chat
     * @param player The specified player
     * @param message The message to be sent
     */
    public static void sendConfirmationMessage(Player player, String message) {
        String formattedMessage = ChatColor.GREEN + "" + ChatColor.BOLD + message;
        player.sendMessage(formattedMessage);
    }

    /**
     * Sends a confirmation message to every player
     * @param message The message to be sent
     */
    public static void broadcastConfirmationMessage(String message) {
        String formattedMessage = ChatColor.GREEN + "" + ChatColor.BOLD + message;
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            player.sendMessage(formattedMessage);
        }
    }

    /**
     * Gets the distance between two entities
     * @param p1 The first entity
     * @param p2 The second entity
     * @return The distance, in blocks, between two entities
     */
    public static double getDistance(Entity p1, Entity p2){
        Location p1pos = p1.getLocation();
        Location p2pos = p2.getLocation();
        return Math.sqrt(Math.pow(p1pos.getX() - p2pos.getX(),2)+Math.pow(p1pos.getY() - p2pos.getY(),2)+Math.pow(p1pos.getZ() - p2pos.getZ(),2));
    }

    /**
     * Clears the potion effects of the selected player
     * @param player The selected player
     */
    public static void clearPotionEffects(Player player){
        for(PotionEffect effect:player.getActivePotionEffects()){
            player.removePotionEffect(effect.getType());
        }
    }

    /**
     * Selects a random number between a and b
     * @param a the lower bound, inclusive
     * @param b the upper bound, inclusive
     */
    public static int getRandom(int a, int b){
        Random r = new Random();
        return r.nextInt(a,b);
    }
}
