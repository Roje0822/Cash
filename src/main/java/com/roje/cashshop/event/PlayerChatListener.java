package com.roje.cashshop.event;

import com.github.nicklib.data.Config;
import com.roje.cashshop.CashShopPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.roje.cashshop.data.CashShopMapData.priceMap;
import static com.roje.cashshop.data.CashShopMapData.priceTypeMap;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String finalMessage = event.getMessage();

        if (priceMap.containsKey(player)) {
            event.setCancelled(true);

            if (priceTypeMap.get(player).equals("구매")) {
                Config config = new Config("shop/" + priceMap.get(player).getA(), CashShopPlugin.getPlugin());
                if (finalMessage.equals("불가")) {
                    config.setString(priceMap.get(player).getA() + ".items." + priceMap.get(player).getB().toString() + ".price.buy", "불가");
                    return;
                }
                try {
                    config.setInt(priceMap.get(player).getA() + ".items." + priceMap.get(player).getB().toString() + ".price.buy", Integer.parseInt(finalMessage));
                    player.sendMessage("구매 가격을 " + finalMessage + " 으로 설정하였습니다.");
                    priceMap.remove(player);
                } catch (NumberFormatException e) {
                    player.sendMessage("정수로 입력해주세요.");
                }
            }
            if (priceTypeMap.get(player).equals("판매")) {
                Config config = new Config("shop/" + priceMap.get(player).getA(), CashShopPlugin.getPlugin());
                if (finalMessage.equals("불가")) {
                    config.setString(priceMap.get(player).getA() + ".items." + priceMap.get(player).getB().toString() + ".price.sell", "불가");
                    return;
                }
                try {
                    config.setInt(priceMap.get(player).getA() + ".items." + priceMap.get(player).getB().toString() + ".price.sell", Integer.parseInt(finalMessage));
                    player.sendMessage("판매 가격을 " + finalMessage + "으로 설정하였습니다.");
                    priceMap.remove(player);
                } catch (NumberFormatException e) {
                    player.sendMessage("정수로 입력해주세요.");
                }
            }

        }

    }
}
