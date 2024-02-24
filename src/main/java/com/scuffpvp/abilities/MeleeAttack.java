package com.scuffpvp.abilities;

import com.scuffpvp.player.PlayerManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public abstract class MeleeAttack extends Ability{
    private Entity target;

    public MeleeAttack(Player caster, int priority, PlayerManager playerManager) {
        super(caster, 0, priority, playerManager);
    }

    public void setTarget(Entity target){
        this.target = target;
    }

    public abstract int getDamage();

    @Override
    public Player[] getTargets(){
        if(target instanceof Player player){
            return new Player[]{player};
        }
        return new Player[0];
    }
}
