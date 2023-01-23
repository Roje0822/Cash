package com.roje.cashshop;

import com.roje.cashshop.command.CashCmd;
import com.roje.cashshop.command.CashShopCmd;
import com.roje.cashshop.command.CashTabComplete;
import com.roje.cashshop.event.InventoryClickListener;
import com.roje.cashshop.event.InventoryCloseListener;
import com.roje.cashshop.event.PlayerChatListener;
import com.roje.cashshop.event.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CashShopPlugin extends JavaPlugin {

    public static CashShopPlugin plugin;

    @Override
    public void onEnable() {

        init();
    }

    private void init() {

        plugin = this;


        /** command */
        Bukkit.getPluginCommand("캐시").setExecutor(new CashCmd());
        Bukkit.getPluginCommand("캐시상점").setExecutor(new CashShopCmd());

        /** event */
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this); //ㅎㅇ 라고할뻔


        /** complete */
        getCommand("캐시").setTabCompleter(new CashTabComplete());

    }


    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
