package com.roje.cashshop.event;

import com.github.nicklib.data.Config;
import com.github.nicklib.data.utils.Tuple;
import com.github.nicklib.utils.InventoryUtil;
import com.roje.cashshop.utils.LoreUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

import static com.roje.cashshop.CashShopPlugin.plugin;
import static com.roje.cashshop.data.CashShopMapData.*;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!inventoryClickMap.containsKey(player)) return;

        if (event.getClickedInventory() == player.getInventory()) return;
        if (event.getClickedInventory() == null) return;

        if (!shopTypeMap.containsKey(player)) return;
        if (shopTypeMap.get(player) == "편집") {
            /**
             * 구매 가격 설정
             */

            if (event.getClick() == ClickType.SHIFT_LEFT) {
                event.setCancelled(true);
                priceMap.put(player, Tuple.of(inventoryClickMap.get(player), event.getSlot()));
                priceTypeMap.put(player, "구매");
                player.closeInventory();
            }

            /**
             * 판매 가격 설정
             */

            if (event.getClick() == ClickType.SHIFT_RIGHT) {
                event.setCancelled(true);
                priceMap.put(player, Tuple.of(inventoryClickMap.get(player), event.getSlot()));
                priceTypeMap.put(player, "판매");
                player.closeInventory();
            }
        }

        if (shopTypeMap.get(player) == "편집") {
            /**
             * 로어 빼기
             */

            if (event.getClick() == ClickType.SWAP_OFFHAND || event.getClick() == ClickType.LEFT) {
                if (event.getCurrentItem() == null) return;
                LoreUtil loreUtil = new LoreUtil(event.getCurrentItem());
                List<String> lore = (List<String>) event.getCurrentItem();
            }
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
