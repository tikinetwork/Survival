package dev.foolen.survival;

import dev.foolen.survival.modules.connectionmessages.ConnectionMessageModule;
import dev.foolen.survival.modules.enhancedmobdrops.EnhancedMobDropsModule;
import dev.foolen.survival.modules.fly.FlyModule;
import dev.foolen.survival.modules.home.HomeModule;
import dev.foolen.survival.modules.rules.RulesModule;
import dev.foolen.survival.modules.sleeppercentage.SleepPercentageModule;
import dev.foolen.survival.modules.spawn.SpawnModule;
import dev.foolen.survival.modules.warp.WarpModule;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalPlugin extends JavaPlugin {

    private static SurvivalPlugin instance;
    public static String PREFIX;

    @Override
    public void onEnable() {
        instance = this;

        // Load default configuration options
        saveDefaultConfig();
        PREFIX = ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"));

        // Load modules
        new SpawnModule();
        new WarpModule();
        new HomeModule();
        new ConnectionMessageModule();
        new SleepPercentageModule();
        new EnhancedMobDropsModule();
        new RulesModule();
        new FlyModule();
    }

    public static SurvivalPlugin getInstance() {
        return instance;
    }
}
