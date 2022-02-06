package com.github.constmine.plugin.blockbreak.command;

import com.github.constmine.plugin.blockbreak.util.cf;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandBlockBreak implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if(canSubCommand(args, 0,"start")) {

            if (canSubCommand(args, 1)) {

                if(Bukkit.getPlayer(args[1]) != null) {

                    cf.setGameStart(true);
                    cf.setPlayer(Bukkit.getPlayer(args[1]));

                } else {

                    player.sendMessage("/bb start <PlayerName> ");
                }
            }

        } else if(canSubCommand(args, 0, "stop")) {

            cf.setGameStart(false);
            cf.setPlayer(null);

        }

        return false;
    }


    public boolean canSubCommand(String[] args, int index) {
        if(args.length > index) {
            return true;
        }
        return false;
    }
    public boolean canSubCommand(String[] args, int index ,String subcommand) {
        if(args.length > index) {
            return args[index].equalsIgnoreCase(subcommand);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if(args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("start");
            list.add("stop");
            return list;
        }

        return null;
    }
}
