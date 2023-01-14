package com.roje.cashshop.event;

import com.github.nicklib.data.Config;
import com.github.nicklib.data.utils.Tuple;
import com.github.nicklib.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import static com.roje.cashshop.CashShopPlugin.plugin;
import static com.roje.cashshop.data.CashShopMapData.*;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!inventoryClickMap.containsKey(player)) return;

        if (event.getClickedInventory() == player.getInventory()) return;
        if (event.getClickedInventory() == null) return;

        /**
         * 인벤토리 클로즈
         */
        if (event.getClick() == ClickType.SHIFT_LEFT) {
            event.setCancelled(true);
            priceMap.put(player, Tuple.of(inventoryClickMap.get(player), event.getSlot()));
            player.closeInventory();
        }

        /**
         * 콘피그에 아이템 저장
         */

        Config config = new Config("shop/" + inventoryClickMap.get(player), plugin);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            InventoryUtil inventoryUtil = new InventoryUtil(config);
            inventoryUtil.saveInventory(inventoryClickMap.get(player), event.getInventory());
        }, 1L);

    }
}
