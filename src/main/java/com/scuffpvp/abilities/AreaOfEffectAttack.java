package com.scuffpvp.abilities;

import com.scuffpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class AreaOfEffectAttack extends Ability{
    private double radius;
    private long duration;
    public double getMaxRadius(){
        return radius;
    }

    public abstract int getDamage(double radius);

    public abstract void spawnActivationParticles();

    public AreaOfEffectAttack(Player player, double radius, int cooldown, int duration, int priority){
        super(player,cooldown,priority);
        this.radius = radius;
        this.duration = duration;
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

    public long getDuration() {
        return duration;
    }
}
