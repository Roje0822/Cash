package com.roje.cashshop.command;

import com.roje.cashshop.data.CashShopData;
import com.roje.cashshop.data.CashShopEditorData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import static com.roje.cashshop.data.CashShopMapData.shopMap;

public class CashShopCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage("§c콘솔에서는 사용할 수 없는 명령어 입니다");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("올바른 사용법 : /상점 생섬 /상점 삭제");
            return true;
        }

        String name;
        CashShopData cashShopData;


        switch (args[0]) {
            case "생성" -> {
                if (args.length == 1) {
                    player.sendMessage("상점이름을 입력해주세요");
                    return true;
                }
                name = args[1];
                cashShopData = new CashShopData(name);
                if(cashShopData.isShopExist()) {
                    player.sendMessage("이미 상점이 존재합니다");
                    return true;
                }


                cashShopData.createShop();
                player.sendMessage("§a" + name + "§f 상점을 생성했습니다");
                return true;
            }
            case "삭제" -> {
                if (args.length == 1) {
                    player.sendMessage("상점이름을 입력해주세요");
                    return true;
                }

                name = args[1];
                cashShopData = new CashShopData(name);
                if (!cashShopData.isShopExist()) {
                    player.sendMessage("이미 상점이 존재합니다");
                    return true;
                }
                name = args[1];
                cashShopData = new CashShopData(name);
                cashShopData.deleteShop();
                player.sendMessage("§a" + name + "§f 상점을 삭제했습니다");
                return true;
            }
            case "편집" -> {
                if (args.length == 1) {
                    player.sendMessage("상점이름을 입력해주세요");
                    return true;
                }

                name = args[1];
                cashShopData = new CashShopData(name);
                Inventory inv = Bukkit.createInventory(null, 54, name);
                player.openInventory(inv);
                shopMap.put(player.getUniqueId(), cashShopData);
                return true;
            }

            case "테스트" -> {
                System.out.println(shopMap.get(player.getUniqueId()));
                return true;
            }

            default -> {
                player.sendMessage("올바른 사용법 : /상점 생성 /상점 삭제");
                return true;
            }
        }

    }
}
