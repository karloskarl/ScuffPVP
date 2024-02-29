package com.scuffpvp.abilities;

import com.scuffpvp.player.PlayerManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public abstract class MeleeAttack extends Ability{
    private Entity target;

    public MeleeAttack(Player caster,int cooldown, int priority, PlayerManager playerManager) {
        super(caster, cooldown, priority, playerManager);
    }

    public void setTarget(Entity target){
        this.target = target;
    }

    public abstract int getDamage();



    public void activate(EntityDamageByEntityEvent event) {
        super.activate();
        event.setDamage(getDamage());
    }

    @Override
    public Player[] getTargets(){
        if(target instanceof Player player){
            return new Player[]{player};
        }
        return new Player[0];
    }
}
