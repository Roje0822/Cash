package com.roje.cashshop.event;

import com.github.nicklib.data.Config;
import com.github.nicklib.data.utils.Tuple;
import com.roje.cashshop.CashShopPlugin;
import com.roje.cashshop.data.GuiType;
import com.roje.cashshop.utils.LoreUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static com.roje.cashshop.data.CashShopMapData.*;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!inventoryNameMap.containsKey(player)) return;
        if (shopTypeMap.get(player) == GuiType.OPEN) event.setCancelled(true);
        if (event.getClickedInventory() == player.getInventory()) return;
        if (event.getClickedInventory() == null) return;
        if (!shopTypeMap.containsKey(player)) return;

        Config config;

        if (shopTypeMap.get(player) == GuiType.EDIT) {

            /**
             * 구매 가격 설정
             */

            if (event.getClick() == ClickType.SHIFT_LEFT) {
                priceMap.put(player, new Tuple<>(inventoryNameMap.get(player), event.getSlot()));
                priceTypeMap.put(player, "구매");
                player.closeInventory();
            }

            /**
             * 판매 가격 설정
             */

            if (event.getClick() == ClickType.SHIFT_RIGHT) {
                priceMap.put(player, new Tuple<>(inventoryNameMap.get(player), event.getSlot()));
                priceTypeMap.put(player, "판매");
                player.closeInventory();
            }

            /**
             * 아이템을 뺼때
             */

            if (event.getCurrentItem() != null) {
                config = new Config("shop/" + inventoryNameMap.get(player), CashShopPlugin.getPlugin());
                if (config.getConfig().get(inventoryNameMap.get(player) + ".items." + event.getSlot() + ".lore") == null) {
                    ItemStack item = event.getCurrentItem();
                    ItemMeta meta = item.getItemMeta();
                    meta.setLore(null);
                    event.setCursor(item);
                }




                config.setObject(inventoryNameMap.get(player) + ".items." + event.getSlot(), null);
            }

            /**
             * 아이템을 넣을때
             */

            if (event.getCurrentItem() == null) {
                config = new Config("shop/" + inventoryNameMap.get(player), CashShopPlugin.getPlugin());
                config.setObject(inventoryNameMap.get(player) + ".items." + event.getSlot() + ".meta", event.getCursor());
                LoreUtil loreUtil = new LoreUtil(event.getCursor());
                ItemMeta meta = event.getCursor().getItemMeta();
                List<String> lore = meta.getLore();
                if (lore == null) {
                    event.getClickedInventory().setItem(event.getSlot(), LoreUtil.setItemMeta(List.of("§c구매가격 설정 : Shift + 좌클릭", "§a판매가격 설정 : Shift + 우클릭")));
                    event.getClickedInventory().remove(event.getCursor());
                }
                if (lore != null) {
                    lore.add("§c구매가격 설정 : Shift + 좌클릭");
                    lore.add("§a판매가격 설정 : Shift + 우클릭");
                    event.getClickedInventory().setItem(event.getSlot(), LoreUtil.setItemMeta(lore));
                    event.getClickedInventory().remove(event.getCursor());
                }
            }

        }

        if (shopTypeMap.get(player) == GuiType.OPEN) {

        }
    }
}
