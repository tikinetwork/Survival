package dev.foolen.survival.modules.spawn;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.spawn.commands.SetSpawn;
import dev.foolen.survival.modules.spawn.commands.Spawn;
import dev.foolen.survival.modules.spawn.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.UUID;

public class SpawnModule {

    private static Location spawnLocation;
    private static ArrayList<UUID> teleportingPlayers;

    public SpawnModule() {
        teleportingPlayers = new ArrayList<>();

        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        loadSpawnLocation(plugin);

        registerCommands(plugin);
        registerEvents(plugin);
    }

    private void loadSpawnLocation(SurvivalPlugin plugin) {
        Configuration config = plugin.getConfig();

        if (config.isSet("spawn.x") &&
            config.isSet("spawn.y") &&
            config.isSet("spawn.z") &&
            config.isSet("spawn.yaw") &&
            config.isSet("spawn.pitch") &&
            config.isSet("spawn.world")) {

            World world = Bukkit.getWorld(config.getString("spawn.world"));
            int x = config.getInt("spawn.x");
            int y = config.getInt("spawn.y");
            int z = config.getInt("spawn.z");
            float yaw = config.getLong("spawn.yaw");
            float pitch = config.getLong("spawn.pitch");

            spawnLocation = new Location(world, x, y, z, yaw, pitch);
        } else {
            spawnLocation = null;
        }
    }

    public static void setSpawnLocation(Location spawnLocation) {
        SpawnModule.spawnLocation = spawnLocation;
    }

    public static Location getSpawnLocation() {
        return spawnLocation;
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("setspawn").setExecutor(new SetSpawn());
        plugin.getCommand("spawn").setExecutor(new Spawn());
    }

    private void registerEvents(SurvivalPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new PlayerJoin(), plugin);
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
