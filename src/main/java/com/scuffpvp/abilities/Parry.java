package com.scuffpvp.abilities;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.IRON_DOOR;

public class Parry extends AreaOfEffectAttack {

    /**
     * Creates the parry object
     * @param caster The caster of the ability
     */
    public Parry(Player caster){
        super(caster,2.5,50,10,1);
    }

    @Override
    public int getDamage(double radius) {
        return 0;
    }

    @Override
    public void spawnActivationParticles() {
        Location center = getCaster().getLocation();
        double radius = getMaxRadius();

        for (double x = center.getX() - radius; x <= center.getX() + radius; x += 0.5) {
            for (double y = center.getY() - radius; y <= center.getY() + radius; y += 0.5) {
                for (double z = center.getZ() - radius; z <= center.getZ() + radius; z += 0.5) {
                    Location particleLoc = new Location(center.getWorld(), x, y, z);
                    double distance = particleLoc.distance(center);

                    if (distance >= radius - 0.1 && distance <= radius + 0.1) {
                        // Spawn particle at particleLoc
                        particleLoc.getWorld().spawnParticle(Particle.SPELL_INSTANT, particleLoc, 1);
                    }
                }
            }
        }
    }

    @Override
    public void activate() {
        if(getTimeOfUse() < getCooldown() + getDuration()) {
            setTimeOfUse(0);
            getSoundMix().forEach((k, v) -> getCaster().getWorld().playSound(getCaster().getLocation(), k, v, 1));
            spawnActivationParticles();
        }
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void tick() {
        super.tick();
        if(getTimeOfUse() < getDuration()){
            for (Entity entity : getCaster().getNearbyEntities(getMaxRadius() + 0.3, getMaxRadius() + 0.3, getMaxRadius() + 0.3)) {
                if (entity instanceof Projectile projectile && !projectile.getShooter().equals(getCaster())) {
                    projectile.setShooter(getCaster());
                    projectile.setVelocity(projectile.getVelocity().multiply(-1));
                    projectile.setRotation(projectile.getLocation().getYaw() * -1, projectile.getLocation().getPitch() * -1);
                }
            }
        }
    }

    @Override
    public Map<Sound, Float> getSoundMix() {
        Map<Sound,Float> sounds = new HashMap<>();
        sounds.put(Sound.BLOCK_ANVIL_LAND, 1.0F);
        sounds.put(Sound.BLOCK_METAL_HIT, 2.0F);
        sounds.put(Sound.BLOCK_WOOD_HIT, 3.0F);
        return sounds;
    }

    @Override
    public ItemStack[] getItems() {
        return new ItemStack[] {new ItemStack(IRON_DOOR)};
    }
}
