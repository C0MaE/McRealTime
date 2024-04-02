package com.comae.mcrealtime;

import com.comae.mcrealtime.commands.GetAllTimeZones;
import com.comae.mcrealtime.commands.GetTimeCommand;
import com.comae.mcrealtime.commands.setWorldZoneCommand;
import com.comae.mcrealtime.methods.PluginRunnables;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class McRealTim extends JavaPlugin {

    public static McRealTim instance;
    public FileConfiguration config;
    public Map<World, String> worlds;

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        this.reloadConfig();

        this.config = this.getConfig();

        Objects.requireNonNull(getCommand("getTime")).setExecutor(new GetTimeCommand());
        Objects.requireNonNull(getCommand("getTime")).setTabCompleter(new GetTimeCommand());
        Objects.requireNonNull(getCommand("getTimeZones")).setExecutor(new GetAllTimeZones());

        Objects.requireNonNull(getCommand("setWorldZone")).setExecutor(new setWorldZoneCommand());
        Objects.requireNonNull(getCommand("setWorldZone")).setTabCompleter(new setWorldZoneCommand());
        getLogger().info("McRealTime Commands was successfully enabled!");
        getLogger().info("start loading worlds");
        loadWorlds();
        getLogger().info("all worlds loaded");
        getLogger().info(worlds.toString());

        Bukkit.getScheduler().runTaskTimer(this, PluginRunnables::setWorldTime, 0, 1200);

        getLogger().info("McRealTime was successfully enabled!");
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }

    public void loadWorlds() {
        List<World> localWorlds = instance.getServer().getWorlds();

        for(World world : localWorlds) {
            File file = new File("plugins/McRealTime/worlds", world.getUID() + ".yml");
            FileConfiguration worldConfig = YamlConfiguration.loadConfiguration(file);
            if(!file.exists()) {
                worldConfig.set("name", world.getName());
                worldConfig.set("uuid", world.getUID().toString());

                try {
                    worldConfig.save(file);
                } catch (IOException e) {
                    McRealTim.instance.getLogger().warning("Fehler beim Speichern der Konfiguration: " + e.getMessage());
                }
            } else {
                String zoneId = worldConfig.getString("zone");

                if(zoneId != null) {
                    world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                    if(this.worlds == null) {
                        Map<World, String> newMap = new HashMap<>();

                        newMap.put(world, zoneId);

                        this.worlds = newMap;
                    } else {
                        this.worlds.put(world, zoneId);
                    }
                }
            }
        }
    }
}
