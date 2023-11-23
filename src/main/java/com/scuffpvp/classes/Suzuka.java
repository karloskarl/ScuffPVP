package com.scuffpvp.classes;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.*;

public class Suzuka extends Class {
    public Suzuka() {
        super(20, "Suzuka", 0.1);
    }

    @Override
    public Map<Integer, ItemStack> generateClassItems() {
        Map<Integer, ItemStack> items = new HashMap<>();
        items.put(0,new ItemStack(IRON_SWORD));
        items.put(1,new ItemStack(LIGHT_GRAY_DYE));
        items.put(2,new ItemStack(DIAMOND_SWORD));
        return items;
    }
}
