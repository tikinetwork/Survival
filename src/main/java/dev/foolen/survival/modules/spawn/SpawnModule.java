package dev.foolen.survival.modules.spawn;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.spawn.commands.SetSpawn;
import dev.foolen.survival.modules.spawn.commands.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;

public class SpawnModule {

    private static Location spawnLocation;

    public SpawnModule() {
        loadSpawnLocation();

        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        registerCommands(plugin);
    }

    private void loadSpawnLocation() {
        Configuration config = SurvivalPlugin.getInstance().getConfig();

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
}
