package com.scuffpvp.classes;

import com.scuffpvp.abilities.bancroft.Parry;
import com.scuffpvp.abilities.bancroft.Stomp;
import com.scuffpvp.abilities.bancroft.Sword;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.entity.Player;

public class Bancroft extends Class {

    //TODO: Make these values constants somewhere, or make a .cfg file to easily control values
    public Bancroft(Player player, PlayerManager playerManager) {
        super(30, "Bancroft", 0.075, playerManager);
        addAbility(new Stomp(player, playerManager));
        addAbility(new Parry(player, playerManager));
        addAbility(new Sword(player, playerManager));
    }
}
