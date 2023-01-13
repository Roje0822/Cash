package com.roje.cashshop.command;

import com.roje.cashshop.CashShopPlugin;
import com.roje.cashshop.data.CashData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CashCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            System.out.println("§c버킷에서는 명령어를 실행 할 수 없습니다");
        } else {

            if (args.length == 0) {
                player.sendMessage("" + new CashData(player).getPlayerCash());
                return true;
            }

            Player target = CashShopPlugin.getPlugin().getServer().getPlayer(args[1]);

            CashData cashData = new CashData(target);
            Long amount;

            if (player.isOp()) {

                switch (args[0]) {
                    case "지급", "주기" -> {
                        try {
                            amount = Long.parseLong(args[2]);
                            cashData.addCash(amount);
                            player.sendMessage("&6 " + target.getName() + "님의 캐시를 §a" + args[2] + " §f만큼 차감했습니다");

                        } catch (Exception e) {
                            player.sendMessage("&c명령어가 잘못 되었습니다");
                        }

                    }
                    case "빼기", "차감" -> {
                        try {
                            amount = Long.parseLong(args[2]);
                            cashData.subtractCash(amount);
                            player.sendMessage("&6 " + target.getName() + "님의 캐시를 §a" + args[2] + " §f만큼 차감했습니다");
                        } catch (NumberFormatException e) {
                            player.sendMessage("§c숫자를 입력해주세요");
                        }
                    }
                    case "설정" -> {
                        try {
                            amount = Long.parseLong(args[2]);
                            cashData.setCash(amount);
                            player.sendMessage("&6 " + target.getName() + "님의 캐시를 §a" + args[2] + " §f으로 설정했습니다");
                        } catch (NumberFormatException e) {
                            player.sendMessage("§c숫자를 입력해주세요");
                        }
                    }
                    case "초기화" -> {
                        cashData.setCash(0l);
                        player.sendMessage("&6 " + target.getName() + "님의 캐시를 초기화했습니다");

                    }

                }
            } else {
                player.sendMessage("§c당신은 권한이 없습니다");
            }
        }

        return false;
    }
}
