package com.roje.cashshop.event;

import com.github.nicklib.data.Config;
import com.github.nicklib.data.utils.Tuple;
import com.github.nicklib.utils.InventoryUtil;
import com.roje.cashshop.CashShopPlugin;
import com.roje.cashshop.data.GuiType;
import com.roje.cashshop.utils.InventoryUtils;
import com.roje.cashshop.utils.LoreUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.roje.cashshop.CashShopPlugin.plugin;
import static com.roje.cashshop.data.CashShopMapData.*;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!inventoryClickMap.containsKey(player)) return;
        if (shopTypeMap.get(player) == GuiType.OPEN) event.setCancelled(true);
        if (event.getClickedInventory() == player.getInventory()) return;
        if (event.getClickedInventory() == null) return;
        if (!shopTypeMap.containsKey(player)) return;

        Config config = new Config("shop/" + inventoryClickMap.get(player), CashShopPlugin.getPlugin());

        if (shopTypeMap.get(player) == GuiType.EDIT) {

            /**
             * 구매 가격 설정
             */

            if (event.getClick() == ClickType.SHIFT_LEFT) {
                priceMap.put(player, new Tuple<>(inventoryClickMap.get(player), event.getSlot()));
                priceTypeMap.put(player, "구매");
                player.closeInventory();
            }

            /**
             * 판매 가격 설정
             */

            if (event.getClick() == ClickType.SHIFT_RIGHT) {
                priceMap.put(player, new Tuple<>(inventoryClickMap.get(player), event.getSlot()));
                priceTypeMap.put(player, "판매");
                player.closeInventory();
            }

            /**
             * 아이템을 뺼때
             */

            if (!(event.getCurrentItem() == null)) {
                config.setObject(inventoryClickMap.get(player) + ".items." + event.getSlot(), null);
                return;
            }

            /**
             * 아이템을 넣을때
             */

            if (event.getCurrentItem() == null) {
                config.setObject(inventoryClickMap.get(player) + ".items." + event.getSlot()  + ".meta", event.getCursor());
            }

        }

        if (shopTypeMap.get(player) == GuiType.OPEN) {

        }
    }
}
