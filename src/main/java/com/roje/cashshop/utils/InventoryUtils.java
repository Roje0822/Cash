package com.roje.cashshop.utils;

import com.github.nicklib.data.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {



    private Player player;
    private Config config;

    public InventoryUtils(Player player, Config config) {
        this.player = player;
        this.config = config;
    }

    public InventoryUtils(Config config) {
        this.config = config;
    }


    public void openInventory(Player player, Inventory inventory) {
        player.openInventory(inventory);
    }

    public void saveInventory(String section, @NotNull Inventory inv) {
        List<ItemStack> itemStacks = new ArrayList();
        ConfigurationSection configurationSection = this.config.getConfig().createSection(section + ".items.meta");
        if (inv.isEmpty()) {
            configurationSection.set("items", (Object)null);
            this.config.saveConfig();
        } else {
            for(int i = 0; i < inv.getSize(); ++i) {
                ItemStack item = inv.getItem(i);
                if (item != null) {
                    itemStacks.add(item);
                    configurationSection.set(String.valueOf(i), item);
                    this.config.saveConfig();
                }
            }

        }
    }
}
