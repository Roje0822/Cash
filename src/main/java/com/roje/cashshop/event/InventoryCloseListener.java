package com.roje.cashshop.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import static com.roje.cashshop.data.CashShopMapData.inventoryClickMap;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();
        if (inventoryClickMap.containsKey(player)) {
            inventoryClickMap.remove(player);
        }
    }
}
