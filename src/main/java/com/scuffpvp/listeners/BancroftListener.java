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

import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.FIRE_CHARGE;
import static org.bukkit.Material.IRON_DOOR;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

/**
 * Class that hold the listener for the Bancroft Class
 */
public class BancroftListener implements Listener{
    private PlayerManager playerManager;
    private Map<String,Integer> cooldownMap;

    public BancroftListener(PlayerManager playerManager){
        this.playerManager = playerManager;
        cooldownMap = new HashMap<>();
        cooldownMap.put("Stomp",15000);
    }


    /**
     * Listens for player interaction events
     * @param event The player interaction event when it occurs
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().toString().contains("RIGHT") && playerManager.getPlayerData(player).getPlayerClass() instanceof Bancroft) {
            if(event.getMaterial() == FIRE_CHARGE) {
                if(playerManager.getPlayerData(player).checkCoolDown("Stomp",cooldownMap.get("Stomp"))) {
                    for(Player otherPlayer : Bukkit.getOnlinePlayers()){
                        if(!otherPlayer.equals(player) && Utils.getDistance(player,otherPlayer) <= 3){
                            otherPlayer.damage(14,player);
                        }
                    }
                    player.getWorld().spawnParticle(Particle.BLOCK_DUST,player.getLocation(),1000,1.5,0.1,1.5,Bukkit.createBlockData(Material.DIRT));
                    player.getWorld().playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER,2,1);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT,1,1);
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_HIT,2,1);
                    playerManager.getPlayerData(player).setCoolDown("Stomp");
                    player.sendMessage("THIS WORKS DUMBASS!");
                }
                else{
                    player.sendMessage("cool the FUCK DOWN you ASSHOLE!!!");
                }
            } else if (event.getMaterial() == IRON_DOOR) {
                player.sendMessage("iron balls your mom heheha");
            }
        }
    }

    public Map<String,Integer> getCooldownMap(){
        return cooldownMap;
    }
}
