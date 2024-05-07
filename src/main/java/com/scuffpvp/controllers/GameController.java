package com.scuffpvp.controllers;

import com.scuffpvp.player.PlayerManager;
import com.scuffpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class GameController {
    /*
     * 1. Player Spawning
     * 2. Player Lives/Death/Respawning
     * 3. Win Condition
     * 4. Reset Game
     *
     *
     */
    private MapController mapController;
    private PlayerManager playerManager;
    private int GLOBAL_LIVES;

    public GameController(MapController mapController, PlayerManager playerManager, int lives) {
        this.mapController = mapController;
        this.playerManager = playerManager;
        GLOBAL_LIVES = lives;
    }

    public void startGame(){
        for(Player p : Bukkit.getOnlinePlayers()){
            respawn(p);
            playerManager.getPlayerData(p).setLives(GLOBAL_LIVES);
        }
    }

    public void respawn(Player p){
        p.teleport(MapController.getSelectedMapLocation());
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
    }

    public void endGame(){

    }
}
