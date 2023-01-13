package com.roje.cashshop.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CashTabComplete implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (player.isOp()) {
                if (args.length == 1) {
                    return List.of("지급", "차감", "설정", "초기화");
                }
                if (args[0].equals("지급") || args[0].equals("주기") || args[0].equals("빼기") || args[0].equals("차감")) {
                    if (args.length == 2) {
                        for (Player target : player.getServer().getOnlinePlayers()) {
                            return List.of(target.getName());
                        }
                    }
                    if (args.length == 3) {
                        return List.of("<amount>");
                    }
                    if (args.length > 3) {
                        return List.of("명령어가 잘못 되었습니다");
                    }

                }
                if (args[0].equals("설정") || args[0].equals("초기화")) {
                    if (args.length == 2) {
                        for (Player target : player.getServer().getOnlinePlayers()) {
                            return List.of(target.getName());
                        }
                    }
                    if (args.length > 2) {
                        return List.of("명령어가 잘못 되었습니다");
                    }
                }
            }
        }

        return null;
    }
}
