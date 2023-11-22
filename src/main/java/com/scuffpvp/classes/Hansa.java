package com.scuffpvp.classes;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.*;

public class Hansa extends Class{
    private int health;
    private String name;
    private Map<Integer,ItemStack> items;

    public Hansa(){
        name = "Hansa";
        health = 20;
        items = new HashMap<>();

        items.put(0,new ItemStack(CROSSBOW));
        items.put(1,new ItemStack(IRON_SWORD));
        items.put(3,new ItemStack(ARROW,2));
    }


}
