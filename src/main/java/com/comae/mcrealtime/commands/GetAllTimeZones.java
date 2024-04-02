package com.comae.mcrealtime.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

public class GetAllTimeZones implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> zoneIds = ZoneId.getAvailableZoneIds().stream().toList();

        for(String id : zoneIds) {
            commandSender.sendMessage(id);
        }
        return true;
    }
}
