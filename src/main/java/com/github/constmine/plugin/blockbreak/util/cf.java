package com.github.constmine.plugin.blockbreak.util;

import com.github.constmine.plugin.blockbreak.BlockBreak;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class cf {
    private static FileConfiguration config = BlockBreak.config;

    public static void setGameStart(boolean start) { config.set("Game", start); }

    public static boolean isGameStart() { return config.getBoolean("Game"); }

    public static void setPlayer(Player player) { config.set("Player", player); }

    public static Player getPlayer() { return (Player) config.get("Player"); }

    public static void setBlock(Material block) { config.set("Block", block); }

    public static Material getBlock() { return Material.getMaterial((String) Objects.requireNonNull(config.get("Block"))); }

    public static void initialize() {
        setGameStart(false);
        setPlayer(null);
    }
}
