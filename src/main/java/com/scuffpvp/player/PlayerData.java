package com.scuffpvp.player;

import com.scuffpvp.classes.Class;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {
    private Map<String,Long> cooldowns;
    private Class chosenClass;
    private Player player;

    public PlayerData(Player player){
        cooldowns = new HashMap<>();
        this.player = player;
    }

    public void setCoolDown(String timerName){
        cooldowns.put(timerName,System.currentTimeMillis());
    }

    public boolean checkCoolDown(String timerName, int cooldown){
        Long firstTime = cooldowns.get(timerName);
        if(firstTime == null){
            setCoolDown(timerName);
            return true;
        }
        long secondTime = System.currentTimeMillis();
        player.sendMessage(String.valueOf(firstTime - secondTime));
        return secondTime - firstTime >= cooldown;
    }

    public Class getPlayerClass() {
        return chosenClass;
    }

    public void setClass(Class playerClass) {
        chosenClass = playerClass;
    }

    public void clearClass() {
        chosenClass = null;
    }
}
