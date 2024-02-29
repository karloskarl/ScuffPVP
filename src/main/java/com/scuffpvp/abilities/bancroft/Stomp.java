package com.scuffpvp.abilities.bancroft;

import com.scuffpvp.abilities.AreaOfEffectAttack;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.FIRE_CHARGE;

public class Stomp extends AreaOfEffectAttack {
    private double radius;

    @Override
    public String toString() {
        return "Stomp";
    }

    public Stomp(Player caster, PlayerManager playerManager){
        super(caster,3.5,300,0,2, playerManager);
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
        if(getTimeOfUse() > getCooldown()) {
            super.activate();
            setTimeOfUse(0);
            getSoundMix().forEach((k, v) -> getCaster().getWorld().playSound(getCaster().getLocation(), k, v, 1));
            spawnActivationParticles();
            for (Player target : getTargets()) {
                if (target != getCaster()) {
                    target.damage(getDamage(0), getCaster());
                }
            }
        }
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void tick() {
        super.tick();
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
