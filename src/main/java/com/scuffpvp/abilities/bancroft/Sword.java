package com.scuffpvp.abilities.bancroft;

import com.scuffpvp.abilities.MeleeAttack;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

import static org.bukkit.Material.FIRE_CHARGE;
import static org.bukkit.Material.NETHERITE_SWORD;
import static org.bukkit.attribute.AttributeModifier.Operation.ADD_NUMBER;

public class Sword extends MeleeAttack {
    public Sword(Player caster, PlayerManager playerManager) {
        super(caster, 60,0, playerManager);
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

    @Override
    public String toString() {
        return "Sword";
    }
}
