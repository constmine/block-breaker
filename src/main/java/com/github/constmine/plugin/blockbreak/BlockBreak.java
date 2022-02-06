package com.github.constmine.plugin.blockbreak;

import com.github.constmine.plugin.blockbreak.command.CommandBlockBreak;
import com.github.constmine.plugin.blockbreak.command.CommandTest;
import com.github.constmine.plugin.blockbreak.event.EventBlock;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockBreak extends JavaPlugin {

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        config = getConfig();
        getCommand("bb").setExecutor(new CommandBlockBreak());
        getCommand("t").setExecutor(new CommandTest());

        Bukkit.getPluginManager().registerEvents(new EventBlock(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
