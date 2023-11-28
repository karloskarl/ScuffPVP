package com.scuffpvp.listeners;

import com.scuffpvp.abilities.Ability;
import com.scuffpvp.classes.Bancroft;
import com.scuffpvp.player.PlayerData;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;


/**
 * Class that hold the listener for the Bancroft Class
 */
public class ItemInteractionListener implements Listener{
    private PlayerManager playerManager;
    private Bancroft playerClass;

    /**
     * Creates the listener object.
     * @param playerManager The player manager object.
     */
    public ItemInteractionListener(PlayerManager playerManager){
        this.playerManager = playerManager;
    }


    /**
     * Listens for player interaction events
     * @param event The player interaction event when it occurs
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = playerManager.getPlayerData(player);
        if(playerData.getPlayerClass() != null && event.getAction().name().contains("RIGHT")) {
            for (Ability ability : playerData.getPlayerClass().getAbilities()) {
                if (event.getMaterial().equals(ability.getItems()[0].getType()) && playerData.checkCoolDown(ability)) {
                    ability.activate();
                    playerData.setCoolDown(ability);
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        PlayerData playerData = playerManager.getPlayerData(event.getEntity());
        playerData.onDeath();
    }
}
