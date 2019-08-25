package dev.foolen.survival.modules.warp;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.warp.commands.DelWarp;
import dev.foolen.survival.modules.warp.commands.SetWarp;
import dev.foolen.survival.modules.warp.commands.Warp;
import dev.foolen.survival.modules.warp.commands.Warps;
import dev.foolen.survival.modules.warp.listeners.PlayerMove;
import dev.foolen.survival.modules.warp.listeners.PlayerQuit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.shanerx.configapi.ConfigFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class WarpModule {

    private static HashMap<String, Location> warpLocations;
    private static ArrayList<UUID> teleportingPlayers;

    public WarpModule() {
        teleportingPlayers = new ArrayList<>();

        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        loadWarpLocations(plugin);

        registerCommands(plugin);
        registerEvents(plugin);
    }

    private void loadWarpLocations(SurvivalPlugin plugin) {
        ConfigFile cf = new ConfigFile(plugin.getDataFolder(), "warps");
        cf.createConfig();
        Configuration config = cf.getConfig();

        warpLocations = new HashMap<>();

        for (String warpName : config.getConfigurationSection("").getKeys(false)) {
            World world = Bukkit.getWorld(config.getString(warpName + ".world"));
            int x = config.getInt(warpName + ".x");
            int y = config.getInt(warpName + ".y");
            int z = config.getInt(warpName + ".z");
            float yaw = config.getLong(warpName + ".yaw");
            float pitch = config.getLong(warpName + ".pitch");

            warpLocations.put(warpName, new Location(world, x, y, z, yaw, pitch));
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

    private void registerEvents(SurvivalPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new PlayerMove(), plugin);
        pm.registerEvents(new PlayerQuit(), plugin);
    }

    public static boolean isTeleporting(UUID uuid) {
        return teleportingPlayers.contains(uuid);
    }

    public static void addTeleportingPlayer(UUID uuid) {
        if (!teleportingPlayers.contains(uuid)) {
            teleportingPlayers.add(uuid);
        }
    }

    public static void removeTeleportingPlayer(UUID uuid) {
        if (teleportingPlayers.contains(uuid)) {
            teleportingPlayers.remove(uuid);
        }
    }
}
