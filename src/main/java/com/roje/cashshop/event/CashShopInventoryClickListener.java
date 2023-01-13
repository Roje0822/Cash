package com.roje.cashshop.event;

import com.roje.cashshop.events.CashShopInventoryClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static com.roje.cashshop.data.CashShopMapData.shopMap;

public class CashShopInventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!shopMap.containsKey(player.getUniqueId())) return;


    }
}
