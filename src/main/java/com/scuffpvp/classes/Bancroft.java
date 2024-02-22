package com.scuffpvp.classes;

import com.scuffpvp.abilities.Ability;
import com.scuffpvp.abilities.Parry;
import com.scuffpvp.abilities.Stomp;
import com.scuffpvp.abilities.Sword;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static org.bukkit.Material.*;

public class Bancroft extends Class {

    //TODO: Make these values constants somewhere, or make a .cfg file to easily control values
    public Bancroft(Player player) {
        super(30, "Bancroft", 0.075);
        addAbility(new Stomp(player));
        addAbility(new Parry(player));
        addAbility(new Sword(player));
    }
}
