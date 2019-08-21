package dev.foolen.survival.modules.home;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.home.commands.DelHome;
import dev.foolen.survival.modules.home.commands.Home;
import dev.foolen.survival.modules.home.commands.Homes;
import dev.foolen.survival.modules.home.commands.SetHome;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.shanerx.configapi.ConfigFile;

import java.util.HashMap;
import java.util.UUID;

public class HomeModule {

    private static HashMap<UUID, HashMap<String, Location>> homes;

    public HomeModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        loadHomes(plugin);

        registerCommands(plugin);
    }

    private static void loadHomes(SurvivalPlugin plugin) {
        homes = new HashMap<>();

        ConfigFile cf = new ConfigFile(plugin.getDataFolder(), "homes");
        cf.createConfig();
        Configuration config = cf.getConfig();

        for (String uuid : config.getConfigurationSection("").getKeys(false)) {
            for (String homeName : config.getConfigurationSection(uuid).getKeys(false)) {
                World world = Bukkit.getWorld(config.getString(uuid + "." + homeName + ".world"));
                int x = config.getInt(uuid + "." + homeName + ".x");
                int y = config.getInt(uuid + "." + homeName + ".y");
                int z = config.getInt(uuid + "." + homeName + ".z");
                float pitch = config.getLong(uuid + "." + homeName + ".pitch");
                float yaw = config.getLong(uuid + "." + homeName + ".yaw");

                homes.get(uuid).put(homeName, new Location(world, x, y, z, pitch, yaw));
            }
        }
    }

    public static Location getHomeFromPlayer(UUID uuid, String name) {
        return homes.get(uuid).get(name);
    }

    public static void removeHomeFromPlayer(UUID uuid, String name) {
        homes.get(uuid).remove(name);
    }

    public static void removePlayer(UUID uuid) {
        homes.remove(uuid);
    }

    public static HashMap<String, Location> getHomesFromPlayer(UUID uuid) {
        return homes.get(uuid);
    }

    public static void setHome(UUID uuid, String name, Location location) {
        if (homes.get(uuid) == null) {
            homes.put(uuid, new HashMap<>());
        }

        homes.get(uuid).put(name, location);
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("sethome").setExecutor(new SetHome());
        plugin.getCommand("delhome").setExecutor(new DelHome());
        plugin.getCommand("homes").setExecutor(new Homes());
        plugin.getCommand("home").setExecutor(new Home());
    }
}
