package com.comae.mcrealtime.methods;

import com.comae.mcrealtime.McRealTim;
import org.bukkit.World;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.comae.mcrealtime.methods.TimeMethods.toMcTime;

public class PluginRunnables {

    public static void setWorldTime() {
        McRealTim.instance.getLogger().info("run SetWorldTime");
        for(Map.Entry<World, String> entries : McRealTim.instance.worlds.entrySet()) {
            World world = entries.getKey();
            String zoneId = entries.getValue();

           LocalDateTime now = LocalDateTime.now(ZoneId.of(zoneId));
           Long mcTime = toMcTime(now);

           world.setTime(mcTime);
        }
    }

}
