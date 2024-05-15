package com.scuffpvp.classes;

import com.scuffpvp.abilities.bancroft.Parry;
import com.scuffpvp.abilities.bancroft.Stomp;
import com.scuffpvp.abilities.bancroft.Sword;
import com.scuffpvp.player.PlayerManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Material.*;

public class Bancroft extends Class {

    //TODO: Make these values constants somewhere, or make a .cfg file to easily control values
    public Bancroft(Player player, PlayerManager playerManager) {
        super(30, "Bancroft", 0.525, playerManager);
        addAbility(new Stomp(player, playerManager));
        addAbility(new Parry(player, playerManager));
        addAbility(new Sword(player, playerManager));
        ItemStack[] armorSet = addArmorSet();
        for(int i = 0; i < armorSet.length; i++){
            addArmor(armorSet[i],i);
        }
    }

    public ItemStack[] addArmorSet(){
        ItemStack[] itemStack = new ItemStack[4];
        itemStack[3] = new ItemStack(PIGLIN_HEAD);
        itemStack[2] = new ItemStack(IRON_CHESTPLATE);
        itemStack[1] = new ItemStack(IRON_LEGGINGS);
        itemStack[0] = new ItemStack(IRON_BOOTS);
        for(int i = 0; i < itemStack.length;i++){
            ItemMeta armorMeta = itemStack[i].getItemMeta();
            armorMeta.addAttributeModifier(Attribute.GENERIC_ARMOR,new AttributeModifier("resistance",0, AttributeModifier.Operation.ADD_NUMBER));
            itemStack[i].setItemMeta(armorMeta);
        }
        return itemStack;
    }
}
