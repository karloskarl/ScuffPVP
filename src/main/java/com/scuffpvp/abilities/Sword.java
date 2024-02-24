package com.scuffpvp.abilities;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static org.bukkit.Material.FIRE_CHARGE;
import static org.bukkit.Material.NETHERITE_SWORD;

public class Sword extends MeleeAttack{
    public Sword(Player caster) {
        super(caster, 0);
    }

    @Override
    public void activate() {
    }

    @Override
    public int getDamage(){
        return 7;
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void tick() {

    }

    @Override
    public Map<Sound, Float> getSoundMix() {
        return null;
    }

    @Override
    public ItemStack[] getItems() {
        return new ItemStack[]{new ItemStack(NETHERITE_SWORD)};
    }
}
