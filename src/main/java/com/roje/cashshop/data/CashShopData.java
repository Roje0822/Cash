package com.roje.cashshop.data;

import com.github.nicklib.data.Config;
import com.github.nicklib.utils.InventoryUtil;
import com.roje.cashshop.CashShopPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class CashShopData implements CashShopImpl {


    private final Config config;
    private final String name;

    public CashShopData(String name) {
        this.config = new Config("shop/" + name, CashShopPlugin.getPlugin());
        this.name = name;
    }

    @Override
    public void createShop() {
        config.getConfig().set(name + ".items", new HashMap<>());
        config.saveConfig();
    }

    @Override
    public void deleteShop() {
        config.deleteFile();
    }


    public void editShop(Player player) {
        InventoryUtil inventoryUtil = new InventoryUtil(player, config);
        Inventory inv = Bukkit.createInventory(null, 54, name);
        inventoryUtil.getInventory(inv, name);
    }


    public Boolean isShopExist() {
        return config.isFileExist();
    }
}
