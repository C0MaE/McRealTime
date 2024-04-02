package com.comae.mcrealtime.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.comae.mcrealtime.methods.WorldMethods.setWorldZone;

public class setWorldZoneCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player player)) return false;
        if(strings.length != 1) return false;

        UUID worldId = player.getWorld().getUID();

        setWorldZone(worldId, strings[0]);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 1) {
            String zone = strings[0];
            List<String> zoneIds = ZoneId.getAvailableZoneIds().stream().toList();
            List<String> matches = new ArrayList<>();

            for(String id : zoneIds) {
                if(id.toLowerCase().contains(zone.toLowerCase())) {
                    matches.add(id);
                }
            }

            return matches;
        } else {
            return null;
        }
    }
}
