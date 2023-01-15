package com.roje.cashshop.data;

import com.github.nicklib.data.Config;
import com.roje.cashshop.CashShopPlugin;
import org.bukkit.entity.Player;

public class CashData {

    private Player player;

    public CashData(Player player) {
        this.player = player;
    }


    public void addCash(Long amount) {
        Config config = new Config("data/" + player.getUniqueId(), CashShopPlugin.getPlugin());
        config.setLong("cash", config.getLong("cash") + amount);
    }


    public void subtractCash(Long amount) {
        Config config = new Config("data/" + player.getUniqueId(), CashShopPlugin.getPlugin());
        config.setLong("cash", config.getLong("cash") - amount);
    }


    public void setCash(Long amount) {
        Config config = new Config("data/" + player.getUniqueId(), CashShopPlugin.getPlugin());
        config.setLong("cash", amount);
    }


    public Long getPlayerCash() {
        Config config = new Config("data/" + player.getUniqueId(), CashShopPlugin.getPlugin());
        return config.getLong("cash");
    }


    public Long getTargetCash() {
        Config config = new Config("data/" + player.getUniqueId(), CashShopPlugin.getPlugin());
        return config.getLong("cash");
    }
}
