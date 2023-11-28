package com.scuffpvp.abilities;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public abstract class MeleeAttack extends Ability{
    private Entity target;

    public MeleeAttack(Player caster, int priority) {
        super(caster, 0, priority);
    }

    public void setTarget(Entity target){
        this.target = target;
    }

    @Override
    public Player[] getTargets(){
        if(target instanceof Player player){
            return new Player[]{player};
        }
        return new Player[0];
    }
}
