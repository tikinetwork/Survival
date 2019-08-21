package dev.foolen.survival.modules.teleportation;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.teleportation.commands.TPA;
import dev.foolen.survival.modules.teleportation.commands.TPAccept;
import dev.foolen.survival.modules.teleportation.commands.TPDeny;
import dev.foolen.survival.modules.teleportation.commands.Teleport;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class TeleportationModule {

    private static HashMap<Player, ArrayList<Player>> teleportRequests;

    public TeleportationModule() {
        teleportRequests = new HashMap<>();

        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        registerCommands(plugin);
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("/tp").setExecutor(new Teleport());
        plugin.getCommand("/teleport").setExecutor(new Teleport());
        plugin.getCommand("/tpa").setExecutor(new TPA());
        plugin.getCommand("/tpaccept").setExecutor(new TPAccept());
        plugin.getCommand("/tpdeny").setExecutor(new TPDeny());
    }

    public static boolean makeTeleportRequest(Player from, Player to) {
        if (teleportRequests.get(from).contains(to)) {
            return false;
        } else {
            if (teleportRequests.get(from) == null) {
                teleportRequests.put(from, new ArrayList<>());
            }

            teleportRequests.get(from).add(to);
            return true;
        }
    }

    public static void respondToRequest(Player from, Player to) {
        if (teleportRequests.get(from).size() == 1) {
            teleportRequests.remove(from);
        } else {
            teleportRequests.get(from).remove(to);
        }
    }
}
