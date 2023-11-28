package com.scuffpvp.listeners;

import com.scuffpvp.abilities.Ability;
import com.scuffpvp.abilities.MeleeAttack;
import com.scuffpvp.classes.Bancroft;
import com.scuffpvp.player.PlayerData;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


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
<<<<<<< HEAD:src/main/java/com/scuffpvp/listeners/BancroftListener.java
        if(playerData.getPlayerClass() != null && event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
=======
        if(playerData.getPlayerClass() != null && event.getAction().name().contains("RIGHT")) {
>>>>>>> origin/master:src/main/java/com/scuffpvp/listeners/ItemInteractionListener.java
            for (Ability ability : playerData.getPlayerClass().getAbilities()) {
                if (event.getMaterial().equals(ability.getItems()[0].getType()) && playerData.checkCoolDown(ability)) {
                    ability.activate();
                    playerData.setCoolDown(ability);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDealDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player){
            PlayerData playerData = playerManager.getPlayerData(player);
            if(playerData.getPlayerClass() != null) {
                for (Ability ability : playerData.getPlayerClass().getAbilities()) {
                    if (ability instanceof MeleeAttack attack && player.getInventory().getItemInMainHand().equals(ability.getItems()[0])) {
                        event.setDamage(0);
                        attack.setTarget(event.getEntity());
                        attack.activate();
                    }
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
