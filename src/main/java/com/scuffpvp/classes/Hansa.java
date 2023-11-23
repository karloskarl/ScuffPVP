package com.scuffpvp.classes;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.*;

public class Hansa extends Class{

    public Hansa(){
        super(20,"Hansa", 0.1);
    }

    @Override
    public Map<Integer, ItemStack> generateClassItems(){
        Map<Integer, ItemStack> items = new HashMap<>();
        items.put(0,new ItemStack(CROSSBOW));
        items.put(1,new ItemStack(IRON_SWORD));
        items.put(2,new ItemStack(ARROW,2));
        return items;
    }
}
