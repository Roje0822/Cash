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
        config = new Config("shop/" + inventoryNameMap.get(player), CashShopPlugin.getPlugin());

        /**
         * gui 상점 편집 모드
         */
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
             * 아이템 뺄때 로어 삭제 및 콘피그 삭제
             */
            if (event.getCurrentItem() != null) {

                config.setObject(inventoryNameMap.get(player) + ".items." + event.getSlot(), null); // 콘피그 삭제

                ItemStack item = event.getCurrentItem();
                ItemMeta meta = item.getItemMeta();


                List<String> lore = meta.getLore();

                lore.remove("§c구매가격 설정 : Shift + 좌클릭");
                lore.remove("§a판매가격 설정 : Shift + 우클릭");

                meta.setLore(lore);
                item.setItemMeta(meta);

                event.setCurrentItem(item);

            }

            /**
             * 아이템 넣을때 로어 추가 및 콘피그 추가
             */
            if (event.getCurrentItem() == null && event.getCursor() != null) {

                config.setObject(inventoryNameMap.get(player) + ".items." + event.getSlot() + ".meta", event.getCursor()); // 콘피그 저장

                ItemStack item = event.getCursor();
                ItemMeta meta = item.getItemMeta();

                List<String> lore = meta.getLore();

                if (lore == null) {
                    meta.setLore(List.of("§c구매가격 설정 : Shift + 좌클릭", "§a판매가격 설정 : Shift + 우클릭"));
                    item.setItemMeta(meta);
                }

                if (lore != null) {
                    lore.add(List.of("§c구매가격 설정 : Shift + 좌클릭", "§a판매가격 설정 : Shift + 우클릭").toString());
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                }
            }

        }

        if (shopTypeMap.get(player) == GuiType.OPEN) {

        }
    }
}
