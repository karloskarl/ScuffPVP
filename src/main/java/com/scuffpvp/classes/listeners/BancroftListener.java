package com.scuffpvp.classes.listeners;

import com.scuffpvp.classes.Bancroft;
import com.scuffpvp.classes.ClassManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

import static org.bukkit.Material.FIRE_CHARGE;
import static org.bukkit.Material.IRON_DOOR;

public class BancroftListener implements Listener{
    private ClassManager classManager;

    public BancroftListener(ClassManager classManager){
        this.classManager = classManager;
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().toString().contains("RIGHT") && classManager.getPlayerClass(player) instanceof Bancroft) {
            if(event.getMaterial() == FIRE_CHARGE) {
                player.sendMessage("Fireball da doo doo da doo da. MR 305!!!! MR WORLDWIDE!!!");
            } else if (event.getMaterial() == IRON_DOOR) {
                player.sendMessage("iron balls your mom heheha");
            }
        }
    }
}
