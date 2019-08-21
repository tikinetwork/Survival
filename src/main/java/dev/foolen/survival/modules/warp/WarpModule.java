package dev.foolen.survival.modules.warp;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.warp.commands.DelWarp;
import dev.foolen.survival.modules.warp.commands.SetWarp;
import dev.foolen.survival.modules.warp.commands.Warp;
import dev.foolen.survival.modules.warp.commands.Warps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;

import java.util.HashMap;

public class WarpModule {

    private static HashMap<String, Location> warpLocations;

    public WarpModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        loadWarpLocations(plugin);

        registerCommands(plugin);
    }

    private void loadWarpLocations(SurvivalPlugin plugin) {
        Configuration config = plugin.getConfig();

        warpLocations = new HashMap<>();

        if (config.isSet("warps")) {
            for(String warpName : config.getConfigurationSection("warps").getKeys(false)) {
                World world = Bukkit.getWorld(config.getString("warps." + warpName + ".world"));
                int x = config.getInt("warps." + warpName + ".x");
                int y = config.getInt("warps." + warpName + ".y");
                int z = config.getInt("warps." + warpName + ".z");
                float yaw = config.getLong("warps." + warpName + ".yaw");
                float pitch = config.getLong("warps." + warpName + ".pitch");

                warpLocations.put(warpName, new Location(world, x, y, z, yaw, pitch));
            }
        }
    }

    public static HashMap<String, Location> getWarpLocations() {
        return warpLocations;
    }

    public static Location getWarpLocation(String name) {
        return warpLocations.get(name);
    }

    public static void setWarpLocation(String name, Location location) {
        warpLocations.put(name, location);
    }

    public static void removeWarpLocation(String name) {
        warpLocations.remove(name);
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("setwarp").setExecutor(new SetWarp());
        plugin.getCommand("delwarp").setExecutor(new DelWarp());
        plugin.getCommand("warps").setExecutor(new Warps());
        plugin.getCommand("warp").setExecutor(new Warp());
    }
}
