package com.scuffpvp.controllers;

import com.scuffpvp.ScuffPVP;
import com.scuffpvp.player.PlayerData;
import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;


public class GameController {

    private PlayerManager playerManager;
    private final int GLOBAL_LIVES;

    public GameController(PlayerManager playerManager, int lives) {
        this.playerManager = playerManager;
        GLOBAL_LIVES = lives;
    }

    public void startGame(){
        displayLivesUpdate();
        for(Player p : Bukkit.getOnlinePlayers()){
            p.teleport(respawn());
            playerManager.getPlayerData(p).setLives(GLOBAL_LIVES);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 100, true));
        }
    }

    public Location respawn(){
        return MapController.getSelectedMapLocation();
    }

    public void winCheck() {
        List<String> alivePlayers = new ArrayList<>();
        for(Player p : Bukkit.getOnlinePlayers()){
            if(p.getGameMode().equals(GameMode.ADVENTURE)){
                alivePlayers.add(p.getName());
            }
        }
        if(alivePlayers.size() == 1){
            Utils.broadcastConfirmationMessage(alivePlayers.get(0) + " WINS!");
            endGame();
        }
        if(alivePlayers.size() == 0){
            Utils.broadcastConfirmationMessage("DRAW!");
            endGame();
        }
        displayLivesUpdate();
    }

    public void endGame(){
        PlayerManager.setGameRunning(false);
        for(Player p : Bukkit.getOnlinePlayers()){
            p.teleport(ScuffPVP.SPAWN_AREA);
            p.setGameMode(GameMode.ADVENTURE);
            playerManager.getPlayerData(p).setLives(0);
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PotionEffect.INFINITE_DURATION, 100, true));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 100, true));
            p.getInventory().clear();
            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            p.setHealth(20);
            Bukkit.getScoreboardManager().getMainScoreboard().resetScores(p.getName());
        }
        MapController.resetMap();
    }

    public void displayLivesUpdate(){
        Scoreboard scoreboard;
        try {
            scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        } catch (NullPointerException e){
            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
            scoreboard.getObjective("Lives").setDisplaySlot(DisplaySlot.BELOW_NAME);
            scoreboard.getObjective("Lives").setDisplaySlot(DisplaySlot.PLAYER_LIST);
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerData playerData = playerManager.getPlayerData(p);
            p.setLevel(playerData.getLives());
            p.setScoreboard(scoreboard);
            scoreboard.getObjective("Lives").getScore(p.getName()).setScore(playerData.getLives());
        }
    }
}
