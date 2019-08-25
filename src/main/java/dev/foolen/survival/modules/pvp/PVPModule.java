package dev.foolen.survival.modules.pvp;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.pvp.commands.PVP;
import dev.foolen.survival.modules.pvp.listeners.EntityDamage;
import dev.foolen.survival.modules.pvp.listeners.PlayerMove;
import dev.foolen.survival.modules.pvp.listeners.PlayerQuit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.UUID;

public class PVPModule {

    private static ArrayList<UUID> pvpPlayers;
    private static ArrayList<UUID> togglingPlayers;

    public PVPModule() {
        pvpPlayers = new ArrayList<>();
        togglingPlayers = new ArrayList<>();

        SurvivalPlugin plugin = SurvivalPlugin.getInstance();

        registerCommands(plugin);
        registerEvents(plugin);
    }

    private void registerCommands(SurvivalPlugin plugin) {
        plugin.getCommand("pvp").setExecutor(new PVP());
    }

    private void registerEvents(SurvivalPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new EntityDamage(), plugin);
        pm.registerEvents(new PlayerMove(), plugin);
        pm.registerEvents(new PlayerQuit(), plugin);
    }

    public static boolean togglePVP(Player p) {
        UUID uuid = p.getUniqueId();

        if (pvpPlayers.contains(uuid)) {
            pvpPlayers.remove(uuid);
            return false;
        } else {
            pvpPlayers.add(uuid);
            return true;
        }
    }

    public static boolean hasPVPEnabled(Player p) {
        return pvpPlayers.contains(p.getUniqueId());
    }

    public static void addTogglingPlayer(Player p) {
        togglingPlayers.add(p.getUniqueId());
    }

    public static void removeTogglingPlayer(Player p) {
        togglingPlayers.remove(p.getUniqueId());
    }

    public static boolean isToggling(Player p) {
        return togglingPlayers.contains(p.getUniqueId());
    }
}


