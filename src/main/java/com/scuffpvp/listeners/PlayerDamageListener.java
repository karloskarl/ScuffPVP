package com.scuffpvp.listeners;

import com.scuffpvp.abilities.Ability;
import com.scuffpvp.abilities.MeleeAttack;
import com.scuffpvp.player.PlayerData;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener {
    private PlayerManager playerManager;

    public PlayerDamageListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * Listens for player damage dealing events
     * @param event The dealt damage event when it occurs
     */
    @EventHandler
    public void onPlayerDealDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player){
            PlayerData playerData = playerManager.getPlayerData(player);
            if(playerData.getPlayerClass() != null) {
                for (Ability ability : playerData.getPlayerClass().getAbilities()) {
                    if (ability instanceof MeleeAttack attack && player.getInventory().getItemInMainHand().getType().equals(ability.getItems()[0].getType())) {
                        attack.activate(event);
                    }
                }
            }
        }
    }
}
