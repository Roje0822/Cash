package com.roje.cashshop.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import static com.roje.cashshop.data.CashShopMapData.inventoryNameMap;
import static com.roje.cashshop.data.CashShopMapData.shopTypeMap;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();
        if (inventoryNameMap.containsKey(player)) {
            inventoryNameMap.remove(player);
            shopTypeMap.remove(player);
        }
    }
}
