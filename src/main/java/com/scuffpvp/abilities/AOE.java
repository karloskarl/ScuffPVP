package com.scuffpvp.abilities;

import com.scuffpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class AOE extends Ability{
    private double radius;
    public double getMaxRadius(){
        return radius;
    }

    public abstract int getDamage(double radius);

    public abstract void spawnActivationParticles();

    public AOE(Player player,double radius,int cooldown,int priority){
        super(player,cooldown,priority);
        this.radius = radius;
    }

    @Override
    public Player[] getTargets() {
        ArrayList<Player> targets = new ArrayList<>();
        for(Player target : Bukkit.getOnlinePlayers()){
            if(Utils.getDistance(getCaster(),target) <= radius){
                targets.add(target);
            }
        }
        return targets.toArray(new Player[0]);
    }


}
