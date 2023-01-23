package com.roje.cashshop.data;

import com.github.nicklib.data.Config;
import com.roje.cashshop.CashShopPlugin;
import com.roje.cashshop.data.impl.CashShopImpl;
import com.roje.cashshop.utils.LoreUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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


    /**
     * 캐쉬샵 오픈
     * @param player
     */
    public void openShop(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, name);
        for (int i = 0; i < 54; i++) {
            ItemStack item = config.getConfig().getItemStack(name + ".items." + i + ".meta");
            if (item != null) {
                LoreUtil loreUtil = new LoreUtil(item);
                String buy = String.valueOf(config.getInt(name + ".items." + i + ".price.buy"));
                String sell = String.valueOf(config.getInt(name + ".items." + i + ".price.sell"));
                List<String> lore = item.getItemMeta().getLore();
                if (buy == null) {
                    buy = "0";
                }
                if (sell == null) {
                    sell = "0";
                }
                if (lore == null) {
                    inv.setItem(i, LoreUtil.setItemMeta(List.of("§c구매가격 : " + buy, "§a판매가격 : " + sell)));
                }
                if (lore != null) {
                    ItemMeta meta = item.getItemMeta();
                    lore.add("§c구매가격 : " + buy);
                    lore.add("§a판매가격 : " + sell);
                    inv.setItem(i, LoreUtil.setItemMeta(lore));
                }
            }
        }
        shopTypeMap.put(player, GuiType.OPEN);
        inventoryNameMap.put(player, name);
        player.openInventory(inv);

    }

    /**
     * 캐쉬샵 편집 모드 오픈
     */
    public void editShop(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, name);
        for (int i = 0; i < 54; i++) {
            ItemStack item = config.getConfig().getItemStack(name + ".items." + i + ".meta");
            if (item != null) {
                ItemMeta meta = item.getItemMeta();
                LoreUtil loreUtil = new LoreUtil(item);
                List<String> lore = item.getItemMeta().getLore();
                if (lore == null) {
                    meta.setLore(List.of("§c구매가격 설정 : Shift + 좌클릭", "§a판매가격 설정 : Shift + 우클릭"));
                    item.setItemMeta(meta);
                    inv.setItem(i, item);
                }
                if (lore != null) {
                    lore.add(List.of("§c구매가격 설정 : Shift + 좌클릭", "§a판매가격 설정 : Shift + 우클릭").toString());
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    inv.setItem(i, item);
                }
            }
        }
        shopTypeMap.put(player, GuiType.EDIT);
        inventoryNameMap.put(player, name);
        player.openInventory(inv);

    }


    /**
     * @return 존재 여부
     */
    public Boolean isShopExist() {
        return config.isFileExist();
    }
}
