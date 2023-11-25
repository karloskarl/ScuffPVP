package com.scuffpvp.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.FIRE_CHARGE;

public class Stomp extends AOE{
    private double radius;

    public Stomp(Player caster){
        super(caster,3.5,15000,2);
    }

    @Override
    public int getDamage(double radius) {
        return 14;
    }

    @Override
    public void spawnActivationParticles() {
        getCaster().getWorld().spawnParticle(Particle.BLOCK_DUST,getCaster().getLocation(),1000,getMaxRadius()/2,0.1,getMaxRadius()/2,Bukkit.createBlockData(Material.DIRT));
    }

    @Override
    public void activate() {
        getSoundMix().forEach((k,v) -> getCaster().getWorld().playSound(getCaster().getLocation(),k,v,1));
        spawnActivationParticles();
        for(Player target: getTargets()){
            if(target != getCaster()) {
                target.damage(getDamage(0), getCaster());
            }
        }
    }

    @Override
    public void cleanup() {
    }

    @Override
    public Map<Sound,Float> getSoundMix() {
        Map<Sound,Float> sounds = new HashMap<>();
        sounds.put(Sound.ITEM_ARMOR_EQUIP_LEATHER, 2.0F);
        sounds.put(Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0F);
        sounds.put(Sound.BLOCK_ANVIL_HIT, 2.0F);
        return sounds;
    }

    @Override
    public ItemStack[] getItems() {
        return new ItemStack[] {new ItemStack(FIRE_CHARGE)};
    }
}
