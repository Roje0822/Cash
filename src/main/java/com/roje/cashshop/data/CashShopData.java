package com.roje.cashshop.data;

import com.github.nicklib.data.Config;
import com.github.nicklib.utils.InventoryUtil;
import com.roje.cashshop.CashShopPlugin;
import com.roje.cashshop.utils.LoreUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

import static com.roje.cashshop.data.CashShopMapData.*;

public class CashShopData implements CashShopImpl {


    private final Config config;
    private final String name;

    public CashShopData(String name) {
        this.config = new Config("shop/" + name, CashShopPlugin.getPlugin());
        this.name = name;
    }

    /**
     * 캐쉬샵 생성
     */
    @Override
    public void createShop() {
        config.getConfig().set(name + ".items", new HashMap<>());
        config.saveConfig();
    }

    /**
     * 캐쉬샵 삭제
     */
    @Override
    public void deleteShop() {
        config.deleteFile();
    }


    public void openShop(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, name);
        for (int i = 0; i < 54; i++) {
            ItemStack item = config.getConfig().getItemStack(name + ".items." + i);
            if (item != null) {
                inventory.setItem(i, item);
                LoreUtil loreUtil = new LoreUtil(item);
                String buy = String.valueOf(config.getInt(name + ".price." + i + ".buy"));
                String sell = String.valueOf(config.getInt(name + ".price." + i + ".sell"));
                List<String> lore = item.getItemMeta().getLore();
                if (buy == null) {
                    buy = "0";
                }
                if (sell == null) {
                    sell = "0";
                }
                if (lore == null) {
                    inventory.setItem(i, loreUtil.setItemMeta(List.of("구매가격 : " + buy, "판매가격 : " + sell)));
                }
                if (lore != null) {
                    inventory.setItem(i, loreUtil.setItemMeta(List.of("" + item.getItemMeta().getLore(), "구매가격 : " + buy, "판매가격 : " + sell)));
                }
            }
        }
        shopTypeMap.put(player, "일반");
        inventoryClickMap.put(player, name);
        player.openInventory(inventory);
    }
    /**
     * 캐쉬샵 편집 모드 오픈
     */
    public void editShop(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, name);
        for (int i = 0; i < 54; i++) {
            ItemStack item = config.getConfig().getItemStack(name + ".items." + i);
            if (item != null) {
                LoreUtil loreUtil = new LoreUtil(item);
                inv.setItem(i, loreUtil.setItemMeta(List.of("test", "test2")));
            }
        }
        shopTypeMap.put(player, "편집");
        inventoryClickMap.put(player, name);
        player.openInventory(inv);
    }


    /**
     * @return 존재 여부
     */
    public Boolean isShopExist() {
        return config.isFileExist();
    }
}
