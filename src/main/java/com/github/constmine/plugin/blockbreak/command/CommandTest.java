package com.github.constmine.plugin.blockbreak.command;

import com.github.constmine.plugin.blockbreak.util.cf;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandTest implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Bukkit.broadcast(Component.text(
                "\nGame 진행 : " + cf.isGameStart() +
                        "\nPlayer : " + cf.getPlayer() +
                        "\nBlock : " + cf.getBlock()
        ));

        TextComponent textComponent = Component.text("Block ", NamedTextColor.AQUA)
                .append(Component.text("Breaker", NamedTextColor.RED));

        Bukkit.broadcast(textComponent);
        return false;
    }
}
