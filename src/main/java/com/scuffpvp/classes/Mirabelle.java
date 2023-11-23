package com.scuffpvp.classes;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.*;

public class Mirabelle extends Class{
    public Mirabelle() {
        super(18, "Mirabelle", 0.12);
    }

    @Override
    public Map<Integer, ItemStack> generateClassItems() {
        Map<Integer, ItemStack> items = new HashMap<>();
        items.put(0,new ItemStack(NETHERITE_HOE));
        items.put(1,new ItemStack(WARPED_FUNGUS));
        items.put(2,new ItemStack(BLAZE_ROD));
        return items;
    }
}
