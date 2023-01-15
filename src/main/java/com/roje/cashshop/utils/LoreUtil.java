package com.roje.cashshop.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.ChatPaginator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoreUtil {

    private static ItemStack stack;

    public LoreUtil (ItemStack stack) {
        this.stack = stack;
    }

    public static ItemStack setItemMeta(List<String> lore) {
        if (stack != null) {
            final ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(stack.getItemMeta().getDisplayName());
            meta.setLore(lore);
            stack.setItemMeta(meta);
            return stack;
        }
        return null;
    }
}
