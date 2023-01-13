package com.roje.cashshop.event;

import com.github.nicklib.data.Config;
import com.roje.cashshop.CashShopPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        Config config = new Config("data/" + player.getUniqueId(), CashShopPlugin.getPlugin());

        /* player first join setting */

        if(!config.isFileExist()) {
            config.setLong("cash", 0l); config.saveConfig();
        }

    }
}
