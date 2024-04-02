package com.comae.mcrealtime.methods;

import com.comae.mcrealtime.McRealTim;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.generator.WorldInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class WorldMethods {

    public static void setWorldZone(UUID worldId, String zoneId) {
        McRealTim.instance.getLogger().info("start file create");
        List<UUID> worlds = McRealTim.instance.getServer().getWorlds().stream().map(WorldInfo::getUID).toList();


        if (worlds.contains(worldId)) {
            File worldFile = new File("plugins/McRealTime/worlds", worldId + ".yml");
            FileConfiguration worldConfig = YamlConfiguration.loadConfiguration(worldFile);



            worldConfig.set("zone", zoneId);
            McRealTim.instance.getLogger().info("start saving!");
            try {
                worldConfig.save(worldFile);
                McRealTim.instance.getLogger().info("file saved!");
            } catch (IOException e) {
                McRealTim.instance.getLogger().warning("Fehler beim Speichern der Konfiguration: " + e.getMessage());
            }
        }
    }
}
