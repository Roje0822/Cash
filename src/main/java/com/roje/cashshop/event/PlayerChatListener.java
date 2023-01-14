package com.roje.cashshop.event;

import com.github.nicklib.data.Config;
import com.roje.cashshop.CashShopPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.roje.cashshop.data.CashShopMapData.priceMap;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String finalMessage = event.getMessage();

        if (priceMap.containsKey(player)) {
            event.setCancelled(true);

            Config config = new Config("shop/" + priceMap.get(player).getA(), CashShopPlugin.getPlugin());
            config.setInt(priceMap.get(player).getA() + ".items." + priceMap.get(player).getB().toString() + ".price", Integer.parseInt(finalMessage));
            player.sendMessage(finalMessage + "의 가격으로 설정하였습니다.");
            priceMap.remove(player);

        }

    }
}
