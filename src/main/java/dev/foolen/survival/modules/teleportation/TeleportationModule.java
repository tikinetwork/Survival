package dev.foolen.survival.modules.teleportation;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.teleportation.commands.*;
import dev.foolen.survival.modules.teleportation.listeners.PlayerMove;
import dev.foolen.survival.modules.teleportation.listeners.PlayerQuit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeleportationModule {

    private static HashMap<Player, ArrayList<Player>> teleportRequests;
    private static ArrayList<UUID> teleportingPlayers;

    public TeleportationModule() {
        teleportRequests = new HashMap<>();
        teleportingPlayers = new ArrayList<>();

        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        registerCommands(plugin);
        registerListeners(plugin);
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("tp").setExecutor(new Teleport());
        plugin.getCommand("teleport").setExecutor(new Teleport());
        plugin.getCommand("tpa").setExecutor(new TPA());
        plugin.getCommand("tpaccept").setExecutor(new TPAccept());
        plugin.getCommand("tpdeny").setExecutor(new TPDeny());
        plugin.getCommand("tppos").setExecutor(new TPPos());
    }

    private void registerListeners(SurvivalPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new PlayerMove(), plugin);
        pm.registerEvents(new PlayerQuit(), plugin);
    }

    public static boolean makeTeleportRequest(Player from, Player to) {
        if (teleportRequests.get(from) == null) {
            teleportRequests.put(from, new ArrayList<>());
        }

        if (teleportRequests.get(from).contains(to)) {
            return false;
        }

        teleportRequests.get(from).add(to);
        return true;
    }

    public static void respondToRequest(Player from, Player to) {
        if (teleportRequests.get(from).size() == 1) {
            teleportRequests.remove(from);
        } else {
            teleportRequests.get(from).remove(to);
        }
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
