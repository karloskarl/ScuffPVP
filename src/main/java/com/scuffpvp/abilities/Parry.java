package com.scuffpvp.abilities;

import com.scuffpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.FIRE_CHARGE;
import static org.bukkit.Material.IRON_DOOR;

public class Parry extends AOE{

    public Parry(Player caster){
        super(caster,2,2000,1);
    }

    @Override
    public int getDamage(double radius) {
        return 0;
    }

    @Override
    public void spawnActivationParticles() {
        getCaster().getWorld().spawnParticle(Particle.SPELL,getCaster().getLocation(),1000,getMaxRadius()/2,0.1,getMaxRadius()/2);
    }

    @Override
    public void activate() {
        getSoundMix().forEach((k,v) -> getCaster().getWorld().playSound(getCaster().getLocation(),k,v,1));
        spawnActivationParticles();
        for(Entity entity : getCaster().getNearbyEntities(getMaxRadius(),getMaxRadius(),getMaxRadius())){
            if(Utils.getDistance(getCaster(),entity) <= getMaxRadius() && entity instanceof Projectile projectile){
                projectile.setShooter(getCaster());
                projectile.setVelocity(projectile.getVelocity().multiply(-1));
                projectile.setRotation(projectile.getLocation().getYaw()*-1,projectile.getLocation().getPitch()*-1);
            }
        }
    }

    @Override
    public void cleanup() {
    }

    @Override
    public Map<Sound, Float> getSoundMix() {
        Map<Sound,Float> sounds = new HashMap<>();
        sounds.put(Sound.BLOCK_ANVIL_LAND, 1.5F);
        sounds.put(Sound.BLOCK_METAL_HIT, 2.0F);
        sounds.put(Sound.BLOCK_WOOD_HIT, 1.0F);
        return sounds;
    }

    @Override
    public ItemStack[] getItems() {
        return new ItemStack[] {new ItemStack(IRON_DOOR)};
    }
}
