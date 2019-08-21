package dev.foolen.survival.modules.sleeppercentage;

import dev.foolen.survival.SurvivalPlugin;
import dev.foolen.survival.modules.sleeppercentage.listeners.PlayerBedEnter;
import dev.foolen.survival.modules.sleeppercentage.listeners.PlayerBedLeave;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

public class SleepPercentageModule {

    private static double percentageNeeded;
    private static ArrayList<Player> sleeping;

    public SleepPercentageModule() {
        SurvivalPlugin plugin = SurvivalPlugin.getInstance();
        Configuration config = plugin.getConfig();

        percentageNeeded = config.getDouble("sleepingpercentage.percentage");
        sleeping = new ArrayList<>();

        registerEvents(plugin);
    }

    private void registerEvents(SurvivalPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new PlayerBedEnter(), plugin);
        pm.registerEvents(new PlayerBedLeave(), plugin);
    }

    public static void addSleepingPlayer(Player player) {
        sleeping.add(player);
    }

    public static void removeSleepingPlayer(Player player) {
        sleeping.remove(player);
    }

    public static ArrayList<Player> getSleepingPlayers() {
        return sleeping;
    }

    public static double getPercentageSleeping() {
        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        return (100 / (double) onlinePlayers) * sleeping.size();
    }

    public static double getPercentageNeeded() {
        return percentageNeeded;
    }
}
