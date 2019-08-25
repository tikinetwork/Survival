package dev.foolen.survival.modules.home;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.home.commands.DelHome;
import dev.foolen.survival.modules.home.commands.Home;
import dev.foolen.survival.modules.home.commands.Homes;
import dev.foolen.survival.modules.home.commands.SetHome;
import dev.foolen.survival.modules.home.listeners.PlayerMove;
import dev.foolen.survival.modules.home.listeners.PlayerQuit;
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

public class HomeModule {

    private static HashMap<UUID, HashMap<String, Location>> homes;
    private static ArrayList<UUID> teleportingPlayers;

    public HomeModule() {
        teleportingPlayers = new ArrayList<>();

        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        loadHomes(plugin);

        registerCommands(plugin);
        registerEvents(plugin);
    }

    private static void loadHomes(SurvivalPlugin plugin) {
        homes = new HashMap<>();

        ConfigFile cf = new ConfigFile(plugin.getDataFolder(), "homes");
        cf.createConfig();
        Configuration config = cf.getConfig();

        for (String strUUID : config.getConfigurationSection("").getKeys(false)) {
            UUID uuid = UUID.fromString(strUUID);
            homes.put(uuid, new HashMap<>());

            for (String homeName : config.getConfigurationSection(strUUID).getKeys(false)) {
                World world = Bukkit.getWorld(config.getString(strUUID + "." + homeName + ".world"));
                int x = config.getInt(strUUID + "." + homeName + ".x");
                int y = config.getInt(strUUID + "." + homeName + ".y");
                int z = config.getInt(strUUID + "." + homeName + ".z");
                float pitch = config.getLong(strUUID + "." + homeName + ".pitch");
                float yaw = config.getLong(strUUID + "." + homeName + ".yaw");

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

    private void registerEvents(SurvivalPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new PlayerMove(), plugin);
        pm.registerEvents(new PlayerQuit(), plugin);
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("sethome").setExecutor(new SetHome());
        plugin.getCommand("delhome").setExecutor(new DelHome());
        plugin.getCommand("homes").setExecutor(new Homes());
        plugin.getCommand("home").setExecutor(new Home());
    }

    public static boolean isTeleporting(Player p) {
        return teleportingPlayers.contains(p.getUniqueId());
    }

    public static void addTeleportingPlayer(Player p) {
        teleportingPlayers.add(p.getUniqueId());
    }

    public static void removeTeleportingPlayer(Player p) {
        teleportingPlayers.remove(p.getUniqueId());
    }
}
