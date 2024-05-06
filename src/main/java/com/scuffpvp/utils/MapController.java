package com.scuffpvp.utils;

import com.scuffpvp.ScuffPVP;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapController {
    /**
     * The length of the vote timer, in seconds
     */
    private static final int VOTE_TIMER_LENGTH = 60;

    //TODO: Put this in a config somewhere along with locations
    /**
     * List of available maps
     */
    public static final String[] MAPS = {"Abandoned Castle","Chateau","Snowy Village","Cage Match"};
    private int timeRemaining;
    private static String selectedMap;
    private Map<Player, Integer> voteMap;

    public MapController() {
        selectedMap = "";
        voteMap = new HashMap<>();
        for(Player player : Bukkit.getOnlinePlayers()){
            voteMap.put(player,-1);
        }
        timeRemaining = -1;
    }

    public static String getSelectedMap() {
        return selectedMap;
    }

    public static Location getSelectedMapLocation() {
        Location mapLocation = null;
        switch(MapController.getSelectedMap()) {
            case "Abandoned Castle":
                mapLocation = ScuffPVP.ABANDONED_CASTLE;
            case "Chateau":
                mapLocation = ScuffPVP.CHATEAU;
            case "Snowy Village":
                mapLocation = ScuffPVP.SNOWY_VILLAGE;
            case "Cage Match":
                mapLocation = ScuffPVP.CAGE_MATCH;
        }
        return mapLocation;
    }

    /**
     * Sets a map as selected
     * @param name The name of the map to be selected
     * @return If the map was set correctly
     */
    public boolean setMap(String name) {
        if(!selectedMap.equals("")) {
            return false;
        }
        for(String map : MAPS){
            if(name.equals(map)){
                selectedMap = map;
                return true;
            }
        }
        return false;
    }

    public void tick(){
        if(timeRemaining == 0) {
            Utils.broadcastErrorMessage("Voting period is over!\nResults:\n" + getResultsString());
            Bukkit.getServer().dispatchCommand((Bukkit.getOnlinePlayers().stream().toList()).get(0),"start");
        } else if(timeRemaining % 20 == 0) {
            int secondsRemaining = timeRemaining/20;
            int[] printStatements = {60,30,15,10,5,4,3,2,1};
            for(int value : printStatements) {
                if (value == secondsRemaining){
                    Utils.broadcastConfirmationMessage(secondsRemaining + " seconds remaining to make your selections!");
                }
            }
        }
        if(timeRemaining >= 0) {
            timeRemaining--;
        }
    }

    public void terminateVotingProcess(){
        timeRemaining = 0;
    }

    private String getResultsString(){
        int[] results = new int[MAPS.length];
        for(int i : results){
            results[i] = 0;
        }
        for(Integer vote : voteMap.values()){
            if(vote != -1) {
                results[vote]++;
            }
        }
        String result = "";
        int highestVotes = -1;
        int totalVotes = voteMap.size();
        for(int i = 0;i < results.length;i++){
            if(totalVotes != 0) {
                result = result + (i + 1) + ". " + MAPS[i] + ": " + results[i] + " (" + (int) (((double) results[i] / totalVotes) * 100) + "%)\n";
            }
            else{
                result = result + (i + 1) + ". " + MAPS[i] + ": " + results[i] + " (0%)\n";
            }
            if (results[i] > highestVotes) {
                highestVotes = results[i];
            }
        }
        List<Integer> tieVotes = new ArrayList<>();
        for(int i = 0;i < results.length;i++){
            if(results[i] == highestVotes) {
                tieVotes.add(i);
            }
        }
        setMap(MAPS[tieVotes.get(Utils.getRandom(0,tieVotes.size()-1))]);
        result = result + "Map vote winner is " + selectedMap + "!";
        return result;
    }

    public boolean hasPlayerVoted(Player player){
        return voteMap.containsKey(player);
    }

    public boolean isSelectionRunning(){
        return timeRemaining <= VOTE_TIMER_LENGTH*20 && timeRemaining >= 0;
    }

    public int getTimeRemaining(){
        return timeRemaining;
    }

    public void addVote(Player player, int option) {
        voteMap.put(player,option);
    }

    public void startVote(){
        timeRemaining = 20*VOTE_TIMER_LENGTH;
    }
}